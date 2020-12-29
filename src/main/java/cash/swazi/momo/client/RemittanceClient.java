package cash.swazi.momo.client;

import cash.swazi.momo.api.RemittanceDelegate;
import cash.swazi.momo.api.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.TransferRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;
/**
 * Implementation of {@link cash.swazi.momo.api.RemittanceDelegate}
 * Handles interactions with MTN MoMo Remittance API
 * @see <a href="https://momodeveloper.mtn.com/docs/services/remittance/">Remittance API</a>
 */
public final class RemittanceClient extends TransactionClient implements RemittanceDelegate {
    public RemittanceClient(Options options) {
        super(options, "remittance", "transfer");
    }

    public RemittanceClient(Options options, TokenProvider tokenProvider) {
        super(options, tokenProvider, "remittance", "transfer");
    }

    public RemittanceClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client, tokenProvider, "remittance", "transfer");
    }

    @Override
    public void transfer(@NotNull UUID referenceId, String callbackUrl, @NotNull TransferRequest request) throws IOException, RequestFailedException {
        sendTransactionRequest(referenceId, callbackUrl, request);
    }
}
