package cash.swazi.client;

import cash.swazi.api.TokenProvider;
import cash.swazi.constant.Headers;
import cash.swazi.model.AccessToken;
import cash.swazi.model.AccessTokenDeserializer;
import cash.swazi.util.ResponseUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public final class AuthenticationClient extends BasicAPIClient implements TokenProvider {
    private final String tokenPath;

    private AccessToken accessToken;

    public AuthenticationClient(Options options, String tokenPath) {
        super(options, new GsonBuilder().registerTypeAdapter(AccessToken.class, new AccessTokenDeserializer()).create());
        this.tokenPath = tokenPath;
    }

    public AuthenticationClient(Options options, IRestClient client, String tokenPath) {
        super(options, client, new GsonBuilder().registerTypeAdapter(AccessToken.class, new AccessTokenDeserializer()).create());
        this.tokenPath = tokenPath;
    }

    @Override
    public AccessToken getToken() throws IOException {
        if (accessToken == null || accessToken.hasExpired()) {
            accessToken = requestNewAccessToken();
        }
        return accessToken;
    }

    private AccessToken requestNewAccessToken() throws IOException {
        Map<String,String> headers = getOptions().generateHeader(
                Headers.AUTHORIZATION,
                Headers.SUBSCRIPTION_KEY
        );

        try {
            HttpResponse response = getRestClient().post(true, "collection/token/", headers, null, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return getGson().fromJson(responseBody, AccessToken.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }
}
