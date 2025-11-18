package hu.zolkasza.hw.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {

    private static final Logger logger = LogManager.getLogger(ConfigurationLoader.class);
    private static final String CONFIG_FILE_NAME = "config.properties";
    private final Properties properties;

    public ConfigurationLoader() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            if (inputStream == null) {
                logger.error("Configuration file not found: {}", CONFIG_FILE_NAME);
                throw new IllegalStateException("Required file not found: " + CONFIG_FILE_NAME);
            }
            logger.info("Configuration file '{}' loaded successfully.", CONFIG_FILE_NAME);
            properties.load(inputStream);
        } catch (IOException ex) {
            logger.error("Error loading configuration file: {}", CONFIG_FILE_NAME, ex);
            throw new IllegalStateException("Error loading configuration file: " + CONFIG_FILE_NAME, ex);
        }
    }

    public String getProperty(String key) {
        String overrideValue = System.getProperty(key);
        if (overrideValue != null) {
            logger.debug("Configuration for '{}' was overridden by System Property. Value: '{}'", key, overrideValue);
            return overrideValue;
        }
        String defaultValue = properties.getProperty(key);
        if (defaultValue == null) {
            logger.error("Configuration key '{}' not found in config.properties or System Properties.", key);
            throw new IllegalStateException("Missing configuration key: " + key);
        }
        return defaultValue;
    }

    public String getSauceUiUrl() {
        return getProperty("sauce.ui.url");
    }

    public String getGuruUiUrl() {
        return getProperty("guru.ui.url");
    }

    public String getHtmlEditorUiUrl() {
        return getProperty("htmleditor.ui.url");
    }

    public String getApiUrl() {
        return getProperty("jsonplaceholder.api.url");
    }

    public boolean isHeadless() {
        String value = getProperty("browser.headless");
        boolean isHeadless = Boolean.parseBoolean(value);
        logger.debug("Configuration: browser.headless = {}", isHeadless);
        return isHeadless;
    }

    public int getTimeoutSeconds() {
        return Integer.parseInt(getProperty("browser.timeout.seconds"));
    }

}
