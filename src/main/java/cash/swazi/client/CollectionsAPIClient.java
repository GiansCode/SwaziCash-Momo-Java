package cash.swazi.client;

import cash.swazi.api.CollectionsAPI;
import cash.swazi.constants.Headers;
import cash.swazi.model.AccessToken;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CollectionsAPIClient extends BasicAPIClient implements CollectionsAPI {
    private final Gson gson = new Gson();
    public CollectionsAPIClient(Options options) {
        super(options);
    }

    public CollectionsAPIClient(Options options, IRestClient client) {
        super(options, client);
    }

    public PaymentRequestResponse requestPayment(UUID referenceId, String callbackUrl, String targetEnvironment, PaymentRequest request) throws IOException {
        Options options = getOptions();

        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.AUTHORIZATION, options.getAuthorization());
        headers.put(Headers.SUBSCRIPTION_KEY, options.getSubscriptionKey());
        headers.put(Headers.CALLBACK_URL, callbackUrl);
        headers.put(Headers.REFERENCE_ID, referenceId.toString());
        headers.put(Headers.TARGET_ENVIRONMENT, targetEnvironment);

        String body = gson.toJson(request);

        try {
            HttpResponse response = getRestClient().post("requesttopay", headers, null, body);
            if (response.getStatusLine().getStatusCode() != 202) {
                return null;
            }
            return PaymentRequestResponse.getResponseFor(response.getStatusLine().getStatusCode());
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

    public AccessToken getToken() throws IOException {
        Options options = getOptions();

        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.AUTHORIZATION, options.getAuthorization());
        headers.put(Headers.SUBSCRIPTION_KEY, options.getSubscriptionKey());

        try {
            HttpResponse response = getRestClient().post("token", headers, null, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outputStream);
            return gson.fromJson(outputStream.toString(),AccessToken.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }

        return null;
    }

    public TransactionInformation getTransactionInformation(UUID transactionId, String targetEnvironment) throws IOException {
        Options options = getOptions();

        Map<String,String> headers = new HashMap<>();
        headers.put(Headers.AUTHORIZATION, options.getAuthorization());
        headers.put(Headers.SUBSCRIPTION_KEY, options.getSubscriptionKey());
        headers.put(Headers.TARGET_ENVIRONMENT, targetEnvironment);

        Map<String,String> parameters = new HashMap<>();
        parameters.put("referenceId", transactionId.toString());


        try {
            HttpResponse response = getRestClient().post("requesttopay/{referenceId}", headers, parameters, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outputStream);
            return gson.fromJson(outputStream.toString(),TransactionInformation.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }


    public enum PaymentRequestResponse {
        ACCEPTED(202),
        BAD_REQUEST(400),
        CONFLICT(409),
        INTERNAL_SERVER_ERROR(500);

        private final int errorCode;

        PaymentRequestResponse(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public static PaymentRequestResponse getResponseFor(int status) {
            for (PaymentRequestResponse resp : values()) {
                if (resp.errorCode == status) {
                    return resp;
                }
            }
            return null;
        }
    }
}
