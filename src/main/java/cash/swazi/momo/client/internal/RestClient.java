package cash.swazi.momo.client.internal;


import cash.swazi.momo.client.data.Response;
import cash.swazi.momo.util.ResponseUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Implementation of {@link RestClient}
 * Handles sending REST requests
 */
public final class RestClient implements IRestClient {
    private static final String DEFAULT_BASE_URL = "https://sandbox.momodeveloper.mtn.com/";
    private final String baseUrl;

    public RestClient() {
        this(DEFAULT_BASE_URL);
    }

    public RestClient(String baseUrl) {

        this.baseUrl = baseUrl;
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final Response post(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters, String body) throws URISyntaxException, IOException {
        URI uri = getUri(useBase, parseParameters(path, parameters));
        HttpPost request = new HttpPost(uri);
        if (body != null) {
            request.setEntity(new StringEntity(body));
        }
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getValue() == null) continue;
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String respStr = ResponseUtils.getResponseBody(response);
            return new Response(statusCode, respStr);
        }
    }
    /**
     *  {@inheritDoc}
     */
    @Override
    public final Response get(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters) throws URISyntaxException, IOException {
        URI uri = getUri(useBase, parseParameters(path, parameters));
        HttpGet request = new HttpGet(uri);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getValue() == null) continue;
            request.setHeader(entry.getKey(), entry.getValue());
        }
        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String respStr = ResponseUtils.getResponseBody(response);
            return new Response(statusCode, respStr);
        }
    }

    /**
     * Builds the URI with the given parameters
     * @param withBase Denotes whether the BaseURL should be used in the path
     * @param path The path relative to base (if used) or full url
     * @return URI in the form "base/path" (if withBase is true) or "path" (if withBase is false)
     * @throws URISyntaxException
     */
    private URI getUri(boolean withBase, String path) throws URISyntaxException {
        if (withBase) {
            return new URIBuilder(baseUrl).setPath(path).build();
        } else {

            return new URIBuilder(path).build();
        }
    }

    private String parseParameters(String path, Map<String, String> parameters) {
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                path = path.replace("{"+entry.getKey()+"}", entry.getValue());
            }
        }
        return path;
    }
}
