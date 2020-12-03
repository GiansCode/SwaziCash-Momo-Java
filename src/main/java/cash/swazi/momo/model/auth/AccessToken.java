package cash.swazi.momo.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Data object that represents the access token returned by MoMo API
 * Used for Bearer authentication to other API calls
 */
public final class AccessToken {

    @SerializedName("access_token")
    private final String token;

    @SerializedName("token_type")
    private final String type;

    @SerializedName("expires_in")
    private final int expiresIn;

    private transient final long endTime;

    public AccessToken(String token, String type, int expiryTime) {
        this.token = token;
        this.type = type;
        this.expiresIn = expiryTime;
        this.endTime = System.currentTimeMillis() + expiresIn * 1000;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public int getTimeToExpire() {
        return expiresIn;
    }

    public long getTimeLeftToExpiry() {
        return  (endTime - System.currentTimeMillis())/1000;
    }

    public boolean hasExpired() {
        return getTimeLeftToExpiry() <= 0;
    }
}
