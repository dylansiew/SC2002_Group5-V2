package Menu;

import java.util.ArrayList;
import Camp.Camp;
import Comment.CommentType.COMMENTTYPES;
import Comment.Enquiry;
import Comment.Comment;
import Comment.CommentType;
import Controller.AllCamp;
import Users.Committee;
import Users.Student;
import Users.User;
import Menu.CampHandler.CampSelectionBuilder;
import Menu.CommentHandler.Interfaces.*;
import Program.MainProgram;
/**
 * The `StudentEnquiryMenu` class represents a menu for students to manage and interact with enquiry comments within the Camp Management System.
 * Students can view, add, edit, and delete their own enquiry comments and reply to camp-related enquiries.
 */
public class StudentEnquiryMenu extends CommentMenu {
    /**
     * Initializes a new instance of the StudentEnquiryMenu class with the specified parameters.
     * @param allCamp     The AllCamp instance associated with this menu.
     * @param commentType The type of comments (e.g., enquiry) handled by this menu.
     */
    public StudentEnquiryMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
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
    protected Camp selectCamp(User user) {
        Student student = (Student) user;
        ArrayList<Camp> campArrayList = FilterCamp.getAvailableCamps(this.getAllCamp(), student);
        if (campArrayList == null) {
            System.out.println("No camp available for enquiry\n");
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
     * Add an enquiry comment using the provided adder.
     * @param student The student adding the enquiry comment.
     * @param adder   The adder for adding comments.
     */
    public void addComment(Student student, IAdding adder) {
        Camp camp = this.selectCamp(student);
        if (camp == null)
            return;
        adder.addComment(student, camp);
        return;
    }
    /**
     * Edit an enquiry comment using the provided editor.
     * @param student The student editing the enquiry comment.
     * @param editor  The editor for editing comments.
     */
    public void editComment(Student student, IEditing editor) {
        Camp camp = this.selectCamp(student);
        if (camp == null)
            return;
        Comment enquiry = this.selectComment(camp, student);
        if (enquiry == null)
            return;
        editor.editComment(student, enquiry);
        return;
    }
    /**
     * Delete an enquiry comment using the provided deleter.
     * @param student The student deleting the enquiry comment.
     * @param deleter The deleter for deleting comments.
     */
    public void deleteComment(Student student, IDeleting deleter) {
        Camp camp = this.selectCamp(student);
        if (camp == null)
            return;
        Comment selectedComment = this.selectComment(camp, student);
        if (!(selectedComment instanceof Enquiry)) {
            return;
        }
        deleter.deleteComment(student, selectedComment, camp);
        return;
    }
    /**
     * Reply to an enquiry comment using the provided replyer.
     * @param student The student replying to the enquiry comment.
     * @param replyer The replyer for responding to comments.
     */
    public void replyComment(Student student, IReplying replyer) {
        Committee committee = student.getCommittee();
        if (committee == null) {
            return;
        }
        Camp selectedCamp = committee.getCamp();
        if (selectedCamp == null) {
            return;
        }
        Comment selectedComment = this.selectComment(selectedCamp, null);
        if (!(selectedComment instanceof Enquiry)) {
            return;
        }
        replyer.replyComment(student, selectedComment, selectedCamp);
        return;
    }
    /**
     * View comments associated with a camp using the provided viewer.
     * @param student The student viewing the comments.
     * @param viewer  The viewer for displaying comments.
     */
    public void viewComment(Student student, IViewing viewer) {
        Camp selectedCamp = this.selectCamp(student);
        if (selectedCamp == null) {
            return;
        }
        viewer.viewComment(student, selectedCamp);
        return;
    }
}