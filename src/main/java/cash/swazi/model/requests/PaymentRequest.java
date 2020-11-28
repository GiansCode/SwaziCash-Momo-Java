package cash.swazi.model.requests;

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
