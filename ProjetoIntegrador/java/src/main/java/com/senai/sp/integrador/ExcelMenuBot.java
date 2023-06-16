package com.senai.sp.integrador;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ExcelMenuBot {
    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\54353123882\\Desktop/products.xlsx"); // Insira o caminho do arquivo Excel existente aqui

            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);

            Scanner scanner = new Scanner(System.in);
            int choice;

            String name = "Jurema";
            String desc = " I was created for a Java integrator project (better than python). " +
                    "I'm here to help you! ❤";

            do {
                System.out.println("Hey! I'm  " + name);
                System.out.println(desc);
                System.out.println("Menu:");
                System.out.println("1. See all products"); // Opção para exibir todos os produtos
                System.out.println("2. See product names only"); // Opção para exibir apenas os nomes dos produtos
                System.out.println("3. See all prices"); // Opção para exibir todos os preços
                System.out.println("4. See names in alphabetical order"); // Opção para exibir os nomes em ordem alfabética
                System.out.println("5. Exit"); // Opção para sair do programa
                System.out.print("Choose an option:\n");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        allProducts(sheet); // Chama a função para exibir todos os produtos
                        break;
                    case 2:
                        productsNames(sheet); // Chama a função para exibir apenas os nomes dos produtos
                        break;
                    case 3:
                        allPrices(sheet); // Chama a função para exibir todos os preços
                        break;
                    case 4:
                        alphabeticOrder(sheet); // Chama a função para exibir os nomes em ordem alfabética
                        break;
                    case 5:
                        System.out.println("Leaving the program... I hope I helped, if you need I'll be here!(●'◡'●)");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (choice != 5);

            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void allProducts(Sheet sheet) {
        int rowCount = sheet.getLastRowNum();

        System.out.println("All products:");

        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);

            int columnCount = row.getLastCellNum();

            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                String cellValue = cell.getStringCellValue();
                System.out.print(cellValue + "\t");
            }

            System.out.println();
        }
    }

    public static void productsNames(Sheet sheet) {
        int rowCount = sheet.getLastRowNum();

        System.out.println("Products names:");

        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);

            Cell cell = row.getCell(0);

            String cellValue = cell.getStringCellValue();
            System.out.println(cellValue);
        }
    }

    public static void allPrices(Sheet sheet) {
        int rowCount = sheet.getLastRowNum();

        System.out.println("All prices:");

        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);

            int columnCount = row.getLastCellNum();

            Cell cell = row.getCell(columnCount - 1);

            String cellValue = cell.getStringCellValue();
            System.out.println(cellValue);
        }
    }

    public static void alphabeticOrder(Sheet sheet) {
        int rowCount = sheet.getLastRowNum();

        List<String> names = new ArrayList<>();

        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);

            Cell cell = row.getCell(0);

            String cellValue = cell.getStringCellValue();
            names.add(cellValue);
        }

        Collections.sort(names); // Ordena os nomes em ordem alfabética

        System.out.println("Names in alphabetical order:");

        for (String name : names) {
            System.out.println(name);
        }
    }
}


