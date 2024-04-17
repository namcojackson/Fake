/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6840.constant;

/**
 * <p>
 * NMAL6840 Sub Warehouse Setup.
 * </p>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/27/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * </pre>
 */
public class NMAL6840Constant {

    // ------------------- SQL Parameter -------------------

    /**
     * The SQL parameter of "Global Company Code".
     */
    public static final String SQL_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * The SQL parameter of "Retail WH Category Code".
     */
    public static final String SQL_PARAM_RTL_WH_CATG_CD = "rtlWhCatgCd";

    /**
     * The SQL parameter of "Retail Sub WH Code".
     */
    public static final String SQL_PARAM_RTL_SWH_CD = "rtlSwhCd";

    // ------------------- Event Name -------------------

    /**
     * The event name of "INIT".
     */
    public static final String EVENT_NM_INIT = "NMAL6840_INIT";

    /**
     * The event name of "OnClick_Search".
     */
    public static final String EVENT_NM_ONCLICK_SEARCH = "NMAL6840Scrn00_OnClick_Search";

    /**
     * The event name of "OnClick_InsertRow".
     */
    public static final String EVENT_NM_ONCLICK_INSERT_ROW = "NMAL6840Scrn00_OnClick_InsertRow";

    /**
     * The event name of "OnClick_DeleteRow".
     */
    public static final String EVENT_NM_ONCLICK_DELETE_ROW = "NMAL6840Scrn00_OnClick_DeleteRow";

    /**
     * The event name of "CMN_Submit".
     */
    public static final String EVENT_NM_CMN_SUBMIT = "NMAL6840Scrn00_CMN_Submit";

    /**
     * The event name of "CMN_Clear".
     */
    public static final String EVENT_NM_CMN_CLEAR = "NMAL6840Scrn00_CMN_Clear";

    /**
     * The event name of "CMN_Reset".
     */
    public static final String EVENT_NM_CMN_RESET = "NMAL6840Scrn00_CMN_Reset";

    // ------------------- Message Code -------------------

    /**
     * No search result is found.
     */
    public static final String MESSAGE_CD_NMAM0038I = "NMAM0038I";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and cannot proceed.
     */
    public static final String MESSAGE_CD_NMAM8114E = "NMAM8114E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String MESSAGE_CD_NMAM0835E = "NMAM0835E";

    /**
     * [Retail Sub Warehouse Code] has to be [3/1]-digit long.
     */
    public static final String MESSAGE_CD_NMAM0216E = "NMAM0216E";

    /**
     * [Retail Sub Warehouse] cannot be deleted because of
     * [configured].
     */
    public static final String MESSAGE_CD_NMAM0074E = "NMAM0074E";

    /**
     * [Retail Sub Warehouse Code] is already registered.
     */
    public static final String MESSAGE_CD_NMAM0010E = "NMAM0010E";

    /**
     * The process has been successfully completed.
     */
    public static final String MESSAGE_CD_NZZM0002I = "NZZM0002I";

    /**
     * Search results exceeded [50]. Only showing first 50 records.
     */
    public static final String MESSAGE_CD_NMAM8181W = "NMAM8181W";

    /**
     * [Retail Sub Warehouse Digit Number] is not registered.
     */
    public static final String MESSAGE_CD_NMAM0011E = "NMAM0011E";

    /**
     * This data has changed. Please perform the search again.
     */
    public static final String MESSAGE_CD_NMAM0186E = "NMAM0186E";

    // ------------------- Message Parameter -------------------

    /**
     * The message parameter : Retail Sub WH Digit Number.
     */
    public static final String MESSAGE_PARAM_RTL_SWH_DIGIT_NUM = "Retail Sub Warehouse Digit Number";

    /**
     * The message parameter : Retail Sub WH Code.
     */
    public static final String MESSAGE_PARAM_RTL_SWH_CD = "Retail Sub Warehouse Code";

    /**
     * The message parameter : Retail Sub WH.
     */
    public static final String MESSAGE_PARAM_RTL_SWH = "Retail Sub Warehouse";

    /**
     * The message parameter : configured.
     */
    public static final String MESSAGE_PARAM_CONFIGURED = "configured";

    // -------- Define Table Column to create Pulldown ---------

    /** RTL_WH_CD */
    public static final String RTL_WH_CD_DBCOLUMN = "RTL_WH_CD";

    /** RTL_WH_NM */
    public static final String RTL_WH_NM_DBCOLUMN = "RTL_WH_NM";

    /** RTL_SWH_CD */
    public static final String RTL_SWH_CD_DBCOLUMN = "RTL_SWH_CD";

    /** RTL_SWH_NM */
    public static final String RTL_SWH_NM_DBCOLUMN = "RTL_SWH_NM";

    /** RTL_SWH_DESC_TXT */
    public static final String RTL_SWH_DESC_TXT_DBCOLUMN = "RTL_SWH_DESC_TXT";

    /** MDSE_COST_TP_NM */
    public static final String MDSE_COST_TP_NM_DBCOLUMN = "MDSE_COST_TP_NM";

    /** MDSE_INVTY_COST_PCT */
    public static final String MDSE_INVTY_COST_PCT_DBCOLUMN = "MDSE_INVTY_COST_PCT";

}
