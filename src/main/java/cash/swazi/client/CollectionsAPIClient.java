package cash.swazi.client;

import cash.swazi.api.CollectionsAPI;
import cash.swazi.api.RequestFailedException;
import cash.swazi.api.TokenProvider;
import cash.swazi.constant.Headers;
import cash.swazi.model.AccessToken;
import cash.swazi.model.Balance;
import cash.swazi.model.PaymentRequest;
import cash.swazi.model.transaction.TransactionInformation;
import cash.swazi.util.AuthUtils;
import cash.swazi.util.ResponseUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CollectionsAPIClient extends BasicAPIClient implements CollectionsAPI {
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

    public void requestPayment(UUID referenceId, String callbackUrl, PaymentRequest request) throws IOException, RequestFailedException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = getOptions().generateHeader(
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.AUTHORIZATION, AuthUtils.encodeBearerAuthentication(token));
        headers.put(Headers.CALLBACK_URL, callbackUrl);
        headers.put(Headers.REFERENCE_ID, referenceId.toString());

        String body = getGson().toJson(request);

        try {
            HttpResponse response = getRestClient().post(true, "collection/v1_0/requesttopay", headers, null, body);
            if (response.getStatusLine().getStatusCode() != 202) {
                throw produceFailureException(response);
            }
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
    }

    public TransactionInformation getTransactionInformation(UUID transactionId) throws IOException, RequestFailedException {
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
                throw produceFailureException(response);
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return getGson().fromJson(responseBody,TransactionInformation.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

    public Balance getBalance() throws IOException, RequestFailedException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = getOptions().generateHeader(
                Headers.SUBSCRIPTION_KEY,
                Headers.TARGET_ENVIRONMENT
        );
        headers.put(Headers.AUTHORIZATION, AuthUtils.encodeBearerAuthentication(token));

        try {
            HttpResponse response = getRestClient().get(true, "collection/v1_0/account/balance", headers, null);
            if (response.getStatusLine().getStatusCode() != 200 || response.getEntity() == null) {
                throw produceFailureException(response);
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return getGson().fromJson(responseBody, Balance.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }


    public Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException, RequestFailedException {
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
                throw produceFailureException(response);
            }
            String responseBody = ResponseUtils.getResponseBody(response);
            return getGson().fromJson(responseBody, JsonObject.class).get("result").getAsBoolean();
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

    public TokenProvider getTokenProvider() {
        return tokenProvider;
    }

}
