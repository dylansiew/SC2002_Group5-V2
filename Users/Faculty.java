package Users;

import java.io.Serializable;
/**
 * The `Faculty` class represents the various faculties at a university.
 * It provides methods to get a faculty type based on faculty name or user choice.
 */
public class Faculty implements Serializable {
    /**
     * All the faculties in NTU, each student only associated to one Faculty.
     * If a camp's faculty is set to UNIVERSE, meaning that the camp is open for students from any of the faculties.
     */
    public static enum FACULTY_TYPE {
        SCSE, EEE, SSS, NBS, CCEB, CEE, MSE, MAE, ADM, SOH, WKWSCI, SBS, SPMS, ASE, LKC, NIE, UNIVERSE
    };
    /**
     * Gets the faculty type based on a given faculty name.
     * @param facultyName The name of the faculty.
     * @return The corresponding faculty type, or null if the name is unrecognized.
     */
    public static FACULTY_TYPE getFacultyType(String facultyName) {
        try {
            return FACULTY_TYPE.valueOf(facultyName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unrecognised faculty... Try again");
            return null;
        }
    }
    /**
     * Gets the faculty type based on a user's choice (integer).
     * @param choice The user's choice (integer) representing a faculty.
     * @return The corresponding faculty type, or null if the choice is invalid.
     */
    public static Faculty.FACULTY_TYPE getFacultyFromChoice(int choice) {
        switch (choice) {
            case 1:
                return Faculty.FACULTY_TYPE.SCSE;
            case 2:
                return Faculty.FACULTY_TYPE.EEE;
            case 3:
                return Faculty.FACULTY_TYPE.SSS;
            case 4:
                return Faculty.FACULTY_TYPE.NBS;
            case 5:
                return Faculty.FACULTY_TYPE.CCEB;
            case 6:
                return Faculty.FACULTY_TYPE.CEE;
            case 7:
                return Faculty.FACULTY_TYPE.MSE;
            case 8:
                return Faculty.FACULTY_TYPE.MAE;
            case 9:
                return Faculty.FACULTY_TYPE.ADM;
            case 10:
                return Faculty.FACULTY_TYPE.SOH;
            case 11:
                return Faculty.FACULTY_TYPE.WKWSCI;
            case 12:
                return Faculty.FACULTY_TYPE.SBS;
            case 13:
                return Faculty.FACULTY_TYPE.SPMS;
            case 14:
                return Faculty.FACULTY_TYPE.ASE;
            case 15:
                return Faculty.FACULTY_TYPE.LKC;
            case 16:
                return Faculty.FACULTY_TYPE.NIE;
            case 17:
                return Faculty.FACULTY_TYPE.UNIVERSE;
            default:
                return null;

        }
    }

}
