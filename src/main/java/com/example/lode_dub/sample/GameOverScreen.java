package com.example.lode_dub.sample;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Stack;
public class GameOverScreen extends StackPane{
    private Button returnButton;
    private String Winner;

    public GameOverScreen(String winner) {
        Winner=winner;
        setupUI();
        setAboutScreen();

    }

    private void setupUI() {
        Image backgroundImage = new Image("file:src/main/resources/com/example/lode_dub/images/background.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1280);
        backgroundImageView.setFitHeight(720);

        Rectangle backgroundRectangle = new Rectangle(720, 480, Color.LIGHTBLUE);
        backgroundRectangle.setOpacity(0.5);
        Text winnerText = new Text("Congratulations, " + Winner + "! You won!");
        winnerText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        winnerText.setFill(Color.GREEN);


        returnButton = createStyledButton("Return back", 120, 30);

        StackPane.setAlignment(returnButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(returnButton, new Insets(0, 0, 20, 0));

        StackPane mainPane = new StackPane(backgroundImageView, backgroundRectangle, winnerText, returnButton);
        mainPane.setAlignment(Pos.CENTER);

        getChildren().add(mainPane);
    }

    private void setAboutScreen() {
        returnButton.setOnAction(event -> SceneManager.showWelcomeScreen());
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
}
