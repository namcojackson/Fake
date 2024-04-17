package com.canon.cusa.s21.mail.imap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;

import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.sun.mail.imap.IMAPFolder;

// import com.sun.mail.imap.OlderTerm;

public class S21ImapClient {

    private static final S21Logger logger = S21LoggerFactory.getLogger(S21ImapClient.class);

    private static final int MAX_BODY_SIZE = 100000;

    /** IMAP host name */
    private String hostname;

    /** IMAP port number */
    private int port;

    /** IMAP user name */
    private String username;

    /** IMAP user password */
    private String password;

    /** IMAP fetch limit */
    private int fetchLimit;

    /** IMAP session object */
    private Session session;

    /** IMAP connection object */
    private Store store;

    /** IMAP folder object */
    private IMAPFolder folder;

// QC#50213 ADD START 05/10/2019
    /** protocol */
    private String protocol;

    /** host info separator*/
    private static String SEPARATOR = "/";
// QC#50213 ADD END   05/10/2019

// QC#51376 ADD START 07/11/2019
    private static final String REG_BODY = "(?is)<body\\s*[^>]*>.*</body>";

    private static Pattern ptnBody = Pattern.compile(REG_BODY, Pattern.DOTALL | Pattern.CASE_INSENSITIVE );
// QC#51376 ADD END 07/11/2019

    public S21ImapClient(String hostname, int port, String username, String password, int fetchLimit) {
// QC#50213 MOD START 05/10/2019
//        this.hostname = hostname;
        String[] hostinfo = hostname.split(SEPARATOR, 2);
        if (hostinfo.length == 2) {
            if (!hostinfo[1].isEmpty()) {
                this.hostname = hostinfo[0];
                this.protocol = hostinfo[1].toLowerCase();
            } else {
                this.hostname = hostinfo[0];
                this.protocol = "imap";
            }
        } else {
            this.hostname = hostinfo[0];
            this.protocol = "imap";
        }
// QC#50213 MOD END   05/10/2019
        this.port = port;
        this.username = username;
        this.password = password;
        this.fetchLimit = fetchLimit;
    }

    public void connect() throws MessagingException {
        Properties props = new Properties();
// QC#50213 MOD START 05/10/2019
//        String protocol = "imap";
//
//        props.setProperty("mail.imap.socketFactory.fallback", "false");
//        props.setProperty("mail.imap.starttls.enable", "true");
//        props.setProperty("mail.imap.ssl.socketFactory.class", "com.canon.cusa.s21.mail.imap.S21NwfSSLSocketFactory");
//        props.setProperty("mail.imap.ssl.socketFactory.fallback", "false");
//        props.put("mail.imap.ssl.checkserveridentity", "false");
//        props.put("mail.imap.ssl.trust", "*");
        props.setProperty("mail." + protocol + ".socketFactory.fallback", "false");
        props.setProperty("mail." + protocol + ".starttls.enable", "true");
        props.setProperty("mail." + protocol + ".ssl.socketFactory.class", "com.canon.cusa.s21.mail.imap.S21NwfSSLSocketFactory");
        props.setProperty("mail." + protocol + ".ssl.socketFactory.fallback", "false");
        props.put("mail." + protocol + ".ssl.checkserveridentity", "false");
        props.put("mail." + protocol + ".ssl.trust", "*");
// QC#50213 MOD END   05/10/2019

        if (logger.isInfoEnabled()) {
            logger.info("Start Connect IMAP Server:");
            logger.info("host:" + hostname);
            logger.info("port:" + String.valueOf(port));
            logger.info("username:" + username);
// QC#50213 ADD START 05/10/2019
            logger.info("protocol:" + protocol);
// QC#50213 ADD END   05/10/2019
        }

        session = Session.getDefaultInstance(props, null);
        store = session.getStore(protocol);
        store.connect(hostname, port, username, password);

        if (logger.isInfoEnabled()) {
            logger.info("Connected IMAP Server:");
        }

        folder = (IMAPFolder) store.getDefaultFolder();
        folder = (IMAPFolder) folder.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
    }

    public void close() {
        if (folder != null) {
            try {
                folder.close(true);
            } catch (MessagingException e) {
                logger.error("folder close error.", e);
            }
        }
        if (store != null) {
            try {
                store.close();
            } catch (MessagingException e) {
                logger.error("store close error.", e);
            }
        }
    }

