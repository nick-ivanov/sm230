//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

// Simplified research candle: mid-price only, no bids/asks
public class SM230Candle {
    private String instrument;
    private long timestamp;
    private String granularity;
    private double open;
    private double high;
    private double low;
    private double close;

    public SM230Candle(String instrument, long timestamp, String granularity, double open, double high, double low, double close) {
        this.instrument = instrument;
        this.timestamp = timestamp;
        this.granularity = granularity;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}
