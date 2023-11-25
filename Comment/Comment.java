package Comment;

import java.io.Serializable;
import Camp.*;
import Users.*;

public abstract class Comment implements Serializable {
    int commentId;
    Student student;
    Camp camp;
    String comment;
    Boolean processed = false;

    /**
     * Edits the comment.
     * @param comment The new comment text.
     */
    public void editComment(String comment) {
        this.comment = comment;
    }
    /**
     * Gets the processing status of the comment.
     * @return True if the comment is processed, false otherwise.
     */
    public boolean getProcessed() {
        return this.processed;
    }
    /**
     * Provides a human-readable string representation of the Comment object.
     * @return A string containing information about the comment.
     */
    public String toString() {
        CampInformation campInformation = camp.getCampInformation();
        if (campInformation == null) {
            return null;
        }
        return "Created by:" + student.getName() + " | Camp: " + campInformation.getName() + " | Comment: "
                + this.comment + "\n";
    }
    /**
     * Gets the student who made this comment.
     * @return The Student object representing the comment author.
     */
    public Student getStudent() {
        return this.student;
    }
}
