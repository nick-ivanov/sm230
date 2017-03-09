//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Playground {
    public static void main(String[] args) {
        System.out.println("Playground!");
    }

    public void hitEndpoint() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // specify the host, protocol, and port
            HttpHost target = new HttpHost("weather.yahooapis.com", 80, "http");

            // specify the get request
            HttpGet getRequest = new HttpGet("/forecastrss?p=80020&u=f");

            System.out.println("executing request to " + target);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity entity = httpResponse.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(httpResponse.getStatusLine());
            Header[] headers = httpResponse.getAllHeaders();
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
            }
            System.out.println("----------------------------------------");

            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
}
