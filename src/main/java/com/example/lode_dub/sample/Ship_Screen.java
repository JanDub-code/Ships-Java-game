package com.example.lode_dub.sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;


public class Ship_Screen extends StackPane {
    private Button returnButton;
    private GridPane player;
    private GridPane computer;
    public Ship_Screen() {
        setGame();
        setShipScreen();
    }
    public char[][] gameMatrixPlayer;
    public char[][] gameMatrixComputer;
    public int playerHealth=20;
    public int computerHealth=20;
    private int[] shipCount = {1,1,1,1,2,2,2,3,3,4};
    private boolean playerTurn = true;
    private Text playerHealthText;
    private Text computerHealthText;
    private ArrayList<Integer> usedPositions = new ArrayList<>();


    private void setGame() {
        playerHealthText = new Text("Player Health: " + playerHealth);
        computerHealthText = new Text("Computer Health: " + computerHealth);
        Text text = new Text("          |   ");

        playerHealthText.setFont(Font.font("Arial", FontWeight.BOLD, 28)); // Nastavení velikosti písma a tučnosti
        computerHealthText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        playerHealthText.setFill(Color.RED);
        computerHealthText.setFill(Color.RED);
        text.setFill(Color.RED);

        HBox healthInfoBox = new HBox(playerHealthText, text, computerHealthText);
        healthInfoBox.setAlignment(Pos.CENTER);
        healthInfoBox.setSpacing(10);
        StackPane.setAlignment(healthInfoBox, Pos.CENTER);
        StackPane.setMargin(healthInfoBox, new Insets(0, 0, -350, 0));

        gameMatrixPlayer = new char[10][10];
        gameMatrixComputer = new char[10][10];

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameMatrixPlayer[x][y] = 'O';
                gameMatrixComputer[x][y] = 'O';
            }
        }
        placeShips();

        player = createMatrix(gameMatrixPlayer);
        computer = createMatrix(gameMatrixComputer);

        player.setHgap(10);
        computer.setVgap(10);

        computer.setHgap(10);
        player.setVgap(10);

        HBox hbox = new HBox(
                createMatrixWithLabel("Player", player, gameMatrixPlayer),
                createMatrixWithLabel("Computer", computer, gameMatrixComputer)
        );
        hbox.setStyle("-fx-alignment: center;");
        hbox.setSpacing(20);

        Image backgroundImage = new Image("file:src/main/resources/com/example/lode_dub/images/background.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1280);
        backgroundImageView.setFitHeight(720);

        returnButton = createStyledButton("Return back", 120, 30);

        StackPane.setAlignment(returnButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(returnButton, new Insets(0, 0, 20, 0));

        StackPane mainPane = new StackPane(backgroundImageView, healthInfoBox, hbox, returnButton);
        mainPane.setAlignment(Pos.CENTER);


        getChildren().add(mainPane);
    }

    private void placeShips() {
        for (int shipSize : shipCount) {
            placeShipRandomly(shipSize, gameMatrixPlayer);
            placeShipRandomly(shipSize, gameMatrixComputer);
        }
    }
    private void placeShipRandomly(int shipSize, char[][] gameMatrix) {
        int x, y;
        boolean horizontal;

        do {
            Random random=new Random();
            x = random.nextInt(10);
            y = random.nextInt(10);
            horizontal = random.nextBoolean();
        } while (!isValidPlacement(x, y, shipSize, horizontal, gameMatrix));


        for (int i = 0; i < shipSize; i++) {
            if (horizontal) {
                gameMatrix[x][y + i] = 'X';
            } else {
                gameMatrix[x + i][y] = 'X';
            }
        }
    }

    private boolean isValidPlacement(int x, int y, int shipSize, boolean horizontal, char[][] gameMatrix) {
        if (horizontal) {
            if (y + shipSize > 10) {
                return false;
            }
            for (int i = 0; i < shipSize; i++) {
                if (gameMatrix[x][y + i] != 'O') {
                    return false;
                }
            }
        } else {
            if (x + shipSize > 10) {
                return false;
            }
            for (int i = 0; i < shipSize; i++) {
                if (gameMatrix[x + i][y] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private GridPane createMatrix(char[][] matrixData) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(5);

        for (int i = 0; i < matrixData.length; i++) {
            for (int j = 0; j < matrixData[i].length; j++) {
                Button button = createButtonWithLetter(matrixData[i][j],matrixData,i,j);
                gridPane.add(button, j, i);
            }
        }

        return gridPane;
    }

    private Button createButtonWithLetter(char letter,char[][] matrixData,int i,int j) {
        Button button = new Button();
        char[][] data=matrixData;
        int I=i;
        int J=j;
        button.setPrefSize(35, 35);
        button.setStyle("-fx-base: lightblue;");
        button.setOnAction(event -> {
            button.setText(String.valueOf(letter)); // Aktualizace obsahu tlačítka po kliknutí
            if(letter=='X'){
                if(data==gameMatrixPlayer){
                    playerHealth--;
                } else if (data==gameMatrixComputer) {
                    computerHealth--;
                    playerTurn=false;
                    //Button but = findButton(player,4,4);
                    //but.fire();
                    computerTurn();
                }
                updateHealthInfo();
            }
            if(letter=='O'){
                if (data==gameMatrixComputer) {
                    playerTurn=false;
                    //Button but = findButton(player,3,4);
                    //but.fire();
                    computerTurn();

                }
                updateHealthInfo();
            }
            button.setDisable(true); // Po kliknutí tlačítko nelze znovu kliknout
        });
        return button;
    }
    Random random = new Random();
    private void computerTurn(){
        int randomRow, randomCol;
        randomRow = random.nextInt(10) ;
        randomCol = random.nextInt(10) ;
        int position =randomRow*10+randomCol;
        if(!usedPositions.contains(position)){
            usedPositions.add(position);
            Button but = findButton(player,randomRow,randomCol);
            but.fire();
            playerTurn=true;
        }
        else {
            computerTurn();
        }

    }

    private Button findButton(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }
    private GridPane createMatrixWithLabel(String label, GridPane matrix, char[][] matrixData) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10); // Mezera mezi popiskem a maticí

        Text labelText = new Text(label);
        labelText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        labelText.setFill(Color.RED);
        gridPane.add(labelText, 0, 0);
        gridPane.add(matrix, 0, 1);

        return gridPane;
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
    private void updateHealthInfo() {
        // Aktualizace textů se zdravím hráče a počítače
        playerHealthText.setText("Player Health: " + playerHealth);
        computerHealthText.setText("Computer Health: " + computerHealth);

        if (playerHealth <= 0) {
            SceneManager.showGameOverScreen("Computer");
        } else if (computerHealth <= 0) {
            SceneManager.showGameOverScreen("Player");
        }
    }
    private void setShipScreen(){
        returnButton.setOnAction(event -> SceneManager.showWelcomeScreen());
    }
}