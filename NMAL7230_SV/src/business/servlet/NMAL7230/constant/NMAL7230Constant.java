/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230.constant;

/**
 *<pre>
 * NMAL7230Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#8303
 *</pre>
 */
public class NMAL7230Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7230";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7230Scrn00";

    /** Freight Zone */
    public static final String FREIGHT_ZONE = "Freight Zone";

    /** Calendar */
    public static final String CALENDAR = "Calendar";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // button
    // --------------------------------
    /** Button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Button Insert Row */
    public static final String[] BTN_INSERT_ROW = {"InsertRow", "InsertRow", "Add" };

    /** Button Delete Row */
    public static final String[] BTN_DELETE_ROW = {"DeleteRow", "DeleteRow", "Delete" };

    /** Button Upload */
    public static final String[] BTN_UPLOAD = {"Upload", "Upload", "Upload" };

    /** Button PRC */
    public static final String[] BTN_LOB = {"OpenWin_LOB", "OpenWin_LOB", "LOB" };

    /** Button PRC */
    public static final String[] BTN_PRC = {"OpenWin_PrcGrp", "OpenWin_PrcGrp", "PRC" };

    /** Button ST */
    public static final String[] BTN_ST = {"OpenWin_St", "OpenWin_St", "ST" };

    /** Button CTRY */
    public static final String[] BTN_CTRY = {"OpenWin_Ctry", "OpenWin_Ctry", "CTRY" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Only the files with the following extensions can be attached. [@] */
    public static final String ZYPM0188E = "ZYPM0188E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /**  The value for [@] must be over [@].  */
    public static final String NMAM0042E = "NMAM0042E";

    /**  The value for [@] must be smaller than [@].  */
    public static final String NMAM0043E = "NMAM0043E";

    /** @  is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /**  Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /**  The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /**  [@] field has too many digits entered.  */
    public static final String NMAM8090W = "NMAM8090W";

    /**  When @ is entered, @ must be entered. */
    public static final String NMAM8178E = "NMAM8178E";

    /**  @ cannot be added because it is exceeding the maximum number of row [@]  */
    public static final String NMAM8187E = "NMAM8187E";

    /**  When @ is entered, @ cannot entered. */
    public static final String NMAM8409E = "NMAM8409E";

    /**  Please enter at least one of '@'. */
    public static final String NMAM8410E = "NMAM8410E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Sales Date  */
    public static final String SALES_DATE = "Sales Date";

    /** PostalCode Pattern  */
    public static final String POSTALCODE_PATTERN = "99999 or 99999-9999";

    /** Five PostalCode Pattern  */
    public static final String FIVE_POSTALCODE_PATTERN = "^[0-9]{5}$";

    /** Ten PostalCode Pattern  */
    public static final String TEN_POSTALCODE_PATTERN = "^[0-9]{5}[-][0-9]{4}$";

    /** PostalCode Zero Pack  */
    public static final String POSTALCODE_ZERO_PACK = "0000";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Event Name: OpenWin_MultiCtry */
    public static final String EVENT_NM_OPENWIN_MULTICTRY = "OpenWin_MultiCtry";

    /** Event Name: OpenWin_Ctry */
    public static final String EVENT_NM_OPENWIN_CTRY = "OpenWin_Ctry";

    /** Popup Name: Country Search Popup */
    public static final String POPUP_NM_COUNTRY_SEARCH_POPUP = "Country Search Popup";

    /** Popup Name: Country Search Popup */
    public static final String POPUP_CTRY_TABLE_NM = "CTRY";

    /** Popup Display Column Name: Country Name */
    public static final String POPUP_NM_COUNTRY_CODE_DISPLAY = "Country Code";

    /** Popup Column Name: Country Name */
    public static final String POPUP_NM_COUNTRY_CODE = "CTRY_CD";

    /** Popup Display Column Name: Country Name */
    public static final String POPUP_NM_COUNTRY_NAME_DISPLAY = "Country Name";

    /** Popup Column Name: Country Name */
    public static final String POPUP_NM_COUNTRY_NAME = "CTRY_DESC_TXT";

    /** Popup Column Name: Country Sort Num */
    public static final String POPUP_NM_COUNTRY_SORT = "CTRY_SORT_NUM";

    /** Event Name: OpenWin_MultiSt */
    public static final String EVENT_NM_OPENWIN_MULTIST = "OpenWin_MultiSt";

    /** Event Name: OpenWin_St */
    public static final String EVENT_NM_OPENWIN_ST = "OpenWin_St";

    /** Popup Name: State Search Popup */
    public static final String POPUP_NM_STATE_SEARCH_POPUP = "State Search Popup";

    /** Popup Name: State Search Popup */
    public static final String POPUP_ST_TABLE_NM = "ST";

    /** Popup Display Column Name: State Code */
    public static final String POPUP_NM_STATE_CODE_DISPLAY = "State Code";

    /** Popup Column Name: State Code */
    public static final String POPUP_NM_STATE_CODE = "ST_CD";

    /** Popup Display Column Name: State Name */
    public static final String POPUP_NM_STATE_NAME_DISPLAY = "State Name";

    /** Popup Column Name: State Name */
    public static final String POPUP_NM_STATE_NAME = "ST_DESC_TXT";

    /** Popup Column Name: State Sort Num */
    public static final String POPUP_NM_STATE_SORT = "ST_SORT_NUM";

    /** Event Name: OpenWin_MultiCust */
    public static final String EVENT_NM_OPENWIN_MULTICUST = "OpenWin_MultiCust";

    /** Event Name: OpenWin_Cust */
    public static final String EVENT_NM_OPENWIN_CUST = "OpenWin_Cust";

    /** Popup Name: Customer Search Popup */
    public static final String POPUP_NM_CUSTOMER_SEARCH_POPUP = "Customer Search Popup";

    /** Popup Name: Customer Search Popup */
    public static final String POPUP_MULTICUST_TABLE_NM = "SELL_TO_CUST";

    /** Popup Display Column Name: Customer Code */
    public static final String POPUP_NM_ACCOUNT_NUMBER_DISPLAY = "DS Account Number";

    /** Popup Column Name: Customer Code */
    public static final String POPUP_NM_ACCOUNT_NUMBER = "SELL_TO_CUST_CD";

    /** Popup Display Column Name: Customer Name */
    public static final String POPUP_NM_ACCOUNT_NAME_DISPLAY = "DS Account Name";

    /** Popup Column Name: Customer Name */
    public static final String POPUP_NM_ACCOUNT_NAME = "DS_ACCT_NM";

    /** Event Name: OpenWin_MultiCustGrp */
    public static final String EVENT_NM_OPENWIN_MULTICUSTGRP = "OpenWin_MultiCustGrp";

    /** Event Name: OpenWin_CustGrp */
    public static final String EVENT_NM_OPENWIN_CUSTGRP = "OpenWin_CustGrp";

    /** Popup Name: Customer Group Search Popup */
    public static final String POPUP_NM_CUSTOMER_GROUP_SEARCH_POPUP = "Prc Grp Search Popup";

    /** Popup Name: Customer Group Search Popup */
    public static final String POPUP_CUSTGRP_TABLE_NM = "PRC_GRP";

    /** Popup Display Column Name: Customer Group Code */
    public static final String POPUP_NM_CUSTGRP_NAME_DISPLAY = "Prc Grp Name";

    /** Popup Column Name: State Code */
    public static final String POPUP_NM_CUSTGRP_NAME = "PRC_GRP_NM";

    /** Popup Display Column Name: State Name */
    public static final String POPUP_NM_CUSTGRP_TP_DISPLAY = "Prc Grp Type Code";

    /** Popup Column Name: State Name */
    public static final String POPUP_NM_CUSTGRP_TP = "PRC_GRP_TP_CD";

    /** Event Name: OpenWin_LOB */
    public static final String EVENT_NM_OPENWIN_LOB = "OpenWin_LOB";

    /** Popup Name: Customer Group Search Popup */
    public static final String POPUP_NM_LOB_SEARCH_POPUP = "LOB Search Popup";

    /** Popup Name: Customer Group Search Popup */
    public static final String POPUP_LOB_TABLE_NM = "LINE_BIZ_TP";

    /** Popup Display Column Name: State Name */
    public static final String POPUP_NM_LOB_DISPLAY = "Line Of Business Code";

    /** Popup Column Name: State Name */
    public static final String POPUP_NM_LOB_CODE = "LINE_BIZ_TP_CD";

    /** Popup Display Column Name: Customer Group Code */
    public static final String POPUP_LOB_NAME_DISPLAY = "Line Of Business Name";

    /** Popup Column Name: State Code */
    public static final String POPUP_NM_LOB_NAME = "LINE_BIZ_TP_DESC_TXT";

    /** Popup Column Name: LOB Sort Num */
    public static final String POPUP_NM_LOB_SORT = "LINE_BIZ_TP_SORT_NUM";

    /** NAML6050 Parameter */
    public static final int NAML6050_PRM_NUM = 11;

    // QC#8302 2016/05/13 Mod start
    /** NAML6760 Parameter */
    public static final int NAML6760_PRM_NUM = 15;

    /** Account Name index in NAML6760 Parameter */
    public static final int ACCT_NUM_PRM_NM_NMAL6760 = 1;
    // QC#8302 2016/05/13 Mod end

    /** NAML6050 Parameter : Code */
    public static final int NAML6050_PRM_CODE_NUM = 9;

    /** NAML6050 Parameter : Name */
    public static final int NAML6050_PRM_NAME_NUM = 10;

    /** Popup Sort Name: ASC */
    public static final String POPUP_SORT_KEY_ASC = "ASC";
}
