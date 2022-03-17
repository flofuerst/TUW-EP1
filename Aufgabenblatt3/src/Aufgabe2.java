/*
    Aufgabe 2) Überladen von Methoden
*/
public class Aufgabe2 {

    private static void addSequence(String text, char symbol) {
        int stringLength = text.length();
        String newString = "";
        if (text.length() <= 1) newString = text; //wenn Leerstring oder nur 1 Zeichen, dann gleiche Ausgabe
        else {
            for (int i = 0; i < stringLength; i++) {
                newString += text.substring(i, i + 1); //Einzelne Zeichen werden bei jedem Durchlauf hinzugefügt
                if (i < stringLength - 1) { //bei letztem Durchlauf soll am Ende kein symbol drangehängt werden
                    for (int j = 0; j < 3; j++) { //Symbol wird 3 mal drangehängt
                        newString += symbol;
                    }
                }
            }
        }
        System.out.println(newString); //Ausgabe von aufgebauten String
    }

    private static void addSequence(int number, char symbol) {
        addSequence(Integer.toString(number), symbol); //int number wird bei der Übergabe in einen String umgewandelt
    }

    private static void addSequence(String text, String symbols) {
//        if (symbols.length() <= 1) addSequence(text, symbols); //wenn Länge von string symbols <= 1, dann wird der string symbols der Methode gleich als char übergeben
//        else {
            for (int i = 0; i < symbols.length(); i++) { //wenn nicht, dann wird jeder char von dem string der Reihe nach der Methode übergeben
                addSequence(text, symbols.charAt(i));
            }
//        }
    }

    private static void addSequence(String text) {
        addSequence(text, '+');
    }

    public static void main(String[] args) {
        System.out.println();
        String text0 = "";
        String text1 = "TU";
        String text2 = "Hello!";
        String text3 = "-EP1-";
        String text4 = "JAVA";

        addSequence(text0, '&');
        addSequence(text1, '#');
        addSequence(text2, '-');
        addSequence(text3, '$');
        addSequence(text4, '!');
        System.out.println();

        addSequence(1, '%');
        addSequence(42, '*');
        addSequence(163, ':');
        addSequence(2048, '#');
        addSequence(42815, '(');
        System.out.println();

        addSequence(text1, "(O)");
        addSequence(text2, "*#=_");
        System.out.println();

        addSequence(text0);
        addSequence(text1);
        addSequence(text2);
    }
}
