package cash.swazi.client;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface IRestClient {
    HttpResponse post(boolean useBase, String path, Map<String,String> headers, Map<String,String> parameters, String body) throws URISyntaxException, IOException;
    HttpResponse get(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters) throws URISyntaxException, IOException;
}
