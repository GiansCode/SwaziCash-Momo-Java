package cash.swazi.client;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface IRestClient {
    HttpResponse post(String path, Map<String,String> headers, String body) throws URISyntaxException, IOException;
    HttpResponse get(String path, Map<String, String> headers, String body) throws URISyntaxException, IOException;
}
