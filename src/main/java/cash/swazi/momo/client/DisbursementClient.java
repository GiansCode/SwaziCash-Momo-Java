package cash.swazi.momo.client;

import cash.swazi.momo.api.DisbursementDelegate;
import cash.swazi.momo.api.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.TransferRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;
/**
 * Implementation of {@link cash.swazi.momo.api.DisbursementDelegate}
 * Handles interactions with MTN MoMo Disbursement API
 * @see <a href="https://momodeveloper.mtn.com/docs/services/disbursement/">Disbursement API</a>
 */
public final class DisbursementClient extends TransactionClient implements DisbursementDelegate {
    public DisbursementClient(Options options) {
        super(options, "disbursement", "transfer");
    }

    public DisbursementClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, "disbursement", "transfer");
    }

    public DisbursementClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, "disbursement", "transfer");
    }

    @Override
    public void transfer(@NotNull UUID referenceId, String callbackUrl, @NotNull TransferRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}