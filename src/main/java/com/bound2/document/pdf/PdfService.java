package com.bound2.document.pdf;

import com.bound2.provider.WebDriverProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.print.PrintOptions;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class PdfService {

    private final WebDriverProvider<ChromeDriver> webDriverProvider;

    public PdfService(WebDriverProvider<ChromeDriver> webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    public byte[] generate(String base64Html) {
        var driver = webDriverProvider.acquire();
        try {
            driver.get("data:text/html;base64,%s".formatted(base64Html));

            var printOptions = new PrintOptions();
            printOptions.setOrientation(PrintOptions.Orientation.PORTRAIT);
            var result = driver.print(printOptions);

            return Base64.getDecoder().decode(result.getContent());
        } finally {
            webDriverProvider.release(driver);
        }
    }
}
