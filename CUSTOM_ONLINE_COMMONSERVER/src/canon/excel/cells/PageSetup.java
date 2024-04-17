package canon.excel.cells;

public interface PageSetup {

	String getPrintArea();

	void setPrintArea(String printArea);

	int  getOrientation();

	void setOrientation(int orientation);

	int getZoom();

	void setZoom(int zoom);

	int getFitToPagesTall();

	void setFitToPagesTall(int fitToPagesTall);

	int getFitToPagesWide();

	void setFitToPagesWide(int fitToPagesWide);

	String getRightFooter();

	void setRightFooter(String rightFooter);

	String getPrintTitleRows();

	void setPrintTitleRows(String printTitleRows);
	
	void setCenterHeader(java.lang.String centerHeader);

	void setPaperSize(int paperSize);

	void setLeftMargin(double d);

	void setRightMargin(double i);

	void setTopMargin(double d);

	void setBottomMargin(double d);

}
