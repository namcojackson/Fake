/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080.constant;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL7080";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7080Scrn00";

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

    /** Search Button */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Search Button */
    public static final String[] BTN_ITEM_SEARCH = {"itemSearch", "OpenWin_Mdse", "Search" };

    /** Apply Button */
    public static final String[] BTN_APPLY = {"apply", "OnClick_Apply", "Apply" };

    /** Mass Update Button */
    public static final String[] BTN_MASS_UPDATE = {"massUpdate", "OnClick_MassUpdate", "Mass Update" };

    /** Insert Row Button */
    public static final String[] BTN_INSERT_ROW = {"insertRow", "InsertRow", "Insert Row" };

    /** Delete Row Button */
    public static final String[] BTN_DELETE_ROW = {"deleteRow", "DeleteRow", "Delete Row" };

    // --------------------------------
    // Message
    // --------------------------------

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";
    
    /** "DeleteRow" cannot remove the record that registered . Please "Delete". */
    public static final String NMAM8444E = "NMAM8444E";
    
    /** "Delete" cannot remove the record that inserted. Please "DeleteRow". */
    public static final String NMAM8445E = "NMAM8445E";

    // --------------------------------
    // Common
    // --------------------------------

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Colum */
    public static final int CMN_PRM_COLUMN_NUM = 4;

    /** Common Pop-up Parameter/sort */
    public static final int CMN_PRM_SORT_NUM = 2;

    /** FUNC_ID_UPDATE */
    public static final String FUNC_ID_UPDATE = "NMAL7080T020";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Mode Update */
    public static final String MODE_UPDATE = "02";

    /** Mode Update */
    public static final String MODE_NEW = "01";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
    
    /** Mode 8 digit */
    public static final String MODE_8_DIGIT = "08";
    
}
