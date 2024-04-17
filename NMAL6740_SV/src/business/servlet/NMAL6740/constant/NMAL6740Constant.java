/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         H.Ikeda         Update          CSA
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2018/08/07   Fujitsu         S.Ohki          Update          QC#27222
 *</pre>
 */
public class NMAL6740Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL6740";

    /** Function ID1 */
    public static final String FUNC_ID1 = "NMAL6740001";

    /** Function ID2 */
    public static final String FUNC_ID2 = "NMAL6740002";

    /** Error Message (Mandatory Key Violation) */
    public static final String ZZM9000E = "ZZM9000E";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6740";

    /** Function Code:20 */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code: 40 */
    public static final String FUNC_CD_UPD = "40";

    /** UPDATE */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

    // ----------- Function Button ----------- //
    /** Save - Button Name */
    public static final String BTN_01_SAV_NAME = "btn1";

    /** Save - Guard Condition */
    public static final String BTN_01_SAV_GUARD = "CMN_Save";

    /** Save - Label Name */
    public static final String BTN_01_SAV_LABEL = "Save";

    /** Submit - Button Name */
    public static final String BTN_02_SUB_NAME = "btn2";

    /** Submit - Guard Condition */
    public static final String BTN_02_SUB_GUARD = "CMN_Submit";

    /** Submit - Label Name */
    public static final String BTN_02_SUB_LABEL = "Submit";

    /** Apply - Button Name */
    public static final String BTN_03_APL_NAME = "btn3";

    /** Apply - Guard Condition */
    public static final String BTN_03_APL_GUARD = "CMN_Apply";

    /** Apply - Label Name */
    public static final String BTN_03_APL_LABEL = "Apply";

    /** Approve - Button Name */
    public static final String BTN_04_APR_NAME = "btn4";

    /** Approve - Guard Condition */
    public static final String BTN_04_APR_GUARD = "CMN_Approve";

    /** Approve - Label Name */
    public static final String BTN_04_APR_LABEL = "Approve";

    /** Reject - Button Name */
    public static final String BTN_05_REJ_NAME = "btn5";

    /** Reject - Guard Condition */
    public static final String BTN_05_REJ_GUARD = "CMN_Reject";

    /** Reject - Label Name */
    public static final String BTN_05_REJ_LABEL = "Reject";

    /** Download - Button Name */
    public static final String BTN_06_DWL_NAME = "btn6";

    /** Download - Guard Condition */
    public static final String BTN_06_DWL_GUARD = "CMN_Download";

    /** Download - Label Name */
    public static final String BTN_06_DWL_LABEL = "Download";

    /** Delete - Button Name */
    public static final String BTN_07_DEL_NAME = "btn7";

    /** Delete - Guard Condition */
    public static final String BTN_07_DEL_GUARD = "CMN_Delete";

    /** Delete - Label Name */
    public static final String BTN_07_DEL_LABEL = "Delete";

    /** Clear - Button Name */
    public static final String BTN_08_CLE_NAME = "btn8";

    /** Clear - Guard Condition */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";

    /** Clear - Label Name */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** Reset - Button Name */
    public static final String BTN_09_RST_NAME = "btn9";

    /** Reset - Guard Condition */
    public static final String BTN_09_RST_GUARD = "CMN_Reset";

    /** Reset - Label Name */
    public static final String BTN_09_RST_LABEL = "Reset";

    /** Return - Button Name */
    public static final String BTN_10_RTR_NAME = "btn10";

    /** Return - Guard Condition */
    public static final String BTN_10_RTR_GUARD = "CMN_Return";

    /** Return - Label Name */
    public static final String BTN_10_RTR_LABEL = "Return";
    
    /** Button Get CoA Channel Name */
    public static final String[] BTN_GET_COA_CH_NM = {"GetCoaChNm", "GetCoaChNm" };

    /** Button Get CoA Affiliate Name */
    public static final String[] BTN_GET_COA_AFFL_NM = {"GetInterCompanyNm", "GetInterCompanyNm" };

    // ----------- Message ----------- //
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Ship To Cust Cd */
    public static final String SHIP_TO_CD = "Ship To Cust Code";

    // Add Start 2018/08/07 QC#27222
    /** TAB_TAXING */
    public static final String TAB_TAXING = "Taxing";

    /** Tab Taxing READ */
    public static final String FUNC_ID_TAXING_INQUIRY = "NMAL6740T030";

    /** Tab Taxing UPDATE */
    public static final String FUNC_ID_TAXING_UPDATE = "NMAL6740T040";
    // Add End 2018/08/07 QC#27222
}
