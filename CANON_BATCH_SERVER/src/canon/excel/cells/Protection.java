package canon.excel.cells;

public interface Protection {

	void setDeletingColumnsAllowed(boolean b);

	void setDeletingRowsAllowed(boolean b);

	void setEditingContentsAllowed(boolean b);

	void setEditingObjectsAllowed(boolean b);

	void setEditingScenariosAllowed(boolean b);

	void setFilteringAllowed(boolean b);

	void setFormattingCellsAllowed(boolean b);

	void setFormattingColumnsAllowed(boolean b);

	void setFormattingRowsAllowed(boolean b);

	void setPassword(String password);

}
