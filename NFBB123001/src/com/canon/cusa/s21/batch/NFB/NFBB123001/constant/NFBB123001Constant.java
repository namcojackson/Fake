/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB123001.constant;

/**
 *<pre>
 * NFBB1230:Invoice Import For CUSA ROSS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2016   CITS            Y.Nomura        Create          N/A
 * 07/14/2016   Hitachi         T.Tsuchida      Update          QC#11625
 * 09/28/2016   Hitachi         K.Kasai         Update          QC#14798
 *</pre>
 */
public class NFBB123001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NFBB123001";

    /** status code normal */
    public static final String ROSS_STS_CD_NORMAL = "00";

    /** EDI_CUST_TP_CD for sell to */
    public static final String EDI_CUST_TP_CD_SELL_TO = "1";

    /** EDI_CUST_TP_CD for sell to */
    public static final String EDI_CUST_TP_CD_SHIP_TO = "2";

    /** default value from date */
    public static final String DEFAULT_EFF_FROM_DT = "19000101";

    /** default value thru date */
    public static final String DEFAULT_EFF_THRU_DT = "99991231";

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name : CUSA EDI Trading Partner Code */
    public static final String CUSA_EDI_TRD_PTNR_CD = "CUSA_EDI_TRD_PTNR_CD";
    // START 07/14/2016 T.Tsuchida [QC#11625,ADD]
    /** Var Char Const Name : CUSA EDI Dummy Merchandise Code */
    public static final String CUSA_EDI_DUMMY_MDSE_CD = "CUSA_EDI_DUMMY_MDSE_CD";
    // END 07/14/2016 T.Tsuchida [QC#11625,ADD]

    // =================================================
    // Message Code
    // =================================================
    /** Failed to insert [@]. */
    public static final String NFDM0013E = "NFDM0013E";

    /** A value is not set in the required field. Field Name: [@] */
    public static final String NLAM1118E = "NLAM1118E";

    /** "Data Global Company Code" does not exist in the Master. */
    public static final String NWZM0650E = "NWZM0650E";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpCd";

    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD_CUSA = "glblCmpCdCusa";

    /** . */
    public static final String DB_PARAM_RTL_INV_APVL_DT = "execDate";

    /** . */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /** . */
    public static final String DB_PARAM_EDI_CUST_TP_CD_SELL_TO = "sellTo";

    /** . */
    public static final String DB_PARAM_EDI_CUST_TP_CD_SHIP_TO = "shipTo";

    /** . */
    public static final String DB_PARAM_ACTV_FLG = "actvFlg";

    /** . */
    public static final String DB_PARAM_EDI_TRD_PTNR_CD = "ediTrdPtnrCd";

    /** . */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDtDefault";

    /** . */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDtDefault";

    // START 07/14/2016 T.Tsuchida [QC#11625,ADD]
    /** . */
    public static final String DB_PARAM_DUMMY_MDSE_CD = "dummyMdseCd";
    // END 07/14/2016 T.Tsuchida [QC#11625,ADD]

    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String RTL_INV_PK = "RTL_INV_PK";

    /** . */
    public static final String RTL_INV_LINE_PK = "RTL_INV_LINE_PK";

    /** . */
    public static final String RTL_INV_STS_CD = "RTL_INV_STS_CD";

    /** . */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** . */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** . */
    public static final String SER_NUM = "SER_NUM";

    /** . */
    public static final String MDL_NM = "MDL_NM";

    /** . */
    public static final String RTL_INV_APVL_DT = "RTL_INV_APVL_DT";

    /** . */
    public static final String BLLG_PER_FROM_DT = "BLLG_PER_FROM_DT";

    /** . */
    public static final String BLLG_PER_THRU_DT = "BLLG_PER_THRU_DT";

    /** . */
    public static final String RTL_INV_LINE_NUM = "RTL_INV_LINE_NUM";

    /** . */
    public static final String RTL_INV_CHRG_TP_DESC_TXT = "RTL_INV_CHRG_TP_DESC_TXT";

    /** . */
    public static final String SHIP_QTY = "SHIP_QTY";

    /** . */
    public static final String DEAL_GRS_UNIT_PRC_AMT = "DEAL_GRS_UNIT_PRC_AMT";

    /** . */
    public static final String SLS_TAX_RATE = "SLS_TAX_RATE";

    /** . */
    public static final String BLLG_BIZ_TP_CD = "BLLG_BIZ_TP_CD";

    /** . */
    public static final String RTL_DIV_CD = "RTL_DIV_CD";

    /** . */
    public static final String RTL_INV_NUM = "RTL_INV_NUM";

    /** . */
    public static final String ITRL_RTL_SMRY_INV_NUM = "ITRL_RTL_SMRY_INV_NUM";

    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String SVC_DLR_CD = "SVC_DLR_CD";

    /** . */
    public static final String INSTL_POST_CD = "INSTL_POST_CD";

    /** . */
    public static final String INSTL_CD = "INSTL_CD";

    /** . */
    public static final String ISTL_SUB_LOC_CD = "ISTL_SUB_LOC_CD";

    /** . */
    public static final String INV_LINE_CRAT_DT = "INV_LINE_CRAT_DT";

    /** . */
    public static final String INV_LINE_MOD_DT = "INV_LINE_MOD_DT";

    // START 2016/09/29 [QC#14798, ADD]
    /** . */
    public static final String RTL_INV_CHRG_TP_CD = "RTL_INV_CHRG_TP_CD";
    // END 2016/09/29 [QC#14798, ADD]

}
