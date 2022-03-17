/*
    Aufgabe 4) Rekursion + Zweidimensionale Arrays - primitive Landschaftssimulation
*/

import codedraw.CodeDraw;

import java.awt.*;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;

public class Aufgabe4 {

    private static final int canvasSize = 500;
    private static Random myRand = new Random(2021L);

    private static Color[][] genLandscape(int size) {

        //create landscape array and fill it with color according to the probability
        Color[][] landscape = new Color[size][size];
        for (int row = 0; row < landscape.length; row++) {
            for (int col = 0; col < landscape[0].length; col++) {
                landscape[row][col] = myRand.nextDouble() < 0.2 ? Color.GRAY : Color.GREEN;
            }
        }
        return landscape;
    }

    private static void drawLandscape(CodeDraw myDrawObj, Color[][] landscape) {

        //draw landscape on canvas. "canvasSize/ landscape.length" is size of 1 pixel
        for (int row = 0; row < landscape.length; row++) {
            for (int col = 0; col < landscape[0].length; col++) {
                myDrawObj.setColor(landscape[row][col]);
                myDrawObj.fillSquare((canvasSize / landscape.length) * col, (canvasSize / landscape.length) * row, (canvasSize / landscape.length));
            }
        }
        myDrawObj.show();
    }

    private static void simWaterFlow(Color[][] landscape, int row, int col) {
        //do nothing if water hits dark grey rock
        if (landscape[row][col] == Color.DARK_GRAY) return;

            //split water flow if water hits grey rock
        else if (landscape[row][col] == Color.GRAY) {
            landscape[row][col] = Color.DARK_GRAY;
            if (col - 1 >= 0) simWaterFlow(landscape, row, col - 1);
            if (col + 1 < landscape[0].length) simWaterFlow(landscape, row, col + 1);

            //water flows normally if water hits no rock
        } else {
            landscape[row][col] = Color.BLUE;

            //decision where water flows
            if (myRand.nextDouble() < 0.5) {
                //check if col and row is still in boundaries after next "flow step"
                if (col + 1 < landscape[0].length && row + 1 < landscape.length) {
                    simWaterFlow(landscape, row + 1, col + 1);
                }
                //check if col and row is still in boundaries after next "flow step"
            } else if (col - 1 >= 0 && row + 1 < landscape.length) {
                simWaterFlow(landscape, row + 1, col - 1);
            }
        }
    }

    private static void simSpreadingFire(Color[][] landscape, int row, int col) {
        //do nothing if fire hits rock or water
        if (landscape[row][col] != Color.GREEN) return;
        landscape[row][col] = Color.RED;

        //North
        if (myRand.nextDouble() >= 0.3) {
            if (row - 1 >= 0) simSpreadingFire(landscape, row - 1, col);
        }
        //East
        if (myRand.nextDouble() >= 0.3) {
            if (col + 1 < landscape[0].length) simSpreadingFire(landscape, row, col + 1);
        }
        //South
        if (myRand.nextDouble() >= 0.3) {
            if (row + 1 < landscape.length) simSpreadingFire(landscape, row + 1, col);
        }
        //West
        if (myRand.nextDouble() >= 0.3) {
            if (col - 1 >= 0) simSpreadingFire(landscape, row, col - 1);
        }

    }

    public static void main(String[] args) {
        CodeDraw myDrawObj = new CodeDraw(canvasSize, canvasSize);

        int size = 100;
        Color[][] landscape = genLandscape(size);

        drawLandscape(myDrawObj, landscape);

        simWaterFlow(landscape, 0, size / 2);
        drawLandscape(myDrawObj, landscape);

        landscape[20][20] = Color.GREEN;
        simSpreadingFire(landscape, 20, 20);
        drawLandscape(myDrawObj, landscape);
    }
}
