package com.canon.cusa.s21.batch.NFC.NFCB026001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDFastTBLAccessor;
import business.db.AR_RCPT_RCV_INTFCTMsg;
import business.db.AR_RCPT_RCV_INTFCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOCK_BOX_NTFY_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NFCB026001 Receipt Data Creation Send Mail
 *
 * Date         Company       Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/26   Fujitsu      H.Ikeda          Create          N/A
 * 2019/04/02   Fujitsu      S.Ohki           Update          QC#31024
 *
 */
public class NFCB026001 extends S21BatchMain  implements ZYPConstant, NFCB026001Constant{

    /** Termination Code */
    private TERM_CD termCd;

    /** GlobalCompanyCode */
    private String glblCmpyCd = "";

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Common component for Database accessing */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Batch Proc Date */
    private String batchProcDt = "";

    /** interfaceId. */
    private String interfaceId = "";

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** err Count */
    private int errCnt = 0;

    @Override
    protected void initRoutine() {
        debugLog("initRoutine start");

        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        if (!getGlblCmpyCd()) {
            execAbendException(NFCM0501E, MSG_STR_GLBL_CMPY_CD);
        }

        this.batchProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.batchProcDt)) {
            execAbendException(NFCM0574E);
        }

        this.interfaceId = S21BatchUtil.getInterfaceID();

        if (S21StringUtil.isEmpty(this.interfaceId)) {
            this.errCnt++;
            setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
            throw new S21AbendException(NFCM0522E, MSG_STR_INTERFACE_ID);
        }

        this.termCd = TERM_CD.NORMAL_END;

        debugLog("initRoutine end");
     }

    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");

        execute();

        debugLog("mainRoutine end");
    }

    /**
     * Receipt Data Creation
     */
    private void execute() {
        debugLog("execute start");

        // normal
        sendMLNormal();
        // err
        sendMLErr();

        debugLog("execute end");
    }

    /**
     * Send Mail Normal
     */
    private void sendMLNormal() {
        // normal data get
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getArLockBoxFileNm(TYPE_NORMAL);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String arLockBoxFileNum = rs.getString("AR_LOCK_BOX_FILE_NM");
                if (chkArLockBoxFileNmError(arLockBoxFileNum)) {
                    // send Mail
                    if (sendMail(TYPE_NORMAL, arLockBoxFileNum)) {
                        // update AR_RCPT_RCV_INTFC
                        updateArRcptRcvIntfc(arLockBoxFileNum);
                    } else {
                        this.errCnt = this.errCnt + 1;
                        if (this.procCount == 0) {
                            this.procCount = this.errCnt;
                        } else {
                            this.procCount = this.procCount - this.errCnt;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    private void sendMLErr() {
        // error data get
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getArLockBoxFileNm(TYPE_ERROR);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String arLockBoxFileNum = rs.getString("AR_LOCK_BOX_FILE_NM");
                // send Mail
                if (sendMail(TYPE_ERROR, arLockBoxFileNum)) {
                    // update AR_RCPT_RCV_INTFC
                    updateArRcptRcvIntfc(arLockBoxFileNum);
                } else {
                    this.errCnt = this.errCnt + 1;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            debugLog("execute : closeResource");
        }
    }

    /**
     * Update AR_RCPT_RCV_INTFC(LOCK_BOX_NTFY_STS_CD = 1)
     * 
     * @param arLockBoxFileNum String
     */
    private void updateArRcptRcvIntfc(String arLockBoxFileNum) {
        AR_RCPT_RCV_INTFCTMsg inMsg = new AR_RCPT_RCV_INTFCTMsg();

        inMsg.setConditionValue("arLockBoxFileNm01", arLockBoxFileNum);
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setSQLID("001");

        AR_RCPT_RCV_INTFCTMsgArray tMsgArray = (AR_RCPT_RCV_INTFCTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

        List<AR_RCPT_RCV_INTFCTMsg> updTMsgList = new ArrayList<AR_RCPT_RCV_INTFCTMsg>();

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            AR_RCPT_RCV_INTFCTMsg updTMsg = tMsgArray.no(i);

            updTMsg.lockBoxNtfyStsCd.setValue(LOCK_BOX_NTFY_STS.SEND);
            updTMsgList.add(updTMsg);

            if (updTMsgList.size() >= BULK_CNT) {
                EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
                updTMsgList.clear();
            }
        }
        if (!updTMsgList.isEmpty()) {
            EZDFastTBLAccessor.update(updTMsgList.toArray(new AR_RCPT_RCV_INTFCTMsg[updTMsgList.size()]));
        }
    }

    /**
     * Send Mail
     * 
     * @param mailType        String
     * @param arLockBoxFileNm String
     * @return boolean true:OK false:NG
     */
    private boolean sendMail(String mailType, String arLockBoxFileNm) {

        S21MailGroup mailGroup = new S21MailGroup(glblCmpyCd, ML_GRP_ID);

        // From
        S21MailAddress from = null;
        mailGroup.setMailKey1(ML_GRP_ID_KEY_FROM);
        List<S21MailAddress> addrFromList = mailGroup.getMailAddress();
        if (addrFromList.size() == 1) {
            from = addrFromList.get(0);
        } else {
            throw new S21AbendException("ZZMM0007E", new String[]{"From Address"});
        }

        // To
        mailGroup.setMailKey1(ML_GRP_ID_KEY_TO);
        List<S21MailAddress> addrToList = mailGroup.getMailAddress();

        // Template
        S21MailTemplate tmplt = null;
        if (TYPE_ERROR.equals(mailType)) {
            tmplt = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID_ERROR);
        } else {
            tmplt = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID_NORMAL);
        }
        tmplt.setTemplateParameter("arLockBoxFileNm", arLockBoxFileNm);

        // Send Mail
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(tmplt);
        mail.setSubject(tmplt.getSubject("en"), "ISO-8859-1");

        // START 2019/04/02 S.Ohki [QC#31024, MOD]
//        String mailEvent = mail.sendMail();
        String mailEvent = mail.postMail();
        // END   2019/04/02 S.Ohki [QC#31024, MOD]
        if (!ZYPCommonFunc.hasValue(mailEvent)) {
            return false;
        } else {
            String mailResult = mail.getResultCd();
            if (ZYPCommonFunc.hasValue(mailResult)) {
                return false;
            }
        }

        this.normalCnt = this.normalCnt + 1;
        this.procCount = this.normalCnt;
        return true;
    }

    private PreparedStatement getArLockBoxFileNm(String type) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("rcvFuncId", this.interfaceId);
        queryParam.put("type", type);
        queryParam.put("arLockBoxStsCd", AR_LOCK_BOX_STS.ERROR);
        queryParam.put("lockBoxNtfyStsCd", LOCK_BOX_NTFY_STS.SEND);

        return this.ssmLLClient.createPreparedStatement("getArLockBoxFileNm", queryParam, execParam);
    }

    private boolean chkArLockBoxFileNmError(String arLockBoxFileNm) {
        
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("rcvFuncId", this.interfaceId);
        sqlParam.put("arLockBoxStsCd", AR_LOCK_BOX_STS.ERROR);
        sqlParam.put("arLockBoxFileNm", arLockBoxFileNm);

        BigDecimal cnt =(BigDecimal) ssmBatchClient.queryObject("getCountErrorData", sqlParam);
        if (!ZYPCommonFunc.hasValue(cnt) || cnt.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }

        return false;
    }
    
    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(termCd, this.normalCnt, this.errCnt, this.procCount);
        
        S21InfoLogOutput.println("termRoutine Method End");
    }

    /**
     * Output Debug Log
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, logmsg, this);
    }

    /**
     * Main method that is called from batch
     * @param args parameter
     */
    public static void main(String[] args) {
        /* Initialization S21BatchMain */
        new NFCB026001().executeBatch(NFCB026001.class.getSimpleName());
    }

    /**
     * Get Global Company Code
     * @return boolean true:Normal false:Failure
     */
    private boolean getGlblCmpyCd() {
        debugLog("getGlblCmpyCd start");

        /* Get Global Company Code */
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            debugLog("getGlblCmpyCd : isEmpty error");
            return false;
        }

        debugLog("getGlblCmpyCd end");
        return true;
    }

    /**
     * Execute AbendException
     * @param msgId String
     */
    private void execAbendException(String msgId) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId);
    }

    /**
     * Execute AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {
        setTermState(TERM_CD.ABNORMAL_END, this.normalCnt, this.errCnt, this.procCount);
        throw new S21AbendException(msgId, msgStr);
    }
}
