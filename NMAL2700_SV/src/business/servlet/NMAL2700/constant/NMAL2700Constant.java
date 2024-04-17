/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700.constant;

/**
 *<pre>
 * NMAL2700Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2700Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2700";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2700Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit"};

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

    /** Button Delete_Row */
    public static final String BTN_DELETE_ROW = "DeleteRow_RoleMnt";

    // --------------------------------
    // Pop up
    // --------------------------------
    /** Screen Title : SFDC Profile Look up */
    public static final String SCRN_TITLE = "SFDC Profile Look up";

    /** Parameter Display Name : Profile ID(*) */
    public static final String DISPLAY_NM_PROFILE_ID = "Profile ID";

    /** Parameter Display Name : Name(*) */
    public static final String DISPLAY_NM_NAME = "Name";

    /** Parameter Column : PROFILE_ID */
    public static final String CLMN_PROFILE_ID = "PROFILE_ID";

    /** Parameter Column : NAME */
    public static final String CLMN_NAME = "NAME";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** [@] is duplicated */
    public static final String NMAM0072E = "NMAM0072E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /**Max Row */
    public static final int  MAX_ROW = 200;

    /** Record Not Found */
    public static final int RECORD_NOTFOUND = 0;

    /** Button Insert_Row */
    public static final String BTN_INSERT_ROW = "InsertRow_RoleMnt";

}
