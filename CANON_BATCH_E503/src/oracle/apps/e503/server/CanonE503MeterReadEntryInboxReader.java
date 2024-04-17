package  oracle.apps.e503.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC061001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonCustomProfile;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.sun.mail.util.MailSSLSocketFactory;


public class CanonE503MeterReadEntryInboxReader {
	
    final static Pattern PATTERN_LAST_NUM_DAY = Pattern.compile("last (.*?) days?",Pattern.CASE_INSENSITIVE);
    final static Pattern PATTERN_ARCHIVE= Pattern.compile("Yes, move to (.*?) folder",Pattern.CASE_INSENSITIVE);


	private boolean textIsHtml = false;
	private String subjectPattern = "METER READ";  // TODO
	static String protocol = "imap";
	static String mbox = "INBOX";

	public static void main(String args[]) {
		new CanonE503MeterReadEntryInboxReader().read();
	}

	@SuppressWarnings("unused")
	public void read() {
		Store store = null;
		Folder inbox = null;
		try {
			Date startDate = new Date();
			System.out.println("CanonE503MeterReadEntryInboxReader Startred at: " + startDate);
			 
			CanonE503MeterReadEntryDao dao = new CanonE503MeterReadEntryDao();
			// Get a Properties object
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol",protocol);
		
			// To enable ssl in test environments
		    boolean enableSSL = true; // Get this value from profile if needed
			if (enableSSL){
				try {
					MailSSLSocketFactory sf = new MailSSLSocketFactory();
					sf.setTrustAllHosts(true);
					props.put("mail.imap.ssl.trust", "*");
					props.put("mail.imap.ssl.socketFactory", sf);
					props.put("mail.imap.starttls.enable", "true");
					props.put("mail.imap.auth", "true");
				} catch (Exception e) {
					System.out.println("Failed to enable ssl ");
					e.printStackTrace();
					System.exit(0);
				}
			}
			
			ImapInfo emailAccount=imapInfo(CanonCustomProfile.getSystemProfileValue("CANON_E503_EMAIL_ACCOUNT"));
			BigDecimal readNumDays=getLastNumDay(CanonCustomProfile.getSystemProfileValue("CANON_E503_INBOX_READ_NUM_DAYS"));
			Archive archive=archive(CanonCustomProfile.getSystemProfileValue("CANON_E503_ARCHIVE_OLD_EMAIL"));	
			
			try{
				// Get a Session object
				Session session = Session.getInstance(props, null);		
				store = session.getStore(protocol);
				store.connect(emailAccount.mailServer, emailAccount.userName, emailAccount.password);
			} catch (Exception e){
				System.out.println("### Failed to connect ###");
				e.printStackTrace();
				System.exit(0);
			}

			
			 inbox = store.getFolder(mbox);
		     inbox.open(Folder.READ_WRITE);				

			Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.DATE,-readNumDays.intValue());
		    Date beginDt =  cal.getTime();
			SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, new Date());
			SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, beginDt);
			SearchTerm dateRangeTerm = new AndTerm(olderThan, newerThan);
			
			
			FlagTerm notDeletedFlagTerm = new FlagTerm(new Flags(Flags.Flag.DELETED), false);
			Message[] messages = inbox.search(notDeletedFlagTerm);

			if (messages.length == 0) {
				System.out.println("No messages found.");
				System.exit(0);
			}
			System.out.println("Checking for unprocessed E503 MR emails from " + messages.length + " emails....");

			int i = 0;
			for (Message message : messages) {				
				String subject = message.getSubject();
				if (subject.contains(subjectPattern) && message.match(dateRangeTerm)){
					i++;
					System.out.println("E503 MR Entry Email # " + i);
					System.out.println("Message ID :  "+ messageID(message));
					System.out.println("Received Date : " + message.getReceivedDate());
					
					String text = getText(message, false);	
					System.out.println("text: "+ text);
					if(text!=null && text!="null"){
						boolean isProcessed = processMREmail(text);
						
						if (isProcessed) {
							 try{     			
								Object[]   result =    dao.insertDmEmailArch(sender(message), message.getSubject(), null, messageID(message), null);
							 }catch(Exception e){
			        			 e.printStackTrace();
			        			}						 
							message.setFlag(Flags.Flag.DELETED, true);
							String archFolderName=String.format(archive.folderTemplate, message.getReceivedDate());
							Folder archFolder=store.getFolder(archFolderName);
							if(archFolder.exists()){
								moveMessageToFolder(message, archFolder);
							}else{
								//throw new Error("Archive folder "+archFolderName+" not found.");
							}
						}
					}
				}						
			}		
			
			// Call procedure to do validations on the inserted records 
		  dao.validateCalls();	  

			// call procedure to get all records to call s21 API for MR Entry
			ArrayList<CanonE503MREntry>  newRequests = dao.getNewRequests();
			for (CanonE503MREntry requestData:newRequests){	
				String res[]  = enterMeterReads(requestData);
				 if (res[0].equalsIgnoreCase("Y")){
					 String flag = "P";
					 String errmsg = null;
					   //Update  with status Processed
					 dao.updateRecords(flag,errmsg,requestData.getSerialNumber(), requestData.getMtrReadDt());
				 } else {
					 String errmsg = res[1];
					 if(errmsg == null){
						 errmsg = "Errror while Invoking s21 API";
					 }
					 String flag = "E";
					 String incidentId = null;
					 // Update  with status Error and Error message
					 dao.updateRecords(flag,errmsg,requestData.getSerialNumber(), requestData.getMtrReadDt());

				 }
			}	
			System.out.println("Processed "+ i + " E503 MR emails  in "+ ( new Date().getTime() - startDate.getTime())/1000 + "Seconds");		
			System.out.println("### Exiting Program CanonE503MeterReadEntryInboxReader ###" );
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			//close the store and folder objects		    
		    try {
				EZDConnectionMgr.getInstance().releaseResource();
		    	if (inbox != null )
		    	  inbox.close(false);
				if (store != null)
		    	  store.close();			
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

	}
	


	
	

	private boolean processMREmail(String emailBody) {		
		
		String date = null; 
		String model = null;  
		String rdg = null;
		String rdg3A = null;  
		String serial = null;
		
		String rdg2A = null;
		String rdg2B = null;
		String rdg2C = null;
		String rdg2D = null;
		String rdg2E = null;

		String totalReading = null;
		String bwReading = null;

		
		     ArrayList<String> datePatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_DATE_PATTERN"));
		     ArrayList<String> modelPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_MODEL_PATTERN"));	     
		     ArrayList<String> rdgPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG_PATTERN"));
		     ArrayList<String> rdg3APatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG3_PATTERN"));	     
		     ArrayList<String> serialPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_SERIALNO_PATTERN"));
		     
		     ArrayList<String> rdg2APatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG2A_PATTERN"));		
		     ArrayList<String> rdg2BPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG2B_PATTERN"));
		     ArrayList<String> rdg2CPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG2C_PATTERN"));	     
		     ArrayList<String> rdg2DPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG2D_PATTERN"));
		     ArrayList<String> rdg2EPatterns = getList(CanonCustomProfile.getSystemProfileValue("CANON_E503_RDG2E_PATTERN"));

		System.out.println("emailBody: "+ emailBody);
		String emailContent[] = emailBody.split("\\r\\n|\\n|\\r");
		
		 for (String emailLine  : emailContent) {
			// System.out.println("emailLine: "+ emailLine);
			if (emailLine != null && ( containsPattern(emailLine, datePatterns))){
	        	 int beginIndex = 0;
	        	 if(emailLine.indexOf(':')>0){
	        		 beginIndex =  emailLine.indexOf(':');
	        	 }else if(emailLine.indexOf(',')>0){
	        		 beginIndex =  emailLine.indexOf(',');
	        	 }else{
	        		 beginIndex = emailLine.indexOf('=');
	        	 }
	        	 String str = emailLine.substring(beginIndex + 1);
	        	 if (!str.isEmpty() )
	        		 date = str.trim();	 
	         } 			
			
 			if (emailLine != null && ( containsPattern(emailLine, modelPatterns))){
				 int beginIndex=0;
				 if(emailLine.indexOf(':')>0){
	        		 beginIndex =  emailLine.indexOf(':');
	        	 }else if(emailLine.indexOf(',')>0){
	        		 beginIndex =  emailLine.indexOf(',');
	        	 }else{
	        		 beginIndex = emailLine.indexOf('=');
	        	 }
	        	 String str = emailLine.substring(beginIndex + 1);
	        	 if (!str.isEmpty() )
	        		 model = str.trim();	 
	         } 	
			
			if (emailLine != null && ( containsPattern(emailLine, rdgPatterns))){
				 int beginIndex=0;
				 if(emailLine.indexOf(':')>0){
	        		 beginIndex =  emailLine.indexOf(':');
	        	 }else if(emailLine.indexOf(',')>0){
	        		 beginIndex =  emailLine.indexOf(',');
	        	 }else{
	        		 beginIndex = emailLine.indexOf('=');
	        	 }
	        	 String str = emailLine.substring(beginIndex + 1);
	        	 if (!str.isEmpty() )
	        		 rdg  = str.trim();	 
	         } 	
			
			if (emailLine != null && ( containsPattern(emailLine, rdg3APatterns))){
				 int beginIndex=0;
				 if(emailLine.indexOf(':')>0){
	        		 beginIndex =  emailLine.indexOf(':');
	        	 }else if(emailLine.indexOf(',')>0){
	        		 beginIndex =  emailLine.indexOf(',');
	        	 }else{
	        		 beginIndex = emailLine.indexOf('=');
	        	 }
	        	 String str = emailLine.substring(beginIndex + 1);
	        	 if (!str.isEmpty() )
	        		 rdg3A = str.trim();	 
	         } 	
			
			if (emailLine != null && ( containsPattern(emailLine, serialPatterns))){
				 int beginIndex=0;
				 if(emailLine.indexOf(':')>0){
	        		 beginIndex =  emailLine.indexOf(':');
	        	 }else if(emailLine.indexOf(',')>0){
	        		 beginIndex =  emailLine.indexOf(',');
	        	 }else{
	        		 beginIndex = emailLine.indexOf('=');
	        	 }
	        	 String str = emailLine.substring(beginIndex + 1);
	        	 if (!str.isEmpty() )
	        		 serial = str.trim();	 
	         } 	
			
		 }	
			
		totalReading = rdg;
		bwReading = rdg3A;
		
		 System.out.println(">>serial:"+serial );
		 System.out.println(">>date:"+date );
		 System.out.println(">>model:"+model );
		 System.out.println(">>Total:"+totalReading );
		 System.out.println(">>B&W:"+bwReading ); 
		 
		 

		 
		 CanonE503MeterReadEntryDao dao = new CanonE503MeterReadEntryDao();
		 boolean isInserted =  dao.insertData(serial,date,model,totalReading,bwReading);		
		 
		return isInserted;
	}
		
	private boolean containsPattern(String emailLine,ArrayList<String> pattern) {

		for(String label: pattern){
		//	System.out.println("label: "+ label+" emailLine: "+emailLine);
			label = label.trim();
			if (emailLine.contains(label)){
				return true;
			}
		}
		return false;
	}
	
	
	
	public String[] enterMeterReads(CanonE503MREntry requestData){
		String res[] = new String[2];         
   		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
   		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
   		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
   		 
			String csaCompanyCode = CanonConstants.CSA_COMPANY_CODE;
	        String  SRC_TP_CD="IWR";
            String READ_TP_CD = "ATEM";
            String  GRP_CD="B";
   		     String USER_ID = CanonCustomProfile.getSystemProfileValue("CANON_E503_USER_ID");
   		     		
			SimpleDateFormat format = new SimpleDateFormat("z");
	        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String timezone = format.format(new Date());
	        String invokeTimestamp = lsDateFmt.format(new Date());
	        EZDDBCICarrier.initOnline(USER_ID, invokeTimestamp, timezone, csaCompanyCode);
	        EZDDBCICarrier.setProgID("EXTNE503");
	        
			 NSZC010001 s21Api=new  NSZC010001();
			 NSZC010001PMsg pMsg=new  NSZC010001PMsg();
             int noOfCounters  = requestData.getNoOfMeters();
			 
			 System.out.println("Total CounteID: "+requestData.getSvcTtlPhysMtrPk()+" BW CounterId"+requestData.getSvcBwPhysMtrPk());
             System.out.println("Read Date: "+requestData.getMtrReadDt()+" Total Read: "+requestData.getTotalRead()+" BW Read: "+requestData.getBwRead());             
			       //Set  value to the MR									
					 pMsg.slsDt.setValue(sSysDt); 			
					 pMsg.glblCmpyCd.setValue(csaCompanyCode);    
					 
					 pMsg.mtrReadSrcTpCd.setValue(SRC_TP_CD);
					 pMsg.dsMtrReadTpCd.setValue(READ_TP_CD); 
					 pMsg.dsMtrReadTpGrpCd.setValue(GRP_CD);
					 
					 if(requestData.getSvcMachMasterPk().length()>0){
						 pMsg.svcMachMstrPk.setValue(new BigDecimal(requestData.getSvcMachMasterPk()));	
					 }else{
						 pMsg.svcMachMstrPk.setValue(new BigDecimal("0"));	
					 }
					 pMsg.A.setValidCount(noOfCounters);				 
					 pMsg.A.no(0).mtrReadDt.setValue( sdfDt.format(requestData.getMtrReadDt()));   
					 pMsg.A.no(0).rgtnMtrDt.setValue(sSysDt);     
					 pMsg.A.no(0).rgtnUsrId.setValue(USER_ID);
					 
					 String meterRead = requestData.getTotalRead()==null?"0":requestData.getTotalRead();
					 String physMtrPk = requestData.getSvcTtlPhysMtrPk()==null?"0":requestData.getSvcTtlPhysMtrPk();
					 if(meterRead.length()>0){
						 pMsg.A.no(0).readMtrCnt.setValue(new BigDecimal(meterRead));
					 }else{
						 pMsg.A.no(0).readMtrCnt.setValue(new BigDecimal("0"));
					 }					 
					 if(physMtrPk.length()>0){ 
						 pMsg.A.no(0).svcPhysMtrPk.setValue(new BigDecimal(physMtrPk));
					 }else{
						 pMsg.A.no(0).svcPhysMtrPk.setValue(new BigDecimal("0"));
					 }
						 
					 pMsg.A.no(0).vldMtrFlg.setValue("Y");				
			
					 if (noOfCounters > 1){
						 pMsg.A.no(1).mtrReadDt.setValue(sdfDt.format(requestData.getMtrReadDt()));  
						 pMsg.A.no(1).rgtnMtrDt.setValue(sSysDt);         
						 pMsg.A.no(1).rgtnUsrId.setValue(USER_ID);
						 pMsg.A.no(1).readMtrCnt.setValue(new BigDecimal(requestData.getBwRead()));          
						 pMsg.A.no(1).svcPhysMtrPk.setValue(new BigDecimal(requestData.getSvcBwPhysMtrPk()));
						 pMsg.A.no(1).vldMtrFlg.setValue("Y");
					 }
			try {		
				System.out.println("Before execute E503 Meter Reads MachMstrPk: "+ requestData.getSvcMachMasterPk());
				//execute API
				s21Api.execute(pMsg, ONBATCH_TYPE.BATCH);  
				// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
				System.out.println("After execute E503 Meter Reads, isXxMsgId=" + S21ApiUtil.isXxMsgId(pMsg));				
					if (!S21ApiUtil.isXxMsgId(pMsg)) {
						// There is no message id, so can do commit the transaction.
						System.out.println("No error, before commit");
						EZDConnectionMgr.getInstance().commit(); 
						res[0]="Y";
						res[1]=pMsg.getReturnCode();
					} else {
						StringBuffer sb=new StringBuffer("");
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pMsg);
						for (String msg : err) {
							System.out.println( "MR Entry  Error :"+msg);
							sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
						}		
						res[0]="E";
						res[1]=sb.toString();	
						System.out.println( "MR Entry  Error Description :"+res[1]);
								// If S21API got error, roll-back the transaction.
								EZDConnectionMgr.getInstance().rollback(); 
					}
				} catch (Exception e) {
					res[0]="E";
					res[1]=e.getMessage();
					System.out.println("ERROR MESSAGE : " + e.getMessage());
					e.printStackTrace();
				} 
			return res;
		}
	

		
		
					
	private ArrayList<String> getList(String systemProfileValue) {
		ArrayList<String> aList= new ArrayList<String>(Arrays.asList(systemProfileValue.split(",")));
		return aList;
	}

	private String messageID(Message message) throws MessagingException {
		return ((MimeMessage) message).getMessageID();
	}
	
	private String sender(Message message) throws MessagingException {
		return InternetAddress.toString(message.getFrom());
	}
	
	private String getText(Part p, boolean preferHtml)
			throws MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp, preferHtml);
					if (preferHtml) {
						continue;
					} else {
						if (text != null)
							return text;
					}
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp, preferHtml);
					if (preferHtml) {
						if (s != null)
							return s;
					} else {
						continue;
					}
				} else {
					return getText(bp, preferHtml);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i), preferHtml);
				if (s != null)
					return s;
			}
		}
		return null;
	}
		
    private ImapInfo imapInfo(String acct){
    	if(acct==null){
    		throw new Error("Email account not found.");
    	}
    	String [] strs=acct.split("\\s+");
    	if(strs.length!=3){
    		throw new Error("Invalid email account "+acct);
    	}
    	return new ImapInfo(strs[0],strs[1],strs[2]);
    	
    }
    
    class ImapInfo {
    	private String mailServer;
    	private String userName;
    	private String password;
		public ImapInfo(String mailServer, String userName, String password) {
			super();
			this.mailServer = mailServer;
			this.userName = userName;
			this.password = password;
		}
		@Override
		public String toString() {
			return "ImapInfo [mailServer=" + mailServer + ", userName="
					+ userName + "]";
		}
    	
    }
          
