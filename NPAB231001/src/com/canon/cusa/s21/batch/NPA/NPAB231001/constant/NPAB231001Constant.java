/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB231001.constant;

/**
 * <pre>    
 * Baxter IF for MINMAX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/11/2022   CITS            K.Ogino         Create          QC#58990
 * 07/05/2021   CITS            F.Fadriquela    Create          QC#60262
 *</pre>
 */
public class NPAB231001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB2310";

    /** VAR_CHAR_CONST_NM : NPAB2310_REPLENORD_CRAT_USER */
    public static final String NPAB2310_REPLENORD_CRAT_USER = "NPAB2310_REPLENORD_CRAT_USER";

    /** MAI_GROUP_CD_FROM */
    public static final String ML_GRP_CD_FROM = "NPAB231001";

    /** MAI_GROUP_CD_TO */
    public static final String ML_GRP_CD_TO = "NPAB231002";

    /** ML_TMPL_ID */
    public static final String ML_TMPL_ID = "NPAB2310M001";

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

    /**
     * Message ID : NPAM1653E Error occurred while calling PR Update
     * API:[ @ ]. Please check error data : Interface Name [@].
     * Transaction Id[@].
     */
    public static final String NPAM1653E = "NPAM1653E";

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

    // *********************************************************
    // Constants: DB Columns
    // *********************************************************

    /** Column Name : MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Column Name : FR_RTL_WH_CD */
    public static final String FR_RTL_WH_CD = "FR_RTL_WH_CD";

    /** Column Name : FR_RTL_SWH_CD */
    public static final String FR_RTL_SWH_CD = "FR_RTL_SWH_CD";

    /** Column Name : FR_WH_OWNR_CD */
    public static final String FR_WH_OWNR_CD = "FR_WH_OWNR_CD";

    /** Column Name : TO_RTL_WH_CD */
    public static final String TO_RTL_WH_CD = "TO_RTL_WH_CD";

    /** Column Name : TO_RTL_SWH_CD */
    public static final String TO_RTL_SWH_CD = "TO_RTL_SWH_CD";

    /** Column Name : TO_WH_OWNR_CD */
    public static final String TO_WH_OWNR_CD = "TO_WH_OWNR_CD";

    /** Column Name : SHIP_TO_CUST_CD */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column Name : FIRST_LINE_ADDR */
    public static final String FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** Column Name : SCD_LINE_ADDR */
    public static final String SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** Column Name : THIRD_LINE_ADDR */
    public static final String THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** Column Name : FRTH_LINE_ADDR */
    public static final String FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** Column Name : LOC_NM */
    public static final String LOC_NM = "LOC_NM";

    /** Column Name : ADDL_LOC_NM */
    public static final String ADDL_LOC_NM = "ADDL_LOC_NM";

    /** Column Name : CTY_ADDR */
    public static final String CTY_ADDR = "CTY_ADDR";

    /** Column Name : ST_CD */
    public static final String ST_CD = "ST_CD";

    /** Column Name : PROV_NM */
    public static final String PROV_NM = "PROV_NM";

    /** Column Name : CNTY_NM */
    public static final String CNTY_NM = "CNTY_NM";

    /** Column Name : POST_CD */
    public static final String POST_CD = "POST_CD";

    /** Column Name : CTRY_CD */
    public static final String CTRY_CD = "CTRY_CD";

    /** Column Name : PRCH_REQ_QTY */
    public static final String PRCH_REQ_QTY = "PRCH_REQ_QTY";

    /** Column Name : SHIP_FR_WH_RG_CD */
    public static final String SHIP_FR_WH_RG_CD = "SHIP_FR_WH_RG_CD";

    /** Column Name : SHIP_FR_WH_SITE_CD */
    public static final String SHIP_FR_WH_SITE_CD = "SHIP_FR_WH_SITE_CD";

    /** Column Name : SHIP_TO_WH_RG_CD */
    public static final String SHIP_TO_WH_RG_CD = "SHIP_TO_WH_RG_CD";

    /** Column Name : SHIP_TO_WH_SITE_CD */
    public static final String SHIP_TO_WH_SITE_CD = "SHIP_TO_WH_SITE_CD";

    /** Column Name : IF_MDSE_CD */
    public static final String IF_MDSE_CD = "IF_MDSE_CD";

    // START 2022/07/05 F.Fadriquela [QC#60262, ADD]
    /** Column Name : TO_RTL_WH_CATG_CD */
    public static final String TO_RTL_WH_CATG_CD = "TO_RTL_WH_CATG_CD";

    /** Column Name : TO_TECH_TOC_CD */
    public static final String TO_TECH_TOC_CD = "TO_TECH_TOC_CD";

    /** Column Name : TECH_NM */
    public static final String TECH_NM = "TECH_NM";

    /** The data @ does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";

    /** DS_COND_CONST : DS_COND_CONST*/
    public static final String DS_COND_CONST = "DS_COND_CONST";

    /** DS_COND_CONST_GRP_ID : NPZC1170 */
    public static final String GRP_ID_NPZC1170 = "NPZC1170";

    /** DS_COND_CONST_CD : REQ_TP_MIN-MAX */
    public static final String REQ_TP_MINMAX = "REQ_TP_MIN-MAX";

    /** Date Format : hh:mm a */
    public static final String DATE_FORMAT_HHMM_AM = "hh:mm a";

    /** Date Format : HHmm*/
    public static final String DATE_FORMAT_HHMM = "HHmm";

    /** DEFAULT_TIME_STR : 0000*/
    public static final String DEFAULT_TIME_STR = "0000";
    // END 2022/07/05 F.Fadriquela [QC#60262, ADD]

}
