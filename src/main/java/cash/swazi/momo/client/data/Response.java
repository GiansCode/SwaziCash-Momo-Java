package cash.swazi.momo.client.data;

import cash.swazi.momo.client.internal.IRestClient;

/**
 * POJO with data returned from {@link IRestClient}
 */
public final class Response {
    private final int statusCode;
    private final String body;

    public Response(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
