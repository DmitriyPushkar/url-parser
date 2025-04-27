package com.example.urlparser;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainRunner {

    private static final String EXCEL_PATH = "C:\\Users\\Dmitrii\\Downloads\\test_amazon_urls_final.xlsx";
    private static final String OUTPUT_DIR = "C:\\Users\\Dmitrii\\Downloads\\inf_sellers";

    public static void main(String[] args) {

        List<UrlBrandPair> urlBrandPairs = ExcelReader.readExcel(EXCEL_PATH);
        if (urlBrandPairs.isEmpty()) {
            System.err.println("Excel is empty or not found. Execution stopped.");
            return;
        }

        File outputRoot = new File(OUTPUT_DIR);
        if (!outputRoot.exists()) {
            outputRoot.mkdirs();
        }

        WebDriver driver = BrowserManager.startBrowser();

        for (UrlBrandPair pair : urlBrandPairs) {
            try {
                System.out.println("Processing: " + pair.getUrl());

                PageCapturer.capturePage(driver, pair.getUrl(), pair.getBrand(), OUTPUT_DIR);

                File brandFolder = new File(OUTPUT_DIR + File.separator + pair.getBrand());
                File[] pngFiles = brandFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

                if (pngFiles != null && pngFiles.length > 0) {
                    File lastPng = Arrays.stream(pngFiles)
                            .max(Comparator.comparingLong(File::lastModified))
                            .orElse(null);

                    if (lastPng != null) {
                        String pdfName = lastPng.getName().replace(".png", ".pdf");
                        File pdfFile = new File(brandFolder, pdfName);

                        PdfConverter.convertPngToPdf(lastPng, pdfFile);

                        if (lastPng.exists()) {
                            lastPng.delete();
                        }
                    }
                }

            } catch (Exception e) {
                System.err.println("Error processing URL: " + pair.getUrl());
                e.printStackTrace();
            }
        }

        BrowserManager.stopBrowser(driver);

        System.out.println("All done! Files saved in " + OUTPUT_DIR);
    }
}