/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NMAL0170Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima          Create          N/A
 * 2015/12/11   Fujitsu         T.Arima          Update          N/A
 * 2016/02/22   CITS            S.Tanikawa       Update          QC#2669
 * 2017/02/08   Fujitsu         K.Ishizuka       Update          QC#17259
 *</pre>
 */
public class NMAL0170Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL0170";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL0170Scrn00";

    /** Function ID - Read */
    public static final String FUNC_ID_EDIT = "NMAL0170T020";

    // --------------------------------
    // Event Name
    // --------------------------------
    /** INIT EVENT */
    public static final String EVENT_INIT = "EVENT_INIT";

    // DELETE START 2016/03/01 QC#2669
    /** NEW EVENT */
    // public static final String EVENT_NEW = "EVENT_NEW";
    // DELETE END 2016/03/01 QC#2669

    /** SEARCH EVENT */
    public static final String EVENT_SEARCH = "EVENT_SEARCH";

    /** InsertRow EVENT */
    public static final String EVENT_INSERT_ROW = "EVENT_INSERT_ROW";

    /** DeleteRow EVENT */
    public static final String EVENT_DELETE_ROW = "EVENT_DELETE_ROW";

    /** CMN_CLOSE EVENT */
    public static final String EVENT_CMN_CLOSE = "EVENT_CMN_CLOSE";

    /** CMN_SUBMIT EVENT */
    public static final String EVENT_CMN_SUBMIT = "EVENT_CMN_SUBMIT";

    /** ItemDetailSupdTo EVENT */
    public static final String EVENT_ITEM_DETAIL_SUPD_TO = "EVENT_ITEM_DETAIL_SUPD_TO";

    /** ItemDetailSupdFrom EVENT */
    public static final String EVENT_ITEM_DETAIL_SUPD_FM = "EVENT_ITEM_DETAIL_SUPD_FM";

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

    /** BTN : InsertRow */
    public static final String BTN_INSERT_ROW = "InsertRow";

    /** BTN : DeleteRow */
    public static final String BTN_DELETE_ROW = "DeleteRow";

    /** BTN : View */
    public static final String BTN_VIEW = "View";
    
    // ADD start S21_NA #17259
    /** Function Code (Search) */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code (Submit) */
    public static final String FUNC_CD_UPDT = "40";
    // ADD end S21 S21_NA #17259

    // --------------------------------
    // Message ID
    // --------------------------------
    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Please set at least one search criteria. */
    public static final String NMAM0288E = "NMAM0288E";

    /** Please specify [@]. */
    public static final String NMAM0041E = "NMAM0041E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Cannot select multiple. */
    public static final String NMAM8098E = "NMAM8098E";

    // --------------------------------
    // Header Label Item
    // --------------------------------
    /** Display Label : Item Number */
    public static final String HDR_LBL_ITEM_NUMBER = "Item Number";

    /** Display Label : Item Type */
    public static final String HDR_LBL_ITEM_TYPE = "Item Type";

    /** Display Label : Item Classification */
    public static final String HDR_LBL_ITEM_CLASSIFICATION = "Item Classification";

    /** Display Label : Date Staged From */
    public static final String HDR_LBL_DATE_STAGED_FROM = "Date Staged From";

    /** Display Label : Date Staged To */
    public static final String HDR_LBL_DATE_STAGED_TO = "Date Staged To";//MOD S21_NA #17259

    // --------------------------------
    // TABLE Label Item
    // --------------------------------
    /** Display Label : Item Number */
    public static final String TBL_LBL_ITEM_NUMBER = "Item Number";

    /** Display Label : Item Description */
    public static final String TBL_LBL_ITEM_DESCRIPTION = "Item Description";

    /** Display Label : Supersedes */
    public static final String TBL_LBL_SUPERSEDES = "Supersedes";

    /** Display Label : Supersedes Description */
    public static final String TBL_LBL_SUPERSEDES_DESCRIPTION = "Supersedes Description";

    /** Display Label : Date Staged */
    public static final String TBL_LBL_DATE_STAGED = "Date Staged";

    /** Display Label : Item Type */
    public static final String TBL_LBL_ITEM_TYPE = "Item Type";

    /** Display Label : Item Classification */
    public static final String TBL_LBL_ITEM_CLASSIFICATION = "Item Classification";

    // --------------------------------
    // Popup Paramater
    // --------------------------------
    /** Popup WINDOW TITLE: Supersession Item Number Search */
    public static final String POPUP_WINDOW_TITLE = "Supersession Item Number Search";

    // UPDATE START 12/11/2015
    /** Popup Display Code : Item Number */
    public static final String POPUP_DISP_ITEM_NUMBER = "Item Number";

    /** Popup SQL Code : SUPD_TO_MDSE_CD */
    public static final String POPUP_SQL_ITEM_NUMBER = "SUPD_TO_MDSE_CD";

    /** Popup Display Code : Supersedes */
    public static final String POPUP_DISP_SUPD = "Supersedes";

    /** Popup SQL Code : SUPD_FROM_MDSE_CD */
    public static final String POPUP_SQL_SUPD = "SUPD_FROM_MDSE_CD";

    /** Popup Display Name: Model Name */
    public static final String POPUP_DISPLAY_NAME = "Merchandise Name";

    /** Popup SQL NAME : Merchandise Name */
    public static final String POPUP_SQL_ITEM_NM = "DT_MDSE_TXT";

    /** Popup SQL NAME : Merchandise Name */
    public static final String POPUP_SQL_SUPD_NM = "DF_MDSE_TXT";

    /** Popup Code Width : 16 */
    public static final BigDecimal POPUP_CODE_WIDTH = new BigDecimal("16");

    /** Popup Name Width : 50 */
    public static final BigDecimal POPUP_NAME_WIDTH = new BigDecimal("30");

    // UPDATE END 12/11/2015

    /** Popup Sort Name: ASC */
    public static final String POPUP_SORT_KEY_ASC = "ASC";

    /** Popup Patameter Num : 7 */
    public static final int POPUP_PRM_NUM = 7;

    /** Popup Parameter Table Suffix : T */
    public static final String POPUP_TBL_SUFFIX = "T";
}
