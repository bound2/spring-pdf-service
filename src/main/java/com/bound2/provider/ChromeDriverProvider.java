package com.bound2.provider;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChromeDriverProvider implements WebDriverProvider<ChromeDriver>{

    @Override
    public ChromeDriver acquire() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        return new ChromeDriver(options);
    }

    @Override
    public void release(ChromeDriver driver) {
        Optional.ofNullable(driver).ifPresent(o -> {
            o.close();
            o.quit();
        });
    }
}
