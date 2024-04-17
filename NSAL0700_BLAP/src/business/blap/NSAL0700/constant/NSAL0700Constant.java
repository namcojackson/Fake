/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0700.constant;

/**
 *<pre>
 * Contract On Billing Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2016/12/05   Hitachi         T.Mizuki        Update          QC#4210
 * 2016/12/13   Hitachi         Y.Takeno        Update          QC#16285
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2018/08/26   Hitachi         K.Kim           Update          QC#22987
 * 2019/07/18   Hitachi         A.Kohinata      Update          QC#51706
 *</pre>
 */
public final class NSAL0700Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0700";

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Error Message */
    public static final String NSAM0013E = "NSAM0013E";

    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Warning Message */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** Return Message : Billing Hold */
    public static final String RTRN_MSG_SUCCESS = "Success";

    /** Return Message : Failed to update Contract to Billing Hold */
    public static final String RTRN_MSG_UPDATE_FAILED = "Failed to update Contract to Billing Hold";

    // START 2018/08/26 [QC#22987, ADD]
    /** Return Message : Failed to update status because there is a workflow for this contract. */
    public static final String RTRN_MSG_UPDATE_FAILED_WF = "Failed to update status because there is a workflow for this contract.";
    // END 2018/08/26 [QC#22987, ADD]

    /** Return Message : Failed to update Read Methods */
    public static final String RTRN_MSG_FAILED = "Failed to update Read Methods";

    /** Level Number : 10 */
    public static final int LVL_NUM_10 = 10;

    /** Level Number : 20 */
    public static final int LVL_NUM_20 = 20;

    /** Level Number : 30 */
    public static final int LVL_NUM_30 = 30;

    /** Meter Label Name : Base */
    public static final String MTR_LB_NM = "Base";

 // START 2016/12/13 [QC#16285, ADD]
    /** SVC Memo (SVC_MEMO_TRX_NM)  : DS_CONTR_BLLG_MTR_PK */
    public static final String SVC_MEMO_TRX_NM_BLLG_MTR = "DS_CONTR_BLLG_MTR_PK";

    /** SVC Memo (SVC_CMNT_TXT, Header Level) */
    public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR = "Manual update - Contract# [%s]";

    /** SVC Memo (SVC_CMNT_TXT, Detail Level) */
    public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_DTL = "Manual update - Serial# [%s] on Contract# [%s]";

    /** SVC Memo (SVC_CMNT_TXT, Meter Level) */
    public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_MTR = "Manual update - Serial# [%s] on Contract# [%s] / [%s]";
    // END   2016/12/13 [QC#16285, ADD]

    // add start 2019/07/18 QC#51706
    /** SVC Memo Catg For Manual Blling Hold */
    public static final String SVC_MEMO_CATG_FOR_MAN_BLLG_HLD = "__";

    /** SVC Memo Type For Manual Blling Hold */
    public static final String SVC_MEMO_TP_FOR_MAN_BLLG_HLD = "__";

    /** SVC Memo Reason For Manual Blling Hold */
    public static final String SVC_MEMO_RSN_FOR_MAN_BLLG_HLD = "__";

    /** SVC Memo Comment For Manual Blling Hold */
    public static final String SVC_MEMO_CMNT_FOR_MAN_BLLG_HLD = "Manual Billing Hold";
    // add end 2019/07/18 QC#51706
}
