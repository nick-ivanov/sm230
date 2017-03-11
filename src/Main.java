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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final double width = 400.0;
    private final double height = 300.0;

    @Override
    public void start(Stage primaryStage) {
        Chart chart = new Chart(width, height);

        BorderPane root = new BorderPane();
        root.setCenter(chart);

        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

//        LoginDialog loginDialog = new LoginDialog();
//        loginDialog.askCredentials();

//        System.out.println("USERNAME: " + loginDialog.getUsername() +
//            ", PASSWORD: " + loginDialog.getPassword());


//        try {
//            System.out.println("Hi");
//            oanda.initProvider("src/sm230/sm230.properties", loginDialog.getUsername(), loginDialog.getPassword());
//        } catch(Exception ex) {
//            System.out.println("Couldn't initialize Oanda provider driver: " + ex.getMessage());
//            Platform.exit();
//        }

//        SM230Instrument instrument = new SM230Instrument("EUR/USD");
//        instrument.setProvider(oanda);
//
//        try {
//            instrument.updateCandleData();
//        } catch(Exception ex) {
//            System.out.println("Unable to update candle data: " + ex.getMessage());
//            Platform.exit();
//        }
//
//        int n = instrument.getCandleData().get("H1").size();
//        int m = instrument.getCandleData().keySet().size();
//
//        System.out.println("Number of granularities: " + m);
//
//        System.out.println("Grans: " + instrument.getCandleData().keySet().toString());
//
//        for(int i = 0; i < n; i++) {
//            System.out.println("Candle #" + i + ": " +
//                instrument.getCandleData().get("H1").get(i).getOpen() + " " +
//                instrument.getCandleData().get("H1").get(i).getHigh() + " " +
//                instrument.getCandleData().get("H1").get(i).getLow() + " " +
//                instrument.getCandleData().get("H1").get(i).getClose() + " "
//            );
//        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
