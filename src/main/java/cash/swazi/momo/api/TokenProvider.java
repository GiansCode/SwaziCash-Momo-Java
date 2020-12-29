package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.auth.AccessToken;

import java.io.IOException;

/**
 *  Associated with classes that help fetch/generate tokens for authentication
 */
public interface TokenProvider {
    /**
     * @return An access token containing the bearer token required for authentication for requests to the MoMo API
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    AccessToken getToken() throws IOException, RequestFailedException;
}
