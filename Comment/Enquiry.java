package Comment;

import java.util.ArrayList;
import Camp.*;
import Users.*;

public class Enquiry extends Comment {
    static int nextEnquiryId = 1;
    ArrayList<String> replies = new ArrayList<>();

    public Enquiry(Student student, Camp camp, String comment) {
        this.comment = comment;
        this.student = student;
        this.camp = camp;
        this.commentId = Enquiry.nextEnquiryId;
        Enquiry.nextEnquiryId += 1;
    }

    public void addReply(String reply) {
        replies.add(reply);
        this.processed = true;
    }

    public ArrayList<String> getReplies() {
        return this.replies;
    }

    public String toString() {
        int i = 1;
        String info = "";
        info += "Enquiry " + this.commentId + ":\n";
        info += super.toString();
        info += "\nReplies (" + replies.size() + "):\n";
        for (String reply : replies) {
            info += i + ":\t" + reply + "\n";
            i += 1;
        }
        return info;
    }
}
