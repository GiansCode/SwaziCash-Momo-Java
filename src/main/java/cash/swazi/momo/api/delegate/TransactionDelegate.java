package cash.swazi.momo.api.delegate;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.transaction.Balance;
import cash.swazi.momo.model.transaction.TransactionInformation;

import java.io.IOException;
import java.util.UUID;

/**
 * Defines basic transaction queries that are valid through any APIs
 */
public interface TransactionDelegate {

    /**
     * @param transactionId Reference id used for the transaction
     * @return POJO describing information returned about the transaction
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    TransactionInformation getTransactionInformation(UUID transactionId) throws IOException, RequestFailedException;

    /**
     *
     * @param accountHolderIdType Type of id being provided
     * @param accountHolderId Id of account holder
     * @return Activation status of specified account
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException, RequestFailedException;

    /**
     * @return Balance associated with api account
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    Balance getBalance() throws IOException, RequestFailedException;

    enum AccountHolderIdType {
        MSISDN, EMAIL, PARTY_CODE;
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
