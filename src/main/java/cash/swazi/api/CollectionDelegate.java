package cash.swazi.api;

import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.requests.PaymentRequest;

import java.io.IOException;
import java.util.UUID;

public interface CollectionDelegate extends Transacting, SelfAuthenticating {
    void requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException, RequestFailedException;
}
