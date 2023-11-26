package Comment;

import Camp.*;
import Users.*;

/**
 * The `Suggestion` class represents a suggestion comment made by a camp comittee.
 * It is a subclass of Comment
 */
public class Suggestion extends Comment {
    static int nextSuggestionId = 1;
    /**
     * The Suggestion's status can be either PENDING, APPROVED, or REJECTED
     */ 
    public enum STATUS {
        APPROVED, REJECTED, PENDING
    }

    private STATUS status;
    /**
     * Constructs a `Suggestion` object with the provided student, camp, and initial comment.
     *
     * @param student The student who created the suggestion comment.
     * @param camp The camp associated with the suggestion comment.
     * @param comment The initial comment content.
     */
    public Suggestion(Student student, Camp camp, String comment) {
        this.student = student;
        this.camp = camp;
        this.comment = comment;
        this.commentId = Suggestion.nextSuggestionId;
        Suggestion.nextSuggestionId += 1;
        status = STATUS.PENDING;
    }
    /**
     * Sets the processed status of the suggestion comment along with its final status (APPROVED, REJECTED, or PENDING).
     *
     * @param status The status of the suggestion.
     */
    public void setProcessed(STATUS status) {
        this.processed = true;
        this.status = status;
    }
    /** 
     * Provide human-readable string representation of the suggestion's object 
    */
    public String toString() {
        String info = "";
        info += "Suggestion " + this.commentId + ":\n";
        info += super.toString();
        info += "Status: " + this.status + "\n";
        return info;

    }
    /** Get the sugesstion status */
    public Suggestion.STATUS getStatus() {
        return this.status;
    }
}
