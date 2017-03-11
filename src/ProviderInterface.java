//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ProviderInterface {
    // TODO: Add a docstring/comment in here
    void initProvider(String propertiesFile, String username, String password) throws Exception;

    // TODO: docstring/comment
    ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp);

    // TODO: docstring/comment
    ArrayList<SM230Candle> getRecentCandles(String instrument, String granularity, int count) throws ClientProtocolException,IOException;

    // TODO: docstring/comment
    ArrayList<String> getGranularitiesAvailable();

    // TODO: docstring/comment
    ArrayList<String> getInstrumentsAvailable();

    // TODO: docstring/comment
    int getMaxCandles(String instrument);
}
