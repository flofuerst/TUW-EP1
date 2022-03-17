/*
    Aufgabe 5) Muster mit Quadraten => Rekursiv vs. Iterativ
*/

import codedraw.CodeDraw;
import codedraw.Palette;

import java.awt.*;

public class Aufgabe5 {
//    private static int count = 0;
    private static void drawPatternRecursively(CodeDraw myDrawObj, int x, int y, int s, boolean c) {
        if (s >= 4) {
//            count++;
            //da c bei jedem Aufruf immer negiert wird, wechseln sich die Farben durch die If-Anweisung immer ab
            drawPatternRecursively(myDrawObj, x - s / 2, y - s / 2, s / 2, !c); //Diagonale nach links oben
            drawPatternRecursively(myDrawObj, x + s / 2, y + s / 2, s / 2, !c); //Diagonale nach rechts unten
            drawPatternRecursively(myDrawObj, x - s / 2, y + s / 2, s / 2, !c); //Diagonale nach links unten
            drawPatternRecursively(myDrawObj, x + s / 2, y - s / 2, s / 2, !c); //Diagonale nach rechts oben
            if (c) myDrawObj.setColor(Palette.ORANGE);
            else myDrawObj.setColor(Palette.BLUE);
            myDrawObj.fillSquare(x - s / 2, y - s / 2, s);
        }
    }

    private static void drawPatternIteratively(CodeDraw myDrawObj, int width) {
        boolean color = false;
        for (int s = 4; s <= width; s *= 2) { //Quadrate vergrößern sich durch diese Schleife
            for (int y = s; y < width; y += 2 * s) { //y-Koordinate wird vergrößert (nachdem alle Quadrate spaltenweise gezeichnet wurden, geht diese Schleife um eine Zeile "hinunter" und dann werden die Quadrate durch die untere Methode wieder jeweils spaltenweise gezeichnet)
                for (int x = s; x < width; x += 2 * s) { //x-Koordinate wird vergrößert (Quadrate werden splatenweise (horizontal) gezeichnet)
                    if (color) myDrawObj.setColor(Palette.BLUE); //Farbenänderung
                    else myDrawObj.setColor(Palette.ORANGE); //Farbenänderung
                    myDrawObj.fillSquare(x - s / 2, y - s / 2, s); //Quadrat wird gezeichnet
                }
            }
            color = !color; //Farbe wird getoggled
        }
    }

    public static void main(String[] args) {
        CodeDraw canvas = new CodeDraw(512, 512);
        drawPatternRecursively(canvas, 256, 256, 256, true);
//        drawPatternIteratively(canvas, 512);
//        System.out.println(count);
        canvas.show();
    }
}

/*
    Zusatzfragen:
    1) Bei einer Quadratseitenlänge von 4 wird die Methode 5461 mal aufgerufen.
       - Man kann es rechnerisch herausfinden: Anzahl der Ebenen = 7, da aber in letzter Ebene keine Aufrufe
         stattfinden: n=6. Jede Ebene hat 4^n Aufrufe --> 4^6+4^5+4^4+4^3+4^2+4+1 = 5461 Aufrufe.
       - oder mit einer globalen Variable "count", welche jedes mal um 1 erhöhrt wird, wenn die Methode aufgerufen wird.

    2) In der letzten Rekursionsebene, also die kleinsten Quadrate, werden 4^6 = 4096 mal gezeichnet.

    3) Die rekursive Methode muss ohne den Aufruf mit der rechts oberen Diagonalrichtung, also ohne den Aufruf
       mit +x und -y Offset, implementiert werden.
 */


