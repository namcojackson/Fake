package canon.excel.cells;

public class CellsHelper {
	public static String convertCellIndexToName(int row, int col) {
//		 aspose 8
		return com.aspose.cells.CellsHelper.cellIndexToName(row,col);
		// aspose 2
//		return com.aspose.cells.CellsHelper.convertCellIndexToName(row,col);
	}

	public static CellArea newCellArea(int startRow, int startColumn, int endRow,int endColumn){
		return new canon.excel.aspose.CellAreaImpl(startRow,startColumn,endRow,endColumn);
	}

	public static Workbook newWorkbook(){
		return new canon.excel.aspose.WorkbookImpl();
	}
	
}
