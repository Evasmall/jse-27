package ru.evasmall.tm;

import java.lang.reflect.Proxy;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        FactorialCalc factorialCalc = new FactorialCalc();
        FactorialHandler factorialHandler = new FactorialHandler(factorialCalc);
        IFactorial proxy = (IFactorial) Proxy.newProxyInstance(
                factorialCalc.getClass().getClassLoader(), factorialCalc.getClass().getInterfaces(), factorialHandler);
        System.out.println("Программа расчета факториала чисел.");
        System.out.println("Для завершения программы используется команда end.");
        while (true) {
            try {
                System.out.println("Введите целое число:");
                final String line = scanner.nextLine();
                if (line.equals("end")) {
                    logger.log(Level.SEVERE, "Программа завершена.");
                    break;
                }
                final Integer intNumber = Integer.parseInt(line);
                if (intNumber < 0) {
                    logger.log(Level.SEVERE,  " Значение является отрицательным числом. Ошибка.");
                }
                else {
                    System.out.println(proxy.getFactorial(intNumber));
                }
            } catch (InputMismatchException | NumberFormatException e) {
                logger.log(Level.SEVERE, " Значение не является целым числом. Ошибка: ".concat(e.toString()));
            }
        }
    }

}
