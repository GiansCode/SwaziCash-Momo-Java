package cash.swazi.momo.client;

import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.TokenProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class AuthenticationClientTest extends TestCase {
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

    private final TokenProvider provider = new AuthenticationClient(options, "collection");

    public AuthenticationClientTest() throws IOException, RequestFailedException {
    }

    public void testGetToken() throws IOException, RequestFailedException {
        assert provider.getToken() != null;
    }
}
