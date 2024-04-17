/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1210.constant;

/**
 * <pre>
 * Business ID : NPAL1210 PO/Inventory Approval History
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2016   CITS            R Shimamoto     Create          N/A
 * 03/08/2016   CITS            K.Ogino         Update          QC#5148
 * 03/24/2016   CITS            T.Tokutomi      Update          QC#5763
 *</pre>
 */
public class NPAL1210Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1210";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1210Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Init
     */
    public static final String EVENT_NM_NPAL1210_INIT = "NPAL1210_INIT";

    /**
     * Event Name : CMN_Close
     */
    public static final String EVENT_NM_NPAL1210_CMN_CLOSE = "NPAL1210Scrn00_CMN_Close";

    /**
     * Event Name : CMN_ColClear
     */
    public static final String EVENT_NM_NPAL1210_CMN_COL_CLEAR = "NPAL1210Scrn00_CMN_ColClear";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: PO_REQUISITION
     */
    public static final String DB_PARAM_PO_REQUISITION = "poRequisition";

    /**
     * DB Param Name: ROWNUM
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /**
     * DB Param Name: cMsg
     */
    public static final String DB_PARAM_CMSG = "cMsg";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: TRX_REF_NUM
     */
    public static final String DB_COLUMN_TRX_REF_NUM = "TRX_REF_NUM";

    /**
     * DB Column Name: APVL_BY_PSN_CD
     */
    public static final String DB_COLUMN_APVL_BY_PSN_CD = "APVL_BY_PSN_CD";

    /**
     * DB Column Name: APVL_FULL_PSN_NM
     */
    public static final String DB_COLUMN_APVL_FULL_PSN_NM = "APVL_FULL_PSN_NM";

    /**
     * DB Column Name: APVL_HIST_INFO_TS
     */
    public static final String DB_COLUMN_APVL_HIST_INFO_TS = "APVL_HIST_INFO_TS";

    /**
     * DB Column Name: APVL_HIST_TXT
     */
    public static final String DB_COLUMN_APVL_HIST_TXT = "APVL_HIST_TXT";

    /**
     * DB Column Name: APVL_HIST_SRC_TP_DESC_TXT
     */
    public static final String DB_COLUMN_APVL_HIST_SRC_TP_DESC_TXT = "APVL_HIST_SRC_TP_DESC_TXT";

    /**
     * DB Column Name: APVL_HIST_ACT_TP_DESC_TXT
     */
    public static final String DB_COLUMN_APVL_HIST_ACT_TP_DESC_TXT = "APVL_HIST_ACT_TP_DESC_TXT";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NZZM0010E: The data [@] does not exist in the master.
     */
    public static final String NZZM0010E = "NZZM0010E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NZZM0001W: There are too many search results, there is data
     * that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * NMAM8181W:
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * ZZM9000E:
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * NMAM0182E: You can not [@] this record Because of [@] already
     * exists.
     */
    public static final String NMAM0182E = "NMAM0182E";

    /**
     * ZZM9003I: The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NMAM0014E: [@] is not selected.
     */
    public static final String NMAM0014E = "NMAM0014E";

    /**
     * NZZM0007E:
     */
    public static final String NZZM0007E = "NZZM0007E";

    /**
     * NPAM0002E:
     */
    public static final String NPAM0002E = "NPAM0002E";

    // =================================================
    // Display Name
    // =================================================
    /**
     * Display Name : MDSE_CD
     */
    public static final String DISPLAY_NM_MDSE_CD = "Item Number";

}
