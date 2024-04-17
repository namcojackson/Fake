/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3180.constant;

/**
 * <pre>
 * Ship Detail Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/18   CITS            J.Evangelista   Create          QC#58876
 * 08/18/2021   CITS            K.Ogino         Update          QC#58876-1
 * </pre>
 */
public class NLBL3180Constant {

    /**
     * Postal Code Length
     */
    public static final int POSTAL_CODE_LENGTH = 5;

    /**
     * Empty Text
     */
    public static final String EMPTY_TXT = "";

    /**
     * Date Time Format: yyyyMMddHHmm
     */
    public static final String TIME_FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

    /**
     * Date Time Format: MM/dd/yyyy HH:mm
     */
    public static final String TIME_FORMAT_MMDDYYYYHHMM = "MM/dd/yyyy HH:mm";

    // =================================================
    // Message Codes
    // =================================================
    /**
     * NLBM1232I: No search results found.
     */
    public static final String NLBM1232I = "NLBM1232I";

    /**
     * NLBM1109E: SO_SLP_NUM does not exist.
     */
    public static final String NLBM1109E = "NLBM1109E";

    // =================================================
    // DB Params
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Shipping Order Number
     */
    public static final String DB_PARAM_SO_NUM = "soNum";

    /**
     * DB Param Name: Purchase Request Number
     */
    public static final String DB_PARAM_PRCH_REQ_NUM = "prchReqNum";

    /**
     * DB Param Name: Purchase Request Line Number
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_NUM = "prchReqLineNum";

    /**
     * DB Param Name: Purchase Request Line Sub Number
     */
    public static final String DB_PARAM_PRCH_REQ_LINE_SUB_NUM = "prchReqLineSubNum";

    /**
     * DB Param Name: Transaction ID
     */
    public static final String DB_PARAM_TRX_ID = "trxId";

    /**
     * DB Param Name: OTBD_ORD_NUM
     */
    public static final String DB_PARAM_OTBD_ORD_NUM_LIST = "otbdOrdNumList";

    /**
     * DB Param Name: OTBD_ORD_LINE_NUM
     */
    public static final String DB_PARAM_OTBD_ORD_LINE_NUM = "otbdOrdLineNum";

    // QC#58876-1 Add Start
    /**
     * DB Param Name: POD
     */
    public static final String DB_PARAM_POD = "pod";

    /**
     * DB Param Name: WRK_TRX_ID
     */
    public static final String DB_PARAM_WRK_TRX_ID = "wrkTrxId";

    /**
     * DB Param Name: INTFC_TRX_ID
     */
    public static final String DB_PARAM_INTFC_TRX_ID = "intfcTrxId";
    // QC#58876-1 Add End

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: Voucher Number
     */
    public static final String DB_COLUMN_BOL_VCH_NUM = "BOL_VCH_NUM";

    /**
     * DB Column Name: Original ETD
     */
    public static final String DB_COLUMN_RQST_QUOTE_DELY_TXT = "RQST_QUOTE_DELY_TXT";

    /**
     * DB Column Name: QTD Change
     */
    public static final String DB_COLUMN_QUOTE_DELY_TXT = "QUOTE_DELY_TXT";

    /**
     * DB Column Name: Transaction ID
     */
    public static final String DB_COLUMN_INTFC_TRX_ID = "INTFC_TRX_ID";

    /**
     * DB Column Name: Unit ID
     */
    public static final String DB_COLUMN_INTFC_TRX_SQ_NUM = "INTFC_TRX_SQ_NUM";

    /**
     * DB Column Name: Milestone
     */
    public static final String DB_COLUMN_DESC_HIST_TXT = "DESC_HIST_TXT";

    /**
     * DB Column Name: Local Date/Time
     */
    public static final String DB_COLUMN_CPLT_LOCAL_TXT = "CPLT_LOCAL_TXT";
    // QC#58876-1 Add Start
    /**
     * DB Column Name: WRK_TRX_ID
     */
    public static final String DB_COLUMN_WRK_TRX_ID = "WRK_TRX_ID";
    
    /**
     * DB Column Name: WMS_TASK_CD
     */
    public static final String DB_COLUMN_WMS_TASK_CD = "WMS_TASK_CD";

    /**
     * DB Column Name: Plan Local Date/Time
     */
    public static final String DB_COLUMN_PLN_LOCAL_TXT = "PLN_LOCAL_TXT";
    // QC#58876-1 Add End
    
}
