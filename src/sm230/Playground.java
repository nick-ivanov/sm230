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

//        try {
//            ArrayList<SM230Candle> candles = getRecentCandles("EUR/USD", "H1", 10);
//
//            for(SM230Candle candle : candles) {
//                System.out.println("Candle: " +
//                        " O: " + candle.getOpen() +
//                        " H: " + candle.getHigh() +
//                        " L: " + candle.getLow() +
//                        " C: " + candle.getClose() +
//                        " Time hour: " + candle.getTime().getHour()
//                );
//            }
//        } catch (Exception ex) {
//            System.out.println("Exception raised: " + ex.getMessage());
//        }
    }


}
