package com.example.urlparser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<UrlBrandPair> readExcel(String filePath) {
        List<UrlBrandPair> urlBrandPairs = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row == null) continue;

                Cell urlCell = row.getCell(2);  // Column C
                Cell brandCell = row.getCell(4); // Column E

                if (urlCell == null || brandCell == null) continue;

                String url = urlCell.getStringCellValue().trim();
                String brand = brandCell.getStringCellValue().trim();

                if (!url.isEmpty() && !brand.isEmpty()) {
                    urlBrandPairs.add(new UrlBrandPair(url, brand));
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }

        return urlBrandPairs;
    }
}