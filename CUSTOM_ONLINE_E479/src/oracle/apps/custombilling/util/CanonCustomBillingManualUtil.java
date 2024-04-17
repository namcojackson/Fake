/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.apps.custombilling.util;

import canon.apps.pci.util.CanonSecurityUtil;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.Request;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
import java.text.DateFormat;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import oracle.apps.custombilling.dao.CanonCustomBillingManualDAO;
import oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO;

public class CanonCustomBillingManualUtil {

    public static BigDecimal BigDecimal_ZERO = new BigDecimal("0");
    static String fileSeperator = System.getProperty("file.separator");
    static String serverInitialPath = (String) CanonCustomBillingCommon.first(CanonCustomBillingManualDAO.getServerInitialPath("ONLINE")) + File.separator + "custombilling";
    // Removed the server initial path as it will be derived from profile.
    static String initialPath =  "custombilling";
    static int calendarYear = Calendar.getInstance().get(Calendar.YEAR);

    static Pattern PATTERN_XLSX_PATH = Pattern.compile("http://[_0-9a-zA-Z\\.\\/]*\\.xlsx"); 
    public static boolean isXlsxPath(String str){
        if(str==null)return false;
        Matcher m=PATTERN_XLSX_PATH.matcher(str);
        return m.matches();
    }
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    public static Timestamp toTimestamp(String tsStr) {
        try {
            return tsStr == null || tsStr.trim().length() == 0 ? null : new Timestamp(sdf.parse(tsStr).getTime());
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    } // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933

    public static String idToPath(long seq, String fileSeperator) {
        String path = fileSeperator;
        seq = seq / 100;
        while (seq > 0) {
            path = fileSeperator + seq % 100 + path;
            seq = seq / 100;
        }
        return path;
    }

    static public String getFullPath(BigDecimal invId, String oldurnNum, String urnNum, String fileExt) {
        //String idpath = idToPath(CanonCustomBillingCommon.toBigDecimal(urnNum, true).longValue(), fileSeperator);  // COMMENTED by DUNA RAO om NOV-16-2016 for ITG Request 645933
        String idpath = idToPath(CanonCustomBillingCommon.toBigDecimal(oldurnNum, true).longValue(), fileSeperator);
        String fullpath = serverInitialPath + fileSeperator + calendarYear + fileSeperator + "e479" + idpath + urnNum + "_" + invId + "."+fileExt;
        return fullpath;
    }
    
    static public String getDriveFilePath(BigDecimal invId, String oldurnNum, String urnNum, String fileExt) {
        //String idpath = idToPath(CanonCustomBillingCommon.toBigDecimal(urnNum, true).longValue(), fileSeperator);  // COMMENTED by DUNA RAO om NOV-16-2016 for ITG Request 645933
        String idpath = idToPath(CanonCustomBillingCommon.toBigDecimal(oldurnNum, true).longValue(), fileSeperator);
        String fullpath = initialPath + fileSeperator + calendarYear + fileSeperator + "e479" + idpath + urnNum + "_" + invId + "."+fileExt;
        return fullpath;
    }
    
    

    public static String cleanName(String fileName) {
        char escape = '_'; // ... or some other legal char.
        String s = fileName;
        int len = s.length();
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch <= ' ' || ch >= 0x7F || ch == '/' || ch == '\\' // || ... // add other // illegal chars
                    || (ch == '.' && i == 0) // we don't want to collide with // "." or ".."!
                    || ch == escape) {
                sb.append(escape);
                if (ch < 0x10) {
                    sb.append('0');
                }
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

    private static String formatfileName(String customerName, BigDecimal custNumber, String billPeriod, String urn, String ext) {
        StringBuffer sb = new StringBuffer();
        String cn = CanonCustomBillingCommon.nonNullify(customerName).trim();
        if (!CanonCustomBillingCommon.isEmpty(cn)) {
            sb.append(cleanName(cn)).append("-");
        }
        if (custNumber != null) {
            sb.append(custNumber).append("-");
        }
        if (!CanonCustomBillingCommon.isEmpty(billPeriod)) {
            sb.append(CanonCustomBillingCommon.nonNullify(billPeriod)).append("_");
        }
        sb.append(df.format(new Date()));
        if (urn != null) {
            sb.append("_").append(urn);
        }
/*        if (origianlurncount != "-1") {
            sb.append("-").append(origianlurncount);
        }
*/        sb.append(".").append(ext);
        return sb.toString();
    }

    public static String upload(PageContext pageContext, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletConfig config = pageContext.getServletConfig();
        StringBuffer stringBuffer = new StringBuffer();

        try {
            SmartUpload objSmartUpload = new SmartUpload();
            objSmartUpload.initialize(config, request, response);
            objSmartUpload.upload();
            objSmartUpload.setAllowedFilesList("xls, xlsx");
            // set max size 50M
            objSmartUpload.setMaxFileSize(52428800);

            com.jspsmart.upload.File updFile = null;

            Request req = objSmartUpload.getRequest();
            for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
                String reqName = (String) e.nextElement();
                System.out.println(reqName + "=>" + req.getParameter(reqName));
            }

            String customerName = req.getParameter("customer_name");
            // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933
            String profileName = req.getParameter("customerGroupNm");
            String parentCustomer = req.getParameter("parentCustomer");
            //DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
           
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            System.out.println("Current Date " + df.format(date));            
            
            //String strbillDate = req.getParameter("BillDate");
            //Date billDate = df.parse(strbillDate);
            //Timestamp newbillDate = new Timestamp(billDate.getTime());
            Timestamp newbillDate=null;
            String strbilldate = req.getParameter("BillDate");
            if (strbilldate==null || strbilldate.length()==0) 
            {
            	newbillDate = new Timestamp(df.parse(df.format(date)).getTime());
            }	
            else
            {	
            	newbillDate =  toTimestamp(strbilldate);
            }
            
            //if (req.getParameter("BillDate").equals(""))
           /// 	newbillDate = new Timestamp(date.getTime()); 
            // Added by DUNA RAO
            System.out.println("in newbillDate "+newbillDate);

            String billerName = req.getParameter("biller_name");
            String billLocation = req.getParameter("bill_location");
            String userId = req.getParameter("user_id");
            String billAmount = req.getParameter("bill_amount");
            String billerEmail = req.getParameter("biller_email");
            String comments = req.getParameter("comments");
            String billPeriod = req.getParameter("bill_period");
            String originalurn = req.getParameter("original_urn");
            String custEmail = req.getParameter("cust_email");
            String urnNumber;
            BigDecimal custNumber = CanonCustomBillingCommon.toBigDecimal(req.getParameter("customer_number"), false);
            String origianlurncount="-1";
            String errorurnvalidation="-1";
            Object[] objs1;
            // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933
            System.out.println("originalurn " + originalurn);
            if ((originalurn != null) && (originalurn.length() != 0))
            {
            	System.out.println("inside originalurn " + originalurn);
            	objs1 = CanonCustomBillingManualDAO.checkvalidateurn(customerName,profileName,billLocation,originalurn,billPeriod,newbillDate);
            	origianlurncount = (String) CanonCustomBillingCommon.first(objs1);
            	errorurnvalidation = (String) CanonCustomBillingCommon.second(objs1);
            	System.out.println("after originalurn " + origianlurncount);
            	if ("-1".equals(origianlurncount))
            		throw new UploadException(errorurnvalidation);
            }
            System.out.println("origianlurncount " + origianlurncount);
            // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933
            
            updFile = objSmartUpload.getFiles().getFile(0);
            int fileSize = updFile.getSize();
            String fileExt = updFile.getFileExt().toLowerCase();
            System.out.println("file size " + fileSize);
            if (!(fileExt.equals("xls") || fileExt.equals("xlsx"))) {
                throw new UploadException("Invalid file type, Only xls and xlsx is allowed." );
            }

            if (fileSize > 52428800) {
                throw new UploadException("File size exceeds 50M limit.");
            }

            Object[] objs = CanonCustomBillingManualDAO.getSequenceValues();
            String validationStatus = (String) (objs != null && objs.length > 2 ? objs[2] : null);
            if ("S".equals(validationStatus)) {
                BigDecimal invoiceId = (BigDecimal) CanonCustomBillingCommon.first(objs);
                
                // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933
                //if ((originalurn != null) && (originalurn.length() != 0))
                if (!"-1".equals(origianlurncount))
                	urnNumber = originalurn +"-" + origianlurncount; 
                else
                    urnNumber = (String) CanonCustomBillingCommon.second(objs);
                // Added by DUNA RAO om NOV-16-2016 for ITG Request 645933
                
                System.out.println("new invoiceId " + invoiceId);
                System.out.println("new urnNumber " + urnNumber);
                String fileName = formatfileName(customerName, custNumber, billPeriod, urnNumber, fileExt);
                FileOutputStream fs = null;
                File file = null;
                try {
                    String fullPath = getFullPath(invoiceId,originalurn, urnNumber, fileExt);
                    String driveFilePath = getDriveFilePath(invoiceId,originalurn, urnNumber, fileExt);
                    file = new File(fullPath);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    fs = new FileOutputStream(file);
                    for (int count = 0; count < fileSize; count++) {
                        fs.write(updFile.getBinaryData(count));
                    }
                    fs.flush();
                    fs.close();
                    System.out.println("file saved " + fullPath);
                    String billNumber = "MB_URN-" + urnNumber;
                    objs = CanonCustomBillingManualDAO.createInvoiceRecord(
                            invoiceId,
                            urnNumber,
                            "Excel", //invoiceType
                            "W",//invRegion
                            billerName,
                            billerEmail,
                            custEmail, //null,//p_customer_email,  Added by Madhusudan Duna for ITG#645933
                            null,//p_other_email,
                            "Y",  //"N",// p_review_required,  Added by Madhusudan Duna for ITG#645933
                            parentCustomer,
                            profileName, //null,//p_profile_name,   Added by Madhusudan Duna for ITG#645933
                            customerName,
                            billLocation, //p_bill_location,
                            billNumber,// p_bill_number,
                            newbillDate, //null,//p_bill_date,  Added by Madhusudan Duna for ITG#645933
                            null, //p_invoice_break,
                            billPeriod,// p_bill_period,
                            CanonCustomBillingCommon.toBigDecimal(billAmount, false),
                            driveFilePath, //p_invoice_path,
                            "", //"S",  Added by Madhusudan Duna for ITG#645933
                            CanonCustomBillingCommon.toBigDecimal(userId, false),
                            fileName,
                            comments);
                    BigDecimal status = (BigDecimal) CanonCustomBillingCommon.first(objs);
                    if (status.intValue() == 1) {
                        CanonCustomBillingManualDAO.urnUpdateStatus(
                                invoiceId, 
                                CanonCustomBillingCommon.toBigDecimal(urnNumber, false), 
                                customerName, 
                                billPeriod, 
                                billLocation
                        );
                        return "{\"success\":\"true\",\"urn\":\"" + urnNumber + "\"}";
                    } else {
                        System.out.println("validationStatus " + validationStatus);
                        throw new Exception("Data error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (file != null) {
                        file.delete();
                        System.out.println("database failed, file deleted");
                    }
                } finally {
                    if (fs != null) {
                        fs.close();
                    }
                    fs = null;
                }

            } else {
                System.out.println("validationStatus " + validationStatus);
                throw new Exception("Data error");
                // saved to database failed, delete the file
            }
//            wb.open(is);
//            toJSON(bsdBid, wb,stringBuffer);

        } catch (UploadException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Upload failed.");
        }
        return stringBuffer.toString();

    }

    public static boolean hasAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        oracle.apps.fnd.common.WebAppsContext objWebAppsContext
                = oracle.apps.fnd.common.WebRequestUtil.validateContext(request, response);
        int userId = objWebAppsContext.getUserId();
        boolean isBiller = "Y".equals((String) CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.isUserBiller(new BigDecimal(userId))));
        int resp_Id = objWebAppsContext.getRespId();
        return hasAccess(userId, resp_Id, isBiller);
    }

    public static boolean hasAccess(int userId, int resp_Id, boolean isBiller) {
        String PAGE_NAME = "canonCustomBillingManualUpload.jsp";
        boolean isJspAccessValid = CanonSecurityUtil.isJSPAccessValid(PAGE_NAME, userId, resp_Id);
        return isJspAccessValid && isBiller;
    }
    
    private static class UploadException extends Exception{
        public UploadException(String str){
            super(str);
        }
    }
}
