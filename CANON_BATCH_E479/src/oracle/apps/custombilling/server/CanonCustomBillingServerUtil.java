package oracle.apps.custombilling.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonCustomBillingServerUtil {

    public static BigDecimal BigDecimal_ZERO = new BigDecimal("0");
    static Properties envVars = envVars();
    public static String fileSeperator = System.getProperty("file.separator");
    static int calendarYear = Calendar.getInstance().get(Calendar.YEAR);

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

    public String getProfileLocal(String profileName) throws Exception {
        return (String) envVars.get(profileName);
    }

    public BigDecimal getBigDecimalProfile(BigDecimal userId,String userName,String roleName,String profileName) throws Exception {
        String p = getProfile(userId,userName,roleName,profileName);
        return toBigDecimal(p, true);
    }

    public String getProfile(BigDecimal userId,String userName,String roleName,String profileName) throws Exception {
        if (envVars != null) {
            return getProfileLocal(profileName);
        }
        String s1 = "BEGIN CANON_E479_CUST_BILL_UTIL_PKG.get_profile_value(?,?,?,?,?,?,?); END;";
        //String s1 = "BEGIN CANON_E479_CUST_BILL_UTIL_PKG.get_profile_value (?,?); END;";
        String s2 = null;
        Connection connection = null;
        CallableStatement oraclecallablestatement = null;

        try {
        	connection = TransactionScope.getConnection();
            oraclecallablestatement = connection.prepareCall(s1);
            oraclecallablestatement.setBigDecimal(1, userId);
            oraclecallablestatement.setString(2, userName);
            oraclecallablestatement.setString(3, roleName);  
            oraclecallablestatement.setString(4, profileName);
            oraclecallablestatement.registerOutParameter(5, OracleTypes.VARCHAR);
            oraclecallablestatement.registerOutParameter(6, OracleTypes.VARCHAR);
            oraclecallablestatement.registerOutParameter(7, OracleTypes.VARCHAR);
            oraclecallablestatement.execute();
            s2 = oraclecallablestatement.getString(5);



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

    public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
        if (s != null) {
            try {
                return new BigDecimal(s.trim());
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

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
    public static String formatDate(java.util.Date d) {
        String strDate = "";
        if (d != null) {
            try {
                strDate = sdf1.format(d);
            } catch (Exception ex) {
                strDate = "";
            }
        }
        return strDate;
    }

    private static SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
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
    
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
    
    static Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");

    public static String trimAll(String str) {
        return PATTERN_WHITESPACES.matcher(str).replaceAll("");
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String getInvoiceFileName(String customerName, String parentCustomerName,String CustomerGroup, String invoiceBreak, String billPeriod, String urnNumber) {
    	String custName =null;
    	System.out.println("Inside getInvoiceFileName: customerName: "+customerName+"|parentCustomerName: "+parentCustomerName+"|CustomerGroup: "+CustomerGroup);
    	
    	if ( (!(isEmpty(customerName ))) &&
          	     (!(isEmpty(customerName.trim())))){
    		custName = customerName;
    	}
    	System.out.println(" 1st assignment: "+custName);

    	if ( (!(isEmpty(parentCustomerName ))) &&
         	     (!(isEmpty(parentCustomerName.trim())))){
    		if ( (!(isEmpty(custName ))) &&
            	     (!(isEmpty(custName.trim())))){
    			custName.concat("-"+parentCustomerName);
    		}
    		else {
    		custName = parentCustomerName;
    		}
    	}
    	System.out.println(" 2nd assignment: "+custName);
    	if ( (!(isEmpty(CustomerGroup ))) &&
        	     (!(isEmpty(CustomerGroup.trim())))){
   		if ( (!(isEmpty(custName ))) &&
           	     (!(isEmpty(custName.trim())))){
    			custName.concat("-"+CustomerGroup);
    		}
    		else {
    		custName = CustomerGroup;
    		}
    	}
    	System.out.println(" 3rd assignment: "+custName);
    	System.out.println("InvoiceFile Name: "+ custName
                + ( isEmpty(nonNullify(invoiceBreak)) ? "" : "-" + nonNullify(invoiceBreak) )
                + "-" + nonNullify(billPeriod)
                + " " + formatDate3(new Date())+" "+nonNullify(urnNumber)+ ".xls");
        return (custName
                + ( isEmpty(nonNullify(invoiceBreak)) ? "" : "-" + nonNullify(invoiceBreak) )
                + "-" + nonNullify(billPeriod)
                + " " + formatDate3(new Date())+" "+nonNullify(urnNumber)+ ".xls");   
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
            boolean characterWithSpecialMeaningInXML = ch == '<' || ch == '&' || ch == '>';

            if (characterWithSpecialMeaningInXML || unicodeButNotAscii || controlCharacter) {
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

    public static void resultSetToXML(String sql, ResultSet rs, PrintWriter writer) throws SQLException {
        writer.println("<?xml version = '1.0'?>");
        writer.println("<!-- " + protectSpecialCharacters(sql) + " -->");
        writer.println("<ROWSET>");
        while (rs.next()) {
            writer.println("  <ROW>");
            ResultSetMetaData md = rs.getMetaData();
            int c = md.getColumnCount();
            for (int i = 1; i <= c; i++) {
                String name = md.getColumnName(i);
                String s = protectSpecialCharacters(nonNullify(rs.getString(i)));
                writer.println("    <" + name + ">" + s + "</" + name + ">");
            }
            writer.println("  </ROW>");
        }
        writer.println("</ROWSET>");
    }

    public static void sqlToHTML(String sql, PrintWriter writer) throws Exception {
        Connection conn = TransactionScope.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        resultSetToHTML(rs, writer);
    }

    public static void resultSetToHTML(ResultSet rs, PrintWriter writer) throws SQLException {
        writer.println("<table>");
        boolean firstRow = true;
        int colcount = 0;
        while (rs.next()) {
            if (firstRow) {
                writer.println("<tr>");
                ResultSetMetaData md = rs.getMetaData();
                colcount = md.getColumnCount();
                for (int i = 1; i <= colcount; i++) {
                    String name = md.getColumnName(i);
                    writer.println("<th>" + name + "</th>");
                }
                writer.println("</tr>");
                firstRow = false;
            }
            writer.println("<tr>");
            for (int i = 1; i <= colcount; i++) {
                String s = protectSpecialCharacters(nonNullify(rs.getString(i)));
                writer.println("<td>" + s + "</td>");
            }
            writer.println("</tr>");
        }
        writer.println("</table>");
    }

    public static void printClassPath() {
        printClassPath(ClassLoader.getSystemClassLoader(), new PrintWriter(System.out, true));
    }

    public static String typeToFolder(String billType) {
        if ("RS01".equals(billType)) {
            return "om";
        }
        if ("RS02".equals(billType)) {
            return "oks";
        }
        if ("BILL".equals(billType)) {
            return "bill";
        }
        return billType;
    }

    public static String idToPath(long seq, String fileSeperator) {
        String path = fileSeperator;
        seq = seq / 100;
        while (seq > 0) {
            path = fileSeperator + seq % 100 + path;
            seq = seq / 100;
        }
        return path;
    }

    public static String join(List list, String seperator) {
        StringBuffer sb = new StringBuffer();
        for (Iterator i = list.iterator(); i.hasNext();) {
            String s = CanonCustomBillingServerUtil.nonNullify((String) i.next());
            if (sb.length() == 0) {
                sb.append(s);
            } else {
                sb.append(seperator).append(s);
            }
        }
        return sb.toString();
    }

    static public String getPath(BigDecimal invId, String urnNum) {
        String idpath = idToPath(toBigDecimal(urnNum, true).longValue(), fileSeperator);
        String path = "custombilling" + fileSeperator + calendarYear+ fileSeperator + "e479" + idpath + urnNum + "_" + invId + ".xls";
        return path;
    }

    static InputStream getTemplateInputStream(String templateFileName) throws FileNotFoundException{
        String initialPath = (String) first(CanonCustomBillingServerInvoiceDAO.getInitialPath("SERVER"));
        String templateFile =initialPath + fileSeperator + "custombillingTemplate" +  fileSeperator + templateFileName;
        return new FileInputStream(templateFile);
    }
    
    public static String runtimeExecute(String[] args) throws Exception {
        Runtime r = Runtime.getRuntime();
        final Process p = r.exec(args);
        final StringBuffer buffer = new StringBuffer();
        new Thread(new Runnable() {

            public void run() {
                try {
                    String out = loadStream(p.getInputStream());
                    buffer.append(out);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        int rc = p.waitFor();
        return buffer.toString();
    }

    private static String loadStream(InputStream s) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(s));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public static void printFiles(String printerName, String files[]) throws Exception {
        String cmd[] = new String[]{"/usr/bin/lp", "-o", "sides=two-sided-long-edge", "-d", printerName};
        String[] fullCommand = new String[cmd.length + files.length];
        System.arraycopy(cmd, 0, fullCommand, 0, cmd.length);
        System.arraycopy(files, 0, fullCommand, cmd.length, files.length);
        System.out.println(runtimeExecute(fullCommand));
    }
    
    public static void printConnectionInfo(){
	    Connection conn = null;
	    try {
			conn = TransactionScope.getConnection();
		    System.out.println(" use TransactionScope conn =" + conn);
		    if (conn != null) {
		    	java.sql.DatabaseMetaData md = conn.getMetaData();
		        System.out.println(" DatabaseMetaData:" + md);
		        System.out.println(" URL:" + md.getURL());
		        System.out.println(" Username:" + md.getUserName());
		        System.out.println(" DatabaseProductName:" + md.getDatabaseProductName());
		        System.out.println(" DatabaseProductVersion:" + md.getDatabaseProductVersion());
		        System.out.println(" DatabaseMajorVersion:" + md.getDatabaseMajorVersion());
		        System.out.println(" DatabaseMinorVersion:" + md.getDatabaseMinorVersion());
		        System.out.println(" JDBCMajorVersion:" + md.getJDBCMajorVersion());
		        System.out.println(" JDBCMinorVersion:" + md.getJDBCMinorVersion());
		    } else {
		    	System.out.println("conn is null. ");
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	        if (conn != null) {
	            try {
	                TransactionScope.releaseConnection(conn);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
		}
    }

}