private static BigDecimal getLastNumDay(String str){
    	Matcher m = PATTERN_LAST_NUM_DAY.matcher(str);
    	if(m.matches()){
    		return toBigDecimal(m.group(1),false);
    	}else{
    		return null;
    	}
    }
    
    public static BigDecimal BigDecimal_ZERO = new BigDecimal("0");
    public static BigDecimal toBigDecimal(String s, boolean nullToZero) {
        if (s != null && s.trim().length()>0) {
            try {
                return new BigDecimal(s.trim().replaceAll(",",""));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return nullToZero ? BigDecimal_ZERO : null;
    }
    
	private void moveMessageToFolder(Message message, Folder archFolder){
		Folder folder=message.getFolder();
		try {
			folder.copyMessages(new Message[]{message}, archFolder);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}
	
	class Archive {
    	private boolean archive;
    	private String folderTemplate;
		public Archive(boolean archive, String folderTemplate) {
			super();
			this.archive = archive;
			this.folderTemplate = folderTemplate;
		}
		
		@Override
		public String toString() {
			return "Archive [archive=" + archive + ", folderTemplate="
					+ folderTemplate + "]";
		}
    	
    }
	
    
private Archive archive(String archStr){
    	if(archStr!=null){
        	Matcher m = PATTERN_ARCHIVE.matcher(archStr);
        	if(m.matches()){
        		return new Archive(true, m.group(1));
        	}
    	}
		return new Archive(false,null);
    }
        
	
}