/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1710.constant;

/**
 *<pre>
 * NWAL1710Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 * 2016/05/11   Fujitsu         M.Suzuki        Update          S21_NA#7565
 *</pre>
 */
public class NWAL1710Constant {

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Global Compy Code */
    public static final String GLOBAL_CMPY_CODE = "glblCmpyCd";

    /** OrdProcTpLvlCd */
    public static final String ORD_PROC_TP_LVL_CD = "ordProcTpLvlCd";

    /** Level H */
    public static final String LEVEL_H = "H";

    /** DBcolumn Ord Proc Tp Cd */
    public static final String DBCOLUMN_ORD_PROC_TP_CD = "ORD_PROC_TP_CD";

    /** DBcolumn Ord Proc Tp Desc Txt */
    public static final String DBCOLUMN_ORD_PROC_TP_DESC_TXT = "ORD_PROC_TP_DESC_TXT";

    /** OrderCategory */
    public static final String ORDER_CATEGORY = "orderCategory";

    /** ReasonCode */
    public static final String REASON_CODE = "reasonCode";

    /** WorkFlow */
    public static final String WORK_FLOW = "workFlow";

    /** LineOfBusiness */
    public static final String LINE_OF_BUSINESS = "lineOfBusiness";

    /** EffectiveFrom */
    public static final String EFFECTIVE_FROM = "effectiveFrom";

    /** EffectiveThru */
    public static final String EFFECTIVE_THRU = "effectiveThru";

    /** ActiveOnly */
    public static final String ACTIVE_ONLY = "activeOnly";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** Csv Name Cont Info */
    public static final String CSV_NAME_CONT_INFO = "Order Category Search Screen";

    /** yyyymmdd Length */
    public static final int YYYYMMDD_LENGTH = 8;

    /** Row Num */
    public static final String ROW_NUM = "rowNum";
    //2016/05/11 S21_NA#7565 MOD Start -------------
    /** CompDtMax */
    public static final String COMPDTMAX = "compDtMax";

    /** CompDtMax Value */
    public static final String COMPDTMAX_VALUE = "99991231";
    //2016/05/11 S21_NA#7565 MOD End --------------
}
