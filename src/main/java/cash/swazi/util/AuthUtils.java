package cash.swazi.util;

import org.apache.commons.codec.binary.Base64;

public final class AuthUtils {
    private AuthUtils() {}
    private static final String FORMAT = "Basic %s";
    public static String encodeBasicAuthentication(String... input) {
        byte[] formattedInput = String.join(":",input).getBytes();
        return String.format(FORMAT, Base64.encodeBase64String(formattedInput));
    }
}
