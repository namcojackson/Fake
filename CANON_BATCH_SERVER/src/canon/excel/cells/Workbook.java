package canon.excel.cells;

import java.io.InputStream;
import java.io.OutputStream;

public interface Workbook {
	public void calculateFormula();
	public void open(InputStream is, int excel97to2003) throws java.io.IOException;
	public void open(InputStream is) throws java.io.IOException;
	public void open(String filename, int excel97to2003) throws java.io.IOException;
	public void open(String filename) throws java.io.IOException;
	public void save(OutputStream os, int excel97to2003) throws java.io.IOException;
	public void save(OutputStream os) throws java.io.IOException;
	public Worksheets getWorksheets();
	public void savePDF(OutputStream os, int pdf, byte pdfa1b) throws java.io.IOException;
	public void combine(Workbook wbk);
}
