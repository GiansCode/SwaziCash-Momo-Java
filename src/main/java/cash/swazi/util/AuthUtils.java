package cash.swazi.util;

import org.apache.commons.codec.binary.Base64;

public final class AuthUtils {
    private AuthUtils() {}

    public static String encodeBasicAuthentication(String... input) {
        byte[] formattedInput = String.join(":",input).getBytes();
        return Base64.encodeBase64String(formattedInput);
    }
}
