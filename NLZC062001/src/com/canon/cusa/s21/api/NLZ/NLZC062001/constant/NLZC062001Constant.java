/* <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NLZ.NLZC062001.constant;

/**
 * <pre>
 * Tech-PI Approval from WF API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/16/2016   CITS            K.Ogino         Create          N/A
 * 11/16/2016   CITS            S.Endo          Update          QC#14479
 * 11/16/2016   CITS            S.Endo          Update          QC#27665
 *</pre>
 */
public class NLZC062001Constant {

    /** An error occure in NPXC001001CreateApprovalHistory. */
    public static final String NPZM0212E = "NPZM0212E";

    /** Physical Inventory Control record is not found. */
    public static final String NLCM0154E = "NLCM0154E";

    /** An error occure in [NLZC0040 Adjustment Order API] */
    public static final String NLCM0165E = "NLCM0165E";

    /** The approver is not found. Please set up it at workflow. */
    public static final String NLCM0168E = "NLCM0168E";

    /** Undefined value is set in Process mode. */
    public static final String NLCM0169E = "NLCM0169E";

    /**
     * Failed to update "PHYS_INVTY_CTRL".
     */
    public static final String NLCM0163E = "NLCM0163E";

    /**
     * Failed to register PHYS_INVTY_ADJ.
     */
    public static final String NLZM2453E = "NLZM2453E";

    /**
     * Failed to update PHYS_INVTY_CNT_HDR.
     */
    public static final String NLZM2454E = "NLZM2454E";

    /**
     * Failed to update PHYS_INVTY_CNT_DTL.
     */
    public static final String NLZM2455E = "NLZM2455E";

    /**
     * The status @ for PI# @ is invalid. It must be OPEN.
     */
    public static final String NLZM2456E = "NLZM2456E";

    /**
     * The WH does not set.
     */
    public static final String NLZM2457E = "NLZM2457E";

    /**
     * The item @ does not exist in Master.
     */
    public static final String NLZM2458E = "NLZM2458E";

    /**
     * The Serial# @ is unnecessary for a non-serialized item @.
     */
    public static final String NLZM2459W = "NLZM2459W";

    /**
     * The quantity @ for @ is not invalid.
     */
    public static final String NLZM2460E = "NLZM2460E";

    /**
     * The Serial# is mandatory for a serialized item @.
     */
    public static final String NLZM2461E = "NLZM2461E";

    /**
     * Inconsistent data. TableID. Attribute[@], Key[@], Value[@]
     */
    public static final String NLCM0049E = "NLCM0049E";

    /**
     * Adjustment Order can't be created because inventory is not enough. Location: [@], MDSE: [@], SS: [@], Loc Status: [@], Order Qty: [@]
     */
    public static final String NLGM0078E = "NLGM0078E";

    /** Return Code : 0 (Normal) */
    public static final String RTRN_CD_NORMAL = "0";

    /** Date Format */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    /** Approve */
    public static final String APPROVE = "APPROVE";

    /** Reject */
    public static final String REJECT = "REJECT";

    /** Cancel */
    public static final String CANCEL = "CANCEL";

    /** CREATE_CLOSE */
    public static final String CREATE_CLOSE = "5";

    /** HEADER */
    public static final String HEADER = "H";

    /** DETAIL */
    public static final String DETAIL = "D";

    /** COMMENT_SUBSTR_INDEX */
    public static final int COMMENT_SUBSTR_INDEX = 64;

    /** STOCK_IN */
    public static final String STOCK_IN = "I";

    /** STOCK_OUT */
    public static final String STOCK_OUT = "O";

    /** Message type is Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** BIND_PARAM */
    public static final String BIND_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_PHYS_INVTY_CNTRL_PK = "physInvtyCntrlPk";

    /** BIND_PARAM */
    public static final String BIND_PARAM_PHYS_INVTY_NUM = "physInvtyNum";

