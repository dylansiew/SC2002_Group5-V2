package Camp;

import java.io.Serializable;
/**
 * The `CampString` class represents a string with a specified type, such as name, location, or description.
 * It is used to categorize and store strings associated with different aspects of a camp.
 */
public class CampString  implements Serializable{
    /**
     * An enumeration representing the type of the string, such as NAME, LOCATION, or DESCRIPTION.
     */
    protected static enum TYPE {
        NAME, LOCATION, DESCRIPTION
    };

    private String data;
    private TYPE type;
    /**
     * Constructs a new `CampString` instance with the specified string data and type.
     * @param newData The string data to be stored.
     * @param newType The type of the string (e.g., NAME, LOCATION, DESCRIPTION).
     */
    public CampString(String newData, TYPE newType) {
        this.data = newData;
        this.type = newType;
    }
    /**
     * Sets the string data to a new value.
     * @param newData The new string data to be set.
     */
    public void setString(String newData) {
        if (newData == null) {
            return;
        }
        data = newData;
    }
    /**
     * Retrieves the stored string data.
     * @return The stored string data.
     */
    public String getString() {
        return this.data;
    }
    /**
     * Returns a string representation of the `CampString`, including its type and data.
     * @return A string containing the type and data of the `CampString`.
     */
    public String toString() {
        return type.toString() + ": " + this.data;
    }
}
