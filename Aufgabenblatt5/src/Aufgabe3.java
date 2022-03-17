/*
    Aufgabe 3) Zweidimensionale Arrays und CodeDraw - "Schwärzen ähnlicher Bildbereiche"
*/

import codedraw.CodeDraw;
import codedraw.Palette;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Aufgabe3 {

    // converts BufferedImage object to a grayscale array
    private static int[][] convertImg2Array(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int) (tempColor.getRed() * 0.3 + tempColor.getGreen() * 0.59 + tempColor.getBlue() * 0.11);
            }

        }
        return imgArray;
    }

    //draws the image array to the canvas
    private static void drawImage(int[][] imgArray) {
        CodeDraw cd = new CodeDraw(Math.max(imgArray[0].length, 150), Math.max(imgArray.length, 150));

        for (int y = 0; y < imgArray.length; y++) {
            for (int x = 0; x < imgArray[y].length; x++) {
                cd.setColor(Palette.fromGrayscale(imgArray[y][x]));
                cd.drawPixel(x, y);
            }
        }
        cd.show();
    }

    private static int[][] blackenSimilarRegions(int[][] imgArray, int rowStart, int rowEnd, int colStart, int colEnd, double threshold) {
        //create template and fill it with wanted elements
        int[][] template = new int[rowEnd - rowStart + 1][colEnd - colStart + 1];
        int[][] blackened = new int[imgArray.length][imgArray[0].length];

        int rowSpanMiddle = (rowEnd - rowStart) / 2;
        int colSpanMiddle = (colEnd - colStart) / 2;

        //create blackened Array (=result Array) and fill it with copies of imgArray & create and fill template with wanted values
        for (int row = 0, i = 0; row < imgArray.length; row++, i++) {
            for (int col = 0; col < imgArray[0].length; col++) {
                blackened[row][col] = imgArray[row][col];
                if (row >= rowStart && row <= rowEnd && col >= colStart && col <= colEnd) {
                    template[row - rowStart][col - colStart] = imgArray[row][col];
                }
            }
        }

        double ssd;
        //calculate SSD
        for (int row = 0; row < imgArray.length; row++) {
            for (int col = 0; col < imgArray[0].length; col++) {
                ssd = 0;
                for (int templateRow = 0; templateRow < template.length; templateRow++) {
                    //check if row of template is in boundaries. ignore if not
                    if (row - rowSpanMiddle + templateRow >= 0 && row - rowSpanMiddle + templateRow < imgArray.length) {
                        for (int templateCol = 0; templateCol < template[0].length; templateCol++) {
                            //check if row of template is in boundaries. if yes, calculate ssd. ignore if not
                            if (col - colSpanMiddle + templateCol >= 0 && col - colSpanMiddle + templateCol < imgArray[0].length) {
                                ssd += Math.pow(imgArray[row - rowSpanMiddle + templateRow][col - colSpanMiddle + templateCol] - template[templateRow][templateCol], 2);
                            }
                        }
                    }
                }

                //the two loops(above and below) could be realised in separate method

                //blacken area if ssd below threshold (match)
                if (ssd < threshold) {
                    for (int templateRow = 0; templateRow < template.length; templateRow++) {
                        //check if row of template is in boundaries. ignore if not
                        if (row - rowSpanMiddle + templateRow >= 0 && row - rowSpanMiddle + templateRow < imgArray.length) {
                            for (int templateCol = 0; templateCol < template[0].length; templateCol++) {
                                //check if row of template is in boundaries. if yes, blacken area. ignore if not
                                if (col - colSpanMiddle + templateCol >= 0 && col - colSpanMiddle + templateCol < imgArray[0].length) {
                                    blackened[row - rowSpanMiddle + templateRow][col - colSpanMiddle + templateCol] = 0;
                                }
                            }
                        }
                    }
                }

            }
        }


        return blackened;
    }

    public static void main(String[] args) {

        String fileName = "./src/page4.png";
        BufferedImage img = null;
        // try to open image file
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int[][] imgArray = convertImg2Array(img);

        //blacken the "g"
        int[][] resultImg = blackenSimilarRegions(imgArray, 148, 158, 321, 328, 1e5);

        //blacken the "while"
//        int[][] resultImg = blackenSimilarRegions(imgArray, 214, 230, 233, 270, 1e6);

        //binarize by comparing to a single black pixel region
        //int[][] resultImg = blackenSimilarRegions(imgArray, 150, 150, 95, 95, 220 * 220);

        drawImage(imgArray);
        if (resultImg != null) {
            drawImage(resultImg);
        }
    }
}
