//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.time.Instant;

public class ChartParameters {
    private GraphicsContext graphicsContext;
    private long periodSeconds = 0;
    private Instant startTime = Instant.MIN;
    private int frameStartPeriod = 0;
    private int frameNumberOfPeriods = 0;
    private double periodWidthPixels = 0.0;
    private int numberOfPeriods = 0;
    private double frameMinPrice = 0.0;
    private double frameMaxPrice = 0.0;
    private String instrument = "";
    private Color foregroundBullColor = Color.GREEN;
    private Color foregroundBearColor = Color.RED;
    private Color foregroundEmptyColor = Color.BLACK;
    private Color foregroundShadowColor = Color.GREY;
    private Color backgroundColor = Color.BLACK;
    private double frameWidthPixels = 0.0;
    private double frameHeightPixels = 0.0;

    ChartParameters(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public int getFrameStartPeriod() {
        return frameStartPeriod;
    }

    public void setFrameStartPeriod(int frameStartPeriod) {
        this.frameStartPeriod = frameStartPeriod;
    }

    public int getFrameNumberOfPeriods() {
        return frameNumberOfPeriods;
    }

    public void setFrameNumberOfPeriods(int frameNumberOfPeriods) {
        this.frameNumberOfPeriods = frameNumberOfPeriods;
    }

    public double getPeriodWidthPixels() {
        return periodWidthPixels;
    }

    public void setPeriodWidthPixels(double periodWidthPixels) {
        this.periodWidthPixels = periodWidthPixels;
    }

    public long getPeriodSeconds() {
        return periodSeconds;
    }

    public void setPeriodSeconds(long periodSeconds) {
        this.periodSeconds = periodSeconds;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(int numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }

    public double getFrameMinPrice() {
        return frameMinPrice;
    }

    public void setFrameMinPrice(double frameMinPrice) {
        this.frameMinPrice = frameMinPrice;
    }

    public double getFrameMaxPrice() {
        return frameMaxPrice;
    }

    public void setFrameMaxPrice(double frameMaxPrice) {
        this.frameMaxPrice = frameMaxPrice;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public GraphicsContext getGraphicsContext() {
        return this.graphicsContext;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Color getForegroundBullColor() {
        return foregroundBullColor;
    }

    public void setForegroundBullColor(Color foregroundBullColor) {
        this.foregroundBullColor = foregroundBullColor;
    }

    public Color getForegroundBearColor() {
        return foregroundBearColor;
    }

    public void setForegroundBearColor(Color foregroundEmptyColor) {
        this.foregroundBearColor = foregroundBearColor;
    }

    public Color getForegroundEmptyColor() {
        return foregroundEmptyColor;
    }

    public void setForegroundEmptyColor(Color foregroundEmptyColor) {
        this.foregroundEmptyColor = foregroundBearColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundShadowColor() {
        return foregroundShadowColor;
    }

    public void setForegroundShadowColor(Color foregroundShadowColor) {
        this.foregroundShadowColor = foregroundShadowColor;
    }

    public double getFrameWidthPixels() {
        return frameWidthPixels;
    }

    public void setFrameWidthPixels(double frameWidthPixels) {
        this.frameWidthPixels = frameWidthPixels;
    }

    public double getFrameHeightPixels() {
        return frameHeightPixels;
    }

    public void setFrameHeightPixels(double frameHeightPixels) {
        this.frameHeightPixels = frameHeightPixels;
    }
}
