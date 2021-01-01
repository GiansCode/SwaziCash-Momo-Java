package cash.swazi.momo.client;

import cash.swazi.momo.api.delegate.DisbursementDelegate;
import cash.swazi.momo.api.delegate.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.client.internal.IRestClient;
import cash.swazi.momo.constant.Paths;
import cash.swazi.momo.model.requests.TransferRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;
/**
 * Implementation of {@link DisbursementDelegate}
 * Handles interactions with MTN MoMo Disbursement API
 * @see <a href="https://momodeveloper.mtn.com/docs/services/disbursement/">Disbursement API</a>
 */
public final class DisbursementClient extends TransactionClient implements DisbursementDelegate {
    public DisbursementClient(Options options) {
        super(options, Paths.DISBURSEMENT, Paths.TRANSFER);
    }

    public DisbursementClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, Paths.DISBURSEMENT, Paths.TRANSFER);
    }

    public DisbursementClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, Paths.DISBURSEMENT, Paths.TRANSFER);
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public void transfer(@NotNull UUID referenceId, String callbackUrl, @NotNull TransferRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}