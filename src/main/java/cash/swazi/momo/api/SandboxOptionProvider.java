package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.Options;

import java.io.IOException;
import java.util.UUID;

/**
 *  Interface to create Sandbox user for testing
 *  {@link cash.swazi.momo.api.SwaziCashFactory#createDisbursementDelegate(Options)}
 *  @see <a href="https://momodeveloper.mtn.com/docs/services/sandbox-provisioning-api/">Sandbox User Provisioning</a>
 */
public interface SandboxOptionProvider {
    Options requestSandboxOptions(UUID userId, String providerCallbackHost) throws IOException, RequestFailedException;
}
