package com.bound2.provider;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.Semaphore;

@Component
public class ChromeDriverProvider implements WebDriverProvider<ChromeDriver>{

    private final Semaphore webDriverSemaphore;

    public ChromeDriverProvider(Semaphore webDriverSemaphore) {
        this.webDriverSemaphore = webDriverSemaphore;
    }

    @Override
    public ChromeDriver acquire() {
        try {
            webDriverSemaphore.acquire();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        return new ChromeDriver(options);
    }

    @Override
    public void release(ChromeDriver driver) {
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
