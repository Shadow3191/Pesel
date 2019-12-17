/*
    Na podswie numeru pesel można stwierdzić informację o płci, dacie urodzenia napisz taki program konsolowy który przyjmie PESEL
    sprawdzi jego poprawność to znaczy: ilość znaków i sprawdzi sumę kontrolną i zwróci informację o
    płci i dacie urodzenia jeżeli PESEL jest poprawny
    */

import java.time.LocalDate;
import java.util.Scanner;

public class Pesel {
    static byte[] PESEL = new byte[11];
    static boolean valid = false;

    public static void main(String[] args) {
        String pesel;
        int sumaKontrolna = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Podaj pesel składający się z 11 liczb :");
            pesel = scanner.next();
            if (pesel.length() != 11) {
                valid = false;
                System.out.println("Podałeś błędny pesel, proszę o wprowadzenie peselu ponownie. \n");
            } else {
                for (int i = 0; i < pesel.length(); i++) {
                    PESEL[i] = (Byte.parseByte(pesel.substring(i, i + 1)));
                    sumaKontrolna += 1;
                }
                if (checkSum() && checkMonth() && checkDay()) {
                    valid = true;
                } else {
                    valid = false;
                }
            }
            System.out.println("Suma kontrolna : " + checkSum() + " " + sumaKontrolna);
            sumaKontrolna = 0;
        }
        while (pesel.length() != 11);

        System.out.println("Data urodzenia to : " + LocalDate.of(getBirthYear(), getBirthMonth(), getBirthDay()));
        System.out.println("Podany pesel należy do " + getSex());
    }

    private static String getSex() {
        if (valid) {
            if (PESEL[9] % 2 == 1) {
                return "Mezczyzna";
            } else {
                return "Kobieta";
            }
        } else {
            return "---";
        }
    }

    static boolean checkDay() {
        int year = getBirthYear();
        int month = getBirthMonth();
        int day = getBirthDay();
        if ((day > 0 && day < 32) &&
                (month == 1 || month == 3 || month == 5 ||
                        month == 7 || month == 8 || month == 10 ||
                        month == 12)) {
            return true;
        } else if ((day > 0 && day < 31) &&
                (month == 4 || month == 6 || month == 9 ||
                        month == 11)) {
            return true;
        } else if ((day > 0 && day < 30 && leapYear(year)) ||
                (day > 0 && day < 29 && !leapYear(year))) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean leapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        else
            return false;
    }

    private static int getBirthDay() {
        int day;
        day = 10 * PESEL[4];
        day += PESEL[5];
        return day;
    }

    private static int getBirthMonth() {
        int month;
        month = 10 * PESEL[2];
        month += PESEL[3];
        if (month > 80 && month < 93) {
            month -= 80;
        } else if (month > 20 && month < 33) {
            month -= 20;
        } else if (month > 40 && month < 53) {
            month -= 40;
        } else if (month > 60 && month < 73) {
            month -= 60;
        }
        return month;
    }

    private static int getBirthYear() {
        int year;
        int month;
        year = 10 * PESEL[0];
        year += PESEL[1];
        month = 10 * PESEL[2];
        month += PESEL[3];
        if (month > 80 && month < 93) {
            year += 1800;
        } else if (month > 0 && month < 13) {
            year += 1900;
        } else if (month > 20 && month < 33) {
            year += 2000;
        } else if (month > 40 && month < 53) {
            year += 2100;
        } else if (month > 60 && month < 73) {
            year += 2200;
        }
        return year;
    }

    static boolean checkMonth() {
        int month = getBirthMonth();
        int day = getBirthDay();
        if (month > 0 && month < 13) {
            return true;
        } else {
            return false;
        }
    }

    static boolean checkSum() {
        int sum = 1 * PESEL[0] +
                3 * PESEL[1] +
                7 * PESEL[2] +
                9 * PESEL[3] +
                1 * PESEL[4] +
                3 * PESEL[5] +
                7 * PESEL[6] +
                9 * PESEL[7] +
                1 * PESEL[8] +
                3 * PESEL[9];
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        if (sum == PESEL[10]) {
            return true;
        } else {
            return false;
        }
    }
}


