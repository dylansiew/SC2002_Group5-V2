package Camp;

import java.io.Serializable;
/**
 * The `CampVisibility` class represents the visibility status of a camp to students.
 */
public class CampVisibility implements Serializable {
    private boolean visibility;
    /**
     * Constructs a new `CampVisibility` instance with the specified visibility status.
     * @param visible `true` if the camp is visible, `false` if it is invisible.
     */
    public CampVisibility(boolean visible) {
        this.visibility = visible;
    }
    /**
     * Gets the current visibility status of the camp.
     * @return `true` if the camp is visible, `false` if it is invisible.
     */
    public boolean getVisbility() {
        return this.visibility;
    }
    /**
     * Sets the visibility status of the camp.
     * @param visible `true` to make the camp visible, `false` to make it invisible.
     */
    public void setVisibility(boolean visible) {
        if (visible == this.visibility) {
            System.out.printf("Camp is already %s%n", visible ? "visible" : "invisible");
        } else {
            this.visibility = visible;
        }
        return;
    }
}
