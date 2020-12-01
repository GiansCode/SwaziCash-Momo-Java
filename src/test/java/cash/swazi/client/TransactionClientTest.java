package cash.swazi.client;

import cash.swazi.api.SwaziCashFactory;
import cash.swazi.api.Transacting;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.transaction.Balance;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class TransactionClientTest extends TestCase {
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
