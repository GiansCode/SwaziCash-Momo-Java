package cash.swazi.client;

import cash.swazi.model.AccessToken;
import cash.swazi.model.Balance;
import cash.swazi.model.Payer;
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
    private final CollectionsAPIClient client = new CollectionsAPIClient(options);

    public void testGetAccessToken() throws IOException {
        AccessToken token = client.getToken();
        assert token != null;
    }

    public void testRequestPayment() throws IOException {
        AccessToken token = client.getToken();
        PaymentRequest request = new PaymentRequest(
                100,
                "EUR",
                "45645",
                new Payer(
                        Payer.PartyIdType.MSISDN,
                        "918369110173"
                ),
                "tester",
                "testee"
        );
        CollectionsAPIClient.PaymentRequestResponse response = client.requestPayment(token, transactionUUID, null, request);
        assert response != null;
    }

    public void testGetTransactionInformation() throws IOException {
        testRequestPayment(); // Pre-requisite
        AccessToken token = client.getToken();
        UUID paymentId = UUID.randomUUID();
        TransactionInformation info = client.getTransactionInformation(token, transactionUUID);
        assert info != null;
    }

    public void testGetBalance() throws IOException {
        AccessToken token = client.getToken();
        Balance balance = client.getBalance(token);
        assert balance != null;
    }

    public void testIsAccountActive() {
    }

    public void testGetToken() {
    }
}