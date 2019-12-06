import java.util.Scanner;

 /*
    Na podswie numeru pesel można stwierdzić informację o płci, dacie urodzenia napisz taki program konsolowy który przyjmie PESEL
    sprawdzi jego poprawność to znaczy: ilość znaków i sprawdzi sumę kontrolną i zwróci informację o
    płci i dacie urodzenia jeżeli PESEL jest poprawny
    */

public class Pesel {
    public static void main(String[] args) {
        String pesel;
        int sumaKontrolna = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Podaj pesel składający się z 11 liczb :");
            pesel = scanner.next();
            if (pesel.length() > 11 || pesel.length() < 11) {
                System.out.println("Podałeś niepoprawną ilość znaków.");
            }

            for (int i = 0; i < pesel.length(); i++) {
                sumaKontrolna += 1;
            }
            System.out.println("Suma kontrolna : " + sumaKontrolna);
            sumaKontrolna = 0;
        } while (pesel.length() != 11);

        System.out.println("Data urodzenia to : " + pesel.substring(0, 6));

        int plec = Integer.parseInt(pesel.substring(9, 10));

        if (plec % 2 == 0) {
            System.out.println("Podany pesel należy to kobiety.");
        } else {
            System.out.println("Podany pesel należy do mężczyzny.");
        }
    }
}


