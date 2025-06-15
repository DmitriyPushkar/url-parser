package com.example.urlparser.service;

import com.example.urlparser.model.UrlBrandPairModel;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ProcessingService {

    public void process(List<UrlBrandPairModel> urlBrandPairModels, String outputDir) {
        long start = System.currentTimeMillis();
        if (urlBrandPairModels.isEmpty()) return;

        File outputRoot = new File(outputDir);
        if (!outputRoot.exists()) outputRoot.mkdirs();

        WebDriver driver = BrowserManagerService.startBrowser();

        for (UrlBrandPairModel pair : urlBrandPairModels) {
            try {
                ScreenshotService.capturePage(driver, pair.getUrl(), pair.getBrand(), outputDir);
                convertLastPngToPdf(outputDir, pair.getBrand());
            } catch (Exception e) {
                System.err.println("Error processing URL: " + pair.getUrl());
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        double seconds = (end - start) / 1000.0;
        System.out.println("⏱️ Processed " + urlBrandPairModels.size() + " pages in " + seconds + " seconds");
        System.out.println("📈 Average speed: " + (urlBrandPairModels.size() / seconds * 60) + " pages/min");
        BrowserManagerService.stopBrowser(driver);
    }

    private void convertLastPngToPdf(String baseDir, String brand) {
        File brandFolder = new File(baseDir, brand);
        File[] pngFiles = brandFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        if (pngFiles != null && pngFiles.length > 0) {
            File latest = Arrays.stream(pngFiles)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);

            if (latest != null) {
                File pdf = new File(brandFolder, latest.getName().replace(".png", ".pdf"));
                PdfConverterService.convertPngToPdf(latest, pdf);
                latest.delete();
            }
        }
    }
}