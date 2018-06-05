package com.steven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application{
    private final URL MAIN_LAYOUT = getClass().getResource("/main.fxml");

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN_LAYOUT);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);


//            primaryStage.setMaximized(true);
            primaryStage.show();

            Controller controller = loader.getController();
            controller.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
