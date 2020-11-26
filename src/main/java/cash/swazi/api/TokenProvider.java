package cash.swazi.api;

import cash.swazi.client.CollectionsAPIClient;
import cash.swazi.model.AccessToken;

import java.io.IOException;

public interface TokenProvider {
    AccessToken getToken() throws IOException, RequestFailedException;
}
