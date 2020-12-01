package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.PaymentRequest;

import java.io.IOException;
import java.util.UUID;

public interface CollectionDelegate extends Transacting, SelfAuthenticating {
    void requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException, RequestFailedException;
}
