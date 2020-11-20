package cash.swazi.client;

import cash.swazi.util.AuthUtils;

public final class Options {
    private final String primaryKey;
    private final String subscriptionKey;
    private final String userID;
    private final String baseUrl;
    private final String authorization;


    public Options(String primaryKey, String apiSecret, String userID, String baseUrl) {
        this.primaryKey = primaryKey;
        this.subscriptionKey = apiSecret;
        this.userID = userID;
        this.baseUrl = baseUrl;
        this.authorization = AuthUtils.encodeBasicAuthentication(getUserID(), getSubscriptionKey());
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
}
