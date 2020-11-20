package cash.swazi.model;

public class PaymentRequest {
    private final String amount;
    private final String currency;
    private final String externalId;
    private final Payer payer;
    private final String payerMessage;
    private final String payeeNote;

    public PaymentRequest(double amount, String currency, String externalID, Payer payer, String payerMessage, String payeeNote) {
        this.amount = String.valueOf(amount);
        this.currency = currency;
        this.externalId = externalID;
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

    public static class Payer {
        private final String partyIdType;
        private final String partyId;

        public Payer(String partyIdType, String partyId) {
            this.partyIdType = partyIdType;
            this.partyId = partyId;
        }

        public String getPartyIdType() {
            return partyIdType;
        }

        public String getPartyId() {
            return partyId;
        }
    }
}
