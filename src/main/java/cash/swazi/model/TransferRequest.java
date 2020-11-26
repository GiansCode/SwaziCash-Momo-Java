package cash.swazi.model;

public final class TransferRequest extends Payment {
    private Party payee;

    public TransferRequest(double amount, String currency, String externalId, Party payee, String payerMessage, String payeeNote) {
        super(amount, currency, externalId, payerMessage, payeeNote);
    }

    public Party getPayee() {
        return payee;
    }
}
