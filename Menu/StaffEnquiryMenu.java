package Menu;

import java.util.ArrayList;
import Camp.Camp;
import Comment.CommentType.COMMENTTYPES;
import Comment.Enquiry;
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
 * The `StaffEnquiryMenu` class represents a menu for staff members to manage and respond to enquiries within the Camp Management System.
 * Staff members can view and reply to enquiries associated with camps they are responsible for.
 */
public class StaffEnquiryMenu extends CommentMenu {
    private AllCamp allCamp;
    /**
     * Initializes a new instance of the StaffEnquiryMenu class with the specified parameters.
     * @param allCamp     The AllCamp instance associated with this menu.
     * @param commentType The type of comments (e.g., enquiry) handled by this menu.
     */
    public StaffEnquiryMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
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
        ArrayList<Camp> campArrayList = staff.getCampsCreated();
        if (campArrayList == null) {
            return null;
        }
        Camp selectedCamp = CampSelectionBuilder.selectCamp(campArrayList);

        return selectedCamp;
    }
    /**
     * Select an enquiry comment within a specific camp based on user input.
     * @param camp The camp associated with the comments.
     * @param user The user making the selection.
     * @return The selected enquiry comment.
     */
    protected Comment selectComment(Camp camp, User user) {
        if (camp == null) {
            return null;
        }
        ArrayList<Comment> enquiryArrayList = camp.getCampCommentManager().getCommentArrayList(COMMENTTYPES.ENQUIRY);
        if (enquiryArrayList.isEmpty()) {
            System.out.println("No available enquiries!");
            return null;
        }
        if (user instanceof Student) {
            Student student = (Student) user;
            ArrayList<Comment> studentEnquirArrayList = new ArrayList<>();
            for (Comment comment : enquiryArrayList) {
                if (student.equals(comment.getStudent())) {
                    studentEnquirArrayList.add(comment);
                }
            }
            enquiryArrayList = studentEnquirArrayList;
        }
        System.out.println("\nSelect Enquiry:");

        int i = 1;
        for (Comment comment : enquiryArrayList) {
            System.out.printf("\n%d ->\t%s", i, comment.toString());
            System.out.println();
            i += 1;
        }
        System.out.printf("%d ->\tExit\n", i);
        System.out.print("\nChoice: ");
        int choice = MainProgram.scanInt();
        if (choice == i) {
            return null;
        }
        while (choice > enquiryArrayList.size() || choice <= 0) {
            System.out.print("Invalid input\nChoice: ");
            choice = MainProgram.scanInt();
        }
        Comment targetComment = enquiryArrayList.get(choice - 1);
        if (targetComment instanceof Enquiry) {
            Enquiry targetEnquiry = (Enquiry) targetComment;

            return targetEnquiry;

        }
        return null;
    }
    /**
     * View comments associated with a camp using the provided viewer.
     * @param staff  The staff member viewing the comments.
     * @param viewer The viewer for displaying comments.
     */
    public void viewComment(Staff staff, IViewing viewer) {
        Camp selectedCamp = this.selectCamp(staff);
        if (selectedCamp == null) {
            return;
        }
        viewer.viewComment(staff, selectedCamp);
    }
    /**
     * Reply to an enquiry comment using the provided replyer.
     * @param staff   The staff member replying to the comment.
     * @param replyer The replyer for responding to comments.
     */
    public void replyComment(Staff staff, IReplying replyer) {
        Camp selectedCamp = this.selectCamp(staff);
        if (selectedCamp == null) {
            return;
        }
        Comment selectedComment = this.selectComment(selectedCamp, staff);
        if (!(selectedComment instanceof Enquiry)) {
            return;
        }
        replyer.replyComment(staff, selectedComment, selectedCamp);
    }

}
