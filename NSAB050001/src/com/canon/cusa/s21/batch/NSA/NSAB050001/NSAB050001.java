/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB050001;

import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.API_PARAM_TIMESTAMP_FORMAT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.BLLG_BIZ_TP_CD_CM;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.BLLG_BIZ_TP_CD_DP;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.BLLG_BIZ_TP_CD_LT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.BLLG_BIZ_TP_CD_P;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.BLLG_BIZ_TP_CD_SM;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.BLLG_BIZ_TP_CD_TP;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.CUSA_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_ADMIN_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_COND_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_COND_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_CYCLE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_DAY;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_MSTR_BLLG_MTR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_MSTR_BLLG_MTR_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_MSTR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_MSTR_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_TMG_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_XS_COPY_PRC_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_BLLG_XS_COPY_PRC_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_CONTR_END_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_CONTR_START_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_EXT_WTY_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_FNDG_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_FNDG_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_INSTL_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_ISTL_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_ISTL_SUB_LOC_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_LAST_BLLG_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MACH_MDL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MACH_MSTR_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MLY_ADMIN_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MLY_BASE_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MLY_COPY_INCL_COMP_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MRCF_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MSTR_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_ORIG_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_ORIG_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PMT_TERM_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PMT_TERM_CASH_DISC_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PMT_TERM_CASH_DISC_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PMT_TERM_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PMT_TERM_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PRNT_BLLG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PRNT_MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_PRNT_SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RNW_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_ROSS_INTFC_PROC_MODE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTL_CUST_PO_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTL_CUST_PO_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTL_CUST_PO_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTL_DIV_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTL_MDL_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTL_MDL_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_RTRN_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SELL_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SELL_TO_CUST_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SELL_TO_CUST_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SPLY_INCL_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SVC_BLLG_MSTR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SVC_BLLG_MSTR_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SVC_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_SVC_DLR_COMP_AMT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_TRMN_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_XS_MTR_COMP_AMT_RATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_COLUMN_XS_MTR_COMP_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_BLLG_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_BLLG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_CONTR_END_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_CUR_PROC_TS;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_GLBL_CMPY_CD_01;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_GLBL_CMPY_CD_ABR;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_LAST_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_LAST_PROC_TS;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_MACH_MDL_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_MSTR_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_PROC_PGM_ID_01;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_ROSS_INTFC_CONTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_RTL_DIV_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_RTL_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.DB_PARAM_SALES_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.MACH_MDL_TP_CD_MACHINE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.MACH_MSTR_STS_CD_65;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.MACH_MSTR_STS_CD_70;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.MACH_MSTR_STS_CD_8X;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.NASM0010E;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.NASM0011E;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.NSAM0031E;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.NSZM0543E;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_CONTR_DEL_VAL;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_CONTR_PROC_ERROR;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_CONTR_PROC_NORMAL;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_CONTR_PROC_TXT_INVALID;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_MODE_CREATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_MODE_ERROR;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_MODE_SKIP;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_MODE_TERMINATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_MODE_TERMINATE_CREATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_MODE_UPDATE;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_STS_PROCESSED;
import static com.canon.cusa.s21.batch.NSA.NSAB050001.constant.NSAB050001Constant.ROSS_INTFC_PROC_STS_UNPROCESSED;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.ROSS_INTFC_CONTRTMsg;
import business.db.ROSS_INTFC_CONTR_USGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRCF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MSTR_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_DIV;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * NSAB0500: Populate CUSA Retail Contract Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/31/2016   CITS            M.Okigami       Create          N/A
 * 01/11/2017   CITS            T.Kikuhara      Update          QC#15484
 * 04/07/2017   Hitachi         T.Tomita        Update          QC#18238
 * 2017/11/14   Hitachi         K.Kojima        Update          QC#22182
 * 2018/01/19   Hitachi         U.Kim           Update          QC#22173
 *</pre>
 */
