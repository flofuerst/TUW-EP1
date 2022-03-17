/*
    Aufgabe 4) Rekursion
*/
public class Aufgabe4 {


    private static boolean isStringPalindrome(String text) {

/*        Hat den Nachteil, dass die Methoden so lange (rekursiv) aufgerufen werden, bis text.length() <= 1.
          Dies passiert auch, wenn die 1. zu verglichenen Zeichen ungleich(false) sind. Trotzdem werden die restlichen
          Zeichen rekursiv verglichen.

        if (text.length() > 1) {
            return (text.charAt(0) == text.charAt(text.length() - 1)) && isStringPalindrome(text.substring(1, text.length() - 1));
        }
        return true;
*/

/*
        Diese Variante vergleicht jeweils vor dem nächsten rekursiven Aufruf, ob die Zeichen gleich sind.
        Wenn nicht, wird false returnt und die restlichen Zeichen werden so mit gar nicht mehr verglichen.
 */
        if (text.length() <= 1) return true;
        if (text.charAt(0) == text.charAt(text.length() - 1)) {
            return isStringPalindrome(text.substring(1, text.length() - 1));
        }
        return false; //else-Zweig kann hier entfallen
    }

/*
    Betrachtet immer das letzte Element und prüft ob dieses ein 'T' ist.
    Falls ja: T wird nach vorne geschrieben & Methode wird ohne dem letzten Zeichen rekursiv aufgerufen
    Falls nein: Methode wird bis auf das (bereits geprüfte) letzte Zeichen rekursiv aufgerufen und das letzte Zeichen
    wird hinten drangehängt
 */
    private static String shiftTs(String text) {
        if (text.length() <= 1) return text;
        if (text.charAt(text.length() - 1) == 'T') {
            return "T" + shiftTs(text.substring(0, text.length() - 1));
        } else {
            return shiftTs(text.substring(0, text.length() - 1)) + text.charAt(text.length() - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(isStringPalindrome("lagerregal"));
        System.out.println(isStringPalindrome("otto"));
        System.out.println(isStringPalindrome("rentner"));
        System.out.println(isStringPalindrome("abcba"));
        System.out.println(isStringPalindrome("test"));
        System.out.println(isStringPalindrome("teppichstaubsauger"));
        System.out.println();

        System.out.println(shiftTs(""));
        System.out.println(shiftTs("T"));
        System.out.println(shiftTs("BT"));
        System.out.println(shiftTs("TBT"));
        System.out.println(shiftTs("TBCTDFTTGHTTT"));
        System.out.println(shiftTs("TTHVVGWTTBSJTTT"));
        System.out.println(shiftTs("ZMNGFBTTTTTTTTT"));

        assert (isStringPalindrome("lagerregal"));
        assert (isStringPalindrome("otto"));
        assert (isStringPalindrome("rentner"));
        assert (isStringPalindrome("abcba"));
        assert (!isStringPalindrome("test"));
        assert (!isStringPalindrome("teppich"));

        assert (shiftTs("").equals(""));
        assert (shiftTs("T").equals("T"));
        assert (shiftTs("BT").equals("TB"));
        assert (shiftTs("TBT").equals("TTB"));
        assert (shiftTs("TBCTDFTTGHTTT").equals("TTTTTTTBCDFGH"));
        assert (shiftTs("TTHVVGWTTBSJTTT").equals("TTTTTTTHVVGWBSJ"));
        assert (shiftTs("ZMNGFBTTTTTTTTT").equals("TTTTTTTTTZMNGFB"));
    }
}
