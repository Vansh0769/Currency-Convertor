package com.company;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
                URLCONN obj = new URLCONN();
                Scanner sc = new Scanner(System.in);

                System.out.println("Enter the currency code from which you have to convert (like INR, USD, etc):");
                String fromCurrency = sc.nextLine().toUpperCase();

                System.out.println("Enter the currency code to which you have to convert your amount:");
                String toCurrency = sc.nextLine().toUpperCase();

                System.out.println("Enter the amount:");
                double amount = sc.nextDouble();

                double rate = obj.getRateForCurrency(fromCurrency, toCurrency);
                if (rate != -1) {
                        double convertedAmount = amount * rate;
                        System.out.println("Converted Amount: " + convertedAmount + " " + toCurrency);
                } else {
                        System.out.println("Conversion failed. Please check the currency codes and try again.");
                }
        }
}
