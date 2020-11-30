package cash.swazi.api;

import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.client.Options;

import java.io.IOException;
import java.util.UUID;

public interface SandboxOptionProvider {
    Options requestSandboxOptions(UUID userId, String providerCallbackHost) throws IOException, RequestFailedException;
}
