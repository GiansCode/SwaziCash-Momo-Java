package cash.swazi.model.transactioninfo;

public class TransactionFailReason {
    private final Code code;
    private final String message;

    public TransactionFailReason(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public enum Code {
        PAYEE_NOT_FOUND,
        PAYER_NOT_FOUND,
        NOT_ALLOWED,
        NOT_ALLOWED_TARGET_ENVIRONMENT,
        INVALID_CALLBACK_URL_HOST,
        INVALID_CURRENCY,
        SERVICE_UNAVAILABLE,
        INTERNAL_PROCESSING_ERROR,
        NOT_ENOUGH_FUNDS,
        PAYER_LIMIT_REACHED,
        PAYEE_NOT_ALLOWED_TO_RECEIVE,
        PAYMENT_NOT_APPROVED,
        RESOURCE_NOT_FOUND,
        APPROVAL_REJECTED,
        EXPIRED,
        TRANSACTION_CANCELED,
        RESOURCE_ALREADY_EXIST;
    }
}
