/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160.constant;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/03/29   Fujitsu         C.Naito         Update          QC#6178
 *</pre>
 */
public class NFDL0160Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFDL0160";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFDL0160Scrn00";

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
    // Screen button
    // --------------------------------
    /** button DeleteRow */
    public static final String BTN_DEL_ROW = "DeleteRow";

    /** button InsertRow */
    public static final String BTN_INS_ROW = "InsertRow";

    /** button OpenWin_Clt */
    public static final String BTN_OPENWIN_CLT = "OpenWin_Clt";

    /** button OpenWin_AR */
    public static final String BTN_OPENWIN_AR = "OpenWin_AR";

    /** button OpenWin_NextLvl */
    public static final String BTN_OPENWIN_NEXTLVL = "OpenWin_NextLvl";

    // --------------------------------
    // 
    // --------------------------------
    /** Index Number 0 */
    public static final int IDX_0 = 0;

    /** Index Number 1 */
    public static final int IDX_1 = 1;

    /** Index Number 2 */
    public static final int IDX_2 = 2;

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Index Number 4 */
    public static final int IDX_4 = 4;

    /** Index Number 5 */
    public static final int IDX_5 = 5;

    /** Index Number 6 */
    public static final int IDX_6 = 6;

    /** Index Number 7 */
    public static final int IDX_7 = 7;

    /** Index Number 8 */
    public static final int IDX_8 = 8;

    /** Index Number 9 */
    public static final int IDX_9 = 9;

    /** Index Number 10 */
    public static final int IDX_10 = 10;

    /** Index Number 15 */
    public static final int IDX_15 = 15;

    /** Index Number 20 */
    public static final int IDX_20 = 20;

    /** Index Number 28 */
    public static final int IDX_28 = 28;

    /** Index Number 30 */
    public static final int IDX_30 = 30;

    /** Index Number 50 */
    public static final int IDX_50 = 50;

    /** Index Number 61 */
    public static final int IDX_61 = 61;

    // [QC#6178] UPDATE start
//    /** cltPtfoStsFlg select ALL */
//    public static final String ALL = "B";
    /** cltPtfoStsFlg select ACTIVE */
    public static final String ACTIVE = "A";
    // [QC#6178] UPDATE end

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Please select data from PopUp. */
    public static final String NFDM0023E = "NFDM0023E";

    /** Please check at least 1 checkbox. */
    public static final String NFDM0024E = "NFDM0024E";

    /** Cannot add anymore details. */
    public static final String NFDM0025E = "NFDM0025E";
}