public class NSAB050001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt;

    /** ROSS_INTFC_CONTR_DEL_VAL Value */
    private BigDecimal delValConst = null;

    /** CUSA_GLBL_CMPY_CD Value */
    private String cusaGlblCmpyCd = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

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

        this.delValConst = ZYPCodeDataUtil.getNumConstValue(ROSS_INTFC_CONTR_DEL_VAL, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.delValConst)) {
            throw new S21AbendException(NSZM0543E, new String[] {ROSS_INTFC_CONTR_DEL_VAL});
        }

        this.cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_GLBL_CMPY_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.cusaGlblCmpyCd)) {
            throw new S21AbendException(NSZM0543E, new String[] {CUSA_GLBL_CMPY_CD});
        }

    }

    @Override
    protected void mainRoutine() {

        purgeRossIntfcContr();

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);
        String lastProcTs = getLastProcTimestamp(systemTime);
        String lastProcDate = null;
        if (lastProcTs != null) {
            lastProcDate = lastProcTs.substring(0, 8);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(DB_PARAM_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
            // START 2017/11/14 K.Kojima [QC#22182,MOD]
            // paramMap.put(DB_PARAM_MACH_MDL_TP_CD, new String[] {MACH_MDL_TP_CD_MACHINE, MACH_MDL_TP_CD_ACCESSORY});
            paramMap.put(DB_PARAM_MACH_MDL_TP_CD, new String[] {MACH_MDL_TP_CD_MACHINE});
            // END 2017/11/14 K.Kojima [QC#22182,MOD]
            paramMap.put(DB_PARAM_BLLG_BIZ_TP_CD, new String[] {BLLG_BIZ_TP_CD_SM,
                                                                BLLG_BIZ_TP_CD_CM,
                                                                BLLG_BIZ_TP_CD_P,
                                                                BLLG_BIZ_TP_CD_TP,
                                                                BLLG_BIZ_TP_CD_LT,
                                                                BLLG_BIZ_TP_CD_DP});
            paramMap.put(DB_PARAM_RTL_DIV_CD, RTL_DIV.NATIONAL_ACCOUNTS);
            paramMap.put(DB_PARAM_MSTR_BIZ_TP_CD, MSTR_BIZ_TP.PURCHASE);
            paramMap.put(DB_PARAM_LAST_PROC_DATE, lastProcDate);
            paramMap.put(DB_PARAM_SALES_DATE, this.slsDt);
            paramMap.put(DB_PARAM_LAST_PROC_TS, lastProcTs);
            paramMap.put(DB_PARAM_CUR_PROC_TS, systemTime);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            preparedStatement = ssmLlcClient.createPreparedStatement("getCsaContrDtl", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                this.totalCount++;

                ROSS_INTFC_CONTRTMsg tMsg = new ROSS_INTFC_CONTRTMsg();

                String prntSerNum = resultSet.getString(DB_COLUMN_PRNT_SER_NUM);
                BigDecimal bllgMstrPk = resultSet.getBigDecimal(DB_COLUMN_BLLG_MSTR_PK);
                String machMstrStsCd = resultSet.getString(DB_COLUMN_MACH_MSTR_STS_CD);
                String trmnDt = resultSet.getString(DB_COLUMN_TRMN_DT);

                Map<String, Object> rossIntfcContr = getRossIntfcContr(bllgMstrPk);

                if (rossIntfcContr == null
                         && (MACH_MSTR_STS_CD_65.equals(machMstrStsCd) || MACH_MSTR_STS_CD_70.equals(machMstrStsCd))
                         && trmnDt == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_CREATE);
                } else if (rossIntfcContr == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_ERROR);
                } else if (ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && trmnDt == null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_CREATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && getDBColumnValue(rossIntfcContr, DB_COLUMN_TRMN_DT) == null
                         && trmnDt != null) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_TERMINATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && (MACH_MSTR_STS_CD_65.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_MACH_MSTR_STS_CD))
                                || MACH_MSTR_STS_CD_70.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_MACH_MSTR_STS_CD)))
                         && (machMstrStsCd != null && machMstrStsCd.startsWith(MACH_MSTR_STS_CD_8X))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_TERMINATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                        // START 2018/01/19 U.Kim [QC#22173, MOD]
                        //&& (!equalDate((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_CONTR_START_DT), resultSet.getString(DB_COLUMN_CONTR_START_DT))
                        && (!equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_MLY_BASE_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_MLY_BASE_COMP_AMT))
                                || !equalDate((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_CONTR_START_DT), resultSet.getString(DB_COLUMN_CONTR_START_DT))
                        // END 2018/01/19 U.Kim [QC#22173, MOD]
                         && (!equalDate((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_CONTR_START_DT), resultSet.getString(DB_COLUMN_CONTR_START_DT))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_BLLG_CYCLE_CD), resultSet.getString(DB_COLUMN_BLLG_CYCLE_CD))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_BLLG_TMG_TP_CD), resultSet.getString(DB_COLUMN_BLLG_TMG_TP_CD))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_SER_NUM), resultSet.getString(DB_COLUMN_SER_NUM))))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_TERMINATE_CREATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && (!equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_SVC_DLR_CD), resultSet.getString(DB_COLUMN_SVC_DLR_CD))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_FNDG_DLR_CD), resultSet.getString(DB_COLUMN_FNDG_DLR_CD))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_ORIG_DLR_CD), resultSet.getString(DB_COLUMN_ORIG_DLR_CD))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_ADMIN_DLR_CD), resultSet.getString(DB_COLUMN_ADMIN_DLR_CD))
                                // START 2018/01/19 U.Kim [QC#22173, ADD]
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_ISTL_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_ISTL_COMP_AMT))
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_SVC_DLR_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_SVC_DLR_COMP_AMT))
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_EXT_WTY_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_EXT_WTY_COMP_AMT))
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_FNDG_DLR_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_FNDG_DLR_COMP_AMT))
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_ORIG_DLR_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_ORIG_DLR_COMP_AMT))
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_MLY_ADMIN_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_MLY_ADMIN_COMP_AMT))
                                || !equalValue((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_RNW_COMP_AMT), resultSet.getBigDecimal(DB_COLUMN_RNW_COMP_AMT))
                                // END 2018/01/19 U.Kim [QC#22173, ADD]
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_RTL_DIV_CD), resultSet.getString(DB_COLUMN_RTL_DIV_CD))
                                || !equalValue((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_SPLY_INCL_FLG), resultSet.getString(DB_COLUMN_SPLY_INCL_FLG))
                                || (MRCF_TP.METER_READING_REQUEST_TO_CUSTOMER_CONTACT.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_MRCF_TP_CD))
                                        || MRCF_TP.STANDARD_FORMAT_TO_SERVICE_DEALER.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_MRCF_TP_CD)))
                                    && (!MRCF_TP.METER_READING_REQUEST_TO_CUSTOMER_CONTACT.equals(resultSet.getString(DB_COLUMN_MRCF_TP_CD))
                                         && !MRCF_TP.STANDARD_FORMAT_TO_SERVICE_DEALER.equals(resultSet.getString(DB_COLUMN_MRCF_TP_CD)))
                                || (!MRCF_TP.METER_READING_REQUEST_TO_CUSTOMER_CONTACT.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_MRCF_TP_CD))
                                        && !MRCF_TP.STANDARD_FORMAT_TO_SERVICE_DEALER.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_MRCF_TP_CD)))
                                    && (MRCF_TP.METER_READING_REQUEST_TO_CUSTOMER_CONTACT.equals(resultSet.getString(DB_COLUMN_MRCF_TP_CD))
                                        || MRCF_TP.STANDARD_FORMAT_TO_SERVICE_DEALER.equals(resultSet.getString(DB_COLUMN_MRCF_TP_CD))))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_UPDATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && isUpdatedRossContractUsageForTerminateAndCreate((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_BLLG_MSTR_PK))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_TERMINATE_CREATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && isUpdatedRossContractUsageForUpdate((BigDecimal) getDBColumnValue(rossIntfcContr, DB_COLUMN_BLLG_MSTR_PK))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_UPDATE);
                } else if (!ROSS_INTFC_PROC_MODE_TERMINATE.equals(getDBColumnValue(rossIntfcContr, DB_COLUMN_ROSS_INTFC_PROC_MODE_CD))
                         && (!equalDate((String) getDBColumnValue(rossIntfcContr, DB_COLUMN_LAST_BLLG_DT), resultSet.getString(DB_COLUMN_LAST_BLLG_DT))
                                || ((MSTR_BIZ_TP.PURCHASE.equals(resultSet.getString(DB_COLUMN_MSTR_BIZ_TP_CD))
                                        || MSTR_BIZ_TP.LEASE.equals(resultSet.getString(DB_COLUMN_MSTR_BIZ_TP_CD)))
                                    && !isExistDealer(resultSet.getString(DB_COLUMN_SVC_DLR_CD))))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_SKIP);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcModeCd, ROSS_INTFC_PROC_MODE_ERROR);
                }

                BigDecimal rossIntfcContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_INTFC_CONTR_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrPk, rossIntfcContrPk);
                if (ROSS_INTFC_PROC_MODE_ERROR.equals(tMsg.rossIntfcProcModeCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrProcCd, ROSS_INTFC_CONTR_PROC_ERROR);
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrProcTxt, ROSS_INTFC_CONTR_PROC_TXT_INVALID);
                    // add start 2017/04/07 CSA Defect#18238
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcStsCd, ROSS_INTFC_PROC_STS_PROCESSED);
                    // add end 2017/04/07 CSA Defect#18238
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrProcCd, ROSS_INTFC_CONTR_PROC_NORMAL);
                    // add start 2017/04/07 CSA Defect#18238
                    ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcStsCd, ROSS_INTFC_PROC_STS_UNPROCESSED);
                    // add end 2017/04/07 CSA Defect#18238
                }
                ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrCratDt, this.slsDt);
                // del start 2017/04/07 CSA Defect#18238
