package oracle.apps.custombilling.server;

import static canon.batch.server.AutosysConcurrentProgram.cpContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import canon.apps.common.CanonAsposeUtil;
import canon.apps.common.CanonCustomProfile;
import canon.apps.common.CanonEmailUtil;

import org.apache.commons.mail.HtmlEmail;









import business.parts.NWZC057001PMsg;
import canon.apps.common.CanonConstants;









import com.canon.cusa.s21.api.NWZ.NWZC057001.NWZC057001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import parts.common.EZDPStringItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import oracle.apps.custombilling.server.CanonCustomBillingServerInvoiceHelper.InvoiceOutputStream;
import oracle.apps.fnd.cp.request.CpContext;
import oracle.apps.fnd.cp.request.JavaConcurrentProgram;
import oracle.apps.fnd.cp.request.ReqCompletion;

public class CanonCustomBillingServerInvConcProg implements JavaConcurrentProgram {

    static String EMAIL_SUBJECT_CUSTOMER = "{0} {1} Custom Excel Invoice";
    static String EMAIL_SUBJECT_BILLER = "New review for Custom Excel Invoice {2}for {0} (Total amount due: {1})";
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
    
    static String EMAIL_BODY_BILLER =
"Dear {0}\n"+
"\n"+
"There is a new bill to review for {1} with a total amount due of: {2}.\n"+
"Please be aware that it can take a few minutes before the new documents are visible in the Custom Billing Review Website.\n"+
"\n"+
"Additional information:\n"+
"Website: {3} \n"+
"Type: {4}\n"+
"Bill Date: {5}\n"+
"Bill Period: {6}\n"+
"Total Amount Due: {2}\n";

    static String EMAIL_BODY_BILLER_NEGATIVE_READ_OR_HIGH_DOLLAR =
"Dear {0}\n"+
"\n"+
"There is New Bill due to {7}. Due to this all the Bills for this site put on hold. Please review and take necessary action.\n" +
"This new bill to review for {1} with a total amount due of: {2}.\n"+
"Please be aware that it can take a few minutes before the new documents are visible in the Custom Billing Review Website.\n"+
"\n"+
"Additional information:\n"+
"Website: {3} \n"+
"Type: {4}\n"+
"Bill Date: {5}\n"+
"Bill Period: {6}\n"+
"Total Amount Due: {2}\n";

    static CanonCustomBillingServerUtil util = new CanonCustomBillingServerUtil();
    static String fileSeperator = System.getProperty("file.separator");
    static String defaultBillerEmailAddress;
    private static final String EMAIL_DELIMITER="[,;]";
    
    private static final String NEGATIVE_READ="Negative Read";
    private static final String HIGH_DOLLAR="High Dollar";
    
