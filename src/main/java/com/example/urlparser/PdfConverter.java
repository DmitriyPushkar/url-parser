package com.example.urlparser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class PdfConverter {

    public static void convertPngToPdf(File imageFile, File pdfFile) {
        try (PDDocument document = new PDDocument()) {
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);

            PDRectangle rectangle = new PDRectangle(pdImage.getWidth(), pdImage.getHeight());
            PDPage page = new PDPage(rectangle);
            document.addPage(page);

            try (PDPageContentStream contents = new PDPageContentStream(document, page)) {
                contents.drawImage(pdImage, 0, 0);
            }

            document.save(pdfFile);

            System.out.println("PDF created: " + pdfFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error converting PNG to PDF: " + imageFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
