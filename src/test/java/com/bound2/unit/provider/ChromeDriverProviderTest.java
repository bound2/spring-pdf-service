package com.bound2.unit.provider;

import com.bound2.provider.ChromeDriverProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.Semaphore;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChromeDriverProviderTest {

    @Mock
    private Semaphore webDriverSemaphore;

    @InjectMocks
    private ChromeDriverProvider chromeDriverProvider;

    @Test
    public void testAcquireAndRelease() throws Exception {
        // when
        var driver = chromeDriverProvider.acquire();
        chromeDriverProvider.release(driver);

        // then
        verify(webDriverSemaphore).acquire();
        verify(webDriverSemaphore).release();
        assertThat(driver).isInstanceOf(ChromeDriver.class);
    }
}
