package canon.excel.aspose;

import canon.excel.cells.Protection;

public class ProtectionImpl implements Protection {
	private com.aspose.cells.Protection prorection;
	public ProtectionImpl(com.aspose.cells.Protection prorection){
		this.prorection=prorection;
	}
	@Override
	public void setDeletingColumnsAllowed(boolean b) {
		prorection.setAllowDeletingColumn(b);
	}

	@Override
	public void setDeletingRowsAllowed(boolean b) {
		prorection.setAllowDeletingRow(b);
	}

	@Override
	public void setEditingContentsAllowed(boolean b) {
		prorection.setAllowEditingContent(b);
	}

	@Override
	public void setEditingObjectsAllowed(boolean b) {
		prorection.setAllowEditingObject(b);
	}

	@Override
	public void setEditingScenariosAllowed(boolean b) {
		prorection.setAllowEditingScenario(b);
	}

	@Override
	public void setFilteringAllowed(boolean b) {
		prorection.setAllowFiltering(b);
	}

	@Override
	public void setFormattingCellsAllowed(boolean b) {
		prorection.setAllowFormattingCell(b);
	}

	@Override
	public void setFormattingColumnsAllowed(boolean b) {
		prorection.setAllowFormattingColumn(b);
	}

	@Override
	public void setFormattingRowsAllowed(boolean b) {
		prorection.setAllowFormattingRow(b);
	}

	@Override
	public void setPassword(String password) {
		prorection.setPassword(password);
	}

}
