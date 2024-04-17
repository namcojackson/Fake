package canon.excel.aspose;

import canon.excel.cells.Shape;
import canon.excel.cells.Shapes;

public class ShapesImpl implements Shapes {
	private com.aspose.cells.ShapeCollection shapes;
	public ShapesImpl(com.aspose.cells.ShapeCollection shapes){
		this.shapes=shapes;
	}
	@Override
	public void clearComments() {
	}
	@Override
	public int size() {
		return shapes.getCount();
	}
	@Override
	public Shape get(int i) {
		return new ShapeImpl(shapes.get(i));
	}
	@Override
	public Shape addCopy(Shape sourceShape, int upperLeftRow, int upperLeftColumn){
		try {
			return new ShapeImpl(shapes.addCopy(((ShapeImpl)sourceShape).getShape(),upperLeftRow,0,upperLeftColumn,0));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
