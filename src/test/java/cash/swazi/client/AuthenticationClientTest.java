package cash.swazi.client;

import cash.swazi.api.TokenProvider;
import cash.swazi.api.exception.RequestFailedException;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class AuthenticationClientTest extends TestCase {
    private final Options options = new Options(
            System.getenv("MOMO_COLLECTIONS_SUB_KEY"),
            System.getenv("SANDBOX_API_KEY"),
            UUID.fromString(System.getenv("SANDBOX_USERID")),
            "https://sandbox.momodeveloper.mtn.com/",
            "sandbox",
            "EUR"
    );

    private final TokenProvider provider = new AuthenticationClient(options, "collection");

    public void testGetToken() throws IOException, RequestFailedException {
        assert provider.getToken() != null;
    }
}
