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

package sm230;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.util.ArrayList;

public class Chart extends Canvas {
    private int numberOfCandlesShown = 0;
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
    private SM230Instrument SM230Instrument;

    Chart(double width, double height) {
        super(width, height);
        graphicsContext = this.getGraphicsContext2D();

        ArrayList<String> timestamps = new ArrayList<>();

        final int NUMBER_OF_CANDLES = 10;

        ProviderInterface oanda = new OandaProviderDriver();

        try {
            System.out.println("here1");
            oanda.initProvider("src/sm230/sm230.properties", null, null);
        } catch (Exception ex) {
            System.out.println("Unable to initialize Oanda provier: " + ex.getMessage());
        }

        ArrayList<SM230Candle> candles = new ArrayList<>();

        try {
            candles = oanda.getRecentCandles("EUR/USD", "H1", NUMBER_OF_CANDLES);
        } catch(Exception ex) {
            System.out.println("Unable to obtain candles: " + ex.getMessage());
            Platform.exit();
        }

        double minPrice = Double.MAX_VALUE;
        double maxPrice = 0.0;

        for(int i = 0; i < NUMBER_OF_CANDLES; i++) {

            if (candles.get(i).getHigh() > maxPrice) {
                maxPrice = candles.get(i).getHigh();
            }

            if (candles.get(i).getLow() < minPrice) {
                minPrice = candles.get(i).getLow();
            }
        }
        setTimestampBarHeight(20.0);
        setFrameMinPrice(minPrice);
        setFrameMaxPrice(maxPrice);
        setFrameWidthPixels(width);
        setFrameHeightPixels(height - getTimestampBarHeight());
        setFrameNumberOfPeriods(NUMBER_OF_CANDLES);
        setNumberOfPeriods(NUMBER_OF_CANDLES);
        setPeriodWidthPixels(getFrameWidthPixels() / getFrameNumberOfPeriods());
        drawGrid(2);

        for(int i = 0; i < NUMBER_OF_CANDLES; i++) {
            drawCandle(i, 20.0,
                    candles.get(i).getOpen(),
                    candles.get(i).getHigh(),
                    candles.get(i).getLow(),
                    candles.get(i).getClose()
            );

            timestamps.add(candles.get(i).getTime().getHour() + ":00");
        }

        populateTimestampBar(timestamps, 0, 2);


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

    public SM230Instrument getSM230Instrument() {
        return SM230Instrument;
    }

    public void setSM230Instrument(SM230Instrument SM230Instrument) {
        this.SM230Instrument = SM230Instrument;
    }

    public int getNumberOfCandlesShown() {
        return numberOfCandlesShown;
    }

    public void setNumberOfCandlesShown(int numberOfCandlesShown) {
        this.numberOfCandlesShown = numberOfCandlesShown;
    }
}
