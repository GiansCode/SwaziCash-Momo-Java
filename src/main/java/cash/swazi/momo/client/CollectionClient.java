package cash.swazi.momo.client;

import cash.swazi.momo.api.delegate.CollectionDelegate;
import cash.swazi.momo.api.delegate.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.client.internal.IRestClient;
import cash.swazi.momo.constant.Paths;
import cash.swazi.momo.model.requests.PaymentRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;
/**
 * Implementation of {@link CollectionDelegate}
 * Handles interactions with MTN MoMo Collection API
 * @see <a href="https://momodeveloper.mtn.com/docs/services/collection/">Collections API</a>
 */
public final class CollectionClient extends TransactionClient implements CollectionDelegate {
    public CollectionClient(Options options) {
        super(options, Paths.COLLECTION, Paths.PAYMENT_REQUEST);
    }

    public CollectionClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, Paths.COLLECTION, Paths.PAYMENT_REQUEST);
    }

    public CollectionClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, Paths.COLLECTION, Paths.PAYMENT_REQUEST);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void requestPayment(@NotNull UUID referenceId, String callbackUrl, @NotNull PaymentRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}
