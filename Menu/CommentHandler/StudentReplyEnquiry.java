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
 * The `StudentReplyEnquiry` class implements the `IReplying` interface and allows students to reply to enquiry comments made by other users within a camp.
 * Students can provide replies to enquiries, and their replies will be recorded with a timestamp, the student's name, and the reply text.
 */
public class StudentReplyEnquiry implements IReplying {
    /**
     * Allows a student to reply to an enquiry comment made by another user within a camp.
     * @param user    The student user replying to the enquiry.
     * @param comment The comment (enquiry) to which the student is replying.
     * @param camp    The camp within which the comment is being replied to.
     */
    public void replyComment(User user, Comment comment, Camp camp) {
        Enquiry selectedEnquiry = (Enquiry) comment;
        Student student = (Student) user;
        if (selectedEnquiry.getStudent().equals(student)) {
            System.out.println("Not allowed to reply to own enquiry!");
            return;
        }
        System.out.printf("Enter reply: ");
        String replyInput = MainProgram.sc.nextLine();
        Committee committee = student.getCommittee();
        committee.addPoint();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String formattedReply = student.getName() + " (" + formattedDateTime + ") : " + replyInput;
        selectedEnquiry.addReply(formattedReply);
        return;
    }
}
