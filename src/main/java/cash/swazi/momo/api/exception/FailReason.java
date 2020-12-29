package cash.swazi.momo.api.exception;

/**
 *  Data class representing failure message (if present) and code returns form api
 */
public final class FailReason {
    private final FailureCode code;
    private final String message;

    public FailReason(FailureCode code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Failure code returned from MoMo api
     */
    public FailureCode getCode() {
        return code;
    }

    /**
     * @return Message describing failure reason
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "FailReason{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
