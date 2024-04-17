package oracle.apps.e008.item.process;

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

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

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

public class CanonE008ItemProcessUtil {

    public static final int LINKS_FOR_PAGINATION = 15;
    public static BigDecimal BigDecimal_ZERO = new BigDecimal("0");
    static String fileSeperator = File.separator;
    static int calendarYear = Calendar.getInstance().get(Calendar.YEAR);
    static final String EXPENSE_ITEM_CODE="EXPENSE";
    
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
                    || (OS.indexOf("windows xp") > -1)
                    || (OS.indexOf("windows 7") > -1)) {
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

    
    public BigDecimal getBigDecimalProfile(String profileName) throws Exception {
        String p = getProfile(profileName);
        return toBigDecimal(p, true);
    }

    public BigDecimal getBigDecimalProfile(String profileName, BigDecimal defaultValue) throws Exception {
        String p = getProfile(profileName);
        BigDecimal bd = toBigDecimal(p, false);
        return (bd == null ? defaultValue : bd);
    }

    static public int getIntProfile(String profileName, int defaultVal) throws Exception {
        String p = getProfile(profileName);
        return p == null ? defaultVal : toInt(p);
    }

    public static String getProfileLocal(String profileName) throws Exception {
        return (String) envVars.get(profileName);
    }

    public static String getProfile(String profileName) throws Exception {
        return envVars != null?
            getProfileLocal(profileName):
            getDBProfile(profileName);
    }
    
    public  boolean hasRole(String userName) throws Exception { 
		S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
		boolean isJspAccessValid =false;
		try{
			String[] functionNames={// Function names
							 //"EXTNE008T010",
							 //"EXTNE008T020",
							 "EXTNE008T030",
							 "EXTNE008T040",
							 "EXTNE008T050",
							 "EXTNE008T060",
							 "EXTNE008T070",
							 "EXTNE008T080", 
							 "EXTNE008T090", 
							 "EXTNE008T100",
							 "EXTNE008T110",  
							 "EXTNE008T120"
					};	
				for(String function:functionNames)
				{	
			        if (upService.isFunctionGranted(userName, function)) 
					{
						isJspAccessValid = true;
						System.out.println("Mapped function Names:"+function);
					}
				}  
		}
		 catch (Exception _ex) {
             throw new Exception(" Exception while getting approval role: CanonCustomBilingUtilJava: "+_ex);
         }
		return isJspAccessValid;
	}
    
    public static String getDBProfile(String profileName) throws Exception {
        
        String s1 = "BEGIN FND_PROFILE.GET(?,?); END;";
        String s2 = null;
        Connection connection = null;
        OracleCallableStatement oraclecallablestatement = null;

        try {
            connection = TransactionScope.getConnection();
            oraclecallablestatement = (OracleCallableStatement) connection.prepareCall(s1);
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

    /**
     * json Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000 through U+001F).
     * @param s
     * @return 
     */
    public static String escape(String s){
            if(s==null)
			return null;
        StringBuffer sb = new StringBuffer();
        escape(s, sb);
        return sb.toString();
    }
    
    static void escape(String s, StringBuffer sb) {
            for(int i=0;i<s.length();i++){
                    char ch=s.charAt(i);
                    switch(ch){
                    case '"':
                            sb.append("\\\"");
                            break;
                    case '\\':
                            sb.append("\\\\");
                            break;
                    case '\b':
                            sb.append("\\b");
                            break;
                    case '\f':
                            sb.append("\\f");
                            break;
                    case '\n':
                            sb.append("\\n");
                            break;
                    case '\r':
                            sb.append("\\r");
                            break;
                    case '\t':
                            sb.append("\\t");
                            break;
                    case '/':
                            sb.append("\\/");
                            break;
                    default:
            //Reference: http://www.unicode.org/versions/Unicode5.1.0/
                            if((ch>='\u0000' && ch<='\u001F') || (ch>='\u007F' && ch<='\u009F') || (ch>='\u2000' && ch<='\u20FF')){
                                    String ss=Integer.toHexString(ch);
                                    sb.append("\\u");
                                    for(int k=0;k<4-ss.length();k++){
                                            sb.append('0');
                                    }
                                    sb.append(ss.toUpperCase());
                            }
                            else{
                                    sb.append(ch);
                            }
                    }
            }//for
    }
    
    public static String nonNullify(String inpStr) {

        String opStr = "";

        if (inpStr != null) {
            opStr = inpStr.trim();
        }

        return opStr;


    }

    public static boolean isValidNumber(String inpStr) {

        boolean flag = true;

        try {
            int number = Integer.parseInt(inpStr);

            flag = true;

        } catch (Exception ex) {
            flag = false;
        }



        return flag;


    }

    public static boolean isValidDouble(String inpStr) {

        boolean flag = true;

        try {
            double number = Double.parseDouble(inpStr);
            if (!closeToZero(number)) {
                flag = true;
            }

        } catch (Exception ex) {
            flag = false;
        }



        return flag;


    }

    public static boolean isValidBigDecimal(String inpStr) {
        return !(toBigDecimal(inpStr, false) == null);
    }

    public static boolean closeToZero(double d) {
        return Math.abs(d - 0f) < 0.00001;
    }

    public static boolean isZero(BigDecimal d) {
        return d.compareTo(BigDecimal_ZERO) == 0;
    }

    public static String capital(String c) {
        return c.substring(0, 1).toUpperCase() + c.substring(1);
    }

    // [+-]?
    // (?:
    //  (?:0)|                              Zero
    //  (?:[123456789][\\d,]*\\d*)|         Integer
    //  (?:[\\d,]*\\.\\d*)                  Number with decimal places
    // )
    static Pattern PARRETN_DEIMAL_NUM = Pattern.compile("[+-]?(?:(?:0)|(?:[123456789][\\d,]*\\d*)|(?:[\\d,]*\\.\\d*))");

    private static boolean isNumber(String str) {
        if(isEmpty(str))return false;
        Matcher matcher = PARRETN_DEIMAL_NUM.matcher(str);
        return matcher.matches();
    }
    
    public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
        if (s != null) {
            try {
                return new BigDecimal(s.trim().replaceAll(",",""));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return nullToZero ? BigDecimal_ZERO : null;
    }

    public static BigDecimal toBigDecimal(String s, boolean nullToZero, int scale) {
        BigDecimal bd = toBigDecimal(s, nullToZero);
        return bd == null || isZero(bd) ? bd : bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

  public static  String formatCurrency(BigDecimal c){
      if(c==null)
          return "";
      String result = "";
      NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
      result = n.format(c);
      return result;
  }

    static DecimalFormat df1 = new DecimalFormat( "#,###,###,##0.00" );
    public static  String formatBigDecimal(BigDecimal c){
        return c==null? "": df1.format(c.doubleValue());
    }

    static DecimalFormat df2 = new DecimalFormat( "#,###,###,###.####" );
    public static  String formatBigDecimal2(BigDecimal c){
        return c==null? "": df2.format(c.doubleValue());
    }
    
    static DecimalFormat df3 = new DecimalFormat( "#,###,###,##0.00000" );
    public static  String formatBigDecimal3(BigDecimal c){
        return c==null? "": df3.format(c.doubleValue());
    }

    
    static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    public static String formatDate(java.util.Date d) {
        String strDate = "";
        if (d != null) {
            try {
                strDate = sdf.format(d);
            } catch (Exception ex) {
                strDate = "";
            }
        }
        return strDate;
    }

    public static Timestamp toTimestamp(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    static SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
    public static String formatDate2(java.util.Date d) {
        String strDate = "";
        if (d != null) {
            try {
                strDate = sdf2.format(d);
            } catch (Exception ex) {
                strDate = "";
            }
        }
        return strDate;
    }

    static SimpleDateFormat sdf3 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    public static String formatDate3(java.util.Date d) {
        String strDate = "";
        if (d != null) {
            try {
                strDate = sdf3.format(d);
            } catch (Exception ex) {
                strDate = "";
            }
        }
        return strDate;
    }

    static SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    public static String formatDate4(java.util.Date d) {
        String strDate = "";
        if (d != null) {
            try {
                strDate = sdf4.format(d);
            } catch (Exception ex) {
                strDate = "";
            }
        }
        return strDate;
    }

    public static Timestamp toTimestamp4(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf4.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static Timestamp toTimestamp2(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf2.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }
    
    static Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");

    public static String trimAll(String str) {
        return PATTERN_WHITESPACES.matcher(str).replaceAll("");
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String getInvoiceFileName(String customerName, String invoiceBreak, String urn) {
        return trimAll(nonNullify(customerName)) + "_"
                + (isEmpty(trimAll(nonNullify(invoiceBreak))) ? "Missing" : trimAll(nonNullify(invoiceBreak))) + "_"
                + trimAll(nonNullify(urn)) + ".xls";
    }

    public static String clobToString(Clob clob) {
        if (clob == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer("");
        try {
            java.io.Reader reader = clob.getCharacterStream();
            BufferedReader bufferedreader = new BufferedReader(reader);
            String s;
            while (null != (s = bufferedreader.readLine())) {
                sb.append(s);
            }
            bufferedreader.close();
            reader.close();
        } catch (Exception e) {
            return sb.toString();
        }
        return sb.toString();
    }

    public static InputStream clobToInputStream(Clob clob) {
        try {
            return clob.getAsciiStream();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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

    public static Object third(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 3 ? null : objs[2];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 3 ? null : l.get(2);
        }
        return null;
    }
    static final int BUFF_SIZE = 1000000;
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

    public static int copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[BUFF_SIZE];
        int count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
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

    public static void prettyPrint(Clob clob, OutputStream outputStream) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(outputStream);
        StreamSource source = new StreamSource(clob.getAsciiStream());
        transformer.transform(source, result);
    }

    public static void printClassPath(ClassLoader classLoader, PrintWriter printWriter) {
        URL[] urls = ((URLClassLoader) classLoader).getURLs();
        for (int i = 0; i < urls.length; i++) {
            printWriter.println(urls[i].getFile());
        }
    }

    public static String protectSpecialCharacters(String originalUnprotectedString) {
        if (originalUnprotectedString == null) {
            return null;
        }
        boolean anyCharactersProtected = false;

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < originalUnprotectedString.length(); i++) {
            char ch = originalUnprotectedString.charAt(i);

            boolean controlCharacter = ch < 32;
            boolean unicodeButNotAscii = ch > 126;
            /*boolean characterWithSpecialMeaningInXML = ch == '<' || ch == '&' || ch == '>';*/
            boolean characterWithSpecialMeaningInXML = ch == '<' || ch == '"'|| ch == '&' || ch == '>'
            										|| ch == ':'|| ch == '#'|| ch == '*'|| ch == '$'|| ch == '+'
            										|| ch == '-'|| ch == ','|| ch == '.'|| ch == ';'|| ch == '/'
            										|| ch == '\\'|| ch == '<'|| ch == '>'|| ch == '?'|| ch == '!'
            										|| ch == '('|| ch == ')'|| ch == '@'|| ch == '+'|| ch == '$'
            										|| ch == '['|| ch == ']';

            if (characterWithSpecialMeaningInXML || unicodeButNotAscii || controlCharacter) {
                //stringBuffer.append("&quot"+ ";");
                stringBuffer.append("&#" + (int) ch + ";");
                anyCharactersProtected = true;
            } else {
                stringBuffer.append(ch);
            }
        }
        if (anyCharactersProtected == false) {
            return originalUnprotectedString;
        }

        return stringBuffer.toString();
    }

    public static String join(List list, String seperator) {
        StringBuffer sb = new StringBuffer();
        for (Iterator i = list.iterator(); i.hasNext();) {
            String s = nonNullify((String) i.next());
            if (sb.length() == 0) {
                sb.append(s);
            } else {
                sb.append(seperator).append(s);
            }
        }
        return sb.toString();
    }

    public static String genPaginationSummary(int totalRecords, int recordsPerPage, int currentPage) {
        if (totalRecords == 0) {
            return "";
        }
        int start = (currentPage - 1) * recordsPerPage + 1;
        if (start > totalRecords || start <= 0) {
            return "<span class='pagination-summary'>" + NumberFormat.getInstance().format(totalRecords) + " </span>";
        }
        int end = currentPage * recordsPerPage > totalRecords ? totalRecords : currentPage * recordsPerPage;
        return "<span class='pagination-summary'>" + start + "-" + end + " of " + NumberFormat.getInstance().format(totalRecords) + " </span>";
    }

    public static String genPageLinks(String jsFuncName,
            String modalName,
            String formName,
            int totalPages, int recordsPerPage, int currentPage, int totalRecords) {
        String fontClass = "linkPage";
        String fontClassInactive = "linkPageInactive";

        int showCount = LINKS_FOR_PAGINATION;
        return genPageLinks(jsFuncName,
                modalName,
                formName, totalPages, recordsPerPage, currentPage, totalRecords,
                fontClass, fontClassInactive, showCount);
    }

    public static String genPageLinks(String jsFuncName, String modalName,
            String formName, int totalPages, int recordsPerPage, int currentPage, int totalRecords,
            String fontClass, String fontClassInactive,
            int showCount) {

        String firstPage = "First";
        String prevPage = "Previous ";
        String nextPage = "Next ";
        String lastPage = "Last";
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
            if (showCount > 0) {
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
            }
            if (currentPage == totalPages) {
                strBuf.append("<FONT CLASS=");
                strBuf.append(fontClassInactive);
                strBuf.append(">");
                if (showCount == 0) {
                    strBuf.append(genPaginationSummary(totalRecords, recordsPerPage, currentPage));
                }
                strBuf.append(separator);
                strBuf.append(nextPage);
                strBuf.append("</FONT>");
                strBuf.append(separator);
                strBuf.append("<FONT CLASS=");
                strBuf.append(fontClassInactive);
                strBuf.append(">");
                strBuf.append(lastPage);
                strBuf.append("</FONT>");
            } else {
                if (showCount == 0) {
                    strBuf.append(genPaginationSummary(totalRecords, recordsPerPage, currentPage));
                }
                strBuf.append(separator);
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
    
    
    public static String fullName(String lastName, String firstName) {
        return isEmpty(lastName) ? nonNullify(firstName) : nonNullify(lastName) + ", " + nonNullify(firstName);
    }

    public static String phoneNumber(String areaCode, String number) {
        return isEmpty(areaCode) || isEmpty(trimAll(areaCode)) ? nonNullify(number) : "("+nonNullify(areaCode) + ") " + nonNullify(number);
    }

    public static String ittOrderProcessor(String id, String name) {
        return isEmpty(id) ? nonNullify(name) :  nonNullify(name)+ " ("+nonNullify(id) + ")" ;
    }

    public static String dealerCode(String id, String name) {
        return isEmpty(name) ? nonNullify(id) :  nonNullify(id)+ " ("+nonNullify(name) + ")" ;
    }
 


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
//                            try
//                            {
//                                double d = Double.parseDouble(s);
//                                //check if 1st character is 0
//                                //if it is 0xxx, we do not want to save as number
//                                if ((s.length()>0)&&(s.charAt(0)=='0')&&(s.length()!=1))//0 should be a double
//                                {
//                                    //not a number, add as string
//                                    list.add(new StringType(s));
//                                }
//                                else
//                                {
//                                    list.add(new DoubleType(d));
//                                }
//                            }
//                            catch(NumberFormatException e)
//                            {
//                                //not a number, add as string
//                                list.add(new StringType(s));
//                            }
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
    
    
    public static List asList(Object[] array){
        ArrayList l=new ArrayList();
        for(int i=0;i<array.length;i++){
            l.add(array[i]);
        }
        return l;
    }
    
        public static void main(String[] args)
        {
            try
            {
                SimpleXLSXFile xlsxFile=new SimpleXLSXFile("testXLSX.xlsx");
                SimpleXLSXFile.Spreadsheet sheet1 = xlsxFile.addNewSheet("a1");

                ArrayList row = new ArrayList();
                row.add("abc");
                row.add(""+37.5);
                sheet1.addRow(row);
                ArrayList row2 = new ArrayList();
                row2.add("x");
                row2.add("y");
                row2.add("z");
                sheet1.addRow(row2);
                ArrayList row3 = new ArrayList();
                row3.add("1.1");
                row3.add("1");
                row3.add("$A$3+$B$3");
                sheet1.addRow(row3);
                sheet1.setCellAsFormula(2,2);
                xlsxFile.save();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
            
    
}
