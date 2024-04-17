package  oracle.apps.e471.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import business.parts.NSZC043001PMsg;
import business.parts.NSZC061001PMsg;
import canon.apps.common.CanonConstants;
import canon.apps.common.CanonCustomProfile;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.sun.mail.util.MailSSLSocketFactory;


public class CanonE471SRCreationInboxReader {
	
    final static Pattern PATTERN_LAST_NUM_DAY = Pattern.compile("last (.*?) days?",Pattern.CASE_INSENSITIVE);
    final static Pattern PATTERN_ARCHIVE= Pattern.compile("Yes, move to (.*?) folder",Pattern.CASE_INSENSITIVE);
    
  
	private boolean textIsHtml = false;
	//private String subjectPattern = "SERVICE REQUEST CREATION";
	//Issues 706920,710480,701386
	private String subjectPattern = "SERVICE REQUEST";
	private String subjectPatternDevice = "SERVICE REQUEST FOR DEVICE";
	
	static String protocol = "imap";
	static String mbox = "INBOX";

	public static void main(String args[]) {
		new CanonE471SRCreationInboxReader().read();
	}

	@SuppressWarnings("unused")
	public void read() {
		Store store = null;
		Folder inbox = null;
		try {
			Date startDate = new Date();
			System.out.println("CanonE471SRCreationInboxReader Starred at: " + startDate);
			 
			CanonE471SRCreationDao dao = new CanonE471SRCreationDao();
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
			
			ImapInfo emailAccount=imapInfo(CanonCustomProfile.getSystemProfileValue("CANON_E471_EMAIL_ACCOUNT"));
			BigDecimal readNumDays=getLastNumDay(CanonCustomProfile.getSystemProfileValue("CANON_E471_INBOX_READ_NUM_DAYS"));
			Archive archive=archive(CanonCustomProfile.getSystemProfileValue("CANON_E471_ARCHIVE_OLD_EMAIL"));	
			
			try{
				// Get a Session object
				Session session = Session.getInstance(props, null);
				store = session.getStore(protocol);
				store.connect(emailAccount.mailServer, emailAccount.userName, emailAccount.password);
				//store.connect("VARHPR198.cusa.canon.com", "s21csa-appl-test@cusa.canon.com", "Canon1234");
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
			System.out.println("Checking for unprocessed E471 SR creation emails from " + messages.length + " emails....");

			int i = 0;
			for (Message message : messages) {				
				String subject = message.getSubject();	
				subject = subject.toUpperCase();
				//Issues 710480,701386 fix
				if ((subject.contains(subjectPattern) || subject.contains(subjectPatternDevice)) && message.match(dateRangeTerm)){
				
					i++;
					System.out.println("E471 SRCreation Email # " + i);
					System.out.println("Message ID :  "+ messageID(message));					
					System.out.println("Received Date : " + message.getReceivedDate());													         
					
					String text = getText(message, false);	
					boolean isProcessed = false;
					
					if(subject.contains(subjectPattern) && !(subject.contains(subjectPatternDevice))){
						isProcessed = processSRCreationEmail(text);
					}
					else if(subject.contains(subjectPatternDevice)) {
						isProcessed = processSRCreationEmail2(text);
					}
					
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
			
			// Call procedure to do validations on the inserted records 
		  dao.validateCalls();
			
			// call procedure to get all records with status as 'N' and then call s21 API for call creation
			ArrayList<CanonE471SRRequestData>  newRequests = dao.getNewRequests();
			for (CanonE471SRRequestData requestData:newRequests){	
				//  Bill code validations
			    String billCd  = null;
				try{

					billCd =getBillCode(requestData.getSvcMachMasterPk(), null, "0"); // 0 - Call Creation
				} catch(Exception e){
					 String errmsg = "Errror while Invoking s21 API for Bill Code";
					 String flag = "E";
					 String incidentId = null;
					 // Update  with status Error and Error message
					 dao.updateRecords(flag,errmsg,incidentId,requestData.getSerialNumber(), requestData.getCreationDate());
					 continue;
				}

				
				System.out.println("billCd - "+billCd);
				if (billCd != null){
					//  call procedure to validate if Customer is trying to create a call against a machine that is billable
					 Boolean isBillable = dao.isBillable(billCd,requestData.getSerialNumber(), requestData.getCreationDate());
					 if(isBillable){
						 continue;
					 }
				}

				String res[]  = createServicerequest(requestData);
				 if (res[0].equalsIgnoreCase("Y")){
					 String flag = "P";
					 String incidentId = res[1];
					 String errmsg = null;
					 System.out.println("FSR NUMBER: "+ res[1]);
					 // update Incident Id & Update CANON_E471_SR_WEB_TBL with status Processed
					 dao.updateRecords(flag,errmsg,incidentId,requestData.getSerialNumber(), requestData.getCreationDate());
				 } else {
					 String errmsg = res[1];
					 if(errmsg == null){
						 errmsg = "Errror while Invoking s21 API";
					 }
					 String flag = "E";
					 String incidentId = null;
					 // Update  with status Error and Error message
					 dao.updateRecords(flag,errmsg,incidentId,requestData.getSerialNumber(), requestData.getCreationDate());

				 }
			}

			// call procedure to get all records to send Email notifications
			ArrayList<CanonE471SRRequestData>  requests = dao.getRequestsForNotifications();
			for (CanonE471SRRequestData requestData:requests){				
				Boolean  isSent  = sendNotification(requestData);
				if(isSent){
					//  Update flag for email sent  
					dao.updateEmailSentFlag(requestData.getSerialNumber(), requestData.getCreationDate());
				}
			}
			
			System.out.println("Processed "+ i + " E471 SR creation emails  in "+ ( new Date().getTime() - startDate.getTime())/1000 + "Seconds");			
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
	


	private Boolean sendNotification(CanonE471SRRequestData requestData) {
		
		Boolean isSent = false; 
		 String host = CanonCustomProfile.getSystemProfileValue("CANON_E471_SMTP_HOST");
		 String from =  CanonCustomProfile.getSystemProfileValue("CANON_E471_FROM_EMAIL");
		 String to = null;
		 String phoneNum = null;

		 String subject = null;
		 String text =getText(requestData);
		 
		System.out.println("Sending Notification for: "+requestData.getSerialNumber() + " Status: " + requestData.getProcessedFlag());
				
		 if(requestData.getProcessedFlag() != null && "P".equalsIgnoreCase(requestData.getProcessedFlag().trim())){
			 subject = "Service Request Confirmation - " + requestData.getSerialNumber() +  " - Service Request #  " + requestData.getIncidentId();
			 to = requestData.getEmailAddress();
		 } else {
			 System.out.println( " Error: " + requestData.getErrorMessage());
			 subject = "CBS Website Failed Service Request -  " + requestData.getSerialNumber();
			 // Fetching Email Address based on State
			 CanonE471SRCreationDao dao = new CanonE471SRCreationDao();
			 ArrayList<String> contactInfo = new ArrayList<String>();
			 contactInfo = dao.getEmailFromState(requestData.getState(), requestData.getSource());//"agurram_consultant@cusa.canon.com";
			 
			 to = contactInfo.get(0);
			 phoneNum = contactInfo.get(1);					 
			 			 
		 }
		 System.out.println(subject);
		   //Get the session object  
		    Properties properties = System.getProperties();  
		    properties.setProperty("mail.smtp.host", host);  
		    Session session = Session.getDefaultInstance(properties);  

		   //compose the message  
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			message.setText(text);						

			// Send message
			Transport.send(message);
			 isSent = true; 
			System.out.println("Notification sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return isSent;
	}

	private String getText(CanonE471SRRequestData requestData) {
		 
		 String newLine 	= "\n";
		 String text 		= null;
		//Issues 706920,710480,701386 begin
		 String company 	= requestData.getCompany() == null ? "" : requestData.getCompany();
		 String name 		= requestData.getRequestedBy() == null ? "" : requestData.getRequestedBy();
		 String address 	= requestData.getAddress() == null ? "" : requestData.getAddress();
		 String address2 	= requestData.getAddress2() == null ? "" : requestData.getAddress2();
		 String city 		= requestData.getCity() == null ? "" : requestData.getCity();
		 String state 		= requestData.getState() == null ? "" : requestData.getState();
		 String zipCode 	= requestData.getZipCode() == null ? "" : requestData.getZipCode();
    	 String email		= requestData.getEmailAddress() == null ? "" : requestData.getEmailAddress();
		 String phone 		= requestData.getPhoneNumber() == null ? "" : requestData.getPhoneNumber();
		 String fax 		= requestData.getFaxNumber() == null ? "" : requestData.getFaxNumber();		
		 String serialNum 	= requestData.getSerialNumber() == null ? "" : requestData.getSerialNumber();
		 String meterRead 	= requestData.getMeterRead() == null ? "" : requestData.getMeterRead();
		 String errorMsg 	= requestData.getErrorMessage() == null ? "" : requestData.getErrorMessage();
		 String problem 	= requestData.getErrorMessage() == null ? "" : requestData.getProblem();
		//Issues 706920,710480,701386 end
				 		 
		if (requestData.getProcessedFlag() != null && "P".equalsIgnoreCase(requestData.getProcessedFlag().trim())) {
			
			CanonE471SRCreationDao dao = new CanonE471SRCreationDao();
			ArrayList<String> contactInfo = new ArrayList<String>();
			 contactInfo = dao.getEmailFromState(requestData.getState(), requestData.getSource());//"agurram_consultant@cusa.canon.com";
			 
			String to = contactInfo.get(0);
			String contactNum = contactInfo.get(1);		
			
			text = "Dear Valued Customer,"
					+ newLine
					+ "A Service Request has been created in our system based on the information you have provided below.  If you have any questions, please feel free to call us at "+ contactNum +" and your confirmation number is "
					+ requestData.getIncidentId() + ".";
		} else {			
			text = "A Service Request was attempted to be placed from the CBS Website.  This request has failed.  Please review the below information and place a Service Request or contact the customer to gather more information."
					+ newLine
					+ newLine
					+ "Error Message:  "
					+ requestData.getErrorMessage();
		}
				
		text = text + newLine 
                + newLine
                + "Company: "       +  company
                + newLine
                + "Name: "          +  name
                + newLine
                + "Address: "       +  address
                + newLine
                + "Address2: "      +  address2
                + newLine
                + "City: "          +  city
                + newLine
                + "State: "         +  state
                + newLine
                + "Zip Code: "      +  zipCode
                + newLine
                + "Email Address: " +  email
                + "\t"                  
                + newLine 
                + "Phone Number: "  +  phone
                + newLine 
                + "Fax Number: "    +  fax
                + newLine
                + "Serial Number: " +  serialNum
                + newLine
                + "Meter Reading: " +  meterRead
                + newLine
                + "Message: "       +  problem
                + newLine
                + newLine
                +  "Thank You,"
                + newLine
                + newLine
                +  "Canon ";				
 		 		
 		return text;
	}

	private boolean processSRCreationEmail(String emailBody) {

		String serialLabel =  "SERIAL# OR TAG#:";
		String incidentLabel =  "CUSTOMER INCIDENT# - CASE #:";
		String companyLabel  =  "COMPANY:";
		String address1Label =  "ADDRESS 1:";
		String address2Label =  "ADDRESS 2:";
		String cityLabel   =  "CITY:";
		String stateLabel  =  "STATE:";
		String postalCodeLabel =  "POSTAL_CODE:";
		String spInstructLabel =  "SPECIAL INSTRUCTIONS:";
		String requestedByLabel =  "REQUESTED BY:";
		String phoneLabel  =  "PHONE:";
		String emailAddressLabel =  "EMAIL ADDRESS:";
		String srIssueLabel =  "SERVICE REQUEST ISSUE:";
		String serviceTypeLabel =  "SERVICE TYPE";
		
		
		String serial = null; 
		String incident = null;  
		String company = null;
		String address1 = null;  
		String address2 = null;
		String city = null;
		String state = null;
		String postalCode = null;
		String spInstruct = null;
		String requestedBy = null;
		String phone = null; 
		String emailAddress = null;
		String srIssue = null;
		String serviceType = null;
		String statusCd = "N";
		String statusMsg = "";
		
		String contactName = null;
		String contactPhone = null;
		String contactEmail = null;
		String source = "EMAIL";
		
		
		String emailContent[] = emailBody.split("\\r\\n|\\n|\\r");
		
		 for (String emailLine  : emailContent) {
			if (emailLine != null && (emailLine.contains(serialLabel))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 serial = emailLine.substring(beginIndex + 1);
	        	 if (!serial.isEmpty() )
	        		 serial = serial.trim();	 
	         } 
			
			else if (emailLine != null && (emailLine.contains(incidentLabel))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 incident = emailLine.substring(beginIndex + 1);
	        	 if (!incident.isEmpty() )
	        		 incident = incident.trim();
	         }	        
				
				else if (emailLine != null && (emailLine.contains(companyLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 company = emailLine.substring(beginIndex + 1);
		        	 if (!company.isEmpty() )
		        		 company = company.trim();
		         }
			
			
				else if (emailLine != null && (emailLine.contains(address1Label))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 address1 = emailLine.substring(beginIndex + 1);
	        	 if (!address1.isEmpty() )
	        		 address1 = address1.trim();
	         }
				
			else if (emailLine != null && (emailLine.contains(address2Label))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 address2 = emailLine.substring(beginIndex + 1);
		        	 if (!address2.isEmpty() )
		        		 address2 = address2.trim();
		         }
		        
				
				else if (emailLine != null && (emailLine.contains(cityLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 city = emailLine.substring(beginIndex + 1);
		        	 if (!city.isEmpty() )
		        		 city = city.trim();
		         }
				
				else if (emailLine != null && (emailLine.contains(stateLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 state = emailLine.substring(beginIndex + 1);
		        	 if (!state.isEmpty() )
		        		 state = state.trim();
		         }
		        
				
				else if (emailLine != null && (emailLine.contains(postalCodeLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 postalCode = emailLine.substring(beginIndex + 1);
		        	 if (!postalCode.isEmpty() )
		        		 postalCode = postalCode.trim();
		         }
				
				
				else if (emailLine != null && (emailLine.contains(spInstructLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 spInstruct = emailLine.substring(beginIndex + 1);
		        	 if (!spInstruct.isEmpty() )
		        		 spInstruct = spInstruct.trim();
		         }
				
				else if (emailLine != null && (emailLine.contains(requestedByLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 requestedBy = emailLine.substring(beginIndex + 1);
		        	 if (!requestedBy.isEmpty() )
		        		 requestedBy = requestedBy.trim();
		         }
		        
				
				else if (emailLine != null && (emailLine.contains(phoneLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 phone = emailLine.substring(beginIndex + 1);
		        	 if (!phone.isEmpty() )
		        		 phone = phone.trim();
		         }
				
				else if (emailLine != null && (emailLine.contains(emailAddressLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 emailAddress = emailLine.substring(beginIndex + 1);
		        	 if (!emailAddress.isEmpty() )
		        		 emailAddress = emailAddress.trim();
		         }
		        
				
				else if (emailLine != null && (emailLine.contains(srIssueLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 srIssue = emailLine.substring(beginIndex + 1);
		        	 if (!srIssue.isEmpty() )
		        		 srIssue = srIssue.trim();
		         }
				
				else if (emailLine != null && (emailLine.contains(serviceTypeLabel))){
		        	 int beginIndex = emailLine.indexOf(':');
		        	 serviceType = emailLine.substring(beginIndex + 1);
		        	 if (!serviceType.isEmpty() )
		        		 serviceType = serviceType.trim();
		         }
		 }	
			
		if (serial == null ||  serial.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "SERIAL_NULL, ";
		}

		if (incident== null || incident.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "INCIDENT_NULL, ";
		}

		if (company == null || company.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "COMPANY_NULL, ";
		}

		if (address1 == null || address1.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "ADDRESS1_NULL, ";
		}

		if (address2 == null || address2.isEmpty()) {
			//statusCd = "E";
			statusMsg = statusMsg + "ADDRESS2_NULL, ";
		}

		if (city == null || city.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "CITY_NULL, ";
		}

		if (state == null || state.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "STATE_NULL, ";
		}

		if (postalCode == null || postalCode.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "POSTAL_CODE_NULL, ";
		}

		if (spInstruct == null || spInstruct.isEmpty()) {
			//statusCd = "E";
			statusMsg = statusMsg + "SPECIAL_INSTRUCTION_NULL, ";
		}

		if (requestedBy == null || requestedBy.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "REQUESTED_BY_NULL, ";
		}

		if (phone == null || phone.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "PHONE_NULL, ";
		}

		if (emailAddress == null || emailAddress.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "EMAIL_NULL, ";
		}

		if (srIssue == null || srIssue.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "SR_ISSUE_NULL, ";
		}

		if (serviceType == null || serviceType.isEmpty()) {
			statusCd = "E";
			statusMsg = statusMsg + "SERVICE_TYPE_NULL, ";
		}
			
		 System.out.println(">>serial:"+serial );
		 System.out.println(">>statusCd:"+statusCd );
		 System.out.println(">>statusMsg:"+statusMsg );
		 
		 CanonE471SRCreationDao dao = new CanonE471SRCreationDao();
		 boolean isInserted =  dao.insertData(serial,incident,company,address1,address2,city,state,
				 postalCode,spInstruct,requestedBy,phone, emailAddress,srIssue,serviceType,statusCd, statusMsg,
				 contactName, contactPhone, contactEmail, source);		
		 
		return isInserted;
	}
	
	private boolean processSRCreationEmail2(String emailBody) {

		String serialLabel =  "Serial Number";
		String modelLabel = "Model Number";
		String companyLabel  =  "Company Name";
		String requestedByLabel =  "Requestor Name";
		String phoneLabel  =  "Requestor Phone Number";
		String emailAddressLabel =  "Requestor Email Address";
		String contactNameLabel = "On Site Contact Name";
		String contactPhoneLabel = "On Site Contact Phone Number";
		String contactEmailLabel = "On Site Contact Email Address";
		String floorLabel = "Current Device Location Floor/Suite";
		String address1Label =  "Current Device Location Address 1";
		String address2Label =  "Current Device Location Address 2";
		String cityLabel   =  "Current Device Location City";
		String stateLabel  =  "Current Device Location State";
		String postalCodeLabel =  "Current Device Location Zip";		
	//	String spInstructLabel =  "Service Request Details";
		

		String srIssueLabel =  "Service Request Details"; //not available same as spl instr
	//	String serviceTypeLabel =  "SERVICE TYPE"; // not available default to '1-SERV CALL' value ??
		
		
		String serial = ""; 
		String incident = "";  
		String company = "";
		String address1 = "";  
		String address2 = "";
		String city = "";
		String state = "";
		String postalCode = "";
		String spInstruct = "";
		String requestedBy = "";
		String phone = ""; 
		String emailAddress = "";
		String srIssue = "";//= spInstruct
		String serviceType = "1-SERV CALL";
		String statusCd = "N";
		String statusMsg = "";
		
		String model = "";
		String floor = "";
		String contactName = "";
		String contactPhone = "";
		String contactEmail = "";
		String source = "EMAIL_FROM_CC";
		
		String faxNumber = "";
		String meterRead = "";
	
		
		String emailContent[] = emailBody.split("\\r\\n|\\n|\\r");		
		String label = null;
		 for (String emailLine  : emailContent) {		
			 
			if (emailLine != null && (emailLine.contains(serialLabel))){
				 label = "serialLabel";	   
				 continue;
				} 
			
			else if (emailLine != null && (emailLine.contains(companyLabel))){
				  label = "companyLabel";	
				  continue;
		         }
			
			
			else if (emailLine != null && (emailLine.contains(address1Label))){
				label = "address1Label";
				continue;
	         	 }
				
			else if (emailLine != null && (emailLine.contains(address2Label))){
				label = "address2Label";
				continue;
		         }		        
				
			else if (emailLine != null && (emailLine.contains(cityLabel))){
				label = "cityLabel";
				continue;
	         }
			
			else if (emailLine != null && (emailLine.contains(stateLabel))){
				label = "stateLabel";
				continue;
	         }
	        
			
			else if (emailLine != null && (emailLine.contains(postalCodeLabel))){
				label = "postalCodeLabel";
				continue;
	         }
			

			else if (emailLine != null && (emailLine.contains(requestedByLabel))){
				label = "requestedByLabel";
				continue;
	         }
		        
				
			else if (emailLine != null && (emailLine.contains(phoneLabel))){
				label = "phoneLabel";
				continue;
	         }
			
			else if (emailLine != null && (emailLine.contains(emailAddressLabel))){
				label = "emailAddressLabel";
				continue;
	         }
	        
			
			else if (emailLine != null && (emailLine.contains(srIssueLabel))){
				label = "srIssueLabel";
				continue;
	         }
			

			else if (emailLine != null && (emailLine.contains(contactNameLabel))){
				label = "contactNameLabel";
				continue;
	         }
			
			else if (emailLine != null && (emailLine.contains(contactPhoneLabel))){
				label = "contactPhoneLabel";
				continue;
	         }
		
			else if (emailLine != null && (emailLine.contains(contactEmailLabel))){
				label = "contactEmailLabel";
				continue;
	         }
			else if (emailLine != null && (emailLine.contains(modelLabel))){
				label = "modelLabel";
				continue;
	         }
		
			else if (emailLine != null && (emailLine.contains(floorLabel))){
				label = "floorLabel";
				continue;
	         }
			
			if(emailLine != null && label != null && !emailLine.equals("")){
				 if(label.equals("serialLabel")){						
						serial = serial + emailLine;
				 }
				 else if(label.equals("companyLabel")){						
					 company =  company + emailLine;

				 }
				 else if(label.equals("address1Label")){						
					 address1 =  address1 + emailLine;

				 }
				 else if(label.equals("address2Label")){						
					 address2 =  address2 + emailLine;

				 }
				 else if(label.equals("cityLabel")){						
					 city =  city + emailLine;

				 }
				 else if(label.equals("stateLabel")){						
					 state = state + emailLine;

				 }
				 else if(label.equals("postalCodeLabel")){						
					 postalCode = postalCode + emailLine;

				 }
				 else if(label.equals("requestedByLabel")){						
					 requestedBy =  requestedBy + emailLine;

				 }
				 else if(label.equals("phoneLabel")){						
					 phone =  phone + emailLine;

				 }
				 else if(label.equals("emailAddressLabel")){						
					 emailAddress = emailAddress + emailLine;

				 }
				 else if(label.equals("srIssueLabel")){						
					 srIssue = srIssue + emailLine;

				 }
				 else if(label.equals("contactNameLabel")){						
					 contactName =  contactName + emailLine;

				 }
				 else if(label.equals("contactPhoneLabel")){						
					 contactPhone =  contactPhone + emailLine;

				 }
				 else if(label.equals("contactEmailLabel")){						
					 contactEmail =  contactEmail + emailLine;

				 }
				 else if(label.equals("modelLabel")){						
					 model = model + emailLine;

				 }
				 else if(label.equals("floorLabel")){						
					 floor = floor + emailLine;

				 }
				 
				}		
						
		 }

			
		 System.out.println(">>serial:"+serial );
		 System.out.println(">>statusCd:"+statusCd );
		 System.out.println(">>statusMsg:"+statusMsg );
		 
		 CanonE471SRCreationDao dao = new CanonE471SRCreationDao();
		 boolean isInserted =  dao.insertData(serial,incident,company,address1,address2,city,state,
				 postalCode,spInstruct,requestedBy,phone, emailAddress,srIssue,serviceType,statusCd, statusMsg,
				 contactName, contactPhone, contactEmail, source);		
		 
		return isInserted;
	}
		
	public String[] createServicerequest(CanonE471SRRequestData requestData){
			
		String res[] = new String[2];
		 int memoCnt=0;
		 String General = "17";
		 String splmsgcd = "13";
		 String contactInfoCd = "16";
            
   		 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
   		 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
   		 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
   		 
			String csaCompanyCode = CanonConstants.CSA_COMPANY_CODE;
	        String  MODE_CODE_SR_CREATE="01";
            String ERROR_CODE = "0013"; // Problem type
   		     String USER_ID = CanonCustomProfile.getSystemProfileValue("CANON_E471_USER_ID");
            
   		 
			
			SimpleDateFormat format = new SimpleDateFormat("z");
	        SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String timezone = format.format(new Date());
	        String invokeTimestamp = lsDateFmt.format(new Date());
	        EZDDBCICarrier.initOnline(USER_ID, invokeTimestamp, timezone, csaCompanyCode);
	        EZDDBCICarrier.setProgID("EXTNE307");
	        
			NSZC043001 s21Api = new NSZC043001();
			NSZC043001PMsg pmsg = new NSZC043001PMsg();
			String source;
			       //Set  value to the SR
				if(csaCompanyCode != null && csaCompanyCode.length() > 0)
					pmsg.glblCmpyCd.setValue(csaCompanyCode);    
				if(USER_ID != null && USER_ID.length() > 0)
				    pmsg.userId.setValue(USER_ID); 
				if(MODE_CODE_SR_CREATE != null && MODE_CODE_SR_CREATE.length() > 0)
					pmsg.xxModeCd.setValue(MODE_CODE_SR_CREATE);  
				if(ERROR_CODE != null && ERROR_CODE.length() > 0)
					pmsg.svcPblmTpCd.setValue(ERROR_CODE);
				if(requestData.getSvcMachMasterPk() != null && requestData.getSvcMachMasterPk().length() > 0)	
					pmsg.svcMachMstrPk.setValue(new BigDecimal(requestData.getSvcMachMasterPk()));	
					if(requestData.getSource().equals("EMAIL") || requestData.getSource().equals("EMAIL_FROM_CC")){
						source = "30";
					} else {
						source = "31"; //CSA
					}
					if(source != null &&  source.length() > 0)	
			        pmsg.svcCallSrcTpCd.setValue(source);
					if(requestData.getServiceTypeCd() != null && requestData.getServiceTypeCd().length() > 0)	
					pmsg.dsSvcCallTpCd.setValue(requestData.getServiceTypeCd());

					
					pmsg.xxTmZnConvtFlg.setValue("Y");
					pmsg.svcTaskRcvDt.setValue(sSysDt);
					pmsg.svcTaskRcvTm.setValue(sSysTm);
					pmsg.slsDt.setValue(sSysDt); 
					
					if(requestData.getSerialNumber() != null && requestData.getSerialNumber().length() > 0)
					pmsg.serNum.setValue(requestData.getSerialNumber());  
					if(requestData.getEmailAddress() != null && requestData.getEmailAddress().length() > 0)
					pmsg.custEmlAddr.setValue(requestData.getEmailAddress());
					if(requestData.getPhoneNumber() != null && requestData.getPhoneNumber().length() > 0)
					pmsg.custTelNum.setValue(requestData.getPhoneNumber());	
					if(requestData.getCaseNumber() != null && requestData.getCaseNumber().length() > 0)
					pmsg.custCseNum.setValue(requestData.getCaseNumber());
					if(requestData.getRequestedBy() != null && requestData.getRequestedBy().length() > 0)
					pmsg.svcCustAttnTxt.setValue(requestData.getRequestedBy());
					

					 String srIssue     = requestData.getProblem();
					 if(srIssue != null && srIssue.length() > 0) {
						 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(srIssue);
						 pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(General);
						 memoCnt++;
					 }
					 String splInst     = requestData.getSpecialInstructions();
					 if(splInst != null && splInst.length() > 0) {
						 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(splInst);
						 pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(splmsgcd);
						 memoCnt++;
					 }
					 String contactInfo     = requestData.getContactName() + requestData.getContactPhone() + requestData.getContactEmail();
					 if(requestData.getSource().equals("EMAIL_FROM_CC")){
						 if(contactInfo != null && contactInfo.length() > 0) {
							 pmsg.svcMemoList.no(memoCnt).svcCmntTxt.setValue(contactInfo);
							 pmsg.svcMemoList.no(memoCnt).svcMemoTpCd.setValue(contactInfoCd);
							 memoCnt++;
						 }
					}		
					
					pmsg.taskList.setValidCount(1); 
					pmsg.taskList.no(0).svcCallPrtyCd.setValue("4"); //??
					pmsg.taskList.no(0).mblIntfcFlg.setValue("Y"); 
					pmsg.taskList.no(0).futSvcDt.setValue(sSysDt);
					pmsg.taskList.no(0).futSvcTm.setValue(sSysTm);
					pmsg.taskList.no(0).svcCustCllrTelNum.setValue(requestData.getPhoneNumber());
			
			try {		        
				//execute API
				s21Api.execute(pmsg, ONBATCH_TYPE.BATCH);  
				// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
				System.out.println("After execute, isXxMsgId=" + S21ApiUtil.isXxMsgId(pmsg));				
					if (!S21ApiUtil.isXxMsgId(pmsg)) {
						// There is no message id, so can do commit the transaction.
						System.out.println("No error, before commit");
						EZDConnectionMgr.getInstance().commit(); 
						res[0]="Y";
						res[1]=pmsg.fsrNum.getValue();
					} else {
						StringBuffer sb=new StringBuffer("");
						// Error Case - S21API set some error message id when got any error while S21API's function.		
						List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : err) {
							System.out.println( "FSR Creation Error :"+msg);
							sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
						}		
						res[0]="E";
						res[1]=sb.toString();	
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
        

public String getBillCode(String machPk, String slsdate, String modeCd)
{
        String billCode = null;
       System.out.println(".getBillCode Before BillCode API call: "+machPk+" modeCd: "+modeCd);
        
        try
        {
		   	 SimpleDateFormat sdfDt = new SimpleDateFormat("yyyyMMdd");
			 SimpleDateFormat sdfTm = new SimpleDateFormat("HHmmss");
			 String sSysDt=sdfDt.format(Calendar.getInstance().getTime());
			 String sSysTm=sdfTm.format(Calendar.getInstance().getTime());
			   
		    NSZC061001 billCodeApi = new NSZC061001();
			
			NSZC061001PMsg  pmsg = new NSZC061001PMsg();
			pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
			pmsg.xxModeCd.setValue(modeCd); //0:Call Creation, 1:Call Close								
			pmsg.startDt.setValue(sSysDt);
			pmsg.startTm.setValue(sSysTm);
			pmsg.svcMachMstrPk.setValue( new BigDecimal( machPk ) );
			
	        			
	       billCodeApi.execute(pmsg, ONBATCH_TYPE.ONLINE);
	                
	        try {
				// Normal Case. (No error msg) - S21API set some error message id when got any error while S21API's function.
				
					if (!S21ApiUtil.isXxMsgId(pmsg)) {
						billCode =  pmsg.svcBillTpCd.getValue();  
					} else {
						StringBuffer sb=new StringBuffer("");
							// Error Case - S21API set some error message id when got any error while S21API's function.		
							List<String> err = S21ApiUtil.getXxMsgIdList(pmsg);
							for (String msg : err) {
								System.out.println("getBillCode"+msg);
								sb.append(S21MessageFunc.clspGetMessage(msg)+"\n");
							}							
							// If S21API got error, roll-back the transaction.
								EZDConnectionMgr.getInstance().rollback(); 
					}
				} catch (Exception e) {
		        	e.printStackTrace();
				} finally {	// Release DB resource (Close DB Connection)	
					//EZDConnectionMgr.getInstance().releaseResource();
				}
             
	    }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return billCode;
 }
	
}