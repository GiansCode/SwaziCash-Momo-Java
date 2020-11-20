package cash.swazi.model;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("access_token")
    private final String token;

    @SerializedName("token_type")
    private final String type;

    @SerializedName("expires_in")
    private final int expiryTime;

    public AccessToken(String token, String type, int expiryTime) {
        this.token = token;
        this.type = type;
        this.expiryTime = expiryTime;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public int getExpiryTime() {
        return expiryTime;
    }
}
