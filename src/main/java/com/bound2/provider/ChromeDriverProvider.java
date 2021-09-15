package com.bound2.provider;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
public class ChromeDriverProvider implements WebDriverProvider {

    private final Semaphore webDriverSemaphore;

    public ChromeDriverProvider(Semaphore webDriverSemaphore) {
        this.webDriverSemaphore = webDriverSemaphore;
    }

    @Override
    public RemoteWebDriver acquire() {
        try {
            webDriverSemaphore.acquire();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu");
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void release(RemoteWebDriver driver) {
        try {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        } finally {
            webDriverSemaphore.release();
        }
    }
}
