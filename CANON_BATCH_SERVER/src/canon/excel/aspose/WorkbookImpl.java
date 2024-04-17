package canon.excel.aspose;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import canon.excel.cells.Workbook;
import canon.excel.cells.Worksheets;

public class WorkbookImpl implements Workbook {
	private com.aspose.cells.Workbook workbook;
	
	public WorkbookImpl(){
		 workbook=new com.aspose.cells.Workbook();
	}
	public void calculateFormula() {
		workbook.calculateFormula();
	}
	@Override
	public void open(InputStream is, int excel97to2003) throws IOException {
		com.aspose.cells.LoadOptions lo=new com.aspose.cells.LoadOptions(excel97to2003);
		try {
			workbook= new com.aspose.cells.Workbook(is, lo);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void save(OutputStream os, int excel97to2003) throws IOException {
		try {
			workbook.save(os, excel97to2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Worksheets getWorksheets() {
		return new WorksheetsImpl(workbook.getWorksheets());
	}
	@Override
	public void open(String filename, int excel97to2003) throws IOException {
		com.aspose.cells.LoadOptions lo=new com.aspose.cells.LoadOptions(excel97to2003);
		try {
			workbook= new com.aspose.cells.Workbook(filename, lo);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void savePDF(OutputStream os, int pdf, byte pdfa1b) throws java.io.IOException{
		com.aspose.cells.PdfSaveOptions pdfso=new com.aspose.cells.PdfSaveOptions(pdf);
		pdfso.setCompliance(pdfa1b);
		try {
			workbook.save(os, pdfso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void open(InputStream is) throws IOException {
		try {
			workbook= new com.aspose.cells.Workbook(is);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void open(String filename) throws IOException {
		try {
			workbook= new com.aspose.cells.Workbook(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void save(OutputStream os) throws IOException {
		try {
			workbook.save(os,com.aspose.cells.SaveFormat.AUTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void combine(Workbook wbk) {
		try {
			workbook.combine(((WorkbookImpl)wbk).workbook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
