package Camp;

import Comment.CommentType;
/**
 * The `CampSuggestion` class represents a type of camp comment that holds suggestions related to a camp.
 * It extends the `CampComment` class and is specifically used for storing and managing suggestions.
 */
public class CampSuggestion extends CampComment{
    /**
     * Constructs a new `CampSuggestion` instance, initializing it as a type of suggestion comment.
     */
    public CampSuggestion(){
        super(CommentType.COMMENTTYPES.SUGGESTION);
    }
}
