package cash.swazi.client;

import cash.swazi.api.CollectionsAPI;
import cash.swazi.model.Balance;
import cash.swazi.model.Party;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;
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

    private final UUID transactionUUID = UUID.randomUUID();
    private final CollectionsAPI client = new CollectionsAPIClient(options);

    public void testGetAccessToken() throws IOException {
        assert client.getTokenProvider().getToken() != null;
    }

    public void testRequestPayment() throws IOException {
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

    public void testGetTransactionInformation() throws IOException {
        testRequestPayment(); // Pre-requisite
        TransactionInformation info = client.getTransactionInformation(transactionUUID);
        assert info != null;
    }

    public void testGetBalance() throws IOException {
        Balance balance = client.getBalance();
        assert balance != null;
    }

    public void testIsAccountActive() throws IOException {
        Boolean result = client.isAccountActive(CollectionsAPIClient.AccountHolderIdType.MSISDN, "123456");
        assert result != null;
    }
}