/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB122001;

import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_BR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_BR_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_CC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_CC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_CMPY_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_EXTN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.COA_EXTN_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.EIGHTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ELVTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIFTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FIRST_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.FRTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.HR_SUPV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.HR_SUPV_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.NINTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_BOS;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_CURRENT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_FUTURE;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_PAST;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_SERVICE;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PSN_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PSN_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.PSN_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.RESRC_ASG_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.RESRC_ASG_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.RESRC_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.RESRC_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.RESRC_REV_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.RESRC_REV_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.REV_ACCT_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.REV_ACCT_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SCD_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SIXTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.SVNTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TENTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_MGR_FIRST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_MGR_LAST_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_MGR_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.THIRD_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB122001.constant.NMAB122001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_S21_ORGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * S21 Organization Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NMAB122001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NMAB122001().executeBatch(NMAB122001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialize
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.totalRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= DEFAULT_COMMIT_COUNT) {
            this.commitCount = DEFAULT_COMMIT_COUNT;
        }

        // Get Target date
        this.trgtDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.trgtDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_PROC_DATE });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            DMN_S21_ORGTMsg tMsg = new DMN_S21_ORGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For DMN_S21_ORG Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(PARAM_BOS, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        param.put(PARAM_SERVICE, BIZ_AREA_ORG.SERVICE);
        param.put(PARAM_PAST, GNRN_TP.PAST);
        param.put(PARAM_CURRENT, GNRN_TP.CURRENT);
        param.put(PARAM_FUTURE, GNRN_TP.FUTURE);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", param, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To DMN_S21_ORG
                DMN_S21_ORGTMsg tMsg = mapData(rs);

                // Insert DMN_S21_ORG
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", PSN_CD, "=", rs.getString(PSN_CD), ", ", TOC_CD, "=", rs.getString(TOC_CD));
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                    this.errRecCnt++;
                }

                // Commit By Commit Point
                wkInsertCount++;
                if (this.errRecCnt == 0 && wkInsertCount == this.commitCount) {
                    commit();
                    this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                    wkInsertCount = 0;
                }

            } // End Loop

            // Commit By Last Data
            if (this.errRecCnt == 0 && wkInsertCount > 0) {
                this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                commit();
            } else {
                rollback();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Map Result Set Data To DMN_S21_ORG
     */
    private DMN_S21_ORGTMsg mapData(ResultSet rs) throws SQLException {
        DMN_S21_ORGTMsg tMsg = new DMN_S21_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, rs.getString(PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, rs.getString(TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.psnNum, rs.getString(PSN_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnFirstNm, rs.getString(PSN_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.psnLastNm, rs.getString(PSN_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.resrcEffFromDt, rs.getString(RESRC_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.resrcEffThruDt, rs.getString(RESRC_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.hrSupvId, rs.getString(HR_SUPV_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.hrSupvNm, rs.getString(HR_SUPV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.revAcctTpCd, rs.getString(REV_ACCT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.revAcctTpDescTxt, rs.getString(REV_ACCT_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyCd, rs.getString(COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyDescTxt, rs.getString(COA_CMPY_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaExtnCd, rs.getString(COA_EXTN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaExtnDescTxt, rs.getString(COA_EXTN_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, rs.getString(COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrDescTxt, rs.getString(COA_BR_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, rs.getString(COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCcDescTxt, rs.getString(COA_CC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.resrcRevEffFromDt, rs.getString(RESRC_REV_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.resrcRevEffThruDt, rs.getString(RESRC_REV_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgMgrPsnCd, rs.getString(FIRST_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgMgrTocCd, rs.getString(FIRST_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgMgrFirstNm, rs.getString(FIRST_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgMgrLastNm, rs.getString(FIRST_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgMgrPsnCd, rs.getString(SCD_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgMgrTocCd, rs.getString(SCD_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgMgrFirstNm, rs.getString(SCD_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgMgrLastNm, rs.getString(SCD_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgMgrPsnCd, rs.getString(THIRD_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgMgrTocCd, rs.getString(THIRD_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgMgrFirstNm, rs.getString(THIRD_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgMgrLastNm, rs.getString(THIRD_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgMgrPsnCd, rs.getString(FRTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgMgrTocCd, rs.getString(FRTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgMgrFirstNm, rs.getString(FRTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgMgrLastNm, rs.getString(FRTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgMgrPsnCd, rs.getString(FIFTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgMgrTocCd, rs.getString(FIFTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgMgrFirstNm, rs.getString(FIFTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgMgrLastNm, rs.getString(FIFTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgMgrPsnCd, rs.getString(SIXTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgMgrTocCd, rs.getString(SIXTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgMgrFirstNm, rs.getString(SIXTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgMgrLastNm, rs.getString(SIXTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgMgrPsnCd, rs.getString(SVNTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgMgrTocCd, rs.getString(SVNTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgMgrFirstNm, rs.getString(SVNTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgMgrLastNm, rs.getString(SVNTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgMgrPsnCd, rs.getString(EIGHTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgMgrTocCd, rs.getString(EIGHTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgMgrFirstNm, rs.getString(EIGHTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgMgrLastNm, rs.getString(EIGHTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgMgrPsnCd, rs.getString(NINTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgMgrTocCd, rs.getString(NINTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgMgrFirstNm, rs.getString(NINTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgMgrLastNm, rs.getString(NINTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgMgrPsnCd, rs.getString(TENTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgMgrTocCd, rs.getString(TENTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgMgrFirstNm, rs.getString(TENTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgMgrLastNm, rs.getString(TENTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgMgrPsnCd, rs.getString(ELVTH_ORG_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgMgrTocCd, rs.getString(ELVTH_ORG_MGR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgMgrFirstNm, rs.getString(ELVTH_ORG_MGR_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgMgrLastNm, rs.getString(ELVTH_ORG_MGR_LAST_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.resrcAsgEffFromDt, rs.getString(RESRC_ASG_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.resrcAsgEffThruDt, rs.getString(RESRC_ASG_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgCd, rs.getString(FIRST_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgNm, rs.getString(FIRST_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgCd, rs.getString(SCD_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgNm, rs.getString(SCD_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgCd, rs.getString(THIRD_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgNm, rs.getString(THIRD_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgCd, rs.getString(FRTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgNm, rs.getString(FRTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgCd, rs.getString(FIFTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgNm, rs.getString(FIFTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgCd, rs.getString(SIXTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgNm, rs.getString(SIXTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgCd, rs.getString(SVNTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgNm, rs.getString(SVNTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgCd, rs.getString(EIGHTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgNm, rs.getString(EIGHTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgCd, rs.getString(NINTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgNm, rs.getString(NINTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgCd, rs.getString(TENTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgNm, rs.getString(TENTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgCd, rs.getString(ELVTH_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgNm, rs.getString(ELVTH_ORG_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOrgTierCd, rs.getString(FIRST_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOrgTierCd, rs.getString(SCD_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdOrgTierCd, rs.getString(THIRD_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frthOrgTierCd, rs.getString(FRTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthOrgTierCd, rs.getString(FIFTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthOrgTierCd, rs.getString(SIXTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthOrgTierCd, rs.getString(SVNTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.eighthOrgTierCd, rs.getString(EIGHTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ninthOrgTierCd, rs.getString(NINTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.tenthOrgTierCd, rs.getString(TENTH_ORG_TIER_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.elvthOrgTierCd, rs.getString(ELVTH_ORG_TIER_CD));
        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

}
