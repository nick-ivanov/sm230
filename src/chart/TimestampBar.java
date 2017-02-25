//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

public class TimestampBar {
    private Chart chart;

    public TimestampBar(Chart chart) {
        this.chart = chart;
    }

//    public void populateTimestampBar(ArrayList<String> allTimestamps, int firstTimestamp, int timestampStep) {
//        for(int i = firstTimestamp; i < chart.getNumberOfPeriods(); i += timestampStep) {
//
//            chart.getGraphicsContext().fillText(allTimestamps.get(i),
//                    i * chart.getPeriodWidthPixels(),
//                    chart.getFrameHeightPixels() + chart.getTimestampBarHeight());
//        }
//    }
}
