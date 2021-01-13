package cash.swazi.momo.client;

import cash.swazi.momo.api.delegate.TransactionDelegate;
import cash.swazi.momo.api.delegate.CollectionDelegate;
import cash.swazi.momo.api.delegate.DisbursementDelegate;
import cash.swazi.momo.api.delegate.RemittanceDelegate;
import cash.swazi.momo.api.exception.RequestFailedException;
import cash.swazi.momo.api.delegate.TokenProvider;
import cash.swazi.momo.client.data.Options;
import cash.swazi.momo.client.data.Response;
import cash.swazi.momo.client.internal.IRestClient;
import cash.swazi.momo.client.internal.OptionedAPIClient;
import cash.swazi.momo.constant.Headers;
import cash.swazi.momo.constant.Paths;
import cash.swazi.momo.model.auth.AccessToken;
import cash.swazi.momo.model.requests.Payment;
import cash.swazi.momo.model.transaction.Balance;
import cash.swazi.momo.model.transaction.TransactionInformation;
import cash.swazi.momo.util.HeaderUtils;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of {@link TransactionDelegate}
 * Abstraction between the 3 Delegates {@link CollectionDelegate}, {@link DisbursementDelegate}, {@link RemittanceDelegate}
 */
public abstract class TransactionClient extends OptionedAPIClient implements TransactionDelegate {
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

    /**
     * Sends transaction request to given endpoint
     *
     * @param referenceId Reference-Id to request Transaction Info later
     * @param callbackUrl URL to the server where any callback should be sent
     * @param request The payment request to be sent
     * @throws IOException thrown if client fails to send request
     * @throws RequestFailedException thrown if an unexpected or error status code is received
     * @implNote Given protected access so child classes can have specific naming for a
     * wrapping function that is more appropriate for use
     */
    protected void sendTransactionRequest(UUID referenceId, String callbackUrl, Payment request) throws IOException, RequestFailedException {
        Map<String, String> headers = HeaderUtils.generateHeader(getOptions(), tokenProvider.getToken(), Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
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
        Map<String, String> headers = HeaderUtils.generateHeader(getOptions(), tokenProvider.getToken(), Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("referenceId", transactionId.toString());
        try {
            Response response = getRestClient().get(true, apiPath + "/v1_0/" + requestPath + "/{referenceId}", headers, parameters);
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
        Map<String, String> headers = HeaderUtils.generateHeader(getOptions(), token, Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        try {
            Response response = getRestClient().get(true, apiPath + Paths.ACCOUNT_BALANCE, headers, null);
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
        Map<String, String> headers = HeaderUtils.generateHeader(getOptions(), token, Headers.AUTHORIZATION, Headers.SUBSCRIPTION_KEY, Headers.TARGET_ENVIRONMENT);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("accountHolderIdType", accountHolderIdType.toString());
        parameters.put("accountHolderId", accountHolderId);

        try {
            Response response = getRestClient().get(true, apiPath + Paths.CHECK_ACCOUNT_ACTIVE, headers, parameters);
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

    public @NotNull TokenProvider getTokenProvider() {
        return tokenProvider;
    }

}
