package Menu;

import java.util.ArrayList;
import Camp.Camp;
import Comment.Comment;
import Comment.CommentType;
import Controller.AllCamp;
import Users.Staff;
import Users.Student;
import Users.User;
import Menu.CampHandlers.CampSelectionBuilder;
import Menu.CommentHandler.Interfaces.*;
import Program.MainProgram;
/**
 * The `StaffSuggestionMenu` class represents a menu for staff members to manage and respond to suggestions within the Camp Management System.
 * Staff members can view and approve (or reject) to suggestions associated with camps they are responsible for.
 */
public class StaffSuggestionMenu extends CommentMenu {
    private AllCamp allCamp;
    /**
     * Initializes a new instance of the StaffSuggestionMenu class with the specified parameters.
     * @param allCamp     The AllCamp instance associated with this menu.
     * @param commentType The type of comments (e.g., suggestion) handled by this menu.
     */
    public StaffSuggestionMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
        super(allCamp, commentType);
    }

    public AllCamp getAllCamp() {
        return this.allCamp;
    }
    /**
     * Select a camp based on user input.
     * @param user The user making the selection.
     * @return The selected camp.
     */
    public Camp selectCamp(User user) {
        Staff staff = (Staff) user;
        if (staff == null) {
            return null;
        }
        return CampSelectionBuilder.selectCamp(staff.getCampsCreated());
    }
    /**
     * Select a suggestion comment within a specific camp based on user input.
     * @param camp The camp associated with the comments.
     * @param user The user making the selection.
     * @return The selected suggestion comment.
     */
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
    /**
     * View comments associated with a camp using the provided viewer.
     * @param staff  The staff member viewing the comments.
     * @param viewer The viewer for displaying comments.
     */
    public void viewComment(Staff staff, IViewing viewer) {
        Camp targetCamp = CampSelectionBuilder.selectCamp(staff.getCampsCreated());
        if (targetCamp == null) {
            return;
        }
        viewer.viewComment(staff, targetCamp);
        return;
    }
    /* Reply to a suggestion comment using the provided replyer.
    * @param staff   The staff member replying to the comment.
    * @param replyer The replyer for responding to comments.
    */
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
