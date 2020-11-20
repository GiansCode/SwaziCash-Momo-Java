package cash.swazi.client;

import cash.swazi.util.AuthUtils;
import org.apache.commons.codec.binary.Base64;

public final class Options {
    private final String primaryKey;
    private final String apiSecret;
    private final String userID;
    private final String baseUrl;
    private final String authorization;


    public Options(String primaryKey, String apiSecret, String userID, String baseUrl) {
        this.primaryKey = primaryKey;
        this.apiSecret = apiSecret;
        this.userID = userID;
        this.baseUrl = baseUrl;
        this.authorization = AuthUtils.encodeBasicAuthentication(getUserID(), getPrimaryKey());
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getApiSecret() {
        return apiSecret;
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
