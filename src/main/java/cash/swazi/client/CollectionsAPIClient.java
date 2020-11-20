package cash.swazi.client;

import cash.swazi.constants.Headers;
import cash.swazi.model.AccessToken;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class CollectionsAPIClient extends BasicAPIClient {
    private final Gson gson = new Gson();
    public CollectionsAPIClient(Options options) {
        super(options);
    }

    public CollectionsAPIClient(Options options, IRestClient client) {
        super(options, client);
    }

    public AccessToken getToken() throws IOException {
        Options options = getOptions();

        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.AUTHORIZATION, options.getAuthorization());
        headers.put(Headers.SUBSCRIPTION_KEY, options.getSubscriptionKey());

        try {
            HttpResponse response = getRestClient().post("token", headers, null);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outputStream);
            return gson.fromJson(outputStream.toString(),AccessToken.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

}
