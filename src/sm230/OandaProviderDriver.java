//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


public class OandaProviderDriver implements ProviderInterface {
    private String apiKey;

    public OandaProviderDriver() {
    }

    @Override
    public void initProvider(String propertiesFile, String username, String password) throws Exception {
        Properties properties = new Properties();
        System.out.println("here");

        try {
            properties.load(new FileInputStream(new File(propertiesFile)));
        } catch(Exception ex) {
            throw new Exception("Unable to open properties file " + propertiesFile +
                    ". Make sure you created one using sm230-TEMPLATE.properties."
            );
        }

        apiKey = properties.getProperty("provider.oanda.apikey");
        System.out.println("API key: " + apiKey);
    }

    public ArrayList<SM230Candle> getRecentCandles(String instrument, String granularity, int count) throws ClientProtocolException,IOException {
        String normalized_instrument = instrument;

        // e.g., EUR/USD (ISO format) => EUR_USD (endpoint format)
        if(instrument.contains("/")) {
            normalized_instrument = instrument.replace("/", "_");
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api-fxtrade.oanda.com/v3/instruments/"
                + normalized_instrument + "/candles?count=" + String.valueOf(count) +
                "&price=M&granularity=" + granularity);

        System.out.println("Api KEY: " + apiKey);

        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + apiKey);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "", l = "";

        while ((l =  rd.readLine()) != null) {
            line += l;
        }

        System.out.println("Line: " + line);

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

    @Override
    public ArrayList<String> getGranularitiesAvailable() {
        return new ArrayList<String>(Arrays.asList("S5", "S10", "S15", "S30", "M1", "M2", "M4", "M5", "M10",
                "M15", "M30", "H1", "H2", "H3", "H4", "H6", "H8", "H12", "D", "W", "M"));
    }

    @Override
    public ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp) {
        ArrayList<Tick> ticks = new ArrayList<>();
        return null;
    }

    @Override
    public int getMaxCandles(String instrument) {
        return 500;
    }
}
