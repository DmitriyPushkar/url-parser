package com.example.urlparser.service;

import com.example.urlparser.model.UrlBrandPairModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderService {

    public List<UrlBrandPairModel> read(String path, int urlCol, int brandCol) {
        List<UrlBrandPairModel> result = new ArrayList<>();

        try (Workbook wb = new XSSFWorkbook(new FileInputStream(path))) {
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell urlCell = row.getCell(urlCol);
                Cell brandCell = row.getCell(brandCol);

                if (urlCell == null || brandCell == null) continue;

                String url = urlCell.getStringCellValue().trim();
                String brand = brandCell.getStringCellValue().trim();

                if (!url.isEmpty() && !brand.isEmpty()) {
                    result.add(new UrlBrandPairModel(url, brand));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }

        return result;
    }
}