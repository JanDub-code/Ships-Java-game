package com.example.lode_dub.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager.setPrimaryStage(primaryStage);

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        Scene welcomeScene = new Scene(welcomeScreen, 1280, 720);

        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Ships");
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}