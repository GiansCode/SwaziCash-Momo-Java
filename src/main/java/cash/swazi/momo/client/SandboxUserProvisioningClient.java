package cash.swazi.momo.client;

import cash.swazi.momo.api.SandboxOptionProvider;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.constant.Headers;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * Implementation of {@link cash.swazi.momo.api.SandboxOptionProvider}
 * Handles fetching sandbox user info and creating Sandbox Options {@link cash.swazi.momo.client.Options}
 */
public final class SandboxUserProvisioningClient extends BasicAPIClient implements SandboxOptionProvider {

    private final String subscriptionKey;
    private final String baseUrl;
    public SandboxUserProvisioningClient(String baseUrl, String subscriptionKey) {
        super(baseUrl);
        this.baseUrl = baseUrl;
        this.subscriptionKey = subscriptionKey;
    }

    public Options requestSandboxOptions(@NotNull UUID userId, String providerCallbackHost) throws IOException, RequestFailedException {
        if (!userExists(userId)) {
            createApiUser(userId, providerCallbackHost);
        }
        return fetchApiUserOptions(userId);
    }


    private boolean createApiUser(UUID userId, String providerCallbackHost) throws IOException {
        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.REFERENCE_ID, userId.toString());
        headers.put(Headers.SUBSCRIPTION_KEY, subscriptionKey);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("providerCallbackHost", providerCallbackHost);
        try {
            Response response = getRestClient().post(true, "v1_0/apiuser", headers, null, jsonObject.toString());
            return (response.getStatusCode() == 201);
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
            Response response = getRestClient().get(true, "v1_0/apiuser/{X-Reference-Id}", headers, parameters);
            return (response.getStatusCode() == 200);
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
            Response response = getRestClient().post(true, "v1_0/apiuser/{X-Reference-Id}/apikey", headers, parameters, null);
            if (response.getStatusCode() != 201 || response.getBody() == null) {
                throw produceFailureException(response);
            }
            String key = getGson().fromJson(response.getBody(),JsonObject.class).get("apiKey").getAsString();
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
