package  oracle.apps.e307.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.sun.mail.util.MailSSLSocketFactory;

import canon.apps.common.CanonCustomProfile;


public class CanonE307UGWErrorsInboxReader {
	
    final static Pattern PATTERN_LAST_NUM_DAY = Pattern.compile("last (.*?) days?",Pattern.CASE_INSENSITIVE);
    final static Pattern PATTERN_ARCHIVE= Pattern.compile("Yes, move to (.*?) folder",Pattern.CASE_INSENSITIVE);
    //final static Pattern PATTERN_SUBJECT= Pattern.compile("Yes, move to (.*?) folder",Pattern.CASE_INSENSITIVE);


	private boolean textIsHtml = false;
	private String ugwErrorSubject = "[imageWARE Remote] Error Notification / Service Call Required /";
	static String protocol = "imap";
	static String mbox = "INBOX";

	public static void main(String args[]) {
		new CanonE307UGWErrorsInboxReader().read();
	}

	@SuppressWarnings("unused")
	public void read() {
		Store store = null;
		Folder inbox = null;
		try {
			Date startDate = new Date();
			System.out.println("CanonE307UGWErrorsInboxReader Startred at: " + startDate);
			 CanonE307UGWErrorsDao dao = new CanonE307UGWErrorsDao();
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
			
			ImapInfo emailAccount=imapInfo(CanonCustomProfile.getSystemProfileValue("CANON_E307_UGW_EMAIL_ACCOUNT"));
			BigDecimal readNumDays=getLastNumDay(CanonCustomProfile.getSystemProfileValue("CANON_E307_UGW_INBOX_READ_NUM_DAYS"));
			Archive archive=archive(CanonCustomProfile.getSystemProfileValue("CANON_E307_UGW_ARCHIVE_OLD_EMAIL"));	
			
			try{
				// GeDJt a Session object
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
			System.out.println("Checking for unprocessed ugw Error emails from " + messages.length + " emails....");

			int i = 0;
			for (Message message : messages) {				
				String subject = message.getSubject();
				if (subject.contains(ugwErrorSubject) && message.match(dateRangeTerm)){
					i++;
					System.out.println("UGW Error Email # " + i);
					System.out.println("Message ID :  "+ messageID(message));
					System.out.println("Received Date : " + message.getReceivedDate());

					
					String text = getText(message, false);								
					boolean isProcessed = processUGWErrorAlert(text);
					
					if (isProcessed) {
						//System.out.println("Purging emails before deleting in mail box ");
						 try{     			
							Object[]   result =   dao.insertDmEmailArch(sender(message), message.getSubject(), null, messageID(message), null);
						 }catch(Exception e){
		        			 e.printStackTrace();
		        			}						 
						message.setFlag(Flags.Flag.DELETED, true);
						String archFolderName=String.format(archive.folderTemplate, message.getReceivedDate());
						Folder archFolder=store.getFolder(archFolderName);
						if(archFolder.exists()){
							moveMessageToFolder(message, archFolder);
						}else{
							throw new Error("Archive folder "+archFolderName+" not found.");
						}
					}
				}						
			}		
			
		//CALL loadUGWErrors
		dao.loadUGWErrors();

			System.out.println("Processed "+ i + " UGW error emails in "+ ( new Date().getTime() - startDate.getTime())/1000 + "Seconds");		
			System.out.println("### Exiting Program CanonE307UGWErrorsInboxReader ###" );
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//close the store and folder objects		    
		    try {
		    	if (inbox != null )
		    	  inbox.close(false);
				if (store != null)
		    	  store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

	}
	
	private String sender(Message message) throws MessagingException {
		return InternetAddress.toString(message.getFrom());
	}
	
	private static String cleanup(String str){
		// remove <> for example <OFC2E9DEC3.F06F0794-ON85257F61.00572BB7@cusa.canon.com>
		if(str!=null && str.length()>0 && str.charAt(0)=='<' && str.charAt(str.length()-1)=='>'){
			return str.substring(1, str.length()-1);
		}else{
			return str;
		}
		
	}


	private boolean processUGWErrorAlert(String emailBody) {
		String deviceId = null;
		String errorCode = null;
		String occurred = null;
		String errorDesc = null;
		Timestamp occurredDate = null;
		boolean isDetails = false;
		
		String emailContent[] = emailBody.split("\\r\\n|\\n|\\r");
		
		 for (String emailLine  : emailContent) {
			 
			if (emailLine != null && (emailLine.contains("Device ID:") || emailLine.contains("Device ID :"))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 deviceId = emailLine.substring(beginIndex + 1);
	        	 if (!deviceId.isEmpty() )
	        		 deviceId = deviceId.trim();
	         }
	         else  if (emailLine != null && (emailLine.contains("Error Code:") || emailLine.contains("Error Code :"))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 errorCode = emailLine.substring(beginIndex + 1);//,beginIndex + 6);
	        	 if (!errorCode.isEmpty() )
	        		 errorCode = errorCode.trim();
	        	     errorCode = errorCode.replaceAll("\\s+","");
	         }	         
	         else  if (emailLine != null && (emailLine.contains("Occurred:") || emailLine.contains("Occurred :"))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 occurred = emailLine.substring(beginIndex + 1);
	        	 if (!occurred.isEmpty() ){
	        		 occurred = occurred.trim();
	        		 try{
	        			    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aaa");
	        			    Date parsedDate = dateFormat.parse(occurred);
	        			    occurredDate = new java.sql.Timestamp(parsedDate.getTime());
	        			}catch(Exception e){
	        			 e.printStackTrace();
	        			}
	        	 }	        		 
	         }
	         else  if (emailLine != null && ( emailLine.contains("Description:") || emailLine.contains("Details:"))){
	        	 int beginIndex = emailLine.indexOf(':');
	        	 errorDesc = emailLine.substring(beginIndex + 1);
	        	 if (!errorDesc.isEmpty() ){
	        		 errorDesc = errorDesc.trim(); // substr($0,0,238) 
	        	 } 
	        	 isDetails = true;
	         } else if (emailLine != null && isDetails == true) {
	        	 errorDesc  = errorDesc + emailLine;
	         }
	      }		 
		 System.out.println(">> deviceId  : "+ deviceId);
		 //System.out.println(">> errorCode  : "+ errorCode);
		 //System.out.println(">> occurred  : "+ occurredDate);
		 //System.out.println(">> errorDesc  : "+ errorDesc);	 
		 CanonE307UGWErrorsDao dao = new CanonE307UGWErrorsDao();
		 boolean isInserted =  dao.insertData(deviceId, errorCode, occurredDate, errorDesc);		
		return isInserted;
	}

	
	private String messageID(Message message) throws MessagingException {
		return ((MimeMessage) message).getMessageID();
	}

		
	
	/**
	 * Return the primary text content of the message.
	 */
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