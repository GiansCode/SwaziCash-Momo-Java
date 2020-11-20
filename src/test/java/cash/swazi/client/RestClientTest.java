package cash.swazi.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestClientTest extends TestCase {
    private final RestClient client = new RestClient("https://postman-echo.com");
    private final Gson gson = new Gson();
    private final String responseString = "This is expected to be sent back as part of response body.";
    public void testPostRequest() throws IOException, URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        HttpResponse response = client.post("post", headers, responseString);
        assertEquals("Request failed! Check base URI.",200, response.getStatusLine().getStatusCode());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            response.getEntity().writeTo(outputStream);
            String output = outputStream.toString();
            JsonObject convertedObject = gson.fromJson(output, JsonObject.class);
            JsonElement dataElement = convertedObject.get("data");
            assertEquals("Invalid response returned.",responseString, dataElement.getAsString());
        }

    }
}