package Menu.CommentHandler;

import Comment.Comment;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.IReplying;
import Program.MainProgram;
import Users.*;
import Camp.Camp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The `StaffReplyEnquiry` class implements the `IReplying` interface to provide functionality for staff members
 * to reply to enquiries within a camp's comment section.
 */
public class StaffReplyEnquiry implements IReplying {
    /**
     * Allows a staff member to reply to an enquiry within a camp's comment section.
     * @param user    The staff member who is replying to the enquiry.
     * @param comment The enquiry comment being replied to.
     * @param camp    The camp associated with the comment.
     */
    public void replyComment(User user, Comment comment, Camp camp) {
        Staff staff = (Staff) user;
        Enquiry selectedEnquiry = (Enquiry) comment;
        System.out.printf("Enter reply: ");
        String replyInput = MainProgram.sc.nextLine();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String formattedReply = staff.getName() + " (" + formattedDateTime + ") : " + replyInput;
        selectedEnquiry.addReply(formattedReply);
        return;
    }
}
