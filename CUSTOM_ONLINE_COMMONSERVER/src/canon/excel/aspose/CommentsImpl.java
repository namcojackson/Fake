package canon.excel.aspose;

import canon.excel.cells.Comment;
import canon.excel.cells.Comments;

public class CommentsImpl implements Comments{
	private com.aspose.cells.CommentCollection comments;
	public CommentsImpl(com.aspose.cells.CommentCollection comments){
		this.comments=comments;
	}
	@Override
	public int size() {
		return comments.getCount();
	}
	@Override
	public Comment get(int i) {
		return new CommentImpl(comments.get(i));
	}
	
}
