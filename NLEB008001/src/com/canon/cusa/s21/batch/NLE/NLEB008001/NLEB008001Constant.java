/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB008001;

import java.math.BigDecimal;

/**
 * <pre>
 * The class explanation: Constant variable for NLEB008001.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/02/2016   Fujitsu         Y.Taoka         Create          N/A
 * 11/07/2017   CITS            K.Ogino         Update          QC#22263
 * </pre>
 */
public class NLEB008001Constant {

    /** business_id : NLEB0080 */
    static final String BUSINESS_ID = "NLEB008001";

    /***************************
     * Message parts
     ***************************/

    /**
     * Error Msg : It failed to get @. @
     */
    static final String NLEM0001E = "NLEM0001E";

    /**
     * Error Msg : @ has not been set up for @. @
     */
    static final String NLEM0002E = "NLEM0002E";

    /**
     * Error Msg : Data Global Company Code" does not exist in the
     * Master.
     */
    static final String NWZM0650E = "NWZM0650E";

    /**
     * Error Msg : There is no data subject to be locked and therefore data lock cannot be done.<Table[@] Key[@]>
     */
    static final String NLEM0019E = "NLEM0019E";

    /**
     * Error Msg : It failed to register [@]. <Table[@] Key[@]>
     */
    static final String NLEM0020E = "NLEM0020E";

    /**
     * Error Msg : It failed to update [@]. <Table[@] Key[@]>
     */
    static final String NLEM0021E = "NLEM0021E";

    /***************************
     * DB Columns
     ***************************/

    /** db column : GLBL_CMPY_CD */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** db column : DS_ASSET_MSTR_PK */
    static final String DS_ASSET_MSTR_PK = "DS_ASSET_MSTR_PK";

    /** db column : CUR_VAL_AMT */
    static final String CUR_VAL_AMT = "CUR_VAL_AMT";

    /** db column : LAST_DEPC_YR_MTH */
    static final String LAST_DEPC_YR_MTH = "LAST_DEPC_YR_MTH";

    /** db column : DEPC_CNT */
    static final String DEPC_CNT = "DEPC_CNT";

    /** db column : DEPC_MTH_NUM */
    static final String DEPC_MTH_NUM = "DEPC_MTH_NUM";

    /** db column : RSDL_VAL_AMT */
    static final String RSDL_VAL_AMT = "RSDL_VAL_AMT";

    /** db column : DEPC_RE_START_DT */
    static final String DEPC_RE_START_DT = "DEPC_RE_START_DT";

    /** db column : FIRST_DEPC_YR_MTH */
    static final String FIRST_DEPC_YR_MTH = "FIRST_DEPC_YR_MTH";

    /** db column : FIRST_BATCH */
    static final String FIRST_BATCH_MTH = "FIRST_BATCH_MTH";

    /** db column : RESTART_BATCH */
    static final String RESTART_BATCH_MTH = "RESTART_BATCH_MTH";

    /** db column : LAST_BATCH */
    static final String LAST_BATCH_MTH = "LAST_BATCH_MTH";

    /** db column : PRNT_DS_ASSET_MSTR_PK */
    static final String PRNT_DS_ASSET_MSTR_PK = "PRNT_DS_ASSET_MSTR_PK";

    /** db column : DEPC_SMLTN_RQST_DT_TM_TS */
    static final String DEPC_SMLTN_RQST_DT_TM_TS = "DEPC_SMLTN_RQST_DT_TM_TS";

    /** db column : DS_ASSET_DEPC_SMLTN_RQST_PK */
    static final String DS_ASSET_DEPC_SMLTN_RQST_PK = "DS_ASSET_DEPC_SMLTN_RQST_PK";

    /** db column : MDSE_CD */
    static final String MDSE_CD = "MDSE_CD";

    /** db column : SER_NUM */
    static final String SER_NUM = "SER_NUM";

    /** db column : ASSET_TP_CD */
    static final String ASSET_TP_CD = "ASSET_TP_CD";

    /** db column : INIT_BOOK_AMT */
    static final String INIT_BOOK_AMT = "INIT_BOOK_AMT";

    /***************************
     * SSM Parameter
     ***************************/

    /** SQL Bind Name for SSM : GLBL_CMPY_CD */
    static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Bind Name for SSM : BATCH_YEAR_MONTH */
    static final String BIND_BATCH_YEAR_MONTH = "batchYrMth";

    /** SQL Bind Name for SSM : Active Asset Flag */
    static final String BIND_ACTV_ASSET_FLG = "actvAssetFlg";

    /** SQL Bind Name for SSM : Depreciation Location Type (Both) */
    static final String BIND_DEPC_LOC_TP_BOTH = "depcLocTpBoth";

    /** SQL Bind Name for SSM : Depreciation Location Type (Customer) */
    static final String BIND_DEPC_LOC_TP_CUST = "depcLocTpCust";

    /** SQL Bind Name for SSM : Depreciation Location Type (Warehouse) */
    static final String BIND_DEPC_LOC_TP_WH = "depcLocTpWh";

    /** SQL Bind Name for SSM : Asset Status Code */
    static final String BIND_ASSET_STS = "assetStsCd";

    /** SQL Bind Name for SSM : DEPC_SMLTN_RQST_STS_CD */
    static final String BIND_DEPC_SMLTN_RQST_STS = "depcSmltnRqstStsCd";

    /***************************
     * VAR_CHAR_CONST Key
     ***************************/

    /** Key : MLY_ASSET_DEPC_MODE */
    static final String MLY_ASSET_DEPC_MODE = "MLY_ASSET_DEPC_MODE";

    /** Key : MLY_ASSET_DEPC_MODE */
    static final String MLY_ASSET_DEPC_MODE_ONE_TRX = "00";

    /** Key : MLY_ASSET_DEPC_MODE */
    static final String MLY_ASSET_DEPC_MODE_SUM_TRX = "01";

    /** Key : MLY_ASSET_DEPC_MODE */
    static final String MLY_ASSET_DEPC_MODE_ROSS_TRX = "02";

    /** Key : MLY_ASSET_DEPC_MODE */
    static final String MLY_ASSET_DEPC_MODE_SEPT_TRX = "03";

    /** Key NLEB0080_PROC_DEPC_SMLTN_RQST_STS_CD:  */
    static final String PROC_DEPC_SMLTN_RQST_STS_CD = "NLEB0080_DEPC_SMLTN_RQST_STS";

    /** Key DEPC_SMLTN_RQST_STS_COMP:  */
    static final String COMP_DEPC_SMLTN_RQST_STS_CD = "DEPC_SMLTN_RQST_STS_COMP";

    /** Key : DEPC_DECIMAL_PLACE */
    static final String DEPC_DECIMAL_PLACE = "DEPC_DECIMAL_PLACE";

    /** Key : DEPC_ROUNDING_MODE */
    static final String DEPC_ROUNDING_MODE = "DEPC_ROUNDING_MODE";

    /** Cost Calculation Mode : Round Up */
    static final BigDecimal ROUND_UP = BigDecimal.valueOf(1);

    /** Cost Calculation Mode : Round Half Up */
    static final BigDecimal ROUND_HALF_UP = BigDecimal.valueOf(2);

}
