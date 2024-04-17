package oracle.apps.custombilling.util;

import canon.apps.common.CanonAsposeUtil;
import canon.excel.cells.Cell;
import canon.excel.cells.Cells;
import canon.excel.cells.Workbook;
import canon.excel.cells.Worksheet;
import canon.excel.cells.Worksheets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;

public class CanonCustomBillingCommon {

    public static String getCurrencyFormat(BigDecimal data) {
        String result = "";

        if (data != null) {
            NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
            double doublePayment = data.doubleValue();
            result = n.format(doublePayment);
        }

        return result;

    }

    public static String getCurrencyFormat(double data) {
        String result = "";

        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        //double doublePayment = data.doubleValue();
        result = n.format(data);

        return result;

    }

    public static String getAddress(String address1, String address2,
            String city, String state, String zipCode) {

        String address = "";

        if (address1 != null && address1.length() > 0) {
            address = address1;
        }

        if (address2 != null && address2.length() > 0) {
            address = address + " " + address2;
        }

        //address = address + "<br>";

        if (city != null && city.length() > 0) {
            address = address + "," + city;
        }

        if (state != null && state.length() > 0) {
            address = address + "," + state;
        }

        if (zipCode != null && zipCode.length() > 0) {
            address = address + "," + zipCode;
        }


        return address;


    }

    public static String getUSFormattedDate(Date ipDate) {

        String textDate = "";

        if (ipDate != null) {
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
            textDate = dateFormatter.format(ipDate);

        }


        return textDate;

    }

    public static String getUSFormattedDate(java.util.Date ipDate) {

        String textDate = "";

        if (ipDate != null) {
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
            textDate = dateFormatter.format(ipDate);

        }


        return textDate;

    }

    public static String nonNullify(String inpStr) {

        String opStr = "";

        if (inpStr != null) {
            opStr = inpStr.trim();
        }

        return opStr;


    }

    public static String naToEmpty(String s) {
        return "N/A".equalsIgnoreCase(s) ? "" : s;
    }

    public static String trimToNull(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        return str.length() == 0 ? null : str;
    }

