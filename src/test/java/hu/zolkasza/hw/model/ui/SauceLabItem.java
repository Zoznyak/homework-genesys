package hu.zolkasza.hw.model.ui;

public enum SauceLabItem {

    BACKPACK("backpack"),
    JACKET("fleece-jacket");

    private final String selectorName;

    SauceLabItem(String selectorName) {
        this.selectorName = selectorName;
    }

    public String getSelectorName() {
        return selectorName;
    }
}
