package cash.swazi.momo.api.delegate;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.model.requests.TransferRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.UUID;
/**
 *  Interface to interact with Remittance API of MTN MoMo API
 *  {@link cash.swazi.momo.api.SwaziCashFactory#createRemittanceDelegate(Options)}
 */
public interface RemittanceDelegate extends TransactionDelegate, SelfAuthenticating {
    /**
     * Used to transfer an amount from the owner’s account to a payee account.
     * @param referenceId Reference-Id to request Transaction Info later
     * @param callbackUrl URL to the server where any callback should be sent
     * @param request The transfer request to be sent
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    void transfer(@NotNull UUID referenceId, @Nullable String callbackUrl,@NotNull TransferRequest request) throws IOException, RequestFailedException;
}
