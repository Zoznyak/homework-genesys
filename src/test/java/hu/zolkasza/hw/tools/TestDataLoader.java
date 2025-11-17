package hu.zolkasza.hw.tools;

import hu.zolkasza.hw.model.AbstractModel;
import hu.zolkasza.hw.model.ui.sauce.SauceLabCustomer;
import hu.zolkasza.hw.model.ui.sauce.SauceLabUser;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class TestDataLoader {

    private static final Logger logger = LogManager.getLogger(TestDataLoader.class);
    private JsonProcessor jsonProcessor;

    public SauceLabUser loadSauceLabUser(String fileName) throws IOException {
        return loadTestData(fileName, SauceLabUser.class);
    }

    public SauceLabCustomer loadSauceLabCustomer(String fileName) throws IOException {
        return loadTestData(fileName, SauceLabCustomer.class);
    }

    private <T extends AbstractModel> T loadTestData(String fileName, Class<T> clazz) throws IOException {
        T data = null;
        try {
            String content = readFileAsString("/testdata/" + fileName);
            data = jsonProcessor().fromJson(content, clazz);
        } catch (IOException ex) {
            logger.error("Failed to read test data file '{}' from /testdata/ resource path: {}", fileName, ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error while loading data from file '{}': {}", fileName, ex.getMessage(), ex);
            throw new IOException("Failed to load data from file: " + fileName, ex);
        }
        if (data != null) {
            data.setId(fileName);
        }
        return data;
    }

    private String readFileAsString(String file) throws IOException {
        return IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream(file)), "UTF-8");
    }

    private JsonProcessor jsonProcessor() {
        if (this.jsonProcessor == null) {
            this.jsonProcessor = new JsonProcessor();
        }
        return this.jsonProcessor;
    }

}
