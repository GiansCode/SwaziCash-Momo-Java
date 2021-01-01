package cash.swazi.momo.client;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.api.delegate.TokenProvider;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.client.data.Response;
import cash.swazi.momo.client.internal.IRestClient;
import cash.swazi.momo.client.internal.OptionedAPIClient;
import cash.swazi.momo.constant.Headers;
import cash.swazi.momo.model.auth.AccessToken;
import cash.swazi.momo.model.auth.AccessTokenDeserializer;
import cash.swazi.momo.util.HeaderUtils;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Implementation of {@link TokenProvider}
 * Provides access tokens and handles renewal on expiry
 */
public final class AuthenticationClient extends OptionedAPIClient implements TokenProvider {
    private final String tokenPath;

    private AccessToken accessToken;

    /**
     * @param options Data required for requests
     * @param tokenPath path from baseurl to send token request to
     */
    public AuthenticationClient(Options options, String tokenPath) {
        super(options, new GsonBuilder().registerTypeAdapter(AccessToken.class, new AccessTokenDeserializer()).create());
        this.tokenPath = tokenPath;
    }

    /**
     * @param options Data required for requests
     * @param client Rest client to be used for the request
     * @param tokenPath path from baseurl to send token request to
     */
    public AuthenticationClient(Options options, IRestClient client, String tokenPath) {
        super(options, client, new GsonBuilder().registerTypeAdapter(AccessToken.class, new AccessTokenDeserializer()).create());
        this.tokenPath = tokenPath;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public AccessToken getToken() throws IOException, RequestFailedException {
        if (accessToken == null || accessToken.hasExpired()) {
            accessToken = requestNewAccessToken();
        }
        return accessToken;
    }

    /**
     * Requests an access token from the api
     * @return New access token
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
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
