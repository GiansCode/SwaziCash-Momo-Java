package cash.swazi.model;

import com.google.gson.*;

import java.lang.reflect.Type;

public final class AccessTokenDeserializaer implements JsonDeserializer<AccessToken> {
    public AccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        return new AccessToken(obj.get("access_token").getAsString(), obj.get("token_type").getAsString(), obj.get("expires_in").getAsInt());
    }
}
