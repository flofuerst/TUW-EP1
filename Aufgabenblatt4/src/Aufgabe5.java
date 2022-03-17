/*
    Aufgabe 5) Eindimensionale Arrays und File I/O
*/

import codedraw.CodeDraw;
import codedraw.textformat.HorizontalAlign;
import codedraw.textformat.TextFormat;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Aufgabe5 {

    private static String[] readFileData(String fileName, int firstLine, int lastLine) {

        String[] textLines = new String[lastLine - firstLine + 1];
        int lineIndex = 0;
        String tempLine;

        try {
            Scanner inputStream = new Scanner(new FileReader("./src/" + fileName));
            int i = 0;

            while (inputStream.hasNext()) {
                if (lineIndex < firstLine - 1) { //skips rows, which are before "firstLine"
                    inputStream.nextLine();
                    lineIndex++;
                } else if (i >= textLines.length) { //if rows are above "lastline" --> break
                    break;
                } else {
                    textLines[i] = inputStream.nextLine(); //if correct lines --> save entries in textLines
                    i++;
                }
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println("File... " + System.getProperty("user.dir") + "\\src\\" + fileName + " not found!");
            System.exit(1);
        }
        return textLines;
    }

    private static void extractData(String[] dataArray, int[] resultArray, int indexColumn, int entriesPerYear) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < dataArray.length; i++) {
            String[] singleEntries = dataArray[i].split(";"); //splits every row at ";"
            //--> every element in new array "singleEntries" is column-entry of this row

            //sums up the numbers of the ice days
            sum += Integer.parseInt(singleEntries[indexColumn]);

            /* if 12 entries of ice days are summed up (equals ice days of 1 year)
            ,then sum is put into "resultArray" and sum is set back to 0. */
            if ((i + 1) % entriesPerYear == 0) {
                resultArray[count] = sum; // "i / entriesPerYear" could be instead of "count"
                sum = 0;
                count++;
            }
        }
    }

    private static void drawChart(CodeDraw myDrawObj, int[] iceDays) {
        TextFormat format = myDrawObj.getTextFormat();
        format.setFontSize(10);
        format.setHorizontalAlign(HorizontalAlign.CENTER);

        int sum = 0, average = 0;
        for (int i : iceDays) {
            sum += i; //sum of all ice days
        }
        average = sum / iceDays.length; //calculating average; iceDays.length = 51 (51 years)
        for (int x = 0; x < iceDays.length; x++) {
            boolean aboveAverage = iceDays[x] >= average; //checks if ice days in specific year is above average
            int xOffset = 15 + 5 * x + 20 * x; //calculating offset on x-axis

            //changes color if ice days are above average (or under)
            myDrawObj.setColor(aboveAverage ? Color.BLUE : Color.CYAN);
            myDrawObj.fillRectangle(xOffset, myDrawObj.getHeight() - 10 * iceDays[x], 20, 10 * iceDays[x]);

            //selecting color of text
            myDrawObj.setColor(aboveAverage ? Color.white : Color.black);

//          format integer with 2 digits & left padding with zero
            myDrawObj.drawText(xOffset + 10, myDrawObj.getHeight() - 10, String.format("%02d", ((70 + x) % 100)));

            myDrawObj.setColor(Color.black);
            myDrawObj.drawText(xOffset + 10, myDrawObj.getHeight() - 10 * iceDays[x] - 10, Integer.toString(iceDays[x]));

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int width = 1300;
        int height = 525;
        CodeDraw myDrawObj = new CodeDraw(width, height);
        myDrawObj.setTitle("Ergebnis Wetterdaten - EISTAGE");

        String[] data = readFileData("weather_data.csv", 182, 793);

        int[] resultArray = new int[51];
        extractData(data, resultArray, 12, 12);

        drawChart(myDrawObj, resultArray);
        myDrawObj.show();
    }
}
