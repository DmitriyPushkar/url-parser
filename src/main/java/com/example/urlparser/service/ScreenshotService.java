package com.example.urlparser.service;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotService {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static void capturePage(WebDriver driver, String url, String brandName, String outputDir) throws IOException, InterruptedException {
        driver.get(url);
        Thread.sleep(3000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File brandDir = new File(outputDir, brandName);
        if (!brandDir.exists()) brandDir.mkdirs();

        String timestamp = LocalDateTime.now().format(FORMAT);
        String safeUrl = url.replaceAll("[^a-zA-Z0-9]", "_");
        String shortName = safeUrl.length() > 50 ? safeUrl.substring(0, 50) : safeUrl;

        File destination = new File(brandDir, brandName + "_" + timestamp + "_" + shortName + ".png");

        FileUtils.copyFile(screenshot, destination);
    }
}