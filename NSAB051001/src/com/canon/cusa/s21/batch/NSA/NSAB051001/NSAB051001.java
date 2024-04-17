/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB051001;

import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.API_CALL_MAX_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DB_COLUMN_PRNT_BLLG_MSTR_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DB_COLUMN_ROSS_INTFC_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DB_PARAM_PRNT_BLLG_MSTR_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DB_PARAM_ROSS_INTFC_CONTR_PROC_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.DB_PARAM_ROSS_INTFC_PROC_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.NASM0010E;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.NASM0011E;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.NSAM0031E;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.ROSS_INTFC_CONTR_PROC_NORMAL;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.ROSS_INTFC_PROC_STS_PROCESSED;
import static com.canon.cusa.s21.batch.NSA.NSAB051001.constant.NSAB051001Constant.ROSS_INTFC_PROC_STS_UNPROCESSED;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ROSS_INTFC_CONTRTMsg;
import business.parts.NSZC106001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC106001.NSZC106001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * NSAB0510: CUSA Retail Contract Interface Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            M.Okigami         Create          N/A
 * 01/11/2017   CITS            T.Kikuhara        Update          QC#15484
 *</pre>
 */
public class NSAB051001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt;

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    @Override
    protected void initRoutine() {

        this.glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NASM0010E);
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NASM0011E);
        }

    }

    @Override
    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
            paramMap.put(DB_PARAM_ROSS_INTFC_CONTR_PROC_CD, ROSS_INTFC_CONTR_PROC_NORMAL);
            paramMap.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            preparedStatement = ssmLlcClient.createPreparedStatement("getRossIntfcContr", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            int apiCallCount = 0;
            NSZC106001PMsg inMsg = new NSZC106001PMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.slsDt, this.slsDt);
            while (resultSet.next()) {

                BigDecimal prntBllgMstrId = resultSet.getBigDecimal(DB_COLUMN_PRNT_BLLG_MSTR_ID);
                ZYPEZDItemValueSetter.setValue(inMsg.prntBllgMstrInfo.no(apiCallCount).prntBllgMstrId, prntBllgMstrId);
                apiCallCount++;
                this.totalCount++;

                // Call NSZC1060
                if (API_CALL_MAX_COUNT == apiCallCount) {
                    // API Call
                    inMsg.prntBllgMstrInfo.setValidCount(apiCallCount);
                    if (callNSZC106001(inMsg)) {
                        commit();
                    } else {
                        this.errorCount += apiCallCount;
                        rollback();
                    }

                    inMsg.prntBllgMstrInfo.clear();
                    inMsg.xxMsgIdList.clear();
                    apiCallCount = 0;
                }
            }

            if (apiCallCount != 0) {
                // API Call
                inMsg.prntBllgMstrInfo.setValidCount(apiCallCount);
                if (callNSZC106001(inMsg)) {
                    commit();
                } else {
                    this.errorCount += apiCallCount;
                    rollback();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    @Override
    protected void termRoutine() {

        sendEmail();
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
    }

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NSAB051001().executeBatch(NSAB051001.class.getSimpleName());
    }

    private boolean callNSZC106001(NSZC106001PMsg inMsg) {

        NSZC106001 nszc106001 = new NSZC106001();
        nszc106001.execute(inMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(inMsg))  {
            for (int i = 0; i < inMsg.xxMsgIdList.getValidCount(); i++) {
                setErrorInfo(inMsg.xxMsgIdList.no(i).xxMsgId.getValue(), null);
            }
            return false;
        }

        for (int i = 0; i < inMsg.prntBllgMstrInfo.getValidCount(); i++) {
            if (!updateProcStatus(inMsg.prntBllgMstrInfo.no(i).prntBllgMstrId.getValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean updateProcStatus(BigDecimal prntBllgMstrId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_PRNT_BLLG_MSTR_ID, prntBllgMstrId);
        ssmParam.put(DB_PARAM_ROSS_INTFC_PROC_STS_CD, ROSS_INTFC_PROC_STS_UNPROCESSED);

        List<Map<String, Object>> rossIntfcContrPkList =
            (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getRossIntfcContrPkFromPrntBllgMstrId", ssmParam);

        for (Map<String, Object> rossIntfcContrPk : rossIntfcContrPkList) {
            ROSS_INTFC_CONTRTMsg inMsg = new ROSS_INTFC_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rossIntfcContrPk, (BigDecimal) rossIntfcContrPk.get(DB_COLUMN_ROSS_INTFC_CONTR_PK));

            ROSS_INTFC_CONTRTMsg outMsg = (ROSS_INTFC_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                setErrorInfo(NSAM0031E, new String[] {inMsg.getTableName()});
                return false;
            } else {
                ZYPEZDItemValueSetter.setValue(outMsg.rossIntfcProcStsCd, ROSS_INTFC_PROC_STS_PROCESSED);
                EZDTBLAccessor.update(outMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    setErrorInfo(NSAM0031E, new String[] {outMsg.getTableName()});
                    return false;
                }
            }
        }
        return true;
    }

    private void setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        this.msgList.add(S21MessageFunc.clspGetMessage(msgId, params));
    }

    private void sendEmail() {

        if (msgList.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(this.glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    for (String msg : msgList) {
                        msgBuf.append(msg);
                        msgBuf.append(newLine);
                    }

                    SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

                    tmpl.setTemplateParameter("batchId", BUSINESS_ID);
                    tmpl.setTemplateParameter("errDate", errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter("message", msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();

                    // Error Info Insert commit
                    commit();
                }
            }
        }
    }

}
