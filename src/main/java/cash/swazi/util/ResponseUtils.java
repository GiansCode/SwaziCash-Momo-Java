package cash.swazi.util;

import cash.swazi.model.transaction.TransactionInformation;
import org.apache.commons.codec.binary.Base64;
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
