package cash.swazi.momo.constant;

public final class Paths {
    public static final String DISBURSEMENT = "disbursement";
    public static final String REMITTANCE = "remittance";
    public static final String COLLECTION = "collection";

    public static final String TRANSFER = "transfer";
    public static final String PAYMENT_REQUEST = "requesttopay";

    public static final String ACCOUNT_BALANCE = "/v1_0/account/balance";
    public static final String CHECK_ACCOUNT_ACTIVE = "/v1_0/accountholder/{accountHolderIdType}/{accountHolderId}/active";
    private Paths() {}
}
