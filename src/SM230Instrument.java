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

import java.util.ArrayList;
import java.util.HashMap;

public class SM230Instrument {
    private String code;    // ISO 4217 code
    private String baseCurrency;    // Forex only
    private String quoteCurrency;   // Forex only
    private String baseCurrencyName;    // Forex only. E.g., "Euro"
    private String quoteCurrencyName;   // Forex only. E.g., "US Dollar"
    private String nickname;    // "Euro", "Yen", "Cable", "Aussie", "Loonie", "Kiwi", etc.
    private ProviderInterface provider;

    private final ArrayList<Tick> tickdata = new ArrayList<>();

    // This 800-lb gorilla stores candle data for each granularity supported
    // E.g., candleData.get("H1").get(0).getOpen() returns open price for first
    // 1-hour candle available.
    private HashMap<String, ArrayList<SM230Candle>> candleData = new HashMap<>();

    public SM230Instrument(String code) {
        this.code = code;
    }

    public void updateCandleData() throws Exception {
        for(String gran : provider.getGranularitiesAvailable()) {
            try {
                candleData.put(gran, provider.getRecentCandles(code, gran, provider.getMaxCandles(code)));
            } catch(Exception ex) {
                throw ex;
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getBaseCurrencyName() {
        return baseCurrencyName;
    }

    public void setBaseCurrencyName(String baseCurrencyName) {
        this.baseCurrencyName = baseCurrencyName;
    }

    public String getQuoteCurrencyName() {
        return quoteCurrencyName;
    }

    public void setQuoteCurrencyName(String quoteCurrencyName) {
        this.quoteCurrencyName = quoteCurrencyName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ProviderInterface getProvider() {
        return provider;
    }

    public void setProvider(ProviderInterface provider) {
        this.provider = provider;
    }

    public HashMap<String, ArrayList<SM230Candle>> getCandleData() {
        return candleData;
    }

    public void setCandleData(HashMap<String, ArrayList<SM230Candle>> candleData) {
        this.candleData = candleData;
    }
}
