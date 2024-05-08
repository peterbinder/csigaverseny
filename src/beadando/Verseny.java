package beadando;

import java.util.*;

class Verseny {

    private Random random;
    private List<Csiga> csigak;
    private int aktualisKor = 0;
    private static final int MAX_KOROK = 5;
    private Scanner scanner;
    private Szin tippSzine;

    public Verseny() {
        random = new Random();
        csigak = new ArrayList<>();
        csigak.add(new Csiga(Szin.PIROS, 3, random));
        csigak.add(new Csiga(Szin.ZOLD, 3, random));
        csigak.add(new Csiga(Szin.KEK, 3, random));
        scanner = new Scanner(System.in);
    }

    public void tippBekeres() {
        System.out.println("Tippelje meg a nyerő csiga színét (PIROS, ZOLD, KEK):");
        String felhasznaloTippje = scanner.nextLine().toUpperCase();
        try {
            tippSzine = Szin.valueOf(felhasznaloTippje);
            System.out.println("A tipp a " + tippSzine + " csigára lett helyezve.");
        } catch (IllegalArgumentException e) {
            System.out.println("Érvénytelen szín. Nem lett tipp helyezve.");
            tippSzine = null;
        }
    }

    public void indit() {
        tippBekeres();
        while (aktualisKor < MAX_KOROK) {
            System.out.println("-----------------------------------------");
            System.out.println("Kör " + (aktualisKor + 1));
            veletlenCsigaGyorsit();
            csigak.forEach(Csiga::halad);
            aktualisKor++;
            tavolsagokKiirasa();
        }
        Csiga gyoztes = gyoztesKivalasztasa();
        eredmenyKiirasa(gyoztes);
    }

    private void veletlenCsigaGyorsit() {
        if (random.nextDouble() < 0.20) {
            int veletlenIndex = random.nextInt(csigak.size());
            csigak.get(veletlenIndex).gyorsitotModosit(2);
            System.out.println(">>>>>A " + csigak.get(veletlenIndex).getSzin() + " csiga gyorsítást kapott!<<<<<");
        }
    }

    private void eredmenyKiirasa(Csiga gyoztes) {
        String eredmenyUzenet = "\nA győztes csiga színe: " + gyoztes.getSzin() + "!\n";
        eredmenyUzenet += tippSzine != null && gyoztes.getSzin().equals(tippSzine)
                ? "\nGratulálunk, a tipp helyes volt, nyertél!"
                : "\nSajnos a tipp helytelen volt, nem nyertél.";
        System.out.println(eredmenyUzenet);
    }

    private void tavolsagokKiirasa() {
        System.out.println("\nKör összesítő:");
        csigak.forEach(csiga ->
                System.out.println(csiga.getSzin() + " csiga eddig összesen " + csiga.getMegtettTavolsag() + " egységet tett meg"));
    }

    private Csiga gyoztesKivalasztasa() {
        return csigak.stream().max(Comparator.comparingDouble(Csiga::getMegtettTavolsag)).get();
    }
}