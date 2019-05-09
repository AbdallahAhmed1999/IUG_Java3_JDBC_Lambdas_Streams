/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q3;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Abdallah_Ahmed
 */
public class Main {

    public static void main(String[] args) {
        Invoice[] invoices = {
            new Invoice(83, "Elctric sander", 7, 57.98),
            new Invoice(24, "Power saw", 18, 99.99),
            new Invoice(7, "Sledge hammer", 11, 21.50),
            new Invoice(77, "Hammer", 76, 11.99),
            new Invoice(39, "Lawn mower", 3, 79.50),
            new Invoice(68, "Screwdriver", 106, 6.99),
            new Invoice(56, "Jig saw", 21, 11.00),
            new Invoice(3, "Wrench", 34, 7.50)
        };

        DbConnection db = DbConnection.getdbConnection();

//        for (Invoice invoice : invoices) {
//            db.addInvoice(invoice);
//        }

ArrayList<Invoice> invoiceList = db.getInvoices();

        //a the results are sorted by Description
        System.err.printf("%-6s | %-15s | %-10s | %-10s\n", "Number", "Description", "Quantity", "Price");
        invoiceList.stream()
                .sorted(Comparator.comparing(Invoice::getPartDescription))
                .forEach(System.out::println);

        //b the results are sorted by price
        System.out.println("\n=============================================");
        System.err.printf("%-6s | %-15s | %-10s | %-10s\n", "Number", "Description", "Quantity", "Price");
        invoiceList.stream()
                .sorted(Comparator.comparing(Invoice::getPrice))
                .forEach(System.out::println);

        //c the results are maped and ordered by quantity
        System.out.println("\n=============================================");
        System.err.printf("%-15s | %-5s\n", "Description", "Quantity");
        invoiceList.stream()
                .sorted(Comparator.comparing(Invoice::getQuantity))
                .map(invoice -> String.format("%-15s | %-5s", invoice.getPartDescription(), invoice.getQuantity()))
                .forEach(System.out::println);

        //d the results are maped and ordered by Invoice value
        System.out.println("\n=============================================");
        System.err.printf("%-15s | %-5s\n", "Description", "Invoice Value");
        invoiceList.stream()
                .sorted(Comparator.comparing(Invoice::invoiceValue))
                .map(invoice -> String.format("%-15s | %-5.2f", invoice.getPartDescription(), invoice.invoiceValue()))
                .forEach(System.out::println);

        //e the results are maped and ordered by Invoice value in the range $200 to $500
        System.out.println("=============================================");
        System.err.printf("%-15s | %-5s\n", "Description", "Invoice Value");
        invoiceList.stream()
                .filter(invoice -> {
                    return invoice.invoiceValue() > 200 && invoice.invoiceValue() < 500;
                })
                .sorted(Comparator.comparing(Invoice::invoiceValue))
                .map(invoice -> String.format("%-15s | %-5.2f", invoice.getPartDescription(), invoice.invoiceValue()))
                .forEach(System.out::println);
    }
}
