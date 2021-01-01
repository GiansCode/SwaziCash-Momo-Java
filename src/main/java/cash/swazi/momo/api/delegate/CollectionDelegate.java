package cash.swazi.momo.api.delegate;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.model.requests.PaymentRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.UUID;

/**
 *  Interface to interact with Collection API of MTN MoMo API
 *  {@link cash.swazi.momo.api.SwaziCashFactory#createCollectionDelegate(Options)}
 */
public interface CollectionDelegate extends Transacting, SelfAuthenticating {
    /**
     * Request payment from a user
     * @param referenceId Reference-Id to request Transaction Info later
     * @param callbackUrl URL to the server where any callback should be sent
     * @param request The payment request to be sent
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    void requestPayment(@NotNull UUID referenceId, @Nullable String callbackUrl, @NotNull PaymentRequest request) throws IOException, RequestFailedException;
}
