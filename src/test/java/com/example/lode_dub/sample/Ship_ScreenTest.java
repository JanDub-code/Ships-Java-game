package com.example.lode_dub.sample;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Ship_ScreenTest  {

    //vložené funkce z ShipScreen, protože se nedokáže inicializovat javaFx v testu a mám nekonečné řádky eroru

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

    private void placeShips(char[][] test1,char[][] test2) {
        int[] shipCount={4};
        for (int shipSize : shipCount) {
            placeShipRandomly(shipSize, test1);
            placeShipRandomly(shipSize, test2);
        }
    }
    @Test
    public void testPlaceShips(){

        char[][] test1 = new char[10][10];
        char[][] test2 = new char[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                test1[x][y] = 'O';
                test2[x][y] = 'O';
            }
        }
        placeShips(test1,test2);
        int i1=0;
        int i2=0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (test1[i][j] == 'X') {
                    i1++;
                }
            }
        }
        assertEquals(4,i1);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (test2[i][j] == 'X') {
                    i2++;
                }
            }
        }
        assertEquals(4,i2);
    }

    @Test
    public void testIsValidPlacement() {
        char[][] gameMatrix = new char[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameMatrix[x][y] = 'O';
            }
        }

        boolean isValidPlacement = isValidPlacement(0, 0, 1, true, gameMatrix);
        placeShipRandomly(1, gameMatrix);

        assertTrue(isValidPlacement);
    }
    @Test
    public void testPlaceShipRandomly() {
        char[][] gameMatrix = new char[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                gameMatrix[x][y] = 'O';
            }
        }

        boolean isValidPlacement = isValidPlacement(0, 0, 1, true, gameMatrix);
        placeShipRandomly(1, gameMatrix);

        boolean shipPlaced = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (gameMatrix[i][j] == 'X') {
                    shipPlaced = true;
                    break;
                }
            }
        }
        assertTrue(shipPlaced);
    }

}