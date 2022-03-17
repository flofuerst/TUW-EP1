/*
    Aufgabe 3) Rekursion
*/
public class Aufgabe3 {

    private static void printNumbersAscending(int start, int end, int divider) {
        if (start <= end) {
            if (start % divider == 0) System.out.println(start);
            printNumbersAscending(start + 1, end, divider);
        }
    }

    private static void printNumbersDescending(int start, int end, int divider) {
        if (start <= end) {
            if (end % divider != 0) System.out.println(end);
            printNumbersDescending(start, end - 1, divider);
        }
    }

    private static int sumSquaredDigits(int number) {
        if (number > 0) return (int) (Math.pow(number % 10, 2) + sumSquaredDigits(number / 10)); //mit number % 10 bekommt man die letzte Ziffer(Einerstelle) und
        else return 0;                                                                                   //mit /10 kann man die Zahl nach rechts shiften, sodass die letzte Ziffer wegfällt
    }

    private static String removeMultipleChars(String text) {
        if (text.length() <= 1) return text; //wenn Länge von text <= 1, dann soll nur text ausgegeben werden (ist schließlich nur ein einzelnes Zeichen, oder nichts)
        if (text.charAt(0) == text.charAt(1)) { //wenn die 1. beiden gleich, dann wird Methode erneut aufgerufen und dabei wird das 1. Zeichen "weggeschnitten"
            return removeMultipleChars(text.substring(1));
        }
        else   {
            return text.charAt(0) + removeMultipleChars(text.substring(1)); //wenn nicht gleich, dann bleibt 1. Zeichen unverändert und Methode wird auf restlichen Zeichen angewandt
        }
    }

    public static void main(String[] args) {
        printNumbersAscending(10, 20, 2);
        System.out.println();
        printNumbersDescending(5, 15, 3);
        System.out.println();

        System.out.println(sumSquaredDigits(1));
        System.out.println(sumSquaredDigits(102));
        System.out.println(sumSquaredDigits(1234));
        System.out.println(sumSquaredDigits(10000));
        System.out.println(sumSquaredDigits(93842));
        System.out.println(sumSquaredDigits(875943789));
        assert (sumSquaredDigits(1) == 1);
        assert (sumSquaredDigits(102) == 5);
        assert (sumSquaredDigits(1234) == 30);
        assert (sumSquaredDigits(10000) == 1);
        assert (sumSquaredDigits(93842) == 174);
        assert (sumSquaredDigits(875943789) == 438);
        System.out.println();

        System.out.println(removeMultipleChars("AA"));
        System.out.println(removeMultipleChars("ABCCDE"));
        System.out.println(removeMultipleChars("SEENDEEN"));
        System.out.println(removeMultipleChars("ABCFFFE 14448"));
        assert (removeMultipleChars("AA").equals("A"));
        assert (removeMultipleChars("ABCCDE").equals("ABCDE"));
        assert (removeMultipleChars("SEENDEEN").equals("SENDEN"));
        assert (removeMultipleChars("ABCFFFE 14448").equals("ABCFE 148"));
    }
}

