package com.canon.apps.servreq.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.PageContext;

import com.canon.apps.servreq.beans.CanonE307AccessRoleBean;
import com.canon.apps.servreq.beans.CanonE307SRTaskHistNewRec;
import com.canon.apps.servreq.util.CanonE307ServiceReqSumryAPIUtil;
import com.canon.common.CanonCommonUtil;
import com.canon.cusa.s21.framework.security.S21Authentication;
import com.canon.cusa.s21.framework.security.S21AuthenticationException;
import com.canon.cusa.s21.framework.security.context.S21SecurityContext;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class CanonE307ServReqUtil {

	
	   /**A class that defines a simple XLXS file
	    */
	    public static class SimpleXLSXFile
	    {

	        /**A class that defines a the data type of a cell
	        */
	        abstract class DataType
	        {
	            /**@return the XML string that represent this data type
	            */
	            public abstract String getXMLString();

	            /**@return the string representation of this cell
	            */
	            public abstract String getString();
	        }

	        /**A class that defines a string in a cell
	        */
	        class StringType extends DataType
	        {
	            /**constructor
	            *@param s the string
	            */
	            public StringType(String s)
	            {
	                this.s = s;
	                index = 0;
	            }

	            /**@return the XML string that represent this data type
	            */
	            public String getXMLString()
	            {
	                return "<c t='s'><v>"+index+"</v></c>";
	            }


	            /**
	              *Set the string value
	              *@param s the s value
	              */
	            public void setString(String s)
	            {
	                this.s = s;
	            }

	            /**
	              *@return the string value
	              */
	            public String getString()
	            {
	                return s;
	            }

	            /**
	              *the index in the shared string
	              *@param index the index value
	              */
	            public void setIndex(int index)
	            {
	                this.index = index;
	            }

	            /**
	              *@return the index value
	              */
	            public int getIndex()
	            {
	                return index;
	            }

	            /**
	              *the index in the shared string
	              */
	            private int index;

	            /**
	              *The string
	              */
	            private String s;

	            public String toString() {
	                return "StringType{" + "index=" + index + ", s=" + s + '}';
	            }
	            
	        }

	        /**A class that defines a double in a cell
	        */
	        class NumberType extends DataType
	        {
	            /**constructor
	            *@param d the double value
	            */
	            public NumberType(Number n)
	            {
	                this.n = n;
	            }

	            /**@return the XML string that represent this data type
	            */
	            public String getXMLString()
	            {
	                return "<c><v>"+n+"</v></c>";
	            }

	            /**
	              *Set the double value
	              *@param d the d value
	              */
	            public void setValue(Number n)
	            {
	                this.n = n;
	            }

	            /**
	              *@return the double value
	              */
	            public Number getValue()
	            {
	                return n;
	            }

	            /**@return the string representation of this cell
	            */
	            public String getString()
	            {
	                return ""+n;
	            }

	            /**
	              *the double value
	              */
	            private Number n;

	            public String toString() {
	                return "NumberType{" + "d=" + n + '}';
	            }
	            
	        }

	        
	        /**A class that defines a double in a cell
	        */
	        class DoubleType extends DataType
	        {
	            /**constructor
	            *@param d the double value
	            */
	            public DoubleType(double d)
	            {
	                this.d = d;
	            }

	            /**@return the XML string that represent this data type
	            */
	            public String getXMLString()
	            {
	                return "<c><v>"+d+"</v></c>";
	            }

	            /**
	              *Set the double value
	              *@param d the d value
	              */
	            public void setValue(double d)
	            {
	                this.d = d;
	            }

	            /**
	              *@return the double value
	              */
	            public double getValue()
	            {
	                return d;
	            }

	            /**@return the string representation of this cell
	            */
	            public String getString()
	            {
	                return ""+d;
	            }

	            /**
	              *the double value
	              */
	            private double d;

	            public String toString() {
	                return "DoubleType{" + "d=" + d + '}';
	            }
	            
	        }

	        /**A class that defines a string in a cell
	        */
	        class FormulaType extends DataType
	        {
	            /**constructor
	            *@param f the formula
	            */
	            public FormulaType(String f)
	            {
	                this.f = f;
	            }

	            /**@return the XML string that represent this data type
	            */
	            public String getXMLString()
	            {
	                return "<c><f>"+f+"</f></c>";
	            }

	            /**
	              *Set the formula
	              *@param f the formula
	              */
	            public void setFormula(String f)
	            {
	                this.f = f;
	            }

	            /**
	              *@return the formula value
	              */
	            public String getFormula()
	            {
	                return f;
	            }

	            /**@return the string representation of this cell
	            */
	            public String getString()
	            {
	                return f;
	            }

	            /**
	              *the formula
	              */
	            private String f;
	        }
	        

	        /**a class that defines a spread sheet
	        */
	        class Spreadsheet
	        {
	            /**Constructor
	            *@param name the name of the spread sheet
	            */
	            public Spreadsheet(String name)
	            {
	                this.name = name;
	                rows = new ArrayList();
	            }

	            /**add a row of string
	            *@param row the row of string
	            */
	            public void addRow(String row)
	            {
	        //        ArrayList<String> data = new ArrayList<String>();
	                ArrayList data = new ArrayList();
	                data.add(row);
	                addRow(data);
	            }

	            /**add a row of string
	            *@param row the row of string
	            */
	        //    public void addRow(List<String> row)
	            public void addRow(List row)
	            {

	                if (row.size()==0)
	                {
	                    addRow("");
	                }
	                else
	                {
	        //            ArrayList<DataType> list = new ArrayList<DataType>();
	                    ArrayList list = new ArrayList();
	        //            for(String s:row)
	                    for(int i=0;i<row.size();i++)
	                    {
	                        Object o=row.get(i);
	                        if(o ==null){
	                            list.add(new StringType(""));
	                        }else if(o instanceof Number){
	                            list.add(new NumberType((Number)o));
	                        }else{
	                            list.add(new StringType((String)o));
	                            //check if string is a double
//	                            try
//	                            {
//	                                double d = Double.parseDouble(s);
//	                                //check if 1st character is 0
//	                                //if it is 0xxx, we do not want to save as number
//	                                if ((s.length()>0)&&(s.charAt(0)=='0')&&(s.length()!=1))//0 should be a double
//	                                {
//	                                    //not a number, add as string
//	                                    list.add(new StringType(s));
//	                                }
//	                                else
//	                                {
//	                                    list.add(new DoubleType(d));
//	                                }
//	                            }
//	                            catch(NumberFormatException e)
//	                            {
//	                                //not a number, add as string
//	                                list.add(new StringType(s));
//	                            }
	                        }
	                    }
	                    addDataRow(list);
	                }
	            }

	            /**add a row of data
	            *@param row the row of data
	            */
	            private void addDataRow(List row)
	            {
	                rows.add(row);
	            }

	            /**
	              *the name of the spreadsheet
	              *@param name the name value
	              */
	            public void setName(String name)
	            {
	                this.name = name;
	            }

	            /**
	              *@return the name value
	              */
	            public String getName()
	            {
	                return name;
	            }

	            /**@return the rows of string
	            */
	            public List getRows()
	            {
	                return rows;
	            }

	            /**set a cell as a formula
	            *@param r the row
	            *@param c the col
	            *@throws ArrayIndexOutOfBoundsException
	            */
	            public void setCellAsFormula(int r, int c)
	            {
	        //        List<DataType> row = rows.get(r);
	                List row = (List)rows.get(r);
	                DataType data = (DataType)row.get(c);
	                FormulaType formula = new FormulaType(data.getString());
	                row.set(c,formula);
	            }

	            /**
	              *the name of the spreadsheet
	              */
	            private String name;

	            /**the array of rows of data
	            */
	        //    private List<List<DataType>> rows;
	            private List rows;
	        }
	        
	        /**constructor
	        *@param filename the filename of the file
	        */
	        public SimpleXLSXFile(String filename)
	        {
	            this();
	            this.filename = filename;
	        }
	        public SimpleXLSXFile()
	        {
	            sheets = new ArrayList();
	            sharesStrings = new ArrayList();
	        }        

	        /**add a spreadsheet
	        */
	        public void addSheet(Spreadsheet sheet)
	        {
	            sheets.add(sheet);
	        }
	        
	        public Spreadsheet addNewSheet(String sheetname)
	        {
	            Spreadsheet sheet=new Spreadsheet(sheetname);
	            addSheet(sheet);
	            return sheet;
	        }

	        /**
	          *the filename of the file
	          *@param filename the filename value
	          */
	        public void setFilename(String filename)
	        {
	            this.filename = filename;
	        }

	        /**create worksheet
	        *@param sheet the sheet to be saved
	        *@param index the pos of the spreadsheet
	        *@throws IOException when there is an io error
	        */
	        private void createWorksheet(Spreadsheet sheet,int pos) throws IOException
	        {
	            //ZipEntry entry = new ZipEntry("xl/worksheets/"+sheet.getName()+".xml");
	            ZipEntry entry = new ZipEntry("xl/worksheets/sheet"+pos+".xml");
	            zos.putNextEntry(entry);                    
	            writer.write("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");writer.newLine();
	            writer.write("<worksheet xmlns='http://schemas.openxmlformats.org/spreadsheetml/2006/main' xmlns:r='http://schemas.openxmlformats.org/officeDocument/2006/relationships'>");writer.newLine();
	            writer.write("<sheetData>");writer.newLine();
	            List rows = sheet.getRows();
	            int rowNo = 1;
	    //        for(List<DataType> row:rows)
	            for(int i=0;i<rows.size();i++)
	            {
	                List row=(List)rows.get(i);
	                writer.write("\t<row r='"+rowNo+"' spans='1:"+row.size()+"'>");writer.newLine();
	    //            for(DataType data:row)
	                for(int j=0;j<row.size();j++)
	                {
	                    DataType data=(DataType)row.get(j);
	                    writer.write("\t\t"+data.getXMLString());writer.newLine();
	                }
	                writer.write("\t</row>");writer.newLine();
	                rowNo++;
	            }

	            writer.write("</sheetData>");writer.newLine();
	            writer.write("</worksheet>");writer.newLine();
	            writer.flush();
	            zos.closeEntry();
	        }

	        /**create the xl/sharedStrings.xml
	        *@throws IOException when there is an io error
	        */
	        private void createSharedStringsXML() throws IOException
	        {
	            ZipEntry entry = new ZipEntry("xl/sharedStrings.xml");
	            zos.putNextEntry(entry);                    
	            int total = 0;
	            int currentIndex = 0;
	            //search through the sheets for all strings
	    //        for(Spreadsheet sheet:sheets)
	            for(int i=0;i<sheets.size();i++)
	            {
	                Spreadsheet sheet=(Spreadsheet)sheets.get(i);
	                List rows = sheet.getRows();
	    //            for(List<DataType> row:rows)
	                for(int ii=0;ii<rows.size();ii++)
	                {
	                    List row=(List)rows.get(ii);
	    //                for(DataType data:row)
	                    for(int j=0;j<row.size();j++)
	                    {
	                        DataType data=(DataType)row.get(j);
	                        if (data instanceof StringType)
	                        {
	                            StringType s = (StringType)data;
	                            total++;
	                            int index = sharesStrings.indexOf(s.getString());
	                            if (index==-1)
	                            {
	                                s.setIndex(currentIndex);
	                                currentIndex++;
	                                sharesStrings.add(s.getString());
	                            }
	                            else
	                            {
	                                s.setIndex(index);
	                            }
	                        }
	                    }
	                }
	            }

	            writer.write("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");writer.newLine();
	            writer.write("<sst xmlns='http://schemas.openxmlformats.org/spreadsheetml/2006/main' count='"+total+"' uniqueCount='"+sharesStrings.size()+"'>");writer.newLine();

	            for(int i=0;i<sharesStrings.size();i++)
	            {
	                String s = (String)sharesStrings.get(i);
	                //replace all & with &amp;
	                s = s.replaceAll("&","&amp;");
	                //replace all < with &lt;
	                s = s.replaceAll("<","&lt;");
	                //replace all > with &gt;
	                s = s.replaceAll(">","&gt;");
	                writer.write("\t<si><t>"+s+"</t></si>");writer.newLine();
	            }
	            writer.write("</sst>");writer.newLine();
	            writer.flush();
	            zos.closeEntry();
	        }

	        /**create the xl/workbook.xml
	        *@throws IOException when there is an io error
	        */
	        private void createWorkbookXML() throws IOException
	        {
	            ZipEntry entry = new ZipEntry("xl/workbook.xml");
	            zos.putNextEntry(entry);                    
	            writer.write("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");writer.newLine();        
	            writer.write("<workbook xmlns='http://schemas.openxmlformats.org/spreadsheetml/2006/main' xmlns:r='http://schemas.openxmlformats.org/officeDocument/2006/relationships'>");writer.newLine();
	            writer.write("\t<sheets>");writer.newLine();
	            int id=1;
	    //        for(Spreadsheet sheet:sheets)
	            for(int i=0;i<sheets.size();i++)
	            {
	                Spreadsheet sheet=(Spreadsheet)sheets.get(i);
	                String data = "<sheet name='"+sheet.getName()+"' sheetId='"+id+"' r:id='rId"+id+"'/>";
	                writer.write("\t\t"+data);writer.newLine();
	                id++;
	            }
	            writer.write("\t</sheets>");writer.newLine();
	            writer.write("</workbook>");writer.newLine();
	            writer.flush();
	            zos.closeEntry();
	        }

	        /**create the xl/_rels/workbook.xml.rels
	        *@throws IOException when there is an io error
	        */
	        private void createXL_rel() throws IOException
	        {        
	            ZipEntry entry = new ZipEntry("xl/_rels/workbook.xml.rels");
	            zos.putNextEntry(entry);                    
	            writer.write("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");writer.newLine();
	            writer.write("<Relationships xmlns='http://schemas.openxmlformats.org/package/2006/relationships'>");writer.newLine();
	            int id=1;
	    //        for(Spreadsheet sheet:sheets)
	            for(int i=0;i<sheets.size();i++)
	            {
	                Spreadsheet sheet=(Spreadsheet)sheets.get(i);
	                String data = "<Relationship Id='rId"+id+"' Type='http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet' Target='worksheets/sheet"+id+".xml'/>";
	                writer.write("\t"+data);writer.newLine();
	                id++;
	            }
	            {
	                String data = "<Relationship Id='rId"+id+"' Type='http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings' Target='sharedStrings.xml'/>";
	                writer.write("\t"+data);writer.newLine();
	            }
	            writer.write("</Relationships>");writer.newLine();
	            writer.flush();
	            zos.closeEntry();
	        }

	        /**create the [Content_Types].xml
	        *@throws IOException when there is an io error
	        */
	        private void creatContentType() throws IOException
	        {
	            ZipEntry entry = new ZipEntry("[Content_Types].xml");
	            zos.putNextEntry(entry);                    
	            writer.write("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");writer.newLine();
	            writer.write("<Types xmlns='http://schemas.openxmlformats.org/package/2006/content-types'>");writer.newLine();
	            writer.write("\t<Default Extension='rels' ContentType='application/vnd.openxmlformats-package.relationships+xml'/>");writer.newLine();
	            writer.write("\t<Default Extension='xml' ContentType='application/xml'/>");writer.newLine();
	            writer.write("\t<Override PartName='/xl/workbook.xml' ContentType='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml'/>");writer.newLine();
	            int id = 1;
	    //        for(Spreadsheet sheet:sheets)
	            for(int i=0;i<sheets.size();i++)
	            {
	                Spreadsheet sheet=(Spreadsheet)sheets.get(i);
	                String data = "<Override PartName='/xl/worksheets/sheet"+id+".xml' ContentType='application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml'/>";
	                writer.write('\t'+data);writer.newLine();
	                id++;
	            }
	            writer.write("\t<Override PartName='/xl/sharedStrings.xml' ContentType='application/vnd.openxmlformats-officedocument.spreadsheetml.sharedStrings+xml'/>");writer.newLine();
	            writer.write("</Types>");writer.newLine();
	            writer.flush();
	            zos.closeEntry();
	        }

	        /**create the .rels file
	        *@throws IOException when there is an io error
	        */
	        private void createRels() throws IOException
	        {
	            ZipEntry entry = new ZipEntry("_rels/.rels");
	            zos.putNextEntry(entry);
	            writer.write("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");writer.newLine();
	            writer.write("<Relationships xmlns='http://schemas.openxmlformats.org/package/2006/relationships'>");writer.newLine();
	            writer.write("\t<Relationship Id='rId1' Type='http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument' Target='xl/workbook.xml'/>");writer.newLine();
	            writer.write("</Relationships>");writer.newLine();
	            writer.flush();
	            zos.closeEntry();
	        }

	        /**save the file
	        *@throws IOException when there is an IOerror
	        */
	        public void save() throws IOException
	        {
	            FileOutputStream outputFile = new FileOutputStream(filename);
	            save(outputFile);
	        }     
	        /**save the file
	        *@throws IOException when there is an IOerror
	        */
	        public void save(OutputStream outputFile) throws IOException
	        {

	            zos = new ZipOutputStream(outputFile);
	            writer = new BufferedWriter(new OutputStreamWriter(zos));
	            createRels();

	            creatContentType();        

	            createXL_rel();
	            createWorkbookXML();
	            createSharedStringsXML();
	            for(int i=0;i<sheets.size();i++)
	            {
	                Spreadsheet sheet =(Spreadsheet) sheets.get(i);
	                createWorksheet(sheet,i+1);
	            }
	            zos.close();
	        }

	        /**
	          *@return the filename value
	          */
	        public String getFilename()
	        {
	            return filename;
	        }

	        /**the list of spreadsheet
	        */

	        //    private List<Spreadsheet> sheets;
	        private List sheets;

	        /**
	          *the filename of the file
	          */
	        private String filename;

	        /**the array of strings used in the workbook
	        */
	    //    private ArrayList<String> sharesStrings;
	        private ArrayList sharesStrings;

	        /**the zipped output stream
	        */
	        private ZipOutputStream zos;

	        /**the root of the zip file
	        */
	        private ZipEntry root;

	        /**the buffered writer
	        */
	        private BufferedWriter writer;

	    }
	    
	    public static class SummaryExcelOutputStream {
	        List data;

	        public SummaryExcelOutputStream(List data) {
	            this.data = data;
	        }

	        public void save(OutputStream os) {
	            System.out.println("Inside save ");
	                SimpleXLSXFile xlsx=new SimpleXLSXFile();
	                SimpleXLSXFile.Spreadsheet sheet1 = xlsx.addNewSheet("Sheet1");
	            	CanonE307ServiceReqSumryAPIUtil utiSrObj = new CanonE307ServiceReqSumryAPIUtil();

	                List h = new ArrayList();
	                
	                h.add("Service Request");
	                h.add("Creation Date");
	                h.add("Task#");
	                h.add("Task Type");
	                h.add("Task Status");
	                h.add("Problem Code");
	                h.add("Problem Note");
	                h.add("Mobile Note");
	                h.add("Primary Meter");
	                h.add("Response Time");
	                h.add("Labor Start");
	                h.add("Labor End");
	                h.add("Problem Code");
	                h.add("Correction Code");
	                h.add("Location Code");
	                h.add("Reason Code");
	                h.add("Machine Status");
	                h.add("Technician");
	                sheet1.addRow(h);
	                System.out.println("After Header ");
	       			for(int i=0;i<data.size();i++){
	       				CanonE307SRTaskHistNewRec histObj = (CanonE307SRTaskHistNewRec)data.get(i);
	       				String creationDt = utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrTskCrtnDt());
	       				String lbrStrtDt = utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrLbrStrt());
	       				String lbrEndDt = utiSrObj.getTmZone(histObj.getStrPostalCd(), histObj.getStrCntryCd(), histObj.getStrLbrEnd());
	                    List r = new ArrayList();
	                    CanonCommonUtil util = new CanonCommonUtil();
	                    r.add(util.checkNull(histObj.getStrFSR() ));
	                    r.add(util.checkNull(creationDt));
	                    r.add(util.checkNull(histObj.getStrTskNum()));
	                    r.add(util.checkNull(histObj.getStrTskTpe()));
	                    r.add(util.checkNull(histObj.getStrTskSts() ));
	                    r.add(util.checkNull(histObj.getStrSrProbCd() ));
	                    r.add(util.checkNull(histObj.getStrSrPblmNt()));
	                    r.add(util.checkNull(histObj.getStrMblNt()));
	                    r.add(util.checkNull(histObj.getStrPrmryMtr()));
	                    r.add(util.checkNull(histObj.getStrRespTm()));
	                    r.add(util.checkNull(lbrStrtDt));
	                    r.add(util.checkNull(lbrEndDt));
	                    r.add(util.checkNull(histObj.getStrTskPblmCd()));
	                    r.add(util.checkNull(histObj.getStrCorctnCd()));
	                    r.add(util.checkNull(histObj.getStrLoctnCd()));
	                    r.add(util.checkNull(histObj.getStrRsnCd()));
	                    r.add(util.checkNull(histObj.getStrMachnSts()));
	                    r.add(util.checkNull(histObj.getStrTechnician()));
	                    sheet1.addRow(r);
	                  
	                    }
	       		 System.out.println("after loop");
	                try {
	                    xlsx.save(os);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	        }

	        public List getData() {
	            return data;
	        }
	    }
	    
	    public static SummaryExcelOutputStream createSummaryExcelOutputStream(List data) throws Exception {
	    	  System.out.println("Inside createSummaryExcelOutputStream");
	        SummaryExcelOutputStream  eos=new SummaryExcelOutputStream(data);
	        return eos;
	    }
	    public static String getUserName(PageContext pageContext, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, S21AuthenticationException {

	        //WebAppsContext objWebAppsContext = WebRequestUtil.validateContext(request, response);
	    	S21SecurityContext context = S21SecurityContextHolder.getContext();
	    	
	        return context.getAuthentication().getIdentityDetails().getUserName();
	    }
	    
	    
	    public static String[] getUserNameAndFullNameS21(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	S21SecurityContext context = S21SecurityContextHolder.getContext();
	    	S21Authentication s21Authentication=context.getAuthentication();
	        String userName = s21Authentication.getIdentityDetails().getUserName();
	        String fullName = s21Authentication.getIdentityDetails().getUID();
	        return new String[]{userName, fullName};
	    }	

		public  CanonE307AccessRoleBean hasASCCRole(String userName) throws Exception { 
			S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
			CanonE307AccessRoleBean rlBeanObj = new CanonE307AccessRoleBean();
			try{
				if (upService.isFunctionGranted(userName, "EXTNE307T030")) 
				{
					rlBeanObj.setHasTechRl("Y");
				}else if(upService.isFunctionGranted(userName, "EXTNE307T040")){
					rlBeanObj.setHasSrvcMngrRl("Y");
				}else if(upService.isFunctionGranted(userName, "EXTNE307T050")){
					rlBeanObj.setHasReadOnly("Y");
				}
			}
			 catch (Exception _ex) {
	             throw new Exception(" Exception while getting access: CanonE307ServReqUtil.java: "+_ex);
	         }
			return rlBeanObj;
		}
}