    public List<Long> getUidList() throws MessagingException {
        List<Long> ret = new ArrayList<Long>();

        Message[] flagedMessage = folder.search(new FlagTerm(new Flags(Flags.Flag.FLAGGED), false));

        if (logger.isInfoEnabled()) {
            logger.info("Search finish:" + String.valueOf(flagedMessage.length));
        }

        int uidListSize = 0;
        if ((flagedMessage.length > fetchLimit) && (fetchLimit > 0)) {
            uidListSize = fetchLimit;
        } else {
            uidListSize = flagedMessage.length;
        }
        
        for (int i = 0; i < uidListSize; i++) {
            ret.add(folder.getUID(flagedMessage[i]));
        }
        return ret;
    }
    
    /*
    public List<Long> getUidList() throws MessagingException {
        List<Long> ret = new ArrayList<Long>();
        long[] uidList;
        Message[] flagedMessage = folder.search(new FlagTerm(new Flags(Flags.Flag.FLAGGED), false));

        if (logger.isInfoEnabled()) {
            logger.info("Search finish:" + String.valueOf(flagedMessage.length));
        }

        int uidListSize = 0;
        if ((flagedMessage.length > fetchLimit) && (fetchLimit > 0)) {
            uidListSize = fetchLimit;
        } else {
            uidListSize = flagedMessage.length;
        }
        uidList = new long[uidListSize];
        for (int i = 0; i < uidListSize; i++) {
            uidList[i] = folder.getUID(flagedMessage[i]);
        }
        return uidList;
    }
     */

    public void setImapFlag(long uid) throws MessagingException {
        Message imapMsg = folder.getMessageByUID(uid);
        imapMsg.setFlag(Flags.Flag.FLAGGED, true);
    }

    public S21ImapMessage getMessages(long uid) throws MessagingException, IOException {
        S21ImapMessage msg = new S21ImapMessage();
        Message imapMsg = folder.getMessageByUID(uid);

        if (imapMsg.getSize() > MAX_BODY_SIZE) {
            msg.setValidMessageFlag(false);
            return msg;
        }

// QC#50213 MOD START 05/14/2019
//        String msgBody = getText(folder.getMessageByUID(uid).getContent());
      String msgBody = getText(folder.getMessageByUID(uid).getContent(), imapMsg.getContentType());
// QC#50213 MOD END   05/14/2019

          // QC#19549 [IT Nightly JOB Abend]S21SWD_SZWJ0050010 Mail Clean Batch
//        if (msgBody.length() < 1) {
//            msg.setValidMessageFlag(false);
//            return msg;
//        }

        // set from header value
        msg.setValidMessageFlag(true);
        msg.setFrom(getAddressPart(imapMsg.getFrom()));
        msg.setDate(imapMsg.getSentDate());
        msg.setSubject(imapMsg.getSubject());
        msg.setBody(msgBody);

        return msg;
    }

    public void setDeleteFlag(long uid) throws MessagingException {
        Message imapMsg = folder.getMessageByUID(uid);
        imapMsg.setFlag(Flags.Flag.DELETED, true);
    }

    public boolean isConnected() {
        boolean ret = false;

        if (store != null) {
            ret = store.isConnected();
        }

        return ret;

    }

    private boolean isRemoveTarget(Message m, int hours) throws MessagingException {
        boolean ret = false;

        Calendar now = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(m.getReceivedDate());
        cal.add(Calendar.HOUR, hours);

        if (now.compareTo(cal) >= 0) {
            ret = true;
        }

        return ret;
    }

