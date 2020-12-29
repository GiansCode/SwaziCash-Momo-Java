package cash.swazi.momo.client;

import cash.swazi.momo.api.CollectionDelegate;
import cash.swazi.momo.api.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.PaymentRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;
/**
 * Implementation of {@link cash.swazi.momo.api.CollectionDelegate}
 * Handles interactions with MTN MoMo Collection API
 * @see <a href="https://momodeveloper.mtn.com/docs/services/collection/">Collections API</a>
 */
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

    /**
     *  {@inheritDoc}
     */
    @Override
    public void requestPayment(@NotNull UUID referenceId, String callbackUrl, @NotNull PaymentRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}
