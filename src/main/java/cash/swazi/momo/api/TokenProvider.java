package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.auth.AccessToken;

import java.io.IOException;

/**
 *  Associated with classes that help fetch/generate tokens for authentication
 */
public interface TokenProvider {
    AccessToken getToken() throws IOException, RequestFailedException;
}
