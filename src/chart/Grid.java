//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

public class Grid {
    Chart chart;

    public Grid(Chart chart) {
        this.chart = chart;
    }

//    public void drawGrid(int gridPeriod) {
//        double frameWidthPixels = chart.getFrameWidthPixels();
//        double numberOfCandlesInFrame = chart.getNumberOfPeriods();
//
//        for(int i = 0; i < numberOfCandlesInFrame; i += gridPeriod) {
//            double candleSlotWidth = (frameWidthPixels / numberOfCandlesInFrame);
//            double centerX = candleSlotWidth * i + candleSlotWidth / 2.0;
//            chart.getGraphicsContext().setStroke(chart.getGridColor());
//            chart.getGraphicsContext().strokeLine(centerX, 0.0, centerX, chart.getFrameHeightPixels());
//        }
//
//        chart.getGraphicsContext().strokeRect(0, 0, chart.getFrameWidthPixels(), chart.getFrameHeightPixels());
//    }
}
