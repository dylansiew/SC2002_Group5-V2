package Camp;

import Comment.CommentType;
/**
 * The `CampEnquiry` class represents a type of camp comment that is used for inquiries or questions related to a camp.
 * It extends the abstract `CampComment` class and specifies the comment type as "ENQUIRY."
 */
public class CampEnquiry extends CampComment {
    /**
     * Constructs a new `CampEnquiry` instance, setting the comment type to "ENQUIRY."
     */
    public CampEnquiry(){
        super(CommentType.COMMENTTYPES.ENQUIRY);
    }
}
