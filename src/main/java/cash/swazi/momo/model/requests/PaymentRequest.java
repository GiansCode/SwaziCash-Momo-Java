package cash.swazi.momo.model.requests;

/**
 * Defines a payment request send to a user.
 * @see <a href="https://momodeveloper.mtn.com/docs/services/collection/operations/requesttopay-POST">collection/requesttopay</a>
 */
public final class PaymentRequest extends Payment {
    private final Party payer;

    public PaymentRequest(double amount, String currency, String externalId, Party payer, String payerMessage, String payeeNote) {
        super(amount, currency, externalId, payerMessage, payeeNote);
        this.payer = payer;
    }

    public Party getPayer() {
        return payer;
    }
}
