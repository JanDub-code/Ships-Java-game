package com.example.lode_dub.sample;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class WelcomeScreen extends StackPane {

    private MenuItem newGameItem;
    private MenuItem aboutItem;
    private MenuItem exitItem;
    private Button newGameButton;
    private Button aboutButton;
    private Button exitButton;


    public WelcomeScreen() {
        setupUI();
        setWelcomeScreen();
    }

    private void setupUI() {
        Image backgroundImage = new Image("file:src/main/resources/com/example/lode_dub/images/background.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1280);
        backgroundImageView.setFitHeight(720);
        getChildren().add(backgroundImageView);

        ContextMenu contextMenu = createContextMenu();
        newGameItem = new MenuItem("New Game");
        aboutItem = new MenuItem("About");
        exitItem = new MenuItem("Exit");


        contextMenu.getItems().addAll(newGameItem, aboutItem, exitItem);

        VBox buttonsVBox = createButtonsVBox();

        VBox contentVBox = new VBox(20);
        contentVBox.setAlignment(Pos.CENTER);
        contentVBox.getChildren().addAll(buttonsVBox);

        getChildren().add(contentVBox);
    }

    private ContextMenu createContextMenu() {
        return new ContextMenu();
    }

    private VBox createButtonsVBox() {
        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER_RIGHT);

        newGameButton = createStyledButton("New Game", 120, 40);
        aboutButton = createStyledButton("About", 120, 30);
        exitButton = createStyledButton("Exit", 120, 30);


        buttonsVBox.getChildren().addAll(newGameButton, aboutButton, exitButton);
        return buttonsVBox;
    }

    private Button createStyledButton(String text, double minWidth, double minHeight) {
        Button button = new Button(text);
        button.setMinWidth(minWidth);
        button.setMinHeight(minHeight);
        button.setOpacity(0.75);

        button.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-color: #1E90FF; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 15px;"
        );

        return button;
    }

    private void setWelcomeScreen() {
        newGameItem.setOnAction(event -> SceneManager.showShip_Screen());
        aboutItem.setOnAction(event -> SceneManager.showAboutScreen());
        exitItem.setOnAction(event -> Platform.exit());

        newGameButton.setOnAction(event -> SceneManager.showShip_Screen());
        aboutButton.setOnAction(event -> SceneManager.showAboutScreen());
        exitButton.setOnAction(event -> Platform.exit());
    }
}