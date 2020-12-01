package cash.swazi.client;


import cash.swazi.util.ResponseUtils;
import org.apache.http.HttpResponse;
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

public final class RestClient implements IRestClient {
    private final String baseUrl;

    public RestClient() {
        this("https://sandbox.momodeveloper.mtn.com/collection/v1_0");
    }

    public RestClient(String baseUrl) {

        this.baseUrl = baseUrl;
    }

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
