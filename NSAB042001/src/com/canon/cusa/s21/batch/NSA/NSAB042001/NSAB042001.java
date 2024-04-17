/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB042001;

import static com.canon.cusa.s21.batch.NSA.NSAB042001.constant.NSAB042001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CFS_BAT_PROCTMsg;
import business.db.CFS_CR_REBIL_MTR_READTMsg;
import business.db.SVC_INVTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_LINK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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

/**
 *<pre>
 * CFS Credit / Rebill Interface
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Hitachi         O.Okuma         Create          N/A
 * 2016/09/28   Hitachi         N.Arai          Update          QC#12670
 * 2017/07/24   Hitachi         K.Kojima        Update          QC#20090
 * 2017/11/16   Hitachi         K.Yamada        Update          QC#21812
 *</pre>
 */
public class NSAB042001 extends S21BatchMain {

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Commit Number */
    private int commitNumber;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global company code */
    private String glblCmpyCd = "";

    /** Current System Timestamp */
    private String currentSystemTs = null;

// START 2016/09/28 N.Arai [QC#12670, MOD]
    /** cfs bat proc pk */
    private BigDecimal cfsBatProcPkSq = null;

    /** Sales Date */
    private String slsDt = null;
// END 2016/09/28 N.Arai [QC#12670, MOD]

    // START 2017/07/24 K.Kojima [QC#20090,ADD]
    String[] dsAcctDlrCdList = null;
    // END 2017/07/24 K.Kojima [QC#20090,ADD]

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB042001().executeBatch(NSAB042001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSZM0392E, new String[] {"GLBL_CMPY_CD" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= FETCH_SIZE_MAX) {
            this.commitNumber = FETCH_SIZE_MAX;
        }
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

// START 2016/09/28 N.Arai [QC#12670, MOD]
        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E);
        }
// END 2016/09/28 N.Arai [QC#12670, MOD]

        // START 2017/07/24 K.Kojima [QC#20090,ADD]
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CSA_DEALER_CODE, this.glblCmpyCd);
        this.dsAcctDlrCdList = constVal.split(COMMA);
        // END 2017/07/24 K.Kojima [QC#20090,ADD]

    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {

// START 2016/09/28 N.Arai [QC#12670, MOD]
        String rtnUpdateCd = null;
        if (this.errMsgList.size() > 0) {
            termCd = TERM_CD.WARNING_END;
            postErrorMail();
            rtnUpdateCd = updateCfsBatProc(CFS_PROC_STS.ERROR);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnUpdateCd)) {
                this.termCd = TERM_CD.ABNORMAL_END;
                throw new S21AbendException(NSAM0031E, new String[] {"CFS_BAT_PROC"});
            }
            commit();
        } else {
            rtnUpdateCd = updateCfsBatProc(ZYPCodeDataUtil.getVarCharConstValue(CFS_PROC_CPLT_STS_CD, glblCmpyCd));
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnUpdateCd)) {
                this.termCd = TERM_CD.ABNORMAL_END;
                throw new S21AbendException(NSAM0031E, new String[] {"CFS_BAT_PROC"});
            }
            commit();
// END 2016/09/28 N.Arai [QC#12670, MOD]
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    private void doProcess() {

// START 2016/09/28 N.Arai [QC#12670, MOD]
        this.cfsBatProcPkSq = ZYPOracleSeqAccessor.getNumberBigDecimal("CFS_BAT_PROC_SQ");
        String rtnInsertCd = insertCfsBatProc();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnInsertCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0032E, new String[] {"CFS_BAT_PROC"});
        }
        commit();
