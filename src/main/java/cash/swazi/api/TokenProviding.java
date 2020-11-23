package cash.swazi.api;

import cash.swazi.client.CollectionsAPIClient;
import cash.swazi.model.AccessToken;

import java.io.IOException;

public interface TokenProviding {
    AccessToken getToken() throws IOException;
    Boolean isAccountActive(AccessToken token, CollectionsAPIClient.AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException;
}
