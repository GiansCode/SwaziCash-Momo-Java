package cash.swazi.momo.api.delegate;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.UUID;

/**
 *  Interface to create Sandbox user for testing
 *  {@link cash.swazi.momo.api.SwaziCashFactory#createDisbursementDelegate(Options)}
 *  @see <a href="https://momodeveloper.mtn.com/docs/services/sandbox-provisioning-api/">Sandbox User Provisioning</a>
 */
public interface SandboxOptionDelegate {
    /**
     * @param userId Id for API user to be created
     * @param providerCallbackHost Callback host for callbacks to be used in subsequent requests
     * @return Options required to use the API with the sandbox environment
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     */
    Options requestSandboxOptions(@NotNull UUID userId, @Nullable String providerCallbackHost) throws IOException, RequestFailedException;
}