    public static String genPageLinks(String jsFuncName, String modalName,
            String formName, int totalPages, int currentPage,
            String fontClass, String fontClassInactive,
            int showCount) {

        String firstPage = "&lt;&lt; First";
        String prevPage = "&lt; Prev";
        String nextPage = "Next&gt";
        String lastPage = "Last &gt;&gt;";
        String separator = "&nbsp;\n\t\t\t";
        String tabs = "\t\t\t";
        int beg = 0;
        int end = 0;
        int count = showCount / 2;

        StringBuffer strBuf = new StringBuffer(tabs);

        if (totalPages > 1) {
            if (currentPage != 1) {
                strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                        1, firstPage,
                        fontClass));
                strBuf.append(separator);
                strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                        currentPage - 1, prevPage,
                        fontClass));
            } else {
                strBuf.append("<FONT CLASS=");
                strBuf.append(fontClassInactive);
                strBuf.append(">");
                strBuf.append(firstPage);
                strBuf.append("</FONT>");
                strBuf.append(separator);
                strBuf.append("<FONT CLASS=");
                strBuf.append(fontClassInactive);
                strBuf.append(">");
                strBuf.append(prevPage);
                strBuf.append("</FONT>");
            }
            strBuf.append(separator);
            beg = currentPage - count - 1;
            end = currentPage + count;
            if (beg <= 0) {
                beg = 0;
                end = showCount;
            }
            if (end > totalPages) {
                beg = totalPages - showCount;
                if (beg < 0) {
                    beg = 0;
                }
                end = totalPages;
            }
            for (int i = beg; i < end; i++) {
                int pageNo = i + 1;
                String pageText = String.valueOf(pageNo);
                if (currentPage == pageNo) {
                    strBuf.append("<FONT CLASS=");
                    strBuf.append(fontClassInactive);
                    strBuf.append(">");
                    strBuf.append(pageText);
                    strBuf.append("</FONT>");
                    strBuf.append(separator);
                } else {
                    strBuf.append(genPageNumberLink(jsFuncName, modalName,
                            formName, pageNo, pageText,
                            fontClass));
                    strBuf.append(separator);
                }
            }
            if (currentPage == totalPages) {
                strBuf.append("<FONT CLASS=");
                strBuf.append(fontClassInactive);
                strBuf.append(">");
                strBuf.append(nextPage);
                strBuf.append("</FONT>");
                strBuf.append(separator);
                strBuf.append("<FONT CLASS=");
                strBuf.append(fontClassInactive);
                strBuf.append(">");
                strBuf.append(lastPage);
                strBuf.append("</FONT>");
            } else {
                strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                        currentPage + 1, nextPage,
                        fontClass));
                strBuf.append(separator);
                strBuf.append(genPageNumberLink(jsFuncName, modalName, formName,
                        totalPages, lastPage,
                        fontClass));
            }
        } else {
            strBuf.append("&nbsp;\n");
        }
        return strBuf.toString();
    }

    public static String genPageNumberLink(String jsFuncName, String modalName,
            String formName, int pageNo,
            String pageText, String fontClass) {

        StringBuffer strBuf = new StringBuffer("<A HREF=\"#\" data-page=").append(pageNo);
        if (jsFuncName != null) {
            strBuf.append(" ONCLICK=\"");
            String jsString = genPageJsString(jsFuncName, modalName, formName, pageNo);
            strBuf.append(jsString);
            strBuf.append("\"");
        }
        strBuf.append(" CLASS=");
        strBuf.append(fontClass);
        strBuf.append(">");
        strBuf.append(pageText);
        strBuf.append("</A>");
        return strBuf.toString();
    }

    public static String genPageJsString(String jsFuncName, String modalName,
            String formName, int pageNo) {
        //searchEmployeeInfo('resource-modal',1,'resourceLOV')
        StringBuffer jsStrBuf = new StringBuffer("javascript:");
        jsStrBuf.append(jsFuncName);
        jsStrBuf.append("(");
        if (modalName != null && modalName.length() > 0) {
            jsStrBuf.append("'" + modalName);
            jsStrBuf.append("',");
        }
        jsStrBuf.append(pageNo);
        jsStrBuf.append(",'");
        jsStrBuf.append(formName);
        jsStrBuf.append("');return false;");

        return jsStrBuf.toString();
    }

    public static String getFormattedDate(java.util.Date ipDate, String format) {

        String textDate = "";

        if (ipDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            textDate = sdf.format(ipDate);
        }


        return textDate;

    }

    public static java.util.Date getUtilParsedDate(String str, String format) {

        java.util.Date dateObj = null;

        if (str != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                dateObj = sdf.parse(str);
            } catch (ParseException ex) {
                dateObj = null;
            }
        }


        return dateObj;

    }

    public static Date getSqlParsedDate(String str, String format) {

        java.util.Date dateObj = null;
        java.sql.Date sqlObj = null;

        if (str != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                dateObj = sdf.parse(str);
                sqlObj = new java.sql.Date(dateObj.getTime());
            } catch (ParseException ex) {
                sqlObj = null;
            }
        }


        return sqlObj;

    }

    public static String removeComma(String s) {
        return s == null ? "" : s.replaceAll(",", "");
    }
    static Pattern NON_INT_PATTERN = Pattern.compile("[^0-9]");

    public static int toInt(String strInt) {
        if (strInt != null) {
            try {
                Matcher matcher = NON_INT_PATTERN.matcher(strInt);
                if (matcher.find()) {
                    String cleanStr = matcher.replaceAll("");
                    return Integer.parseInt(cleanStr);
                } else {
                    return Integer.parseInt(strInt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
    static BigDecimal BigDecimal_ZERO = new BigDecimal("0");

    public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
        if (s != null && s.trim().length() > 0) {
            try {
                return new BigDecimal(s.trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return nullToZero ? BigDecimal_ZERO : null;
    }

    static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");

    public static Timestamp toTimestamp(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(dateFormat.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    
    public static String genPaginationSummary(int totalRecords, int recordsPerPage, int currentPage) {
        if (totalRecords == 0) {
            return "";
        }
        int start = (currentPage - 1) * recordsPerPage + 1;
        if (start > totalRecords || start <= 0) {
            return "<span class='pagination-summary'>" + NumberFormat.getInstance().format(totalRecords) + " records </span>";
        }
        int end = currentPage * recordsPerPage > totalRecords ? totalRecords : currentPage * recordsPerPage;
        return "<span class='pagination-summary'>" + start + "-" + end + " of " + NumberFormat.getInstance().format(totalRecords) + " records </span>";
    }

    public static String genPageLinks(String jsFuncName,
            String modalName,
            String formName,
            int totalPages, int currentPage) {
        String fontClass = "linkPage";
        String fontClassInactive = "linkPageInactive";

        int showCount = CanonCustomBillingConstants.LINKS_FOR_PAGINATION;
        return genPageLinks(jsFuncName,
                modalName,
                formName, totalPages, currentPage,
                fontClass, fontClassInactive, showCount);
    }

    public static Object first(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 1 ? null : objs[0];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 1 ? null : l.get(0);
        }
        return null;
    }

    public static Object second(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 2 ? null : objs[1];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 2 ? null : l.get(1);
        }
        return null;
    }

    public static String capital(String s) {
        String c = nonNullify(s).toLowerCase();
        if (c.length() > 1) {
            return c.substring(0, 1).toUpperCase() + c.substring(1);
        } else {
            return c;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String fullName(String lastName, String firstName) {
    	System.out.println(" lastName "+lastName);
    			System.out.println(" firstName "+firstName);
    					
    	System.out.println(" full name "+ (isEmpty(lastName) ? (isEmpty(firstName)? "": nonNullify(firstName)) : nonNullify(lastName) + ", " + nonNullify(firstName)));
        return isEmpty(lastName) ? (isEmpty(firstName)? "": nonNullify(firstName)) : nonNullify(lastName) + ", " + nonNullify(firstName);
    }
    static final int BUFF_SIZE = 100000;
    static final byte[] buffer = new byte[BUFF_SIZE];

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        try {
            while (true) {
                synchronized (buffer) {
                    int amountRead = in.read(buffer);
                    if (amountRead == -1) {
                        break;
                    }
                    out.write(buffer, 0, amountRead);
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    public static class URLOutputStream {
        private Workbook workbook;

        public URLOutputStream(String fullInvoicePath) throws Exception {
            InputStream is= new FileInputStream(fullInvoicePath);
            workbook =canon.excel.cells.CellsHelper.newWorkbook();
            workbook.open(is);
            is.close();
        }

        public void saveText(PrintWriter writer){
            
            Worksheets wss=workbook.getWorksheets();
            Worksheet ws=wss.getSheet(1);
            Cells cs=ws.getCells();
            int colCount=cs.getMaxColumn();
            int rowCount=cs.getMaxRow()-1;

            for(int row=0;row<rowCount;row++){
                for(int col=0;col<=colCount;col++){
                    Cell cell=cs.getCell(row,col);
                    String cellValue=getCellStringValue(cell);
                    if(col==0){
                        writer.print("\"" + escapeCSV(cellValue) + "\"" );
                    }else{
                        writer.print(",\"" + escapeCSV(cellValue) + "\"");
                    }
                }
                writer.println();
            }
            writer.flush();
            writer.close();
        }
        
        
        public int getSize() {
            return workbook.getWorksheets().size();
        }

        public String getTextFileName() {
            return workbook.getWorksheets().getSheet(1).getName()+".txt";
        }
        
    }
    
    private static String getCellStringValue(Cell cell){
        return cell.getStringValue();
    }
    
    public static String escapeCSV(String s) {
        return s == null ? "" : s.replaceAll("\"", "\"\"");
    }
    
    public static class FileStream {

        String file;
        List extras;

        public FileStream(String file) throws IOException {
            this.file = file;
            if(!new File(file).exists()){
                throw new FileNotFoundException(file);
            }
        }

        public void save(OutputStream out) throws Exception {
            if (extras == null || extras.size() == 1) {
                copyStream(new FileInputStream(file), out);
            } else {
            	CanonAsposeUtil.loadLicense();
                Workbook workbook = canon.excel.cells.CellsHelper.newWorkbook();
                try {
                    System.out.println("openning " + file);
                    workbook.open(file);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }

                for (int i = 0; i < extras.size(); i++) {
                    String e = (String) extras.get(i);
                    System.out.println("combining extra " + e);
                    Workbook wbk = canon.excel.cells.CellsHelper.newWorkbook();
                    try {
                        wbk.open(e);
                        workbook.combine(wbk);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
                workbook.save(out);
            }
        }

        public void addExtra(String e) {
            if (extras == null) {
                extras = new ArrayList();
            }
            extras.add(e);
        }
    }
    public static String BSD_PATH = "CANON_E473_BSD_PATH";
    public static String ASPOSE_LIC_FILE = "Aspose.Total.Java.lic";
    static boolean lincenseLoaded = false;

    static Properties envVars = envVars();

    public static Properties envVars() {
        Process p = null;
        Runtime r = Runtime.getRuntime();
        String OS = System.getProperty("os.name").toLowerCase();
        try {
            if (OS.indexOf("windows 9") > -1) {
                p = r.exec("command.com /c set");
            } else if ((OS.indexOf("nt") > -1)
                    || (OS.indexOf("windows 2000") > -1)
                    || (OS.indexOf("windows xp") > -1)) {
                p = r.exec("cmd.exe /c set");
            }
            if (p != null) {
                Properties envs = new Properties();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    int idx = line.indexOf('=');
                    String key = line.substring(0, idx);
                    String value = line.substring(idx + 1);
                    envs.setProperty(key, value);
                }
                return envs;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static public String getProfileLocal(String profileName) throws Exception {
        return (String) envVars.get(profileName);
    }

    static public String getProfile(String profileName) throws Exception {
        if (envVars != null) {
            return getProfileLocal(profileName);
        }

        String s1 = "BEGIN CANON_E479_CUST_BILL_UTIL_PKG.get_profile_value(?,?); END;";
        String s2 = null;
        Connection connection = null;
        CallableStatement oraclecallablestatement = null;



        try {
            connection = TransactionScope.getConnection();
            oraclecallablestatement = (CallableStatement) connection.prepareCall(s1);
            oraclecallablestatement.setString(1, profileName);
            oraclecallablestatement.registerOutParameter(2, OracleTypes.VARCHAR);
            oraclecallablestatement.execute();
            s2 = oraclecallablestatement.getString(2);


        } catch (SQLException sqlexception) {
            s2 = null;
            throw new Exception(sqlexception);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
                if (oraclecallablestatement != null) {
                    oraclecallablestatement.close();
                }
                if (connection != null) {
                    TransactionScope.releaseConnection(connection);
                }
            } catch (SQLException _ex) {
                throw new Exception(_ex);
            }
        }
        return s2;
    }
    
    //http://[_0-9a-zA-Z\.\/]*\.xls
    static Pattern PATTERN_XLS_PATH = Pattern.compile("http://[_0-9a-zA-Z\\.\\/]*\\.xls"); 
    public static boolean isXlsPath(String str){
        if(str==null)return false;
        Matcher m=PATTERN_XLS_PATH.matcher(str);
        return m.matches();
    }

    public static void main(String args[]){
        System.out.println(isXlsPath("http://invoiceprint.dev.cusa.canon.com/inidev/custombilling/2012/e479/3/395_195.xls"));
        System.out.println(isXlsPath("http://invoiceprint.dev.cusa.canon.com/inidev/custombilling/2012/e479/3/395_195.xls<H1>"));
        System.out.println(isXlsPath(null));
        System.out.println(isXlsPath("http://.xls"));
    }
}
