package com.itschool.waketraindev.excercise;

import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.Scanner;

public class DoNotGamble {
    static private final double HIGH_MATCH = 51.0;
    static private final double LOW_MATCH = 49.0;

    private static class Game {
        private final Random rng = new Random();
        private final Scanner inputScanner = new Scanner(System.in);
        private int pocketChange = 5_00; // In cents
        private int bankBalance = 100_00;
        DoubleSummaryStatistics playerStats = new DoubleSummaryStatistics();
        DoubleSummaryStatistics bankStats = new DoubleSummaryStatistics();

        private void exitTheCasino() {
            System.out.println("=========================");
            System.out.println("Felicitari!!! Ai plecat din cazino cu bani!!@#$!");
            System.out.printf("Balans: %.2f %n", moneyToDouble(pocketChange));
            System.out.printf("Player Stats; \t\t Maini Jucate: %d, Medie Balans: %.2f, Suma Jucata: %.2f, Top Balance: %.2f %n", playerStats.getCount(), playerStats.getAverage(), playerStats.getSum(), playerStats.getMax());
            System.out.printf("Bank   Stats: \t\t Maini Jucate: %d, Medie Balans: %.2f, Suma Jucata: %.2f, Top Balance: %.2f %n", bankStats.getCount(), bankStats.getAverage(), bankStats.getSum(), bankStats.getMax());
            System.exit(1);
        }

        private double moneyToDouble(int value) {
            return value / 100.0;
        }

        private void run() {
            while (pocketChange > 0) {
                boolean choice = false;
                System.out.printf("Balans$: %.2f  Ce alegi? +(mare) -(mic) X(exit) [+/-/X]: ", moneyToDouble(pocketChange));
                String line = inputScanner.nextLine().trim().toLowerCase();

                switch (line) {
                    case "+" -> choice = true;
                    case "x" -> exitTheCasino();
                }

                double luckyNumber = rng.nextDouble(100.0);
                boolean isWin = (luckyNumber > HIGH_MATCH && choice) || (luckyNumber < LOW_MATCH && !choice);
                System.out.printf("Alegerea ta: %s \t\t\t Numarul Norocos: %.2f %n", choice ? "MARE" : "mic", luckyNumber);

                if (isWin) {
                    pocketChange += 1_00;
                    bankBalance -= 1_00;
                    System.out.printf("Ai CASTIGAT 1 credit.\t\t Ramas: %.2f  Bank: %.2f %n", moneyToDouble(pocketChange), moneyToDouble(bankBalance));
                } else {
                    pocketChange -= 1_00;
                    bankBalance += 1_00;
                    System.out.printf("Ai PIERDUT 1 credit. \t\t Ramas: %.2f Bank: %.2f %n", moneyToDouble(pocketChange), moneyToDouble(bankBalance));
                }
                playerStats.accept(moneyToDouble(pocketChange));
                bankStats.accept(moneyToDouble(pocketChange));
            }
            System.out.println("=========================");
            System.out.println("Ai pierdut, du-te la ATM!");
            System.out.printf("Player Stats; \t\t Maini Jucate: %d, Medie Balans: %.2f, Suma Jucata: %.2f, Top Balance: %.2f %n", playerStats.getCount(), playerStats.getAverage(), playerStats.getSum(), playerStats.getMax());
            System.out.printf("Bank   Stats: \t\t Maini Jucate: %d, Medie Balans: %.2f, Suma Jucata: %.2f, Top Balance: %.2f %n", bankStats.getCount(), bankStats.getAverage(), bankStats.getSum(), bankStats.getMax());

        }
    }


    public static void main(String[] args) {
        new Game().run();
    }

}

