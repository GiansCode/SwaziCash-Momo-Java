package cash.swazi.momo.client;

import cash.swazi.momo.api.delegate.RemittanceDelegate;
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
 * Implementation of {@link RemittanceDelegate}
 * Handles interactions with MTN MoMo Remittance API
 * @see <a href="https://momodeveloper.mtn.com/docs/services/remittance/">Remittance API</a>
 */
public final class RemittanceClient extends TransactionClient implements RemittanceDelegate {
    public RemittanceClient(Options options) {
        super(options, Paths.REMITTANCE, Paths.TRANSFER);
    }

    public RemittanceClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, Paths.REMITTANCE, Paths.TRANSFER);
    }

    public RemittanceClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, Paths.REMITTANCE, Paths.TRANSFER);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void transfer(@NotNull UUID referenceId, String callbackUrl, @NotNull TransferRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}
