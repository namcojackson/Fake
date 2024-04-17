/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB118001;

import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ASSET_RECOV_COST_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ASSET_RECOV_COST_AMT_UPD_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.COA_MDSE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.COA_MDSE_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.COA_PROD_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.COA_PROD_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.COGS_COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.COGS_COA_ACCT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.DS_CMSN_GRP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.DS_CMSN_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.EXP_COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.EXP_COA_ACCT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FIFTH_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FIFTH_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FIRST_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FIRST_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FRTH_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.FRTH_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_DESC_LONG_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_CLS_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_CLS_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_MNF_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_MNF_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_ITEM_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MDSE_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MNF_ITEM_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.PRT_ITEM_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.REV_COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.REV_COA_ACCT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SCD_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SCD_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SIXTH_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SIXTH_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SLS_MAT_GRP_CD_01;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SLS_MAT_GRP_CD_02;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SLS_MAT_GRP_CD_03;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SLS_MAT_GRP_DESC_TXT_01;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SLS_MAT_GRP_DESC_TXT_02;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SLS_MAT_GRP_DESC_TXT_03;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SUPD_TO_MDSE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SVNTH_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.SVNTH_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIRD_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIRD_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIS_MTH_FOB_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIS_MTH_IMPT_DTY_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIS_MTH_INLND_FRT_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIS_MTH_INS_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIS_MTH_INTL_FRT_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.THIS_MTH_TOT_STD_COST_AMT;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ZEROTH_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ZEROTH_PROD_CTRL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB118001.constant.NMAB118001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Merchandise Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/28/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB118001 extends S21BatchMain {


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
        new NMAB118001().executeBatch(NMAB118001.class.getSimpleName());
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
            DMN_MDSETMsg tMsg = new DMN_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For DMN_MDSE Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);

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

                // Map Result Set Data To DMN_MDSE
                DMN_MDSETMsg tMsg = mapData(rs);

                // Insert DMN_MDSE
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", MDSE_CD, "=", rs.getString(MDSE_CD));
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
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
     * Map Result Set To DMN_MDSETMsg
     */
    private DMN_MDSETMsg mapData(ResultSet rs) throws SQLException {
        DMN_MDSETMsg tMsg = new DMN_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescLongTxt, rs.getString(MDSE_DESC_LONG_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, rs.getString(COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdDescTxt, rs.getString(COA_PROD_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseTpCd, rs.getString(MDSE_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseTpDescTxt, rs.getString(MDSE_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthFobAmt, rs.getBigDecimal(THIS_MTH_FOB_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthInlndFrtAmt, rs.getBigDecimal(THIS_MTH_INLND_FRT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthIntlFrtAmt, rs.getBigDecimal(THIS_MTH_INTL_FRT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthImptDtyAmt, rs.getBigDecimal(THIS_MTH_IMPT_DTY_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthInsAmt, rs.getBigDecimal(THIS_MTH_INS_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.thisMthTotStdCostAmt, rs.getBigDecimal(THIS_MTH_TOT_STD_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.zerothProdCtrlCd, rs.getString(ZEROTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.zerothProdCtrlNm, rs.getString(ZEROTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.firstProdCtrlCd, rs.getString(FIRST_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstProdCtrlNm, rs.getString(FIRST_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.scdProdCtrlCd, rs.getString(SCD_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdProdCtrlNm, rs.getString(SCD_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdProdCtrlCd, rs.getString(THIRD_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdProdCtrlNm, rs.getString(THIRD_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.frthProdCtrlCd, rs.getString(FRTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.frthProdCtrlNm, rs.getString(FRTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthProdCtrlCd, rs.getString(FIFTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fifthProdCtrlNm, rs.getString(FIFTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthProdCtrlCd, rs.getString(SIXTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.sixthProdCtrlNm, rs.getString(SIXTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthProdCtrlCd, rs.getString(SVNTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svnthProdCtrlNm, rs.getString(SVNTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd_01, rs.getString(SLS_MAT_GRP_CD_01));
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpDescTxt_01, rs.getString(SLS_MAT_GRP_DESC_TXT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd_02, rs.getString(SLS_MAT_GRP_CD_02));
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpDescTxt_02, rs.getString(SLS_MAT_GRP_DESC_TXT_02));
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpCd_03, rs.getString(SLS_MAT_GRP_CD_03));
        ZYPEZDItemValueSetter.setValue(tMsg.slsMatGrpDescTxt_03, rs.getString(SLS_MAT_GRP_DESC_TXT_03));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCmsnGrpCd, rs.getString(DS_CMSN_GRP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsCmsnGrpDescTxt, rs.getString(DS_CMSN_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mnfItemCd, rs.getString(MNF_ITEM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemMnfCd, rs.getString(MDSE_ITEM_MNF_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemMnfDescTxt, rs.getString(MDSE_ITEM_MNF_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.revCoaAcctCd, rs.getString(REV_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.revCoaAcctDescTxt, rs.getString(REV_COA_ACCT_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cogCoaAcctCd, rs.getString(COGS_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cogCoaAcctDescTxt, rs.getString(COGS_COA_ACCT_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.expCoaAcctCd, rs.getString(EXP_COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.expCoaAcctDescTxt, rs.getString(EXP_COA_ACCT_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.assetRecovCostAmt, rs.getBigDecimal(ASSET_RECOV_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.assetRecovCostAmtUpdDt, rs.getString(ASSET_RECOV_COST_AMT_UPD_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemStsCd, rs.getString(MDSE_ITEM_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemStsDescTxt, rs.getString(MDSE_ITEM_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, rs.getString(COA_MDSE_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpDescTxt, rs.getString(COA_MDSE_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnStsCd, rs.getString(RGTN_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemTpCd, rs.getString(MDSE_ITEM_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemTpDescTxt, rs.getString(MDSE_ITEM_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpCd, rs.getString(MDSE_ITEM_CLS_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpDescTxt, rs.getString(MDSE_ITEM_CLS_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.supdToMdseCd, rs.getString(SUPD_TO_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prtItemTpCd, rs.getString(PRT_ITEM_TP_CD));

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
