package cash.swazi.momo.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
/**
 * Defines basic contracts for internal REST client to be used for requests
 */
public interface IRestClient {
    Response post(boolean useBase, String path, Map<String,String> headers, Map<String,String> parameters, String body) throws URISyntaxException, IOException;
    Response get(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters) throws URISyntaxException, IOException;
}
