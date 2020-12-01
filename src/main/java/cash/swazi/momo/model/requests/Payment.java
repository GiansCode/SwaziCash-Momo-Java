package cash.swazi.momo.model.requests;

public abstract class Payment {
    private final String amount;
    private final String currency;
    private final String externalId;
    private final String payerMessage;
    private final String payeeNote;
    public Payment(double amount, String currency, String externalId, String payerMessage, String payeeNote) {
        this.amount = String.valueOf(amount);
        this.currency = currency;
        this.externalId = externalId;
        this.payerMessage = payerMessage;
        this.payeeNote = payeeNote;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getPayerMessage() {
        return payerMessage;
    }

    public String getPayeeNote() {
        return payeeNote;
    }
}
