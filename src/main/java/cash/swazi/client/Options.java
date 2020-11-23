package cash.swazi.client;

import cash.swazi.constants.Headers;
import cash.swazi.util.AuthUtils;
import org.intellij.lang.annotations.MagicConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Options {
    private final String subscriptionKey;
    private final String apiKey;
    private final UUID userID;
    private final String baseUrl;
    private final String basicAuthorization;
    private final String targetEnvironment;
    private final String currency;
    private final Map<String,String> basicHeaders = new HashMap<>();


    public Options(String primaryKey, String subscriptionKey, UUID userID, String baseUrl, String targetEnvironment, String currency) {
        this.subscriptionKey = primaryKey;
        this.apiKey = subscriptionKey;
        this.userID = userID;
        this.baseUrl = baseUrl;
        this.basicAuthorization = AuthUtils.encodeBasicAuthentication(getUserID().toString(), getApiKey());
        this.targetEnvironment = targetEnvironment;
        this.currency = currency;
        prepareBasicHeaders();
    }

    private void prepareBasicHeaders() {
        basicHeaders.put(Headers.AUTHORIZATION, getBasicAuthorization());
        basicHeaders.put(Headers.SUBSCRIPTION_KEY, getApiKey());
        basicHeaders.put(Headers.TARGET_ENVIRONMENT, getTargetEnvironment());
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public UUID getUserID() {
        return userID;
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

    public Map<String,String> generateHeader(@MagicConstant(valuesFromClass = Headers.class) String... headerKeys) {
        Map<String,String> header = new HashMap<>();
        for (String key: headerKeys) {
            header.put(key, basicHeaders.get(key));
        }
        return header;
    }
}
