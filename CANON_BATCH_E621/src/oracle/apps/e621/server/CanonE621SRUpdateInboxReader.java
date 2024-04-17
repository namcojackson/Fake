package oracle.apps.e621.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
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

import com.sun.mail.util.MailSSLSocketFactory;

import oracle.apps.e621.server.CanonE621SRUpdateDao;
import oracle.apps.e621.server.CanonE621SRRequestData;
import oracle.apps.e621.server.CanonE621SRUpdateInboxReader.Archive;
import oracle.apps.e621.server.CanonE621SRUpdateInboxReader.ImapInfo;
import canon.apps.common.CanonCustomProfile;
import parts.dbcommon.EZDConnectionMgr;

public class CanonE621SRUpdateInboxReader {
		
	final static Pattern PATTERN_LAST_NUM_DAY = Pattern.compile("last (.*?) days?",Pattern.CASE_INSENSITIVE);
    final static Pattern PATTERN_ARCHIVE= Pattern.compile("Yes, move to (.*?) folder",Pattern.CASE_INSENSITIVE);
    
  
	private boolean textIsHtml = false;
	//private String subjectPattern = "SERVICE REQUEST CREATION";
	//Issues 706920,710480,701386
	private String subjectPattern = "ISSUE";
	private String subjectPatternDevice = "ASSIGNED";
	
	static String protocol = "imap";
	static String mbox = "INBOX";

	public static void main(String args[]) {
		new CanonE621SRUpdateInboxReader().read();
	}
	
