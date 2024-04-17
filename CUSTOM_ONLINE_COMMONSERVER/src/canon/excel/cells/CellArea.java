package canon.excel.cells;

public interface CellArea {

	int getStartRow();

	void setStartRow(int rowIndex);

	int getEndRow();

	void setEndRow(int rowIndex);

	int getStartColumn();

	void setStartColumn(int columnIndex);

	int getEndColumn();

	void setEndColumn(int columnIndex);

}
