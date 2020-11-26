package cash.swazi.api;

import cash.swazi.model.TransferRequest;

import java.util.UUID;

public interface DisbursementsDelegate {
    void transfer(UUID referenceId, String callbackUrl, TransferRequest request);
}
