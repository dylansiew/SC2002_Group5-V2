package Menu;

import java.util.ArrayList;
import Camp.Camp;
import Comment.Comment;
import Comment.CommentType;
import Controller.AllCamp;
import Users.Staff;
import Users.Student;
import Users.User;
import Menu.CampHandler.CampSelectionBuilder;
import Menu.CommentHandler.Interfaces.*;
import Program.MainProgram;

public class StaffSuggestionMenu extends CommentMenu {
    private AllCamp allCamp;

    public StaffSuggestionMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
        super(allCamp, commentType);
    }

    public AllCamp getAllCamp() {
        return this.allCamp;
    }

    public Camp selectCamp(User user) {
        Staff staff = (Staff) user;
        if (staff == null) {
            return null;
        }
        return CampSelectionBuilder.selectCamp(staff.getCampsCreated());
    }

    protected Comment selectComment(Camp camp, User user) {

        if (camp == null) {
            return null;
        }
        ArrayList<Comment> suggestionArrayList = camp.getCampCommentManager()
                .getCommentArrayList(CommentType.COMMENTTYPES.SUGGESTION);
        if (suggestionArrayList.isEmpty()) {
            System.out.println("No available suggestions!");
            return null;
        }
        if (user instanceof Student) {
            Student student = (Student) user;
            ArrayList<Comment> studentSuggestionArrayList = new ArrayList<>();
            for (Comment comment : suggestionArrayList) {
                if (student.equals(comment.getStudent())) {
                    studentSuggestionArrayList.add(comment);
                }
            }
            suggestionArrayList = studentSuggestionArrayList;
        }
        System.out.println("\nSelect Comment:");

        int i = 1;
        for (Comment comment : suggestionArrayList) {
            System.out.printf("\n%d ->\t%s\n", i, comment.toString());
            System.out.println();
            i += 1;
        }
        System.out.printf("%d ->\tExit\n", i);
        System.out.print("\nChoice: ");
        int choice = MainProgram.scanInt();
        if (choice == i) {
            return null;
        }
        while (choice > suggestionArrayList.size() || choice <= 0) {
            System.out.print("Invalid input\nChoice: ");
            choice = MainProgram.scanInt();
        }
        Comment targetComment = suggestionArrayList.get(choice - 1);

        return targetComment;
    }

    public void viewComment(Staff staff, IViewing viewer) {
        Camp targetCamp = CampSelectionBuilder.selectCamp(staff.getCampsCreated());
        if (targetCamp == null) {
            return;
        }
        viewer.viewComment(staff, targetCamp);
        return;
    }

    public void replyComment(Staff staff, IReplying replyer) {
        if (staff == null) {
            return;
        }
        Camp selectedCamp = selectCamp(staff);
        Comment selectedComment = selectComment(selectedCamp, staff);
        if (selectedComment == null) {
            return;
        }
        replyer.replyComment(staff, selectedComment, selectedCamp);
        return;
    }
}
