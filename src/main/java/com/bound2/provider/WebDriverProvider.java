package com.bound2.provider;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverProvider {

    RemoteWebDriver acquire();

    void release(RemoteWebDriver driver);
}
