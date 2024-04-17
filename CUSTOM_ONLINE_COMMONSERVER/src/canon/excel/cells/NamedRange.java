package canon.excel.cells;

public interface NamedRange {

	int getStartRow();

	int getEndRow();

	int getEndColumn();

	int getStartColumn();

	Worksheet getSourceSheet();

}
