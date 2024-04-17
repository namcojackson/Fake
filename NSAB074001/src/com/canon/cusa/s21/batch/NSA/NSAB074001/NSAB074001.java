/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB074001;

import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.ALT_PAYER_CUST_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.BLLG_CYCLE_MTH_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.BLLG_PER_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.BLLG_PER_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.BW_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.BW_COST_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.BW_PROJ_SPLY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.CLR_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.CLR_COST_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.CLR_PROJ_SPLY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.COA_CMPY_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.COA_PROD_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.CUR_LOC_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.CUR_LOC_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_CATG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_DTL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_DTL_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DS_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DWH_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DWH_TRGT_QTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DWH_TRGT_YR;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.IMG_SPLY_COLOR_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.IMG_SPLY_COLOR_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.IMG_SPLY_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.IMG_SPLY_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.IMG_SPLY_YIELD_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.IND_CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.INV_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.INV_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_AVE_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_CRAT_DAYS;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_INV_CHRG_TP_MC;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_SEG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_CONTR_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_INV_LINE_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_LINE_BIZ_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_PGM_MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_SEG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.SVC_SEG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.TOT_MTR_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.UNIT_COST_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.VAL_AVE_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.VAL_FLEET;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.VAL_NO_MODEL;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.VAL_NO_SEGMENT;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.VAL_NSAB0740_CRAT_DAYS;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB074001.constant.NSAB074001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_CONTR_SPLY_PROJTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Monthly Contract Supply Project Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/18/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB074001 extends S21BatchMain {

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
        new NSAB074001().executeBatch(NSAB074001.class.getSimpleName());
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

        // Check Target Date Recored (FCT_MLY_CONTR_SPLY_PROJ)
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            FCT_MLY_CONTR_SPLY_PROJTMsg tMsg = new FCT_MLY_CONTR_SPLY_PROJTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fctCratDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For FCT_MLY_CONTR_SPLY_PROJ Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(PARAM_SER_NUM, VAL_FLEET);
        param.put(PARAM_MDL_NM, VAL_NO_MODEL);
        param.put(PARAM_SEG_DESC_TXT, VAL_NO_SEGMENT);
        param.put(PARAM_CRAT_DAYS, VAL_NSAB0740_CRAT_DAYS);
        param.put(PARAM_AVE_MTH, VAL_AVE_MTH);
        param.put(PARAM_INV_CHRG_TP_MC, SVC_INV_CHRG_TP.METER_CHARGE);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // Map Result Set Data and Insert FCT_MLY_CONTR_SPLY_PROJ
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", param, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {

                // Map Result Set Data To FCT_MLY_CONTR_SPLY_PROJ
                FCT_MLY_CONTR_SPLY_PROJTMsg tMsg = mapData(rs);

                for (int i = 0; i < rs.getBigDecimal(BLLG_CYCLE_MTH_AOT).intValue(); i++) {

                    // Map Calendar Data to FCT_MLY_CONTR_SPLY_PROJ
                    tMsg = mapCalData(tMsg, rs.getString(DWH_TRGT_DT), i);

                    // Insert FCT_MLY_CONTR_SPLY_PROJ
                    EZDTBLAccessor.insert(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", DWH_TRGT_DT, "=", tMsg.dwhTrgtDt.getValue(),
                                ", ", DS_CONTR_PK, "=", tMsg.dsContrPk.getValue(), "]");
                        S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
                        this.errRecCnt++;
                    }
                    this.totalRecCnt++;

                    // Commit By Commit Point
                    wkInsertCount++;
                    if (this.errRecCnt == 0 && wkInsertCount == this.commitCount) {
                        commit();
                        this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                        wkInsertCount = 0;
                    }
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
     * Map Result Set Data To FCT_MLY_CONTR_SPLY_PROJ
     */
    private FCT_MLY_CONTR_SPLY_PROJTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_CONTR_SPLY_PROJTMsg tMsg = new FCT_MLY_CONTR_SPLY_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal fctMlyContrSplyProjPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_MLY_CONTR_SPLY_PROJ_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyContrSplyProjPk, fctMlyContrSplyProjPk);
        ZYPEZDItemValueSetter.setValue(tMsg.fctCratDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, rs.getString(DWH_TRGT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, rs.getBigDecimal(DS_CONTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, rs.getString(DS_CONTR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, rs.getBigDecimal(DS_CONTR_DTL_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpCd, rs.getString(DS_CONTR_DTL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpDescTxt, rs.getString(DS_CONTR_DTL_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, rs.getString(SVC_CONTR_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgCd, rs.getString(DS_CONTR_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgDescTxt, rs.getString(DS_CONTR_CATG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, rs.getString(SVC_LINE_BIZ_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, rs.getString(DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNm, rs.getString(DS_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.altPayerCustCd, rs.getString(ALT_PAYER_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, rs.getString(CUR_LOC_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNm, rs.getString(CUR_LOC_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, rs.getString(CUR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.indCurLocNum, rs.getString(IND_CUR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.t_MdlNm, rs.getString(T_MDL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSegCd, rs.getString(SVC_SEG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSegDescTxt, rs.getString(SVC_SEG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, rs.getString(COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.unitCostAmt, rs.getBigDecimal(UNIT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.invNum, rs.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvLinePk, rs.getBigDecimal(SVC_INV_LINE_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, rs.getString(INV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.bwCopyQty, rs.getBigDecimal(BW_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.bwProjSplyQty, rs.getBigDecimal(BW_PROJ_SPLY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.bwCostRate, rs.getBigDecimal(BW_COST_RATE));
        ZYPEZDItemValueSetter.setValue(tMsg.clrCopyQty, rs.getBigDecimal(CLR_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.clrProjSplyQty, rs.getBigDecimal(CLR_PROJ_SPLY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.clrCostRate, rs.getBigDecimal(CLR_COST_RATE));
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrCopyQty, rs.getBigDecimal(TOT_MTR_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgPerFromDt, rs.getString(BLLG_PER_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgPerThruDt, rs.getString(BLLG_PER_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyYieldCnt, rs.getBigDecimal(IMG_SPLY_YIELD_CNT));

        // Get SPLY_INCL_FLG 
        CovTmplInfo covTmplInfo = new CovTmplInfo();
        covTmplInfo.setSvcPgmMdseCd(rs.getString(SVC_PGM_MDSE_CD));
        NSXC001001GetCovTmpl nsxc001001GetCovTmpl = new NSXC001001GetCovTmpl();
        String splyInclFlg = ZYPConstant.FLG_OFF_N;
        if (nsxc001001GetCovTmpl.isSuplIncl(covTmplInfo)) {
            splyInclFlg = ZYPConstant.FLG_ON_Y;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.splyInclFlg, splyInclFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyTpCd, rs.getString(IMG_SPLY_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyTpDescTxt, rs.getString(IMG_SPLY_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyColorTpCd, rs.getString(IMG_SPLY_COLOR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyColorTpDescTxt, rs.getString(IMG_SPLY_COLOR_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyDescTxt, rs.getString(COA_CMPY_DESC_TXT));

        return tMsg;
    }

   /**
    * mapCalData
    * @param  FCT_MLY_CONTR_SPLY_PROJTMsg tMsg
    * @param  String dwhTrgtDt
    * @param  int cnt
    * @return FCT_MLY_CONTR_SPLY_PROJTMsg
    */
    private FCT_MLY_CONTR_SPLY_PROJTMsg mapCalData(FCT_MLY_CONTR_SPLY_PROJTMsg tMsg, String dwhTrgtDt, int cnt) {

        // Get DWH_TRGT_QTR_NUM and add months DWH_TRGT_DT
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, dwhTrgtDt);
        param.put(PARAM_CNT, cnt);
        Map<String, Object> trgtMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrgtQtrNum", param);

        if (trgtMap != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, (String) trgtMap.get(DWH_TRGT_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, (String) trgtMap.get(DWH_TRGT_YR_MTH));
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYr, (String) trgtMap.get(DWH_TRGT_YR));
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtQtrNum, (BigDecimal) trgtMap.get(DWH_TRGT_QTR_NUM));
        }

        BigDecimal fctMlyContrSplyProjPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_MLY_CONTR_SPLY_PROJ_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyContrSplyProjPk, fctMlyContrSplyProjPk);

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
