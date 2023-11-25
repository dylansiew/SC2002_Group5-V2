package Menu.CommentHandler;

import Camp.Camp;
import Comment.CommentType.COMMENTTYPES;
import Comment.Suggestion;
import Menu.CommentHandler.Interfaces.IAdding;
import Program.MainProgram;
import Users.Committee;
import Users.*;

public class StudentAddSuggestion implements IAdding {
    public void addComment(User user, Camp camp) {
        Student student = (Student) user;
        System.out.print("Enter your suggestion: ");
        String comment = MainProgram.sc.nextLine();
        Committee committee = student.getCommittee();
        if (committee == null) {
            return;
        }
        Suggestion suggestion = new Suggestion(student, camp, comment);
        camp.getCampCommentManager().getComment(COMMENTTYPES.SUGGESTION).addComment(suggestion);
        committee.addPoint();
        System.out.printf("Suggestion submitted\n");
        return;
    }
}
