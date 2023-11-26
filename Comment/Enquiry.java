package Comment;

import java.util.ArrayList;
import Camp.*;
import Users.*;
/**
 * The `Enquiry` class represents an enquiry comment made by a student.
 * It is a subclass of Comment
 */
public class Enquiry extends Comment {
    static int nextEnquiryId = 1;
    ArrayList<String> replies = new ArrayList<>();
    /**
     * Constructs an `Enquiry` object with the provided student, camp, and initial comment.
     *
     * @param student The student who created the enquiry comment.
     * @param camp The camp associated with the enquiry comment.
     * @param comment The initial enquiry content.
     */
    public Enquiry(Student student, Camp camp, String comment) {
        this.comment = comment;
        this.student = student;
        this.camp = camp;
        this.commentId = Enquiry.nextEnquiryId;
        Enquiry.nextEnquiryId += 1;
    }
    /**
     * Adds a reply to the enquiry comment.
     *
     * @param reply The reply to be added.
     */
    public void addReply(String reply) {
        replies.add(reply);
        this.processed = true;
    }
    /**
     * Retrieves the list of replies associated with the enquiry comment.
     *
     * @return An ArrayList of replies.
     */
    public ArrayList<String> getReplies() {
        return this.replies;
    }
    /*** Provide human-readable string representation of the Enquiry's object */
    public String toString() {
        int i = 1;
        String info = "";
        info += "Enquiry " + this.commentId + ":\n";
        info += super.toString();
        info += "\nReplies (" + replies.size() + "):\n";
        for(String reply : replies){
            info += i + ":\t" + reply + "\n";
            i += 1;
        }
        return info;
    }
}
