package cash.swazi.client;

import cash.swazi.api.SwaziCashFactory;
import cash.swazi.api.exception.RequestFailedException;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class DisbursementsClientTest extends TestCase {
    private static Options options;

    static {
        try {
            options = SwaziCashFactory
                    .createSandboxOptionProvider("e879669e61f64d9f882c0b90d79d8fac")
                    .requestSandboxOptions(UUID.fromString("ff2c26e6-9cd3-40e5-9760-6cf1656def08"), "");
        } catch (IOException | RequestFailedException e) {
            e.printStackTrace();
        }
    }


}