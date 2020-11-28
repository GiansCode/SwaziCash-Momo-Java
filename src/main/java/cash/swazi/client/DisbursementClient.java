package cash.swazi.client;

import cash.swazi.api.DisbursementDelegate;
import cash.swazi.api.TokenProvider;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.requests.TransferRequest;

import java.io.IOException;
import java.util.UUID;

public final class DisbursementClient extends TransactionClient implements DisbursementDelegate {
    public DisbursementClient(Options options) {
        super(options, "disbursement", "transfer");
    }

    public DisbursementClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, "disbursement", "transfer");
    }

    public DisbursementClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, "disbursement", "transfer");
    }

    @Override
    public void transfer(UUID referenceId, String callbackUrl, TransferRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}