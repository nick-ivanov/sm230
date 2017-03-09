//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import java.time.LocalDateTime;

// Simplified research candle: mid-price only, no bids/asks
public class SM230Candle {
    private String instrument;
    private LocalDateTime time;
    private String granularity;
    private double open;
    private double high;
    private double low;
    private double close;

    public SM230Candle(String instrument, LocalDateTime time, String granularity, double open, double high, double low, double close) {
        this.instrument = instrument;
        this.time = time;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
