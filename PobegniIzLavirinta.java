import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author petar
 */
public class PobegniIzLavirinta {

    public static String paziZamka = """
             ____   __   ____  __    ____  _  _  ____   __     _
            (  _ \\ / _\\ (__  )(  )  (  _ \\/ )( \\(  _ \\ / _\\   / \\
             ) __//    \\ / _/  )(    )   /) \\/ ( ) __//    \\  \\_/
            (__)  \\_/\\_/(____)(__)  (__\\_)\\____/(__)  \\_/\\_/  (_)  """;

    public static String zid = """
             ____  __  ____
            (__  )(  )(    \\
             / _/  )(  ) D (
            (____)(__)(____/""";

    public static String rupa = """
             _  _  ____   __    __     ____  __    _  _    ____  _  _  ____  _  _    _
            / )( \\(  _ \\ / _\\  /  \\   / ___)(  )  / )( \\  (  _ \\/ )( \\(  _ \\/ )( \\  / \\
            ) \\/ ( ) __//    \\(  O )  \\___ \\ )(   ) \\/ (   )   /) \\/ ( ) __/) \\/ (  \\_/
            \\____/(__)  \\_/\\_/ \\__/   (____/(__)  \\____/  (__\\_)\\____/(__)  \\____/  (_)  """;

    public static String pobeda = """
             ____   __  ____  ____  ___   __     ____  __    _
            (  _ \\ /  \\(  _ \\(  __)/ __) /  \\   / ___)(  )  / \\
             ) __/(  O )) _ ( ) _)( (_ \\(  O )  \\___ \\ )(   \\_/
            (__)   \\__/(____/(____)\\___/ \\__/   (____/(__)  (_)  """;

    public static String predaja = """
             ____  _  _  _  _  _  _     __  ____  _  _  ____  ____  __    __     ____  __    _
            (  _ \\/ )( \\/ )( \\/ )( \\   /  \\(    \\/ )( \\/ ___)(_  _)/ _\\  /  \\   / ___)(  )  / \\
             ) _ () \\/ () \\/ () \\/ (  (  O )) D () \\/ (\\___ \\  )( /    \\(  O )  \\___ \\ )(   \\_/
            (____/\\____/\\____/\\____/   \\__/(____/\\____/(____/ (__)\\_/\\_/ \\__/   (____/(__)  (_)  """;

    public static int[][] generisiLavirint(int level) {
        int[][] lavirint = new int[level][level];

        int min = 0;
        int max = 1;

        for (int i = 0; i < level; i++) {
            for (int j = 0; j < level; j++) {
                lavirint[i][j] = (int) Math.round(Math.random());// Za svaki element u matrici generisi 0 ili 1 kao put
                                                                 // ili zid
            }
        }
        lavirint[0][0] = 0;
        lavirint[level - 1][level - 1] = 0;
        // ostavi ulaz i izlaz kao 0

        return lavirint;
    }

