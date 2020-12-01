package cash.swazi.momo.client;

import cash.swazi.momo.api.RemittanceDelegate;
import cash.swazi.momo.api.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.TransferRequest;

import java.io.IOException;
import java.util.UUID;

public final class RemittanceClient extends TransactionClient implements RemittanceDelegate {
    public RemittanceClient(Options options) {
        super(options, "remittance", "transfer");
    }

    public RemittanceClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, "remittance", "transfer");
    }

    public RemittanceClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, "remittance", "transfer");
    }

    @Override
    public void transfer(UUID referenceId, String callbackUrl, TransferRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}
