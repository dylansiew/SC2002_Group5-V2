package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Comment.*;
/**
 * The `CampCommentManager` class manages comments for a camp, including both inquiries and suggestions.
 * It provides methods for retrieving comments of specific types and generating a combined list of comments.
 */
public class CampCommentManager  implements Serializable{
    private CampEnquiry campEnquiry = new CampEnquiry();
    private CampSuggestion campSuggestion = new CampSuggestion();
    /**
     * Gets the comments of a specific type associated with this camp.
     * @param type The type of comments to retrieve (ENQUIRY, SUGGESTION, or BOTH).
     * @return A CampComment instance representing the specified type of comments.
     */
    public CampComment getComment(CommentType.COMMENTTYPES type) {
        if (type == CommentType.COMMENTTYPES.ENQUIRY) {
            return this.campEnquiry;
        } else if (type == CommentType.COMMENTTYPES.SUGGESTION) {
            return this.campSuggestion;
        }
        return null;
    }
    /**
     * Gets an ArrayList of comments of a specific type associated with this camp.
     * @param type The type of comments to retrieve (ENQUIRY, SUGGESTION, or BOTH).
     * @return An ArrayList of Comment objects representing the specified type of comments.
     */
    public ArrayList<Comment> getCommentArrayList(CommentType.COMMENTTYPES type) {
        if (type == CommentType.COMMENTTYPES.ENQUIRY) {
            return this.campEnquiry.getComments();
        } else if (type == CommentType.COMMENTTYPES.SUGGESTION) {
            return this.campSuggestion.getComments();
        } else if (type == CommentType.COMMENTTYPES.BOTH) {
            ArrayList<Comment> combinedList = new ArrayList<>();
            combinedList.addAll(this.campEnquiry.getComments());
            combinedList.addAll(this.campSuggestion.getComments());
            return combinedList;
        }
        return null;
    }
    /**
     * Returns a string representation of comments of a specific type associated with this camp.
     * @param type The type of comments to retrieve (ENQUIRY, SUGGESTION, or BOTH).
     * @return A string containing the comments of the specified type.
     */
    public String toString(CommentType.COMMENTTYPES type) {
        if (type == CommentType.COMMENTTYPES.ENQUIRY) {
            return this.campEnquiry.toString();
        } else if (type == CommentType.COMMENTTYPES.SUGGESTION) {
            return this.campSuggestion.toString();
        } else if (type == CommentType.COMMENTTYPES.BOTH) {
            return this.campSuggestion.toString() + this.campEnquiry.toString();
        }
        return null;
    }
}
