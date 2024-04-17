/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB116001;

import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ALT_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ALT_PSN_FULL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ATTN_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.AUTO_SO_DROP_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.CMPY_INVTY_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.CNTY_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.COA_BR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.COA_BR_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.COA_BR_RG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.COA_BR_RG_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.COA_BR_ZN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.COA_BR_ZN_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_RTRN_PROCR_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_RTRN_TO_LOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_RTRN_TO_PROCR_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_RTRN_TO_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_RTRN_TO_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_SRC_LOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_SRC_PROCR_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_SRC_PROCR_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_SRC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.DEF_SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.EMER_SRC_LOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.EMER_SRC_PROCR_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.EMER_SRC_PROCR_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.EMER_SRC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.EMER_SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.FIRST_OVNGT_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.FIRST_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.GEO_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.GND_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.INVTY_ACCT_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.INVTY_ACCT_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.INVTY_OWNR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.INVTY_OWNR_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.KEY_DFLT_RTL_WH_RPT_OP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.KEY_DFLT_RTL_WH_RPT_RG_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.KEY_RTL_WH_RPT_OP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.KEY_RTL_WH_RPT_RG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.LOC_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PHYS_WH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PLN_ITEM_INSRC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PLN_ITEM_INSRC_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PO_RCPT_RTE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PO_RCPT_RTE_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PRF_CARR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RMA_RCPT_RTE_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RMA_RCPT_RTE_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_SWH_DSBL_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_SWH_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_SWH_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_SWH_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_CATG_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_RPT_OP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTL_WH_RPT_RG_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_ADDL_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_ATTN_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_CNTY_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_FAX_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_PROV_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_TEL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RTRN_TO_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.RWS_PRIN_REQ_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SCD_OVNGT_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SCD_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SHIP_TO_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SO_PRIN_REQ_FLG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.SRC_ZN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.TEL_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.TM_ZONE_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.VAL_999;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.VAL_RTL_WH_RPT_OP_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.VAL_RTL_WH_RPT_RG;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.VAL_UNDEFINED;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_CUT_OFF_TM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_END_TM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_MGR_PSN_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_MGR_PSN_FULL_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_OWNR_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_OWNR_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_START_TM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WH_SYS_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WMS_DESC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.WMS_WH_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB116001.constant.NMAB116001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_RTL_SWHTMsg;

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
 * Warehouse Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/27/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NMAB116001 extends S21BatchMain {


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
        new NMAB116001().executeBatch(NMAB116001.class.getSimpleName());
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
            DMN_RTL_SWHTMsg tMsg = new DMN_RTL_SWHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E,  new String[] {errMsg});
            }
        }

        // Get Main Data For DMN_RTL_SWH Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(KEY_RTL_WH_RPT_RG, VAL_RTL_WH_RPT_RG);
        param.put(KEY_RTL_WH_RPT_OP_ID, VAL_RTL_WH_RPT_OP_ID);
        param.put(KEY_DFLT_RTL_WH_RPT_OP_ID, VAL_999);
        param.put(KEY_DFLT_RTL_WH_RPT_RG_TXT, VAL_UNDEFINED);

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

                // Map Result Set Data To DMN_RTL_SWH
                DMN_RTL_SWHTMsg tMsg = mapData(rs);

                // Insert DMN_RTL_SWH
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", INVTY_LOC_CD, "=", rs.getString(INVTY_LOC_CD));
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
     * Map Result Set To DMN_RTL_SWHTMsg
     */
    private DMN_RTL_SWHTMsg mapData(ResultSet rs) throws SQLException {
        DMN_RTL_SWHTMsg tMsg = new DMN_RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString(RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rs.getString(RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhNm, rs.getString(RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhNm, rs.getString(RTL_SWH_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.locTpCd, rs.getString(LOC_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, rs.getString(TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, rs.getBigDecimal(CNTY_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.provNm, rs.getString(PROV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString(ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString(POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, rs.getString(CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, rs.getString(ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.faxNum, rs.getString(FAX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.telNum, rs.getString(TEL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.attnNm, rs.getString(ATTN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhEffFromDt, rs.getString(RTL_WH_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhEffThruDt, rs.getString(RTL_WH_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, rs.getString(COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrDescTxt, rs.getString(COA_BR_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrRgCd, rs.getString(COA_BR_RG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrRgDescTxt, rs.getString(COA_BR_RG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrZnCd, rs.getString(COA_BR_ZN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrZnDescTxt, rs.getString(COA_BR_ZN_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhRptOpId, rs.getBigDecimal(RTL_WH_RPT_OP_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhRptRgTxt, rs.getString(RTL_WH_RPT_RG_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhDsblFlg, rs.getString(RTL_SWH_DSBL_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhEffFromDt, rs.getString(RTL_SWH_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhEffThruDt, rs.getString(RTL_SWH_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, rs.getString(RTL_WH_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgDescTxt, rs.getString(RTL_WH_CATG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.physWhCd, rs.getString(PHYS_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsWhCd, rs.getString(WMS_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsDescNm, rs.getString(WMS_DESC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.whMgrPsnCd, rs.getString(WH_MGR_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whMgrPsnFullNm, rs.getString(WH_MGR_PSN_FULL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.altPsnCd, rs.getString(ALT_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.altPsnFullNm, rs.getString(ALT_PSN_FULL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyAcctCd, rs.getString(INVTY_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyAcctDescTxt, rs.getString(INVTY_ACCT_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyOwnrCd, rs.getString(INVTY_OWNR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyOwnrDescTxt, rs.getString(INVTY_OWNR_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cmpyInvtyFlg, rs.getString(CMPY_INVTY_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.whOwnrCd, rs.getString(WH_OWNR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whOwnrDescTxt, rs.getString(WH_OWNR_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.firstRefCmntTxt, rs.getString(FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.scdRefCmntTxt, rs.getString(SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.autoSoDropFlg, rs.getString(AUTO_SO_DROP_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.soPrinReqFlg, rs.getString(SO_PRIN_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rwsPrinReqFlg, rs.getString(RWS_PRIN_REQ_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.whSysTpCd, rs.getString(WH_SYS_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whSysTpDescTxt, rs.getString(WH_SYS_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.whStartTm, rs.getString(WH_START_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.whEndTm, rs.getString(WH_END_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.whCutOffTm, rs.getString(WH_CUT_OFF_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.tmZoneCd, rs.getString(TM_ZONE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.geoCd, rs.getString(GEO_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.srcZnCd, rs.getString(SRC_ZN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcProcrTpCd, rs.getString(DEF_SRC_PROCR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcProcrTpDescTxt, rs.getString(DEF_SRC_PROCR_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcLocCd, rs.getString(DEF_SRC_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlWhCd, rs.getString(DEF_SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcRtlSwhCd, rs.getString(DEF_SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.emerSrcProcrTpCd, rs.getString(EMER_SRC_PROCR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.emerSrcProcrTpDescTxt, rs.getString(EMER_SRC_PROCR_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.emerSrcLocCd, rs.getString(EMER_SRC_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.emerSrcRtlWhCd, rs.getString(EMER_SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.emerSrcRtlSwhCd, rs.getString(EMER_SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToProcrTpCd, rs.getString(DEF_RTRN_TO_PROCR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defRtrnProcrTpDescTxt, rs.getString(DEF_RTRN_PROCR_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToLocCd, rs.getString(DEF_RTRN_TO_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlWhCd, rs.getString(DEF_RTRN_TO_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defRtrnToRtlSwhCd, rs.getString(DEF_RTRN_TO_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prfCarrCd, rs.getString(PRF_CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.plnItemInsrcCd, rs.getString(PLN_ITEM_INSRC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.plnItemInsrcDescTxt, rs.getString(PLN_ITEM_INSRC_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.gndSvcLvlCd, rs.getString(GND_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstOvngtSvcLvlCd, rs.getString(FIRST_OVNGT_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.scdOvngtSvcLvlCd, rs.getString(SCD_OVNGT_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCustCd, rs.getString(RTRN_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToLocNm, rs.getString(RTRN_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToFirstLineAddr, rs.getString(RTRN_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToScdLineAddr, rs.getString(RTRN_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToThirdLineAddr, rs.getString(RTRN_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToFrthLineAddr, rs.getString(RTRN_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCtyAddr, rs.getString(RTRN_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCntyPk, rs.getBigDecimal(RTRN_TO_CNTY_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToProvNm, rs.getString(RTRN_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToStCd, rs.getString(RTRN_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToPostCd, rs.getString(RTRN_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToCtryCd, rs.getString(RTRN_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToAddlLocNm, rs.getString(RTRN_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToFaxNum, rs.getString(RTRN_TO_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToTelNum, rs.getString(RTRN_TO_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnToAttnNm, rs.getString(RTRN_TO_ATTN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.poRcptRteTpCd, rs.getString(PO_RCPT_RTE_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.poRcptRteTpDescTxt, rs.getString(PO_RCPT_RTE_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.rmaRcptRteTpCd, rs.getString(RMA_RCPT_RTE_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rmaRcptRteTpDescTxt, rs.getString(RMA_RCPT_RTE_TP_DESC_TXT));

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
