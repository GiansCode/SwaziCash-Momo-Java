package cash.swazi.api;

import cash.swazi.client.CollectionsAPIClient;
import cash.swazi.model.AccessToken;
import cash.swazi.model.Balance;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;

import java.io.IOException;
import java.util.UUID;

public interface CollectionsAPI {
    CollectionsAPIClient.PaymentRequestResponse requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException;
    TransactionInformation getTransactionInformation(UUID transactionId) throws IOException;
    Boolean isAccountActive(CollectionsAPIClient.AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException;
    Balance getBalance() throws IOException;
}
