package cash.swazi.model.requests;

public final class TransferRequest extends Payment {
    private final Party payee;

    public TransferRequest(double amount, String currency, String externalId, Party payee, String payerMessage, String payeeNote) {
        super(amount, currency, externalId, payerMessage, payeeNote);
        this.payee = payee;
    }

    public Party getPayee() {
        return payee;
    }
}
