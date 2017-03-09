//    Copyright (C) Nick Ivanov <nick@nnbits.org> <nnrowan@gmail.com>
//    All rights reserved.

package sm230;

import javafx.application.Application;
import javafx.application.Platform;
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

        LoginDialog loginDialog = new LoginDialog();
        loginDialog.askCredentials();

        System.out.println("USERNAME: " + loginDialog.getUsername() +
            ", PASSWORD: " + loginDialog.getPassword());

        ProviderInterface oanda = new OandaProviderDriver();

        try {
            System.out.println("Hi");
            oanda.initProvider("src/sm230/sm230.properties", loginDialog.getUsername(), loginDialog.getPassword());
        } catch(Exception ex) {
            System.out.println("Couldn't initialize Oanda provider driver: " + ex.getMessage());
            Platform.exit();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
