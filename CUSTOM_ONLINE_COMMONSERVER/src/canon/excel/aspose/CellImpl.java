package canon.excel.aspose;

import canon.excel.cells.Cell;
import canon.excel.cells.Style;

public class CellImpl implements Cell {
	private com.aspose.cells.Cell cell;
	public CellImpl(com.aspose.cells.Cell cell){
		this.cell=cell;
	}
	
	@Override
	public String getStringValue() {
		return cell.getStringValue();
	}

	@Override
	public int getColumnIndex() {
		return cell.getColumn();
	}

	@Override
	public int getRowIndex() {
		return cell.getRow();
	}

	@Override
	public Object getValue() {
		return cell.getValue();
	}

	@Override
	public void setValue(Object cellvalue) {
		cell.setValue(cellvalue);
	}

	@Override
	public Style getStyle() {
		return new StyleImpl(cell.getStyle());
	}

	@Override
	public void setStyle(Style style) {
		cell.setStyle(((StyleImpl)style).style);
	}

	@Override
	public int getValueType() {
		return cell.getType();
	}

	@Override
	public void setFormula(String string) {
		cell.setFormula(string);
	}

}
