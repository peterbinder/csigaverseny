package ora;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListaModositasa {

    public static void main(String[] args) {
        List<Integer> szamok = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            szamok.add(rnd.nextInt(10, 100));
        }

        System.out.println("Eredeti lista: " + szamok);

        szamok.removeIf(szam -> szam % 2 != 0);

        System.out.println("Módisított lista: " + szamok);
    }
}
