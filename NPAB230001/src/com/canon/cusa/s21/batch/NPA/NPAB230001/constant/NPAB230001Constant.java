/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB230001.constant;

/**
 * <pre>    
 * Baxter IF for MINMAX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/11/2021   CITS            K.Ogino         Create          QC#58990
 * 12/23/2021   CITS            K.Ogino         Update          QC#58990-3
 * 02/01/2022   CITS            K.Ogino         Update          QC#59649
 * 07/11/2022   CITS            K.Ogino         Update          QC#60261
 *</pre>
 */
public class NPAB230001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB2300";
    // QC#59649 Mod
    // /** DS_COND_CONST_GRP_CD */
    // public static final String NPAB2300_MINMAX_SUP = "NPAB2300_MINMAX_SUP";

    /** VAR_CHAR_CONST_NM : NPAB2300_MRP_PLN_NM_PARTS */
    public static final String NPAB2300_MNX_MRP_PLN_NM_PARTS = "NPAB2300_MNX_MRP_PLN_NM_PARTS";

    /** MAI_GROUP_CD_FROM */
    public static final String ML_GRP_CD_FROM = "NPAB230001";

    /** MAI_GROUP_CD_TO */
    public static final String ML_GRP_CD_TO = "NPAB230002";

    /** ML_TMPL_ID */
    public static final String ML_TMPL_ID = "NPAB2300M001";

    /** Mail Language */
    public static final String ML_LANG = "en";

    /** Mail Language Code */
    public static final String ML_LANG_CD = "ISO-8859-1";

    // *********************************************************
    // Constants: Message ID
    // *********************************************************

    /** Message ID : ZZBM0009I */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : NPZM0161E It failed to register an email. */
    public static final String NPZM0161E = "NPZM0161E";

    /** Message ID : NPAM1171E Failed to update. [@] */

    public static final String NPAM1171E = "NPAM1171E";

    /** Message ID : NPAM1172E Failed to insert. [@]. */
    public static final String NPAM1172E = "NPAM1172E";

    // *********************************************************
    // Constants: Message Parameters
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Mail Parameter Name : batchId */
    public static final String EMAIL_PARAM_BATCH_ID = "batchId";

    /** Mail Parameter Name : message */
    public static final String EMAIL_PARAM_MESSAGE = "message";

    // *********************************************************
    // Constants: DB Parameters
    // *********************************************************

    /** Parameter Name : #whOwnrMNX# */
    public static final String DB_PARAM_WH_OWNR_MNX = "whOwnrMNX";

    /** Parameter Name : #glblCmpyCd# */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** Parameter Name : #dsCondConstGrpId# */
    public static final String DB_PARAM_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /** Parameter Name : #parts# */
    public static final String DB_PARAM_PARTS = "parts";

    /** Parameter Name : #interfaceId# */
    public static final String DB_PARAM_INTERFACE_ID = "interfaceId";

    /** Parameter Name : #transactionId# */
    public static final String DB_PARAM_TRANSACTION_ID = "transactionId";

    /** Parameter Name : #SalesDate# */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /** Parameter Name : #mdseCd# */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** Parameter Name : #rtlWhCd# */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** Parameter Name : #rtlSwhCd# */
    public static final String DB_PARAM_RTL_SWH_CD = "rtlSwhCd";

    /** Parameter Name : #rgtnStsCd# */
    public static final String DB_PARAM_RGTN_STS_CD = "rgtnStsCd";

    /** Parameter Name : #mrpPlnNm# */
    public static final String DB_PARAM_MRP_PLN_NM = "mrpPlnNm";

    // QC#58990-3
    /** Column Name : #mrpInfoRgtnStsCd# */
    public static final String DB_PARAM_MRP_INFO_RGTN_STS_CD = "mrpInfoRgtnStsCd";

    // Mod QC#60261
    /** Parameter Name : #rtlWhCatgList# */
    public static final String DB_PARAM_RTL_WH_CATG_LIST = "rtlWhCatgList";
    // Mod QC#60261
    /** Parameter Name : #srcRtlWhCd# */
    public static final String DB_PARAM_SRC_RTL_WH_CD = "srcRtlWhCd";

    // *********************************************************
    // Constants: DB Columns
    // *********************************************************

    /** Column Name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column Name : MIN_INVTY_QTY */
    public static final String MIN_INVTY_QTY = "MIN_INVTY_QTY";

    /** Column Name : MAX_INVTY_QTY */
    public static final String MAX_INVTY_QTY = "MAX_INVTY_QTY";

    /** Column Name : RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** Column Name : RTL_SWH_CD */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** Column Name : MRP_PLN_NM */
    public static final String MRP_PLN_NM = "MRP_PLN_NM";

    /** Column Name : ASL_DTL_PK */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";

    /** Column Name : WH_RG_CD */
    public static final String WH_RG_CD = "WH_RG_CD";

    /** Column Name : IF_MDSE_CD */
    public static final String IF_MDSE_CD = "IF_MDSE_CD";

    /** Column Name : MRP_INFO_PK */
    public static final String MRP_INFO_PK = "MRP_INFO_PK";

    /** Column Name : WH_OWNR_CD */
    public static final String WH_OWNR_CD = "WH_OWNR_CD";

    /** Column Name : RTL_WH_CATG_CD */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";

    // QC#58990-3
    /** Column Name : RPLSH_DLY_FLG */
    public static final String RPLSH_DLY_FLG = "RPLSH_DLY_FLG";

    /** Column Name : RPLSH_MON_FLG */
    public static final String RPLSH_MON_FLG = "RPLSH_MON_FLG";

    /** Column Name : RPLSH_TUE_FLG */
    public static final String RPLSH_TUE_FLG = "RPLSH_TUE_FLG";

    /** Column Name : RPLSH_WED_FLG */
    public static final String RPLSH_WED_FLG = "RPLSH_WED_FLG";

    /** Column Name : RPLSH_THU_FLG */
    public static final String RPLSH_THU_FLG = "RPLSH_THU_FLG";

    /** Column Name : RPLSH_FRI_FLG */
    public static final String RPLSH_FRI_FLG = "RPLSH_FRI_FLG";

    // Mod QC#60261
    /** Column Name : IF_RTL_WH_CD */
    public static final String IF_RTL_WH_CD = "IF_RTL_WH_CD";

}
