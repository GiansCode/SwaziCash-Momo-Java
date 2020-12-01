package cash.swazi.momo.util;

import cash.swazi.momo.model.auth.AccessToken;
import org.apache.commons.codec.binary.Base64;

public final class AuthUtils {
    private AuthUtils() {}
    private static final String BASIC_FORMAT = "Basic %s";
    private static final String BEARER_FORMAT = "Bearer %s";
    public static String encodeBasicAuthentication(String... input) {
        byte[] formattedInput = String.join(":",input).getBytes();
        return String.format(BASIC_FORMAT, Base64.encodeBase64String(formattedInput));
    }
    public static String encodeBearerAuthentication(AccessToken token) {
        return String.format(BEARER_FORMAT, token.getToken());
    }
}