// END 2016/09/28 N.Arai [QC#12670, MOD]

        PreparedStatement ps = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            ps = this.ssmLLClient.createPreparedStatement("getSvcInvInfo", getSvcInvInfoParam(), execParam);
            rs = ps.executeQuery();

            String svcInvNum = "";
            boolean isNormal = true;
            int rowCount = 0;
            while (rs.next()) {

                if (!svcInvNum.equals(rs.getString(SVC_INV_NUM))) {
                    if (isNormal) {
                        normalCount = normalCount + rowCount;
                        commit();
                    } else {
                        errorCount = errorCount + rowCount;
                        rollback();
                    }

                    svcInvNum = rs.getString(SVC_INV_NUM);
                    rowCount = 0;
                    isNormal = true;

                    if (!hasValue(rs.getString(DS_CONTR_PK)) || !hasValue(rs.getString(SVC_INV_LINE_PK))) {
                        isNormal = updateSvcInv(rs, CFS_LINK_STS.N_OR_A);

                        if (!isNormal) {
                            rowCount++;
                        }
                        continue;
                    }
                }

                if (isNormal) {
                    isNormal = insertCfsCrRebilMtrRead(rs);
                }
                if (isNormal) {
                    isNormal = updateSvcInv(rs, CFS_LINK_STS.COMPLETED);
                }

                rowCount++;
            }
            if (isNormal) {
                normalCount = normalCount + rowCount;
                commit();
            } else {
                errorCount = errorCount + rowCount;
                rollback();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

// START 2016/09/28 N.Arai [QC#12670, ADD]
    /**
     * insertCfsBatProc
     * @return String
     */
    private String insertCfsBatProc() {

        CFS_BAT_PROCTMsg insTMsg = new CFS_BAT_PROCTMsg();

        setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(insTMsg.cfsBatProcPk, this.cfsBatProcPkSq);
        setValue(insTMsg.cfsBatId, PROGRAM_ID);
        setValue(insTMsg.cfsBatTpCd, CFS_BAT_TP.CREDIT_AND_REBILL);
        setValue(insTMsg.cfsBatProcStsCd, CFS_PROC_STS.IN_COMPLETED);
        setValue(insTMsg.cfsBatProcDt, this.slsDt);
        setValue(insTMsg.cfsBatProcTs, this.currentSystemTs);

        S21FastTBLAccessor.insert(insTMsg);
        return insTMsg.getReturnCode();

    }

    /**
     * updateCfsBatProc
     * @param procStsCd 
     * @return String
     */
    private String updateCfsBatProc(String procStsCd) {

        CFS_BAT_PROCTMsg inTMsg = new CFS_BAT_PROCTMsg();

        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.cfsBatProcPk, this.cfsBatProcPkSq);

        CFS_BAT_PROCTMsg updTMsg = (CFS_BAT_PROCTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);

        setValue(updTMsg.cfsBatProcStsCd, procStsCd);

        S21FastTBLAccessor.update(updTMsg);
        return updTMsg.getReturnCode();

    }
