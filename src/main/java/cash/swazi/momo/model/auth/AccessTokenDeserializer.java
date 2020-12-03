package cash.swazi.momo.model.auth;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Deserializer to call the AccessToken constructor to let it calculate its "expiry" time
 */
public final class AccessTokenDeserializer implements JsonDeserializer<AccessToken> {
    public AccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        return new AccessToken(obj.get("access_token").getAsString(), obj.get("token_type").getAsString(), obj.get("expires_in").getAsInt());
    }
}
