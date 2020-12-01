package cash.swazi.momo.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import junit.framework.TestCase;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestClientTest extends TestCase {
    private static final String POST_TEST_BODY = "This is expected to be sent back as part of response body.";
    private static final String GET_TEST_HEADER_VALUE = "Get-Req-Test";
    private final IRestClient client = new RestClient("https://postman-echo.com");
    private final Gson gson = new Gson();


    public void testPostRequest() throws IOException, URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Response response = client.post(true,"post", headers,null, POST_TEST_BODY);
        assertEquals("Request failed!",200, response.getStatusCode());
        JsonObject convertedObject = gson.fromJson(response.getBody(), JsonObject.class);
        JsonElement dataElement = convertedObject.get("data");
        assertEquals("Invalid response returned.", POST_TEST_BODY, dataElement.getAsString());
    }

    public void testGetRequest() throws IOException, URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("test-header", GET_TEST_HEADER_VALUE);

        Response response = client.get(true, "get", headers, null);
        assertEquals("Request failed!",200, response.getStatusCode());
        JsonObject convertedObject = gson.fromJson(response.getBody(), JsonObject.class);
        JsonElement testElement = convertedObject.getAsJsonObject("headers").get("test-header");
        assertEquals("Invalid response header!",GET_TEST_HEADER_VALUE, testElement.getAsString());
    }
}