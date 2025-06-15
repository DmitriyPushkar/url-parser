package com.example.urlparser.service;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class PdfConverterService {

    public static void convertPngToPdf(File image, File output) {
        try (PDDocument doc = new PDDocument()) {
            PDImageXObject pdImage = PDImageXObject.createFromFile(image.getAbsolutePath(), doc);
            PDPage page = new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));
            doc.addPage(page);

            try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {
                stream.drawImage(pdImage, 0, 0);
            }

            doc.save(output);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert image to PDF", e);
        }
    }
}