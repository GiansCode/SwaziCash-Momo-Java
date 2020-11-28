package cash.swazi.client;

import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.util.ResponseUtils;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;

import java.io.IOException;

abstract class BasicAPIClient {
    private final Options options;
    private final IRestClient client;
    private final Gson gson;

    public BasicAPIClient(Options options) {
        this(options, new RestClient(options.getBaseUrl()));
    }

    public BasicAPIClient(Options options, Gson gson) {
        this(options, new RestClient(options.getBaseUrl()), gson);
    }

    public BasicAPIClient(Options options, IRestClient client) {
        this(options, client, new Gson());
    }

    public BasicAPIClient(Options options, IRestClient client, Gson gson) {
        this.options = options;
        this.client = client;
        this.gson = gson;
    }

    public Options getOptions() {
        return options;
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
