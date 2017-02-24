//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final double width = 400.0;
    private final double height = 300.0;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ChartParameters chartParameters = new ChartParameters(gc);

        chartParameters.setFrameMinPrice(1.05165);
        chartParameters.setFrameMaxPrice(1.05715);
        chartParameters.setFrameWidthPixels(width);
        chartParameters.setFrameHeightPixels(height);
        chartParameters.setNumberOfPeriods(8);

        Candle candle = new Candle(chartParameters);
        candle.drawCandle(0, 30.0, 1.05486, 1.05597, 1.05323, 1.05545);
        candle.drawCandle(1, 30.0, 1.05544, 1.05656, 1.05432, 1.05464);
        candle.drawCandle(2, 30.0, 1.05462, 1.05486, 1.05340, 1.05462);



        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
