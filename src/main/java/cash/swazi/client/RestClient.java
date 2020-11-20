package cash.swazi.client;


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
    public final HttpResponse post(String path, Map<String, String> headers, String body) throws URISyntaxException, IOException {
        URI uri = getUri(path);
        HttpPost request = new HttpPost(uri);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
        request.setEntity(new StringEntity(body));
        return client.execute(request);
    }

    @Override
    public final HttpResponse get(String path, Map<String, String> headers) throws URISyntaxException, IOException {
        URI uri = getUri(path);
        HttpGet request = new HttpGet(uri);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
        return client.execute(request);
    }

    private URI getUri(String path) throws URISyntaxException {
        return new URIBuilder(baseUrl).setPath(path).build();
    }
}
