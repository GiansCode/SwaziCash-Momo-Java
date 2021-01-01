package cash.swazi.momo.client.internal;

import cash.swazi.momo.client.data.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
/**
 * Defines basic contracts for internal REST client to be used for requests
 */
public interface IRestClient {
    /**
     * Send a POST request to specified path
     * @param useBase Should base URL provided be used? If yes final path would be "base/path"
     * @param path  The path relative to base (if used) or full url
     * @param headers headers to be included in the request
     * @param parameters parameters to be replaced in the path
     * @param body body of request
     * @return Response sent by endpoint
     * @throws URISyntaxException When Invalid path/baseURL is provided
     * @throws IOException If request was unable to be sent
     */
    Response post(boolean useBase, String path, Map<String,String> headers, Map<String,String> parameters, String body) throws URISyntaxException, IOException;

    /**
     * Send a GET request to a specified path
     * @param useBase Should base URL provided be used? If yes final path would be "base/path"
     * @param path The path relative to base (if used) or full url
     * @param headers headers to be included in the request
     * @param parameters parameters to be replaced in the path
     * @return Response sent by endpoint
     * @throws URISyntaxException When Invalid path/baseURL is provided
     * @throws IOException If request was unable to be sent
     */
    Response get(boolean useBase, String path, Map<String, String> headers, Map<String,String> parameters) throws URISyntaxException, IOException;
}
