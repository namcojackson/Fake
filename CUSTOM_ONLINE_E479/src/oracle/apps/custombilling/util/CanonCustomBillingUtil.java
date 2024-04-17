package oracle.apps.custombilling.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataSource;
import javax.mail.PasswordAuthentication;

import oracle.apps.custombilling.beans.CanonCustomBillingInvSearchBean;
import oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO;
import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import org.apache.commons.mail.MultiPartEmail;

import canon.apps.common.CanonEmailUtil;
import canon.apps.pci.util.CanonSecurityUtil;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class CanonCustomBillingUtil {
	
	
    static String EMAIL_SUBJECT = "{0} {1} Invoice";
    static String EMAIL_BODY = "Please see the attached invoice for billing period {0}.";
    static String EMAIL_SUBJECT_CUSTOMER = "{0} {1} Custom Invoice";
    static String EMAIL_BODY_CUSTOMER = "Dear Valued Customer,\n"+
"Attached is your Custom Invoice for {0}.\n"+
"\n"+
"{1}\n"+
"{2} {3}\n"+
"Phone: {4}\n"+
"Fax: {5}\n"+
"{6}\n"+
"\n"+
"Please Do Not Reply to this email address. Please contact your biller indicated above.\n";
    
    static Properties envVars = envVars();
    static final String NULL_STR = "null";
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final String EMAIL_DELIMITER="[,;]";

    public static String fileSeperator = System.getProperty("file.separator");

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

    public String getProfileLocal(String profileName) throws Exception {
        return (String) envVars.get(profileName);
    }

    public String getProfile(BigDecimal userId,String userName,String roleName,String profileName) throws Exception {
        if (envVars != null) {
            return getProfileLocal(profileName);
        }

        String s1 = "BEGIN CANON_E479_CUST_BILL_UTIL_PKG.get_profile_value(?,?,?,?,?,?,?); END;";
        //String s1 = "BEGIN CANON_E001_CODETABLE_UI_PKG.retrieve_profile_values (?,?,?,?,?,?); END;";
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
	
	public static boolean isUserBiller(String userName) throws Exception{
		boolean userBiller =false;
		try {
		S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
		if (upService.isFunctionGranted(userName, "EXTNE479T030")) {
			userBiller = true;
		}
		}
		 catch (Exception _ex) {
             throw new Exception(" Exception while getting approval role: CanonCustomBilingUtilJava: "+_ex);
         }
		return userBiller;
	}
	
	public  boolean hasRole(String userName) throws Exception { 
		S21UserProfileService upService = S21UserProfileServiceFactory.getInstance().getService();
		boolean isJspAccessValid =false;
		try{
			String[] functionNames={// Function names
							 "EXTNE479T010",
							 "EXTNE479T020",
							 "EXTNE479T030" 
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

    public static void sendEmailToCustomer(BigDecimal userId,BigDecimal invoiceId) throws Exception {
        CanonCustomBillingUtil util = new CanonCustomBillingUtil();
        List list = (List) CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getInvDetailsForApproval(invoiceId));
        if (list.size() > 0) {
            String defaultBillerEmailAddress = util.getProfile(userId,"","","CANON_E479_BILLER_EMAIL_ADDRESS");
            CanonCustomBillingInvSearchBean firstone = (CanonCustomBillingInvSearchBean) list.get(0);

            MultiPartEmail email=CanonEmailUtil.createMultiPartEmail();

            for (int i = 0; i < list.size(); i++) {
                CanonCustomBillingInvSearchBean b = (CanonCustomBillingInvSearchBean) list.get(i);
                String initialPath = (String) first(CanonCustomBillingSearchingDAO.getInitialPath("ONLINE"));
                final String fullInvoicePath = initialPath+ fileSeperator+ b.getInvoicePath();
                final String fileName = CanonCustomBillingCommon.isEmpty(b.getInvoiceFileName())? b.getUrnNumber() + "_" + invoiceId + ".xls"  : CanonCustomBillingCommon.nonNullify(b.getInvoiceFileName()) ;
                DataSource ds=new DataSource() {

                    public InputStream getInputStream() throws IOException {
                        return new FileInputStream(fullInvoicePath);
                    }

                    public OutputStream getOutputStream() throws IOException {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public String getContentType() {
                        return "application/vnd.ms-excel";
                    }

                    public String getName() {
                        return fileName;
                    }
                };
                email.attach(ds,null,fileName);
                
                if("Y".equals(propertyValue("EmailTextData",b.getEmailtext()))){
                    final CanonCustomBillingCommon.URLOutputStream urlos=new CanonCustomBillingCommon.URLOutputStream(fullInvoicePath);
                    if(urlos.getSize()==2){
                        final String txtfileName = toCSVFileName(fileName);
                        DataSource dstxt=new DataSource() {

                            public InputStream getInputStream() throws IOException {
                                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                                PrintWriter pw=new PrintWriter(arrayOutputStream);
                                urlos.saveText(pw);
                                pw.flush();
                                arrayOutputStream.flush();
                                return new ByteArrayInputStream(arrayOutputStream.toByteArray());
                            }

                            public OutputStream getOutputStream() throws IOException {
                                throw new UnsupportedOperationException("Not supported yet.");
                            }

                            public String getContentType() {
                                return "text/plain";
                            }

                            public String getName() {
                                return txtfileName;
                            }
                        };
                        email.attach(dstxt,null,txtfileName);
                    }
                }
            }


            boolean isProd = CanonEmailUtil.isProd();
            String sendEmailInNonProd=CanonCustomBillingCommon.nonNullify(util.getProfile(null,"","","CANON_E479_SEND_EMAIL_IN_NON_PROD"));
            System.out.println("isProd flag = " + isProd);
            System.out.println("sendEmailInNonProd = " + sendEmailInNonProd);
            if(!isProd && !"Y".equals(sendEmailInNonProd)){
                System.out.println("defaultBillerEmailAddress = " + defaultBillerEmailAddress);
                String[] emails = defaultBillerEmailAddress.split(EMAIL_DELIMITER);
                for (int i = 0; i < emails.length; i++) {
                    System.out.println("add recipient " + emails[i]);
                    email.addTo(emails[i]);
                }
            }else if(isProd || "Y".equals(sendEmailInNonProd)){
                // send to customer and other emails. 
                if (!CanonCustomBillingCommon.isEmpty(firstone.getCustomerEmail())) {
                    System.out.println("send to customer email addres " + firstone.getCustomerEmail());
                    String[] emails = firstone.getCustomerEmail().split(EMAIL_DELIMITER);
                    for (int i = 0; i < emails.length; i++) {
                        System.out.println("add recipient " + emails[i]);
                        email.addTo(emails[i]);
                    }
                }
                if (!CanonCustomBillingCommon.isEmpty(firstone.getOtherEmail())) {
                    System.out.println("send to other email addres " + firstone.getOtherEmail());
                    String[] emails = firstone.getOtherEmail().split(EMAIL_DELIMITER);
                    for (int i = 0; i < emails.length; i++) {
                        System.out.println("add recipient " + emails[i]);
                        email.addCc(emails[i]);
                    }
                }

            } else {
                throw new RuntimeException("Invalid Is_Prod flag " + isProd);
            }

            String emailSubject=MessageFormat.format(EMAIL_SUBJECT_CUSTOMER, new Object[]{CanonCustomBillingCommon.nonNullify(name(firstone)), CanonCustomBillingCommon.nonNullify(firstone.getBillPeriod())});
            emailSubject="Y".equals(isProd) ? emailSubject : emailSubject+" - "+CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getDbName());
            email.setSubject(emailSubject);
            Object result = CanonCustomBillingCommon.first(CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.getControlInfo(firstone.getUrnNumber())));
            InvoiceBean control=toInvoiceBean((CanonCustomBillingSearchingDAO.ControlBean)result);
            String emailBody=MessageFormat.format(EMAIL_BODY_CUSTOMER,new Object[]{
                    CanonCustomBillingCommon.nonNullify(control.getBillPeriod()),
                    CanonCustomBillingCommon.nonNullify(control.getBillerName()),
                    CanonCustomBillingCommon.nonNullify(control.getCompany1()),
                    CanonCustomBillingCommon.nonNullify(control.getCompany2()),
                    CanonCustomBillingCommon.nonNullify(control.getPhone()),
                    CanonCustomBillingCommon.nonNullify(control.getFax()),
                    CanonCustomBillingCommon.nonNullify(control.getBillerEmail()),
                });
            email.setMsg(emailBody);
            email.send();

        }

    }
    
    private static String name(CanonCustomBillingInvSearchBean inv ){
    	return inv.getCustomerName();
    }
    
    public static Object getPropertyValue(Object bean, String propName) {
        try {
            Method readMethod = getReadPropertyMethod(bean, propName);
            Object value = null;
            if (readMethod != null) {
                value = readMethod.invoke(bean, EMPTY_OBJECT_ARRAY);
            } else if (bean instanceof Map) {
                return ((Map) bean).get(propName);
            }
            if (NULL_STR.equals(value)) {
                value = null;
            }
            return (value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;

        }
        return name.substring(0, 1).toUpperCase(java.util.Locale.ENGLISH) + name.substring(1);
    }
    
    static Method getReadPropertyMethod(Object bean, String propName) {
        try {
            return new PropertyDescriptor(propName, bean.getClass(), "get" + capitalize(propName), null).getReadMethod();
        } catch (Exception ex) {
        }
        try {
            return new PropertyDescriptor(propName, bean.getClass(), "is" + capitalize(propName), null).getReadMethod();
        } catch (IntrospectionException ex) {
        }
        return null;
    }
    
    private static InvoiceBean toInvoiceBean(CanonCustomBillingSearchingDAO.ControlBean c) {
        InvoiceBean b = new InvoiceBean();
        b.setInvoiceId(c.getInvId());
        b.setUrnNumber(c.getUrnNmuber());
        boolean found = false;
        HashMap h = new HashMap();
        for (int i = 1; i < 100; i++) {
            String propName = "field" + i;
            Object o = getPropertyValue(c, propName);
            if (NULL_STR.equals(o)) {
                o = null;
            }
            if (o == null && found) {
                break;
            }
            if (o != null) {
                found = true;
            }
            String propValue = (String) o;
            int splitIdx = propValue.indexOf("=");
            if (splitIdx > 0) {
                String name = propValue.substring(0, splitIdx);
                String value = propValue.substring(splitIdx + 1);
                h.put(name, value);
            }
        }
        /*
        Summary
        Type=EXCEL
        Region=WESTERN
        Biller=Nichola Secka
        BillerEmail=abc@email.com
        CustomerEmail=fgh@email.com
        OtherEmail=xyz@email.com
        ReviewRequired=Y
        AccountName=XYZ
        CustomerName=MUTUAL OF OMAHA INSURANCE CO
        BilltoLoc=123456
        BillNumber=708944682
        BillDate=5/12/2007
        InvoiceBreak=NULL
        URN=123456
        Company1=Oce Imagistics Inc. d/b/a
        Company2=Oce North America Document Printing Systems
        Phone=(203) 123 4567
        Fax=(203) 432 7654
        BillPeriod=MAY2007
        TotalAmountDue=10179.65
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        b.setInvoiceType((String) h.get("Type"));
        b.setInvRegion((String) h.get("Region"));
        b.setBillerName((String) h.get("Biller"));
        b.setBillerEmail((String) h.get("BillerEmail"));
        b.setCustomerEmail((String) h.get("CustomerEmail"));
        b.setOtherEmail((String) h.get("OtherEmail"));
        b.setReviewRequired((String) h.get("ReviewRequired"));
        b.setProfileName((String) h.get("AccountName"));
        b.setCustomerName((String) h.get("CustomerName"));
        b.setBillLocation((String) h.get("BilltoLoc"));
        b.setBillNumber((String) h.get("BillNumber"));
        try {
            Date d = dateFormat.parse((String) h.get("BillDate"));
            b.setBillDate(new Timestamp(d.getTime()));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        b.setInvoiceBreak((String) h.get("InvoiceBreak"));
        b.setBillPeriod((String) h.get("BillPeriod"));
        b.setAmountDue((String) h.get("TotalAmountDue"));
        b.setCompany1((String) h.get("Company1"));
        b.setCompany2((String) h.get("Company2"));
        b.setPhone((String) h.get("Phone"));
        b.setFax((String) h.get("Fax"));

        return b;
    }
    
    static public class CanonSMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {

            String SMTP_AUTH_USER = "";
            String SMTP_AUTH_PWD = "";

            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
    
    public static class InvoiceBean {
       private BigDecimal invoiceId;
       private String urnNumber;
       private String invoiceType;
       private String invRegion;
       private String billerName;
       private String billerEmail;
       private String customerEmail;
       private String otherEmail;
       private String reviewRequired;
       private String profileName;
       private String customerName;
       private String billLocation;
       private String billNumber;
       private Timestamp billDate;
       private String invoiceBreak;
       private String billPeriod;
       private String amountDue;
       private Timestamp creationDate;
       private BigDecimal createdBy;
       private BigDecimal lastUpdatedBy;
       private Timestamp lastUpdateDate;
       private String invoicePath;
       private String pendingAction;
       private String company1;
       private String company2;
       private String phone;
       private String fax;

        public InvoiceBean(){
        }
        public InvoiceBean(BigDecimal invoiceId, 
                String urnNumber, 
                String invoiceType, 
                String invRegion, 
                String billerName, 
                String billerEmail, 
                String customerEmail, 
                String otherEmail, 
                String reviewRequired, 
                String profileName, 
                String customerName, 
                String billLocation, 
                String billNumber, 
                Timestamp billDate, 
                String invoiceBreak, 
                String billPeriod, 
                String amountDue, 
                Timestamp creationDate, 
                BigDecimal createdBy, 
                BigDecimal lastUpdatedBy, 
                Timestamp lastUpdateDate, 
                String invoicePath, 
                String pendingAction){
            this.invoiceId=invoiceId;
            this.urnNumber=urnNumber;
            this.invoiceType=invoiceType;
            this.invRegion=invRegion;
            this.billerName=billerName;
            this.billerEmail=billerEmail;
            this.customerEmail=customerEmail;
            this.otherEmail=otherEmail;
            this.reviewRequired=reviewRequired;
            this.profileName=profileName;
            this.customerName=customerName;
            this.billLocation=billLocation;
            this.billNumber=billNumber;
            this.billDate=billDate;
            this.invoiceBreak=invoiceBreak;
            this.billPeriod=billPeriod;
            this.amountDue=amountDue;
            this.creationDate=creationDate;
            this.createdBy=createdBy;
            this.lastUpdatedBy=lastUpdatedBy;
            this.lastUpdateDate=lastUpdateDate;
            this.invoicePath=invoicePath;
            this.pendingAction=pendingAction;
        }
        public BigDecimal getInvoiceId() {
            return invoiceId;
        }
        public void setInvoiceId(BigDecimal invoiceId) {
            this.invoiceId=invoiceId;
        }
        public String getUrnNumber() {
            return urnNumber;
        }
        public void setUrnNumber(String urnNumber) {
            this.urnNumber=urnNumber;
        }
        public String getInvoiceType() {
            return invoiceType;
        }
        public void setInvoiceType(String invoiceType) {
            this.invoiceType=invoiceType;
        }
        public String getInvRegion() {
            return invRegion;
        }
        public void setInvRegion(String invRegion) {
            this.invRegion=invRegion;
        }
        public String getBillerName() {
            return billerName;
        }
        public void setBillerName(String billerName) {
            this.billerName=billerName;
        }
        public String getBillerEmail() {
            return billerEmail;
        }
        public void setBillerEmail(String billerEmail) {
            this.billerEmail=billerEmail;
        }
        public String getCustomerEmail() {
            return customerEmail;
        }
        public void setCustomerEmail(String customerEmail) {
            this.customerEmail=customerEmail;
        }
        public String getOtherEmail() {
            return otherEmail;
        }
        public void setOtherEmail(String otherEmail) {
            this.otherEmail=otherEmail;
        }
        public String getReviewRequired() {
            return reviewRequired;
        }
        public void setReviewRequired(String reviewRequired) {
            this.reviewRequired=reviewRequired;
        }
        public String getProfileName() {
            return profileName;
        }
        public void setProfileName(String profileName) {
            this.profileName=profileName;
        }
        public String getCustomerName() {
            return customerName;
        }
        public void setCustomerName(String customerName) {
            this.customerName=customerName;
        }
        public String getBillLocation() {
            return billLocation;
        }
        public void setBillLocation(String billLocation) {
            this.billLocation=billLocation;
        }
        public String getBillNumber() {
            return billNumber;
        }
        public void setBillNumber(String billNumber) {
            this.billNumber=billNumber;
        }
        public Timestamp getBillDate() {
            return billDate;
        }
        public void setBillDate(Timestamp billDate) {
            this.billDate=billDate;
        }
        public String getInvoiceBreak() {
            return invoiceBreak;
        }
        public void setInvoiceBreak(String invoiceBreak) {
            this.invoiceBreak=invoiceBreak;
        }
        public String getBillPeriod() {
            return billPeriod;
        }
        public void setBillPeriod(String billPeriod) {
            this.billPeriod=billPeriod;
        }
        public String getAmountDue() {
            return amountDue;
        }
        public void setAmountDue(String amountDue) {
            this.amountDue=amountDue;
        }
        public Timestamp getCreationDate() {
            return creationDate;
        }
        public void setCreationDate(Timestamp creationDate) {
            this.creationDate=creationDate;
        }
        public BigDecimal getCreatedBy() {
            return createdBy;
        }
        public void setCreatedBy(BigDecimal createdBy) {
            this.createdBy=createdBy;
        }
        public BigDecimal getLastUpdatedBy() {
            return lastUpdatedBy;
        }
        public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
            this.lastUpdatedBy=lastUpdatedBy;
        }
        public Timestamp getLastUpdateDate() {
            return lastUpdateDate;
        }
        public void setLastUpdateDate(Timestamp lastUpdateDate) {
            this.lastUpdateDate=lastUpdateDate;
        }
        public String getInvoicePath() {
            return invoicePath;
        }
        public void setInvoicePath(String invoicePath) {
            this.invoicePath=invoicePath;
        }
        public String getPendingAction() {
            return pendingAction;
        }
        public void setPendingAction(String pendingAction) {
            this.pendingAction=pendingAction;
        }

        public String getCompany1() {
            return company1;
        }

        public void setCompany1(String company1) {
            this.company1 = company1;
        }

        public String getCompany2() {
            return company2;
        }

        public void setCompany2(String company2) {
            this.company2 = company2;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
        
        public String toString() {
            return "InvoiceBean{" + "invoiceId=" + invoiceId + ", urnNumber=" + urnNumber + ", invoiceType=" + invoiceType + ", invRegion=" + invRegion + ", billerName=" + billerName + ", billerEmail=" + billerEmail + ", customerEmail=" + customerEmail + ", otherEmail=" + otherEmail + ", reviewRequired=" + reviewRequired + ", profileName=" + profileName + ", customerName=" + customerName + ", billLocation=" + billLocation + ", billNumber=" + billNumber + ", billDate=" + billDate + ", invoiceBreak=" + invoiceBreak + ", billPeriod=" + billPeriod + ", amountDue=" + amountDue + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", invoicePath=" + invoicePath + ", pendingAction=" + pendingAction + ", company1=" + company1 + ", company2=" + company2 + ", phone=" + phone + ", fax=" + fax + '}';
        }

    }
    
    private static String toCSVFileName(String xlsfile){
        if(xlsfile!=null){
            return xlsfile.substring(0, xlsfile.length()-3)+"csv";
        }else{
            return "";
        }
    }
    
    private static String propertyValue(String propName, String propValue){
            if(propValue!=null){
                int splitIdx = propValue.indexOf("=");
                if (splitIdx > 0) {
                    String name = propValue.substring(0, splitIdx);
                    if(propName.equalsIgnoreCase(name)){
                        return propValue.substring(splitIdx + 1);
                    }
                }
            }
            return null;
    } 
	
}
