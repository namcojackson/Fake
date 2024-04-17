package canon.excel.aspose;

import java.util.List;

import canon.excel.cells.Cell;
import canon.excel.cells.CellArea;
import canon.excel.cells.Cells;

public class CellsImpl implements Cells {
	private com.aspose.cells.Cells cells;
	public CellsImpl(com.aspose.cells.Cells cells){
		this.cells=cells;
	}
	@Override
	public void subtotal(CellArea region, int groupBy, short function, int[] totalList) {
		cells.subtotal( ((CellAreaImpl)region).cellarea ,groupBy,function,totalList);
	}
	@Override
	public int getMaxColumn() {
		return cells.getMaxColumn();
	}
	@Override
	public int getMaxRow() {
		return cells.getMaxRow();
	}
	@Override
	public Cell getCell(int row, int col) {
		return new CellImpl(cells.get(row,col));
	}
	@Override
	public double getColumnWidth(int c) {
		return cells.getColumnWidth(c);
	}
	@Override
	public void setColumnWidth(int c, double columnWidth) {
		cells.setColumnWidth(c, columnWidth);
	}
	@Override
	public double getRowHeight(int r) {
		return cells.getRowHeight(r);
	}
	@Override
	public void setRowHeight(int r, double rowHeight) {
		cells.setRowHeight(r, rowHeight);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getMergedCells() {
		List l=new java.util.ArrayList();
		List l2=cells.getMergedCells();
		if(l2!=null){
			for(int i=0;i<l2.size();i++){
				com.aspose.cells.CellArea ca =(com.aspose.cells.CellArea )l2.get(i);
				l.add(new CellAreaImpl(ca));
			}
		}
		return l;
	}
	@Override
	public void merge(int startRow, int startColumn, int endrow, int endcol) {
		//public void merge(int firstRow, int firstColumn, int totalRows, int totalColumns)
		cells.merge(startRow, startColumn, endrow-startRow+1, endcol-startColumn+1);
	}

}
