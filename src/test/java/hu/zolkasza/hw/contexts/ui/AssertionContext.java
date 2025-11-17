package hu.zolkasza.hw.contexts.ui;

import org.assertj.core.api.SoftAssertions;

public class AssertionContext {

    private final SoftAssertions softAssertions;

    public AssertionContext() {
        this.softAssertions = new SoftAssertions();
    }

    public SoftAssertions get() {
        return this.softAssertions;
    }

    public void assertAll() {
        this.softAssertions.assertAll();
    }

}
