package cash.swazi.momo.client;

import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.Transacting;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.transaction.Balance;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class TransactionClientTest extends TestCase {
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
