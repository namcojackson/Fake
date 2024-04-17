/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB005001;

import java.math.BigDecimal;

/**
 * <pre>
 * The class explanation: Constant variable for NLEB005001.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/18/2013   Fujitsu         Y.Taoka         Create          R-WH002 Serial Number Asset Management
 * 08/14/2013   CSAI            Y.Imazu         Update          Defect#1509
 * 01/21/2016   Fujitsu         Y.Taoka         Update          CSA
 * 11/07/2017   CITS            K.Ogino         Update          QC#22263
 * </pre>
 */
public class NLEB005001Constant {

    /** business_id : NLEB0050 */
    static final String BUSINESS_ID = "NLEB005001";

    /***************************
     * Message parts
     ***************************/

    /**
     * Error Msg : Global Company Code is mandatory.
     */
    static final String NASM0010E = "NASM0010E";

    /**
     * Error Msg : @ has not been set up for @. @
     */
    static final String NLEM0002E = "NLEM0002E";

    /**
     * Error Msg : System error has occurred : DS_ASSET_TRX_PK [@]
     */
    static final String NLZM2165E = "NLZM2165E";

    /**
     * Error Msg : "Data Global Company Code" does not exist in the
     * Master.
     */
    static final String NWZM0650E = "NWZM0650E";

    /**
     * Error Msg : Batch Operation Date cannot be obtained.
     */
    static final String NDMM0016E = "NDMM0016E";

    /**
     * Error Msg : Error occurred: [@] items
     */
    static final String NASM0009E = "NASM0009E";

    // Add 01/21/2016
    /**
     * Error Msg : System error has occurred : DS_ASSET_MSTR_PK [@]
     */
    static final String NLBM1335E = "NLBM1335E";

    /**
     * Error Msg :  @ is invalid. [@]
     */
    static final String NLBM1336E = "NLBM1336E";

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

    /** db column : INIT_BOOK_AMT */
    static final String INIT_BOOK_AMT = "INIT_BOOK_AMT";

    /***************************
     * SSM Parameter
     ***************************/

    /** SQL Bind Name for SSM : GLBL_CMPY_CD */
    static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Bind Name for SSM : BATCH_DATE */
    static final String BIND_BATCH_DATE = "batchDate";

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

    // Add 01/21/2016
    /** SQL Bind Name for SSM : Asset Status Code */
    static final String BIND_ASSET_STS = "assetStsCdList";

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

    /** Key : DEPC_DECIMAL_PLACE */
    static final String DEPC_DECIMAL_PLACE = "DEPC_DECIMAL_PLACE";

    /** Key : DEPC_ROUNDING_MODE */
    static final String DEPC_ROUNDING_MODE = "DEPC_ROUNDING_MODE";

    /** Cost Calculation Mode : Round Up */
    static final BigDecimal ROUND_UP = BigDecimal.valueOf(1);

    /** Cost Calculation Mode : Round Half Up */
    static final BigDecimal ROUND_HALF_UP = BigDecimal.valueOf(2);
}
