package hu.zolkasza.hw.hooks;

import hu.zolkasza.hw.contexts.ui.AssertionContext;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssertionHooks {

    private static final Logger logger = LogManager.getLogger(AssertionHooks.class);
    private final AssertionContext assertionContext;

    public AssertionHooks(AssertionContext assertionContext) {
        this.assertionContext = assertionContext;
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        try {
            assertionContext.assertAll();
        } catch (AssertionError ex) {
            logger.warn("Soft assertions failed for scenario: {}", scenario.getName(), ex);
            throw ex;
        }
    }

}
