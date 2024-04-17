/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240.constant;

/**
 *<pre>
 * NMAL7240Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7240Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7240";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7240Scrn00";

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
    public static final String[] BTN_SVCLVL = {"OpenWin_ShpgSvcLvl", "OpenWin_ShpgSvcLvl", "SVC_LVL" };

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

    /**  Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /**  [@] field has too many digits entered.  */
    public static final String NMAM8090W = "NMAM8090W";

    /**  @ cannot be added because it is exceeding the maximum number of row [@]  */
    public static final String NMAM8187E = "NMAM8187E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Freight Rate */
    public static final String FREIGHT_RATE = "Freight Rate";

    /** Event Name: OpenWin_MultiSt */
    public static final String EVENT_NM_OPENWIN_MULTISHPGSVCLVL = "OpenWin_MultiShpgSvcLvl";

    /** Event Name: OpenWin_St */
    public static final String EVENT_NM_OPENWIN_SHPGSVCLVL = "OpenWin_ShpgSvcLvl";

    /** Popup Name: State Search Popup */
    public static final String POPUP_NM_SHPGSVCLVL_SEARCH_POPUP = "Shipping Service Level Search Popup";

    /** Popup Name: State Search Popup */
    public static final String POPUP_SHPGSVCLVL_TABLE_NM = "SHPG_SVC_LVL";

    /** Popup Display Column Name: State Code */
    public static final String POPUP_NM_SHPGSVCLVL_CODE_DISPLAY = "Service Level Code";

    /** Popup Column Name: State Code */
    public static final String POPUP_NM_SHPGSVCLVL_CODE = "SHPG_SVC_LVL_CD";

    /** Popup Display Column Name: State Name */
    public static final String POPUP_NM_SHPGSVCLVL_NAME_DISPLAY = "Service Level Name";

    /** Popup Column Name: State Name */
    public static final String POPUP_NM_SHPGSVCLVL_NAME = "SHPG_SVC_LVL_DESC_TXT";

    /** Popup Column Name: State Sort Num */
    public static final String POPUP_NM_SHPGSVCLVL_SORT = "SHPG_SVC_LVL_SORT_NUM";

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

    /** NAML6050 Parameter : Code */
    public static final int NAML6050_PRM_CODE_NUM = 9;

    /** NAML6050 Parameter : Name */
    public static final int NAML6050_PRM_NAME_NUM = 10;

    /** Popup Sort Name: ASC */
    public static final String POPUP_SORT_KEY_ASC = "ASC";

}
