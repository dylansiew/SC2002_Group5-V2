package Camp;

import java.io.Serializable;

public class CampVisibility implements Serializable {
    private boolean visibility;

    public CampVisibility(boolean visible) {
        this.visibility = visible;
    }

    public boolean getVisbility() {
        return this.visibility;
    }

    public void setVisibility(boolean visible) {
        if (visible == this.visibility) {
            System.out.printf("Camp is already %s%n", visible ? "visible" : "invisible");
        } else {
            this.visibility = visible;
        }
        return;
    }
}
