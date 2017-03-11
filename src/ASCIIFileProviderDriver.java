/****************************************************************************
 *    sm230 -- Non-traditional Forex Research Tool
 *    Copyright (C) 2017  Nick Ivanov
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *    E-mail: nick@nnbits.org
 *    Website: http://nnbits.org/sm230
 *****************************************************************************/

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ASCIIFileProviderDriver implements ProviderInterface {
    @Override
    public void initProvider(String propertiesFile, String username, String password) throws Exception {

    }

    @Override
    public ArrayList<Tick> getTicks(String instrument, long beginTimestamp, long endTimestamp) {
        return null;
    }

    @Override
    public ArrayList<SM230Candle> getRecentCandles(String instrument, String granularity, int count) throws ClientProtocolException, IOException {
        ArrayList<SM230Candle> candles = new ArrayList<>();
        return candles;
    }

    @Override
    public ArrayList<String> getGranularitiesAvailable() {
        return new ArrayList<String>(Arrays.asList("M1"));
    }

    @Override
    public ArrayList<String> getInstrumentsAvailable() {
        return new ArrayList<String>(Arrays.asList(
                "EUR/USD", "EUR/CHF", "EUR/GBP", "EUR/JPY", "EUR/AUD", "USD/CAD", "USD/CHF", "USD/JPY",
                        "USD/MXN", "GBP/CHF", "GBP/JPY", "GBP/USD", "AUD/JPY", "AUD/USD", "CHF/JPY",
                        "NZD/JPY", "NZD/USD", "XAU/USD", "EUR/CAD", "AUD/CAD", "CAD/JPY", "EUR/NZD",
                        "GRX/EUR", "NZD/CAD", "SGD/JPY", "USD/HKD", "USD/NOK", "USD/TRY", "XAU/AUD",
                        "AUD/CHF", "AUX/AUD", "EUR/HUF", "EUR/PLN", "FRX/EUR", "HKX/HKD", "NZD/CHF",
                        "SPX/USD", "USD/HUF", "USD/PLN", "USD/ZAR", "XAU/CHF", "ZAR/JPY", "BCO/USD",
                        "ETX/EUR", "EUR/CZK", "EUR/SEK", "GBP/AUD", "GBP/NZD", "JPX/JPY", "UDX/USD",
                        "USD/CZK", "USD/SEK", "WTI/USD", "XAU/EUR", "AUD/NZD", "CAD/CHF", "EUR/DKK",
                        "EUR/NOK", "EUR/TRY", "GBP/CAD", "NSX/USD", "UKX/GBP", "USD/DKK", "USD/SGD",
                        "XAG/USD", "XAU/GBP"
                ));
    }

    @Override
    public int getMaxCandles(String instrument) {
        return 0;
    }
}
