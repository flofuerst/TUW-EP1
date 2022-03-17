/*
    Aufgabe 2) Zweidimensionale Arrays - Sortieren und Filtern
*/
public class Aufgabe2 {

    private static double[][] genMeanFilter(int rows, int cols) {
        //check if invalid input
        if (rows % 2 == 0 || cols % 2 == 0 || rows < 1 || cols < 1) return null;

        double[][] array1 = new double[rows][cols];

        //calculate mean
        double mean = 1d / (rows * cols);

        //fill array elements with mean
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                array1[i][j] = mean;
            }
        }
        return array1;
    }

    private static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        //create new output array. workArray[0].length can be assumed because of precondition
        double[][] outputArray = new double[workArray.length][workArray[0].length];

        //calculate middle row and column of filterArray
        int middleRow = filterArray.length / 2;
        int middleCol = filterArray[0].length / 2;

        double sum;

        //iterate through elements of workArray
        for (int row = 0; row < outputArray.length; row++) {
            for (int col = 0; col < outputArray[0].length; col++) {
                sum = 0;

                //iterate through row of filter to calculate position
                for (int filterRow = 0; filterRow < filterArray.length; filterRow++) {
                    //check if row of filter is in boundaries
                    if (row - middleRow + filterRow >= 0 && row - middleRow + filterRow < outputArray.length) {
                        //iterate through column of filter to calculate position
                        for (int filterCol = 0; filterCol < filterArray[0].length; filterCol++) {
                            //check if column of filter is in boundaries as well. If yes, add calculation to sum
                            if (col - middleCol + filterCol >= 0 && col - middleCol + filterCol < outputArray[0].length) {
                                sum += workArray[row - middleRow + filterRow][col - middleCol + filterCol] *
                                        filterArray[filterRow][filterCol];
                            }
                        }
                    }
                }
                outputArray[row][col] = sum;
            }
        }
        return outputArray;
    }

    private static void print(double[][] workArray) {
        if (workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray[y].length; x++) {
                    System.out.printf("%.2f", workArray[y][x]);
                    System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] myResultArray;

        double[][] myFilter1 = genMeanFilter(3, 3);
        print(myFilter1);
        double[][] myFilter2 = genMeanFilter(1, 5);
        print(myFilter2);

        assert (genMeanFilter(2, 2) == null);
        assert (genMeanFilter(0, -1) == null);

        double[][] myArray1 = {{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}, {5, 5, 5, 5}};
        print(myArray1);

        myResultArray = applyFilter(myArray1, myFilter1);
        print(myResultArray);
        myResultArray = applyFilter(myArray1, myFilter2);
        print(myResultArray);

        //Beispiel aus Aufgabenblatt
        double[][] myArray3 = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};
        double[][] myFilter3 = {{1, 0, 0}, {1, 2, 0}, {0, 0, 3}};
        print(myArray3);
        myResultArray = applyFilter(myArray3, myFilter3);
        print(myResultArray);

        double[][] myArray4 = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
        print(myArray4);

        double[][] myFilter4 = {{1, 0, 0}};
        myResultArray = applyFilter(myArray4, myFilter4);
        print(myResultArray);
    }


}