    public static void ispis(int[][] lavirint) {
        for (int i = 0; i < lavirint[0].length; i++) {
            for (int j = 0; j < lavirint.length; j++) {
                System.out.print(lavirint[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean slobodnoPolje(int[][] lav, int xigrac, int yigrac) {
        int y = lav.length;
        int x = lav[0].length;
        return (xigrac >= 0 && xigrac < y && yigrac >= 0 && yigrac < x && lav[xigrac][yigrac] == 0);
        // ispitaj da li je trenutno polje moguce i da li je 0
    }

    public static boolean nadjiPut(int[][] lavirint, int xigrac, int yigrac) {
        int v = lavirint.length;
        int k = lavirint[0].length;
        if (slobodnoPolje(lavirint, xigrac, yigrac)) {
            lavirint[xigrac][yigrac] = 2;// oznacavamo polje na kome smo
            if (xigrac == v - 1 && yigrac == k - 1 && lavirint[xigrac][yigrac] == 2) {
                return true;// nasli smo resenje
            }
            if (nadjiPut(lavirint, xigrac + 1, yigrac))
                return true;// ispitaj desno
            if (nadjiPut(lavirint, xigrac, yigrac + 1))
                return true;// ispitaj dole
            if (nadjiPut(lavirint, xigrac - 1, yigrac))
                return true;// ispitaj levo
            if (nadjiPut(lavirint, xigrac, yigrac - 1))
                return true;// isptiaj gore
            lavirint[xigrac][yigrac] = 0;// ako dodje do corsokaka vraca se i stavlja se 0
        }
        return false;

    }

    public static int[][] unisitiPut(int[][] lavirint) {
        for (int i = 0; i < lavirint[0].length; i++) {
            for (int j = 0; j < lavirint.length; j++) {
                if (lavirint[i][j] == 2) {
                    lavirint[i][j] = 0;// sve 2 pretvara u 0 jer ih je prethodni algoritam pretvorio da bi odredio sebi
                                       // put
                }
            }
        }
        return lavirint;
    }

    public static int brGranicenja(int[][] lavirint, int i, int j) {
        int br = 0;
        if (j + 1 != lavirint[0].length && (lavirint[i][j + 1] == 0)) {
            br++;// da li 0 desno od trenutnog 1
        }
        if (j - 1 >= 0 && (lavirint[i][j - 1] == 0)) {
            br++;// da li je 0 levo od trenutnog 1
        }
        if (i + 1 != lavirint[0].length && (lavirint[i + 1][j] == 0)) {
            br++;// da li je 0 is[pd od trenutnog 1
        }
        if (i - 1 >= 0 && (lavirint[i - 1][j] == 0)) {
            br++;// 0 iznad od trenutnog 1
        }
        return br;
    }

    public static int[][] iskopajRupe(int[][] lavirint) {
        for (int i = 0; i < lavirint[0].length; i++) {
            for (int j = 0; j < lavirint.length; j++) {
                if (lavirint[i][j] == 1) {
                    // int daLiDaStavi = (int) Math.round(Math.random());
                    double daLIDaStavi = Math.random();
                    if (brGranicenja(lavirint, i, j) >= 3 && daLIDaStavi <= 0.75) {
                        lavirint[i][j] = 2; // Ako 1 ima tri ili vise granicenja sa 0
                    }
                }
            }
        }
        return lavirint;
    }

    public static void paziZamka(int xigrac, int yigrac, int[][] lavirint) {
        if ((xigrac - 1 >= 0 && lavirint[yigrac][xigrac - 1] == 2) || // levo
                (xigrac + 1 != lavirint[0].length && (lavirint[yigrac][xigrac + 1] == 2)) || // desno
                (yigrac - 1 >= 0 && (lavirint[yigrac - 1][xigrac] == 2)) || // gore
                (yigrac + 1 != lavirint.length && (lavirint[yigrac + 1][xigrac]) == 2))// desno
        {
            /*
             * ako u postoji polje 2(zamka) u bilo kom smeru
             * (gore,dole,levo,desno) ispse se upozorenje
             */
            System.out.println(paziZamka);
        }
    }

    public static char[][] popuniLavirint(int[][] lavirint) {
        char mapa[][] = new char[lavirint[0].length][lavirint.length];

        for (int i = 0; i < mapa[0].length; i++) {
            for (int j = 0; j < mapa.length; j++) {
                mapa[i][j] = '.';// popuni matricu sa 'x'-ovima
            }
        }
        mapa[0][0] = 'o';// stavi lika na pocetak

        return mapa;
    }

    public static char[][] upisiPutUMapu(char[][] mapa, int xigrac, int yigrac) {

        for (int i = 0; i < mapa[0].length; i++) {
            for (int j = 0; j < mapa.length; j++) {
                mapa[i][j] = '.';/*
                                  * restartuje celu mapu da bi izbrisalo
                                  * mesto lika 'o'
                                  */
            }
        }

        mapa[yigrac][xigrac] = 'o';// ponovo upise lika na mesto gde se trenutno nalazi
        return mapa;

    }

    public static void ispisMapu(char[][] mapa) {
        for (int i = 0; i < mapa[0].length; i++) {
            for (int j = 0; j < mapa.length; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    public static void lavirintIgra(int[][] lavirint) {
        Scanner sc = new Scanner(System.in);
        int xigrac = 0;
        int yigrac = 0;
        int brSmrti = 0;

        char[][] mapa = popuniLavirint(lavirint);

        ispisMapu(mapa);
        paziZamka(xigrac, yigrac, lavirint);

        while (xigrac != lavirint[0].length - 1 || yigrac != lavirint.length - 1) {
            char pokret;
            pokret = sc.next().charAt(0);

            switch (pokret) {
                case 'a':
                case 'A':
                    // levo

                    if (xigrac - 1 >= 0 && lavirint[yigrac][xigrac - 1] == 0) {
                        /*
                         * ispitaj da li polje u matrici levo od igraca
                         * i da li je sledece bolje moguci put
                         */
                        xigrac--;
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                    } else if (xigrac - 1 >= 0 && lavirint[yigrac][xigrac - 1] == 2) {// ako je sledece polje 2(rupa)
                        xigrac = 0;
                        yigrac = 0;
                        // igraca restartujemo na pocetak
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                        System.out.println(rupa);
                        brSmrti++;
                    } else {
                        ispisMapu(mapa);
                        System.out.println(zid);// ako je 1(zid) lik ne moze da prodje
                    }
                    paziZamka(xigrac, yigrac, lavirint);
                    break;
                case 'd':
                case 'D':
                    // desno
                    if (xigrac + 1 != lavirint[0].length && (lavirint[yigrac][xigrac + 1] == 0)) {
                        /*
                         * ispitaj da li polje u matrici desno od igraca
                         * i da li je sledece bolje moguci put
                         */
                        xigrac++;
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                    } else if (xigrac + 1 != lavirint[0].length && lavirint[yigrac][xigrac + 1] == 2) {// ako je sledece
                                                                                                       // polje 2(rupa)
                        xigrac = 0;
                        yigrac = 0;
                        // igraca restartujemo na pocetak
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                        System.out.println(rupa);
                        brSmrti++;
                    } else {
                        ispisMapu(mapa);
                        System.out.println(zid);// ako je 1(zid) lik ne moze da prodje
                    }
                    paziZamka(xigrac, yigrac, lavirint);
                    break;
                case 'w':
                case 'W':
                    // gore
                    if (yigrac - 1 >= 0 && (lavirint[yigrac - 1][xigrac]) == 0) {
                        /*
                         * ispitaj da li polje u matrici iznad igraca
                         * i da li je sledece bolje moguci put
                         */
                        yigrac--;
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                    } else if (yigrac - 1 >= 0 && lavirint[yigrac - 1][xigrac] == 2) {// ako je sledece polje 2(rupa)
                        xigrac = 0;
                        yigrac = 0;
                        // igraca restartujemo na pocetak
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                        System.out.println(rupa);
                        brSmrti++;
                    } else {
                        ispisMapu(mapa);
                        System.out.println(zid);// ako je 1(zid) lik ne moze da prodje
                    }
                    paziZamka(xigrac, yigrac, lavirint);
                    break;
                case 's':
                case 'S':
                    // dole
                    if (yigrac + 1 != lavirint.length && (lavirint[yigrac + 1][xigrac]) == 0) {
                        /*
                         * ispitaj da li polje u matrici ispod igraca
                         * i da li je sledece bolje moguci put
                         */
                        yigrac++;
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                    } else if (yigrac + 1 != lavirint.length && lavirint[yigrac + 1][xigrac] == 2) {// ako je sledece
                                                                                                    // polje 2(rupa)
                        xigrac = 0;
                        yigrac = 0;
                        // igraca restartujemo na pocetak
                        upisiPutUMapu(mapa, xigrac, yigrac);
                        ispisMapu(mapa);
                        System.out.println(rupa);
                        brSmrti++;
                    } else {
                        ispisMapu(mapa);
                        System.out.println(zid);// ako je 1(zid) lik ne moze da prodje
                    }
                    paziZamka(xigrac, yigrac, lavirint);
                    break;
                case 'm':
                case 'M':
                    System.out.println(predaja);
                    return;
                default:
                    throw new RuntimeException("Nisi uneo validnu kontrolu");
            }
        }

        System.out.println(brSmrti);
        System.out.println(pobeda);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Pokusaj da nadjes put van mracnog zacaranog lavirinta, pokusaj da izbegnes rupe i da prolazis izmedju zidova!");
        System.out.println("W A S D da bi se pomerao i M da bi odusto");
        System.out.println("Unesi tezinu lavirinta 1-7x7, 2-12*12, 3-17*17");
        int[][] lavirint = null;
        int tezinaLavirinta;

        try {
            tezinaLavirinta = sc.nextInt();
            if (tezinaLavirinta < 1 || tezinaLavirinta > 3) {
                throw new RuntimeException("Tezina moze da bude od 1 do 3");
            }
            switch (tezinaLavirinta) {
                case 1 -> {
                    tezinaLavirinta = 7;
                    lavirint = generisiLavirint(tezinaLavirinta);
                }
                case 2 -> {
                    tezinaLavirinta = 12;
                    lavirint = generisiLavirint(tezinaLavirinta);
                }
                case 3 -> {
                    tezinaLavirinta = 17;
                    lavirint = generisiLavirint(tezinaLavirinta);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Unesi korektnu vrednosti");
            return;
        } catch (RuntimeException e) {
            System.out.println("Unesi validnu kontrolu");
            return;
        }

        int i = 1;
        while (!nadjiPut(lavirint, 0, 0)) {
            lavirint = generisiLavirint(tezinaLavirinta);
            i++;
        }

        lavirint = unisitiPut(lavirint);
        lavirint = iskopajRupe(lavirint);
        // ispis(lavirint);

        long start = System.currentTimeMillis();
        lavirintIgra(lavirint);
        long stop = System.currentTimeMillis();
        System.out.println("Igra je zavrsena za " + (stop - start) / 1000 + "." + (stop - start) % 1000 + " sekunde");

    }
}