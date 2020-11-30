package cash.swazi.client;

import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.util.ResponseUtils;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;

import java.io.IOException;

abstract class BasicAPIClient {
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

    protected RequestFailedException produceFailureException(HttpResponse response) throws IOException {
        String respText = ResponseUtils.getResponseBody(response);
        RequestFailedException.FailReason failReason = null;
        if (respText != null && respText.length() != 0) {
            failReason = gson.fromJson(respText,RequestFailedException.FailReason.class);
        }
        return new RequestFailedException(response.getStatusLine().getStatusCode(), failReason);
    }
}
