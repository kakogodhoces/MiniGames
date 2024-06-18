
//Sluzi za pokretanje igre



import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        
        int BrojIgre =0;
        
        try{
            System.out.println("Upiši broj igre koju zeliš da igras: 1)Skočko 2)Lavirint 3)Vešala");
            BrojIgre = input.nextInt();
        } catch(InputMismatchException e){
            System.out.println("Pokušaj ponovo " + e + ".");
        }
         
        if(BrojIgre<1 || BrojIgre > 3){
            throw new InputMismatchException("Pokušaj ponovo.");
        } else if(BrojIgre == 1){
            //Skocko
            Skocko.main(args);
        } else if(BrojIgre==2){
            //Lavirint
            PobegniIzLavirinta.main(args);
        } else if(BrojIgre==3){
            //Vesala
            Vesala.main(args);
        }
    }

}
