package cash.swazi.client;


import cash.swazi.model.AccessToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public final class RestClient implements IRestClient {
    private final HttpClient client;
    private final String baseUrl;

    public RestClient() {
        this("https://sandbox.momodeveloper.mtn.com/collection/v1_0");
    }

    public RestClient(String baseUrl) {
        this(HttpClientBuilder.create().build(), baseUrl);
    }

    public RestClient(HttpClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    @Override
    public final HttpResponse post(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters, String body) throws URISyntaxException, IOException {
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                path = path.replaceAll("{"+entry.getKey()+"}", entry.getValue());
            }
        }
        URI uri = getUri(useBase, path);
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
        return client.execute(request);
    }

    @Override
    public final HttpResponse get(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters) throws URISyntaxException, IOException {
        URI uri = getUri(useBase, path);
        HttpGet request = new HttpGet(uri);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getValue() == null) continue;
            request.setHeader(entry.getKey(), entry.getValue());
        }
        return client.execute(request);
    }

    private URI getUri(boolean withBase, String path) throws URISyntaxException {
        if (withBase) {
            return new URIBuilder(baseUrl).setPath(path).build();
        } else {

            return new URIBuilder(path).build();
        }
    }
}
