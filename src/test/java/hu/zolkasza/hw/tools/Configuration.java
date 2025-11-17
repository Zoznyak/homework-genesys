package hu.zolkasza.hw.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private final Properties properties;

    public Configuration() {
        properties = new Properties();
        String configFileName = "config.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (inputStream == null) {
                logger.error("Configuration file not found: {}", configFileName);
                throw new IllegalStateException("Configuration file not found: " + configFileName);
            }
            logger.info("Configuration file '{}' loaded successfully.", configFileName);
            properties.load(inputStream);
        } catch (IOException ex) {
            logger.error("Error loading configuration file: {}", configFileName, ex);
            throw new IllegalStateException("Error loading configuration file: " + configFileName, ex);
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

    public String getApiUrl() {
        return getProperty("jsonplaceholder.api.url");
    }

    public boolean isHeadless() {
        String value = getProperty("browser.headless");
        boolean isHeadless = Boolean.parseBoolean(value);
        logger.debug("Configuration: browser.headless = {}", isHeadless);
        return isHeadless;
    }

    public int getShortTimeoutSeconds() {
        return Integer.parseInt(getProperty("browser.timeout.short.seconds"));
    }

    public int getLongTimeoutSeconds() {
        return Integer.parseInt(getProperty("browser.timeout.long.seconds"));
    }

}
