package canon.excel.aspose;

import canon.excel.cells.NamedRange;
import canon.excel.cells.Worksheet;

import canon.excel.cells.Worksheets;

public class WorksheetsImpl  implements Worksheets{
	private com.aspose.cells.WorksheetCollection worksheets;
	
	public WorksheetsImpl(com.aspose.cells.WorksheetCollection arg0) {
		this.worksheets=arg0;
	}

	@Override
	public NamedRange getRangeByName(String name) {
		//TODO need test
		return new NamedRangeImpl(worksheets.getNames().get(name));
	}

	@Override
	public int size() {
		return worksheets.getCount();
	}

	@Override
	public Worksheet getSheet(int i) {
		return new WorksheetImpl(worksheets.get(i));
	}

	@Override
	public String getSheetName(int targetSheetIndex) {
		return worksheets.get(targetSheetIndex).getName();
	}

	@Override
	public Worksheet addSheet(String sheetName) {
		return new WorksheetImpl(worksheets.add(sheetName));
	}

	@Override
	public void removeSheet(int i) {
		worksheets.removeAt(i);
	}

}
