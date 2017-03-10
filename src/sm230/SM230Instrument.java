//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import java.util.ArrayList;
import java.util.HashMap;

public class SM230Instrument {
    private String code;    // ISO 4217 code
    private String baseCurrency;    // Forex only
    private String quoteCurrency;   // Forex only
    private String baseCurrencyName;    // Forex only. E.g., "Euro"
    private String quoteCurrencyName;   // Forex only. E.g., "US Dollar"
    private String nickname;    // "Euro", "Yen", "Cable", "Aussie", "Loonie", "Kiwi", etc.
    private final ArrayList<OHLC> data = new ArrayList<>();
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

    public ArrayList<OHLC> getData() {
        return data;
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
