package cash.swazi.momo.client;

import cash.swazi.momo.util.AuthUtils;

import java.util.UUID;
/**
 * A POJO defining the parameters required for the client to be used.
 */
public final class Options {
    private final String subscriptionKey;
    private final String apiKey;
    private final UUID userId;
    private final String baseUrl;
    private final String basicAuthorization;
    private final String targetEnvironment;
    private final String currency;


    public Options(String subscriptionKey, String apiKey, UUID userId, String baseUrl, String targetEnvironment, String currency) {
        this.subscriptionKey = subscriptionKey;
        this.apiKey = apiKey;
        this.userId = userId;
        this.baseUrl = baseUrl;
        this.basicAuthorization = AuthUtils.encodeBasicAuthentication(getUserId().toString(), getApiKey());
        this.targetEnvironment = targetEnvironment;
        this.currency = currency;
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
