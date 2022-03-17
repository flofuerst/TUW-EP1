/*
    Aufgabe 1) Zweidimensionale Arrays und Gameplay - Mastermind
*/

import codedraw.CodeDraw;
import codedraw.Palette;
import codedraw.textformat.HorizontalAlign;
import codedraw.textformat.TextFormat;
import codedraw.textformat.VerticalAlign;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Aufgabe1 {
    // global constants
    private static final int NUMBER_OF_TURNS = 10;
    private static final int CODE_LENGTH = 4;
    private static final int NUMBER_OF_COLUMNS = CODE_LENGTH * 2 + 1;
    private static final Color[] COLORS = new Color[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.DARK_GRAY, Color.RED, Color.PINK, Color.YELLOW};

    // global variables
    private static int[][] playField = null;
    private static int[][] tips = null;
    private static int turn = 0;
    private static int turnCount = 0;
    private static boolean gameWon = false;
    private static int pin = 0;
    private static int[] solution = null;

    // initializes all global variables for the game
    private static void initGame() {
        playField = new int[NUMBER_OF_TURNS][CODE_LENGTH];
        tips = new int[NUMBER_OF_TURNS][CODE_LENGTH]; // 1 == red; 2 == white
        turn = 0;
        pin = 0;
        solution = generateCode();
    }

    // generates array with length CODE_LENGTH and random numbers from 1 to COLORS.length
    private static int[] generateCode() {
        int[] rndmColor = new int[CODE_LENGTH];
        int rndm;
        boolean containsColor = false;

        //iterate CODE_LENGTH times
        for (int i = 0; i < CODE_LENGTH; i++) {
            //generate random number
            rndm = (int) (Math.random() * 10);

            //check if number already exists in array. Also eliminates the index 0
            for (int x : rndmColor) {
                if (x == rndm) containsColor = true;
            }
            //if generated number already exists in array --> generate number again (for this index)
            if (containsColor) {
                containsColor = false;

                //decrease i to do the same iteration-step again
                i--;
            } else {
                //store random number in array
                rndmColor[i] = rndm;
            }
        }
        //for testing to print the solution
        System.out.println(Arrays.toString(rndmColor));
        return rndmColor;
    }

    // calculates red and white tip pins
    private static void updateTips() {
        int tippIndex = 0;
        int redCount = 0;
        //create new array which indicates which red tip is already set
        int[] setRed = new int[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            //if guessed right --> set red tip & also indicate it in setRed array
            if (playField[NUMBER_OF_TURNS-turn-1][i] == solution[i]) {
                setRed[i] = -1;
                tips[NUMBER_OF_TURNS-turn-1][tippIndex++] = 1;
                redCount++;
            }
        }
        //if whole code is guessed right --> set gameWon true (which is used in processGameStep)
        if (redCount == CODE_LENGTH) gameWon=true;

        //check if all tips are not set yet & set white tip if color is right but not on right position
        if (tippIndex < CODE_LENGTH) {
            for (int guessedIndex = 0; guessedIndex < CODE_LENGTH; guessedIndex++) {
                if (setRed[guessedIndex] != -1) {
                    for (int solutionIndex = 0; solutionIndex < CODE_LENGTH; solutionIndex++) {
                        if (playField[NUMBER_OF_TURNS-turn-1][guessedIndex] == solution[solutionIndex]) {
                            //if color is right but position is false --> set white tip
                            tips[NUMBER_OF_TURNS-turn-1][tippIndex++] = 2;
                        }
                    }
                }

            }
        }
    }

    // draws game to screen
    private static void drawGame(CodeDraw myDrawObj) {
        myDrawObj.setColor(Color.LIGHT_GRAY);
        myDrawObj.fillRectangle(0, 0, myDrawObj.getWidth(), myDrawObj.getHeight());

        //every element has the size 80x80
        int radiusCircle = 80 / 2;
        int radiusTips = 80 / 8;
        int offsetCircle = (int) (radiusCircle / CODE_LENGTH * 1.5);
        for (int row = 0; row < NUMBER_OF_TURNS; row++) {
            for (int col = 0; col < CODE_LENGTH; col++) {

                //calculating x and y coordinates
                int x = radiusCircle + offsetCircle + col * (radiusCircle * 2 + offsetCircle);
                int y = radiusCircle + row * radiusCircle * 2;

                //draw outline of circle
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawCircle(x, y, radiusCircle);

                //set Color of Circle if already guessed
                if (playField[row][col] > 0) myDrawObj.setColor(COLORS[playField[row][col] - 1]);
                else myDrawObj.setColor(Color.WHITE);
                myDrawObj.fillCircle(x, y, radiusCircle);

                //only draw tip-circle if tip 1 or 2
                if (tips[row][col] != 0) {
                    //draw outline of tip-circle
                    myDrawObj.setColor(Color.BLACK);
                    myDrawObj.drawCircle(x + (myDrawObj.getWidth() - 80) / 2, y, radiusTips);

                    if (tips[row][col] == 1) myDrawObj.setColor(Color.RED);
                    else myDrawObj.setColor(Color.WHITE);

                    //draw filled tip-circle with determined color
                    myDrawObj.fillCircle(x + (myDrawObj.getWidth() - 80) / 2, y, radiusTips);
                }
            }
        }
        //draw color panels on right side
        for (int i = 0; i < COLORS.length; i++) {
            myDrawObj.setColor(COLORS[i]);
            myDrawObj.fillSquare(myDrawObj.getWidth() - 80, 80 * i, 80);
        }
        //draw "back"-image in right bottom corner
        myDrawObj.drawImage(myDrawObj.getWidth() - 80, myDrawObj.getHeight() - 80, "src/back_button.png");


        myDrawObj.setColor(Color.BLACK);
        myDrawObj.drawLine(400, 0, 400, 800);
        myDrawObj.drawLine(800, 0, 800, 800);
        myDrawObj.show();
    }


    private static void processGameStep(CodeDraw myDrawObj, MouseEvent me) {
        int[] clickPos = new int[2];
        clickPos[0] = me.getX();
        clickPos[1] = me.getY();

        int colorIndex;
        boolean colorTaken = false;

        int width = myDrawObj.getWidth();
        int height = myDrawObj.getHeight();

        //if clicked on back-button --> go back 1 step and color previous circle white
        //NUMBER_OF_TURNS-turn-1 is used to start at bottom of playfield (and don't begin at top)
        if (clickPos[0] >= myDrawObj.getWidth() - 80 && clickPos[1] >= myDrawObj.getHeight() - 80) {
            if (turnCount != 0) playField[NUMBER_OF_TURNS-turn-1][--turnCount] = 0;
        }

        //if clicked on color panel --> calculate index of color & fill circle with wanted color & go to next circle
        if (clickPos[0] >= myDrawObj.getWidth() - 80 && clickPos[1] < myDrawObj.getHeight() - 80) {
            colorIndex = clickPos[1] / 80;
            //check if color is already taken
            for (int i = 0; i < CODE_LENGTH; i++) {
                if (playField[NUMBER_OF_TURNS-turn-1][i] == colorIndex + 1) colorTaken = true;
            }

            //fill circle with color, if color was not taken before (in this turn)
            if (!colorTaken) playField[NUMBER_OF_TURNS-turn-1][turnCount++] = colorIndex + 1;
        }

        //next turn if all circles in row are set
        if (turnCount >= CODE_LENGTH) {
            updateTips();
            turn++;
            turnCount = 0;
        }
        drawGame(myDrawObj);

        //call showMessage based on result
        if(gameWon) showMessage("GAME WON! :)", myDrawObj, Color.GREEN);
        else if(turn >=NUMBER_OF_TURNS)showMessage("GAME LOST! :(", myDrawObj, Color.RED);
    }

    private static void showMessage (String message, CodeDraw myDrawObj, Color messageColor){
        //draw outline of message-rectangle
        myDrawObj.setColor(Color.BLACK);
        myDrawObj.drawRectangle(240, 300, 400, 200);

        //fill message-rectangle
        myDrawObj.setColor(Color.LIGHT_GRAY);
        myDrawObj.fillRectangle(240, 300, 400, 200);

        //set format of text and color of text (based on result)
        myDrawObj.setColor(messageColor);
        TextFormat txt = new TextFormat();
        txt.setHorizontalAlign(HorizontalAlign.CENTER);
        txt.setVerticalAlign(VerticalAlign.MIDDLE);
        myDrawObj.setTextFormat(txt);

        //draw text
        myDrawObj.drawText(myDrawObj.getWidth()/2, myDrawObj.getHeight()/2, message);

        //show message-rectangle for 5 seconds and then start clearing board
        myDrawObj.show(5000);
        clearBoard(myDrawObj);
    }

    // clears game board
    private static void clearBoard(CodeDraw myDrawObj) {
        //start clearing at first colored row and clear downwards
        for(int row = NUMBER_OF_TURNS-turn ; row < NUMBER_OF_TURNS; row++){
            for(int col = 0; col<CODE_LENGTH; col++){
                playField[row][col] = 0;
                tips[row][col] = 0;
            }
            //show cleared row for 500ms
            drawGame(myDrawObj);
            myDrawObj.show(500);
        }
        //init new game
        gameWon=false;
        initGame();
    }

    public static void main(String[] args) {

        int height = 800;
        int width = height + height / (COLORS.length + 1);

        CodeDraw myDrawObj = new CodeDraw(width, height);
        myDrawObj.setTitle("MasterMind Game");

        initGame();

        drawGame(myDrawObj);

        myDrawObj.onMouseClick(Aufgabe1::processGameStep);
    }
}



