/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB043001;

import static com.canon.cusa.s21.batch.NSA.NSAB043001.constant.NSAB043001Constant.*;
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

import parts.dbcommon.EZDTBLAccessor;
import business.db.CFS_BAT_PROCTMsg;
import business.db.CFS_CONTR_DTLTMsg;
import business.db.CFS_CONTR_DTLTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Hitachi         O.Okuma         Create          N/A
 * 2016/06/07   Hitachi         O.Okuma         Update          QC#8974
 * 2016/09/28   Hitachi         N.Arai          Update          QC#12670
 * 2017/03/24   Hitachi         N.Arai          Update          QC#17945
 * 2017/05/11   Hitachi         K.Kitachi       Update          QC#18446
 * 2017/06/19   Hitachi         T.Mizuki        Update          QC#19256
 * 2017/08/01   Hitachi         K.Kojima        Update          QC#20363
 *</pre>
 */
public class NSAB043001 extends S21BatchMain {

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Commit Number */
    private int commitNumber = 0;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global company code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Last Update Timestamp */
    private String lastUpdTs = null;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

// START 2016/09/28 N.Arai [QC#12670, MOD]
    /** cfs bat proc pk */
    private BigDecimal cfsBatProcPkSq = null;
// END 2016/09/28 N.Arai [QC#12670, MOD]

    // mod start 2017/03/24 CSA QC#17945
     /** isBaseChrg */
     private Boolean isBaseChrg = false;
     // mod end 2017/03/24 CSA QC#17945

    // START 2017/05/11 K.Kitachi [QC#18446, ADD]
    String[] dsAcctDlrCdList = null;
    // END 2017/05/11 K.Kitachi [QC#18446, ADD]

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB043001().executeBatch(NSAB043001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSZM0392E, new String[] {"GLBL_CMPY_CD" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= FETCH_SIZE_MAX) {
            this.commitNumber = FETCH_SIZE_MAX;
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSZM0392E, new String[] {"Sales Date" });
        }

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // START 2017/05/11 K.Kitachi [QC#18446, ADD]
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(CSA_DEALER_CODE, this.glblCmpyCd);
        this.dsAcctDlrCdList = constVal.split(COMMA);
        // END 2017/05/11 K.Kitachi [QC#18446, ADD]
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

            getDsBizProcLog();

            ps = this.ssmLLClient.createPreparedStatement("getTargetData", getTargetDataParam(), execParam);
            rs = ps.executeQuery();

            deleteInvalidData();

            BigDecimal dsContrPk = BigDecimal.ZERO;
            // mod start 2017/03/24 CSA QC#17945
            BigDecimal dsContrDtlPk = BigDecimal.ZERO;
            this.isBaseChrg = false;
            // mod end 2017/03/24 CSA QC#17945
            boolean isNormal = true;
            int rowCount = 0;
            while (rs.next()) {

                if (dsContrPk.compareTo(rs.getBigDecimal(DS_CONTR_PK)) != 0) {
                    if (isNormal) {
                        normalCount = normalCount + rowCount;
                        commit();
                    } else {
                        errorCount = errorCount + rowCount;
                        rollback();
                    }

                    dsContrPk = rs.getBigDecimal(DS_CONTR_PK);
                    rowCount = 0;
                    isNormal = true;

                    deleteCfsContrDtlToDsContrPk(dsContrPk);
                }

                // mod start 2017/03/24 CSA QC#17945
                // START 2017/05/11 K.Kitachi [QC#18446, MOD]
//                if (dsContrDtlPk.compareTo(rs.getBigDecimal(DS_CONTR_DTL_PK)) != 0) {
                if (dsContrDtlPk.compareTo(rs.getBigDecimal(DS_CONTR_DTL_PK)) != 0 && isLeaseCompany(rs.getString(DS_ACCT_DLR_CD))) {
                // END 2017/05/11 K.Kitachi [QC#18446, MOD]
                    dsContrDtlPk = rs.getBigDecimal(DS_CONTR_DTL_PK);
                    this.isBaseChrg = true;
                }
                // mod start 2017/03/24 CSA QC#17945
                setLastUpdateTs(rs);
                // START 2017/05/11 K.Kitachi [QC#18446, MOD]
//                List<Map<String, Object>> insList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRegistData", getRegistDataParam(rs));
                List<Map<String, Object>> insList;
                if (hasValue(rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK))) {
                    insList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRegistData", getRegistDataParam(rs));
                } else {
                    insList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRegistDataForBaseChrg", getRegistDataParam(rs));
                }
                // END 2017/05/11 K.Kitachi [QC#18446, MOD]

                if (insList == null || insList.size() == 0) {
                    continue;
                }