    static{
        try {
            defaultBillerEmailAddress= util.getProfile(null,"","E479_SPL","CANON_E479_BILLER_EMAIL_ADDRESS");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } 
    
    public void runProgram(CpContext ctx) {
        try {
			CanonAsposeUtil.loadLicense();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        List invIdList = (List) first(CanonCustomBillingServerInvoiceDAO.getInvoicesToCreate()); 
        System.out.println("found " + invIdList.size());
        for (int i = 0; i < invIdList.size(); i++) {
        	System.out.println("Inside " + invIdList.size());
        	InvoiceHandler invoiceHandler = new ChainedInvoiceHandler(new InvoiceHandler[]{new FileInvoiceHandler(), new EmailInvoiceHandler()});
        	System.out.println("Inside 1 " + invIdList.size());
            CanonCustomBillingServerInvoiceDAO.InvoiceInfo invInfo = (CanonCustomBillingServerInvoiceDAO.InvoiceInfo) invIdList.get(i);
            System.out.println("Inside 2" + invIdList.size());
            BigDecimal invId = invInfo.getInvId();
            System.out.println(invInfo);
            try {
                invoiceHandler.start(invId);
                List urnList = (List) first(CanonCustomBillingServerInvoiceDAO.getInvDetails(invId));
                Collections.sort(urnList,getComparator(invInfo));
                for (int j = 0; j < urnList.size(); j++) {
                    String urnNum = (String) urnList.get(j);
                    InvoiceOutputStream ios = CanonCustomBillingServerInvoiceHelper.createInvoice(urnNum);
                    invoiceHandler.invoice(urnNum, ios, null);
                }
                invoiceHandler.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        calls21apiupdInvoice("MANUAL_INVOICE_LIST","");
        calls21apiupdBill("MANUAL_INVOICE_LIST","");
        calls21apiupdInvoice("MANUAL_INVOICE_LIST_UPDATE","");
        
        ctx.getOutFile().writeln("Succeefully run the file");
        ctx.getReqCompletion().setCompletion(ReqCompletion.NORMAL, "");
        

    }
    
    static Comparator getComparator(final CanonCustomBillingServerInvoiceDAO.InvoiceInfo ii){
        return new Comparator(){
            public int compare(Object o1, Object o2) {
                String urn1=(String)o1;
                String urn2=(String)o2;
                if(!CanonCustomBillingServerUtil.isEmpty(ii.getNegativeReadUrn())){
                    if(ii.getNegativeReadUrn().equals(urn1)){
                        return 1;
                    }else if(ii.getNegativeReadUrn().equals(urn2)){
                        return -1;
                    }
                }else if(!CanonCustomBillingServerUtil.isEmpty(ii.getHighDollarUrn())){
                    if(ii.getHighDollarUrn().equals(urn1)){
                        return 1;
                    }else if(ii.getHighDollarUrn().equals(urn2)){
                        return -1;
                    }
                }
                return 0;
            }
        };
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

    interface InvoiceHandler {
    	
        void start(BigDecimal invId) throws Exception;
        
        Object invoice(String urnNumber, InvoiceOutputStream invoiceOutputStream, Object param) throws Exception;
        
        void finish() throws Exception;
    }

    class ChainedInvoiceHandler implements InvoiceHandler {

        private InvoiceHandler[] chainedHandlers;

        public ChainedInvoiceHandler(InvoiceHandler[] chainedHandlers) {
            this.chainedHandlers = chainedHandlers;
        }

        public void start(BigDecimal invId) throws Exception {
            for (int i = 0; i < chainedHandlers.length; i++) {
                InvoiceHandler h = (InvoiceHandler) chainedHandlers[i];
                h.start(invId);
            }
        }

        public Object invoice(String urnNumber, InvoiceOutputStream invoiceOutputStream, Object param) throws Exception {
            Object lastValue=null;
            for (int i = 0; i < chainedHandlers.length; i++) {
                InvoiceHandler h = (InvoiceHandler) chainedHandlers[i];
                lastValue=h.invoice(urnNumber, invoiceOutputStream, lastValue);
            }
            return lastValue;
        }

        public void finish() throws Exception {
            for (int i = 0; i < chainedHandlers.length; i++) {
                InvoiceHandler h = (InvoiceHandler) chainedHandlers[i];
                h.finish();
            }
        }
    }

    private static String remarks( CanonCustomBillingServerInvSearchBean b ){
        return "Y".equals(b.getNegativeRead())? NEGATIVE_READ:
            ("Y".equals(b.getHighDollar())? HIGH_DOLLAR : null);
    }
    
    private static String formatBillerEmailSubject(CanonCustomBillingServerInvSearchBean control ){
        String remarks=remarks(control);
        String remarksString=remarks==null?"":"("+remarks+") ";
        return MessageFormat.format(EMAIL_SUBJECT_BILLER, new Object[]{util.nonNullify(name(control)), util.nonNullify(control.getAmountDue()), remarksString});
    }
    
    private static String formatBillerEmailBody(CanonCustomBillingServerInvSearchBean control ) throws Exception{
        String remarks=remarks(control);
        return MessageFormat.format(remarks==null? EMAIL_BODY_BILLER : EMAIL_BODY_BILLER_NEGATIVE_READ_OR_HIGH_DOLLAR ,new Object[]{
                util.nonNullify(control.getBillerName()),
                util.nonNullify( name(control) ),
                util.nonNullify(control.getAmountDue()),
                util.getProfile(null,"","","APPS_JSP_AGENT")+"/e479/jsp/canonCustomBillingInvSearch.jsp",
                util.nonNullify(control.getInvoiceType()),
                util.formatDate2(control.getBillDate()),
                util.nonNullify(control.getBillPeriod()),
                util.nonNullify(remarks)
            });
    } 
    
    /**
     * save to file system and data base;
     */
    class FileInvoiceHandler implements InvoiceHandler {

        BigDecimal invId;

        public void start(BigDecimal invId) throws Exception {
            this.invId = invId;
        }

        public Object invoice(String urnNum, InvoiceOutputStream invoiceOutputStream, Object param) throws Exception {
        	System.out.println("Invoice 1" );
        	BigDecimal errFlag;
        	
    		
        	String initialPath = (String) first(CanonCustomBillingServerInvoiceDAO.getInitialPath("SERVER"));
        	System.out.println("Invoice initialPath " + initialPath );
            String path = CanonCustomBillingServerUtil.getPath(invId, urnNum);
            System.out.println("Invoice path " + path );
            String fullPath = initialPath + fileSeperator + path;
            System.out.println("Invoice fullPath " + fullPath );
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            invoiceOutputStream.save(fos);
            System.out.println("saved to " + file.getAbsolutePath());
            CanonCustomBillingServerInvSearchBean b = (CanonCustomBillingServerInvSearchBean) invoiceOutputStream.getData().get("control");
            final String fileName = CanonCustomBillingServerUtil.getInvoiceFileName(b.getCustomerName(), b.getParentCustomerName(), b.getCustomerGroup(), b.getInvoiceBreak(), b.getBillPeriod(), b.getUrnNumber());
            System.out.println("Invoice fileName " + fileName );
            Object[] createinvoiceObj= CanonCustomBillingServerInvoiceDAO.createInvoiceRecord(
							                    b.getInvoiceId(),
							                    b.getUrnNumber(),
							                    b.getInvoiceType(),
							                    b.getInvRegion(),
							                    b.getBillerName(),
							                    b.getBillerEmail(),
							                    b.getCustomerEmail(),
							                    b.getOtherEmail(),
							                    b.getReviewRequired(),
							                    b.getParentCustomerName(),
							                    b.getCustomerGroup(),
							                    b.getCustomerName(),
							                    b.getBillLocation(),
							                    b.getBillNumber(),
							                    b.getBillDate(),
							                    b.getInvoiceBreak(),
							                    b.getBillPeriod(),
							                    CanonCustomBillingServerUtil.toBigDecimal(b.getAmountDue(), false),
							                    path,
							                    null,
							                    new BigDecimal(0),
							                    fileName,
							                    remarks(b));
            

   		 System.out.println("FileInvoiceHandler.invoice : "+createinvoiceObj[0]);
   		 errFlag=(BigDecimal)createinvoiceObj[0];
   		 System.out.println("FileInvoiceHandler.invoice : -	errFlag  " + errFlag);
   		 if (errFlag.equals(new BigDecimal("1")))
   		 {	 
   			calls21apiupdInvoice("AUTOMATIC",urnNum);
   			calls21apiupdBill("AUTOMATIC",urnNum);
   		 }
   		 
            return fileName;
        }

        public void finish() throws Exception {
        }
    }

	public void calls21apiupdInvoice(String source,String urnNum) {
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
    	String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		
		System.out.println("calls21apiupdInvoice : -	source " + source);
		
		//List invupdList = (List) first(CanonCustomBillingServerInvoiceDAO.getBillInvforUpd(source,urnNum));
		Object[] result = CanonCustomBillingServerInvoiceDAO.getBillInvforUpd(source,urnNum);
		List invupdList = (List)result[1];
		
		System.out.println("calls21apiupdInvoice : -	Listcount " + invupdList.size());
		 for (int i = 0; i < invupdList.size(); i++) {
				 System.out.println("calls21apiupdInvoice : -	inside " );
				 CanonCustomBillingServerInvoiceDAO.billinvoiceupdInfo invoice = (CanonCustomBillingServerInvoiceDAO.billinvoiceupdInfo) invupdList.get(i);
	         NWZC057001 s21Api=new NWZC057001();
	 		 System.out.println("calls21apiupdInvoice : -	3 " );
	 		 NWZC057001PMsg  pmsg = new NWZC057001PMsg();
	 		 
	 		 EZDDBCICarrier.initOnline("", invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
			 EZDDBCICarrier.setProgID("S21EXTN_E479");
			
	 		 pmsg.invNum.setValue(invoice.getInvoiceNumber());
	 		 pmsg.invSpclBillProcStsCd.setValue("2");
	 		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
			
	 		 s21Api.execute(pmsg,ONBATCH_TYPE.BATCH); // execute API
			 if (!S21ApiUtil.isXxMsgId(pmsg)) {
				EZDConnectionMgr.getInstance().commit(); 
				System.out.println("calls21apiupdInvoicee Updated Invoice: - " + invoice.getInvoiceNumber() );
			      }
			 else {
					StringBuffer sb = new StringBuffer("");
					// Error Case - S21API set some error message id when got any
					// error while S21API's function.
					List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
					for (String msg : msgList) {
						logMsg("ERROR MESSAGE : "
								+ S21MessageFunc.clspGetMessage(msg));					
						sb.append("ERROR : " + "\n");
				}
			 }
			 
		}
	}    

	public void calls21apiupdBill(String source,String urnNum) {
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
    	String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		
		System.out.println("calls21apiupdBill : -	source " + source);
		
		Object[] result = CanonCustomBillingServerInvoiceDAO.getBillInvforUpd(source,urnNum);
		List billupdList = (List)result[0];
		
		System.out.println("calls21apiupdBill : -	Listcount " + billupdList.size());
		 for (int i = 0; i < billupdList.size(); i++) {
			 
			 System.out.println("calls21apiupdBill : -	inside " );
			 CanonCustomBillingServerInvoiceDAO.billupdInfo bill = (CanonCustomBillingServerInvoiceDAO.billupdInfo) billupdList.get(i);
			 
		         NWZC057001 s21Api=new NWZC057001();
		 		 System.out.println("calls21apiupdBill : -	3 " );
		 		 NWZC057001PMsg  pmsg = new NWZC057001PMsg();
		 		 
		 		 EZDDBCICarrier.initOnline("", invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
				 EZDDBCICarrier.setProgID("S21EXTN_E479");
				
		 		 pmsg.conslBillNum.setValue(bill.getBillNumber());
		 		 pmsg.invSpclBillProcStsCd.setValue("2");
		 		 pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
				
		 		 s21Api.execute(pmsg,ONBATCH_TYPE.BATCH); // execute API
				 if (!S21ApiUtil.isXxMsgId(pmsg)) {
					EZDConnectionMgr.getInstance().commit(); 
					System.out.println("calls21apiupdBill Updated Bill: - " + bill.getBillNumber() );
				      }
				 else {
						StringBuffer sb = new StringBuffer("");
						// Error Case - S21API set some error message id when got any
						// error while S21API's function.
						List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : msgList) {
							logMsg("ERROR MESSAGE : "
									+ S21MessageFunc.clspGetMessage(msg));					
							sb.append("ERROR : " + "\n");
							}
				 	}
		}
	}    

	
	public void logMsg(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("[E479: Invoice Update API ] ["
				+ sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
    
    class EmailInvoiceHandler implements InvoiceHandler {

        private BigDecimal invId;
        private javax.mail.Message msg;
        private CanonCustomBillingServerInvSearchBean control;
        private Multipart multipart = new MimeMultipart();
        
        public void start(BigDecimal invId) throws Exception {
            this.invId = invId;
            System.out.println("start " + invId);
            Properties mailProps = new Properties();
            //HtmlEmail msg = CanonEmailUtil.createHtmlEmail();
    		String hostName = CanonCustomProfile.getSystemProfileValue("CANON_EMAIL_SER_HOST_NAME");
    		System.out.println("in BATCH CanonEmailUtil hostName is "+ hostName);

    		//mailProps.put("mail.smtp.host", "mail01.cusa.canon.com");
    		mailProps.put("mail.smtp.host", hostName);
            System.out.println("start mail01 " );	
            javax.mail.Authenticator auth = new CanonSMTPAuthenticator();
            javax.mail.Session session = javax.mail.Session.getDefaultInstance(mailProps, auth);

            session.setDebug(false);
            msg = new MimeMessage(session);

            //InternetAddress addressFrom = new InternetAddress("noreply@csa.canon.com", "Canon Solutions America");
            InternetAddress addressFrom = new InternetAddress("S21_Operation@cusa.canon.com", "S21 CSA");
            	
            msg.setFrom(addressFrom);
            
    		msg.setSentDate(new Date());

        }

        public Object invoice(final String urnNumber, final InvoiceOutputStream invoiceOutputStream, Object param) throws Exception {
            control = (CanonCustomBillingServerInvSearchBean) invoiceOutputStream.getData().get("control");
            String isReviewRequired=control.getReviewRequired();
            System.out.println("invoice isReviewRequired "+ isReviewRequired );
            if("Y".equals(isReviewRequired)) return null;
            
            final String fileName = (String)param;
            
            System.out.println("invoice fileName "+ fileName );
            
            BodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.setDataHandler(new javax.activation.DataHandler(new DataSource() {

                public InputStream getInputStream() throws IOException {
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    invoiceOutputStream.save(arrayOutputStream);
                    arrayOutputStream.flush();
                    return new ByteArrayInputStream(arrayOutputStream.toByteArray());
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
            }));
            attachmentBodyPart.setFileName(fileName);
            multipart.addBodyPart(attachmentBodyPart);
            
            if("Y".equals(control.getEmailTextData()) && invoiceOutputStream.getSize()==2){
                
                final String textfileName = toCSVFileName(fileName);
                System.out.println("invoice textfileName "+ fileName );
                BodyPart textAttachmentBodyPart = new MimeBodyPart();
                textAttachmentBodyPart.setDataHandler(new javax.activation.DataHandler(new DataSource() {

                    public InputStream getInputStream() throws IOException {
                        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                        PrintWriter pw=new PrintWriter(arrayOutputStream);
                        invoiceOutputStream.saveText(pw);
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
                        return textfileName;
                    }
                }));
                
                textAttachmentBodyPart.setFileName(textfileName);
                multipart.addBodyPart(textAttachmentBodyPart);
            }
            return fileName;
        }

        public void finish() throws Exception {
        	System.out.println("finish multipart"+ multipart );
        	if (multipart != null && msg != null) {
                boolean isProd=CanonEmailUtil.isProd();
                System.out.println("finish isProd"+ isProd );
                String sendEmailInNonProd=util.nonNullify(util.getProfile(null,"","","CANON_E479_SEND_EMAIL_IN_NON_PROD"));
                System.out.println("finish sendEmailInNonProd"+ sendEmailInNonProd );        
                String isReviewRequired=control.getReviewRequired();
                System.out.println("isProd flag = " + isProd);
                System.out.println("isReviewRequired = " + isReviewRequired);
                System.out.println("sendEmailInNonProd = " + sendEmailInNonProd);
                if(!isProd && !"Y".equals(sendEmailInNonProd)){
                    System.out.println("defaultBillerEmailAddress11 = " + defaultBillerEmailAddress);
                    msg.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(defaultBillerEmailAddress));
                    //String[] emails=defaultBillerEmailAddress.split(EMAIL_DELIMITER);
                    /*for(int i=0;i<emails.length;i++){
                        System.out.println("add recipient " + emails[i]);
                        
                        //msg.addRecipient(javax.mail.Message.RecipientType.TO,  new InternetAddress(emails[i]));
                        //msg.addRecipient(javax.mail.Message.RecipientType.BCC,  new InternetAddress(emails[i]));
                        //msg.addTo(emails[i]);
                    }*/
                }else if(isProd || "Y".equals(sendEmailInNonProd)){
                    System.out.println("Review Required = "
                + control.getReviewRequired());
                    if("Y".equals(isReviewRequired)){
                        // send to biller for approval.
                        String billerEmail=control.getBillerEmail();
                        if(!util.isEmpty(billerEmail)){
                            InternetAddress billerEmailIA = new InternetAddress(billerEmail);
                            System.out.println("send to biller email addres " + billerEmail);
                            msg.addRecipient(javax.mail.Message.RecipientType.TO, billerEmailIA);
                            //msg.addTo(billerEmail);
                        }
                    }else if("N".equals(isReviewRequired)){
                        // send to customer and other emails. 
                        if(!util.isEmpty(control.getCustomerEmail())){
                            System.out.println("send to customer email addres " + control.getCustomerEmail());
                            String[] emails=control.getCustomerEmail().split(EMAIL_DELIMITER);
                            for(int i=0;i<emails.length;i++){
                                System.out.println("add recipient " + emails[i]);
                                msg.addRecipient(javax.mail.Message.RecipientType.TO,  new InternetAddress(emails[i]));
                                //msg.addTo(emails[i]);
                            }
                        }
                        if(!util.isEmpty(control.getOtherEmail())){
                            System.out.println("send to other email addres " + control.getOtherEmail());
                            String[] emails=control.getOtherEmail().split(EMAIL_DELIMITER);
                            for(int i=0;i<emails.length;i++){
                                System.out.println("add recipient " + emails[i]);
                                msg.addRecipient(javax.mail.Message.RecipientType.CC,  new InternetAddress(emails[i]));
                                //msg.addTo(emails[i]);
                            }
                        }
                        
                    }else{
                        throw new RuntimeException("Invalid ReviewRequired flag "+control.getReviewRequired());
                    }
                }else{
                    throw new RuntimeException("Invalid Is_Prod flag "+isProd + " and CANON_E479_SEND_EMAIL_IN_NON_PROD "+sendEmailInNonProd);
                }

                BodyPart emailBodyPart = new MimeBodyPart();
                String emailSubject="Y".equals(isReviewRequired)? 
                        formatBillerEmailSubject(control)
                        :
                        MessageFormat.format(EMAIL_SUBJECT_CUSTOMER, new Object[]{util.nonNullify(name(control)), util.nonNullify(control.getBillPeriod())});
                emailSubject=isProd ? emailSubject : emailSubject+" - "+first(CanonCustomBillingServerInvoiceDAO.getDbName());
                msg.setSubject(emailSubject);
                String emailBody="Y".equals(isReviewRequired)? 
                    formatBillerEmailBody(control)
                    : MessageFormat.format(EMAIL_BODY_CUSTOMER,new Object[]{
                        util.nonNullify(control.getBillPeriod()),
                        util.nonNullify(control.getBillerName()),
                        util.nonNullify(control.getCompany1()),
                        util.nonNullify(control.getCompany2()),
                        util.nonNullify(control.getPhone()),
                        util.nonNullify(control.getFax()),
                        util.nonNullify(control.getBillerEmail()),
                    });
                emailBodyPart.setText(emailBody);
                multipart.addBodyPart(emailBodyPart);
                msg.setContent(multipart);
                
                //msg.send();
                
                javax.mail.Transport.send(msg);

            }
        }
    }
    
    private static String name(CanonCustomBillingServerInvSearchBean control ){
    	String name =null;
    	System.out.println("Inside name: customerName: "+control.getCustomerName()+"|parentCustomerName: "+control.getParentCustomerName()+"|CustomerGroup: "+control.getCustomerGroup());
    	if ( (!(util.isEmpty(control.getCustomerName() ))) &&
    	     (!(util.isEmpty(control.getCustomerName().trim())))){
    		name =  control.getCustomerName();
    	}
    	System.out.println(" 1st assignment: "+name);
    	
    	if ( (!(util.isEmpty(control.getParentCustomerName() ))) &&
       	     (!(util.isEmpty(control.getParentCustomerName().trim())))){
    		if ( (!(util.isEmpty(name ))) && (!(util.isEmpty(name.trim())))){
    			name.concat("-"+control.getParentCustomerName());
    		}
    		else {
    			name = control.getParentCustomerName();
    		}
    	}
    	System.out.println(" 2nd assignment: "+name);
    	if ( (!(util.isEmpty(control.getCustomerGroup() ))) &&
          	     (!(util.isEmpty(control.getCustomerGroup().trim())))){
       		if ( (!(util.isEmpty(name ))) && (!(util.isEmpty(name.trim())))){
       			name.concat("-"+control.getCustomerGroup());
       		}
       		else {
       			name = control.getCustomerGroup();
       		}
       	}
    	System.out.println(" 3rd assignment: "+name);
        return  name;
        /*util.isEmpty(control.getCustomerName()) || util.isEmpty(control.getCustomerName().trim())
                ?( (util.isEmpty(control.getCustomerName())) control.getProfileName() : control.getCustomerName(); */
    }
    
    static class CanonSMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {

            String SMTP_AUTH_USER = "";
            String SMTP_AUTH_PWD = "";

            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
    
    private static String toCSVFileName(String xlsfile){
        if(xlsfile!=null){
            return xlsfile.substring(0, xlsfile.length()-3)+"csv";
        }else{
            return "";
        }
    }
    
    public static void main(String args[]) throws Exception {
		String parameters =args.length>0? args[0] : null; // program parameter pass to
		// concurrent program, example
		// p1=1234:p2=5678:p3=abcde
		int userId = 0; // user id running the program
    	JavaConcurrentProgram c=new CanonCustomBillingServerInvConcProg();
    	c.runProgram(cpContext(parameters));
    }

    
}
