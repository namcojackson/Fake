package canon.excel.cells;

public interface Shape {

	public int getUpperLeftRow();

	public int getUpperLeftColumn();

//	public void move(int rowIndex, int columnIndex);

	public void setUpperLeftColumn(int columnIndex);

	public void setUpperLeftRow(int rowIndex);

}
