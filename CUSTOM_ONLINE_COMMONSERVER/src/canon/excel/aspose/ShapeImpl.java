package canon.excel.aspose;

import canon.excel.cells.Shape;

public class ShapeImpl implements Shape{

	private com.aspose.cells.Shape shape;
	
	public ShapeImpl(com.aspose.cells.Shape shape){
		this.shape=shape;
	}
	@Override
	public int getUpperLeftRow() {
		return shape.getUpperLeftRow();
	}

	@Override
	public int getUpperLeftColumn() {
		return shape.getUpperLeftColumn();
	}

	public com.aspose.cells.Shape getShape(){
		return shape;
	}
	@Override
	public void setUpperLeftColumn(int columnIndex) {
		shape.setUpperLeftColumn(columnIndex);
	}
	@Override
	public void setUpperLeftRow(int rowIndex) {
		shape.setUpperLeftRow(rowIndex);
	}
	

}
