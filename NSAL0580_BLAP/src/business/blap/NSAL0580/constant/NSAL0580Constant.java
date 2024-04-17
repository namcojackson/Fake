/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0580.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         J.Kim           Create          N/A
 * 2015/12/15   Hitachi         K.Yamada        Update          CSA QC#1254
 * 2016/02/26   Hitachi         K.Kishimoto     Update          QC#2011
 * 2016/12/14   Hitachi         Y.Takeno        Update          QC#16285
 * 2018/08/26   Hitachi         K.Kim           Update          QC#22987
 * 2019/07/18   Hitachi         A.Kohinata      Update          QC#51706
 *</pre>
 */
public class NSAL0580Constant {

    /**
     * &param is found.(&param = No input parameter)
     */
    public static final String NSAM0353E = "NSAM0353E";

    /**
     * This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * Please enter [&param]. <&param = Description or Start Date>
     */
    public static final String NSAM0212E = "NSAM0212E";

    /**
     *Failed to update &param.(&param = Table name)
     */
    public static final String NSAM0031E = "NSAM0031E";

    // START 2018/08/26 [QC#22987, ADD]
    /**
     * Failed to update status because there is a workflow for this contract.
     */
    public static final String NSAM0737E = "NSAM0737E";
    // END 2018/08/26 [QC#22987, ADD]

    // add start 2015/12/15 CSA Defect#1254
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    // add end 2015/12/15 CSA Defect#1254

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /**
     * Contract Control Status - Entered
     */
    public static final String STATUS_ENTERED = "Entered";

    /**
     * Contract Control Status - Active
     */
    public static final String STATUS_ACTIVE = "Active";

    /**
     * Contract Control Status - Bill
     */
    public static final String STATUS_HOLD = "Hold";

    /**
     * Contract Control Status - Bill Hold
     */
    public static final String STATUS_BILL_HOLD = "Bill Hold";

    /**
     * Contract Control Status - Bill Hold
     */
    public static final String STATUS_BILLING_HOLD = "Billing Hold";

    /**
     * Contract Control Status - Bill Hold
     */
    public static final String STATUS_CANCELLED = "Cancelled";

    // Add Start 12/14/2016 <QC#16285>
    /**
     * SVC Memo (SVC_CMNT_TXT, Header Level)
     */
    public static final String SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR = "Manual update - Contract# [%s]";
    // End Start 12/14/2016 <QC#16285>

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
