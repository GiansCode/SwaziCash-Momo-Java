package cash.swazi.client;

import cash.swazi.constants.Headers;
import cash.swazi.util.AuthUtils;
import org.intellij.lang.annotations.MagicConstant;

import java.util.HashMap;
import java.util.Map;

public final class Options {
    private final String primaryKey;
    private final String subscriptionKey;
    private final String userID;
    private final String baseUrl;
    private final String authorization;
    private final String targetEnvironment;
    private final String currency;
    private final Map<String,String> basicHeaders = new HashMap<>();


    public Options(String primaryKey, String apiSecret, String userID, String baseUrl, String targetEnvironment, String currency) {
        this.primaryKey = primaryKey;
        this.subscriptionKey = apiSecret;
        this.userID = userID;
        this.baseUrl = baseUrl;
        this.authorization = AuthUtils.encodeBasicAuthentication(getUserID(), getSubscriptionKey());
        this.targetEnvironment = targetEnvironment;
        this.currency = currency;

        prepareBasicHeaders();
    }

    private void prepareBasicHeaders() {
        basicHeaders.put(Headers.AUTHORIZATION, getAuthorization());
        basicHeaders.put(Headers.SUBSCRIPTION_KEY, getSubscriptionKey());
        basicHeaders.put(Headers.TARGET_ENVIRONMENT, getTargetEnvironment());
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public String getUserID() {
        return userID;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAuthorization() {
        return authorization;
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
