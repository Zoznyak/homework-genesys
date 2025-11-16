package hu.zolkasza.hw.contexts.ui;

public class SauceContext extends UiContext{

    private int itemsAddedToCart = 0;

    public int getItemsAddedToCart() {
        return itemsAddedToCart;
    }

    public void setItemsAddedToCart(int itemsAddedToCart) {
        this.itemsAddedToCart = itemsAddedToCart;
    }

}