//                ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcProcStsCd, ROSS_INTFC_PROC_STS_UNPROCESSED);
                // del end 2017/04/07 CSA Defect#18238
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrPk, resultSet.getBigDecimal(DB_COLUMN_BLLG_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, resultSet.getString(DB_COLUMN_SER_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, resultSet.getString(DB_COLUMN_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.prntBllgMstrId, resultSet.getBigDecimal(DB_COLUMN_PRNT_BLLG_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, resultSet.getString(DB_COLUMN_PRNT_SER_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.prntMdseCd, resultSet.getString(DB_COLUMN_PRNT_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.mstrBizTpCd, resultSet.getString(DB_COLUMN_MSTR_BIZ_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.machMstrStsCd, resultSet.getString(DB_COLUMN_MACH_MSTR_STS_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, resultSet.getString(DB_COLUMN_BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, resultSet.getString(DB_COLUMN_SELL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.instlCd, resultSet.getString(DB_COLUMN_INSTL_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.istlSubLocCd, resultSet.getString(DB_COLUMN_ISTL_SUB_LOC_CD));
                // START 2018/01/19 U.Kim [QC#22173,MOD]
                // ZYPEZDItemValueSetter.setValue(tMsg.mlyBaseCompAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(tMsg.mlyBaseCompAmt, resultSet.getBigDecimal(DB_COLUMN_MLY_BASE_COMP_AMT));
                // END 2018/01/19 U.Kim [QC#22173,MOD]
                ZYPEZDItemValueSetter.setValue(tMsg.splyInclFlg, resultSet.getString(DB_COLUMN_SPLY_INCL_FLG));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgBizTpCd, resultSet.getString(DB_COLUMN_BLLG_BIZ_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.contrStartDt, resultSet.getString(DB_COLUMN_CONTR_START_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.contrEndDt, resultSet.getString(DB_COLUMN_CONTR_END_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.trmnDt, resultSet.getString(DB_COLUMN_TRMN_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.rtrnDt, resultSet.getString(DB_COLUMN_RTRN_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.mrcfTpCd, resultSet.getString(DB_COLUMN_MRCF_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleCd, resultSet.getString(DB_COLUMN_BLLG_CYCLE_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.lastBllgDt, resultSet.getString(DB_COLUMN_LAST_BLLG_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgDay, resultSet.getString(DB_COLUMN_BLLG_DAY));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgTmgTpCd, resultSet.getString(DB_COLUMN_BLLG_TMG_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.pmtTermAot, resultSet.getBigDecimal(DB_COLUMN_PMT_TERM_AOT));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlDivCd, resultSet.getString(DB_COLUMN_RTL_DIV_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCd, resultSet.getString(DB_COLUMN_SVC_DLR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrCd, resultSet.getString(DB_COLUMN_FNDG_DLR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.origDlrCd, resultSet.getString(DB_COLUMN_ORIG_DLR_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.adminDlrCd, resultSet.getString(DB_COLUMN_ADMIN_DLR_CD));
                // START 2018/01/19 U.Kim [QC#22173,MOD]
                // ZYPEZDItemValueSetter.setValue(tMsg.istlCompAmt, BigDecimal.ZERO);
                // ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCompAmt, BigDecimal.ZERO);
                // ZYPEZDItemValueSetter.setValue(tMsg.extWtyCompAmt, BigDecimal.ZERO);
                // ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrCompAmt, BigDecimal.ZERO);
                // ZYPEZDItemValueSetter.setValue(tMsg.origDlrCompAmt, BigDecimal.ZERO);
                // ZYPEZDItemValueSetter.setValue(tMsg.mlyAdminCompAmt, BigDecimal.ZERO);
                // ZYPEZDItemValueSetter.setValue(tMsg.rnwCompAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(tMsg.istlCompAmt, resultSet.getBigDecimal(DB_COLUMN_ISTL_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCompAmt, resultSet.getBigDecimal(DB_COLUMN_SVC_DLR_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.extWtyCompAmt, resultSet.getBigDecimal(DB_COLUMN_EXT_WTY_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.fndgDlrCompAmt, resultSet.getBigDecimal(DB_COLUMN_FNDG_DLR_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.origDlrCompAmt, resultSet.getBigDecimal(DB_COLUMN_ORIG_DLR_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.mlyAdminCompAmt, resultSet.getBigDecimal(DB_COLUMN_MLY_ADMIN_COMP_AMT));
                ZYPEZDItemValueSetter.setValue(tMsg.rnwCompAmt, resultSet.getBigDecimal(DB_COLUMN_RNW_COMP_AMT));
                // END 2018/01/19 U.Kim [QC#22173,MOD]
                ZYPEZDItemValueSetter.setValue(tMsg.machMdlTpCd, resultSet.getString(DB_COLUMN_MACH_MDL_TP_CD));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrCratDt, resultSet.getString(DB_COLUMN_BLLG_MSTR_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrModDt, resultSet.getString(DB_COLUMN_BLLG_MSTR_MOD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.svcBllgMstrCratDt, resultSet.getString(DB_COLUMN_SVC_BLLG_MSTR_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.svcBllgMstrModDt, resultSet.getString(DB_COLUMN_SVC_BLLG_MSTR_MOD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCratDt, resultSet.getString(DB_COLUMN_SELL_TO_CUST_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustModDt, resultSet.getString(DB_COLUMN_SELL_TO_CUST_MOD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlMdlCratDt, resultSet.getString(DB_COLUMN_RTL_MDL_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlMdlModDt, resultSet.getString(DB_COLUMN_RTL_MDL_MOD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgCondCratDt, resultSet.getString(DB_COLUMN_BLLG_COND_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.bllgCondModDt, resultSet.getString(DB_COLUMN_BLLG_COND_MOD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCratDt, resultSet.getString(DB_COLUMN_PMT_TERM_CASH_DISC_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscModDt, resultSet.getString(DB_COLUMN_PMT_TERM_CASH_DISC_MOD_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlCustPoPk, resultSet.getBigDecimal(DB_COLUMN_RTL_CUST_PO_PK));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlCustPoCratDt, resultSet.getString(DB_COLUMN_RTL_CUST_PO_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(tMsg.rtlCustPoModDt, resultSet.getString(DB_COLUMN_RTL_CUST_PO_MOD_DT));

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0032E, new String[] {tMsg.getTableName()});
                }

                createRossIntfcContrUsg(resultSet.getBigDecimal(DB_COLUMN_BLLG_MSTR_PK), rossIntfcContrPk);
            }

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

    }

    @Override
    protected void termRoutine() {

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
        new NSAB050001().executeBatch(NSAB050001.class.getSimpleName());
    }

    private void purgeRossIntfcContr() {

        int delVal = this.delValConst.intValue();
        String delDate = ZYPDateUtil.addDays(this.slsDt, -delVal);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_CONTR_END_DT, delDate);
        List<BigDecimal> purgeList = (List<BigDecimal>) this.glSsmBatchClient.queryObjectList("getRossIntfcContrPkForPurge", queryParam);

        for (BigDecimal purgePk : purgeList) {
            ROSS_INTFC_CONTRTMsg tMsg = new ROSS_INTFC_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrPk, purgePk);
            EZDTBLAccessor.remove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName()});
            }
            purgeRossIntfcContrUsg(purgePk);
        }
    }

    private void purgeRossIntfcContrUsg(BigDecimal rossIntfcContrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_ROSS_INTFC_CONTR_PK, rossIntfcContrPk);
        List<BigDecimal> purgeList = (List<BigDecimal>) this.glSsmBatchClient.queryObjectList("getRossIntfcContrUsgPkForPurge", queryParam);

        for (BigDecimal purgePk : purgeList) {
            ROSS_INTFC_CONTR_USGTMsg tMsg = new ROSS_INTFC_CONTR_USGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrUsgPk, purgePk);
            EZDTBLAccessor.remove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName()});
            }
        }
    }

    private String getLastProcTimestamp(String systemTime) {

        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue(DB_PARAM_GLBL_CMPY_CD_01, this.glblCmpyCd);
        inMsg.setConditionValue(DB_PARAM_PROC_PGM_ID_01, PROGRAM_ID);

        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {

            DS_BIZ_PROC_LOGTMsg curMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(curMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(curMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
            ZYPEZDItemValueSetter.setValue(curMsg.procPgmId, PROGRAM_ID);
            ZYPEZDItemValueSetter.setValue(curMsg.dsBizProcDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(curMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(curMsg.dsBizLastProcTs, systemTime);
            ZYPEZDItemValueSetter.setValue(curMsg.dsBizLastUpdTs, systemTime);

            EZDTBLAccessor.insert(curMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(curMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0032E, new String[] {curMsg.getTableName()});
            }

            return null;

        } else {

            DS_BIZ_PROC_LOGTMsg outMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0031E, new String[] {inMsg.getTableName()});
            } else {
                ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcDt, this.slsDt);
                ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, systemTime);
                ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, systemTime);
                EZDTBLAccessor.update(outMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    throw new S21AbendException(NSAM0031E, new String[] {outMsg.getTableName()});
                }
            }

            return tMsgAry.no(0).dsBizLastProcTs.getValue();
        }
    }

    private Map<String, Object> getRossIntfcContr(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getRossIntfcContr", queryParam);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    private boolean isUpdatedRossContractUsageForTerminateAndCreate(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_GLBL_CMPY_CD_ABR, this.cusaGlblCmpyCd);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countUpdatedRossContractUsageForTerminateAndCreate", queryParam);
        return (result.intValue() > 0);
    }

    private boolean isUpdatedRossContractUsageForUpdate(BigDecimal bllgMstrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        queryParam.put(DB_PARAM_GLBL_CMPY_CD_ABR, this.cusaGlblCmpyCd);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countUpdatedRossContractUsageForUpdate", queryParam);
        return (result.intValue() > 0);
    }

    private boolean isExistDealer(String svcDlrCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(DB_PARAM_RTL_DLR_CD, svcDlrCd);

        BigDecimal result = (BigDecimal) this.glSsmBatchClient.queryObject("countDealer", queryParam);
        return (result.intValue() > 0);
    }

    private void createRossIntfcContrUsg(BigDecimal bllgMstrPk, BigDecimal rossIntfcContrPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, this.cusaGlblCmpyCd);
        queryParam.put(DB_PARAM_BLLG_MSTR_PK, bllgMstrPk);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.glSsmBatchClient.queryObjectList("getCsaUsg", queryParam);

        for (Map<String, Object> record : resultList) {

            ROSS_INTFC_CONTR_USGTMsg tMsg = new ROSS_INTFC_CONTR_USGTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrUsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_INTFC_CONTR_USG_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcContrPk, rossIntfcContrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrPk, (BigDecimal) getDBColumnValue(record, DB_COLUMN_BLLG_MSTR_PK));
            ZYPEZDItemValueSetter.setValue(tMsg.mlyCopyInclCompQty, (BigDecimal) getDBColumnValue(record, DB_COLUMN_MLY_COPY_INCL_COMP_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.mtrCnt, (BigDecimal) getDBColumnValue(record, DB_COLUMN_MTR_CNT));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrBllgMtrPk, (BigDecimal) getDBColumnValue(record, DB_COLUMN_BLLG_MSTR_BLLG_MTR_PK));
            ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCompCopyQty, (BigDecimal) getDBColumnValue(record, DB_COLUMN_XS_MTR_COMP_COPY_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCompAmtRate, (BigDecimal) getDBColumnValue(record, DB_COLUMN_XS_MTR_COMP_AMT_RATE));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrBllgMtrCratDt, (String) getDBColumnValue(record, DB_COLUMN_BLLG_MSTR_BLLG_MTR_CRAT_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgMstrBllgMtrModDt, (String) getDBColumnValue(record, DB_COLUMN_BLLG_MSTR_BLLG_MTR_MOD_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCratDt, (String) getDBColumnValue(record, DB_COLUMN_PMT_TERM_CRAT_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermModDt, (String) getDBColumnValue(record, DB_COLUMN_PMT_TERM_MOD_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgXsCopyProcCratDt, (String) getDBColumnValue(record, DB_COLUMN_BLLG_XS_COPY_PRC_CRAT_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.bllgXsCopyProcModDt, (String) getDBColumnValue(record, DB_COLUMN_BLLG_XS_COPY_PRC_MOD_DT));

            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0032E, new String[] {tMsg.getTableName()});
            }
        }
    }

    private Object getDBColumnValue(Map<String, Object> record, String columnName) {
        if (record != null) {
            return record.get(columnName);
        } else {
            return null;
        }
    }

    private boolean equalValue(String a, String b) {
        if (a == null) {
            return (b == null);
        } else {
            return a.equals(b);
        }
    }

    private boolean equalValue(BigDecimal a, BigDecimal b) {
        if (a == null) {
            return (b == null);
        } else {
            return (a.compareTo(b) == 0);
        }
    }

    private boolean equalDate(String a, String b) {
        if (a == null) {
            return (b == null);
        } else {
            return (ZYPDateUtil.compare(a, b) == 0);
        }
    }
}
