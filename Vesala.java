
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;


class Vesala {


    static Random rand = new Random();

    public static char[] slovaKojaSeNeSadrze = new char[6];
    public static int g = 0;

    public static void slovaKojaSeNeSadrzeIspis() { // ispis slova koja si pogresio
        if (g != 0) {
            System.out.print("Slova koja nisu u reci su: ");
            for (int i = 0; i < g; i++) {
                System.out.print(slovaKojaSeNeSadrze[i] + ", ");
            }
            System.out.println("");
        }

    }

    public static void cicaglisa(int greske) { // ispis coveculjka i vesala
        switch (greske) {
            case 0:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        " |");
                break;
            case 1:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |    O\n" +
                        " |\n" +
                        " |\n" +
                        " |\n" +
                        " |");
                break;
            case 2:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |    O\n" +
                        " |    | \n" +
                        " |\n" +
                        " |\n" +
                        " |");
                break;
            case 3:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |    O\n" +
                        " |    |\\\n" +
                        " |\n" +
                        " |\n" +
                        " |");
                break;
            case 4:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |    O\n" +
                        " |   /|\\\n" +
                        " |\n" +
                        " |\n" +
                        " |");
                break;
            case 5:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |    O\n" +
                        " |   /|\\\n" +
                        " |   /\n" +
                        " |\n" +
                        " |");
                break;
            case 6:
                System.out.println("  _____\n" +
                        " |    |\n" +
                        " |    O   \n" +
                        " |   /|\\  \n" +
                        " |   / \\  \n" +
                        " |\n" +
                        " |");
                break;
        }

    }

    public static boolean slovaNeSadrze(String rec, int i, char slovo) {// Ova metoda proverava ako nema unetog slova;
        if (i == rec.length()) {
            slovaKojaSeNeSadrze[g] = slovo;
            g++;
            return true;

        }

        if (rec.charAt(i) == slovo) {
            return false;

        }
        return slovaNeSadrze(rec, i + 1, slovo);
    }

    public static char[] slova(String rec, char slovo, int i, char[] pogodjeni) {// Ova metoda gleda da li ima slova i
                                                                                 // ako ih ima koliko ih ima i postavlja
                                                                                 // ih na mesta u char[]pogodjeni
        if (i == rec.length()) { // Bazni slucaj
            return pogodjeni;

        }

        if (rec.charAt(i) == slovo) {// ako nadje slovo u reci replejsuje ga sa _ u istoj poziciji (i sluzi za
                                     // poziciju)
            pogodjeni[i] = slovo;

        }

        return slova(rec, slovo, i + 1, pogodjeni); // Rekurzija

    }

    public static char[] praznaRec(String rec) { // Ova metoda ispunjava char[]pogodjeni sa onliko crta koliko rec ima.
        char[] pogodjeni = new char[rec.length()];
        for (int j = 0; j < rec.length(); j++) {
            pogodjeni[j] = '_';

        }
        return pogodjeni;
    }

    public static void igra(int greske, int i, String rec, char[] praznaRec) {
        Scanner ul = new Scanner(System.in);
        while (greske < 6) { // sve ide dok ne dostigne 6 gresaka
            System.out.println("");
            System.out.println("");
            System.out.println("Unesite slovo");
            char slovo = ul.next().charAt(0); // unos slova
            if ((int) slovo >= 65 && (int) slovo <= 90) { // proverava ako je Veliko slovo
                slovo = (char) ((int) slovo + 32); // pretvara ga u malo
            }
            if (Character.isLetter(slovo)) { // proverava da li je slovo slovo a ne broj ili nes drugo

            } else {
                throw new RuntimeException("Mora da bude slovo."); // ako nije baca eksepsn
            }

            char[] pogodjeniMet = slova(rec, slovo, i, praznaRec); // poziva metodu Slova koja replejsuje npr. char[]
                                                                   // blabla = _ _ _ _ _; sa chat[] blabla2 = _ _ a _ a;
            for (int z = 0; z < praznaRec.length; z++) { // ispisuje char[] pogodjeniMet sto je metoda slova
                System.out.print(pogodjeniMet[z] + " ");
            }

            System.out.println("");
            slovaKojaSeNeSadrzeIspis(); // ispis slova koja se ne sardze

            if (slovaNeSadrze(rec, i, slovo) == true) { // ako su slovaNeSadrze metoda true to znaci da ne sadrzi slova
                                                        // pa mu ovaj if nadodaje jos jednu gresku i preskace ostatak
                slovaKojaSeNeSadrzeIspis(); // ispis slova koja se ne sardze
                greske++;
                cicaglisa(greske); // ispis vesala
                System.out.println("Nije, vase greske su trenutno na " + greske + "/" + 6);
                continue;
            }
            cicaglisa(greske);
            System.out.println("Da li znate rec?");
            System.out.println("Ako ne znate napisite 'ne'");
            String akoZnasRec = ul.next(); // unos reci koje korisnik pogadja ili negira
            akoZnasRec = akoZnasRec.toLowerCase(); // pretvara se sve u mala slova da bi mogao da napise i Ne i NE i nE
            if (rec.equals(akoZnasRec)) { // ako je rec pogodjena
                System.out.println("Bravo! pogodili ste rec!");
                break;
            } else if (akoZnasRec.equals("ne")) { // ako ne zna rec
                System.out.println("Oke.");

            } else if (akoZnasRec.equals("neznam")) { // ako ne zna rec ali ne zna ni da pise
                System.out.println("Nauci da pises");

            } else { // ako je pokusao da pogodi rec ali je nije pogodio
                slovaKojaSeNeSadrzeIspis(); // ispis slova koja se ne sardze
                greske++; // dizu mu se greske
                cicaglisa(greske); // ispis vesala
                System.out.println("Nije, vase greske su trenutno na " + greske + "/" + 6);
            }
        }
        if (greske == 6) { // ako su greske gostigle 6 ispisuje game over
            cicaglisa(greske);
            System.out.println("Obesili ste coveka!");
            System.out.println("Rec je bila " + rec);
            System.out.println("GAME OVER");

        }
    }


    public static String CitajIzFajla() throws IOException {
        //Inicijalizacija klasa
        BufferedReader reader = new BufferedReader(new FileReader("reci.txt"));
        
        //Broj linija u fajlu
        long BrojLinija= 0;
        //Dokle god ima neceg u fajlu broji linije fajla
        while(reader.readLine() != null) BrojLinija++;
        reader.close();
        
        //Random broj
        long RandomLinija = rand.nextLong(BrojLinija);
        
        //Random rec
        String s ="";
        //Mora u try blok zbog fajla
        //Stream je kao ArrayLista(isto je collection) nadjemo fajl i onda preskacemo linije dok ne dodjemo do one koja nama odogovara pa je procitamo i stavimo u string.
        try(Stream<String> Linije = Files.lines(Paths.get("reci.txt"))){
            s = Linije.skip(RandomLinija).findFirst().get();
        } catch(IOException e){
            System.out.println(e);
        }
        //Za test:
        //System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        Scanner ul = new Scanner(System.in); // Scanner
        
        try {
            String rec = CitajIzFajla();
            rec = rec.toLowerCase();
            for (int i = 0; i < 40; i++) { // da se ne vidi rec
                System.out.println("");
            }
            char[] praznaRec = praznaRec(rec);// samo ime kaze prazna rec
            int i = 0; // pozicija u nizu carova u metodama slova i slovaNePronadjena
            int greske = 0; // koliko je gresaka uradio
            igra(greske, i, rec, praznaRec);
        } catch (RuntimeException e) {
            System.out.println("Mora da bude slovo.");
        } catch (Exception e) {
            System.out.println("Greska! Molim vas probajte ponovo.");

        }

    }

}
