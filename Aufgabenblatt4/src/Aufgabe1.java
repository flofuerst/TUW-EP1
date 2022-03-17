/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {

    private static void fillArray(int[] filledArray) {
        int number = 6;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = number;
            number += 6;
        }
    }

    private static void printContentFilteredArray(int[] workArray) {
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if (copiedArray[i] % 4 == 0) {
                copiedArray[i] = -1;
            }
        }
        printArray(copiedArray);
    }

    private static void fillArrayWithNewContent(int[] workArray) {
        int[] helpArray = new int[10];
        int number = 6;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = number;
            number += 6;
        }
        workArray = helpArray;
        printArray(workArray);
    }

    private static void printArray(int[] workArray) {
        for (int i = 0; i < workArray.length; i++) { //Zählvariable hat nach jedem Durchlauf (mit i--) heruntergezählt
            System.out.print(workArray[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] filledArray = new int[10];
        fillArray(filledArray);
        printArray(filledArray);

        printContentFilteredArray(filledArray);
        printArray(filledArray);

        filledArray[0] = 777;
        printArray(filledArray);

        fillArrayWithNewContent(filledArray);
        printArray(filledArray);
    }

    //**************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ***
    //**************************************************************************
    // Zu a): Die Zählvariable hat mit jedem Durchlauf heruntergezählt. Der erste Durchlauf ist, mit Index 0, möglich,
    // jedoch bekommt die Zählvariable danach schon den Wert -1. -1 ist kein Index im Bereich des Arrays (Start bei 0)
    //
    // Zu b): Beim Methodeaufruf wird die Referenz auf das Array übergeben. Anhand dieser Referenz wird dann in der
    // Methode selbst das Array befüllt, das Array selbst befindet sich aber nicht in der Methode.
    // Frage 1:
    //
    // Zu c): Durch "int[] copiedArray = workArray" wird der Array-Variable "copiedArray" die gleiche Referenz wie bei
    // "workArray" zugewiesen. (beide zeigen dadurch auf denselben Speicherbereich)
    // Durch die Veränderung der Elemente in "copiedArray" wird also das eigentliche Array
    // verändert (da "copiedArray" und "workArray" auf das gleiche Array referenzieren). Hierbei findet also keine
    // Kopie des Arrays selbst statt.
    // Zu d): Durch die Methode wird ein neues Array erzeugt. Diesem Array werden auch verschiedene Werte zugewiesen.
    // Durch "workArray = helpArray" bekommt die Array-Variable "workArray" dieselbe Referenz auf das neue Array-Objekt
    // wie die Array-Variable "helpArray". Der alte Arrayinhalt (vom Array "filledArray") wird dadurch nicht verändert.
    //
    // Zusatzfrage 1: Der Typ des Indexausdrucks muss int, short, byte oder char sein.
    //
    // Zusatzfrage 2: Array-Elemente werden grundsätzlich automatisch initialisiert (die Art der Initialisierung hängt
    // vom Datentyp ab). Jedoch kann man Array-Elemente auch selbst initialisiern
    // z.B.: int[] arr = new int[]{1, 2, 3, 4, 5};
    //
    // Zusatzfrage 3: Die Größe eines Array kann nach Zuweisung der Größe nicht mehr verändert werden.
    // Man kann aber (auf verschiedene Wege) das Array kopieren (und die Größe verändern) und die alten Werte evt.
    // wieder zuweisen.
    //
    // Zusatzfrage 4: Neues Array erstellen --> selbe Länge wie altes Array zuweißen --> mittels for-Schleife dem
    // neuen Array die Werte aus dem alten Array zuweißen. | Oder mit System.arraycopy
    //
    // Zusatzfrage 5: Ja, die Indexzählung beginnt immer bei 0.
    //
    // Zusatzfrage 6: Nein es ist nicht sinnvoll. Der Operator "==" vergleicht nur die Referenzen der Arrays, jedoch
    // nicht den Inhalt. (Arrays.equals(arr1, arr2) vergleicht den Inhalt zweier Arrays. --> auch mit for-Schleife
    // möglich, wenn man alle einzelnen Elemente miteinander vergleicht)
}
