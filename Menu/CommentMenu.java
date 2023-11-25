package Menu;

import Camp.*;
import Comment.*;
import Controller.*;
import Users.*;

public abstract class CommentMenu {
    protected AllCamp allCamp;
    protected CommentType.COMMENTTYPES commentType;

    public CommentMenu(AllCamp allCamp, CommentType.COMMENTTYPES commentType) {
        this.allCamp = allCamp;
        this.commentType = commentType;
    }

    public AllCamp getAllCamp() {
        return this.allCamp;
    }

    protected abstract Camp selectCamp(User user);

    protected abstract Comment selectComment(Camp camp, User user);
}
