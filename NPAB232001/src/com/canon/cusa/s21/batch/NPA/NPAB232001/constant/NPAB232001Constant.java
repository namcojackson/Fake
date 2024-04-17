/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB232001.constant;

/**
 * <pre>    
 * Baxter IF for ORDERSBPS
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 11/01/2022   CITS            K.Ogino         Create          QC#58990
 * 12/01/2021   CITS            K.Ogino         Create          QC#58990-1
 * 12/08/2021   CITS            K.Ogino         Create          QC#58990-2
 * 07/19/2022   CITS            F.Fadriquela    Update          QC#60274
 *</pre>
 */
public class NPAB232001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB2320";

    /** VAR_CHAR_CONST_NM : NPAB2310_ORDERSBPS_CRAT_USER */
    public static final String NPAB2310_ORDERSBPS_CRAT_USER = "NPAB2320_ORDERSBPS_CRAT_USER";

    /** MAI_GROUP_CD_FROM */
    public static final String ML_GRP_CD_FROM = "NPAB232001";

    /** MAI_GROUP_CD_TO */
    public static final String ML_GRP_CD_TO = "NPAB232002";

    /** ML_TMPL_ID */
    public static final String ML_TMPL_ID = "NPAB2320M001";

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

    /** Parameter Name : #glblCmpyCd# */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

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

    /** Column Name : RTL_WH_CD */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** Column Name : RTL_SWH_CD */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** Column Name : WH_OWNR_CD */
    public static final String WH_OWNR_CD = "WH_OWNR_CD";

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

    /** Column Name : PO_QTY */
    public static final String PO_QTY = "PO_QTY";

    /** Column Name : WH_RG_CD */
    public static final String WH_RG_CD = "WH_RG_CD";

    /** Column Name : IF_RTL_WH_CD */
    public static final String IF_RTL_WH_CD = "IF_RTL_WH_CD";

    /** Column Name : IF_MDSE_CD */
    public static final String IF_MDSE_CD = "IF_MDSE_CD";

    /** Column Name : DUE_DT */
    public static final String DUE_DT = "DUE_DT";

    /** Column Name : PRNT_VND_CD */
    public static final String PRNT_VND_CD = "PRNT_VND_CD";

    /** Column Name : VND_CD */
    public static final String VND_CD = "VND_CD";

    /** Column Name : UNIT_PRC_AMT */
    public static final String UNIT_PRC_AMT = "UNIT_PRC_AMT";

    /** Column Name : ASL_MDSE_CD */
    public static final String ASL_MDSE_CD = "ASL_MDSE_CD";

    /** Column Name : SPLY_ITEM_NUM */
    public static final String SPLY_ITEM_NUM = "SPLY_ITEM_NUM";

    /** Column Name : ASL_DTL_PK */
    public static final String ASL_DTL_PK = "ASL_DTL_PK";

    /** Column Name : PRIM_SPLY_SITE_TXT */
    public static final String PRIM_SPLY_SITE_TXT = "PRIM_SPLY_SITE_TXT";

    // Add QC#58990-1
    /** Column Name : SRC_APVL_ID */
    public static final String SRC_APVL_ID = "SRC_APVL_ID";

    // Add QC#58990-2
    /** Column Name : SRC_APVL_SQ */
    public static final String SRC_APVL_SQ = "SRC_APVL_SQ";

    // START 2022/07/18 F.Fadriquela [QC#60274, ADD]
    /** Column Name : RTL_WH_CATG_CD */
    public static final String RTL_WH_CATG_CD = "RTL_WH_CATG_CD";
    // END 2022/07/18 F.Fadriquela [QC#60274, ADD]

}
