package hu.zolkasza.hw.hooks;

import hu.zolkasza.hw.contexts.ui.SauceContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class WebDriverHooks {

    private SauceContext sauceContext;

    public WebDriverHooks(SauceContext context) {
        this.sauceContext = context;
    }

    @Before("@ui")
    public void setUp() {
        // TODO other browsers
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection_enabled", false);
//        prefs.put("safebrowsing.enabled", true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--guest");
//        options.addArguments("--password-store=basic");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        sauceContext.setDriver(new ChromeDriver(options));
    }

    @After("@ui")
    public void tearDown() {
        if (sauceContext.getDriver() != null) {
            sauceContext.getDriver().quit();
        }
    }

}
