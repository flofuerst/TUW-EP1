/*
    Aufgabe 2) Eindimensionale Arrays
*/
public class Aufgabe2 {

    private static void printReversedContent(int[] array1) {
        for (int i = array1.length - 1; i >= 0; i--) {
            if (i == 0) System.out.println(array1[i]); //Verhindert, dass nach letztem Element ein Komma ausgegeben wird
            else System.out.print(array1[i] + ",");
        }
    }

    private static void printFilteredContent(int[] array2) {
        for (int i = 0; i < array2.length; i++) { //Initialisierung des Arrays mit entsprechenden Werten
            array2[i] = 12 + 4 * i;
        }
        for (int i = 0; i < array2.length; i++) {
            if (array2[i] % 6 == 0) array2[i] = 0; //wenn Element durch 6 teilbar, dann wird es auf 0 gesetzt
            if (i == array2.length - 1) System.out.println(array2[i]); //letztes Leerzeichen wird verhindert
            else System.out.print(array2[i] + " ");
        }
    }

    private static void printNewModifiedArray(int[] array3) {
        int fiveCounter = 0;
        int alreadyFive = 0;
        for (int i : array3) { //mit Iterator-Form der for-Schleife (foreach) werden Vorkommnisse von Zahl 5 gezählt
            if (i == 5) fiveCounter++;
        }

        //in dieser Schleife wird array4 "zusammengebaut"
        int[] array4 = new int[array3.length + fiveCounter]; //neues Array wird mit größerer Länge initialisiert
        for (int i = 0; i < array3.length; i++) { //oder: i + alreadyFive < array4.length
            if (array3[i] == 5) {
                array4[i + alreadyFive] = array3[i];
                array4[i + alreadyFive + 1] = -1;
                alreadyFive++;
            } else array4[i + alreadyFive] = array3[i];
        }
        for (int i : array4) { //Elemente von Array werden ausgegeben
            System.out.print(i + " ");
        }
        System.out.println(); //Für Zeilenumbruch
    }

    private static void printDoubleReverseContent(int[] array5) {
        //Initialisierung
        for (int i = 0; i < array5.length; i++) {
            array5[i] = 1 + i;
        }

        //Realisierung mit while-Schleife
        int x = 0;
        System.out.print("while-Schleife: ");
        while (array5.length - 1 - x >= 0) {
            System.out.print(array5[array5.length - 1 - x] + " ");
            x++;
        }
        System.out.println(); //Zeilenumbruch

        //Realisierung mit for-Schleife
        System.out.print("for-Schleife: ");
        for (int i = array5.length - 1; i >= 0; i--) {
            System.out.print(array5[i] + " ");
        }
        System.out.println(); //Zeilenumbruch
    }

    private static void printCalculations(int[] array6) {
        int maxValue = array6[0], minValue = array6[0], sum = array6[0]; //1. Element definiert Startwerte
        for (int i = 1; i < array6.length; i++) {
            if (array6[i] < minValue) minValue = array6[i];
            if (array6[i] > maxValue) maxValue = array6[i];
            sum += array6[i];
        }
        int[] output = new int[]{minValue, sum / array6.length, maxValue};

        for (int x : array6) {
            System.out.print(x + " ");
        }
        System.out.println();
        for (int y : output) {
            System.out.print(y + " ");
        }
    }

    public static void main(String[] args) {
        // a)
        int[] arr1 = new int[]{2, 14, 5, 9, 3, 8, 4, 7, 6, 10}; //könnte auch in Methode initialisiert werden
        printReversedContent(arr1);

        // b)
        int[] arr2 = new int[20];
        printFilteredContent(arr2);

        // c)
        int[] arr3 = new int[]{5, 2, 3, 5, 5, 4, 6, 7, 8, 5};
        printNewModifiedArray(arr3);

        // d)
        int[] arr5 = new int[15];
        printDoubleReverseContent(arr5);

        // e)
        int[] arr6 = new int[]{54, 14, 18, 9, 5, 32, 41, 71, 1, 46, 76, 6, 45, 89, 18};
        printCalculations(arr6);
    }
}

