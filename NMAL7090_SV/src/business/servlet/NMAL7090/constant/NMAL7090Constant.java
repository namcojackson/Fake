/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090.constant;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/11/22   Fujitsu         R.Nakamura      Update          QC#16082
 *</pre>
 */
public class NMAL7090Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7090";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7090Scrn00";

    /** Function ID - Read */
    public static final String FUNC_INQUIRY = "NMAL7090T010";

    /** Function ID - Edit */
    public static final String FUNC_ID_EDIT = "NMAL7090T020";

    /** Function Code : Search */
    public static final String FUNCTION_CD_SEARCH = "20";

    /** Function Code : Update */
    public static final String FUNCTION_CD_UPDATE = "40";

    /** TABLE_A **/
    public static final String TABLE_A = "A";

    /** TABLE_A **/
    public static final String TABLE_B = "B";

    /** TABLE_C **/
    public static final String TABLE_C = "C";

    /** Param Value for Upload */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_CSV = ".csv";

    // Add Start 2016/11/22QC#16802
    /** Param Value for Upload */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_TXT = ".txt";

    /** Param Value for Upload */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_XLSX = ".xlsx";

    /** Param Value for Upload */
    public static final String PRAM_UPLOAD_FILE_EXTENSION_XLS = ".xls";
    // Add End 2016/11/22QC#16802

    // --------------------------------
    // Common button
    // --------------------------------
    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    /** Business button :Search */
    public static final String BTN_SEARCH = "Search";

    /** Business button :UpdatePriceList */
    public static final String BTN_UPDATE_PRC_LIST = "UpdatePriceList";

    /** Business button LABEL: UpdatePriceList */
    public static final String BTN_LBL_UPDATE_PRC_LIST = "Update Price List";

    /** Business button LABEL: UpdatePriceList */
    public static final String BTN_LBL_PROCESSED = "Processed";

    /** Business button :Apply */
    public static final String BTN_APPLY_A = "Apply_A";

    /** Business button :Apply */
    public static final String BTN_APPLY_B = "Apply_B";

    /** Business button :Apply */
    public static final String BTN_APPLY_C = "Apply_C";

    /** Business button :Refresh */
    public static final String BTN_REFRESH = "Refresh";

    /** Business button: Insert Row */
    public static final String BTN_INSERT = "InsertRow";

    /** Business button: Upload */
    public static final String BTN_UPLOAD = "Upload";

    /** Business button :DownloadHistoricalRequestResult */
    public static final String BTN_DOWNLOAD_HIST_RQST_RSLT = "DownloadHistoricalRequestResult";

    // =================================================
    // Display Name
    // =================================================
    /** Display Label : SUPD_CRAT_DT_FR */
    public static final String LBL_SUPD_CRAT_DT_FR = "Add Date From";

    /** Display Label : SUPD_CRAT_DT_TO */
    public static final String LBL_SUPD_CRAT_DT_TO = "Add Date To";

    // =================================================
    // Message ID
    // =================================================
    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Please check Submit or Discard. */
    public static final String NMAM8429E = "NMAM8429E";

    /** Please check Discard. */
    public static final String NMAM8431E = "NMAM8431E";

    /** ZZM9000E: */
    public static final String ZZM9000E = "ZZM9000E";

    // =================================================
    // Popup Param
    // =================================================
    /** Item Master Search Popup : Mode Code = 8 */
    public static final String MODE_CODE_8 = "08";

    // =================================================
    // Event Name
    // =================================================
    /** INIT EVENT */
    public static final String EVENT_INIT = "EVENT_INIT";

    /** EVENT CLEAR */
    public static final String EVENT_CLEAR = "EVENT_CLEAR";

    /** EVENT: SEARCH */
    public static final String EVENT_SEARCH = "EVENT_SEARCH";

    /** PAGE NEXT EVENT */
    public static final String EVENT_PAGE_NEXT = "EVENT_PAGE_NEXT";

    /** PAGE PREVIEW EVENT */
    public static final String EVENT_PAGE_PREV = "EVENT_PAGE_PREV";

    /** REFRESH EVENT */
    public static final String EVENT_REFRESH = "EVENT_REFRESH";

    /** EVENT :UPDATE PRICE LIT */
    public static final String EVENT_UPDATE_PRICE_LIST = "EVENT_UPDATE_PRICE_LIST";

    /** EVENT :INSERT ROW */
    public static final String EVENT_INSERT_ROW = "EVENT_INSERT_ROW";

    /** EVENT :COPY */
    public static final String EVENT_COPY = "EVENT_COPY";

    /** EVENT :UPLOAD */
    public static final String EVENT_UPLOAD = "EVENT_UPLOAD";

    /** EVENT :OPEN_WIN_ITEM_OLD */
    public static final String EVENT_OPEN_WIN_ITEM_OLD = "EVENT_OPEN_WIN_ITEM_OLD";

    /** EVENT :OPEN_WIN_ITEM_NEW */
    public static final String EVENT_OPEN_WIN_ITEM_NEW = "EVENT_OPEN_WIN_ITEM_NEW";

    /** EVENT :EVENT_CMN_CLOSE */
    public static final String EVENT_CMN_CLOSE = "EVENT_CMN_CLOSE";

    /** EVENT: DELETE */
    public static final String EVENT_DELETE = "EVENT_DELETE";

    /** EVENT: Apply A */
    public static final String EVENT_APPLY_A = "EVENT_APPLY_A";

    /** EVENT: Apply B */
    public static final String EVENT_APPLY_B = "EVENT_APPLY_B";

    /** EVENT: Apply C */
    public static final String EVENT_APPLY_C = "EVENT_APPLY_C";

    /** EVENT: Submit */
    public static final String EVENT_SUBMIT = "EVENT_SUBMIT";

}
