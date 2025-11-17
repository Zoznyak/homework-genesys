package hu.zolkasza.hw.contexts.ui;

import org.openqa.selenium.WebDriver;

public class UiContext {

    private WebDriver driver;
    private String originalTabHandle;

    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver in UiContext is not set!");
        }
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setOriginalTabHandle(String handle) {
        this.originalTabHandle = handle;
    }

    public String getOriginalTabHandle() {
        if (originalTabHandle == null) {
            throw new IllegalStateException("Original tab handle was never set in context!");
        }
        return originalTabHandle;
    }

}
