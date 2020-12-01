package cash.swazi.client;

import cash.swazi.api.Transacting;
import cash.swazi.api.exception.RequestFailedException;
import cash.swazi.api.TokenProvider;
import cash.swazi.constant.Headers;
import cash.swazi.model.auth.AccessToken;
import cash.swazi.model.requests.Payment;
import cash.swazi.model.transaction.Balance;
import cash.swazi.model.transaction.TransactionInformation;
import cash.swazi.util.HeaderUtils;
import cash.swazi.util.ResponseUtils;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

abstract class TransactionClient extends OptionedAPIClient implements Transacting {
    private final TokenProvider tokenProvider;
    private final String apiPath;
    private final String requestPath;
    public TransactionClient(Options options, String apiPath, String requestPath) {
        this(options, new AuthenticationClient(options, apiPath), apiPath, requestPath);
    }

    public TransactionClient(Options options, TokenProvider tokenProvider, String apiPath, String requestPath) {
        super(options);
        this.tokenProvider = tokenProvider;
        this.apiPath = apiPath;
        this.requestPath = requestPath;
    }

    public TransactionClient(Options options, IRestClient client, TokenProvider tokenProvider, String apiPath, String requestPath) {
        super(options, client);
        this.tokenProvider = tokenProvider;
        this.apiPath = apiPath;
        this.requestPath = requestPath;
    }

    void sendTransactionRequest(UUID referenceId, String callbackUrl, Payment request) throws IOException, RequestFailedException {
        Map<String,String> headers = HeaderUtils.generateHeader(getOptions(), tokenProvider.getToken(), Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        headers.put(Headers.CALLBACK_URL, callbackUrl);
        headers.put(Headers.REFERENCE_ID, referenceId.toString());
        String body = getGson().toJson(request);
        try {
            Response response = getRestClient().post(true, apiPath + "/v1_0/" + requestPath, headers, null, body);
            if (response.getStatusCode() != 202) {
                throw produceFailureException(response);
            }
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
    }

    public TransactionInformation getTransactionInformation(UUID transactionId) throws IOException, RequestFailedException {
        Map<String,String> headers = HeaderUtils.generateHeader(getOptions(), tokenProvider.getToken(), Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        Map<String,String> parameters = new HashMap<>();
        parameters.put("referenceId", transactionId.toString());
        try {
            Response response = getRestClient().get(true, apiPath+"/v1_0/"+requestPath+"/{referenceId}", headers, parameters);
            if (response.getStatusCode() != 200 || response.getBody() == null) {
                throw produceFailureException(response);
            }
            return getGson().fromJson(response.getBody(), TransactionInformation.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }

    public Balance getBalance() throws IOException, RequestFailedException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = HeaderUtils.generateHeader(getOptions(), token, Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        try {
            Response response = getRestClient().get(true, apiPath+"/v1_0/account/balance", headers, null);
            if (response.getStatusCode() != 200 || response.getBody() == null) {
                throw produceFailureException(response);
            }
            return getGson().fromJson(response.getBody(), Balance.class);
        } catch (URISyntaxException e) {
            System.err.println("Invalid baseURI or request path changed!");
            e.printStackTrace();
        }
        return null;
    }


    public Boolean isAccountActive(AccountHolderIdType accountHolderIdType, String accountHolderId) throws IOException, RequestFailedException {
        AccessToken token = tokenProvider.getToken();
        Map<String,String> headers = HeaderUtils.generateHeader(getOptions(), token, Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        Map<String,String> parameters = new HashMap<>();
        parameters.put("accountHolderIdType", accountHolderIdType.toString());
        parameters.put("accountHolderId", accountHolderId);

        try {
            Response response = getRestClient().get(true, apiPath+"/v1_0/accountholder/{accountHolderIdType}/{accountHolderId}/active", headers, parameters);
            if (response.getStatusCode() != 200 || response.getBody() == null) {
                throw produceFailureException(response);
            }
            return getGson().fromJson(response.getBody(), JsonObject.class).get("result").getAsBoolean();
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
