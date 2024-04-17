package canon.excel.aspose;
import canon.excel.cells.PageSetup;
import canon.excel.cells.Cells;
import canon.excel.cells.Comments;
import canon.excel.cells.Protection;
import canon.excel.cells.Shapes;
import canon.excel.cells.Worksheet;
public class WorksheetImpl implements Worksheet {
	private com.aspose.cells.Worksheet worksheet;
	public WorksheetImpl(com.aspose.cells.Worksheet worksheet){
		this.worksheet=worksheet;
	}
	@Override
	public void setFirstVisibleRow(int i) {
		worksheet.setFirstVisibleRow(i);
		
	}
	@Override
	public void setFirstVisibleColumn(int i) {
		worksheet.setFirstVisibleColumn(i);
		
	}
	@Override
	public Cells getCells() {
		return new CellsImpl(worksheet.getCells());
	}
	@Override
	public String getName() {
		return worksheet.getName();
	}
	@Override
	public void protect(Protection pr) {
		// TODO need test
		// worksheet.protect(((ProtectionImpl)pr).getProrection());
	}
	@Override
	public Comments getComments() {
		return new CommentsImpl(worksheet.getComments());
	}
	@Override
	public Shapes getShapes() {
		return new ShapesImpl(worksheet.getShapes());
	}
	@Override
	public PageSetup getPageSetup() {
		return new PageSetupImpl(worksheet.getPageSetup());
	}
	@Override
	public int getIndex() {
		return worksheet.getIndex();
	}
	@Override
	public void autoFitColumns() {
		try {
			worksheet.autoFitColumns();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setName(String name) {
		worksheet.setName(name);
	}
	@Override
	public Protection getProtection() {
		return new ProtectionImpl(worksheet.getProtection());
	}
	@Override
	public void clearComments() {
		worksheet.clearComments();
	}
}
