package canon.excel.cells;

public interface Worksheet {

	void setFirstVisibleRow(int i);

	void setFirstVisibleColumn(int i);

	Cells getCells();

	String getName();

	void protect(Protection pr);

	Comments getComments();

	Shapes getShapes();

	PageSetup getPageSetup();

	int getIndex();

	void autoFitColumns();
	
	void setName(java.lang.String name);

	Protection getProtection();

	void clearComments();

}
