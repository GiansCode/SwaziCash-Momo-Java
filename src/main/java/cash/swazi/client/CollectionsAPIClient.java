package cash.swazi.client;

import cash.swazi.api.CollectionsAPI;
import cash.swazi.constants.Headers;
import cash.swazi.model.AccessToken;
import cash.swazi.model.Balance;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.intellij.lang.annotations.MagicConstant;

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

    public PaymentRequestResponse requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException {
        Map<String,String> headers = getOptions().generateHeader(
                Headers.AUTHORIZATION,
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.CALLBACK_URL, callbackUrl);
        headers.put(Headers.REFERENCE_ID, referenceId.toString());

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
        Map<String,String> headers = getOptions().generateHeader(
                Headers.AUTHORIZATION,
                Headers.SUBSCRIPTION_KEY
        );

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

    public TransactionInformation getTransactionInformation(UUID transactionId) throws IOException {
        Map<String,String> headers = getOptions().generateHeader(
                Headers.AUTHORIZATION,
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );

        Map<String,String> parameters = new HashMap<>();
        parameters.put("referenceId", transactionId.toString());


        try {
            HttpResponse response = getRestClient().get("requesttopay/{referenceId}", headers, parameters);
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

    public Balance getBalance() throws IOException {
        Map<String,String> headers = getOptions().generateHeader(
                Headers.AUTHORIZATION,
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );


        try {
            HttpResponse response = getRestClient().get("v1_0/account/balance", headers, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outputStream);
            return gson.fromJson(outputStream.toString(), Balance.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }


    public Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException {
        Map<String,String> headers = getOptions().generateHeader(
                Headers.AUTHORIZATION,
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );

        Map<String,String> parameters = new HashMap<>();
        parameters.put("accountHolderIdType", accountHolderIdType.toString());
        parameters.put("accountHolderId", accountHolderId);

        try {
            HttpResponse response = getRestClient().get("/v1_0/accountholder/{accountHolderIdType}/{accountHolderId}/active", parameters, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outputStream);
            return gson.fromJson(outputStream.toString(), Boolean.class);
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

    public enum AccountHolderIdType {
        MSISDN, EMAIL, PARTY_CODE;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
