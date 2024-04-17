/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB053001;

import static com.canon.cusa.s21.batch.NSA.NSAB053001.constant.NSAB053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_TERM_CONDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * SLA Time Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   Hitachi         Y.Osawa         Create          N/A
 * 2018/06/05   Hitachi         K.Kim           Update          QC#25993
 * </pre>
 */
public class NSAB053001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** System TimeStamp */
    private String sysTime = null;

    /** Sales Date */
    private String salesDate = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Massage */
    private String errMsg = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Mail Template ID */
    private String mailTemplateId = null;

    /** Division Number */
    private String divisionNum = null;

    /** Residue */
    private String residue = null;

    /** Get Commit Number */
    private int commitNumber;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Get Division Number
        this.divisionNum = getUserVariable1();

        // Get Residue
        this.residue = getUserVariable2();

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Mail Template
        this.mailTemplateId = SET_MAIL_TEMPLATE_ID;

        // initialize
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("divisionNum", this.divisionNum);
        paramMap.put("residue", this.residue);
        paramMap.put("flt", DS_CONTR_CATG.FLEET);
        paramMap.put("fleet", DS_CONTR_DTL_TP.FLEET);
        paramMap.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        paramMap.put("space", SPACE);

        // START 2018/06/05 K.Kim [QC#25993,DEL]
        // paramMap.put("rspMeasPer", ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_RSP_TM_MEAS_PER, this.glblCmpyCd));
        // paramMap.put("rspRmd1", ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_RSP_TM_RMD_1, this.glblCmpyCd));
        // paramMap.put("rspRmd2", ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_RSP_TM_RMD_2, this.glblCmpyCd));
        // paramMap.put("slaRmd", ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_MAX_CMBN_SLA_RMD, this.glblCmpyCd));
        // paramMap.put("rspTmComit", ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_RSP_TM_COMIT, this.glblCmpyCd));
        // END 2018/06/05 K.Kim [QC#25993,DEL]

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getContractForMachineLevel", paramMap, execParam);

            rs = stmt.executeQuery();

            SVC_TERM_CONDTMsg svcTermCondMsg = null;

            ArrayList<SVC_TERM_CONDTMsg> setInsertSvcTermCondMsgList = new ArrayList<SVC_TERM_CONDTMsg>();
            ArrayList<SVC_TERM_CONDTMsg> setUpdateSvcTermCondMsgList = new ArrayList<SVC_TERM_CONDTMsg>();

            while (rs.next()) {
                if (!ZYPCommonFunc.hasValue(rs.getString("SVC_TERM_COND_PK"))) {
                    // set SVC_TERM_COND
                    svcTermCondMsg = setInsertSvcTermCond(rs);
                    setInsertSvcTermCondMsgList.add(svcTermCondMsg);
                } else {
                    // get SVC_TERM_COND
                    svcTermCondMsg = getUpdateSvcTermCond(rs.getBigDecimal("SVC_TERM_COND_PK"));
                    if (svcTermCondMsg == null) {
                        // insert SVC_BAT_ERR_LOG
                        insertErrorLog(rs.getBigDecimal("DS_CONTR_PK"), rs.getBigDecimal("DS_CONTR_DTL_PK"), rs.getBigDecimal("SVC_TERM_COND_ATTRB_PK"), rs.getBigDecimal("SVC_TERM_COND_PK"));
                        commit();
                        this.errorCount++;
                        continue;
                    }
                    setValue(svcTermCondMsg.svcTermAttrbMapValCd, rs.getString("TERM_COND_OPT_VAL_TXT"));
                    setUpdateSvcTermCondMsgList.add(svcTermCondMsg);
                }
            }

            ArrayList<SVC_TERM_CONDTMsg> svcTermCondInsertList = new ArrayList<SVC_TERM_CONDTMsg>();
            int insertCnt = 0;
            for (int i = 0; i < setInsertSvcTermCondMsgList.size(); i++) {

                svcTermCondInsertList.add(setInsertSvcTermCondMsgList.get(i));

                if (this.commitNumber == svcTermCondInsertList.size()) {
                    // insert SVC_TERM_COND
                    insertCnt = insertSvcTermCond(svcTermCondInsertList);
                    if (insertCnt != svcTermCondInsertList.size()) {
                        addErrMsg(NSAM0469E, new String[] {"SVC_TERM_COND" });
                        insertErrorLog(null, null, null, null);
                        this.errorCount = this.errorCount + (svcTermCondInsertList.size() - insertCnt);
                    }
                    commit();
                    this.normalCount = this.normalCount + insertCnt;
                    svcTermCondInsertList.clear();
                    insertCnt = 0;
                }
            }
            if (svcTermCondInsertList.size() != 0) {
                // insert SVC_TERM_COND
                insertCnt = insertSvcTermCond(svcTermCondInsertList);
                if (insertCnt != svcTermCondInsertList.size()) {
                    addErrMsg(NSAM0469E, new String[] {"SVC_TERM_COND" });
                    insertErrorLog(null, null, null, null);
                    this.errorCount = this.errorCount + (svcTermCondInsertList.size() - insertCnt);
                }
                commit();
                this.normalCount = this.normalCount + insertCnt;
            }

            ArrayList<SVC_TERM_CONDTMsg> svcTermCondUpdateList = new ArrayList<SVC_TERM_CONDTMsg>();
            int updateCnt = 0;
            for (int i = 0; i < setUpdateSvcTermCondMsgList.size(); i++) {

                svcTermCondUpdateList.add(setUpdateSvcTermCondMsgList.get(i));

                if (this.commitNumber == svcTermCondUpdateList.size()) {
                    // update SVC_TERM_COND
                    updateCnt = updateSvcTermCond(svcTermCondUpdateList);
                    if (updateCnt != svcTermCondUpdateList.size()) {
                        addErrMsg(NSAM0470E, new String[] {"SVC_TERM_COND" });
                        insertErrorLog(null, null, null, null);
                        this.errorCount = this.errorCount + (svcTermCondUpdateList.size() - updateCnt);
                    }
                    commit();
                    this.normalCount = this.normalCount + updateCnt;
                    svcTermCondUpdateList.clear();
                    updateCnt = 0;
                }
            }
            if (svcTermCondUpdateList.size() != 0) {
                // update SVC_TERM_COND
                updateCnt = updateSvcTermCond(svcTermCondUpdateList);
                if (updateCnt != svcTermCondUpdateList.size()) {
                    addErrMsg(NSAM0470E, new String[] {"SVC_TERM_COND" });
                    insertErrorLog(null, null, null, null);
                    this.errorCount = this.errorCount + (svcTermCondUpdateList.size() - updateCnt);
                }
                commit();
                this.normalCount = this.normalCount + updateCnt;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertSvcTermCond(ArrayList<SVC_TERM_CONDTMsg> inMsgLst) {
        SVC_TERM_CONDTMsg[] inMsgArray;
        inMsgArray = new SVC_TERM_CONDTMsg[inMsgLst.size()];
        return S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));
    }

    private int updateSvcTermCond(ArrayList<SVC_TERM_CONDTMsg> inMsgLst) {
        SVC_TERM_CONDTMsg[] inMsgArray;
        inMsgArray = new SVC_TERM_CONDTMsg[inMsgLst.size()];
        return S21FastTBLAccessor.update(inMsgLst.toArray(inMsgArray));
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
            // Send error mail
            postErrorMail();
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB053001().executeBatch(NSAB053001.class.getSimpleName());
    }

    private SVC_TERM_CONDTMsg setInsertSvcTermCond(ResultSet rs) throws SQLException {

        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
        setValue(inMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        setValue(inMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        setValue(inMsg.svcTermCondAttrbPk, rs.getBigDecimal("SVC_TERM_COND_ATTRB_PK"));
        setValue(inMsg.svcTermAttrbMapValCd, rs.getString("TERM_COND_OPT_VAL_TXT"));

        return inMsg;
    }

    private SVC_TERM_CONDTMsg getUpdateSvcTermCond(BigDecimal svcTermCondPk) {
        // get SVC_TERM_COND
        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcTermCondPk, svcTermCondPk);

        SVC_TERM_CONDTMsg outMsg = (SVC_TERM_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (outMsg == null) {
            addErrMsg(NZZM0003E, new String[] {"SVC_TERM_COND" });
            return null;
        }

        return outMsg;
    }

    private void insertErrorLog(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal svcTermCondAttrbPk, BigDecimal svcTermCondPk) {
        this.termCd = TERM_CD.WARNING_END;

        SVC_BAT_ERR_LOGTMsg inMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BAT_ERR_LOG_SQ));
        setValue(inMsg.bizAppId, BIZ_APP_ID);
        setValue(inMsg.svcBatErrLogTs, this.sysTime);

        if (ZYPCommonFunc.hasValue(dsContrPk)) {
            setValue(inMsg.svcBatErrKeyNum_01, String.valueOf(dsContrPk));
        }
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            setValue(inMsg.svcBatErrKeyNum_02, String.valueOf(dsContrDtlPk));
        }
        if (ZYPCommonFunc.hasValue(svcTermCondAttrbPk)) {
            setValue(inMsg.svcBatErrKeyNum_03, String.valueOf(svcTermCondAttrbPk));
        }
        if (ZYPCommonFunc.hasValue(svcTermCondPk)) {
            setValue(inMsg.svcBatErrKeyNum_04, String.valueOf(svcTermCondPk));
        }
        setValue(inMsg.svcBatErrKeyNm_01, DS_CONTR_PK);
        setValue(inMsg.svcBatErrKeyNm_02, DS_CONTR_DTL_PK);
        setValue(inMsg.svcBatErrKeyNm_03, SVC_TERM_COND_ATTRB_PK);
        setValue(inMsg.svcBatErrKeyNm_04, SVC_TERM_COND_PK);
        setValue(inMsg.svcBatErrMsgTxt, this.errMsg);
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(NSAM0469E, new String[] {"SVC_BAT_ERR_LOG" });
        }
    }

    private void postErrorMail() {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BIZ_APP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, this.mailTemplateId);
        if (template == null) {
            return;
        }
        template.setTemplateParameter("batchId", this.getClass().getSimpleName());
        template.setTemplateParameter("errDate", ZYPDateUtil.formatEzd17ToDisp(this.sysTime));

        StringBuilder msgBuf = new StringBuilder();
        for (String tmpErrMsg : this.errMsgList) {
            msgBuf.append(tmpErrMsg);
            msgBuf.append(ERR_MSG_CRLF);
            msgBuf.append(ERR_MSG_SPACE);
        }
        String errorMessage = msgBuf.toString();

        template.setTemplateParameter("message", errorMessage);

        // *****************************************************************
        // Post mail
        // *****************************************************************
        S21Mail mail;
        for (S21MailAddress addr : addrToList) {
            mail = new S21Mail(this.glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddress(addr);
            mail.setMailTemplate(template);
            mail.postMail();
        }
        return;
    }

    private void addErrMsg(String msgId, String... param) {
        this.errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        if (!this.errMsgList.contains(this.errMsg)) {
            this.errMsgList.add(errMsg);
        }
    }
}
