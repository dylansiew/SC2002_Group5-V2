package Users;

import java.io.Serializable;
import Camp.*;
/**
 * The `Committee` class represents a committee member in a camp, associated with a specific camp and student.
 */
public class Committee implements Serializable {
    private Camp camp;
    private Student student;
    private int points = 0;
    /**
     * Constructs a new `Committee` instance associated with the given camp and student.
     * @param camp    The camp that the committee member is associated with.
     * @param student The student who is a committee member.
     */
    public Committee(Camp camp, Student student) {
        this.camp = camp;
        this.student = student;
    }
    /**
     * Awards one point to the committee member and prints a message.
     */
    public void addPoint() {
        this.points++;
        System.out.printf("You have been awarded one point\nYour points: %d\n", getPoints());
    }
    /**
     * Deducts one point from the committee member and prints a message.
     */
    public void minusPoint() {
        this.points--;
        System.out.printf("One point has been deducted\nYour points: %d\n", getPoints());
    }
    /**
     * Gets the current points of the committee member.
     *
     * @return The current points.
     */
    public int getPoints() {
        return this.points;
    }
    /**
     * Gets the camp associated with the committee member.
     * @return The associated camp.
     */
    public Camp getCamp() {
        return this.camp;
    }
    /**
     * Gets the student who is a committee member.
     * @return The student.
     */
    public Student getStudent() {
        return this.student;
    }

}
