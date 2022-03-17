/*
    Aufgabe 3) Eindimensionale Arrays und Methoden
*/

import java.util.Arrays;

public class Aufgabe3 {

    private static int[] genRandomArray(int length, int maxNumber) {
        int[] array1 = new int[length];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (int) (Math.random() * maxNumber); //Math.random() ist double im Intervall [0;1[
        }
        return array1;
    }

    private static void replaceValuesLowerAverage(int[] workArray) {
        int sum = 0, average = 0;
        for (int i : workArray) { //calculating sum of array elements
            sum += i;
        }
        average = sum / workArray.length; //calculating average
        for (int x = 0; x < workArray.length; x++) { //replacing array elements below average
            if (workArray[x] < average) workArray[x] = average;
        }
    }

    private static int[] combineArrays(int[] workArray1, int[] workArray2) {
        int[] sumArray = new int[workArray1.length + workArray2.length];

        if (workArray1.length > workArray2.length) {
            int startIndexArr2 = 0;
            for (int i = 0; i < workArray1.length; i++) { //fill with workArray1
                sumArray[i] = workArray1[i];
                startIndexArr2 = i + 1; //safe end-index for second loop
            }
            for (int x = 0; startIndexArr2 < sumArray.length; x++, startIndexArr2++) { //fill with workArray2
                sumArray[startIndexArr2] = workArray2[x];
            }
        } else if (workArray1.length < workArray2.length) {
            int startIndexArr1 = 0;
            for (int i = 0; i < workArray2.length; i++) { //fill with workArray2
                sumArray[i] = workArray2[i];
                startIndexArr1 = i + 1; //safe end-index for second loop
            }
            for (int x = 0; startIndexArr1 < sumArray.length; x++, startIndexArr1++) { //fill with workArray1
                sumArray[startIndexArr1] = workArray1[x];
            }
        } else {
            for (int i = 0, x = 0; i < sumArray.length; i++, x++) { //alternated fill
                sumArray[i] = workArray1[x];
                i++; //index increases by 1 after beeing filled
                sumArray[i] = workArray2[x];
            }
        }
        return sumArray;
    }

    public static void main(String[] args) {
        int[] array1 = genRandomArray(20, 50);
        System.out.println(Arrays.toString(array1));
        System.out.println();

        int[] array2 = new int[]{17, 3, 15, 21, 34, 12, 5, 8, 25, 30};
        replaceValuesLowerAverage(array2);
        System.out.println(Arrays.toString(array2));
        assert (Arrays.equals(array2, new int[]{17, 17, 17, 21, 34, 17, 17, 17, 25, 30}));

        int[] array3 = new int[]{21, 14, 17, 12};
        replaceValuesLowerAverage(array3);
        System.out.println(Arrays.toString(array3));
        assert (Arrays.equals(array3, new int[]{21, 16, 17, 16}));

        int[] array4 = new int[]{3, 4, 6, 7};
        replaceValuesLowerAverage(array4);
        System.out.println(Arrays.toString(array4));
        assert (Arrays.equals(array4, new int[]{5, 5, 6, 7}));

        int[] array5 = new int[]{7, 7};
        replaceValuesLowerAverage(array5);
        System.out.println(Arrays.toString(array5));
        assert (Arrays.equals(array5, new int[]{7, 7}));

        int[] array6 = new int[]{3, 2, 1};
        replaceValuesLowerAverage(array6);
        System.out.println(Arrays.toString(array6));
        assert (Arrays.equals(array6, new int[]{3, 2, 2}));
        System.out.println();

        int[] array7 = combineArrays(array2, array3);
        System.out.println(Arrays.toString(array7));
        assert (Arrays.equals(array7, new int[]{17, 17, 17, 21, 34, 17, 17, 17, 25, 30, 21, 16, 17, 16}));

        int[] array8 = combineArrays(array6, array4);
        System.out.println(Arrays.toString(array8));
        assert (Arrays.equals(array8, new int[]{5, 5, 6, 7, 3, 2, 2}));

        int[] array9 = combineArrays(array3, array4);
        System.out.println(Arrays.toString(array9));
        assert (Arrays.equals(array9, new int[]{21, 5, 16, 5, 17, 6, 16, 7}));

        int[] array10 = combineArrays(array6, new int[]{});
        System.out.println(Arrays.toString(array10));
        assert (Arrays.equals(array10, new int[]{3, 2, 2}));
    }
}
