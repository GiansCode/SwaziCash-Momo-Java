package cash.swazi.momo.client;

import cash.swazi.momo.api.CollectionDelegate;
import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.Party;
import cash.swazi.momo.model.requests.PaymentRequest;
import cash.swazi.momo.model.transaction.TransactionInformation;
import junit.framework.TestCase;
import java.io.IOException;
import java.util.UUID;

public class CollectionsClientTest extends TestCase {
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


    private final UUID transactionUUID = UUID.randomUUID();
    private final PaymentRequest request = new PaymentRequest(
            100,
            "EUR",
            "4565411",
            new Party(
                    Party.PartyIdType.MSISDN,
                    "918369110173"
            ),
            "tester",
            "testee"
    );


    public CollectionsClientTest() throws IOException, RequestFailedException {
    }

    public void testSendPaymentRequest() throws IOException, RequestFailedException {
        final CollectionDelegate client = SwaziCashFactory.createCollectionDelegate(options);
        client.requestPayment(transactionUUID, null, request);
    }


    public void testTransactionInfo() throws IOException, RequestFailedException {

        final CollectionDelegate client = SwaziCashFactory.createCollectionDelegate(options);
        client.requestPayment(transactionUUID, null, request);

        TransactionInformation info = client.getTransactionInformation(transactionUUID);

        assert info != null;
    }
}