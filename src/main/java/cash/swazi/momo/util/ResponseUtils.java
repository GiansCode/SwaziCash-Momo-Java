package cash.swazi.momo.util;

import org.apache.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseUtils {
    private ResponseUtils() {}
    public static String getResponseBody(HttpResponse response) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        response.getEntity().writeTo(outputStream);
        return outputStream.toString();
    }
}
