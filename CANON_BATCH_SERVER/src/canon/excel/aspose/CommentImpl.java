package canon.excel.aspose;

import canon.excel.cells.Comment;

public class CommentImpl implements Comment {
	private com.aspose.cells.Comment comment;
	public CommentImpl(com.aspose.cells.Comment comment){
		this.comment=comment;
	}
	
	@Override
	public String getText() {
		return comment.getNote();
	}

	@Override
	public int getColumn() {
		return comment.getColumn();
	}

	@Override
	public int getRow() {
		return comment.getRow();
	}

}