    public List<Long> getOldMessage(int hours) throws MessagingException {
        // Message[] oldMessage = folder.search(new AndTerm(new
        // FlagTerm(new Flags(Flags.Flag.FLAGGED), true), new
        // OlderTerm(time, 246060)));
        Message[] oldMessage = folder.search(new FlagTerm(new Flags(Flags.Flag.FLAGGED), true));

        List<Long> uidList = new ArrayList<Long>();
        
        
        for (int i = 0; i < oldMessage.length; i++) {
            if (isRemoveTarget(oldMessage[i], hours)) {
                uidList.add(folder.getUID(oldMessage[i]));
            }
        }
        
        return uidList;
    }

//    private static String getText(Object content) throws MessagingException, IOException {
//        // QC#19549 [IT Nightly JOB Abend]S21SWD_SZWJ0050010 Mail Clean Batch
//        if (content == null) {
//            return "";
//        }
//
//        StringBuffer sb = new StringBuffer();
//
//        if (content instanceof String) {
//            sb.append((String) content);
//        } else if (content instanceof Multipart) {
//            Multipart mp = (Multipart) content;
//            for (int i = 0; i < mp.getCount(); i++) {
//                BodyPart bp = mp.getBodyPart(i);
//                if (bp.getContentType().split(";")[0].equalsIgnoreCase("text/plain")) {
//                    sb.append(getText(bp.getContent()));
//                    break;
//                }
//            }
//        }
//        return sb.toString();
//    }

// QC#50213 MOD START 05/14/2019
//    private static String getText(Object content) throws MessagingException, IOException {
  private static String getText(Object content, String contentType) throws MessagingException, IOException {
// QC#50213 MOD END   05/14/2019
        if (content == null) {
            return "";
        }

        String retText = "";

        if (content instanceof String) {
            if (logger.isInfoEnabled()) {
                logger.info("getText content = [String]");
// QC#50213 MOD START 05/14/2019
                logger.info("getText contentType = " + (contentType == null ? "null" : contentType));
// QC#50213 MOD END   05/14/2019
            }
            retText = (String) content;
// QC#50213 MOD START 05/14/2019
            if (contentType != null && contentType.contains("text/html")) {
// QC#51723 MOD START 07/18/2019
//                retText = retText.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "\r\n");
                retText = retText.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*(\r\n)*", "\r\n");
// QC#51723 MOD END   07/18/2019
            }
// QC#50213 MOD END   05/14/2019
        } else if (content instanceof Multipart) {
            if (logger.isInfoEnabled()) {
                logger.info("getText content = [Multipart]");
            }
            retText = getTextMultipart((Multipart)content);
        }
        return retText;
    }

    private static String getTextMultipart(Multipart mp) throws MessagingException, IOException {
// QC#51723 MOD START 07/18/2019
//        StringBuffer sb = new StringBuffer();
        String bdy = "";
// QC#51723 MOD END   07/18/2019
        int partCount = mp.getCount();

        if (logger.isInfoEnabled()) {
            logger.info("mp.count" + partCount);
            logger.info("mp.contentType = " + mp.getContentType());
        }

        for (int i = 0; i < partCount; i++) {
            BodyPart bp = mp.getBodyPart(i);

            if (logger.isInfoEnabled()) {
                logger.info("[" + i + "] bp.contentType = " + bp.getContentType());
            }

            if (bp.isMimeType("text/plain")) {
// QC#51723 MOD START 07/18/2019
//                sb.append((String)bp.getContent());
//                break;
                bdy = (String)bp.getContent();
// QC#51723 MOD END   07/18/2019
//            } else if (bp.isMimeType("text/html")) {
//                sb.append((String)bp.getContent());
// QC#51376 ADD START 07/11/2019
            } else if (bp.isMimeType("text/html")) {
                Matcher m = ptnBody.matcher((String)bp.getContent());
                String bodyStr = "";
                if (m.find()) {
                    bodyStr = m.group();
// QC#51723 MOD START 07/18/2019
//                    sb.append(bodyStr.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "\r\n"));
                    bdy = bodyStr.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*(\r\n)*", "\r\n");
// QC#51723 MOD END   07/18/2019
                }
                break;
// QC#51376 ADD END   07/11/2019
            } else if (bp.getContent() instanceof Multipart) {
// QC#51723 MOD START 07/18/2019
//                sb.append(getTextMultipart((Multipart)bp.getContent()));
                bdy = getTextMultipart((Multipart)bp.getContent());
// QC#51723 MOD END   07/18/2019
            }
        }
// QC#51723 MOD START 07/18/2019
//        return sb.toString();
        return bdy;
// QC#51723 MOD END   07/18/2019
    }

    private String[] getAddressPart(Address[] addr) {
        // QC#19549 [IT Nightly JOB Abend]S21SWD_SZWJ0050010 Mail Clean Batch
        if (addr == null || addr.length == 0) {
            return(new String[]{""});
        }

        String[] addrPart = new String[addr.length];
        InternetAddress ia;
        for (int i = 0; i < addr.length; i++) {
            try {
                ia = new InternetAddress(addr[i].toString());
                addrPart[i] = ia.getAddress();
            } catch (AddressException e) {
                addrPart[i] = addr[i].toString();
            }
        }
        return addrPart;
    }
}
