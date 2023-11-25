package Menu.CommentHandler;

import Comment.Comment;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.IReplying;
import Program.MainProgram;
import Users.*;
import Camp.Camp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentReplyEnquiry implements IReplying {
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
