package canon.excel.aspose;

import canon.excel.cells.CellArea;

public class CellAreaImpl implements CellArea {
	com.aspose.cells.CellArea cellarea;
	public CellAreaImpl(int startRow, int startColumn, int endRow,
			int endColumn) {
		this.cellarea=com.aspose.cells.CellArea.createCellArea(startRow, startColumn, endRow, endColumn); 

	}
	public CellAreaImpl(com.aspose.cells.CellArea cellarea) {
		this.cellarea=cellarea;
	}
	
	@Override
	public int getStartRow() {
		return cellarea.StartRow;
	}
	@Override
	public void setStartRow(int rowIndex) {
		cellarea.StartRow=rowIndex;
	}
	@Override
	public int getEndRow() {
		return cellarea.EndRow;
	}
	@Override
	public void setEndRow(int rowIndex) {
		cellarea.EndRow=rowIndex;
	}
	@Override
	public int getStartColumn() {
		return cellarea.StartColumn;
	}
	@Override
	public void setStartColumn(int columnIndex) {
		cellarea.StartColumn=columnIndex;
	}
	@Override
	public int getEndColumn() {
		return cellarea.EndColumn;
	}
	@Override
	public void setEndColumn(int columnIndex) {
		cellarea.EndColumn=columnIndex;
	}

}
