//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Playground {
    public static void main(String[] args) {
        System.out.println("Playground!");

        try {
            ArrayList<SM230Candle> candles = getRecentCandles("EUR/USD", "H1", 10);

            for(SM230Candle candle : candles) {
                System.out.println("Candle: " +
                        " O: " + candle.getOpen() +
                        " H: " + candle.getHigh() +
                        " L: " + candle.getLow() +
                        " C: " + candle.getClose() +
                        " Time hour: " + candle.getTime().getHour()
                );
            }
        } catch (Exception ex) {
            System.out.println("Exception raised: " + ex.getMessage());
        }
    }

    private static ArrayList<SM230Candle> getRecentCandles(String instrument, String granularity, int count) throws ClientProtocolException,IOException {
        String normalized_instrument = instrument;

        // e.g., EUR/USD (ISO format) => EUR_USD (endpoint format)
        if(instrument.contains("/")) {
            normalized_instrument = instrument.replace("/", "_");
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api-fxtrade.oanda.com/v3/instruments/"
                + normalized_instrument + "/candles?count=" + String.valueOf(count) +
                "&price=M&granularity=" + granularity);

        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + "a6f208f32cfee64606fafd325b590c78-fdb5ba0cd5184a21a2d8ff9b930cc355");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "", l = "";

        while ((l =  rd.readLine()) != null) {
            line += l;
        }

        JSONObject root = new JSONObject(line);
        JSONArray candles_json = root.getJSONArray("candles");
        JSONObject candle_json, mid;

        ArrayList<SM230Candle> candles = new ArrayList<>();

        for(int i = 0; i < candles_json.length(); i++) {
            candle_json = candles_json.getJSONObject(i);
            mid = candle_json.getJSONObject("mid");

            SM230Candle candle = new SM230Candle(
                    instrument,
                    LocalDateTime.parse(candle_json.get("time").toString(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSz")),
                    granularity,
                    Double.valueOf(mid.get("o").toString()),
                    Double.valueOf(mid.get("h").toString()),
                    Double.valueOf(mid.get("l").toString()),
                    Double.valueOf(mid.get("c").toString())
            );

            candles.add(candle);
        }

        return candles;
    }
}
