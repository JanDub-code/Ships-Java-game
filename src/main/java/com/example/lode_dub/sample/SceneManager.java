package com.example.lode_dub.sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void showWelcomeScreen() {
        Scene welcomeScene = new Scene(new WelcomeScreen(), 1280, 720);
        primaryStage.setScene(welcomeScene);
    }

    public static void showAboutScreen() {
        Scene aboutScene = new Scene(new AboutScreen(), 1280, 720);
        primaryStage.setScene(aboutScene);
    }

    public static void  showShip_Screen(){
        Scene shipScene = new Scene(new Ship_Screen(), 1280, 720);
        primaryStage.setScene(shipScene);
    }
    public static void showGameOverScreen(String winner) {
        Scene overScene = new Scene(new GameOverScreen(winner), 1280, 720);
        primaryStage.setScene(overScene);
    }

}

