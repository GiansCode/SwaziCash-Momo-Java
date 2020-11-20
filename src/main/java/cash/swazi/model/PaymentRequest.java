package cash.swazi.model;

public final class PaymentRequest {
    private final String amount;
    private final String currency;
    private final String externalId;
    private final Payer payer;
    private final String payerMessage;
    private final String payeeNote;

    public PaymentRequest(double amount, String currency, String externalId, Payer payer, String payerMessage, String payeeNote) {
        this.amount = String.valueOf(amount);
        this.currency = currency;
        this.externalId = externalId;
        this.payer = payer;
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

    public Payer getPayer() {
        return payer;
    }

    public String getPayerMessage() {
        return payerMessage;
    }

    public String getPayeeNote() {
        return payeeNote;
    }
}
