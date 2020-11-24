package cash.swazi.client;

import cash.swazi.model.AccessToken;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class CollectionsAPIClientTest extends TestCase {
    private final Options options = new Options(
            System.getenv("MOMO_COLLECTIONS_SUB_KEY"),
            System.getenv("SANDBOX_API_KEY"),
            UUID.fromString(System.getenv("SANDBOX_USERID")),
            "https://sandbox.momodeveloper.mtn.com/",
            "sandbox",
            "EUR"
    );
    private final CollectionsAPIClient client = new CollectionsAPIClient(options);

    public void testGetAccessToken() throws IOException {
        AccessToken token = client.getToken();
        assert token != null;
    }
}