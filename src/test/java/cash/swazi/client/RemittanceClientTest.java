package cash.swazi.client;

import cash.swazi.api.SwaziCashFactory;
import cash.swazi.api.exception.RequestFailedException;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class RemittanceClientTest extends TestCase {
    private static Options options;

    static {
        try {
            options = SwaziCashFactory
                    .createSandboxOptionProvider(System.getenv("MOMO_SUB_COLLECTIONS"))
                    .requestSandboxOptions(UUID.fromString("ff2c26e6-9cd3-40e5-9760-6cf1656def08"), "");
        } catch (IOException | RequestFailedException e) {
            e.printStackTrace();
        }
    }
}