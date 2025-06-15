package com.example.urlparser.service;

import com.example.urlparser.model.UrlBrandPairModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class UploadService {

    private final ExcelReaderService excelReaderService;
    private final ProcessingService processingService;

    public UploadService(ExcelReaderService excelReaderService, ProcessingService processingService) {
        this.excelReaderService = excelReaderService;
        this.processingService = processingService;
    }

    public int process(MultipartFile file, String outputDir, String urlColumn, String brandColumn) throws Exception {
        int urlIndex = columnToIndex(urlColumn);
        int brandIndex = columnToIndex(brandColumn);

        File tempFile = File.createTempFile("upload_", ".xlsx");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        List<UrlBrandPairModel> pairs = excelReaderService.read(tempFile.getAbsolutePath(), urlIndex, brandIndex);

        if (pairs.isEmpty()) {
            throw new IllegalArgumentException("Excel file is empty or has no valid data.");
        }

        processingService.process(pairs, outputDir);
        return pairs.size();
    }

    private int columnToIndex(String col) {
        return Character.toUpperCase(col.trim().charAt(0)) - 'A';
    }
}