    /** BIND_PARAM */
    public static final String BIND_PARAM_WH_CD = "whCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_MDSE_CD = "mdseCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_ADJ_VAR_FLG = "adjVarFlg";

    /** BIND_PARAM */
    public static final String BIND_PARAM_LOC_STS_CD = "locStsCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_LOC_TP_CD = "locTpCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_PHYS_INVTY_ADJ_STS_CD = "physInvtyAdjStsCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_PHYS_INVTY_ADJ_NM = "physInvtyAdjNm";

    /** BIND_PARAM */
    public static final String BIND_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_STK_STS_CD = "stkStsCd";

    /** BIND_PARAM */
    public static final String BIND_PARAM_PHYS_INVTY_CTRL_PK = "physInvtyCtrlPk";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_PHYS_INVTY_CNT_HDR_PK = "PHYS_INVTY_CNT_HDR_PK";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_PHYS_INVTY_CTRL_PK = "PHYS_INVTY_CTRL_PK";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_MDSE_CD = "MDSE_CD";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_RTL_WH_CD = "RTL_WH_CD";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_WH_CD = "WH_CD";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_ADJ_VAR_QTY = "ADJ_VAR_QTY";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_ADJ_NET_AMT = "ADJ_NET_AMT";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_TOT_VAR_NET_AMT = "TOT_VAR_NET_AMT";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_TOT_VAR_GRS_AMT = "TOT_VAR_GRS_AMT";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_STK_STS_CD = "STK_STS_CD";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_SER_NUM = "SER_NUM";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_RCV_SER_TAKE_FLG = "RCV_SER_TAKE_FLG";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_TOT_ADJ_VAR_GRS_AMT = "TOT_ADJ_VAR_GRS_AMT";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_TOT_ADJ_VAR_NET_AMT = "TOT_ADJ_VAR_NET_AMT";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_SUB_TOT_ADJ_VAR_QTY = "SUB_TOT_ADJ_VAR_QTY";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_SUB_TOT_ADJ_VAR_AMT = "SUB_TOT_ADJ_VAR_AMT";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_PHYS_INVTY_STS_CD = "PHYS_INVTY_STS_CD";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_MSTR_MDSE_CD = "MSTR_MDSE_CD";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_PHYS_INVTY_CNT_DTL_PK = "PHYS_INVTY_CNT_DTL_PK";

    /** DB_RESULT_COL */
    public static final String DB_RESULT_COL_PHYS_INVTY_STS_NM = "PHYS_INVTY_STS_NM";

    /** DB_RESULT_COL Mod. QC#27665 */
    public static final String DB_RESULT_COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** TABLE ID **/
    public static final String TBL_ID_PHYS_INVTY_CTRL = "PHYS_INVTY_CTRL";
    /** COL_NAME ON PHYS_INVTY_CTRL **/
    public static final String COL_NM_LOC_TP_CD = "LOC_TP_CD";
    /** COL_NAME ON PHYS_INVTY_CTRL **/
    public static final String COL_NM_PHYS_INVTY_NUM = "PHYS_INVTY_NUM";

    /** NLZC0040 Adjustment Order API Call Max Line Count */
    public static final int LINE_MAX_COUNT = 100;

    /** NLZC0040 Adjustment Order API Call Max Serial Count */
    public static final int SERIAL_MAX_COUNT = 100;

    /** ADJ_VAR_SGN_CD(+) */
    public static final String ADJ_VAR_SGN_CD_PLUS = "+";

    /** ADJ_VAR_SGN_CD(-) */
    public static final String ADJ_VAR_SGN_CD_MINUS = "-";

    // =================================================
    // Mail Setting
    // =================================================
    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = "NLZC0620M001";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Prefix PI Adjust Name for createApprovalHistory **/
    public static final String PI_ADJ_NM_PREFIX = "PI ADJ NM:";

    /** Prefix Comment for createApprovalHistory **/
    public static final String COMMENT_PREFIX = "Comment:";

}
