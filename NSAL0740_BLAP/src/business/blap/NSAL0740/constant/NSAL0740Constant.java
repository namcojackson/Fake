/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0740.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/07   Hitachi         T.Aoyagi        Update          QC#3945
 * 2016/05/17   Hitachi         M.Gotou         Update          QC#4472
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 *</pre>
 */
public class NSAL0740Constant {

    /** Business ID. */
    public static final String BUSINESS_ID = "NSAL0740";

    /** DATE_FORMAT : yyyyMMddHHmmssSSS */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /**
     * The process has been error in some data. Please check the data.
     */
    public static final String NSAM0394W = "NSAM0394W";

    // START 2016/03/07 T.Aoyagi [QC#3945, ADD]
    /** When [@] is not [@], enter [@]. */
    public static final String NSAM0434E = "NSAM0434E";
    // END 2016/03/07 T.Aoyagi [QC#3945, ADD]

    // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
    /** @ cannot be entered because @ is not equivalent. */
    public static final String NSAM0710E = "NSAM0710E";
    // END 2017/12/21 M.Kidokoro [QC#22660, ADD]

    // START 2018/11/16 K.Kitachi [QC#28638, ADD]
    /** Fixed Month is greater than contract term(@-@). */
    public static final String NSAM0740E = "NSAM0740E";

    /** Hold Price Until Date is not included in the contract term(@-@). */
    public static final String NSAM0741E = "NSAM0741E";

    /** [@] field is less than minimum value. */
    public static final String ZZM9003E = "ZZM9003E";
    // END 2018/11/16 K.Kitachi [QC#28638, ADD]

    /** DS_CONTR_RNW_ESCL */
    public static final String DS_CONTR_RNW_ESCL = "Direct Sales Contract Renewal Escalation";

    /** SVC_MEMO */
    public static final String SVC_MEMO = "Service Memo";

    /** Base/Overage : BASE */
    public static final String BASE = "BASE";

    /** Base/Overage : OVERAGE */
    public static final String OVERAGE = "OVERAGE";
    // START 2016/03/07 T.Aoyagi [QC#3945, DEL]
//    /** Code Table : CONTR_UPLFT_TP */
//    public static final String CONTR_UPLFT_TP = "CONTR_UPLFT_TP";
//
//    /** Code Table : UPLFT_PRC_METH */
//    public static final String UPLFT_PRC_METH = "UPLFT_PRC_METH";
    // END 2016/03/07 T.Aoyagi [QC#3945, DEL]
    /** Line Level Number : 1(contract) */
    public static final String LINE_LEVEL_CONTRACT = "1";

    /** Line Level Number : 2(contract detail) */
    public static final String LINE_LEVEL_CONTRACT_DETAIL = "2";

    /** Line Level Number : 3(base / overage) */
    public static final String LINE_LEVEL_BASE_OVERAGE = "3";

    /** checkbox(Contract/Serial#) */
    public static final String XX_CHK_BOX_S1 = "xxChkBox_S1";

    /** checkbox(Base/Overage) */
    public static final String XX_CHK_BOX_S2 = "xxChkBox_S2";
    // add start 2016/05/17 CSA Defect#4472
    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";
    // add end 2016/05/17 CSA Defect#4472
}
