package com.example.urlparser;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PageCapturer {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static void capturePage(WebDriver driver, String url, String brandName, String outputDir) {
        try {
            driver.get(url);

            Thread.sleep(3000);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File brandFolder = new File(outputDir + File.separator + brandName);
            if (!brandFolder.exists()) {
                brandFolder.mkdirs();
            }

            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
            String sanitizedUrl = url.replaceAll("[^a-zA-Z0-9]", "_");
            String shortUrl = sanitizedUrl.length() > 50 ? sanitizedUrl.substring(0, 50) : sanitizedUrl;

            File destFile = new File(brandFolder, brandName + "_" + timestamp + "_" + shortUrl + ".png");

            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());

        } catch (InterruptedException | IOException e) {
            System.err.println("Error processing URL: " + url);
            e.printStackTrace();
        }
    }
}
