package cash.swazi.momo.api.exception;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Thrown when the request results in a failed status code
 */
public final class RequestFailedException extends Exception {
    private final int statusCode;
    private final @Nullable FailReason reason;


    public RequestFailedException(int statusCode, @Nullable FailReason reason) {
        super("Request failed with status code " + statusCode + " for reason " + reason);
        this.statusCode = statusCode;
        this.reason = reason;
    }

    /**
     * @return Http status code that resulted from tpublic String getCode() {
     *             return code;
     *         }
     *
     *         public String getMessage() {
     *             return message;
     *         }
     *
     *         @Override
     *         public String toString() {
     *             return "FailReason{" +
     *                     "code='" + code + '\'' +
     *                     ", message='" + message + '\'' +
     *                     '}';
     *         }he request
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return The Fail reason message from the api if available else null
     */
    public @Nullable FailReason getReason() {
        return reason;
    }
}

