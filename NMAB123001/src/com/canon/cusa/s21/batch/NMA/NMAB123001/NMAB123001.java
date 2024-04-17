/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB123001;

import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.EIGHTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.EIGHTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.EIGHTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ELVTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ELVTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ELVTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FIFTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FIFTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FIFTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FIRST_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FIRST_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FIRST_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FRTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FRTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.FRTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.NINTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.NINTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.NINTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.PARAM_BIZ_AREA_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.PARAM_FST_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.PARAM_ORG_STRU_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SCD_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SCD_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SCD_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SIXTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SIXTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SIXTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SVC_BR_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SVC_BR_ORG_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SVNTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SVNTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.SVNTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.TENTH_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.TENTH_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.TENTH_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.THIRD_ORG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.THIRD_ORG_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.THIRD_ORG_TIER_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.VAL_2;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ZZZM9014E;
import static com.canon.cusa.s21.batch.NMA.NMAB123001.constant.NMAB123001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_SVC_BRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
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
 * Service Branch Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB123001 extends S21BatchMain {

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
        new NMAB123001().executeBatch(NMAB123001.class.getSimpleName());
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
            DMN_SVC_BRTMsg tMsg = new DMN_SVC_BRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Hierarchy Number
        Map<String, Object> param1 = new HashMap<String, Object>();
        param1.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param1.put(PARAM_ORG_STRU_TP_CD, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        param1.put(PARAM_BIZ_AREA_ORG_CD, BIZ_AREA_ORG.SERVICE);
        BigDecimal orgTierCd = (BigDecimal) ssmBatchClient.queryObject("getBrHierNum", param1);
        int hierNum = orgTierCd.intValue() + 1;

        // Set Query To Get Main Data
        String query = null;
        switch (hierNum) {
            case 2:
                query = "getMainDataSCD";
                break;
            case 3:
                query = "getMainDataTHIRD";
                break;
            case 4:
                query = "getMainDataFRTH";
                break;
            case 5:
                query = "getMainDataFIFTH";
                break;
            case 6:
                query = "getMainDataSIXTH";
                break;
            case 7:
                query = "getMainDataSVNTH";
                break;
            case 8:
                query = "getMainDataEIGHTH";
                break;
            case 9:
                query = "getMainDataNINTH";
                break;
            case 10:
                query = "getMainDataTENTH";
                break;
            case 11:
                query = "getMainDataELVTH";
                break;
            default:
                String errMsg = S21StringUtil.concatStrings("ORG_HRCH_STRU_DFN.ORG_TIER_CD = ", orgTierCd);
                throw new S21AbendException(ZZZM9026E, new String[] {errMsg});
        }

        // Get Main Data For DMN_SVC_BR Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param2 = new HashMap<String, Object>();
        param2.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param2.put(PARAM_TRGT_DT, this.trgtDt);
        param2.put(PARAM_FST_ORG_CD, VAL_2);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(query, param2, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To DMN_SVC_BR
                DMN_SVC_BRTMsg tMsg = mapData(rs);

                // Insert DMN_SVC_BR
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", SVC_BR_ORG_CD, "=", rs.getString(SVC_BR_ORG_CD));
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
     * Map Result Set Data To DMN_SVC_BR
     */
    private DMN_SVC_BRTMsg mapData(ResultSet rs) throws SQLException {
        DMN_SVC_BRTMsg tMsg = new DMN_SVC_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBrOrgCd, rs.getString(SVC_BR_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcBrOrgDescTxt, rs.getString(SVC_BR_ORG_DESC_TXT));
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
