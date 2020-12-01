package cash.swazi.momo.model.auth;

import com.google.gson.InstanceCreator;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.time.Instant;

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
