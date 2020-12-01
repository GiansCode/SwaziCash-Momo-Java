package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.transaction.Balance;
import cash.swazi.momo.model.transaction.TransactionInformation;

import java.io.IOException;
import java.util.UUID;

public interface Transacting {
    TransactionInformation getTransactionInformation(UUID transactionId) throws IOException, RequestFailedException;
    Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException, RequestFailedException;
    Balance getBalance() throws IOException, RequestFailedException;
    enum AccountHolderIdType {
        MSISDN, EMAIL, PARTY_CODE;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
