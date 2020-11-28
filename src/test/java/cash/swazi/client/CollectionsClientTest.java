package cash.swazi.client;

import cash.swazi.api.CollectionDelegate;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.model.requests.Party;
import cash.swazi.model.requests.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class CollectionsClientTest extends TestCase {
    private final Options options = new Options(
            System.getenv("MOMO_COLLECTIONS_SUB_KEY"),
            System.getenv("SANDBOX_API_KEY"),
            UUID.fromString(System.getenv("SANDBOX_USERID")),
            "https://sandbox.momodeveloper.mtn.com/",
            "sandbox",
            "EUR"
    );

    private final UUID transactionUUID = UUID.randomUUID();
    private final CollectionDelegate client = new CollectionClient(options);

    public void testRequestPayment() throws IOException, RequestFailedException {
        PaymentRequest request = new PaymentRequest(
                100,
                "EUR",
                "45645",
                new Party(
                        Party.PartyIdType.MSISDN,
                        "918369110173"
                ),
                "tester",
                "testee"
        );
        client.requestPayment(transactionUUID, null, request);
    }

    public void testGetTransactionInformation() throws IOException, RequestFailedException {
        testRequestPayment(); // Pre-requisite
        TransactionInformation info = client.getTransactionInformation(transactionUUID);
        assert info != null;
    }
}