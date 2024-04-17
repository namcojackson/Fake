package canon.excel.cells;

public interface Shapes {

	void clearComments();

	int size();

	Shape get(int i);

	Shape addCopy(Shape sourceShape, int upperLeftRow, int upperLeftColumn);

}
