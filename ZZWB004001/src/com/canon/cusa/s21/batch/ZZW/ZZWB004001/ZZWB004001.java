package com.canon.cusa.s21.batch.ZZW.ZZWB004001;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCommonFunc;
import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;

import business.db.WF_SYS_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailExt;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.mail.S21Mail.MAILFORMAT;
import com.canon.cusa.s21.framework.mail.imap.S21MailImapClient;
import com.canon.cusa.s21.framework.mail.imap.S21MailImapMessage;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.common.impl.S21NwfBatchContext;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfAuthException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfInvalidStateException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.notify.S21NwfMailTemplate;
import com.canon.cusa.s21.framework.nwf.core.notify.S21NwfMailTemplate.MAILTYPE;
import com.canon.cusa.s21.framework.nwf.core.notify.impl.S21NwfMailTemplateImpl;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
//import com.canon.cusa.s21.mail.imap.S21ImapClient;
//import com.canon.cusa.s21.mail.imap.S21ImapMessage;

// Message ID
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0040E;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0041E;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0042E;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0043E;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0044E;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0045I;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0046I;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0047E;
import static com.canon.cusa.s21.batch.ZZW.ZZWB004001.constant.ZZWB004001Constant.ZZWM0048E;

/**
 *<pre>
 * Mail Approve Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/03/26   Fujitsu         Q10627          Update          QC#30827
 * 2019/05/14   Fujitsu         Q10627          Update          QC#50213
 * 2019/07/18   Fujitsu         Q10814          Update          QC#51723
 * 2020/05/14   Fujitsu         Q09079          Update          QC#525(CCIDS) FWDEF536
 * 2021/12/06   Fujitsu         Q09079          Update          FWREQ004_1 IMAP TLSv1.2 Support
 *</pre>
 */
public class ZZWB004001 extends S21BatchMain {

    private static final S21Logger logger = S21LoggerFactory.getLogger(ZZWB004001.class);

// QC#51723 MOD START 07/18/2019
//    private static final String REG_APPROVE = "\\s*APPROVE\\:(.*)\\s*";
    private static final String REG_APPROVE = "\\s*APPROVE\\:(.{36})\\s*";

//    private static final String REG_REJECT = "\\s*REJECT\\:(.*)\\s*";
    private static final String REG_REJECT = "\\s*REJECT\\:(.{36})\\s*";

//    private static final String REG_COMMENT = "\\s*Comment\\s*\r\n\\s*;START\\-*\\s*\r\n(.*)\r\n\\s*;END\\-*";
    private static final String REG_COMMENT = "\\s*Comment\\s*(\r\n)*\\s*;START\\-*\\s*(\r\n)*(.*)(\r\n)*\\s*;END\\-*";
// QC#51723 MOD END   07/18/2019

    /** Fetch size from IMAP server */
    private static int fetchLimit = 500;

    /** Get Line Separator */
    private static final String LS = System.getProperty("line.separator", "\n");

    private Pattern ptnApprove = Pattern.compile(REG_APPROVE);

    private Pattern ptnReject = Pattern.compile(REG_REJECT);

    private Pattern ptnComment = Pattern.compile(REG_COMMENT, Pattern.DOTALL);

    private String globalCompanyCode;

    private String imapServer;

    private String imapUser;

    private String imappasswd;

    private int imapPort;

    private boolean errorFlg;

    private static enum APLV_TYPE {
        APPROVE, REJECT, ERROR
    };

    private static enum REPLY_TYPE {
        NONE, NORMAL, ERROR
    };

    private int totalRecCnt = 0;

