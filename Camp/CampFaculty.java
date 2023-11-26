package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.Faculty;
import Users.Faculty.FACULTY_TYPE;
/**
 * The `CampFaculty` class represents the faculties associated with a camp.
 * It stores a list of faculty types (e.g., SCSE, ADM) that are part of the camp's faculties.
 */
public class CampFaculty  implements Serializable{
    private ArrayList<Faculty.FACULTY_TYPE> faculty;
    /**
     * Constructs a new `CampFaculty` instance with the specified list of faculty types.
     * @param faculty The list of faculty types associated with the camp.
     */
    public CampFaculty(ArrayList<Faculty.FACULTY_TYPE> faculty) {
        this.faculty = faculty;
    }
    /**
     * Retrieves the list of faculty types associated with the camp.
     * @return The list of faculty types.
     */
    public ArrayList<Faculty.FACULTY_TYPE> getFaculty() {
        return faculty;
    }
    /**
     * Adds a new faculty type to the list of faculties if it is not already present.
     * @param newFaculty The new faculty type to add.
     * @return `true` if the faculty type was added successfully, `false` if it already exists or is invalid.
     */
    public boolean addFaculty(Faculty.FACULTY_TYPE newFaculty) {
        if (newFaculty == null) {
            return false;
        }
        else if ((this.faculty.contains(newFaculty) || this.faculty.contains(FACULTY_TYPE.UNIVERSE))) {
            System.out.println(newFaculty + "is already in the faculty list!");
            return true;
        }


        if (newFaculty == FACULTY_TYPE.UNIVERSE) {
            this.faculty.clear();
            this.faculty.add(newFaculty);
        } else {
            this.faculty.add(newFaculty);
        }
        return true;
    }
    /**
     * Removes a faculty type from the list of faculties if it exists.
     * @param newFaculty The faculty type to remove.
     * @return `true` if the faculty type was removed successfully, `false` if it does not exist.
     */
    public boolean removeFaculty(Faculty.FACULTY_TYPE newFaculty){
        if(newFaculty == null || !(this.faculty.contains(newFaculty))){
            return false;
        }
        else{
            this.faculty.remove(newFaculty);
            return true;
        }
    }
    /**
     * Returns a string representation of the camp's faculties.
     * @return A string containing the list of faculties.
     */
    public String toString(){
        String info = "Faculties (" + faculty.size() + "):\n";
        int i = 1;
        for(Faculty.FACULTY_TYPE type : faculty){
            info += (i + ". " + type + "\n");
            i += 1;
        }
        return info;
    }
}