	@SuppressWarnings("unused")
	public void read() {
		Store store = null;
		Folder inbox = null;
		try {
			Date startDate = new Date();
			System.out.println("CanonE621SRUpdateInboxReader Started at: " + startDate);
			
			CanonE621SRUpdateDao dao = new CanonE621SRUpdateDao();
			
			//Sending New SR's that are created for Rite Aid Serial Numbers
			ArrayList<CanonE621SRRequestData> srRequests = dao.createRiteAidSR();
			
			for (CanonE621SRRequestData requestData:srRequests){	
				requestData.setEmailTaskStatus("INITIAL STATUS        : "+ requestData.getTaskStatus().toUpperCase());
				requestData.setEmailType("SERVICE REQUEST SUCCESSFULLY CREATED");
				requestData.setEmailSubj("Canon Service "+requestData.getSerialNumber() + ", SR # "+ requestData.getIncidentNumber());
				requestData.setHelpDeskNum(null);

				Boolean  isSent  = sendNotification(requestData);
				if(isSent){					
					dao.insertData(requestData, "S");
				}
				else
					dao.insertData(requestData, "F");				
				}	
			
			//Sending updated status to Rite Aid
			ArrayList<CanonE621SRRequestData> srStatusUpdates = dao.getUpdatedStatus();
			
			for (CanonE621SRRequestData requestData:srStatusUpdates){	
								
				requestData.setEmailTaskStatus("SERVICE REQUEST STATUS  : "+ requestData.getTaskStatus().toUpperCase());
				requestData.setEmailType("SERVICE REQUEST STATUS UPDATE");
				requestData.setEmailSubj("RE: Issue # "+ requestData.getHelpDeskNum());			
				
				Boolean  isSent  = sendNotification(requestData);
				if(isSent){
					System.out.println("email sent to is" + requestData.getEmailSentTo());
					dao.updateStatusData(requestData, "S");
				}
				else
					dao.updateStatusData(requestData, "F");				
				}
			
			// check for emails to update help desk number
			
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
			
			ImapInfo emailAccount=imapInfo(CanonCustomProfile.getSystemProfileValue("CANON_E621_EMAIL_ACCOUNT"));
			BigDecimal readNumDays=getLastNumDay(CanonCustomProfile.getSystemProfileValue("CANON_E621_INBOX_READ_NUM_DAYS"));
			Archive archive=archive(CanonCustomProfile.getSystemProfileValue("CANON_E621_ARCHIVE_OLD_EMAIL"));	
			
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
			System.out.println("Checking for emails from riteAId" + messages.length + " emails....");
			
			int i = 0;
			for (Message message : messages) {				
				String subject = message.getSubject();	
				subject = subject.toUpperCase();
				//Issues 710480,701386 fix
				if ((subject.contains(subjectPattern) && subject.contains(subjectPatternDevice)) && message.match(dateRangeTerm)){
				
					i++;
					System.out.println("E621 SRCreation Email # " + i);
					System.out.println("Message ID :  "+ messageID(message));					
					System.out.println("Received Date : " + message.getReceivedDate());													         
					
					boolean isProcessed = false;					
					
					isProcessed = processSREmail(subject);					
					
					if (isProcessed) {										 
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
		
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(0);
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
	
	
private boolean processSREmail(String subject) {
	
	CanonE621SRUpdateDao dao = new CanonE621SRUpdateDao();
	System.out.println("subject is " + subject);
	String status = dao.prepareEmailRequest(subject);
	
	if(status != null && status.equals("S"))
		return true;	
	else
		return false;
	}

private Boolean sendNotification(CanonE621SRRequestData requestData) {
		
		Boolean isSent = false; 
		 String host = CanonCustomProfile.getSystemProfileValue("CANON_E621_SMTP_HOST");
		 String from =  CanonCustomProfile.getSystemProfileValue("CANON_E621_FROM_EMAIL");
		 String to = "";
		
		 String subject = null;
		 String text =getText(requestData);
		 
		System.out.println("Sending Notification for: "+requestData.getSerialNumber() );
		
		CanonE621SRUpdateDao dao = new CanonE621SRUpdateDao();
		
		ArrayList<String> riteAidEmails = new ArrayList<String>();
		riteAidEmails = dao.getRiteAidEmail();
		
		for (int i=0 ; i< riteAidEmails.size(); i++){	
			
			if(!to.equals("") || to.length() > 0){
				to = to + "," + riteAidEmails.get(i);			
			}
			else 
				to = riteAidEmails.get(i);
			
			System.out.println("to email is " + to);
			requestData.setEmailSentTo(to);
		}
			
		 subject = requestData.getEmailSubj();			
	
		 System.out.println(subject);
		   //Get the session object  
		    Properties properties = System.getProperties();  
		    properties.setProperty("mail.smtp.host", host);  
		    Session session = Session.getDefaultInstance(properties);  

		   //compose the message  
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setRecipients(Message.RecipientType.TO, to);
			
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


private String getText(CanonE621SRRequestData requestData) {
	 
	 String newLine 	= "\n";
	 String text 		= null;

	 String serialNum 	= requestData.getSerialNumber() == null ? "" : requestData.getSerialNumber();
	 String incidentNum = requestData.getIncidentNumber() == null ? "" : requestData.getIncidentNumber();
	 String helpDeskNum = requestData.getHelpDeskNum() == null ? "" : requestData.getHelpDeskNum();
	 String probDesc	= requestData.getProbDescription() == null ? "" : requestData.getProbDescription();
	 String taskStatus  = requestData.getEmailTaskStatus() == null ? "" : requestData.getEmailTaskStatus();
	 String creationDate= requestData.getCreationDate() == null ? "" : requestData.getCreationDate();
	 String contactInfo	= requestData.getContactInfo() == null ? "" : requestData.getContactInfo();
	 String emailType	= requestData.getEmailType() == null ? "" : requestData.getEmailType();
			 		 

	text = emailType
			+ newLine
			+ "-------------------------------------"
			+ newLine;
			
	text = text + newLine 
           + newLine
           + "SERIAL NUMBER : "       +  serialNum
           + newLine
           + "SERVICE REQUEST NUMBER: "       +  incidentNum;
	
     if(helpDeskNum != null  && helpDeskNum.length() > 0)
     	{
     text = text + newLine      	
           + "HELP DESK NUMBER     : "       +  helpDeskNum;
     	} 
          
     if(probDesc != null  && probDesc.length() > 0)
  		{
     text = text + newLine      	
        + "PROBLEM DESCRIPTION     : "       +  probDesc;
  		}
     
     text = text + newLine
           + taskStatus;
     
     if(creationDate != null)
		{
     text = text + newLine      	
    		+ "CREATION DATE         : "       +  creationDate;
		}
     
     text = text + newLine
    		+ "CONTACT INFORMATION   :  "      +  contactInfo;
          
     text = text + newLine
           + newLine
           +  "Thanks"
           + newLine
           + newLine
           +  "Oracle Service Team";		
		   
     System.out.println("text is " + text);
	 		
	return text;
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

private String messageID(Message message) throws MessagingException {
	return ((MimeMessage) message).getMessageID();
}

private String sender(Message message) throws MessagingException {
	return InternetAddress.toString(message.getFrom());
}
    
}
