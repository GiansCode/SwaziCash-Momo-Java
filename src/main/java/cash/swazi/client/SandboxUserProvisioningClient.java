package cash.swazi.client;

import cash.swazi.api.SandboxOptionProvider;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.constant.Headers;
import cash.swazi.util.ResponseUtils;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SandboxUserProvisioningClient extends BasicAPIClient implements SandboxOptionProvider {

    private final String subscriptionKey;
    private final String baseUrl;
    public SandboxUserProvisioningClient(String baseUrl, String subscriptionKey) {
        super(baseUrl);
        this.baseUrl = baseUrl;
        this.subscriptionKey = subscriptionKey;
    }

    public Options requestSandboxOptions(UUID userId, String providerCallbackHost) throws IOException, RequestFailedException {
        assert createApiUser(userId, providerCallbackHost) || userExists(userId);
        return fetchApiUserOptions(userId);
    }


    private boolean createApiUser(UUID userId, String providerCallbackHost) throws IOException {
        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.REFERENCE_ID, userId.toString());
        headers.put(Headers.SUBSCRIPTION_KEY, subscriptionKey);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("providerCallbackHost", providerCallbackHost);
        try {
            HttpResponse response = getRestClient().post(true, "v1_0/apiuser", headers, null, jsonObject.toString());
            return (response.getStatusLine().getStatusCode() == 201);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return false;
    }

    private boolean userExists(UUID userId) throws IOException {
        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.SUBSCRIPTION_KEY, subscriptionKey);
        Map<String,String> parameters = new HashMap<>();
        parameters.put(Headers.REFERENCE_ID, userId.toString());
        try {
            HttpResponse response = getRestClient().post(true, "v1_0/apiuser/{X-Reference-Id}", headers, parameters, null);
            return (response.getStatusLine().getStatusCode() == 200);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return false;
    }

    private Options fetchApiUserOptions(UUID userId) throws IOException, RequestFailedException {
        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.SUBSCRIPTION_KEY, subscriptionKey);

        Map<String,String> parameters = new HashMap<>();
        parameters.put(Headers.REFERENCE_ID, userId.toString());


        try {
            HttpResponse response = getRestClient().post(true, "v1_0/apiuser/{X-Reference-Id}/apikey", headers, null, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                throw produceFailureException(response);
            }
            String key = getGson().fromJson(ResponseUtils.getResponseBody(response),JsonObject.class).get("apiKey").getAsString();
            return new Options(subscriptionKey, key, userId, baseUrl, "sandbox", "EUR");
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }


    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
