/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7270.constant;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *<pre>
 * NMAL7270Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6931
 * 2018/06/07   Fujitsu         T.Noguchi       Update          QC#26099
 *</pre>
 */
public class NMAL7270Constant {

    /** Check Box PA */
    public static final String CHK_A = "xxChkBox_A1";

    /** Check Box PB */
    public static final String CHK_B = "xxChkBox_B1";

    /** COMMA */
    public static final String COMMA = ",";

    /** ASTERISK */
    public static final String ASTERISK = "*";

    /** HIGH_VAL_DT : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    /** Mode : Search */
    public static final String MODE_SEARCH = "Search";

    /** Mode : Submit */
    public static final String MODE_SUBMIT = "Submit";

    /** Mode : OpenWin_PrcCondGrp */
    public static final String MODE_OPENWIN_PRC = "OpenWin_PrcCondGrp";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** [@] is already registered. */
    public static final String NMAM0010E = "NMAM0010E";

    /** [@] is not registered. */
    public static final String NMAM0011E = "NMAM0011E";

    /** Details cannot be added because the number will exceed [@]. */
    public static final String NMAM0050E = "NMAM0050E";

    /** The entered [@] does not exist in [@].*/
    public static final String NMAM0163E = "NMAM0163E";

    /** The process ended abnormally. */
    public static final String NMAM8020E = "NMAM8020E";

    /** Please select item(s). */
    public static final String NMAM8054E = "NMAM8054E";

    /** Effective date is out of range. Please check the Effective date. */
    public static final String NMAM8213E = "NMAM8213E";

    /** Please select the Modify corresponding to @. */
    public static final String NMAM8259E = "NMAM8259E";

    /** Delete target, it has been used in the @. Please try again to delete. */
    public static final String NMAM8260E = "NMAM8260E";

    /** You can not register by selecting the [@] in the item of [@]. */
    public static final String NMAM8326E = "NMAM8326E";

    /** Given price rule combination is not accepted. Please review validation table (PRC_RULE_CMBN_EXCL). */
    public static final String NMAM8369E = "NMAM8369E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Please select all the items the same value. */
    public static final String NMAM8258E = "NMAM8258E"; // QC#9694 2016/07/08 Add

    /** If Default Precedence# is duplicated, Please register the Price Precedence. */
    public static final String NMAM8627W = "NMAM8627W"; // QC#9694 2016/07/08 Add

    // 2018/06/07 QC#26099 Add Start
    /** [@] field has too many decimal digits entered. */
    public static final String ZZM9015E = "ZZM9015E";
    // 2018/06/07 QC#26099 Add End

    /** csv file name : Transaction Conditions */
    public static final String CSV_FILE_NM_TRX_COND = "NMAL7270PriceRuleTrxCond";

    /** csv file name : Adjustment Details */
    public static final String CSV_FILE_NM_ADJ_DTL = "NMAL7270PriceRuleAdjDtl";

    /** csv file extension */
    public static final String CSV_FILE_EXTENSION = ".csv";

    // Mod Start 2016/09/27 QC#6931
    /** CSV Header For Download : Transaction Conditions */
    public static final String[] CSV_DOWNLOAD_HEADER_TRX_COND = new String[] {
        "Rule ID"
        , "Rule Name"
        , "Rule Description"
        , "Default Rule Precedence"
        , "Precedence Group"
        , "Line of Business"
        , "Rule Category "
        , "Apply Automatically"
        , "Override"
        , "Active "
        , "Effective Date From"
        , "Effective Date To"
        , "Rule Created By"
        , "Rule Created Date"
        , "Rule Updated By"
        , "Rule Updated Date "
        , "Category"
        , "Attribute"
        , "Value From"
        , "Value From Name"
        , "Value To"
        , "Condition#"
        , "Effective Date From"
        , "Effective Date To"
        , "Created By"
        , "Created Date"
        , "Updated By"
        , "Updated Date"
    };

    /** CSV Header For Download : Adjustment Details */
    public static final String[] CSV_DOWNLOAD_HEADER_ADJ_DTL = new String[] {
        "Rule ID"
        , "Rule Name"
        , "Rule Description"
        , "Default Rule Precedence"
        , "Precedence Group"
        , "Line of Business"
        , "Rule Category "
        , "Apply Automatically"
        , "Override"
        , "Active "
        , "Effective Date From"
        , "Effective Date To"
        , "Rule Created By"
        , "Rule Created Date"
        , "Rule Updated By"
        , "Rule Updated Date "
        , "Adjustment Condition"
        , "Charge Name"
        , "Modify"
        , "Adjustment"
        , "Type"
        , "Adjustment Level"
        , "Value"
        , "Effective Date From"
        , "Effective Date To"
        , "Created By"
        , "Created Date"
        , "Updated By"
        , "Updated Date"
    };
    // Mod End 2016/09/27 QC#6931

    // Add Start 2016/09/27 QC#6931
    /** convert format : 0.regex, 1.replacement */
    public static final String[] DT_CONV_FORMAT = {"^(....)(..)(..)$", "$2/$3/$1" };
    // Add End 2016/09/27 QC#6931

    /** 100 */
    public static final BigDecimal NUM_100 = new BigDecimal(100);

    /** percent digit */
    public static final int PERCENT_DIGIT = 2;

    /**db digit */
    public static final int DB_DIGIT = 5;

    // 2018/06/07 QC#26099 Add Start
    /** decimal format for amount */
    public static final DecimalFormat DF_AMT = new DecimalFormat("#,##0.00");
    // 2018/06/07 QC#26099 Add End
}
