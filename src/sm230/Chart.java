//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Chart extends Canvas {
    private GraphicsContext graphicsContext;
    private long periodSeconds = 0;
    private Instant startTime = Instant.MIN;
    private int frameStartPeriod = 0;
    private int frameNumberOfPeriods = 0;
    private double periodWidthPixels = 0.0;
    private int numberOfPeriods = 0;
    private double frameMinPrice = 0.0;
    private double frameMaxPrice = 0.0;
    private Color foregroundBullColor = Color.GREEN;
    private Color foregroundBearColor = Color.RED;
    private Color foregroundEmptyColor = Color.BLACK;
    private Color foregroundShadowColor = Color.GREY;
    private Color backgroundColor = Color.BLACK;
    private double frameWidthPixels = 0.0;
    private double frameHeightPixels = 0.0;
    private Color gridColor = Color.LIGHTPINK;
    private double timestampBarHeight = 0.0;
    private double shadowThickness = 2.0;  // TODO: Should become flexible in the future
    private Instrument instrument;

    Chart(double width, double height) {
        super(width, height);
        graphicsContext = this.getGraphicsContext2D();

        setTimestampBarHeight(20.0);
        setFrameMinPrice(1.05165);
        setFrameMaxPrice(1.05715);
        setFrameWidthPixels(width);
        setFrameHeightPixels(height - getTimestampBarHeight());
        setFrameNumberOfPeriods(8);
        setNumberOfPeriods(8);
        setPeriodWidthPixels(getFrameWidthPixels() / getFrameNumberOfPeriods());

        drawGrid(2);

        ArrayList<String> timestamps = new ArrayList<>(Arrays.asList(
                "Feb 24",
                "Feb 25",
                "Feb 26",
                "Feb 27",
                "Feb 28",
                "Mar 1",
                "Mar 2",
                "Mar 3")
        );

        populateTimestampBar(timestamps, 0, 2);

        drawCandle(0, 30.0, 1.05486, 1.05597, 1.05323, 1.05545);
        drawCandle(1, 30.0, 1.05544, 1.05656, 1.05432, 1.05464);
        drawCandle(2, 30.0, 1.05462, 1.05486, 1.05340, 1.05462);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clickity-click! X: " + event.getX() + ", Y: " + event.getY());
            }
        });
    }

    private void drawCandle(int candleNumber, double candleWidth, double open, double high, double low, double close) {
        graphicsContext.setStroke(foregroundShadowColor);
        graphicsContext.setLineWidth(shadowThickness);
        double priceValueInOnePixel = (frameMaxPrice - frameMinPrice) / frameHeightPixels;
        double bodyTopY, bodyBottomY;
        double highY = (frameMaxPrice - high) / priceValueInOnePixel;
        double lowY = (frameMaxPrice - low) / priceValueInOnePixel;

        double candleSlotWidth = (frameWidthPixels / frameNumberOfPeriods);
        double centerX = candleSlotWidth * candleNumber + candleSlotWidth / 2.0;
        double x1 = centerX - candleWidth / 2.0;
        double x2 = centerX + candleWidth / 2.0;

        // Bodies of bull, bear and doji candles are drawn differently
        if(close > open) {  // Bull (green/white candle)
            graphicsContext.setFill(foregroundBullColor);
            bodyTopY = (frameMaxPrice - close) / priceValueInOnePixel;
            bodyBottomY = (frameMaxPrice - open) / priceValueInOnePixel;
        } else if(close < open) { // Bear (read/black candle)
            graphicsContext.setFill(foregroundBearColor);
            bodyTopY = (frameMaxPrice - open) / priceValueInOnePixel;
            bodyBottomY = (frameMaxPrice - close) / priceValueInOnePixel;
        } else { // doji candle (no real body)
            bodyBottomY = bodyTopY = (frameMaxPrice - close) / priceValueInOnePixel;
        }

        graphicsContext.strokeLine(centerX, highY, centerX, bodyTopY);

        if(open == close) {
            graphicsContext.setStroke(foregroundEmptyColor);
            graphicsContext.strokeLine(x1, bodyBottomY, x2, bodyBottomY);
            graphicsContext.setStroke(foregroundShadowColor);

        } else {
            graphicsContext.fillRect(x1, bodyTopY, candleWidth, bodyBottomY - bodyTopY);
        }

        graphicsContext.strokeLine(centerX, bodyBottomY, centerX, lowY);
    }

    private void populateTimestampBar(ArrayList<String> allTimestamps, int firstTimestamp, int timestampStep) {
        for(int i = firstTimestamp; i < numberOfPeriods; i += timestampStep) {
            graphicsContext.fillText(allTimestamps.get(i),
                    i * periodWidthPixels,
                    frameHeightPixels + timestampBarHeight);
        }
    }

    private void drawGrid(int gridPeriod) {
        for(int i = 0; i < numberOfPeriods; i += gridPeriod) {
            double candleSlotWidth = (frameWidthPixels / numberOfPeriods);
            double centerX = candleSlotWidth * i + candleSlotWidth / 2.0;
            graphicsContext.setStroke(gridColor);
            graphicsContext.strokeLine(centerX, 0.0, centerX, frameHeightPixels);
        }

        graphicsContext.strokeRect(0.0, 0.0, frameWidthPixels, frameHeightPixels);
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

    public Color getGridColor() {
        return gridColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public double getTimestampBarHeight() {
        return timestampBarHeight;
    }

    public void setTimestampBarHeight(double timestampBarHeight) {
        this.timestampBarHeight = timestampBarHeight;
    }

    public double getShadowThickness() {
        return shadowThickness;
    }

    public void setShadowThickness(double shadowThickness) {
        this.shadowThickness = shadowThickness;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}