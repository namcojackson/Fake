package canon.excel.cells;

import java.util.List;

public interface Cells {

	public void subtotal(CellArea region, int groupBy, short sum, int[] cols);

	public int getMaxColumn();

	public int getMaxRow();

	public Cell getCell(int row, int col);

	public double getColumnWidth(int c);

	public void setColumnWidth(int c, double columnWidth);

	public double getRowHeight(int r);

	public void setRowHeight(int r, double rowHeight);

	public List getMergedCells();

	public void merge(int rowIndex, int colIndex, int endrow, int endcol);

}
