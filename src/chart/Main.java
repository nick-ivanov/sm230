//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final double width = 400.0;
    private final double height = 300.0;

    @Override
    public void start(Stage primaryStage) {
        //Canvas canvas = new Canvas(width, height);
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        Chart chart = new Chart(width, height);

//        chart.setTimestampBarHeight(20.0);
//        chart.setFrameMinPrice(1.05165);
//        chart.setFrameMaxPrice(1.05715);
//        chart.setFrameWidthPixels(width);
//
//        chart.setFrameHeightPixels(height - chart.getTimestampBarHeight());
//        chart.setFrameNumberOfPeriods(8);
//        chart.setNumberOfPeriods(8);
//        chart.setPeriodWidthPixels(chart.getFrameWidthPixels() / chart.getFrameNumberOfPeriods());

        //System.out.println("wwwtn: " + chart.getPeriodWidthPixels());



        //Grid grid = new Grid(chart);
        //grid.drawGrid(2);

//        TimestampBar timestampBar = new TimestampBar(chart);
//        ArrayList<String> timestamps = new ArrayList<>(Arrays.asList(
//                "Feb 24",
//                "Feb 25",
//                "Feb 26",
//                "Feb 27",
//                "Feb 28",
//                "Mar 1",
//                "Mar 2",
//                "Mar 3"));
//        timestampBar.populateTimestampBar(timestamps, 0, 2);

//        Candle candle = new Candle(chart);
//        candle.drawCandle(0, 30.0, 1.05486, 1.05597, 1.05323, 1.05545);
//        candle.drawCandle(1, 30.0, 1.05544, 1.05656, 1.05432, 1.05464);
//        candle.drawCandle(2, 30.0, 1.05462, 1.05486, 1.05340, 1.05462);

        BorderPane root = new BorderPane();
        root.setCenter(chart);

        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
