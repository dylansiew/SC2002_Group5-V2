package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.Faculty;
import Users.Faculty.FACULTY_TYPE;

public class CampFaculty implements Serializable {
    private ArrayList<Faculty.FACULTY_TYPE> faculty;

    public CampFaculty(ArrayList<Faculty.FACULTY_TYPE> faculty) {
        this.faculty = faculty;
    }

    public ArrayList<Faculty.FACULTY_TYPE> getFaculty() {
        return faculty;
    }

    public boolean addFaculty(Faculty.FACULTY_TYPE newFaculty) {
        if (newFaculty == null) {
            return false;
        } else if ((this.faculty.contains(newFaculty) || this.faculty.contains(FACULTY_TYPE.UNIVERSE))) {
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

    public boolean removeFaculty(Faculty.FACULTY_TYPE newFaculty) {
        if (newFaculty == null || !(this.faculty.contains(newFaculty))) {
            return false;
        } else {
            this.faculty.remove(newFaculty);
            return true;
        }
    }

    public String toString() {
        String info = "Faculties (" + faculty.size() + "):\n";
        int i = 1;
        for (Faculty.FACULTY_TYPE type : faculty) {
            info += (i + ". " + type + "\n");
            i += 1;
        }
        return info;
    }
}
