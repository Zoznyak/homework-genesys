package hu.zolkasza.hw.contexts.ui;

import org.openqa.selenium.WebDriver;

public class UiContext {

    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver in UiContext is not set!");
        }
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

}
