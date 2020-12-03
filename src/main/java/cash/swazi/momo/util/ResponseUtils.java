package cash.swazi.momo.util;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility class responsible for reading {@link HttpResponse}'s body, if any
 */
public class ResponseUtils {
    private ResponseUtils() {}
    public static String getResponseBody(HttpResponse response) throws IOException {
        if (response.getEntity() == null || !(response.getEntity() instanceof StringEntity)) return null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        response.getEntity().writeTo(outputStream);
        return outputStream.toString();
    }
}
