package Menu.CommentHandler;

import Comment.Comment;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.IReplying;
import Program.MainProgram;
import Users.*;
import Camp.Camp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StaffReplyEnquiry implements IReplying {
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
