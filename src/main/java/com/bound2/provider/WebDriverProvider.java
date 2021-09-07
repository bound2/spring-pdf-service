package com.bound2.provider;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverProvider<T extends RemoteWebDriver> {

    T acquire();

    void release(T driver);
}
