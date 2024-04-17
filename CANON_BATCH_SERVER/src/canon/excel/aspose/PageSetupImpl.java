package canon.excel.aspose;

import canon.excel.cells.PageSetup;

public class PageSetupImpl implements PageSetup {
	private com.aspose.cells.PageSetup pageSetup;
	public PageSetupImpl(com.aspose.cells.PageSetup pageSetup){
		this.pageSetup=pageSetup;
	}
	@Override
	public String getPrintArea() {
		return pageSetup.getPrintArea();
	}
	@Override
	public void setPrintArea(String printArea) {
		pageSetup.setPrintArea(printArea);
	}
	@Override
	public int getOrientation() {
		return pageSetup.getOrientation();
	}
	@Override
	public void setOrientation(int orientation) {
		pageSetup.setOrientation(orientation);
		
	}
	@Override
	public int getZoom() {
		return pageSetup.getZoom();
	}
	@Override
	public void setZoom(int zoom) {
		pageSetup.setZoom( zoom);
	}
	@Override
	public int getFitToPagesTall() {
		return pageSetup.getFitToPagesTall();
	}
	@Override
	public void setFitToPagesTall(int fitToPagesTall) {
		pageSetup.setFitToPagesTall(fitToPagesTall);
	}
	@Override
	public int getFitToPagesWide() {
		return pageSetup.getFitToPagesWide();
	}
	@Override
	public void setFitToPagesWide(int fitToPagesWide) {
		pageSetup.setFitToPagesWide(fitToPagesWide);
	}
	@Override
	public String getRightFooter() {
		return pageSetup.getFooter(2);
	}
	@Override
	public void setRightFooter(String rightFooter) {
		pageSetup.setFooter(2, rightFooter);
	}
	@Override
	public String getPrintTitleRows() {
		return pageSetup.getPrintTitleRows();
	}
	@Override
	public void setPrintTitleRows(String printTitleRows) {
		pageSetup.setPrintTitleRows(printTitleRows);
	}
	@Override
	public void setCenterHeader(String centerHeader) {
		pageSetup.setHeader(1, centerHeader);
	}
	@Override
	public void setPaperSize(int paperSize) {
		pageSetup.setPaperSize(paperSize);
		
	}
	@Override
	public void setLeftMargin(double d) {
		pageSetup.setLeftMargin(d);
	}
	@Override
	public void setRightMargin(double i) {
		pageSetup.setRightMargin(i);
	}
	@Override
	public void setTopMargin(double d) {
		pageSetup.setTopMargin(d);
	}
	@Override
	public void setBottomMargin(double d) {
		pageSetup.setBottomMargin(d);
	}
}
