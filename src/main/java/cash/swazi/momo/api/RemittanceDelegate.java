package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.TransferRequest;

import java.io.IOException;
import java.util.UUID;

public interface RemittanceDelegate extends Transacting, SelfAuthenticating {
    void transfer(UUID referenceId, String callbackUrl, TransferRequest request) throws IOException, RequestFailedException;
}
