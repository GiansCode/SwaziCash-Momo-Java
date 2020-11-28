package cash.swazi.client;

import cash.swazi.constant.Headers;
import cash.swazi.model.auth.AccessToken;
import cash.swazi.util.AuthUtils;
import org.intellij.lang.annotations.MagicConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Options {
    private final String subscriptionKey;
    private final String apiKey;
    private final UUID userId;
    private final String baseUrl;
    private final String basicAuthorization;
    private final String targetEnvironment;
    private final String currency;
    private final Map<String,String> basicHeaders = new HashMap<>();


    public Options(String subscriptionKey, String apiKey, UUID userId, String baseUrl, String targetEnvironment, String currency) {
        this.subscriptionKey = subscriptionKey;
        this.apiKey = apiKey;
        this.userId = userId;
        this.baseUrl = baseUrl;
        this.basicAuthorization = AuthUtils.encodeBasicAuthentication(getUserId().toString(), getApiKey());
        this.targetEnvironment = targetEnvironment;
        this.currency = currency;
        prepareBasicHeaders();
    }

    private void prepareBasicHeaders() {
        basicHeaders.put(Headers.AUTHORIZATION, getBasicAuthorization());
        basicHeaders.put(Headers.SUBSCRIPTION_KEY, getSubscriptionKey());
        basicHeaders.put(Headers.TARGET_ENVIRONMENT, getTargetEnvironment());
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBasicAuthorization() {
        return basicAuthorization;
    }

    public String getTargetEnvironment() {
        return targetEnvironment;
    }

    public String getCurrency() {
        return currency;
    }

}