    private int normalRecCnt = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new ZZWB004001().executeBatch(ZZWB004001.class.getSimpleName());
    }

    @Override
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
        int limit = condMsg.mlImapFetchLimitQty.getValueInt();

        if (limit > 0){
            fetchLimit = limit;
        }

        this.errorFlg = false;
    }

    @Override
    protected void mainRoutine() {

        if (logger.isDebugEnabled()) {
            logger.debug("Start Process:ZZWB004001");
        }

        boolean abendFlg = true;

        // [FWREQ004_1]  - Mz.Takahashi 2021/12/06  MOD START 'IMAP TLSv1.2 Support'
        S21MailImapClient ic = S21MailImapClient.create(this.imapServer, this.imapPort, this.imapUser, this.imappasswd, fetchLimit);
        //S21ImapClient ic = new S21ImapClient(this.imapServer, this.imapPort, this.imapUser, this.imappasswd, fetchLimit);
        // [FWREQ004_1]  - Mz.Takahashi 2021/12/06  MOD END 'IMAP TLSv1.2 Support'

        try {
            ic.connect();
            List<Long> uidList = ic.getUidList();
            BigDecimal taskId = null;
            StringBuilder taskIdentifier = new StringBuilder();

            logger.info("search result: " + uidList.size());

            String fromAddr;
            S21NwfTokenImpl token;

            this.totalRecCnt = uidList.size();
            if (totalRecCnt == 0) {
                logger.error("No mail to read.");
            }

            for (long uid : uidList) {
                // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
                S21MailImapMessage im = null;
                //S21ImapMessage im = null;
                // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'
                fromAddr = "";
                token = null;
                String signalName = "";
                taskIdentifier = new StringBuilder();
                REPLY_TYPE reply = REPLY_TYPE.NONE;
                boolean imapFlag = true;
                abendFlg = false;

                List<EZDMessageInfo> msgInfoList = new ArrayList<EZDMessageInfo>();

                try {
                    logger.info("start ** uid: " + uid);

                    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
                    // 2020/05/15 QC#525(CCIDS) FWDEF536 Mod Start 
                    //im =(S21ImapMessage)ic.getMessages(uid);
                    //im = ic.getMessages(uid);
                    // 2020/05/15 QC#525(CCIDS) FWDEF536 Mod End
                    im = ic.getMessages(uid);
                    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'

                    if (im.getValidMessageFlag()) {
                        logger.info("--------------------------------------------------");
                        logger.info("** Valid Flag: " + im.getValidMessageFlag());
                        logger.info("** from: " + im.getFrom()[0]);
                        logger.info("** subject: " + im.getSubject());
                        logger.info("** Date: " + im.getDate());
                        logger.info("** body: " + im.getBody());
                        logger.info("--------------------------------------------------");

                        // fromAddr to uid
                        String qid = toQid(im.getFrom()[0]);

                        if (S21StringUtil.isEmpty(qid)) {
                            String[] param = {im.getFrom()[0]};
                            errorHandle(ZZWM0040E, param, im, abendFlg);
                            continue;
                        }

                        fromAddr = im.getFrom()[0];

                        // Parse
                        
                        StringBuilder comment = new StringBuilder();
                        APLV_TYPE aplvType = parseBody(im.getBody(), taskIdentifier, comment);

                        if (APLV_TYPE.ERROR.equals(aplvType)) {
                            String[] param = {"email body parse error"};
                            msgInfoList.add(errorHandle(ZZWM0041E, param, im, abendFlg));
                            continue;
                        }

                        // taskIdentifie to procId taskId
                        StringBuilder procIdSb = new StringBuilder();
                        StringBuilder taskIdSb = new StringBuilder();

                        boolean ret = getProcAndTaskId(taskIdentifier.toString(), procIdSb, taskIdSb);

                        if (ret == false) {
                            String[] param = {String.format("process task id error[%s]", taskIdentifier.toString())};
                            msgInfoList.add(errorHandle(ZZWM0041E, param, im, abendFlg));
                            continue;
                        }

                        BigDecimal procId = new BigDecimal(procIdSb.toString());
                        taskId = new BigDecimal(taskIdSb.toString());

                        logger.info("procId = " + procId.intValue());
                        logger.info("taskId = " + taskId.intValue());


                        if (APLV_TYPE.APPROVE.equals(aplvType)) {
                            signalName = S21NwfConst.SIGNAL_APPROVE;
                        } else if (APLV_TYPE.REJECT.equals(aplvType)) {
                            signalName = S21NwfConst.SIGNAL_REJECT;
                        }
                        logger.info("signalName = " + signalName);

                        // getToken
                        try {
                            token = getToken(procId, taskId, qid);
                        } catch (S21NwfSystemException e) {
                            String[] param = {"get token by identifier error"};
                            msgInfoList.add(errorHandle(ZZWM0042E, param, im, e, abendFlg));
                            continue;
                        }
                        catch (EZDDBRecordLockedException e) {
                            // Other user is processing, retry next time
                            imapFlag = false;
                            errorHandle(ZZWM0048E, im, e, abendFlg);
                            continue;
                        }
                        catch (Exception e) {
                            abendFlg = true;
                            msgInfoList.add(errorHandle(ZZWM0047E, im, e, abendFlg));
                            continue;
                        }

                        // Approve or Reject
                        try {

                            if (comment.length() > 0) {
                                S21NwfTokenObj tobj = token.getTokenObj();
                                tobj.setComment(comment.toString());
                                token.setTokenObj(tobj);
                            }
// 2019/03/26 UPD START QC#30827
                            // token.signal(signalName);
                            if (S21NwfConst.SIGNAL_REJECT.equalsIgnoreCase(signalName)) {
                                // Reject with AuthCheck
                                boolean ignoreAuth = false;
                                token.signal(signalName, ignoreAuth);
                            }
                            else if (S21NwfConst.SIGNAL_APPROVE.equalsIgnoreCase(signalName)) {
                                // Approve with AuthCheck
                                token.signal(signalName);
                            }
// 2019/03/26 UPD END   QC#30827
                        } catch (S21NwfAuthException e) {
                            msgInfoList.add(errorHandle(ZZWM0043E, im, e, abendFlg));
                            continue;
                        } catch (S21NwfBizException e) {
                            msgInfoList.add(errorHandle(ZZWM0044E, im, e, abendFlg));
                            reply = REPLY_TYPE.ERROR;
                            continue;
                        } catch (S21NwfInvalidStateException e) {
                            msgInfoList.add(errorHandle(ZZWM0045I, im, e, abendFlg));
                            this.normalRecCnt++;
                            continue;
                        } catch (S21NwfSystemException e) {
                            msgInfoList.add(errorHandle(ZZWM0047E, im, e, abendFlg));
                            continue;
                        } catch (S21NwfException e) {
                            msgInfoList.add(errorHandle(ZZWM0047E, im, e, abendFlg));
                            continue;
                        }
                        catch (EZDDBRecordLockedException e) {
                            // Other user is processing, retry next time
                            imapFlag = false;
                            errorHandle(ZZWM0048E, im, e, abendFlg);
                            continue;
                        }
                        catch (Exception e) {
                            abendFlg = true;
                            msgInfoList.add(errorHandle(ZZWM0047E, im, e, abendFlg));
                            continue;
                        }
                    }
 

                    // Normal Process

                    // Check messages in token result
                    if (token != null){
                        msgInfoList = token.getLastBizMessage();
                        if (msgInfoList == null || msgInfoList.size() == 0) {
                            abendFlg = true;
                            //The request has been successfully processed
                            reply = REPLY_TYPE.NORMAL;
                            String[] param = new String[]{signalName};
                            msgInfoList.add(new EZDMessageInfo(ZZWM0046I, param));
                            // commit here once to update history
                            commit();
                            abendFlg = false;
                            logger.info("commit end after token.signal()");
                        } else {
                            reply = REPLY_TYPE.ERROR;
                        }

                        for (EZDMessageInfo msgInfo: msgInfoList) {
                            logger.error(msgInfo.getCode() + ": "+ msgInfo.getMessage());
                        }
                    }
                    this.normalRecCnt++;
                }
                finally {
                    if ((reply != REPLY_TYPE.NONE) && !abendFlg) {
                        mailSend(fromAddr, im, token, taskIdentifier.toString(), msgInfoList, reply);
                        commit();
                        logger.info("commit end after mailsend");
                    }

                    if (ic != null && !abendFlg && imapFlag) {
                        if (ic.isConnected()) {
                            ic.setImapFlag(uid);
                        }
                    }

                    logger.info("end ** uid: " + uid);
                }
            }

        } catch (IOException e) {
            abendFlg = true;
            errorHandle(ZZWM0047E, null, e, abendFlg);
        } catch (javax.mail.MessagingException e) {
            abendFlg = true;
            errorHandle(ZZWM0047E, null, e, abendFlg);
        } catch (Exception e) {
            abendFlg = true;
            errorHandle(ZZWM0047E, null, e, abendFlg);
        } finally {
            ic.close();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("End Process:ZZWB004001");
        }
    }

    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD START 'IMAP TLSv1.2 Support'
    /**
     * errorHandle()
     * Print Error and rollback transaction
     * @param msgId String
     * @param im S21ImapMessage
     * @param ex Exception
     * @param abendFlg boolean
     * @return messageinfo EZDMessageInfo
     */
    private EZDMessageInfo errorHandle(String msgId, S21MailImapMessage im, Exception ex, boolean abendFlg) {
    //private EZDMessageInfo errorHandle(String msgId, S21ImapMessage im, Exception ex, boolean abendFlg) {
        return errorHandle(msgId, null, im, ex, abendFlg);
    }

    /**
     * errorHandle()
     * Print Error and rollback transaction
     * @param msgId String
     * @param param String[]
     * @param im S21ImapMessage
     * @param abendFlg boolean
     * @return messageinfo EZDMessageInfo
     */
    private EZDMessageInfo errorHandle(String msgId, String[] param, S21MailImapMessage im, boolean abendFlg) {
    //private EZDMessageInfo errorHandle(String msgId, String[] param, S21ImapMessage im, boolean abendFlg) {
        return errorHandle(msgId, param, im, null, abendFlg);
    }

    /**
     * errorHandle()
     * Print Error and rollback transaction
     * @param msgId String
     * @param param String[]
     * @param im S21ImapMessage
     * @param ex Exception
     * @param abendFlg boolean
     * @return messageinfo EZDMessageInfo
     */
    private EZDMessageInfo errorHandle(String msgId, String[] param, S21MailImapMessage im, Exception ex, boolean abendFlg) {
    //private EZDMessageInfo errorHandle(String msgId, String[] param, S21ImapMessage im, Exception ex, boolean abendFlg) {

        EZDMessageInfo msgInfo = null;
        if (param != null) {
            msgInfo = new EZDMessageInfo(msgId, param);

        } else {
            // Get cause message
            String causeMsg = null;
            if (ex instanceof S21NwfException) {
                S21NwfException e = (S21NwfException) ex;
                EZDMessageInfo exMsgInfo = e.getMessageInfo();
                if (exMsgInfo != null) {
                    causeMsg = exMsgInfo.getCode() + ": " + exMsgInfo.getMessage();
                }

            } else if (ex instanceof S21AbendException) {
                causeMsg = "Abend Exception Occurred";

            } else {
                causeMsg = ex.getMessage();
            }
            if (causeMsg == null) {
                causeMsg = "Some error occurred";
            }
            param = new String[]{ causeMsg };
            msgInfo = new EZDMessageInfo(msgId, param);

      }

      printError(msgInfo, im, ex, abendFlg);

      rollback();
      logger.info("rollback end");

      return msgInfo;
    }

    private void printError(EZDMessageInfo msgInfo, S21MailImapMessage im, Exception ex, boolean abendFlg) {
    //private void printError(EZDMessageInfo msgInfo, S21ImapMessage im, Exception ex, boolean abendFlg) {

        StringBuilder sb = new StringBuilder();

        if (msgInfo != null) {
            String code = msgInfo.getCode();
            String err = msgInfo.getMessage();
            sb.append(code).append(": ").append(err).append(LS);
        }
        else {
            sb.append("Some system error occurred").append(LS);
        }

        if (im != null) {
            sb.append("--------------------------------------------------").append(LS);
            sb.append("** Valid Flag: " + im.getValidMessageFlag()).append(LS);
            sb.append("** from: " + im.getFrom()[0]).append(LS);
            sb.append("** subject: " + im.getSubject()).append(LS);
            sb.append("** Date: " + im.getDate()).append(LS);
            sb.append("** body: " + im.getBody()).append(LS);
            sb.append("--------------------------------------------------").append(LS);
        }

        if (ex != null) {
            String message = null;
            if (ex instanceof S21NwfException) {
                S21NwfException e = (S21NwfException) ex;
                EZDMessageInfo exMsgInfo = e.getMessageInfo();
                if (exMsgInfo != null) {
                    message = exMsgInfo.getCode() + ": " + exMsgInfo.getMessage();
                }
            } else {
                message = ex.getMessage();
            }
            if (message != null) {
                sb.append("Cause Exception : ").append(message);
            }
            logger.error(sb.toString(), ex);
        } else {
            logger.error(sb.toString());
        }

        if (abendFlg) {
            this.errorFlg = true;
        }

    }
    // [FWREQ004_1]  - Mz.Takahashi　2021/12/06  MOD END 'IMAP TLSv1.2 Support'

    @Override
    protected void termRoutine() {

        TERM_CD ret = TERM_CD.NORMAL_END;

        if (this.errorFlg) {
            ret = TERM_CD.ABNORMAL_END;
        }

        this.setRecordCount(this.normalRecCnt, this.totalRecCnt-this.normalRecCnt, this.totalRecCnt);
        this.setTermState(ret);

    }

    /**
     * @param body
     * @param taskIdentifier
     * @param comment
     * @return
     */
    private APLV_TYPE parseBody(String body, StringBuilder taskIdentifier, StringBuilder comment) {

        // Comment
        Matcher m = this.ptnComment.matcher(body);

        while (m.find()) {
            int cnt = m.groupCount();

// QC#51723 MOD START 07/18/2019
//            if (cnt >= 1) {
            if (cnt >= 4) {
// QC#51723 MOD END   07/18/2019
// QC#50213 MOD START 05/14/2019
//              comment.append(m.group(1));
// QC#51723 MOD START 07/18/2019
//                comment.append(EZDCommonFunc.toRestoreHTMLString(m.group(1)));
                comment.append(EZDCommonFunc.toRestoreHTMLString(m.group(3).trim()));
// QC#51723 MOD END   07/18/2019
// QC#50213 MOD END   05/14/2019
                break;
            }
        }

        // Approve
        m = this.ptnApprove.matcher(body);

        while (m.find()) {
            int cnt = m.groupCount();

            if (cnt >= 1) {
                taskIdentifier.append(m.group(1).trim());
                return APLV_TYPE.APPROVE;
            }
        }

        // Reject
        m = this.ptnReject.matcher(body);

        while (m.find()) {
            int cnt = m.groupCount();

            if (cnt >= 1) {
                taskIdentifier.append(m.group(1).trim());
                return APLV_TYPE.REJECT;
            }
        }

        return APLV_TYPE.ERROR;
    }

    /**
     * to qid
     * @param fromAddr from address
     * @return qid
     */
    private String toQid(String fromAddr) {
        String ret = "";

        S21SsmEZDResult userList = ZZWB004001Query.getInstance().getQidByFromAddr(fromAddr);

        if (userList.isCodeNormal()) {
            // Normal
            List userltList = (List) userList.getResultObject();

            if (userltList.size() > 0) {
                Map userMap = (Map) userltList.get(0);

                if (userMap.containsKey("USR_NM")) {
                    ret = (String) userMap.get("USR_NM");
                }
            }
        }

        return ret;

    }

    private boolean getProcAndTaskId(String taskIdentifier, StringBuilder procId, StringBuilder taskId) {
        boolean ret = false;

        S21SsmEZDResult taskList = ZZWB004001Query.getInstance().getProcAndTaskIdByApvlId(taskIdentifier);

        if (taskList.isCodeNormal()) {
            // Normal
            List tList = (List) taskList.getResultObject();

            if (tList.size() > 0) {
                Map tMap = (Map) tList.get(0);

                procId.append(tMap.get("WF_PROC_PK"));
                taskId.append(tMap.get("WF_WRK_ITEM_PK"));
                ret = true;
            }
        }
        return ret;
    }

    private S21NwfTokenImpl getToken(BigDecimal procId, BigDecimal taskId, String usrId) throws S21NwfSystemException {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process;

        try {
            S21NwfContext context = factory.getContex();

            if (context instanceof S21NwfBatchContext) {
                ((S21NwfBatchContext) context).setUserID(usrId);
            }

            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(procId);
        } catch (NumberFormatException e1) {
            throw new S21NwfSystemException(e1);
        } catch (S21NwfSystemException e1) {
            throw e1;
        }

        return (S21NwfTokenImpl)process.getToken();
    }

    private void mailSend(String fromUser, S21MailImapMessage im, S21NwfTokenImpl token, String wrkItemApvlId, List<EZDMessageInfo> msgInfoList, REPLY_TYPE reply){
    //private void mailSend(String fromUser, S21ImapMessage im, S21NwfTokenImpl token, String wrkItemApvlId, List<EZDMessageInfo> msgInfoList, REPLY_TYPE reply){
        if (S21StringUtil.isEmpty(fromUser)) {
            return;
        }

        String tmplId = "";
        
        if (reply == REPLY_TYPE.NORMAL){
            tmplId = getMlTmplRtrnId(wrkItemApvlId);
        } else if (reply == REPLY_TYPE.ERROR){
            tmplId = getMlTmplErrId(wrkItemApvlId);
        }

        if (S21StringUtil.isEmpty(tmplId)){
            return;
        }

        S21MailExt mail = new S21MailExt(globalCompanyCode);
        S21MailAddress mailAddr = new S21MailAddress(globalCompanyCode, fromUser);

        mail.setMailTemplate(getTemplate(globalCompanyCode, im, tmplId, token, msgInfoList));
        mail.setToAddress(mailAddr);
        mail.postMail();
        // mail.sendMail(); --> For Debug
    }

    /**
     * getTemplate
     * @param globalCompanyCode String
     * @param templateId String
     * @param token S21NwfTokenImpl
     * @param msgInfoList List<EZDMessageInfo>
     * @return
     */
    private S21MailTemplate getTemplate(String globalCompanyCode, S21MailImapMessage im, String templateId, S21NwfTokenImpl token, List<EZDMessageInfo> msgInfoList) {
    //private S21MailTemplate getTemplate(String globalCompanyCode, S21ImapMessage im, String templateId, S21NwfTokenImpl token, List<EZDMessageInfo> msgInfoList) {
        String separator = "\n";
        S21MailTemplate tpl;
        if (token != null) {
            S21NwfMailTemplate tmpl = S21NwfMailTemplateImpl.create(globalCompanyCode, templateId);
            tmpl.setMailType(MAILTYPE.NOTIFY);
            tmpl.setTemplateParameter(token.getTokenObj());
            tmpl.setHistoryTagKey(token.getProcess().getProcessId());
            tpl = tmpl.getMailTemplate();

            // Set separator for HTML/Text
            MAILFORMAT format = tmpl.getMailFormat();
            if (format == MAILFORMAT.HTML) {
                separator = "<br>";
            }
        } else {
            // In case that token is no gotten.
            tpl = new S21MailTemplate(globalCompanyCode, "NWFERRNOTKN");
            tpl.setTemplateParameter("body", im.getBody());
            tpl.setTemplateParameter("subject", im.getSubject());
            tpl.setTemplateParameter("date", im.getDate());
        }

        // Set messages
        StringBuffer msgBuf = new StringBuffer();
        int i = 0;
        for (EZDMessageInfo msgInfo: msgInfoList) {
            if (i > 0) {
                msgBuf.append(separator);
            }
            msgBuf.append(msgInfo.getCode()).append(": ").append(msgInfo.getMessage());
            i++;
        }

        String message = msgBuf.toString();
        if (message != null) {
            tpl.setTemplateParameter("S21NwfMailMessage", message);
        }
        return tpl;
    }

    /**
     * getMlTmplErrId()
     * Get mail template id for error
     * @param taskId BigDecimal
     * @return mlTmplErrId String
     */
    private String getMlTmplRtrnId(String wrkItemApvlId){
        String mlTmplErrId = "";

        if (S21StringUtil.isEmpty(wrkItemApvlId)) {
            return null;
        }

        S21SsmEZDResult ssmResult = ZZWB004001Query.getInstance().getMlTmplRtrnId(wrkItemApvlId);

        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map resultMap = (Map) resultList.get(0);
                String tmplId = (String)resultMap.get("ML_TMPL_RTRN_ID");
                if (tmplId != null && !tmplId.isEmpty()) {
                    mlTmplErrId = tmplId;
                }
            }
        }

        return mlTmplErrId;

    }
    
    
    /**
     * getMlTmplErrId()
     * Get mail template id for error
     * @param taskId BigDecimal
     * @return mlTmplErrId String
     */
    private String getMlTmplErrId(String wrkItemApvlId){
        String mlTmplErrId = "";

        if (S21StringUtil.isEmpty(wrkItemApvlId)) {
            return null;
        }

        S21SsmEZDResult ssmResult = ZZWB004001Query.getInstance().getMlTmplErrId(wrkItemApvlId);

        if (ssmResult.isCodeNormal()) {
            // Normal
            List resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map resultMap = (Map) resultList.get(0);
                String tmplId = (String)resultMap.get("ML_TMPL_ERR_ID");
                if (tmplId != null && !tmplId.isEmpty()) {
                    mlTmplErrId = tmplId;
                }
            }
        }

        return mlTmplErrId;

    }
}

