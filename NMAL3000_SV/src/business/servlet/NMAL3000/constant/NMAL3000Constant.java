/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000.constant;

/**
 *<pre>
 * NMAL3000Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL3000Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL3000";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL3000Scrn00";

    // --------------------------------
    // button
    // --------------------------------
    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

    /** Button Select All */
    public static final String BTN_SELECT_ALL = "Select_All";

    /** Button Un-select All */
    public static final String BTN_UNSELECT_ALL = "UnSelect_All";

    /** Button Insert_Row */
    public static final String BTN_INSERT_ROW = "InsertRow_DelrAM";

    /** Button Delete_Row */
    public static final String BTN_DELETE_ROW = "DeleteRow_DelrAM";

    /** Button Copy */
    public static final String BTN_COPY = "Copy_DelrAM";

    /** Button Search Account */
    public static final String BTN_SEARCH_ACCOUNT = "Search_Account";

    /** Button OpenWin Search Account */
    public static final String BTN_OPENWIN_SEARCH_ACCOUNT = "OpenWin_SearchAccount";

    /** OpenWin SearchModel */
    public static final String BTN_OPENWIN_SEARCH_MODEL = "OpenWin_SearchModel";

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
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /**  past date cannot be entered into the "Date Effective From */
    public static final String NMAM0185E = "NMAM0185E";

    /** Error Message */
    public static final String NMAM8234I = "NMAM8234I";

    /**Max Row */
    public static final int  MAX_ROW = 5000;

    /** Check Box */
    public static final String CHK_A = "xxChkBox_A";

    /** Header value */
    public static final int HEADER_VALUE = -1;

    /** PoPUp Param Pop Win Title*/
    public static final String POP_WIN_TITLE = "Marketing Model Name";

    /** PoPUp Param Pop Table Nm*/
    public static final String POP_TABLE_NM = "MKT_MDL";

    /** PoPUp Param Disp Nm */
    public static final String POP_DISP_NM_MDL_CD = "Markting MDL Code";

    /** PoPUp Param Sql Col*/
    public static final String POP_SQL_COL_MDL_CD = "MKT_MDL_CD";

    /** PoPUp Param Disp Nm*/
    public static final String POP_DISP_NM_MDL_NM = "Markting MDL Name";

    /** PoPUp Param Sql col */
    public static final String POP_SQL_COL_MDL_NM = "MKT_MDL_DESC_TXT";

    /** PoPUp Param Sort Col*/
    public static final String POP_SORT_COL = "MKT_MDL_CD";

    /** PoPUp Param Sort Condition*/
    public static final String POP_SORT_CONDITION = "ASC";

    /** Btn Delete Row */
    public static final String  BTN_DELLETE_ROW = "DeleteRow_DelrAM";

    /** Record Not Found */
    public static final int RECORD_NOTFOUND = 0;

    /** Insert Flg */
    public static final String INSERT_FLG = "I";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Param Size 15*/
    public static final int PARAM_SIZE_15 = 15;

    /** Param Size 7*/
    public static final int PARAM_SIZE_7 = 7;

    /** Param Size 4*/
    public static final int PARAM_SIZE_4 = 4;

    /** Param Size 2*/
    public static final int PARAM_SIZE_2 = 2;

}
