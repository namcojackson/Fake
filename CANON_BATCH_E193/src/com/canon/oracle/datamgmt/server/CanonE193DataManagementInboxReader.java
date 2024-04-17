package com.canon.oracle.datamgmt.server;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import org.apache.commons.mail.EmailException;

import canon.apps.common.CanonCustomProfile;
import canon.apps.common.CanonEmailUtil;


/**
 * 
 * Profile examples:
 * 
 * CANON_E193_DM_INBOX_READ_SCHEDULE=
 * 		STOPPED: do not run program
 * 		SCHEDULED: scheduled with autosys.
 * 		Hourly from 8AM to 8PM America/New_York: specify start and end time
 *     
 * CANON_E193_DM_INBOX_ARCHIVE_SCHEDULE
 *   
 * CANON_E193_DM_INBOX_READ_NUM_DAYS=Last n days
 * 		other format will read all.
 * CANON_E193_DM_ARCHIVE_OLD_EMAIL=Yes, move to archive %1$tY folder	
 * 		%1$tY is message receive year, see http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#detail date/time conversion
 * 		archive folder must be exist.
 * CANON_E193_DM_ARCHIVE_OLD_EMAIL=No
 * 		do not archive old messages	
 *
 */

public class CanonE193DataManagementInboxReader {
	
	//(?:(.*?)\s+?from\s+?(\d{1,2}):?(\d\d)?\s*?([A|P]M)\s+?to\s+?(\d{1,2}):?(\d\d)?\s*?([A|P]M)\s+?(America\/New_York))|STOPPED|SCHEDULED
	final static Pattern SCHEDULE_PATTERN= Pattern.compile("(?:(.*?)\\s+?from\\s+?(\\d{1,2}):?(\\d\\d)?\\s*?([A|P]M)\\s+?to\\s+?(\\d{1,2}):?(\\d\\d)?\\s*?([A|P]M)\\s+?(America\\/New_York))|STOPPED|SCHEDULED");
    final static Pattern PATTERN_ARCHIVE= Pattern.compile("Yes, move to (.*?) folder",Pattern.CASE_INSENSITIVE);
    final static Pattern PATTERN_LAST_NUM_DAY = Pattern.compile("last (.*?) days?",Pattern.CASE_INSENSITIVE);
	
    final static Schedule SCHEDULE_STOPPED=new Schedule(){@Override public boolean shouldRun() {return false;}};
    final static Schedule SCHEDULE_SCHEDULED=new Schedule(){@Override public boolean shouldRun() {return true;}};
    
	private boolean textIsHtml = false;
	private int count=0;

	public static boolean compare(String str1, String str2) {
	    return (str1 == null ? str2 == null : str1.equals(str2));
	}
	
	public static boolean compare(java.sql.Timestamp t, Date d) {
	    return (t == null ? t == null : t.getTime()== d.getTime());
	}
	
	public boolean processed(String messageID, String sender, String subject, Date receivedDate) {
		
		Object objs[]=CanonE193DataManagementDAO.getMessageDetails(messageID);
		@SuppressWarnings("unchecked")
		List<CanonE193DataManagementDAO.MessageInfo> list=(List<CanonE193DataManagementDAO.MessageInfo>)objs[0];
		if(list!=null && list.size()>0){
			for(CanonE193DataManagementDAO.MessageInfo m:list){
				if(compare(m.getFromEmail(), sender) && 
						compare(m.getSubject(),subject) &&
						compare(m.getReceivedDate(), receivedDate))
				{
					return true;
				}
						
			}
		}
		
		return false;
		
	}

	private String contentType(String str){
		if(str==null) return str;
		int idx=str.indexOf(';');
    	return idx>0? str.substring(0,idx) : str;
	}
	