// END 2016/09/28 N.Arai [QC#12670, ADD]

    private Map<String, Object> getSvcInvInfoParam() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cfsLinkStsCd_inCompleted", CFS_LINK_STS.IN_COMPLETED);
        params.put("cfsLinkStsCd_error", CFS_LINK_STS.ERROR);
        params.put("svcInvLineTpCd_fleet", SVC_INV_LINE_TP.FLEET);
        params.put("svcInvLineTpCd_machine", SVC_INV_LINE_TP.MACHINE);
        params.put("dsContrEdi_cfs", DS_CONTR_EDI.CFS);
        // START 2017/07/24 K.Kojima [QC#20090,ADD]
        params.put("dsAcctDlrCdList", this.dsAcctDlrCdList);
        // END 2017/07/24 K.Kojima [QC#20090,ADD]
        // START 2017/11/16 [QC#21812,ADD]
        params.put("invoice", INV_TP.INVOICE);
        params.put("debitMemo", INV_TP.DEBIT_MEMO);
        // END 2017/11/16 [QC#21812,ADD]

        return params;
    }

    private boolean insertCfsCrRebilMtrRead(ResultSet rs) throws SQLException {

        CFS_CR_REBIL_MTR_READTMsg tMsg = new CFS_CR_REBIL_MTR_READTMsg();

        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.cfsCrRebilMtrReadPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CR_REBIL_MTR_READ_SQ));
        setValue(tMsg.dsAcctDlrCd, rs.getString(DS_ACCT_DLR_CD));
        setValue(tMsg.dsContrPk, rs.getBigDecimal(DS_CONTR_PK));
        setValue(tMsg.dsContrNum, rs.getString(DS_CONTR_NUM));
        setValue(tMsg.cfsLeaseNum, rs.getString(CFS_LEASE_NUM));
        setValue(tMsg.svcInvNum, rs.getString(SVC_INV_NUM));
        setValue(tMsg.origSvcInvNum, rs.getString(ORIG_SVC_INV_NUM));
        setValue(tMsg.origSvcInvLinePk, rs.getBigDecimal(ORIG_SVC_INV_LINE_PK));
        setValue(tMsg.svcInvLinePk, rs.getBigDecimal(SVC_INV_LINE_PK));
        setValue(tMsg.bllgPerFromDt, rs.getString(BLLG_PER_FROM_DT));
        setValue(tMsg.bllgPerThruDt, rs.getString(BLLG_PER_THRU_DT));
        setValue(tMsg.oldTotCopyQty, rs.getBigDecimal(OLD_TOT_COPY_QTY));
        setValue(tMsg.oldPrevTotCopyQty, rs.getBigDecimal(OLD_PREV_TOT_COPY_QTY));
        setValue(tMsg.oldTestCopyQty, rs.getBigDecimal(OLD_TEST_COPY_QTY));
        setValue(tMsg.newTotCopyQty, rs.getBigDecimal(NEW_TOT_COPY_QTY));
        setValue(tMsg.newPrevTotCopyQty, rs.getBigDecimal(NEW_PREV_TOT_COPY_QTY));
        setValue(tMsg.newTestCopyQty, rs.getBigDecimal(NEW_TEST_COPY_QTY));
        setValue(tMsg.mtrReadDt, rs.getString(MTR_READ_DT));
        setValue(tMsg.dsContrCatgCd, rs.getString(DS_CONTR_CATG_CD));
        setValue(tMsg.origSvcInvLineMtrPk, rs.getBigDecimal(ORIG_SVC_INV_LINE_MTR_PK));
        setValue(tMsg.svcInvLineMtrPk, rs.getBigDecimal(SVC_INV_LINE_MTR_PK));
        setValue(tMsg.dsContrBllgMtrPk, rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK));
        setValue(tMsg.bllgMtrLbCd, rs.getString(MTR_LB_CD));
        setValue(tMsg.cratTs, currentSystemTs);
        setValue(tMsg.custCareTktNum, rs.getString(CUST_CARE_TKT_NUM));

        if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) && !hasValue(rs.getString(SER_NUM))) {
            setValue(tMsg.serNum, getSerNumForFlt(rs));
        } else {
            setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
            setValue(tMsg.serNum, rs.getString(SER_NUM));
        }

        S21FastTBLAccessor.insert(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            String message = SVC_INV_NUM + " = " + tMsg.svcInvNum.getValue();
            this.errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0469E, new String[] {CFS_CR_REBIL_MTR_READ, message }));
            return false;
        }
        return true;
    }

    private boolean updateSvcInv(ResultSet rs, String cfsLinkStsCd) throws SQLException {

        SVC_INVTMsg inTMsg = new SVC_INVTMsg();

        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.svcInvPk, rs.getBigDecimal(SVC_INV_PK));

        SVC_INVTMsg updTMsg = (SVC_INVTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);

        setValue(updTMsg.cfsLinkStsCd, cfsLinkStsCd);

        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            String message = SVC_INV_PK + " = " + updTMsg.svcInvPk.getValue();
            this.errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0470E, new String[] {TBL_NM_SVC_INV, message }));
            return false;
        }
        return true;
    }

    private String getSerNumForFlt(ResultSet rs) throws SQLException {

        StringBuilder contrNumBldr = new StringBuilder(rs.getString(DS_CONTR_CATG_ABBR_NM));
        contrNumBldr.append("_");
        contrNumBldr.append(rs.getString(DS_CONTR_NUM));
        return contrNumBldr.toString();
    }

    private void postErrorMail() {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BUSINESS_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, SET_MAIL_TEMPLATE_ID);
        if (template == null) {
            return;
        }
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, this.getClass().getSimpleName());
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, currentSystemTs);

        StringBuilder msgBuf = new StringBuilder();
        for (String errMsg : this.errMsgList) {
            msgBuf.append(errMsg);
            msgBuf.append(ERR_MSG_CRLF);
        }
        String errorMessage = msgBuf.toString();

        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, errorMessage);

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
}
