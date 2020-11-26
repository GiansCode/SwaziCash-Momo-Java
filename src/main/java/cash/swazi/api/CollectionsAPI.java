package cash.swazi.api;

import cash.swazi.model.Balance;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;

import java.io.IOException;
import java.util.UUID;

public interface CollectionsAPI {
    void requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException, RequestFailedException;
    TransactionInformation getTransactionInformation(UUID transactionId) throws IOException, RequestFailedException;
    Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException, RequestFailedException;
    Balance getBalance() throws IOException, RequestFailedException;
    TokenProvider getTokenProvider();


    enum AccountHolderIdType {
        MSISDN, EMAIL, PARTY_CODE;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
