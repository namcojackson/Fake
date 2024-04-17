/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID :  NPBL0010 Inventory Request List
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS         Makoto Okigami     Create          N/A
 * 04/05/2016   CITS         K.Ogino            Update          N/A
 * 01/17/2017   CITS         T.Kikuhara         Update          QC#16256
 * 08/23/2017   CITS         H.Naoi             Update          Sol#097(QC#18398)
 * 03/23/2020   Fujitsu      R.Nakamura         Update          QC#55940
 *</pre>
 */
public class NPBL0010Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPBL0010";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NPBL0010T020";

    /**
     * Function Code : Search
     */
    public static final String FUNCTION_CD_SEARCH = "20";

    /**
     * Function Code : Update
     */
    public static final String FUNCTION_CD_UPDATE = "40";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPBL0010Scrn00";

    /**
     * USD Decimal Point (e.g. 50.25)
     */
    public static final int DECIMAL_POINT_USD = 2;

    // =================================================
    // Business Button Name
    // =================================================
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /**
     * Business button SaveSearch
     */
    public static final String BTN_SAVE_SEARCH = "SaveSearch";

    /**
     * Business button DeleteSearch
     */
    public static final String BTN_DELETE_SEARCH = "DeleteSearch";

    /**
     * Business button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Business button Add
     */
    public static final String BTN_NEW = "New";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : SRCH_OPT_NM
     */
    public static final String DISPLAY_NM_SRCH_OPT_NM = "Saved Option Name";

    /**
     * Display Name : PRCH_REQ_NUM
     */
    public static final String DISPLAY_NM_PRCH_REQ_NUM = "Request Number";

    /**
     * Display Name : PRCH_REQ_CRAT_DT_RF
     */
    public static final String DISPLAY_NM_PRCH_REQ_CRAT_DT_RF = "Date Created";

    /**
     * Display Name : PRCH_REQ_CRAT_DT_RT
     */
    public static final String DISPLAY_NM_PRCH_REQ_CRAT_DT_RT = "Date Created";

    /**
     * Display Name : PRCH_REQ_CRAT_DT_NF
     */
    public static final String DISPLAY_NM_RQST_RCV_DT_NF = "Date Needed";

    /**
     * Display Name : PRCH_REQ_CRAT_DT_NT
     */
    public static final String DISPLAY_NM_RQST_RCV_DT_NT = "Date Needed";

    /**
     * Display Name : TRX_REF_NUM
     */
    public static final String DISPLAY_NM_TRX_REF_NUM = "Source Doc#";

    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
    /**
     * Display Name : MRP_PLN_NM
     */
    public static final String DISPLAY_NM_MRP_PLN_NM = "Plan Name";
    //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END

    /**
     * Display Name : FULL_PSN_NM
     */
    public static final String DISPLAY_NM_FULL_PSN_NM = "Requester(*)";

    /**
     * Display Name : CARR_NM
     */
    public static final String DISPLAY_NM_CARR_NM = "Requested Carrier(*)";

    // Add Start 2020/03/23 QC#55940
    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item#(*)";
    // Add End 2020/03/23 QC#55940

    /**
     * Display Name : SRC_RTL_WH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_WH_CD = "Source WH(*)";

    /**
     * Display Name : RTL_WH_NM_SW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_SW = "Source WH(*)";

    /**
     * Display Name : SRC_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_SRC_RTL_SWH_CD = "Source SWH(*)";

    /**
     * Display Name : RTL_SWH_NM_SS
     */
    public static final String DISPLAY_NM_RTL_SWH_NM_SS = "Source SWH(*)";

    /**
     * Display Name : DEST_RTL_WH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_WH_CD = "Destination WH(*)";

    /**
     * Display Name : RTL_WH_NM_DW
     */
    public static final String DISPLAY_NM_RTL_WH_NM_DW = "Destination WH(*)";

    /**
     * Display Name : DEST_RTL_SWH_CD
     */
    public static final String DISPLAY_NM_DEST_RTL_SWH_CD = "Destination SWH(*)";

    /**
     * Display Name : RTL_SWH_NM_DS
     */
    public static final String DISPLAY_NM_RTL_SWH_NM_DS = "Destination SWH(*)";

    /**
     * Display Name : VND_CD
     */
    public static final String DISPLAY_NM_VND_CD = "Ship To Supplier Site(*)";

    /**
     * Display Name : LOC_NM
     */
    public static final String DISPLAY_NM_DPLY_VND_NM = "Ship To Supplier Name(*)";

    /**
     * Display Name : UNIT_PRICE
     */
    public static final String DISPLAY_NM_UNIT_PRICE = "Unit Price";

    /**
     * Display Name : UNIT_PRICE Vendor Return
     */
    public static final String DISPLAY_NM_UNIT_PRICE_VR = "Vnd Rtrn ARV Unit Cost";

    /**
     * Display Name : TOTAL_COST
     */
    public static final String DISPLAY_NM_TOTAL_COST = "Total Cost";
 
    /**
     * Display Name : TOTAL_COST Vendor Return
     */
    public static final String DISPLAY_NM_TOTAL_COST_VR = "Vnd Rtrn ARV Ttl Cost";

    /**
     * Display Name : SHIP_TO_CUST_CD Ship To Customer Code
     */
    public static final String DISPLAY_NM_SHIP_TO_CUST_CD = "Ship To Customer Code";

    /**
     * Display Name : DS_ACCT_NM Ship To Customer Name
     */
    public static final String DISPLAY_NM_DS_ACCT_NM = "Ship To Customer Name";

    // =================================================
    // Popup Param
    // =================================================
    /**
     * Param Value for NWAL1130
     */
    public static final String TBL_NM_FOR_AUTH_PSN = "AUTH_PSN";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_S21_PSN_V = "PSN_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_S21_PSN_V = "FULL_PSN_NM";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_S21_PSN_V = "PSN_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String SCR_NM_FOR_S21_PSN_V = "Requester Search Popup";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_S21_PSN_V = "Person Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_S21_PSN_V = "Person Name";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_S21_PSN_V = "Person Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_S21_PSN_V = "Person Name";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_FOR_OTBD_CARR_V = "OTBD_CARR_V";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_CD_COL_NM_FOR_OTBD_CARR_V = "CARR_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_NM_COL_NM_FOR_OTBD_CARR_V = "CARR_NM";

    /**
     * Param Value for NMAL6050
     */
    public static final String TBL_SORT_COL_NM_FOR_OTBD_CARR_V = "CARR_CD";

    /**
     * Param Value for NMAL6050
     */
    public static final String SCR_NM_FOR_OTBD_CARR_V = "Carrier Search Popup";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_CD_LB_NM_FOR_OTBD_CARR_V = "Carrier Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String HDR_NM_LB_NM_FOR_OTBD_CARR_V = "Carrier Name";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_CD_LB_NM_FOR_OTBD_CARR_V = "Carrier Code";

    /**
     * Param Value for NMAL6050
     */
    public static final String DTL_NM_LB_NM_FOR_OTBD_CARR_V = "Carrier Name";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_WAREHOUSE = "0";

    /**
     * Param Value for NPAL1010
     */
    public static final String SOURCE_SUB_WAREHOUSE = "1";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_WAREHOUSE = "2";

    /**
     * Param Value for NPAL1010
     */
    public static final String DESTINATION_SUB_WAREHOUSE = "3";

    /**
     * Param Value for NWAL1130
     */
    public static final String SCRN_NM_SUPPLIER = "Ship To Supplier Search";

    /**
     * Param Value for NWAL1130
     */
    public static final String TABLE_NM_PO_VND_V = "PO_VND_V";

    /**
     * Param Value for NWAL1130
     */
    public static final String SUP_CD = "Supplier Code";

    /**
     * Param Value for NWAL1130
     */
    public static final String SUP_CD_COL = "PRNT_VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final String SUP_SITE_CD = "Supplier Site Code";

    /**
     * Param Value for NWAL1130
     */
    public static final String SUP_SITE_CD_COL = "VND_CD";

    /**
     * Param Value for NWAL1130
     */
    public static final String SUP_SITE_NM = "Supplier Site Namee";

    /**
     * Param Value for NWAL1130
     */
    public static final String SUP_SITE_NM_COL = "DPLY_VND_NM";

    /**
     * Param Value for NWAL1130
     */
    public static final BigDecimal WIDTH_20 = new BigDecimal(20);

    /**
     * Param Value for NWAL1130
     */
    public static final BigDecimal WIDTH_30 = new BigDecimal(30);

    /**
     * Param Value for NWAL1130
     */
    public static final String ASC = "ASC";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM0288E:Please set at least one search criteria.
     */
    public static final String NMAM0288E = "NMAM0288E";

    /**
     * NPAM0225E chronological sequence on the dates for [@] is wrong.
     */
    public static final String NPAM0225E = "NPAM0225E";

    // =================================================
    // HTML TAG ID
    // =================================================
    /**
     * HTML TAG ID Request # Link
     */
    public static final String HTML_ID_REQUEST_NUMBER_LINK = "prchReqNum_Ancher";

    /**
     * HTML TAG ID Account Button
     */
    public static final String HTML_ID_ACCOUNT_BUTTON = "account_Button";

    // =================================================
    // INDEX
    // =================================================

    /**
     * index 0
     */
    public static final int IDX_0 = 0;

    /**
     * index 1
     */
    public static final int IDX_1 = 1;

    /**
     * index 2
     */
    public static final int IDX_2 = 2;

    /**
     * index 3
     */
    public static final int IDX_3 = 3;

    /**
     * index 4
     */
    public static final int IDX_4 = 4;

    /**
     * index 5
     */
    public static final int IDX_5 = 5;

    /**
     * index 6
     */
    public static final int IDX_6 = 6;

    /**
     * index 7
     */
    public static final int IDX_7 = 7;

    /**
     * index 17
     */
    public static final int IDX_17 = 17;

    /**
     * index 20
     */
    public static final int IDX_20 = 20;

    /**
     * index 26
     */
    public static final int IDX_26 = 26;

    /**
     * index 62
     */
    public static final int IDX_62 = 62;

    /**
     * Ship To's Only.
     */
    public static final String SHIP_TO_ONLY_CD = "03";

}
