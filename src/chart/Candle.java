//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package chart;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Candle {
    private GraphicsContext graphicsContext;
    private Color color;
    private Color bullColor;
    private Color bearColor;
    private Color emptyColor;
    private Color shadeColor;

    Candle(ChartParameters params) {
        updateParameters(params);
    }

    public void updateParameters(ChartParameters params) {
        graphicsContext = params.getGraphicsContext();
    }

    public void drawCandle(int number, double o, double h, double l, double c) {
        if(c > o) {
            graphicsContext.setFill(Color.GREEN);
        } else if(c < o) {

        }

        graphicsContext.setStroke(Color.GREEN);
        graphicsContext.setLineWidth(2);

        graphicsContext.strokeLine(20, 10, 20, 30);
        graphicsContext.fillRect(10, 30, 20, 100);
        graphicsContext.strokeLine(20, 130, 20, 160);
    }
}
