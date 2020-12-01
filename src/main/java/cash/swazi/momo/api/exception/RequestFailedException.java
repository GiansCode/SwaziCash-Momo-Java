package cash.swazi.momo.api.exception;

import java.util.Objects;

public final class RequestFailedException extends Exception {
    private final int statusCode;
    private final FailReason reason;


    public RequestFailedException(int statusCode, FailReason reason) {
        super(Objects.toString(reason));
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

        @Override
        public String toString() {
            return "FailReason{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}

