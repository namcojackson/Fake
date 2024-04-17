package com.canon.cusa.s21.batch.ZZW.ZZWB005001;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import parts.dbcommon.EZDTBLAccessor;
import business.db.WF_SYS_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
//import com.canon.cusa.s21.mail.imap.S21ImapClient;
//import com.canon.cusa.s21.mail.imap.S21ImapMessage;
import com.canon.cusa.s21.framework.mail.imap.S21MailImapClient;
import com.canon.cusa.s21.framework.mail.imap.S21MailImapMessage;

/**
 *<pre>
 * Approved Mail delete Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * XXXX/XX/XX   Fujitsu         Q09079          Create          
 * 2021/12/06   Fujitsu         Q09079          Update          FWREQ004_1 IMAP TLSv1.2 Support
 *</pre>
 */
public class ZZWB005001 extends  S21BatchMain{

    private static final S21Logger logger = S21LoggerFactory.getLogger(ZZWB005001.class);

    private String globalCompanyCode;

    private String imapServer;

    private String imapUser;

    private String imappasswd;

    private int imapPort;

    private int holdHours;

    private int fetchLimit;

    private boolean errorFlg;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new ZZWB005001().executeBatch(ZZWB005001.class.getSimpleName());
    }

    protected void initRoutine() {

        this.globalCompanyCode = getGlobalCompanyCode();

        WF_SYS_CONFIGTMsg condMsg = new WF_SYS_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(condMsg.wfSysConfigRecId, "0");

        condMsg = (WF_SYS_CONFIGTMsg) EZDTBLAccessor.findByKey(condMsg);

        this.imapServer = condMsg.mlImapHostNm.getValue();
        this.imapUser = condMsg.mlImapUsrNm.getValue();
        this.imappasswd = condMsg.mlImapPwTxt.getValue();
        this.imapPort = condMsg.mlImapPortNum.getValueInt();
        this.fetchLimit = 0;
        this.holdHours = condMsg.mlImapMsgHldQty.getValueInt();
        this.errorFlg = false;

    }

    protected void mainRoutine() {

        if (logger.isDebugEnabled()) {
            logger.debug("Start Process:ZZWB005001");
        }

        // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
        S21MailImapClient ic = S21MailImapClient.create(this.imapServer, this.imapPort, this.imapUser, this.imappasswd, fetchLimit);
        //S21ImapClient ic = new S21ImapClient(this.imapServer, this.imapPort, this.imapUser, this.imappasswd, this.fetchLimit);
        // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'

        try {
            ic.connect();
            List<Long> uidList = ic.getOldMessage(this.holdHours);

            if (logger.isDebugEnabled()) {
                logger.debug("search result: " + uidList.size());
            }

            for (long uid : uidList) {
                // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
                S21MailImapMessage im = null;
                //S21ImapMessage im = null;
                // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'

                try {
                    if (logger.isDebugEnabled()) {
                        logger.debug("start ** uid: " + uid);
                    }

                    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
                    // 2020/05/25 QC#525(CCIDS) FWDEF536 Mod Start
                    //im = (S21ImapMessage)ic.getMessages(uid);
                    //im = ic.getMessages(uid);
                    // 2020/05/25 QC#525(CCIDS) FWDEF536 Mod End
                    im = ic.getMessages(uid);
                    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'

                    if (im.getValidMessageFlag()) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("--------------------------------------------------");
                            logger.debug("** Valid Flag: " + im.getValidMessageFlag());
                            logger.debug("** from: " + im.getFrom()[0]);
                            logger.debug("** subject: " + im.getSubject());
                            logger.debug("** Date: " + im.getDate());
                            logger.debug("** body: " + im.getBody());
                            logger.debug("--------------------------------------------------");
                        }
                    }

                    ic.setDeleteFlag(uid);

                } finally {
                    if (logger.isDebugEnabled()) {
                        logger.debug("end ** uid: " + uid);
                    }
                }
            }
        } catch (IOException e) {
            errorHandle("imap server connet error", e, null);
        } catch (MessagingException e) {
            errorHandle("imap server connet error", e, null);
        } finally {
            ic.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("End Process:ZZWB005001");
        }
    }

    protected void termRoutine() {
        TERM_CD ret = TERM_CD.NORMAL_END;

        if (this.errorFlg) {
            ret = TERM_CD.ABNORMAL_END;
        }

        this.setTermState(ret);
    }

    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
    private void errorHandle(String err, Exception ex, S21MailImapMessage im) {
    //private void errorHandle(String err, Exception ex, S21ImapMessage im) {
        this.errorFlg = true;

        printError(err, im, ex);
    }

    private void printError(String err, S21MailImapMessage im, Exception ex) {
    //private void printError(String err, S21ImapMessage im, Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append(err + "\r\n");

        if (im != null) {
            sb.append("--------------------------------------------------\r\n");
            sb.append("** Valid Flag: " + im.getValidMessageFlag());
            sb.append("** from: " + im.getFrom()[0]);
            sb.append("** subject: " + im.getSubject());
            sb.append("** Date: " + im.getDate());
            sb.append("** body: " + im.getBody());
            sb.append("--------------------------------------------------\r\n");
        }

        if (ex != null) {
            logger.error(sb.toString(), ex);
        } else {
            logger.error(sb.toString());
        }
    }
    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'
}
