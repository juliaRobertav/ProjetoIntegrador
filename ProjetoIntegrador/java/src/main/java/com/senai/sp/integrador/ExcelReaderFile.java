package com.senai.sp.integrador;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderFile {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\54353123882\\Desktop/products.xlsx"); // Insira o caminho do arquivo Excel existente aqui

            // Cria um objeto Workbook para representar o arquivo Excel
            // XSSFWorkbook é usado para arquivos no formato XLSX
            Workbook workbook = new XSSFWorkbook(fis);

            // Obtém a primeira planilha do arquivo
            Sheet sheet = workbook.getSheetAt(0);

            // Obtém o número total de linhas na planilha
            int rowCount = sheet.getLastRowNum();

            // Loop pelas linhas da planilha
            for (int i = 0; i <= rowCount; i++) {
                // Obtém a linha atual
                Row row = sheet.getRow(i);

                // Obtém o número total de células (colunas) na linha
                int columnCount = row.getLastCellNum();

                // Loop pelas células (colunas) da linha
                for (int j = 0; j < columnCount; j++) {
                    // Obtém a célula atual
                    Cell cell = row.getCell(j);

                    // Obtém o valor da célula como uma string
                    String cellValue = cell.getStringCellValue();

                    // Imprime o valor da célula na saída padrão
                    System.out.print(cellValue + "\t");
                }

                // Imprime uma nova linha após imprimir todas as células da linha atual
                System.out.println();
            }

            // Fecha o objeto Workbook para liberar recursos
            workbook.close();

            // Fecha o objeto FileInputStream para liberar recursos
            fis.close();
        } catch (IOException e) {
            // Em caso de exceção durante a leitura ou fechamento de arquivos,
            // imprime a pilha de chamadas do erro no console
            e.printStackTrace();
        }
    }
}

