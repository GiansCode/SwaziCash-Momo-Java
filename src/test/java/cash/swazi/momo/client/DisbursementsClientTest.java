package cash.swazi.momo.client;

import cash.swazi.momo.api.DisbursementDelegate;
import cash.swazi.momo.api.SwaziCashFactory;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.model.requests.Party;
import cash.swazi.momo.model.requests.TransferRequest;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.UUID;

public class DisbursementsClientTest extends TestCase {
    private static Options options;

    static {
        try {
            options = SwaziCashFactory
                    .createSandboxOptionProvider(System.getenv("MOMO_SUB_DISBURSEMENTS"))
                    .requestSandboxOptions(UUID.fromString("ff2c26e6-9cd3-40e5-9760-6cf1656def08"), "");
        } catch (IOException | RequestFailedException e) {
            e.printStackTrace();
        }
    }

    private final UUID transactionUUID = UUID.randomUUID();
    private final TransferRequest request = new TransferRequest(
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

    public void testTransfer() throws IOException, RequestFailedException {
        final DisbursementDelegate client = SwaziCashFactory.createDisbursementDelegate(options);
        client.transfer(transactionUUID, null, request);
    }


}