package cash.swazi.momo.client.data;

import cash.swazi.momo.util.AuthUtils;
import org.jetbrains.annotations.NotNull;

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


    public Options(@NotNull String subscriptionKey, @NotNull String apiKey, @NotNull UUID userId, @NotNull String baseUrl, @NotNull String targetEnvironment, @NotNull String currency) {
        this.subscriptionKey = subscriptionKey;
        this.apiKey = apiKey;
        this.userId = userId;
        this.baseUrl = baseUrl;
        this.basicAuthorization = AuthUtils.encodeBasicAuthentication(getUserId().toString(), getApiKey());
        this.targetEnvironment = targetEnvironment;
        this.currency = currency;
    }

    @NotNull
    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    @NotNull
    public String getApiKey() {
        return apiKey;
    }

    @NotNull
    public UUID getUserId() {
        return userId;
    }

    @NotNull
    public String getBaseUrl() {
        return baseUrl;
    }

    @NotNull
    public String getBasicAuthorization() {
        return basicAuthorization;
    }

    @NotNull
    public String getTargetEnvironment() {
        return targetEnvironment;
    }

    @NotNull
    public String getCurrency() {
        return currency;
    }

}
