package canon.excel.aspose;

import canon.excel.cells.Font;
import canon.excel.cells.Style;

public class StyleImpl implements Style {
	com.aspose.cells.Style style;
	public StyleImpl(com.aspose.cells.Style style){
		this.style=style;
	}

	@Override
	public void setNumber(int number) {
		style.setNumber(number);		
	}

	@Override
	public Font getFont() {
		return new FontImpl(style.getFont());
	}

	@Override
	public void setFont(Font font) {
	
	}
}
