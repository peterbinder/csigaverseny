package beadando;

import java.util.Random;

class Csiga {
    private Szin szin;

    private int gyorsito = 1;
    private double megtettTavolsag = 0.0;

    private int maxSebesség;

    private Random random;

    public Csiga(Szin szin, int maxSebesség, Random random) {
        this.szin = szin;
        this.maxSebesség = maxSebesség;
        this.random = random;
    }

    public Szin getSzin() {
        return szin;
    }

    public double getMegtettTavolsag() {
        return megtettTavolsag;
    }

    public void halad() {
        int haladas = random.nextInt(maxSebesség + 1) * gyorsito;
        megtettTavolsag += haladas;
        gyorsitotVisszaallit();

        System.out.println("A " + szin + " csiga " + haladas + " egységet haladt ebben a körben");
    }

    public void gyorsitotModosit(int gyorsito) {
        this.gyorsito = gyorsito;
    }

    private void gyorsitotVisszaallit() {
        gyorsito = 1;
    }

}