	public void processEmails() {
		
		Schedule readSchedule=schedule(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_INBOX_READ_SCHEDULE"));
		BigDecimal readNumDays=getLastNumDay(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_INBOX_READ_NUM_DAYS"));
		ImapInfo emailAccount=imapInfo(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_EMAIL_ACCOUNT"));
		System.out.println("  readSchedule "+readSchedule);
		System.out.println("  readNumDays "+readNumDays);
		System.out.println("  emailAccount "+emailAccount);
		
		if(readSchedule==null || !readSchedule.shouldRun()){
			System.out.println(" program skipped.");
			return;
		}
		
		InboxReader reader=new InboxReader(){
			public void process(int index,String messageID, String sender, String subject, Date receivedDate, Message message) throws Exception {
				System.out.println("#" + index+"|"+sender+"|"+ subject +"|" +receivedDate+ "|"+messageID);
				if(!processed(messageID,sender,subject,receivedDate)){
					String emailBody =getText(message,false);
					Object objs[]=CanonE193DataManagementDAO.insertDmEmailStg(sender, subject, emailBody, messageID, new java.sql.Timestamp(receivedDate.getTime()));
					count++;
					if(objs==null || objs.length!=2){
						throw new Error("Invalid objs "+objs);
					}
					String status=(String)second(objs);
					if(!status.equals("S")){
						throw new Error("Error status "+status);
					}
					BigDecimal newId=(BigDecimal)first(objs);
					if(message.getContent() instanceof Multipart){
						Multipart multipart = (Multipart) message.getContent();
	
					    for (int i = 0; i < multipart.getCount(); i++) {
					    	Part bodyPart = multipart.getBodyPart(i);
					        if(!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) || isBlank(bodyPart.getFileName())) {
					            continue; // dealing with attachments only
					        } 
					        InputStream is = bodyPart.getInputStream();
					        String fileName=bodyPart.getFileName();
					        Object result[]=CanonE193DataManagementDAO.insertE193DmAttchment(newId, contentType(bodyPart.getContentType()), fileName, is);
							if(result==null || result.length!=1){
								throw new Error("Invalid objs "+result);
							}
							status=(String)first(result);
							if(!status.equals("S")){
								throw new Error("Error status "+status);
							}
					    }
					}else{
						System.out.println("message content type "+message.getContentType());
					}
					
					System.out.println("new, inserted.");
				}else{
					System.out.println("existing, ignored.");
				}
				
			}
			
		};
		
		count=0;
		process(reader,readNumDays,emailAccount);
		System.out.println(count==0?"No new email found.":"Processed "+count+" email(s).");
		
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
    
    private static BigDecimal getLastNumDay(String str){
    	Matcher m = PATTERN_LAST_NUM_DAY.matcher(str);
    	if(m.matches()){
    		return toBigDecimal(m.group(1),false);
    	}else{
    		return null;
    	}
    }
	
	private static boolean isOld(Date receivedDate,BigDecimal readNumDays){
		if(readNumDays!=null){
			long diffDays = (System.currentTimeMillis()-receivedDate.getTime()) / (24*3600*1000);
			if(diffDays>readNumDays.intValue()){
				return true;
			}
		}
		return false;
	}
	
	private void moveMessageToFolder(Message message, Folder archFolder){
		System.out.println("move old message to folder:" +archFolder.getFullName());
		Folder folder=message.getFolder();
		try {
			folder.copyMessages(new Message[]{message}, archFolder);
			message.setFlag(Flags.Flag.DELETED, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Error(e);
		}
	}

	private void error(Throwable t){
		sendEmail("Data Management Inbox Reader Error", t.getMessage());
	}
	
	private void sendEmail(String subject, String body){
		String adminEmail=CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_ADMIN_EMAIL");
		if(adminEmail!=null){
			String subjectPrefix=nullToEmpty(CanonCustomProfile.getSystemProfileValue("CANON_CUSTOM_EMAIL_SUBJECT_INIT"));
			try {
				CanonEmailUtil.createSimpleEmail().setSubject(subjectPrefix+ subject)
				 	.setMsg(body)
				 	.addTo(adminEmail)
				 	.send();
			} catch (EmailException e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void process(InboxReader handler,BigDecimal readNumDays,ImapInfo emailAccount) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imap");
		if(emailAccount.port!=null){
			props.setProperty("mail.imap.port", emailAccount.port.toString());
		}
		
		Folder inbox=null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			if("Y".equals(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_EMAIL_DEBUG"))){
				session.setDebug(true);
			}
			Store store = session.getStore("imap");
			store.connect(emailAccount.mailServer, emailAccount.userName, emailAccount.password);

			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);

			SearchTerm sender = new FromTerm(new InternetAddress("datamanagement@cusa.canon.com"));
			System.out.println("before search");
			Message[] messages = inbox.search(sender);			
			System.out.println("messages length " + messages.length);

			int i = 0;
			for (Message message : messages) {
				if(!isOld(message.getReceivedDate(),readNumDays)) {
					handler.process(i++, 
						messageID(message), 
						sender(message),  
						message.getSubject(), 
						message.getReceivedDate(), 
						message);
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			error(e);
		}finally{
			if(inbox!=null){
				try {
					inbox.close(false);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void archiveEmails() {
		
		Schedule archiveSchedule=schedule(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_INBOX_ARCHIVE_SCHEDULE"));
		BigDecimal readNumDays=getLastNumDay(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_INBOX_READ_NUM_DAYS"));
		ImapInfo emailAccount=imapInfo(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_EMAIL_ACCOUNT"));
		Archive archive=archive(CanonCustomProfile.getSystemProfileValue("CANON_E193_DM_ARCHIVE_OLD_EMAIL"));
		System.out.println("  archiveSchedule "+archiveSchedule);
		System.out.println("  readNumDays "+readNumDays);
		System.out.println("  emailAccount "+emailAccount);
		System.out.println("  archive "+archive);
		
		if(archiveSchedule==null || !archiveSchedule.shouldRun() || !archive.archive){
			System.out.println("archive program skipped.");
			return;
		}
		count=0;
		
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imap");
		Folder inbox=null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imap");
			store.connect(emailAccount.mailServer, emailAccount.userName, emailAccount.password);

			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);

			Message messages[] = inbox.getMessages();
			System.out.println("messages length " + messages.length);

			for (Message message : messages) {
				if(isOld(message.getReceivedDate(),readNumDays)) {
					System.out.println("old message " +message.getSubject());
					// only archive processed messages
					if(processed(messageID(message),sender(message),message.getSubject(),message.getReceivedDate())){
						String archFolderName=String.format(archive.folderTemplate, message.getReceivedDate());
						Folder archFolder=store.getFolder(archFolderName);
						if(archFolder.exists()){
							moveMessageToFolder(message, archFolder);
							count++;
						}else{
							throw new Error("Archive folder "+archFolderName+" not found.");
						}
					}else{
						System.out.println("messages procesed, skip archiving.");
					}
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			error(e);
		}finally{
			if(inbox!=null){
				try {
					inbox.close(archive.archive);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(count==0?"No new email found.":"archived "+count+" email(s).");
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
	private String messageID(Message message) throws MessagingException {
		String msgId= ((MimeMessage) message).getMessageID();
		return cleanup(msgId);
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
	
	interface InboxReader{
		void process(int index, String messageID, String sender, String subject,Date receivedDate,Message message )  throws Exception;
	}

    static Object nth(Object obj,int n) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < n ? null : objs[n-1];
        }
        return null;
    }

    static Object first(Object obj) {
    	return nth(obj,1);
    }

    static Object second(Object obj) {
    	return nth(obj,2);
    }

    static Object third(Object obj) {
    	return nth(obj,3);
    }
    
    private static String nullToEmpty(String str) {
    	return str==null? "":str;
    }
	
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    
    private Schedule schedule(String sche){
    	if(sche!=null){ 
	    	Matcher m = SCHEDULE_PATTERN.matcher(sche);
	    	if(m.matches()){
	    		if("STOPPED".equals(m.group().toUpperCase())){
	    			return SCHEDULE_STOPPED;
	    		}
	    		if("SCHEDULED".equals(m.group().toUpperCase())){
	    			return SCHEDULE_SCHEDULED;
	    		}
	    		Frequency frq;
				try {
					frq = toFrequency(m.group(1));
        		int fromHour=Integer.parseInt(m.group(2));
        		int formMinute=isBlank(m.group(3))? 0: Integer.parseInt(m.group(3));
        		AMPM fromAMPM=toAMPM(m.group(4));
        		int toHour=Integer.parseInt(m.group(5));
        		int toMinute=isBlank(m.group(6))? 0: Integer.parseInt(m.group(6));
        		AMPM toAMPM=toAMPM(m.group(7));
            	TimeZone timezone = TimeZone.getTimeZone(m.group(8));
            	return new ScheduleImpl(frq,fromHour,formMinute,fromAMPM,toHour,toMinute,toAMPM,timezone);
				} catch (Exception e) {
					//TODO notification 
					e.printStackTrace();
			    	return null;
				}
	    	}else{
	    		//TODO notification 
	    		System.out.println("Invalid schedule value "+sche);
	    	}
    	}
    	return null;
    }

    enum Frequency{
    	HOURLY,
    	EVERY_15_MINUTES 
    }
    enum AMPM{
        AM,
        PM
    }

	private Frequency toFrequency(String str)throws Exception{
		if(str!=null){
			if("HOURLY".equals(str.toUpperCase())){
				return Frequency.HOURLY;
			}else if("EVERY 15 MINUTES".equals(str.toUpperCase())){
				return Frequency.EVERY_15_MINUTES;
			}
		}
		throw new Exception("Invalid Frequency "+str);
	}
    
	private AMPM toAMPM(String str)throws Exception{
		if(str!=null){
			if("AM".equals(str.toUpperCase())){
				return AMPM.AM;
			}else if("PM".equals(str.toUpperCase())){
				return AMPM.PM;
			}
		}
		throw new Exception("Invalid AM PM "+str);
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

    
    private ImapInfo imapInfo(String acct){
    	if(acct==null){
    		throw new Error("Email account not found.");
    	}
    	String [] strs=acct.split("\\s+");
    	if(strs.length!=3){
    		throw new Error("Invalid email account "+acct);
    	}
    	String server=null;
    	BigDecimal port=null;
    	int idx=strs[0].indexOf(':');
    	if(idx==-1){
    		server=strs[0];
    	}else{
    		server=strs[0].substring(0,idx);
    		port=toBigDecimal(strs[0].substring(idx+1),false);
    	}
    			
    	return new ImapInfo(server,port,strs[1],strs[2]);
    	
    }
    
    class ImapInfo {
    	private String mailServer;
    	private String userName;
    	private String password;
    	BigDecimal port;
		public ImapInfo(String mailServer,BigDecimal port, String userName, String password) {
			super();
			this.mailServer = mailServer;
			this.port=port;
			this.userName = userName;
			this.password = password;
		}
		@Override
		public String toString() {
			return "ImapInfo [mailServer=" + mailServer + ", userName="
					+ userName + ", password=" + password + ", port=" + port
					+ "]";
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

    interface Schedule{
		boolean shouldRun();
    }

    /**
     * By convention, 12 AM denotes midnight and 12 PM denotes noon.
     * HOUR_OF_DAY is 0 for midnight.
     */
    class ScheduleImpl implements Schedule{
    	private Frequency frequency;
    	private int formHour;
    	private int formMinute;
    	private AMPM fromAMPM; 
    	private int toHour;
    	private int toMinute;
    	private AMPM toAMPM; 
    	private int formHourOfDay;
    	private int toHourOfDay;
    	private TimeZone timezone;		/*  America/New_York  */
		public ScheduleImpl(Frequency frequency, int formHour,int formMinute,AMPM fromAMPM, int toHour,int toMinute,AMPM toAMPM, TimeZone timezone) {
			this.frequency = frequency;
			this.formHour = formHour;
			this.formMinute = formMinute;
			this.fromAMPM=fromAMPM;
			this.toHour = toHour;
			this.toMinute = toMinute;
			this.toAMPM=toAMPM;
			this.timezone = timezone;
			this.formHourOfDay=fromAMPM==AMPM.AM? (formHour==12? 0 :formHour) : (formHour==12? 12 :formHour+12) ;
			this.toHourOfDay=toAMPM==AMPM.AM? (toHour==12? 24 :toHour) :  (toHour==12? 12 :toHour+12);
		}

		public boolean shouldRun(Calendar cal){
			int currentHourofDay=cal.get(Calendar.HOUR_OF_DAY);
			int currentMinute=cal.get(Calendar.MINUTE);
			if((formHourOfDay*60+formMinute)<=(toHourOfDay*60+toMinute)){
				return (currentHourofDay*60+currentMinute)>=(formHourOfDay*60+formMinute) && (currentHourofDay*60+currentMinute)<(toHourOfDay*60+toMinute);
			}else{
				return ((currentHourofDay*60+currentMinute)>=(formHourOfDay*60+formMinute) && (currentHourofDay*60+currentMinute)<(24*60)) ||
						((currentHourofDay*60+currentMinute)>=0 && (currentHourofDay*60+currentMinute)<(toHourOfDay*60+toMinute)) ;
			}
		}
		
		public boolean shouldRun(){
			Calendar cal=Calendar.getInstance(timezone);
			return shouldRun(cal);
		}

		@Override
		public String toString() {
			return "Schedule [frequency=" + frequency + ", formHour="
					+ formHour + ", fromAMPM=" + fromAMPM + ", toHour="
					+ toHour + ", toAMPM=" + toAMPM + ", formHourOfDay="
					+ formHourOfDay + ", toHourOfDay=" + toHourOfDay
					+ ", timezone=" + timezone + "]";
		}

		
    }
    

// Test start here, uncomment to test
/*    
    static void assertTrue(boolean b, String msg){
    	if(!b) throw new Error(msg+" assert failed ");
    }

    public static void testRunAtMidnight(){
    	// midnight hour today    
    	TimeZone tz=TimeZone.getTimeZone("America/New_York");
    	Calendar date = Calendar.getInstance(tz);
    	DateFormat format = DateFormat.getDateTimeInstance();
    	format.setTimeZone(tz);
    	// reset hour, minutes, seconds and millis
    	date.set(Calendar.HOUR_OF_DAY, 0);
    	date.set(Calendar.MINUTE, 0);
    	date.set(Calendar.SECOND, 0);
    	date.set(Calendar.MILLISECOND, 0);
    	CanonE193DataManagementInboxReader obj=new CanonE193DataManagementInboxReader();
    	System.out.println(format.format(date.getTime()));
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 12AM to 10AM America/New_York")).shouldRun(date), " from midnight to am");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 12AM to 1PM America/New_York")).shouldRun(date), " from midnight to pm");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 1AM to 12AM America/New_York")).shouldRun(date), " from am to midnight");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1PM to 12PM America/New_York")).shouldRun(date), " from pm to midnight");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 5AM to 11AM America/New_York")).shouldRun(date), " from am to am");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 1AM to 1PM America/New_York")).shouldRun(date), " from am to pm");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1PM to 1AM America/New_York")).shouldRun(date), " from pm to am");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 5PM to 11PM America/New_York")).shouldRun(date), " from pm to pm");
    	System.out.println("testMidnight success!");
    }

    public static void testRunAtAM(){
    	// midnight hour today    
    	TimeZone tz=TimeZone.getTimeZone("America/New_York");
    	Calendar date = Calendar.getInstance(tz);
    	DateFormat format = DateFormat.getDateTimeInstance();
    	format.setTimeZone(tz);
    	// reset hour, minutes, seconds and millis
    	date.set(Calendar.HOUR_OF_DAY, 9);
    	date.set(Calendar.MINUTE, 0);
    	date.set(Calendar.SECOND, 0);
    	date.set(Calendar.MILLISECOND, 0);
    	CanonE193DataManagementInboxReader obj=new CanonE193DataManagementInboxReader();
    	System.out.println(format.format(date.getTime()));
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 12AM to 10AM America/New_York")).shouldRun(date), " from midnight to am");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 12AM to 1PM America/New_York")).shouldRun(date), " from midnight to pm");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1AM to 12AM America/New_York")).shouldRun(date), " from am to midnight");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1PM to 12PM America/New_York")).shouldRun(date), " from pm to midnight");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 5AM to 11AM America/New_York")).shouldRun(date), " from am to am");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1AM to 1PM America/New_York")).shouldRun(date), " from am to pm");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 1PM to 1AM America/New_York")).shouldRun(date), " from pm to am");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 5PM to 11PM America/New_York")).shouldRun(date), " from pm to pm");
    	System.out.println("testMidnight success!");
    }

    public static void testRunAtPM(){
    	// midnight hour today    
    	TimeZone tz=TimeZone.getTimeZone("America/New_York");
    	Calendar date = Calendar.getInstance(tz);
    	DateFormat format = DateFormat.getDateTimeInstance();
    	format.setTimeZone(tz);
    	// reset hour, minutes, seconds and millis
    	date.set(Calendar.HOUR_OF_DAY, 15);
    	date.set(Calendar.MINUTE, 30);
    	date.set(Calendar.SECOND, 0);
    	date.set(Calendar.MILLISECOND, 0);
    	CanonE193DataManagementInboxReader obj=new CanonE193DataManagementInboxReader();
    	System.out.println(format.format(date.getTime()));
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 12AM to 10AM America/New_York")).shouldRun(date), " from midnight to am");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 12AM to 1PM America/New_York")).shouldRun(date), " from midnight to pm");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1AM to 12AM America/New_York")).shouldRun(date), " from am to midnight");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1PM to 12PM America/New_York")).shouldRun(date), " from pm to midnight");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 5AM to 11AM America/New_York")).shouldRun(date), " from am to am");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 1AM to 1PM America/New_York")).shouldRun(date), " from am to pm");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 1PM to 1AM America/New_York")).shouldRun(date), " from pm to am");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 5PM to 11PM America/New_York")).shouldRun(date), " from pm to pm");
    	assertTrue(((ScheduleImpl)obj.schedule("Hourly from 3:29PM to 11PM America/New_York")).shouldRun(date), " from pm to pm");
    	assertTrue(!((ScheduleImpl)obj.schedule("Hourly from 3:31PM to 11PM America/New_York")).shouldRun(date), " from pm to pm");
    	System.out.println("testMidnight success!");
    }
    
    public static void testSTOPPED(){
    	CanonE193DataManagementInboxReader obj=new CanonE193DataManagementInboxReader();
    	assertTrue(!obj.schedule("STOPPED").shouldRun(), " stopped");
    	System.out.println("testSTOPPED success!");
    }

    public static void testSHEDULED(){
    	CanonE193DataManagementInboxReader obj=new CanonE193DataManagementInboxReader();
    	assertTrue(obj.schedule("SCHEDULED").shouldRun(), " stopped");
    	System.out.println("testSHEDULED success!");
    }

    
    public static void main(String [] args){
    	testRunAtMidnight();
    	testRunAtAM();
    	testRunAtPM();
    	testSTOPPED();
    	testSHEDULED();
    }
*/

// Test end here 
    
//    public static void main(String[] args){
//    	
//    	final String regex = "(?:(.*?)\\s+?from\\s+?(\\d{1,2}):?(\\d\\d)?\\s*?([A|P]M)\\s+?to\\s+?(\\d{1,2}):?(\\d\\d)?\\s*?([A|P]M)\\s+?(America\\/New_York))|STOPPED|SCHEDULED";
//    	final String string = "Every 15 Minutes from 8AM to 8PM America/New_York\n"
//    		 + "Hourly from 8AM to 8PM America/New_York\n"
//    		 + "Every 15 Minutes from 5PM to 8PM America/New_York\n"
//    		 + "Every 15 Minutes from 8:30AM to 8:00PM America/New_York\n"
//    		 + "STOPPED\n"
//    		 + "SCHEDULED";
//
//    	final Pattern pattern = Pattern.compile(regex);
//    	final Matcher matcher = pattern.matcher(string);
//
//    	while (matcher.find()) {
//    	    System.out.println("Full match: " + matcher.group());
//    	    for (int i = 1; i <= matcher.groupCount(); i++) {
//    	        System.out.println("Group " + i + ": " + matcher.group(i));
//    	    }
//    	}
//    	
//    }

//    
//	public static Email makeTest(Email email){
//		email.setHostName("varhpr198.cusa.canon.com");  //mail01.cusa.canon.com
//		email.setStartTLSRequired(true);
//		email.setDebug(true);
//
//		//email.setAuthenticator(new DefaultAuthenticator("s21csa-appl-test@cusa.canon.com", "Canon1234"));
////		email.setAuthenticator(new DefaultAuthenticator("s21csa-appl-test@cusa.canon.com", "Canon1234"));
//		
//		try {
////			email.setFrom("s21admin@cusa.canon.com", "S21 CSA");
////			email.setFrom("noreply@csa.canon.com", "Canon Solutions America");
////			email.setFrom("s21csa-appl-test@cusa.canon.com", "S21 CSA");
//			email.setFrom("S21_Operation@cusa.canon.com", "S21 CSA");
//
//		} catch (EmailException e) {
//			e.printStackTrace();
//		}
//		return email;
//	}
  
//  public static void main(String []args) throws EmailException{
//	System.out.println(new CanonE193DataManagementInboxReader().imapInfo("localhost:8134 uid pwd"));  
//  }
//  
//  public static void main1(String []args) throws EmailException{
//	  makeTest(new SimpleEmail())
//				.setSubject("Test CSA IMAP")
//			 	.setMsg("Hi ")
//			 	.addTo("s21csa-appl-test@cusa.canon.com")
//			 	.addTo("s21admin@cusa.canon.com")
//			 	.addTo("jwang_consultant@cusa.canon.com")
//			 	.send();
//  }
//  
//  public static void main2(String[] args){
//
//		Properties props = System.getProperties();
//		props.setProperty("mail.store.protocol", "imap");
//		props.put("mail.imap.starttls.enable","true");
//		props.put("mail.imap.auth", "true");
//		
//		Session session = Session.getDefaultInstance(props, null);
//        session.setDebug(true);
//		Store store;
//		try {
//			store = session.getStore("imap");
//
//			//store.connect("LNNYMASSM01", "GM02533", "#GM02533");
//			//store.connect("varhpr198", "s21admin@cusa.canon.com", "Cusa2015");
//			store.connect("varhpr198", "s21csa-appl-test@cusa.canon.com", "Canon1234");
//			
//			Folder inbox=store.getFolder("Inbox");
//			inbox.open(Folder.READ_ONLY);
//			
//			Message messages[] = inbox.getMessages();
//			System.out.println("messages length " + messages.length);
//
//			int i = 0;
//			for (Message message : messages) {
//				System.out.println(message.getSubject());
//				if(i++>100)break;
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//  }
    
}
