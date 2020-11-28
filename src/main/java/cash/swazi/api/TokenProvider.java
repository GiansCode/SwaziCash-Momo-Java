package cash.swazi.api;

import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.auth.AccessToken;

import java.io.IOException;

public interface TokenProvider {
    AccessToken getToken() throws IOException, RequestFailedException;
}
