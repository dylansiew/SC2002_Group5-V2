package Menu.CommentHandler;

import Comment.CommentType.COMMENTTYPES;
import Comment.Enquiry;
import Menu.CommentHandler.Interfaces.*;
import Program.MainProgram;
import Users.*;
import Camp.Camp;

public class StudentAddEnquiry implements IAdding {
    public void addComment(User user, Camp camp) {
        Student student = (Student) user;
        if (student.getCommittee() != null) {
            Committee committee = student.getCommittee();
            if (committee.getCamp() == camp) {
                System.out.println("Committee member cannot raise enquiry for own camp");
                return;
            }
        }
        System.out.print("Enter enquiry: ");

        String comment = MainProgram.sc.nextLine();
        Enquiry enquiry = new Enquiry(student, camp, comment);
        camp.getCampCommentManager().getComment(COMMENTTYPES.ENQUIRY).addComment(enquiry);
        System.out.println("Enquiry raised");
        return;
    }
}