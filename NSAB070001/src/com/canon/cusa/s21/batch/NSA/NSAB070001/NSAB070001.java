/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB070001;

import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ACCT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ACTL_MACH_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ALT_PAYER_CUST_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.BLLG_PER_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.BLLG_PER_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.BW_COST_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.BW_MTR_CHRG_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CLR_COST_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CLR_MTR_CHRG_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_ACCT_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_AFFL_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_CC_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_CH_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_CMPY_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_EXTN_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_PROD_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.COA_PROJ_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CPO_ORD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CUR_LOC_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CUR_LOC_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_ACCT_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_CATG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_DTL_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_DTL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_DTL_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DS_INV_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DWH_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DWH_TRGT_QTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DWH_TRGT_YR;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.FCT_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.FSR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.FSR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.FSR_VISIT_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.IMG_SPLY_COLOR_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.IMG_SPLY_COLOR_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.IMG_SPLY_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.IMG_SPLY_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.IMG_SPLY_YIELD_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.IND_CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_BOL_LINE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_LINE_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_LINE_SPL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_LINE_SPL_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_LINE_SUB_TRX_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.INV_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ORD_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.OTH_COST_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.OTH_INV_LINE_DEAL_NET_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_CNTR_CTG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_COSTSPLY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_COSTSVC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_CRAT_DAYS;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_DR_CR_TP_C;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_EA;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_EXP_CHRG_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_FLEET;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_HR;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_HYPHEN;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_CHRG_TP_BC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_CHRG_TP_LC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_CHRG_TP_MC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_CHRG_TP_PC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_CHRG_TP_TC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_CHRG_TP_XC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_TP_DESC_BC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_INV_TP_DESC_MC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_LBOR_UNIT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_MI;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_REVSPLY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_REVSVC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_RPT_COND_CONST_CD_01;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_SEG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_SVC_ACCT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_SVC_MACH_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_USG_CHRG_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PARAM_USG_TRX_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.PRT_USED_BY_TECH_LOC_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SHIP_CPLT_COST_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SHIP_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SPLY_INCL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_CONTR_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_INV_CHRG_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_INV_CHRG_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_INV_LINE_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_INV_UNIT_HRS_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_LBOR_UNIT_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_LINE_BIZ_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_PBLM_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_PBLM_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_PGM_MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_SEG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_SEG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_TASK_CLO_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.SVC_TASK_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.UNIT_COST_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.UOM_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.USED_EQUA_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.USED_MULT_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_01;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_BASE;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_COSTSPLY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_COSTSVC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_DR_CR_TP_C;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_EA;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_EXP_CHRG_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_FLEET;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_HR;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_HYPHEN;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_LBOR_UNIT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_MI;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_NO_MODEL;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_NO_SEGMENT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_NSAB0700_CRAT_DAYS;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_REVSPLY;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_REVSVC;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_SVC_ACCT;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_USAGE;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_USG_CHRG_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.VAL_USG_TRX_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB070001.constant.NSAB070001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_DLY_CONTR_REV_COSTTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
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
 * Monthly Contract Revenue Cost Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/18/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB070001 extends S21BatchMain {

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
        new NSAB070001().executeBatch(NSAB070001.class.getSimpleName());
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

        // Check Target Date Recored (FCT_DLY_CONTR_REV_COST)
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            FCT_DLY_CONTR_REV_COSTTMsg tMsg = new FCT_DLY_CONTR_REV_COSTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fctCratDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For FCT_DLY_CONTR_REV_COST Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(PARAM_REVSVC, VAL_REVSVC);
        param.put(PARAM_REVSPLY, VAL_REVSPLY);
        param.put(PARAM_COSTSVC, VAL_COSTSVC);
        param.put(PARAM_COSTSPLY, VAL_COSTSPLY);
        param.put(PARAM_CNTR_CTG_CD, DS_CONTR_CATG.FLEET);
        param.put(PARAM_SER_NUM, VAL_FLEET);
        param.put(PARAM_HR, VAL_HR);
        param.put(PARAM_EA, VAL_EA);
        param.put(PARAM_MI, VAL_MI);
        param.put(PARAM_MDL_NM, VAL_NO_MODEL);
        param.put(PARAM_SEG_DESC_TXT, VAL_NO_SEGMENT);
        param.put(PARAM_FLEET, DS_CONTR_DTL_TP.FLEET);
        param.put(PARAM_SVC_MACH_TP, SVC_MACH_TP.MACHINE);
        param.put(PARAM_INV_CHRG_TP_LC, SVC_INV_CHRG_TP.LABOR_CHARGE);
        param.put(PARAM_INV_CHRG_TP_PC, SVC_INV_CHRG_TP.PARTS_CHARGE);
        param.put(PARAM_INV_CHRG_TP_XC, SVC_INV_CHRG_TP.EXPENSE_CHARGE);
        param.put(PARAM_INV_CHRG_TP_TC, SVC_INV_CHRG_TP.TRAVEL_CHARGE);
        param.put(PARAM_INV_CHRG_TP_BC, SVC_INV_CHRG_TP.BASE_CHARGE);
        param.put(PARAM_INV_CHRG_TP_MC, SVC_INV_CHRG_TP.METER_CHARGE);
        param.put(PARAM_INV_TP_DESC_BC, VAL_BASE);
        param.put(PARAM_INV_TP_DESC_MC, VAL_USAGE);
        param.put(PARAM_CRAT_DAYS, VAL_NSAB0700_CRAT_DAYS);
        param.put(PARAM_USG_TRX_TP, VAL_USG_TRX_TP);
        param.put(PARAM_SVC_ACCT, VAL_SVC_ACCT);
        param.put(PARAM_EXP_CHRG_TP, VAL_EXP_CHRG_TP);
        param.put(PARAM_USG_CHRG_TP, VAL_USG_CHRG_TP);
        param.put(PARAM_LBOR_UNIT, VAL_LBOR_UNIT);
        param.put(PARAM_RPT_COND_CONST_CD_01, VAL_01);
        param.put(PARAM_DR_CR_TP_C, VAL_DR_CR_TP_C);
        param.put(PARAM_HYPHEN, VAL_HYPHEN);

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

                // Map Result Set Data To FCT_DLY_CONTR_REV_COST
                FCT_DLY_CONTR_REV_COSTTMsg tMsg = mapData(rs);

                // Insert FCT_DLY_CONTR_REV_COST
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:");
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
     * Map Result Set Data To FCT_DLY_CONTR_REV_COST
     */
    private FCT_DLY_CONTR_REV_COSTTMsg mapData(ResultSet rs) throws SQLException {
        FCT_DLY_CONTR_REV_COSTTMsg tMsg = new FCT_DLY_CONTR_REV_COSTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal fctDlyContrRevCostPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_DLY_CONTR_REV_COST_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctDlyContrRevCostPk, fctDlyContrRevCostPk);
        ZYPEZDItemValueSetter.setValue(tMsg.fctCratDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, rs.getString(DWH_TRGT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, rs.getString(DWH_TRGT_YR_MTH));
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYr, rs.getString(DWH_TRGT_YR));
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtQtrNum, rs.getBigDecimal(DWH_TRGT_QTR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.fctCatgCd, rs.getString(FCT_CATG_CD));
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
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyCd, rs.getString(COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaAfflCd, rs.getString(COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, rs.getString(COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaChCd, rs.getString(COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, rs.getString(COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, rs.getString(COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, rs.getString(COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaProjCd, rs.getString(COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.coaExtnCd, rs.getString(COA_EXTN_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, rs.getString(MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipQty, rs.getBigDecimal(SHIP_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.unitCostAmt, rs.getBigDecimal(UNIT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipCpltCostAmt, rs.getBigDecimal(SHIP_CPLT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvChrgTpCd, rs.getString(SVC_INV_CHRG_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvChrgTpDescTxt, rs.getString(SVC_INV_CHRG_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTpCd, rs.getString(DS_INV_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.invTpDescTxt, rs.getString(INV_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvUnitHrsAot, rs.getBigDecimal(SVC_INV_UNIT_HRS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborUnitAmt, rs.getBigDecimal(SVC_LBOR_UNIT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.invNum, rs.getString(INV_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.invBolLineNum, rs.getString(INV_BOL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.invLineNum, rs.getString(INV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.invLineSubNum, rs.getString(INV_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.invLineSubTrxNum, rs.getString(INV_LINE_SUB_TRX_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvLinePk, rs.getBigDecimal(SVC_INV_LINE_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, rs.getString(INV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.acctDt, rs.getString(ACCT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.bwMtrChrgDealAmt, rs.getBigDecimal(BW_MTR_CHRG_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.bwCostAmt, rs.getBigDecimal(BW_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.clrMtrChrgDealAmt, rs.getBigDecimal(CLR_MTR_CHRG_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.clrCostAmt, rs.getBigDecimal(CLR_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.othInvLineDealNetAmt, rs.getBigDecimal(OTH_INV_LINE_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.othCostAmt, rs.getBigDecimal(OTH_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgPerFromDt, rs.getString(BLLG_PER_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.bllgPerThruDt, rs.getString(BLLG_PER_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.actlMachQty, rs.getBigDecimal(ACTL_MACH_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty, rs.getBigDecimal(ORD_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, rs.getString(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoDtlLineNum, rs.getString(CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdDt, rs.getString(CPO_ORD_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyYieldCnt, rs.getBigDecimal(IMG_SPLY_YIELD_CNT));
        if (ZYPCommonFunc.hasValue(rs.getString(SPLY_INCL_FLG))) {
            ZYPEZDItemValueSetter.setValue(tMsg.splyInclFlg, rs.getString(SPLY_INCL_FLG));
        } else {
            // Get SPLY_INCL_FLG 
            CovTmplInfo covTmplInfo = new CovTmplInfo();
            covTmplInfo.setSvcPgmMdseCd(rs.getString(SVC_PGM_MDSE_CD));
            NSXC001001GetCovTmpl nsxc001001GetCovTmpl = new NSXC001001GetCovTmpl();
            String splyInclFlg = ZYPConstant.FLG_OFF_N;
            if (nsxc001001GetCovTmpl.isSuplIncl(covTmplInfo)) {
                splyInclFlg = ZYPConstant.FLG_ON_Y;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.splyInclFlg, splyInclFlg);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, rs.getString(FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratDt, rs.getString(FSR_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.invLineSplTpCd, rs.getString(INV_LINE_SPL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.invLineSplTpDescTxt, rs.getString(INV_LINE_SPL_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, rs.getString(FSR_VISIT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskCloDt, rs.getString(SVC_TASK_CLO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.prtUsedByTechLocCd, rs.getString(PRT_USED_BY_TECH_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.uomCd, rs.getString(UOM_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, rs.getString(SVC_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmTpCd, rs.getString(SVC_PBLM_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmTpDescTxt, rs.getString(SVC_PBLM_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyTpCd, rs.getString(IMG_SPLY_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyTpDescTxt, rs.getString(IMG_SPLY_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyColorTpCd, rs.getString(IMG_SPLY_COLOR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.imgSplyColorTpDescTxt, rs.getString(IMG_SPLY_COLOR_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.usedMultCnt, rs.getBigDecimal(USED_MULT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.usedEquaCnt, rs.getBigDecimal(USED_EQUA_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyDescTxt, rs.getString(COA_CMPY_DESC_TXT));
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
