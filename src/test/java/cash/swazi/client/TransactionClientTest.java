package cash.swazi.client;

import cash.swazi.api.Transacting;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.transaction.Balance;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class TransactionClientTest extends TestCase {
    private final Options options = new Options(
            System.getenv("MOMO_COLLECTIONS_SUB_KEY"),
            System.getenv("SANDBOX_API_KEY"),
            UUID.fromString(System.getenv("SANDBOX_USERID")),
            "https://sandbox.momodeveloper.mtn.com/",
            "sandbox",
            "EUR"
    );
    private final Transacting transacting = new CollectionClient(options);

    public void testGetBalance() throws IOException, RequestFailedException {
        Balance balance = transacting.getBalance();
        assert balance != null;
    }

    public void testIsAccountActive() throws IOException, RequestFailedException {
        Boolean result = transacting.isAccountActive(TransactionClient.AccountHolderIdType.MSISDN, "123456");
        assert result != null;
    }
}
