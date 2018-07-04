package online.omnia.conversions;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class HttpMethodUtils {

    private static CloseableHttpClient httpClient;
    static {
        httpClient = HttpClients.createDefault();
    }
    public static String getMethod(String urlPath, Map<String, String> headers) throws IOException {

        if (urlPath == null) return null;
        HttpGet httpGet = new HttpGet(urlPath);
        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            httpGet.addHeader(headerEntry.getKey(), headerEntry.getValue());
        }
        return getResponse(httpGet);
    }

    public static String postMethod(String urlPath, List<NameValuePair> params, Map<String, String> headers) throws IOException {
        if (urlPath == null || params == null) return null;
        HttpPost httpPost = new HttpPost(urlPath);
        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            httpPost.addHeader(headerEntry.getKey(), headerEntry.getValue());
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        return getResponse(httpPost);

    }

    private static String getResponse(HttpUriRequest httpRequest) throws IOException {
        CloseableHttpResponse response = httpClient.execute(httpRequest);
        StringBuilder serverAnswer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        String answer;
        while ((answer = reader.readLine()) != null) {
            serverAnswer.append(answer);
        }
        reader.close();
        response.close();
        return serverAnswer.toString();
    }


}
