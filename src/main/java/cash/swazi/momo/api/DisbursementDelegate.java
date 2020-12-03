package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.Options;
import cash.swazi.momo.model.requests.TransferRequest;

import java.io.IOException;
import java.util.UUID;
/**
 *  Interface to interact with Disbursement API of MTN MoMo API
 *  {@link cash.swazi.momo.api.SwaziCashFactory#createDisbursementDelegate(Options)}
 */
public interface DisbursementDelegate extends Transacting, SelfAuthenticating {
    void transfer(UUID referenceId, String callbackUrl, TransferRequest request) throws IOException, RequestFailedException;
}
