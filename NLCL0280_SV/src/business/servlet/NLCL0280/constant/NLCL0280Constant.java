/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NLCL0280Constant {

    /*
     * Application Data
     */

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NLCL0280";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NLCL0280T020";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NLCL0280Scrn00";

    /**
     * FUNC_SRCH
     */
    public static final String FUNC_SRCH = "20";

    /**
     * FUNC_UPD
     */
    public static final String FUNC_UPD = "40";

    /**
     * Month(30 Days)
     */
    public static final int DAY_OF_ONE_MONTH = 30;

    /*
     * Event Name
     */
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /*
     * Popup Parameter
     */

    /**
     * Item Master Search Popup : Mode Code = All Item
     */
    public static final String MODE_CODE_ALL_ITEM = "AL";

    /**
     * Common Popup : TBL_NM = COA_PROD
     */
    public static final String TBL_NM_COA_PROD = "COA_PROD";

    /**
     * Common Popup : TBL_CD_COLUMN_CD = COA_PROD_CD
     */
    public static final String TBL_CD_COLUMN_CD_COA_PROD_CD = "COA_PROD_CD";

    /**
     * Common Popup : TBL_CD_COLUMN_NM = COA_PROD_DESC_TXT
     */
    public static final String TBL_CD_COLUMN_NM_COA_PROD_DESC_TXT = "COA_PROD_DESC_TXT";

    /**
     * Common Popup : TBL_SORT_NUM_COLUMN_NM = COA_PROD_CD
     */
    public static final String TBL_SORT_NUM_COLUMN_NM_COA_PROD_CD = "COA_PROD_CD";

    /**
     * Common Popup : SCR_NM = Product Popup
     */
    public static final String SCR_NM_COA_PROD = "Product Popup";

    /**
     * Common Popup : HDR_CD_LABEL = Product Code
     */
    public static final String HDR_CD_LABEL_COA_PROD = "Product Code";

    /**
     * Common Popup : SCR_NM = Product Name
     */
    public static final String HDR_NM_LABEL_COA_PROD = "Product Name";

    /**
     * Common Popup : DTL_CD_LABEL = Product Code
     */
    public static final String DTL_CD_LABEL_COA_PROD = "Product Code";

    /**
     * Common Popup : DTL_NM_LABEL_COA_PROD = Product Name
     */
    public static final String DTL_NM_LABEL_COA_PROD = "Product Name";

    /**
     * Common Popup : TBL_NM = VND
     */
    public static final String TBL_NM_VND = "PO_VND_V";

    /**
     * Common Popup : TBL_CD_COLUMN_CD = VND_CD
     */
    public static final String WHERE_SQL_NM_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Common Popup : TBL_CD_COLUMN_NM = DPLY_VND_NM
     */
    public static final String WHERE_SQL_NM_FOR_SUPPLIER_SITE_NAME = "DPLY_VND_NM";

    /**
     * Common Popup : SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE = VND_CD
     */
    public static final String SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Common Popup : SORT_COLUMN_NAME_FOR_SUPPLIER_CODE = PRNT_VND_CD
     */
    public static final String SORT_COLUMN_NAME_FOR_SUPPLIER_CODE = "PRNT_VND_CD";

    /**
     * Common Popup : SORT_CONDITION_FOR_SUPPLIER_CODE = ASC
     */
    public static final String SORT_CONDITION_FOR_SUPPLIER_CODE = "ASC";

    /**
     * Common Popup : SORT_CONDITION_FOR_SUPPLIER_SITE_CODE = ASC
     */
    public static final String SORT_CONDITION_FOR_SUPPLIER_SITE_CODE = "ASC";

    /**
     * Common Popup : SCR_NM = Supplier Popup
     */
    public static final String SCR_NM_VND = "Supplier Popup";

    /**
     * Common Popup : SCR_NM = Cross Refference Item Popup
     */
    public static final String SCR_NM_CROSS_REF = "Cross Refference Item Popup";

    /**
     * Common Popup : HDR_CD_LABEL = Supplier Code
     */
    public static final String WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE = "Supplier Site Code";

    /**
     * Common Popup : SCR_NM = Product Name
     */
    public static final String WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME = "Supplier Site Name";

    /**
     * Common Popup : DTL_CD_LABEL = Supplier Code
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_CODE = "Supplier Code";

    /**
     * Common Popup : DTL_CD_LABEL = Supplier Site Code
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE = "Supplier Site Code";

    /**
     * Common Popup : DTL_NM_LABEL = Supplier Site Name
     */
    public static final String COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME = "Supplier Site Name";

    /**
     * Common Popup : COLUMN_SQL_NM_FOR_SUPPLIER_CODE
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_CODE = "PRNT_VND_CD";

    /**
     * Common Popup : COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE = "VND_CD";

    /**
     * Common Popup : COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME
     */
    public static final String COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME = "DPLY_VND_NM";

    /**
     * Common Popup : COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE = new BigDecimal(20);

    /**
     * Common Popup : COLUMN_WIDTH_FOR_SUPPLIER_CODE
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_CODE = new BigDecimal(20);

    /**
     * Common Popup : COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME
     */
    public static final BigDecimal COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME = new BigDecimal(52);

    /**
     * Common Popup : SCRN_NM_SHIP_FROM = Ship From Location Popup
     */
    public static final String SCRN_NM_SHIP_FROM = "Ship From Location Popup";

    /**
     * Common Popup : SCRN_NM_SHIP_TO = Ship To Location Popup
     */
    public static final String SCRN_NM_SHIP_TO = "Ship To Location Popup";

    /**
     * Common Popup : WHERE_DISP_NM_CD_FOR_LOC_CD_FROM
     */
    public static final String WHERE_DISP_NM_CD_FOR_LOC_CD_FROM = "Ship From Loc Code";

    /**
     * Common Popup : WHERE_DISP_NM_CD_FOR_LOC_CD_TO
     */
    public static final String WHERE_DISP_NM_CD_FOR_LOC_CD_TO = "Ship To Loc Code";

    /**
     * Common Popup : WHERE_DISP_NM_NM_FOR_LOC_CD
     */
    public static final String WHERE_DISP_NM_NM_FOR_LOC_CD = "LOC_CD";

    /**
     * Common Popup : WHERE_DISP_NM_CD_FOR_LOC_NM_FROM
     */
    public static final String WHERE_DISP_NM_CD_FOR_LOC_NM_FROM = "Ship From Loc Name";

    /**
     * Common Popup : WHERE_DISP_NM_CD_FOR_LOC_NM_TO
     */
    public static final String WHERE_DISP_NM_CD_FOR_LOC_NM_TO = "Ship To Loc Name";

    /**
     * Common Popup : WHERE_DISP_NM_NM_FOR_LOC_NM
     */
    public static final String WHERE_DISP_NM_NM_FOR_LOC_NM = "LOC_NM";

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LOC_CD_FROM
     */
    public static final String COLUMN_DISP_NM_CD_FOR_LOC_CD_FROM = "Ship From Loc Code";

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LOC_CD_TO
     */
    public static final String COLUMN_DISP_NM_CD_FOR_LOC_CD_TO = "Ship To Loc Code";

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LOC_CD
     */
    public static final String COLUMN_SQL_NM_CD_FOR_LOC_CD = "LOC_CD";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_LOC_CD
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_LOC_CD = new BigDecimal(15);

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LOC_NM_FROM
     */
    public static final String COLUMN_DISP_NM_CD_FOR_LOC_NM_FROM = "Ship From Loc Name";

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LOC_NM_TO
     */
    public static final String COLUMN_DISP_NM_CD_FOR_LOC_NM_TO = "Ship To Loc Name";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_LOC_NM
     */
    public static final String COLUMN_SQL_NM_CD_FOR_LOC_NM = "LOC_NM";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FORLOC_NM
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_LOC_NM = new BigDecimal(25);

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LOC_TP_NM
     */
    public static final String COLUMN_DISP_NM_CD_FOR_LOC_TP_NM = "Location Type";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_LOC_TP_NM
     */
    public static final String COLUMN_SQL_NM_CD_FOR_LOC_TP_NM = "LOC_TP_NM";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_LOC_TP_NM
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_LOC_TP_NM = new BigDecimal(15);

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_LINE_ADDR
     */
    public static final String COLUMN_DISP_NM_CD_FOR_LINE_ADDR = "Address";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_LINE_ADDR
     */
    public static final String COLUMN_SQL_NM_CD_FOR_LINE_ADDR = "LINE_ADDR";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_LINE_ADDR
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_LINE_ADDR = new BigDecimal(30);

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_CTY_ADDR
     */
    public static final String COLUMN_DISP_NM_CD_FOR_CTY_ADDR = "City";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_CTY_ADDR
     */
    public static final String COLUMN_SQL_NM_CD_FOR_CTY_ADDR = "CTY_ADDR";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_CTY_ADDR
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_CTY_ADDR = new BigDecimal(15);

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_ST_CD
     */
    public static final String COLUMN_DISP_NM_CD_FOR_ST_CD = "State";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_ST_CD
     */
    public static final String COLUMN_SQL_NM_CD_FOR_ST_CD = "ST_CD";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_ST_CD
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_ST_CD = new BigDecimal(5);

    /**
     * Common Popup : COLUMN_DISP_NM_CD_FOR_POST_CD
     */
    public static final String COLUMN_DISP_NM_CD_FOR_POST_CD = "Post";

    /**
     * Common Popup : COLUMN_SQL_NM_CD_FOR_POST_CD
     */
    public static final String COLUMN_SQL_NM_CD_FOR_POST_CD = "POST_CD";

    /**
     * Common Popup : COLUMN_WIDTH_CD_FOR_POST_CD
     */
    public static final BigDecimal COLUMN_WIDTH_CD_FOR_POST_CD = new BigDecimal(15);

    /**
     * Common Popup : SORT_NAME_FOR_LOC_CD
     */
    public static final String SORT_NAME_FOR_LOC_CD = "LOC_CD";

    /**
     * Common Popup : SORT_NAME_FOR_LOC_CD
     */
    public static final String SORT_VAL_FOR_LOC_CD = "ASC";

    /*
     * Screen Item
     */

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    public static final String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    public static final String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    public static final String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    public static final String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    public static final String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    public static final String[] BTN_CMN_DETELE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** BTN_SAVE_SEARCH */
    public static final String BTN_SAVE_SEARCH = "Save_Search";

    /** BTN_DELETE_SEARCH */
    public static final String BTN_DELETE_SEARCH = "Delete_Search";

    /** BTN_SEARCH_PROD_INFO */
    public static final String BTN_SEARCH_PROD_INFO = "Search_ProdInfo";

    /** BTN_SEARCH_RTL_WH_INFO */
    public static final String BTN_SEARCH_RTL_WH_INFO = "Search_RtlWHInfo";

    /** BTN_SEARCH_RTL_SWH_INFO */
    public static final String BTN_SEARCH_RTL_SWH_INFO = "Search_RtlSWHInfo";

    /** BTN_SEARCH_VND_INFO */
    public static final String BTN_SEARCH_VND_INFO = "Search_VndInfo";

    /** BTN_SEARCH_SHIP_FROM_INFO */
    public static final String BTN_SEARCH_SHIP_FROM_INFO = "Search_ShipLocInfoFrom";

    /** BTN_SEARCH_SHIP_TO_INFO */
    public static final String BTN_SEARCH_SHIP_TO_INFO = "Search_ShipLocInfoTo";

    /** BTN_SEARCH */
    public static final String BTN_SEARCH = "Search";

    /** BTN_OPEN_JRNL_ENTRY */
    public static final String BTN_OPEN_JRNL_ENTRY = "Open_JrnlEntrySearch";

    /*
     * Message
     */

    /**
     * Please specify @ period within one month.
     */
    public static final String NLCM0134E = "NLCM0134E";

    /**
     * [@] or [@] must be entered.
     */
    public static final String NLCM0135E = "NLCM0135E";

    /**
     * If [@] is set, [@] must be entered.
     */
    public static final String NLCM0136E = "NLCM0136E";

    /**
     * The value for [@] must be bigger than [@].
     */
    public static final String NLZM2277E = "NLZM2277E";

    /**
     * Please set at least one search criteria.
     */
    public static final String NLZM2276E = "NLZM2276E";

    /**
     * Please execute again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NLZM2274E : The process [@] has been successfully completed.
     */
    public static final String NLZM2274E = "NLZM2274E";

}
