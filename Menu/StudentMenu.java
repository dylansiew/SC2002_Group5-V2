package Menu;

import Comment.CommentType;
import Controller.*;
import Program.*;
import Users.*;
import Menu.CommentHandler.*;
/**
 * The `StudentMenu` class provides a menu for student users to perform various actions, including joining camps,
 * viewing available and joined camps, withdrawing from registered camps, view and raise enquiries, and accessing the committee menu (if applicable).
 */
public class StudentMenu extends UserMenu {
    /**
     * Constructs a new `StudentMenu` instance with the specified student user, all users, and all camps.
     * @param student  The student user for whom the student menu is created.
     * @param allUser  The collection of all users in the system.
     * @param allCamp  The collection of all camps in the system.
     */
    public StudentMenu(Student student, AllUser allUser, AllCamp allCamp) {
        super(student, allUser, allCamp);
    }
    /**
     * Displays text for `mainMenu`.
     */
    private String getMenuText() {
        String menuText = "\nWhat are we doing today?\n(1)\tJoin camp\n(2)\tView available camps\n(3)\tView joined camps\n(4)\tWithdraw from registered camp\n(5)\tEnquire about one Camp\n(6)\tEdit your enquiry\n(7)\tDelete your enquiry\n(8)\tView enquries\n";
        if (((Student) this.user).getCommittee() != null) {
            menuText += "(9)\tCommittee Menu ("
                    + ((Student) this.user).getCommittee().getCamp().getCampInformation().getName()
                    + ")\n(10)\tExit\nChoice: ";
        } else {
            menuText += "(9)\tExit\nChoice: ";
        }
        return menuText;
    }
    /**
     * Displays the main menu for student users and handles user input to perform various actions.
     */
    public void mainMenu() {
        StudentEnquiryMenu enquiryMenu = new StudentEnquiryMenu(allCamp, CommentType.COMMENTTYPES.ENQUIRY);
        UserMenu committeeMenu = new CommitteeMenu(((Student) this.user), allUser, allCamp);
        String menuText = getMenuText();
        System.out.print(menuText);

        int choice = MainProgram.scanInt();
        while (true) {
            switch (choice) {
                case 1:
                    campHandler.getCampParticipantHandler().joinCamp();
                    break;
                case 2:
                    campHandler.getCampViewHandler().viewAvailableCamps();
                    break;
                case 3:
                    campHandler.getCampViewHandler().viewJoinedCamps();
                    break;
                case 4:
                    campHandler.getCampParticipantHandler().withdrawCamp();
                    break;
                case 5:
                    enquiryMenu.addComment(((Student) this.user), new StudentAddEnquiry());
                    break;
                case 6:
                    enquiryMenu.editComment(((Student) this.user), new StudentEditEnquiry());
                    break;
                case 7:
                    enquiryMenu.deleteComment(((Student) this.user), new StudentDeleteEnquiry());
                    break;
                case 8:
                    enquiryMenu.viewComment(((Student) this.user), new StudentViewEnquiry());
                    break;
                case 9:
                    if (((Student) this.user).getCommittee() != null) {
                        committeeMenu.mainMenu();
                        break;
                    } else
                        return;
                default:
                    return;
            }
            menuText = getMenuText();
            System.out.print(menuText);
            choice = MainProgram.scanInt();
        }

    }
}