                if (isNormal) {
                    isNormal = insertCfsContrDtl(insList);
                }
                rowCount = rowCount + insList.size();
            }
            if (isNormal) {
                normalCount = normalCount + rowCount;
                commit();
            } else {
                errorCount = errorCount + rowCount;
                rollback();
            }

            if (hasValue(dsBizProcLogTMsg.dsBizProcLogPk)) {
                isNormal = updateDsBizProcLog();
            } else {
                isNormal = insertDsBizProcLog();
            }

            if (isNormal) {
                commit();
            } else {
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
        setValue(insTMsg.cfsBatTpCd, CFS_BAT_TP.INSTALL_BASE);
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

    private void deleteInvalidData() throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        ps = this.ssmLLClient.createPreparedStatement("getDelData", getDelDataParam(), execParam);
        rs = ps.executeQuery();

        while (rs.next()) {
            deleteCfsContrDtl(rs.getBigDecimal(CFS_CONTR_DTL_PK));
        }
    }

    private void getDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", PROGRAM_ID);

        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
        } else {
            dsBizProcLogTMsg = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
        }
    }

    private void deleteCfsContrDtlToDsContrPk(BigDecimal dsContrPk) {

        CFS_CONTR_DTLTMsg inMsg = new CFS_CONTR_DTLTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);

        CFS_CONTR_DTLTMsgArray tMsgAry = (CFS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return;
        }

        for (int i = 0; i < tMsgAry.getValidCount(); i++) {
            deleteCfsContrDtl(tMsgAry.no(i).cfsContrDtlPk.getValue());
        }
    }

    private void deleteCfsContrDtl(BigDecimal cfsContrDtlPk) {

        CFS_CONTR_DTLTMsg inMsg = new CFS_CONTR_DTLTMsg();

        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.cfsContrDtlPk, cfsContrDtlPk);

        CFS_CONTR_DTLTMsg tMsg = (CFS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

        if (tMsg == null) {
            return;
        }

        EZDTBLAccessor.logicalRemove(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            String message = CFS_CONTR_DTL_PK + " = " + tMsg.cfsContrDtlPk.getValue().toString();
            this.errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0475E, new String[] {TBL_NM_CFS_CONTR_DTL, message }));
        }
    }

    private Map<String, Object> getDelDataParam() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("dsContrEdi_cfs", DS_CONTR_EDI.CFS);
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);

        return params;
    }

    private Map<String, Object> getTargetDataParam() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("updTs_init", TS_ZERO);
        params.put("dsContrEdi_cfs", DS_CONTR_EDI.CFS);
        params.put("lastUpdTs", dsBizProcLogTMsg.dsBizLastUpdTs.getValue());
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // START 2017/05/11 K.Kitachi [QC#18446, ADD]
        params.put("dsAcctDlrCdList", this.dsAcctDlrCdList);
        // END 2017/05/11 K.Kitachi [QC#18446, ADD]
        // START 2017/06/19 T.Mizuki [QC#19256,ADD]
        params.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 T.Mizuki [QC#19256,ADD]
        // START 2017/08/01 K.Kojima [QC#20212,ADD]
        params.put("dsContrCatgFleet", DS_CONTR_CATG.FLEET);
        // END 2017/08/01 K.Kojima [QC#20212,ADD]

        return params;
    }

    private Map<String, Object> getRegistDataParam(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", rs.getBigDecimal(DS_CONTR_DTL_PK));
        params.put("svcMachMstrPk", rs.getBigDecimal(SVC_MACH_MSTR_PK));
        params.put("dsContrBllgMtrPk", rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK));
        params.put("exclCfsContrSts", ZYPCodeDataUtil.getVarCharConstValue(EXCL_CFS_CONTR_STS, glblCmpyCd));
        params.put("exclCfsContrDtlSts", ZYPCodeDataUtil.getVarCharConstValue(EXCL_CFS_CONTR_DTL_STS, glblCmpyCd));
        params.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // mod start 2017/03/24 CSA QC#17945
        params.put("dsContrPk", rs.getBigDecimal(DS_CONTR_PK));
        params.put("dsContrNum", rs.getString(DS_CONTR_NUM));
        params.put("serNum", rs.getString(SER_NUM));
        if (DS_CONTR_CATG.FLEET.equals(rs.getString(DS_CONTR_CATG_CD))) {
            params.put("cfsFleetFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("cfsFleetFlg", ZYPConstant.FLG_OFF_N);
        }
        // mod end 2017/03/24 CSA QC#17945
        // START 2017/06/19 T.Mizuki [QC#19256,ADD]
        params.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/19 T.Mizuki [QC#19256,ADD]

        return params;
    }

    private boolean insertCfsContrDtl(List<Map<String, Object>> insList) {

        for (Map<String, Object> insMap : insList) {

            CFS_CONTR_DTLTMsg tMsg = new CFS_CONTR_DTLTMsg();

            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.cfsContrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CONTR_DTL_SQ));
            setValue(tMsg.dsContrPk, (BigDecimal) insMap.get(DS_CONTR_PK));
            setValue(tMsg.dsContrNum, (String) insMap.get(DS_CONTR_NUM));
            setValue(tMsg.cfsLeaseNum, (String) insMap.get(CFS_LEASE_NUM));
            setValue(tMsg.dsContrDtlPk, (BigDecimal) insMap.get(DS_CONTR_DTL_PK));
            setValue(tMsg.dsContrCatgCd, (String) insMap.get(DS_CONTR_CATG_CD));
            setValue(tMsg.locNm, (String) insMap.get(LOC_NM));
            setValue(tMsg.dsAcctDlrCd, (String) insMap.get(DS_ACCT_DLR_CD));
            // mod start 2017/03/24 CSA QC#17945
//            setValue(tMsg.baseChrgFlg, (String) insMap.get(BASE_CHRG_FLG));
            setValue(tMsg.usgChrgFlg, (String) insMap.get(USG_CHRG_FLG));
            setValue(tMsg.dsContrBllgMtrPk, (BigDecimal) insMap.get(DS_CONTR_BLLG_MTR_PK));
            setValue(tMsg.mtrLbCd, (String) insMap.get(BLLG_MTR_LB_CD));
//            setValue(tMsg.baseBllgCycleCd, (String) insMap.get(BASE_BLLG_CYCLE_CD));
            setValue(tMsg.bllgMtrBllgCycleCd, (String) insMap.get(BLLG_MTR_BLLG_CYCLE_CD));
//            setValue(tMsg.basePrcDealAmt, (BigDecimal) insMap.get(BASE_PRC_DEAL_AMT));
            if  (this.isBaseChrg) {
                setValue(tMsg.baseChrgFlg, (String) insMap.get(BASE_CHRG_FLG));
                setValue(tMsg.baseBllgCycleCd, (String) insMap.get(BASE_BLLG_CYCLE_CD));
                setValue(tMsg.basePrcDealAmt, (BigDecimal) insMap.get(BASE_PRC_DEAL_AMT));
            } else {
                setValue(tMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
            }
            // mod end 2017/03/24 CSA QC#17945

            setValue(tMsg.lastUpdTs, this.lastUpdTs);

            setValue(tMsg.contrEffFromDt, (String) insMap.get(CONTR_EFF_FROM_DT));
            setValue(tMsg.contrEffThruDt, (String) insMap.get(CONTR_EFF_THRU_DT));
            setValue(tMsg.contrVrsnEffFromDt, (String) insMap.get(CONTR_VRSN_EFF_FROM_DT));
            setValue(tMsg.contrVrsnEffThruDt, (String) insMap.get(CONTR_VRSN_EFF_THRU_DT));
            setValue(tMsg.contrCloDt, (String) insMap.get(CONTR_CLO_DT));
            setValue(tMsg.contrMtrMultRate, (BigDecimal) insMap.get(CONTR_MTR_MULT_RATE));

            setXsMtr(tMsg, tMsg.dsContrBllgMtrPk.getValue());

            if (DS_CONTR_CATG.FLEET.equals(tMsg.dsContrCatgCd.getValue()) && !hasValue((String) insMap.get(SER_NUM))) {
                setValue(tMsg.serNum, getSerNumForFlt((String) insMap.get(DS_CONTR_CATG_ABBR_NM), (String) insMap.get(DS_CONTR_NUM)));
            } else {
                setValue(tMsg.svcMachMstrPk, (BigDecimal) insMap.get(SVC_MACH_MSTR_PK));
                setValue(tMsg.serNum, (String) insMap.get(SER_NUM));
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
                setValue(tMsg.aggrContrNum, tMsg.dsContrNum);
            }

            if (DS_CONTR_STS.TERMINATED.equals((String) insMap.get(DS_CONTR_STS_CD))) {
                setValue(tMsg.contrTrmnDt, ZYPCommonFunc.subByteString(lastUpdTs, 0, LENGTH_8));
            }

            if (ZYPConstant.FLG_ON_Y.equals((String) insMap.get(COLOR_MTR_FLG))) {
                setValue(tMsg.mtrTpTxt, MTR_TP_IND_TXT_C);
            } else {
                setValue(tMsg.mtrTpTxt, MTR_TP_IND_TXT_B);
            }
            S21FastTBLAccessor.insert(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String message = DS_CONTR_PK + " = " + tMsg.dsContrPk.getValue();
                this.errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0469E, new String[] {TBL_NM_CFS_CONTR_DTL, message }));
                return false;
            }
            // mod start 2017/03/24 CSA QC#17945
            this.isBaseChrg = false;
            // mod end 2017/03/24 CSA QC#17945
        }
        return true;
    }

    private void setXsMtr(CFS_CONTR_DTLTMsg tMsg, BigDecimal dsContrBllgMtrPk) {

        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();

        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);

        CONTR_XS_COPYTMsgArray tMsgAry = (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return;
        }

        int i = 0;
        if (ZYPConstant.FLG_ON_Y.equals(tMsgAry.no(i).xsMtrFirstFlg.getValue())) {
            setValue(tMsg.xsMtrCopyQty_01, tMsgAry.no(i).xsMtrCopyQty);
            setValue(tMsg.xsMtrAmtRate_01, tMsgAry.no(i++).xsMtrAmtRate);
        } else {
            i++;
        }

        if (tMsgAry.getValidCount() > i) {
            setValue(tMsg.xsMtrCopyQty_02, tMsgAry.no(i).xsMtrCopyQty);
            setValue(tMsg.xsMtrAmtRate_02, tMsgAry.no(i++).xsMtrAmtRate);
        }

        if (tMsgAry.getValidCount() > i) {
            setValue(tMsg.xsMtrCopyQty_03, tMsgAry.no(i).xsMtrCopyQty);
            setValue(tMsg.xsMtrAmtRate_03, tMsgAry.no(i++).xsMtrAmtRate);
        }

        if (tMsgAry.getValidCount() > i) {
            setValue(tMsg.xsMtrCopyQty_04, tMsgAry.no(i).xsMtrCopyQty);
            setValue(tMsg.xsMtrAmtRate_04, tMsgAry.no(i++).xsMtrAmtRate);
        }
    }

    private boolean insertDsBizProcLog() {

        setValue(dsBizProcLogTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        setValue(dsBizProcLogTMsg.procPgmId, PROGRAM_ID);
        setValue(dsBizProcLogTMsg.dsBizProcDt, this.slsDt);
        setValue(dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.insert(dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsBizProcLogTMsg.getReturnCode())) {
            String message = PROC_PGM_ID + " = " + dsBizProcLogTMsg.procPgmId.getValue();
            this.errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0469E, new String[] {TBL_NM_DS_BIZ_PROC_LOG, message }));
            return false;
        }
        return true;
    }

    private boolean updateDsBizProcLog() {

        setValue(dsBizProcLogTMsg.dsBizProcDt, this.slsDt);
        setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.update(dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsBizProcLogTMsg.getReturnCode())) {
            String message = DS_BIZ_PROC_LOG_PK + " = " + dsBizProcLogTMsg.dsBizProcLogPk.getValue();
            this.errMsgList.add(S21MessageFunc.clspGetMessage(NSAM0470E, new String[] {TBL_NM_DS_BIZ_PROC_LOG, message }));
            return false;
        }
        return true;
    }

    private void setLastUpdateTs(ResultSet rs) throws SQLException {

        String updateTs = rs.getString(EZUPTIME_01);

        this.lastUpdTs = null;

        if (updateTs.compareTo(rs.getString(EZUPTIME_02)) < 0) {
            updateTs = rs.getString(EZUPTIME_02);
        }
        if (updateTs.compareTo(rs.getString(EZUPTIME_03)) < 0) {
            updateTs = rs.getString(EZUPTIME_03);
        }
        if (updateTs.compareTo(rs.getString(EZUPTIME_04)) < 0) {
            updateTs = rs.getString(EZUPTIME_04);
        }
        if (updateTs.compareTo(rs.getString(EZUPTIME_05)) < 0) {
            updateTs = rs.getString(EZUPTIME_05);
        }
        if (updateTs.compareTo(rs.getString(EZUPTIME_06)) < 0) {
            updateTs = rs.getString(EZUPTIME_06);
        }
        if (updateTs.compareTo(rs.getString(EZUPTIME_07)) < 0) {
            updateTs = rs.getString(EZUPTIME_07);
        }
        if (updateTs.compareTo(rs.getString(EZUPTIME_08)) < 0) {
            updateTs = rs.getString(EZUPTIME_08);
        }
        this.lastUpdTs = updateTs;
    }

    private String getSerNumForFlt(String dsContrCatgAbbrNm, String dsContrNum) {

        StringBuilder contrNumBldr = new StringBuilder(dsContrCatgAbbrNm);
        contrNumBldr.append("_");
        contrNumBldr.append(dsContrNum);
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
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, BUSINESS_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, SET_MAIL_TEMPLATE_ID);
        if (template == null) {
            return;
        }
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, this.getClass().getSimpleName());
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, this.currentSystemTs);

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

    // START 2017/05/11 K.Kitachi [QC#18446, ADD]
    private boolean isLeaseCompany(String inVal) {
        if (!hasValue(inVal)) {
            return false;
        }
        for (String dsAcctDlrCd : this.dsAcctDlrCdList) {
            if (dsAcctDlrCd.equals(inVal)) {
                return true;
            }
        }
        return false;
    }
    // END 2017/05/11 K.Kitachi [QC#18446, ADD]
}
