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

    public void editComment(String comment) {
        this.comment = comment;
    }

    public boolean getProcessed() {
        return this.processed;
    }

    public String toString() {
        CampInformation campInformation = camp.getCampInformation();
        if (campInformation == null) {
            return null;
        }
        return "Created by:" + student.getName() + " | Camp: " + campInformation.getName() + " | Comment: "
                + this.comment + "\n";
    }

    public Student getStudent() {
        return this.student;
    }
}
