package cash.swazi.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtils {
    private JsonUtils() {}

    public static void switchFieldNames(JsonObject object, String original, String newName) {
        JsonElement element = object.get(original);
        object.remove(original);
        object.add(newName, element);
    }
}
