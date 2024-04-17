package canon.excel.aspose;

import canon.excel.cells.NamedRange;
import canon.excel.cells.Worksheet;

public class NamedRangeImpl implements NamedRange{
	//TODO need test
	com.aspose.cells.Name namedRange;
	public NamedRangeImpl(com.aspose.cells.Name namedRange){
		this.namedRange=namedRange;
	}
	@Override
	public int getStartRow() {
		return namedRange.getRange().getFirstRow();
	}
	@Override
	public int getEndRow() {
		return namedRange.getRange().getFirstRow()+namedRange.getRange().getRowCount()-1;
	}
	@Override
	public int getEndColumn() {
		return namedRange.getRange().getFirstColumn()+namedRange.getRange().getColumnCount()-1;
	}
	@Override
	public int getStartColumn() {
		return namedRange.getRange().getFirstColumn();
	}
	@Override
	public Worksheet getSourceSheet() {
		return new WorksheetImpl(namedRange.getRange().getWorksheet());
	}

}
