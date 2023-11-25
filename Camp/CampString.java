package Camp;

import java.io.Serializable;

public class CampString implements Serializable {
    protected static enum TYPE {
        NAME, LOCATION, DESCRIPTION
    };

    private String data;
    private TYPE type;

    public CampString(String newData, TYPE newType) {
        this.data = newData;
        this.type = newType;
    }

    public void setString(String newData) {
        if (newData == null) {
            return;
        }
        data = newData;
    }

    public String getString() {
        return this.data;
    }

    public String toString() {
        return type.toString() + ": " + this.data;
    }
}
