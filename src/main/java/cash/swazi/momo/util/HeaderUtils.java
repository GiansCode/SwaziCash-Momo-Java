package cash.swazi.momo.util;

import cash.swazi.momo.client.Options;
import cash.swazi.momo.constant.Headers;
import cash.swazi.momo.model.auth.AccessToken;
import org.intellij.lang.annotations.MagicConstant;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtils {
    private HeaderUtils() { }

    public static Map<String, String> generateHeader(Options options, @MagicConstant(valuesFromClass = Headers.class)String... keys) {
        return generateHeader(options, null, keys);
    }

    public static Map<String, String> generateHeader(Options options, AccessToken token, @MagicConstant(valuesFromClass = Headers.class) String...keys) {
        Map<String, String> result = new HashMap<>();
        for (String key : keys) {
            String value = getHeaderValue(options, token, key);
            if (value == null) continue;
            result.put(key, value);
        }
        return result;
    }

    private static String getHeaderValue(Options options, AccessToken token, String key) {
        switch (key) {
            case Headers.AUTHORIZATION:
                return token != null ? AuthUtils.encodeBearerAuthentication(token) : options.getBasicAuthorization();
            case Headers.SUBSCRIPTION_KEY:
                return options.getSubscriptionKey();
            case Headers.TARGET_ENVIRONMENT:
                return options.getTargetEnvironment();
            default:
                return null;
        }
    }
}
