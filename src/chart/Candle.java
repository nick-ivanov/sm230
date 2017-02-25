//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Candle {
    private GraphicsContext graphicsContext;
    private Color bullColor;
    private Color bearColor;
    private Color emptyColor;
    private Color shadowColor;
    private double frameMinPrice;
    private double frameMaxPrice;
    private double frameWidthPixels;
    private double frameHeightPixels;
    private int numberOfCandlesInFrame;

//    private final int shadowThickness = 2;  // TODO: Should become flexible in the future

    Candle(Chart chart) {
        updateParameters(chart);
    }

    public void updateParameters(Chart chart) {
        graphicsContext = chart.getGraphicsContext();
        bullColor = chart.getForegroundBullColor();
        bearColor = chart.getForegroundBearColor();
        emptyColor = chart.getForegroundEmptyColor();
        shadowColor = chart.getForegroundShadowColor();
        frameMinPrice = chart.getFrameMinPrice();
        frameMaxPrice = chart.getFrameMaxPrice();
        frameHeightPixels = chart.getFrameHeightPixels();
        frameWidthPixels = chart.getFrameWidthPixels();
        numberOfCandlesInFrame = chart.getNumberOfPeriods();
    }

//    public void drawCandle(int candleNumber, double candleWidth, double open, double high, double low, double close) {
//        graphicsContext.setStroke(shadowColor);
//        graphicsContext.setLineWidth(shadowThickness);
//        double priceValueInOnePixel = (frameMaxPrice - frameMinPrice) / frameHeightPixels;
//        double bodyTopY, bodyBottomY;
//        double highY = (frameMaxPrice - high) / priceValueInOnePixel;
//        double lowY = (frameMaxPrice - low) / priceValueInOnePixel;
//
//        double candleSlotWidth = (frameWidthPixels / numberOfCandlesInFrame);
//        double centerX = candleSlotWidth * candleNumber + candleSlotWidth / 2.0;
//        double x1 = centerX - candleWidth / 2.0;
//        double x2 = centerX + candleWidth / 2.0;
//
//        if(close > open) {
//            graphicsContext.setFill(bullColor);
//            bodyTopY = (frameMaxPrice - close) / priceValueInOnePixel;
//            bodyBottomY = (frameMaxPrice - open) / priceValueInOnePixel;
//        } else if(close < open) {
//            graphicsContext.setFill(bearColor);
//            bodyTopY = (frameMaxPrice - open) / priceValueInOnePixel;
//            bodyBottomY = (frameMaxPrice - close) / priceValueInOnePixel;
//        } else {
//            bodyBottomY = bodyTopY = (frameMaxPrice - close) / priceValueInOnePixel;
//        }
//
//        graphicsContext.strokeLine(centerX, highY, centerX, bodyTopY);
//
//        if(open == close) {
//            graphicsContext.setStroke(emptyColor);
//            graphicsContext.strokeLine(x1, bodyBottomY, x2, bodyBottomY);
//            graphicsContext.setStroke(shadowColor);
//
//        } else {
//            graphicsContext.fillRect(x1, bodyTopY, candleWidth, bodyBottomY - bodyTopY);
//        }
//
//        graphicsContext.strokeLine(centerX, bodyBottomY, centerX, lowY);
//    }
}
