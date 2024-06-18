//Pogodite zadatu kombinaciju znakova iz 6 pokušaja.
// 
//

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

class Skocko {

    // Inicijalizacija klasa
    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);

    // Promenljive
    // s=Skocko t=Tref p=Pik k=Karo h=Herc z=Zvezda
    static final char ZnakoviZaSkocka[] = {'S' , 'T', 'P', 'K', 'H', 'Z' };
    static final int DuzinaNiz = 4;
    static int Pokusaji = 6;

    // Generisanje konacne kombinacije
    public static char[] GenerisiKombinaciju() {

        int Duzina = ZnakoviZaSkocka.length;
        char Kombinacija[] = new char[4];

        for (int i = 0; i < 4; i++) {
            Kombinacija[i] = ZnakoviZaSkocka[(int) Math.floor(Math.random() * Duzina)];
        }
        
        

        return Kombinacija;
    }

    // Niz koja sluzi kao konacna kombinacija
    static char[] Kombinacija = GenerisiKombinaciju();

    // Glavni deo igre. Igrac ima 6 pokusaja, ako je broj veci od 0 (korisnik ima
    // jos poteza), igra ce se pokrenuti.
    public static int[] SkockoIgra(char[] UpisaniZnakovi) {

        int NaDobromMestu = 0;
        int NaLosemMestu = 0;

        // Igrac ima 6 pokusaja, ako je broj veci od 0 (korisnik ima jos poteza), igra
        // ce se pokrenuti.

        // Glavni algoritam
        for (int i = 0; i < DuzinaNiz; i++) {

            if (UpisaniZnakovi[i] == Kombinacija[i]) {
                NaDobromMestu++;
                UpisaniZnakovi[i] = 'X';
            } else {
                for (int j = 0; j < DuzinaNiz; j++) {
                    if (Kombinacija[i] == UpisaniZnakovi[j] && Kombinacija[j] != UpisaniZnakovi[j]) {
                        NaLosemMestu++;
                        UpisaniZnakovi[j] = 'X';
                        break;
                    }
                }
            }
        }

        // Niz koji vraca dal su znakovi na dobrom mestu
        int[] PomocniNiz = { NaDobromMestu, NaLosemMestu };

        NaDobromMestu = 0;
        NaLosemMestu = 0;

        return PomocniNiz;
    }

    // Korisnik upisuje znakove
    public static char[] UpisZnakova(char[] UpisaniZnakovi) {

        char a = ' ';

        for (int i = 0; i < DuzinaNiz; i++) {
            try {
                System.out.println("Upiši sledeći znak");
                a = input.next().charAt(0);

            } catch (InputMismatchException e) {
                throw new InputMismatchException("Moraš da uneseš neki od sledećih znakova: 'H', 'S', 'T', 'Z', 'K'.");
                // System.out.println("Mora biti jedan karakter.");
            } catch (Exception e) {
                throw new InputMismatchException("Pokušaj ponovo.");
                // System.out.println("Pokusaj ponovo.");
            }

            // Konvertovanje ako korisnik upise malo slovo
            if (a == 'h') {
                a = 'H';
            } else if (a == 's') {
                a = 'S';
            } else if (a == 't') {
                a = 'T';
            } else if (a == 'p') {
                a = 'P';
            } else if (a == 'k') {
                a = 'K';
            } else if (a == 'z') {
                a = 'Z';
            }

            if (a == 'H' || a == 'S' || a == 'T' || a == 'P' || a == 'K' || a == 'Z') {
                UpisaniZnakovi[i] = a;
            } else
                throw new InputMismatchException("Moraš da uneseš neki od sledećih znakova: 'H', 'S', 'T', 'Z', 'K'.");

        }

        return UpisaniZnakovi;
    }

    // Ispisivanje rezultata
    public static void Ispis(char[] UpisaniZnakovi) {

        System.out.println("Vaša kombinacija je: ");
        for (int k = 0; k < DuzinaNiz; k++) {
            System.out.print(UpisaniZnakovi[k] + " ");
        }

        System.out.println();

        int[] niz = SkockoIgra(UpisaniZnakovi);
        int NaDobromMestu = niz[0];
        int NaLosemMestu = niz[1];

        if (NaDobromMestu == 4) {

            System.out.println("Pogodio si");
            return;

        } else {

            System.out.println();
            System.out.println("Na dobrom mestu " + NaDobromMestu);
            System.out.println("Na lošem mestu " + NaLosemMestu);

            Pokusaji--;

            if (Pokusaji <= 0) {
                System.out.println("Izgubio si. Rešenje je: ");
                for (int i = 0; i < DuzinaNiz; i++) {
                    System.out.print(Kombinacija[i] + " ");
                }
                return;
            }

            UpisaniZnakovi = UpisZnakova(UpisaniZnakovi);
            Ispis(UpisaniZnakovi);
        }
    }

    public static void main(String[] args) {

        System.out.println("SKOČKO.");
        System.out.println("Skočko se igra sa sledećim znakovima: 'H', 'S', 'T', 'Z', 'K'.");

        char[] UpisaniZnakovi = new char[DuzinaNiz];

        UpisaniZnakovi = UpisZnakova(UpisaniZnakovi);
        Ispis(UpisaniZnakovi);

    }
}