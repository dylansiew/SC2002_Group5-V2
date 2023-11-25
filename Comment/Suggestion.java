package Comment;

import Camp.*;
import Users.*;

public class Suggestion extends Comment {
    static int nextSuggestionId = 1;

    public enum STATUS {
        APPROVED, REJECTED, PENDING
    }

    private STATUS status;

    public Suggestion(Student student, Camp camp, String comment) {
        this.student = student;
        this.camp = camp;
        this.comment = comment;
        this.commentId = Suggestion.nextSuggestionId;
        Suggestion.nextSuggestionId += 1;
        status = STATUS.PENDING;
    }

    public void setProcessed(STATUS status) {
        this.processed = true;
        this.status = status;
    }

    public String toString() {
        String info = "";
        info += "Suggestion " + this.commentId + ":\n";
        info += super.toString();
        info += "Status: " + this.status + "\n";
        return info;

    }

    public Suggestion.STATUS getStatus() {
        return this.status;
    }
}
