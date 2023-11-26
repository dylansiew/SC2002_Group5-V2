package Camp;

import java.io.Serializable;
import java.util.ArrayList;
import Comment.*;
/**
 * The abstract `CampComment` class represents comments associated with a camp, including various comment types.
 * It provides functionality for managing comments, such as adding and removing comments.
 */
public abstract class CampComment  implements Serializable{
    private ArrayList<Comment> comments = new ArrayList<>();
    private CommentType.COMMENTTYPES commentType;
    /**
     * Constructs a new `CampComment` instance with the specified comment type.
     * @param type The type of comments associated with this `CampComment`.
     */
    public CampComment(CommentType.COMMENTTYPES type){
        this.commentType = type;
    }
    /**
     * Gets the list of comments associated with this `CampComment`.
     * @return An ArrayList of Comment objects.
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
    }
    /**
     * Adds a comment to the list of comments associated with this `CampComment`.
     * @param comments The Comment object to be added.
     */
    public void addComment(Comment comments) {
        if (comments == null) {
            return;
        }
        this.comments.add(comments);
    }
    /**
     * Removes a comment from the list of comments associated with this `CampComment`.
     * @param comments The Comment object to be removed.
     */
    public void removeComment(Comment comments) {
        if (comments == null || !this.comments.contains(comments)) {
            return;
        }
        this.comments.remove(comments);
    }
    /**
     * Gets the number of comments associated with this `CampComment`.
     * @return The number of comments.
     */
    public int getCommentSize() {
        return this.comments.size();
    }
    /**
     * Provide a human-readable format to describe the `CampComment` object
     */
    public String toString(){
        String info = "\n" + commentType + " (" + comments.size() + "):\n";
        for(Comment comment : comments){
            info += "\t" + comment.toString() + "\n";
        }
        return info;
    }
}
