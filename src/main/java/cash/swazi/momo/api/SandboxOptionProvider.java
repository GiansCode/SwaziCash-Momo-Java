package cash.swazi.momo.api;

import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.Options;

import java.io.IOException;
import java.util.UUID;

public interface SandboxOptionProvider {
    Options requestSandboxOptions(UUID userId, String providerCallbackHost) throws IOException, RequestFailedException;
}
