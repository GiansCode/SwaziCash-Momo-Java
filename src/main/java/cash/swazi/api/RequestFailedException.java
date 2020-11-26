package cash.swazi.api;

public final class RequestFailedException extends Exception {
    private final int statusCode;
    private final FailReason reason;


    public RequestFailedException(int statusCode, FailReason reason) {
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public FailReason getReason() {
        return reason;
    }

    public static class FailReason {
        private final String code;
        private final String message;

        public FailReason(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}

