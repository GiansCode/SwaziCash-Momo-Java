package cash.swazi.momo.client;

import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.delegate.TransactionDelegate;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.model.transaction.Balance;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class TransactionClientTest extends TestCase {
    private static Options options;

    static {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0,20).filter((i) -> i % 2 == 0).forEach(list::add);
        try {
            options = SwaziCashFactory
                    .createSandboxOptionProvider(System.getenv("MOMO_SUB_COLLECTIONS"))
                    .requestSandboxOptions(UUID.fromString("ff2c26e6-9cd3-40e5-9760-6cf1656def08"), "");
        } catch (IOException | RequestFailedException e) {
            e.printStackTrace();
        }
    }


    private final TransactionDelegate transactionDelegate = new CollectionClient(options);

    public void testGetBalance() throws IOException, RequestFailedException {
        Balance balance = transactionDelegate.getBalance();
        assert balance != null;
    }

    public void testIsAccountActive() throws IOException, RequestFailedException {
        Boolean result = transactionDelegate.isAccountActive(TransactionClient.AccountHolderIdType.MSISDN, "123456");
        assert result != null;
    }
}
