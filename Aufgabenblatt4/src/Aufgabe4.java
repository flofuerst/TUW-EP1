/*
    Aufgabe 4) Rekursion mit eindimensionalen Arrays
*/

import java.util.Arrays;

public class Aufgabe4 {

    private static int getHighestTripleSum(int[] workArray, int start, int end) {
        if (end - start + 1 < 3) return 0; //checks if less than 3 elements in range
        int highestSum = workArray[start] + workArray[start + 1] + workArray[start + 2];
        int tempSum = getHighestTripleSum(workArray, start + 1, end);
        if (tempSum > highestSum) {
            //highestSum = tempSum;
            return tempSum;
        } else {
            return highestSum;
        }
    }

    private static int getHighestDifferenceWithLastValue(int[] workArray, int index) {
        if (index == workArray.length - 1) return 0; //checks if used element is last element
        int highestDifference = workArray[index] - workArray[workArray.length - 1];
        int tempDifference = getHighestDifferenceWithLastValue(workArray, index + 1);
        if (tempDifference > highestDifference) {
            return tempDifference;
        } else {
            return highestDifference;
        }
    }

    private static int[] replaceAllMoreDigitValues(int[] workArray, int index) {
//        works, but not good implementation:
//        int[] workArrayCopy = workArray.clone();
//        if (workArrayCopy[index] == workArrayCopy[workArray.length - 1]) {
//            if (workArrayCopy[index] / 10 != 0) workArrayCopy[index] = -1; //Math.abs(workArrayCopy[index]) > 9
//            return workArrayCopy;
//        } else {
//            if (workArrayCopy[index] / 10 != 0) workArrayCopy[index] = -1;
//            return replaceAllMoreDigitValues(workArrayCopy, index + 1);
//        }

//      better:
//      corrects array element at index "index" and calls recursive method --> start over again, till:
//      index is out of array index. then: returns workArray
        if (index > workArray.length - 1) return workArray;
        int[] workArrayCopy = workArray.clone();
        if (workArrayCopy[index] / 10 != 0) workArrayCopy[index] = -1; //Math.abs(workArrayCopy[index]) > 9
        return replaceAllMoreDigitValues(workArrayCopy, index + 1);
    }

    private static boolean containsValue(int[] workArray, int value) {
//      if 1 element in array left --> check if this equals value --> return result
        if(workArray.length==1) return workArray[0] == value;

        boolean firstHalf = containsValue(Arrays.copyOfRange(workArray, 0, workArray.length/2), value);
        boolean secondHalf = containsValue(Arrays.copyOfRange(workArray,  workArray.length/2, workArray.length), value);

        return firstHalf || secondHalf;
    }

    public static void main(String[] args) {
        int[] array1 = {2, 13, 3, 16, 12, 4, 9, 14};
        System.out.println(getHighestTripleSum(array1, 0, array1.length - 1));
        System.out.println(getHighestTripleSum(array1, 4, array1.length - 1));
        System.out.println(getHighestTripleSum(array1, 2, 5));
        System.out.println(getHighestTripleSum(array1, 0, 1));
        System.out.println();

        int[] array2 = {33, 23, 53, 29, 12, 34, 41, 44, 28, 13};
        System.out.println(getHighestDifferenceWithLastValue(array2, 1));
        System.out.println(getHighestDifferenceWithLastValue(array2, 4));
        System.out.println(getHighestDifferenceWithLastValue(array2, 6));
        System.out.println(getHighestDifferenceWithLastValue(array2, 8));
        System.out.println();

        int[] array3 = {12, 6, 21, 2, 5, 15, 26, 3, 10, 28};
        System.out.println(Arrays.toString(array3));
        System.out.println(Arrays.toString(replaceAllMoreDigitValues(array3, 0)));
        System.out.println(Arrays.toString(replaceAllMoreDigitValues(array3, 3)));
        System.out.println(Arrays.toString(replaceAllMoreDigitValues(array3, 6)));
        System.out.println(Arrays.toString(replaceAllMoreDigitValues(array3, 8)));
        System.out.println();

        int[] array4 = {3, 9, 17, 11, -7, 8, 0, 9, 24, -3, 17, 4};
        System.out.println(containsValue(array4, 11));
        System.out.println(containsValue(array4, 2));
        System.out.println(containsValue(array4, -7));
        System.out.println(containsValue(array4, 0));
        System.out.println(containsValue(array4, 9));
        System.out.println(containsValue(array4, 16));


        assert (getHighestTripleSum(array1, 0, array1.length - 1) == 32);
        assert (getHighestTripleSum(array1, 4, array1.length - 1) == 27);
        assert (getHighestTripleSum(array1, 2, 5) == 32);
        assert (getHighestTripleSum(array1, 0, 1) == 0);

        assert (getHighestDifferenceWithLastValue(array2, 1) == 40);
        assert (getHighestDifferenceWithLastValue(array2, 4) == 31);
        assert (getHighestDifferenceWithLastValue(array2, 6) == 31);
        assert (getHighestDifferenceWithLastValue(array2, 8) == 15);

        assert (Arrays.equals(replaceAllMoreDigitValues(array3, 0), new int[]{-1, 6, -1, 2, 5, -1, -1, 3, -1, -1}));
        assert (Arrays.equals(replaceAllMoreDigitValues(array3, 3), new int[]{12, 6, 21, 2, 5, -1, -1, 3, -1, -1}));
        assert (Arrays.equals(replaceAllMoreDigitValues(array3, 6), new int[]{12, 6, 21, 2, 5, 15, -1, 3, -1, -1}));
        assert (Arrays.equals(replaceAllMoreDigitValues(array3, 8), new int[]{12, 6, 21, 2, 5, 15, 26, 3, -1, -1}));

        assert (containsValue(array4, 11));
        assert (!containsValue(array4, 2));
        assert (containsValue(array4, -7));
        assert (containsValue(array4, 0));
        assert (containsValue(array4, 9));
        assert (!containsValue(array4, 16));
    }
}

