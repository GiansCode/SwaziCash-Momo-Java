package cash.swazi.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestClientTest extends TestCase {
    private static final String POST_TEST_BODY = "This is expected to be sent back as part of response body.";
    private static final String GET_TEST_HEADER_VALUE = "Get-Req-Test";
    private final RestClient client = new RestClient("https://postman-echo.com");
    private final Gson gson = new Gson();


    public void testPostRequest() throws IOException, URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        HttpResponse response = client.post("post", headers, POST_TEST_BODY);
        assertEquals("Request failed!",200, response.getStatusLine().getStatusCode());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            response.getEntity().writeTo(outputStream);
            String output = outputStream.toString();
            JsonObject convertedObject = gson.fromJson(output, JsonObject.class);
            JsonElement dataElement = convertedObject.get("data");
            assertEquals("Invalid response returned.", POST_TEST_BODY, dataElement.getAsString());
        }
    }

    public void testGetRequest() throws IOException, URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("test-header", GET_TEST_HEADER_VALUE);
        HttpResponse response = client.get("get", headers);
        assertEquals("Request failed!",200, response.getStatusLine().getStatusCode());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            response.getEntity().writeTo(outputStream);
            String output = outputStream.toString();
            System.out.println(output);
            JsonObject convertedObject = gson.fromJson(output, JsonObject.class);
            JsonElement testElement = convertedObject.getAsJsonObject("headers").get("test-header");
            assertEquals("Invalid response header!",GET_TEST_HEADER_VALUE, testElement.getAsString());
        }
    }
}