package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.auth.AccessToken;

import java.io.IOException;

public interface TokenProvider {
    AccessToken getToken() throws IOException, RequestFailedException;
}
