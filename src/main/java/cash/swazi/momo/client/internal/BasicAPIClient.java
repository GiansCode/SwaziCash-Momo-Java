package cash.swazi.momo.client.internal;

import cash.swazi.momo.api.exception.FailReason;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.client.data.Response;
import com.google.gson.Gson;

import java.io.IOException;
/**
 * Abstraction between the different Client classes
 */
public abstract class BasicAPIClient {
    private final IRestClient client;
    private final Gson gson;

    public BasicAPIClient(String baseUrl) {
        this(new RestClient(baseUrl));
    }

    public BasicAPIClient(String baseUrl, Gson gson) {
        this(new RestClient(baseUrl), gson);
    }

    public BasicAPIClient(IRestClient client) {
        this(client, new Gson());
    }

    public BasicAPIClient(IRestClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    protected IRestClient getRestClient() {
        return client;
    }

    public Gson getGson() {
        return gson;
    }

    protected RequestFailedException produceFailureException(Response response) throws IOException {
        FailReason failReason = null;
        if (response.getBody() != null && response.getBody().length() != 0) {
            failReason = gson.fromJson(response.getBody(), FailReason.class);
        }
        return new RequestFailedException(response.getStatusCode(), failReason);
    }
}
