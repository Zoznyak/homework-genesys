package hu.zolkasza.hw.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TextResourceLoader {

    private static final Logger logger = LogManager.getLogger(TextResourceLoader.class);
    private static final String PROPERTIES_NAME = "texts.properties";
    private final Properties properties;

    public TextResourceLoader() {
       this.properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME)) {
            if (inputStream == null) {
                logger.error("Text resource file not found: {}", PROPERTIES_NAME);
                throw new IllegalStateException("Required file not found: " + PROPERTIES_NAME);
            }
            logger.info("Configuration file '{}' loaded successfully.", PROPERTIES_NAME);
            properties.load(inputStream);
        } catch (IOException ex) {
            logger.error("Error loading configuration file: {}", PROPERTIES_NAME, ex);
            throw new IllegalStateException("Error loading configuration file: " + PROPERTIES_NAME, ex);
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.error("Configuration key '{}' not found in " + PROPERTIES_NAME + ".", key);
            throw new IllegalStateException("Missing configuration key: " + key);
        }
        return value;
    }
}
