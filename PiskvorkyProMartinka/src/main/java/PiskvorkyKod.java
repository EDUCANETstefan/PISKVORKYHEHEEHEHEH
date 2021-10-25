import java.util.Arrays;
import java.util.Scanner;

public class PiskvorkyKod {

    // Vím, že pole nemá být omezené, ale netuším, jak udělat to, aby omezené nebylo  :D
    public String[][] pole = new String[15][15];

    public int sloupec, radek;

    public void kod() {
        System.out.println("-----VITEJ V PISKVORKACH-----\n-----TATO HRA MI DALA ZABRAT, TAK SI JI LASKAVE UZIJ-----\n\n\n");

        for (String[] strings : pole) {
            Arrays.fill(strings, "- ");
        }

        boolean hrac = true;
        while (!vyhral()) {
            renderPole();
            vstup(hrac);
            hrac = !hrac;
        }

        renderPole();
    }

    // Rendrování pole pro hru
    public void renderPole() {

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                System.out.print(pole[y][x]);
            }
            System.out.print("\n");
        }
    }

    // Hráči zadávají vstup
    public void vstup(boolean uzivatel) {
        Scanner sc = new Scanner(System.in);
        String hrac1, hrac2;
        hrac1 = "x";
        hrac2 = "o";

        if (uzivatel) {
            System.out.println("HRAJE HRAC: " + hrac1);
        } else {
            System.out.println("HRAJE HRAC: " + hrac2);
        }

        do {
            System.out.println("LASKAVE ZADEJ SOURADNICI PRO RADEK! 1-15");

            radek = sc.nextInt();
            radek = radek - 1;

            System.out.println("LASKAVE ZADEJ SOURADNICI PRO SLOUPEC! 1-15\"");

            sloupec = sc.nextInt();
            sloupec = sloupec - 1;

            if (!pole[radek][sloupec].equals("- ")) {
                System.out.println("VYBER SI JINE POLE!");
            }

        } while (!pole[radek][sloupec].equals("- "));

        if (uzivatel) {
            pole[radek][sloupec] = "x ";
        } else {
            pole[radek][sloupec] = "o ";
        }

    }

    // Kontrola, jestli již jeden z hráčů nevyhrál
    public boolean vyhral() {
        if (overeniSloupce()) {
            return true;
        } else if (overeniRadku()) {
            return true;
        } else if (overeniDiagonala1()) {
            return true;
        } else return overeniDiagonala2();
    }

    // Ověření, jestli hŕač nevyhrál ve sloupci
    public boolean overeniSloupce() {
        String x = pole[radek][sloupec];
        int a;
        a = 1;

        for (int i = 1; i < 5; i++) {
            if (radek + i >= pole.length) {
                break;
            }
            if (pole[radek][sloupec].equals(pole[radek + i][sloupec])) {
                a = a + 1;
            }
        }

        for (int j = 1; j < 5; j++) {
            if (radek - j < 0) {
                break;
            }
            if (pole[radek][sloupec].equals(pole[radek - j][sloupec])) {
                a = a + 1;
            }
        }

        if (a >= 5) {
            System.out.println("VYHRAL HRAC: " + x);
        }

        System.out.println(pole[radek][sloupec]);
        return false;
    }

    // Ověření, jestli hŕač nevyhrál v řádku
    public boolean overeniRadku() {
        String x = pole[radek][sloupec];
        int a = 1;

        for (int i = 1; i < 5; i++) {
            if (sloupec + i >= pole.length) {
                break;
            }
            if (pole[radek][sloupec].equals(pole[radek][sloupec + i])) {
                a++;
            }
        }

        for (int j = 1; j < 5; j++) {
            if (sloupec - j < 0) {
                break;
            }
            if (pole[radek][sloupec].equals(pole[radek][sloupec - j])) {
                a++;
            }
        }

        if (a >= 5) {
            System.out.println("VYHRAL HRAC: " + x);
        }

        System.out.println(pole[radek][sloupec]);
        return false;
    }

    // Ověření, jestli hŕač nevyhrál v diagonále - 1
    public boolean overeniDiagonala1() {

        String x = pole[radek][sloupec];

        int a, b;
        a = 1;
        b = 1;


        for (int i = 1; i < 5; i++) {
            if (sloupec + i >= pole.length) {
                break;
            }
            if (radek + i >= pole.length) {
                break;
            }

            if (pole[radek][sloupec].equals(pole[radek + i][sloupec + i])) {
                a++;
                b++;
            }
        }

        for (int j = 1; j < 5; j++) {
            if (sloupec - j < 0) {
                break;
            }
            if (radek - j < 0) {
                break;
            }
            if (pole[radek][sloupec].equals(pole[radek - j][sloupec - j])) {
                a = a + 1;
                b = b + 1;
            }
        }

        if (a >= 5 && b >= 5) {
            System.out.println("VYHRAL HRAC: " + x);
        }

        System.out.println(pole[radek][sloupec]);

        return false;
    }

    // Ověření, jestli hŕač nevyhrál v diagonále - 2
    public boolean overeniDiagonala2() {

        String x = pole[radek][sloupec];

        int a = 1;

        for (int i = 1; i < 5; i++) {

            if (sloupec + i >= pole.length) {
                break;
            }
            if (radek + i < 0) {
                break;
            }

            if (pole[radek][sloupec].equals(pole[radek + i][sloupec + i])) {
                a++;
            }
        }

        for (int j = 1; j < 5; j++) {

            if (sloupec - j < 0) {
                break;
            }
            if (radek + j >= pole.length) {
                break;
            }

            if (pole[radek][sloupec].equals(pole[radek + j][sloupec + j])) {
                a++;
            }
        }

        return false;
    }
}
