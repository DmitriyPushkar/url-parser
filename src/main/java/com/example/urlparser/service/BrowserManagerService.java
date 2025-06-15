package com.example.urlparser.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManagerService {

    public static WebDriver startBrowser() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-extensions", "--disable-gpu");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

        return new ChromeDriver(options);
    }

    public static void stopBrowser(WebDriver driver) {
        if (driver != null) driver.quit();
    }
}