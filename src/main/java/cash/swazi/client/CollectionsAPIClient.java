package cash.swazi.client;

import cash.swazi.api.CollectionsAPI;
import cash.swazi.api.TokenProvider;
import cash.swazi.constants.Headers;
import cash.swazi.model.AccessToken;
import cash.swazi.model.AccessTokenDeserializer;
import cash.swazi.model.Balance;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;
import cash.swazi.util.AuthUtils;
import cash.swazi.util.ResponseUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CollectionsAPIClient extends BasicAPIClient implements CollectionsAPI {
    private final Gson gson = new Gson();
    private final TokenProvider tokenProvider;

    public CollectionsAPIClient(Options options) {
        this(options, new AuthenticationClient(options, "collection/token/"));
    }

    public CollectionsAPIClient(Options options, TokenProvider tokenProvider) {
        super(options);
        this.tokenProvider = tokenProvider;
    }

    public CollectionsAPIClient(Options options, IRestClient client, TokenProvider tokenProvider) {
        super(options, client);
        this.tokenProvider = tokenProvider;
    }

    public PaymentRequestResponse requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = getOptions().generateHeader(
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.AUTHORIZATION, AuthUtils.encodeBearerAuthentication(token));
        headers.put(Headers.CALLBACK_URL, callbackUrl);
        headers.put(Headers.REFERENCE_ID, referenceId.toString());

        String body = gson.toJson(request);

        try {
            HttpResponse response = getRestClient().post(true, "collection/v1_0/requesttopay", headers, null, body);
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

    public TransactionInformation getTransactionInformation(UUID transactionId) throws IOException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = getOptions().generateHeader(
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.AUTHORIZATION, AuthUtils.encodeBearerAuthentication(token));
        Map<String,String> parameters = new HashMap<>();
        parameters.put("referenceId", transactionId.toString());


        try {
            HttpResponse response = getRestClient().get(true, "collection/v1_0/requesttopay/{referenceId}", headers, parameters);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return gson.fromJson(responseBody,TransactionInformation.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

    public Balance getBalance() throws IOException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = getOptions().generateHeader(
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.AUTHORIZATION, AuthUtils.encodeBearerAuthentication(token));

        try {
            HttpResponse response = getRestClient().get(true, "collection/v1_0/account/balance", headers, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return gson.fromJson(responseBody, Balance.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }


    public Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = getOptions().generateHeader(
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.AUTHORIZATION, AuthUtils.encodeBearerAuthentication(token));
        Map<String,String> parameters = new HashMap<>();
        parameters.put("accountHolderIdType", accountHolderIdType.toString());
        parameters.put("accountHolderId", accountHolderId);

        try {
            HttpResponse response = getRestClient().get(true, "collection/v1_0/accountholder/{accountHolderIdType}/{accountHolderId}/active", headers, parameters);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                return null;
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return gson.fromJson(responseBody, JsonObject.class).get("result").getAsBoolean();
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

    public TokenProvider getTokenProvider() {
        return tokenProvider;
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
