package hu.zolkasza.hw.hooks;

import hu.zolkasza.hw.contexts.ui.UiContext;
import hu.zolkasza.hw.tools.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class WebDriverHooks {

    private final UiContext context;
    private final Configuration config;

    public WebDriverHooks(UiContext context, Configuration config) {
        this.context = context;
        this.config = config;
    }

    @Before("@ui")
    public void setUp() {
        // TODO other browsers
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection_enabled", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--guest");
        options.addArguments("--disable-notifications");
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        } else {
            options.addArguments("--start-maximized");
        }
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        context.setDriver(new ChromeDriver(options));
    }

    @After(value = "@ui", order = 2)
    public void tearDown() {
        if (context.getDriver() != null) {
            context.getDriver().quit();
        }
    }

}
