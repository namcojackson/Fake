package canon.excel.cells;

public interface Worksheets {
	public NamedRange getRangeByName(String name);
	public int size();
	public Worksheet getSheet(int i);
	public String getSheetName(int targetSheetIndex);
	public Worksheet addSheet(String sheetName);
	public void removeSheet(int i);
}
