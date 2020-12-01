package cash.swazi.client;

import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.api.TokenProvider;
import cash.swazi.constant.Headers;
import cash.swazi.model.auth.AccessToken;
import cash.swazi.model.auth.AccessTokenDeserializer;
import cash.swazi.util.HeaderUtils;
import cash.swazi.util.ResponseUtils;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public final class AuthenticationClient extends OptionedAPIClient implements TokenProvider {
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
    public AccessToken getToken() throws IOException, RequestFailedException {
        if (accessToken == null || accessToken.hasExpired()) {
            accessToken = requestNewAccessToken();
        }
        return accessToken;
    }

    private AccessToken requestNewAccessToken() throws IOException, RequestFailedException {
        Map<String, String> headers = HeaderUtils.generateHeader(
                getOptions(),
                Headers.SUBSCRIPTION_KEY,
                Headers.AUTHORIZATION
        );
        try {
            Response response = getRestClient().post(true, tokenPath+"/token/", headers, null, null);
            if (response.getStatusCode() != 200 || response.getBody() == null) {
                throw produceFailureException(response);
            }
            return getGson().fromJson(response.getBody(), AccessToken.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }
}
