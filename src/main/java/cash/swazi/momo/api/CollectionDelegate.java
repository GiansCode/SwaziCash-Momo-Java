package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.Options;
import cash.swazi.momo.model.requests.PaymentRequest;

import java.io.IOException;
import java.util.UUID;

/**
 *  Interface to interact with Collection API of MTN MoMo API
 *  {@link cash.swazi.momo.api.SwaziCashFactory#createCollectionDelegate(Options)}
 */
public interface CollectionDelegate extends Transacting, SelfAuthenticating {
    void requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException, RequestFailedException;
}
