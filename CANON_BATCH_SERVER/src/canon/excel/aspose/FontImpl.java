package canon.excel.aspose;

import canon.excel.cells.Font;

public class FontImpl implements Font {
	private com.aspose.cells.Font font;
	public FontImpl(com.aspose.cells.Font font){
		this.font=font;
	}
	@Override
	public void setBold(boolean value) {
		font.setBold(true);		
	}
	
}
