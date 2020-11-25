package cash.swazi.model;

public class Payer {
    private final PartyIdType partyIdType;
    private final String partyId;

    public Payer(PartyIdType partyIdType, String partyId) {
        this.partyIdType = partyIdType;
        this.partyId = partyId;
    }

    public PartyIdType getPartyIdType() {
        return partyIdType;
    }

    public String getPartyId() {
        return partyId;
    }

    public enum PartyIdType {
        MSISDN,
        EMAIL,
        PARTY_CODE
    }
}