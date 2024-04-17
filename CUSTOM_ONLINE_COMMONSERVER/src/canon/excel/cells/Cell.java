package canon.excel.cells;

public interface Cell {

	String getStringValue();

	int getColumnIndex();

	int getRowIndex();

	Object getValue();

	void setValue(Object cellvalue);

	Style getStyle();

	void setStyle(Style style);

	int getValueType();

	void setFormula(String string);

}
