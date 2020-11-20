package cash.swazi.model;

public class Payer {
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
