/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7290.constant;

/**
 *<pre>
 * NMAL7290Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6931
 *</pre>
 */
public class NMAL7290Constant {

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

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /** [@] is already registered. */
    public static final String NMAM0010E = "NMAM0010E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** The field of [@] is not input. */
    public static final String ZZZM9007E = "ZZZM9007E";

    /** Please add Rule/Table Entry more than two. */
    public static final String NMAM8307E = "NMAM8307E";

    /** Evaluator is mandatory if Operator is "Or". */
    public static final String NMAM8308E = "NMAM8308E";

    /** You cannot enter Evaluator if Operator is not "Or". */
    public static final String NMAM8309E = "NMAM8309E";

    /** Please enter different Seq after "Or" operator. */
    public static final String NMAM8310E = "NMAM8310E";

    /** You cannot enter Operator at the end of rows. */
    public static final String NMAM8311E = "NMAM8311E";

    // QC#9694 2016/07/08 Add Start
    /** Please select all the items the same value. */
    public static final String NMAM8258E = "NMAM8258E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** You want to delete the [@]?  If 'yes', click again. */
    public static final String NMAM8330I = "NMAM8330I";

    /** @ is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /**  [@] already exists in [@] */
    public static final String NMAM0834E = "NMAM0834E";

    /**  [@] must be unique within the same [@]. */
    public static final String NMAM8625E = "NMAM8625E";

    /** When Price Formula  is "GET_PROMOTION_API", you must select "ALL" in Precedence Action. */
    public static final String NMAM8626E = "NMAM8626E";
    // QC#9694 2016/07/08 Add End

    /** Price Adjustment Precedence Group # */
    public static final String MSG_PRC_RULE_PRCD_GRP_NUM = " Price Adjustment Precedence Group #";

    /** Price Adjustment Precedence Group Name */
    public static final String MSG_PRC_RULE_PRCD_GRP_NM = " Price Adjustment Precedence Group Name";

    /** Operator */
    public static final String MSG_OP = "Operator";

    // --------------------------------
    // Table Name
    // --------------------------------
    /** PRC_PRCD_ACT_TP */
    public static final String TBL_PRC_PRCD_ACT_TP = "PRC_PRCD_ACT_TP"; // QC#9694 2016/07/08 Add

    /** PRC_RULE_CATG */
    public static final String TBL_PRC_RULE_CATG = "PRC_RULE_CATG";

    /** LINE_BIZ_TP */
    public static final String TBL_LINE_BIZ_TP = "LINE_BIZ_TP";

    /** PRC_RULE_COND_TP */
    public static final String TBL_PRC_RULE_COND_TP = "PRC_RULE_COND_TP";

    /** PRC_RULE_OP_TP */
    public static final String TBL_PRC_RULE_OP_TP = "PRC_RULE_OP_TP";

    /** PRC_RULE_EVTR_TP */
    public static final String TBL_PRC_RULE_EVTR_TP = "PRC_RULE_EVTR_TP";

    /** PRC_RULE_PRCD */
    public static final String TBL_PRC_RULE_PRCD = "PRC_RULE_PRCD";

    /** PRC_RULE_PRCD_DTL */
    public static final String TBL_PRC_RULE_PRCD_DTL = "PRC_RULE_PRCD_DTL";

    // --------------------------------
    // CSV Download
    // --------------------------------
    /** CSV File Name */
    public static final String CSV_FILE_NAME = "NMAL7290_PrcRulePrcd";

    /** CSV Extension */
    public static final String CSV_EXT = ".csv";

    /** Header: Price Adjustment Precedence Group# */
    public static final String HEADER_GRP_NUM = "Price Adjustment Precedence Group#";

    /** Header: Price Adjustment Precedence Name */
    public static final String HEADER_GRP_NM = "Price Adjustment Precedence Group Name";

    // Add Start 2016/09/27 QC#6931
    /** Header: Precedence Action */
    public static final String HEADER_GRP_ACT = "Precedence Action";

    /** Header: Effective Date From */
    public static final String HEADER_EFF_FROM_H = "Effective Date From";

    /** Header: Effective Date To */
    public static final String HEADER_EFF_TO_H = "Effective Date To";
    // Add End 2016/09/27 QC#6931

    /** Header: Precedence Action */
    public static final String HEADER_SEQ = "Seq";

    // Dell Start 2016/09/27 QC#6931
//    /** Header: Operator */
//    public static final String HEADER_OP = "Operator";
//
//    /** Header: Evaluator */
//    public static final String HEADER_EVLV = "Evaluator";
    // Dell End 2016/09/27 QC#6931

    /** Header: ID */
    public static final String HEADER_ID = "ID";

    /** Header: Rule or Table Name */
    public static final String HEADER_RULE_NM = "Rule or Table Name";

    /** Header: Price Adjustment Type */
    public static final String HEADER_TP = "Price Adjustment Type";

    /** Header: Line of Business */
    public static final String HEADER_BIZ = "Line of Business";

    /** Header: Category */
    public static final String HEADER_CATG = "Category";

    /** Header: Apply Automatically */
    public static final String HEADER_AUTO = "Apply Automatically";

    /** Header: Override */
    public static final String HEADER_OVR = "Override";

    /** Header: Status */
    public static final String HEADER_STS = "Status";

    /** Header: Default Rule Precedence */
    public static final String HEADER_DEF = "Default Rule Precedence";

    /** Header: Effective Date From */
    public static final String HEADER_EFF_FROM = "Effective Date From";

    /** Header: Effective Date To */
    public static final String HEADER_EFF_TO = "Effective Date To";

    // Add Start 2016/09/27 QC#6931
    /** Header: Created By */
    public static final String HEADER_CREATED_BY = "Created By";

    /** Header: Created Date */
    public static final String HEADER_CREATED_DATE = "Created Date";

    /** Header: Updated By */
    public static final String HEADER_UPLOADED_BY = "Updated By";

    /** Header: Updated Date */
    public static final String HEADER_UPLOADED_DATE = "Updated Date";
    // Add End 2016/09/27 QC#6931

    // --------------------------------
    // Other
    // --------------------------------
    /** xxChkBox_A */
    public static final String CHKBOX_A = "xxChkBox_A";

    /** xxChkBox_B */
    public static final String CHKBOX_B = "xxChkBox_B";

    // Add Start 2016/09/27 QC#6931
    /** convert format : 0.regex, 1.replacement */
    public static final String[] DT_CONV_FORMAT = {"^(....)(..)(..)$", "$2/$3/$1" };
    // Add End 2016/09/27 QC#6931

}
