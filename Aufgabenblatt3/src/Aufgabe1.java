/*
    Aufgabe 1) Code-Analyse, Coding-Style und Methoden
*/
public class Aufgabe1 {

    //*************************************************************************************************************
    // FOLGENDER KOMMENTIERTER CODE DIENT ALS ANGABE - BITTE NICHT ÄNDERN!
    //*************************************************************************************************************
    /*
    private static String f0(String s) {
        int n = f1(s);String ss = s;
        for (int i = 0; f2(i, n/2); i=f3(i,1)) {
            for (int j = 0; f2(j, n - i - 1); j = f3(j, 1)) {
                if (f4(ss.charAt(j), ss.charAt(j+1))) ss = f5(ss, 0, j) + ss.charAt(j + 1) + "" + ss.charAt(j) + f5(ss, j + 2, ss.length());}
            for (int j = n - i - 2; f2(i,j); j = f3(j, -1)) {
                if (f4(ss.charAt(j-1), ss.charAt(j))) {
                    ss = f5(ss, 0, j-1) + ss.charAt(j) + "" + ss.charAt(j-1) + f5(ss, j + 1, ss.length());
                }
            }
        }
        return ss;
    }

    private static int f1(String s) {
        return s.length();
    }

    private static boolean f2(int i1, int i2) { return i1 < i2; }

    private static int f3(int n1, int n2) {
        return n1 + n2;
    }

    private static boolean f4(char c1, char c2) {
        return c1 > c2;
    }

    private static String f5(String s, int n1, int n2) {
        return s.substring(n1, n2);
    }
    */
    //*************************************************************************************************************

    //*************************************************************************************************************
    //TODO: a) Funktionsweise Code - Beschreiben Sie in ein bis zwei Sätzen, was der gegebene Code macht.
    /*
               Der Code vergleicht die chars in dem String "s" und sortiert diese aufsteigend (nach dem Alphabet).
               Die 2. for-Schleife beginnt von vorne und die 3. for-Schleife beginnt anschließend von hinten
               zu sortieren. Die einzelnen Methoden werden in der Angabe selbst kurz beschrieben.

               Beschreibung der einzelnen Methoden:
               f0: diese Methode ist für die gesamte alphabetische Sortierung zuständig
               f1: gibt die Länge von dem übergebenen String als integer zurück
               f2: vergleicht zwei integer und gibt einen boolean zurück
               f3: addiert zwei integer und gibt die Summe zurück
               f4: vergleicht die ASCII-Werte der beiden übergebenen chars und gibt einen boolean zurück
               f5: gibt einen substring zurück. Untergrenze: n1, Obergrenze (exklusiv): n2
     */


    //*************************************************************************************************************
    //TODO: b) Code-Adaptierung - Formatieren Sie den gegebenen Code und verwenden Sie sinnvolle Methoden- und Variablennamen.
/*
    private static String sortString(String unsortedString) {
        int stringLength = getLength(unsortedString);
        String sortedString = unsortedString;
        for (int i = 0; compareInt(i, stringLength / 2); i = sum(i, 1)) {
            for (int j = 0; compareInt(j, stringLength - i - 1); j = sum(j, 1)) {
                if (compareChar(sortedString.charAt(j), sortedString.charAt(j + 1)))
                    sortedString = splitString(sortedString, 0, j) + sortedString.charAt(j + 1) + "" + sortedString.charAt(j) + splitString(sortedString, j + 2, sortedString.length());
            }
            for (int j = stringLength - i - 2; compareInt(i, j); j = sum(j, -1)) {
                if (compareChar(sortedString.charAt(j - 1), sortedString.charAt(j))) {
                    sortedString = splitString(sortedString, 0, j - 1) + sortedString.charAt(j) + "" + sortedString.charAt(j - 1) + splitString(sortedString, j + 1, sortedString.length());
                }
            }
        }
        return sortedString;
    }

    private static int getLength(String s) {
        return s.length();
    }

    private static boolean compareInt(int i1, int i2) {
        return i1 < i2;
    }

    private static int sum(int n1, int n2) {
        return n1 + n2;
    }

    private static boolean compareChar(char c1, char c2) {
        return c1 > c2;
    }

    private static String splitString(String s, int n1, int n2) {
        return s.substring(n1, n2);
    }
*/

    //*************************************************************************************************************
    //TODO: c) Vervollständigen Sie hier die Methode, die die gleiche Funktionalität wie f0 hat.

    private static String sortString(String unsortedString) {
        int stringLength = unsortedString.length();
        String sortedString = unsortedString;
        for (int i = 0; i < stringLength / 2; i++) {
            for (int j = 0; j < stringLength - i - 1; j++) {
                if (sortedString.charAt(j) > sortedString.charAt(j + 1))
                    sortedString = sortedString.substring(0, j) + sortedString.charAt(j + 1) + sortedString.charAt(j) + sortedString.substring(j + 2); //sortedString.length() als endIndex kann weggelassen werden
            }
            for (int j = stringLength - i - 2; i < j; j--) {
                if (sortedString.charAt(j - 1) > sortedString.charAt(j)) {
                    sortedString = sortedString.substring(0, j - 1) + sortedString.charAt(j) + sortedString.charAt(j - 1) + sortedString.substring(j + 1); //Leerstring kann auch weggelassen werden
                }
            }
        }
        return sortedString;
    }

    //*************************************************************************************************************

    public static void main(String args[]) {

        System.out.println(sortString("ab"));
        System.out.println(sortString("ba"));
        System.out.println(sortString("aa"));
        System.out.println(sortString("cba"));
        System.out.println(sortString("abababab"));
        System.out.println(sortString("abcfghed"));
        System.out.println(sortString("abnasnasab"));
        System.out.println(sortString("najskaghkkjsfvjhbavbdfsan"));
        System.out.println(sortString("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv"));

        assert (sortString("ab").equals("ab"));
        assert (sortString("ba").equals("ab"));
        assert (sortString("aa").equals("aa"));
        assert (sortString("cba").equals("abc"));
        assert (sortString("abababab").equals("aaaabbbb"));
        assert (sortString("abcfghed").equals("abcdefgh"));
        assert (sortString("abnasnasab").equals("aaaabbnnss"));
        assert (sortString("najskaghkkjsfvjhbavbdfsan").equals("aaaabbdffghhjjjkkknnsssvv"));
        assert (sortString("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv").equals("aaaaabbbbbbbbbdddddffggggjjjjjjjjkkkksssvvvv"));

    }
}


