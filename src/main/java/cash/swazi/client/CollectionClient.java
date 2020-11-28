package cash.swazi.client;

import cash.swazi.api.CollectionDelegate;
import cash.swazi.api.TokenProvider;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.requests.PaymentRequest;

import java.io.IOException;
import java.util.UUID;

public final class CollectionClient extends TransactionClient implements CollectionDelegate {
    public CollectionClient(Options options) {
        super(options, "collection", "requesttopay");
    }

    public CollectionClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, "collection", "requesttopay");
    }

    public CollectionClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, "collection", "requesttopay");
    }

    public void requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}
