//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.*;

public class Playground {
    public static void main(String[] args) {
        System.out.println("Playground!");

        try {
            System.out.println("Result: " + hitEndpoint());
        } catch (Exception ex) {
            System.out.println("Zhopa: " + ex.getMessage());
        }
    }

    private static String hitEndpoint() throws ClientProtocolException,IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api-fxtrade.oanda.com/v3/instruments/EUR_USD/candles?count=6&price=M&granularity=S5");
        //HttpGet request = new HttpGet("https://api-fxtrade.oanda.com/v3/accounts/001-001-1093975-001/pricing?instruments=EUR_USD,USD_CAD");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + "a6f208f32cfee64606fafd325b590c78-fdb5ba0cd5184a21a2d8ff9b930cc355");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String l = "";

        while ((l =  rd.readLine()) != null) {
            line += l;
        }

        return line;
//        JSONObject root = new JSONObject(line);
//        JSONArray prices = root.getJSONArray("prices");
//        JSONArray asks= prices.getJSONObject(0);
//        String priceString = item0.getString("price");
//        Double priceDouble = Double.parseDouble(priceString);
//
//        return priceString;
    }
}
