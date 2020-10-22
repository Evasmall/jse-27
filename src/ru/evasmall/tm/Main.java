package ru.evasmall.tm;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число:");
        try {
            final Integer intNumber = scanner.nextInt();
            if (intNumber < 0) {
                logger.log(Level.SEVERE, "Значение является отрицательным числом. Ошибка.");
            }
            System.out.println("Факториал числа " + intNumber + " = " +  Factorial.getFactorial(intNumber));
        } catch (InputMismatchException e) {
            logger.log(Level.SEVERE, e.toString() + " Значение не является целым числом. Ошибка.");
        }
        return;
    }

}
