package hu.zolkasza.hw.model;

public class AbstractModel {

    protected String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (id != null) {
            return id;
        }
        return super.toString();
    }

}
