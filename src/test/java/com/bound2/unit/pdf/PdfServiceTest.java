package com.bound2.unit.pdf;

import com.bound2.document.pdf.PdfService;
import com.bound2.provider.WebDriverProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PdfServiceTest {

    @Mock
    private RemoteWebDriver webDriver;

    @Mock
    private WebDriverProvider webDriverProvider;

    @InjectMocks
    private PdfService pdfService;

    @Test
    public void testGenerate() {
        // given
        var base64html = "dGVzdA==";
        var printBase64 = "cHJpbnRtZQ==";
        when(webDriverProvider.acquire()).thenReturn(webDriver);
        when(webDriver.print(any())).thenReturn(new Pdf(printBase64));

        // when
        var result = pdfService.generate(base64html);

        // then
        assertThat(result).isEqualTo(Base64.getDecoder().decode(printBase64));
        verify(webDriverProvider).acquire();
        verify(webDriverProvider).release(webDriver);
        // separator
        verify(webDriver).print(any(PrintOptions.class));
        verify(webDriver).get(eq("data:text/html;base64,%s".formatted(base64html)));
    }
}
