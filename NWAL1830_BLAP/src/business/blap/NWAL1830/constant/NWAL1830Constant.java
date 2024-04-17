/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1830.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NWAL1830Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Fujitsu         Y.Taoka         Create          N/A
 * 2017/08/30   Fujitsu         S.Ohki          Update          S21_NA#20763
 * 2017/09/11   Fujitsu         M.Ohno          Update          S21_NA#19800(L3)
 * 2017/11/24   Fujitsu         A.Kosai         Update          S21_NA#21508
 * 2018/07/30   Fujitsu         M.Ohno          Update          S21_NA#26330
 * 2018/08/21   Fujitsu         Hd.Sugawara     Update          S21_NA#27489
 * 2020/04/13   Fujitsu         C.Hara          Update          QC#56374
 * 2020/05/14   Fujitsu         C.Hara          Update          QC#56509-1
 *</pre>
 */
public class NWAL1830Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1830";
    //--------------------------------
    // Message ID
    //--------------------------------
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

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Code Master @ is invalid Registered. Please call IT Division. */
    public static final String NWAM0811E = "NWAM0811E";

    /** Details require more than 1 row.  Please enter. */
    public static final String NWAM0812E = "NWAM0812E";

    /** Specified @ does not exist. */
    public static final String NWAM0684E = "NWAM0684E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** For the main machine is "Loan To Return", select the same action. */
    public static final String NMAM8543E = "NMAM8543E";

    /** Select an action. Accessary cannot be only conversion. */
    public static final String NMAM8544E = "NMAM8544E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Registration cannot be completed because unreleased Hold information exists. */
    public static final String DUPL_BILLG_HLD_ERR = "NWZM0443E";

    /** Unreleased @ Hold information exists already. */
    public static final String NWAM8454I = "NWAM8454I";

    // 2017/11/24 S21_NA#21508 Add Start
    /** The entered item is not a returnable item. */
    public static final String NWZM1507E = "NWZM1507E";

    /** The entered item [@] is not a returnable item. */
    public static final String NWAM0948E = "NWAM0948E";
    // 2017/11/24 S21_NA#21508 Add End

    // 2020/05/14 QC#56509-1 Add Start
    /** Please select all accessories which are in the configuration, if you would like to sell main machine. */
    public static final String NWAM0982E = "NWAM0982E";
    // 2020/05/14 QC#56509-1 Add End

    //--------------------------------
    // ORD_LINE_VAL_SET Key
    //--------------------------------
    /** ORD_LINE_VAL_SET Key: Loan Conversion Line */
    public static final String ORD_LINE_VAL_SET_KEY = "LOAN_CONV_LINE_CRAT"; // 2017/09/11 S21_NA#19800 mod
    //--------------------------------
    // LABEL NAEM
    //--------------------------------
    /** Action Label Name */
    public static final String ACTION_LBL_NOT_CONV = "NOT ELIGIBLE";

    // --------------------------------
    // Radio Button Value
    // --------------------------------
    /** New Order */
    public static final BigDecimal RADIO_VAL_NEW_ORD = BigDecimal.ZERO;

    // --------------------------------
    // OTHER
    // --------------------------------
    /** Period */
    public static final String PERIOD = ".";

    /** Timestamp Pattern */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS"; // 2017/08/30 QC#20763 Add

    // 2017/09/11 S21_NA#19800 add start
    /** Loan Order Action (Loan Sell) */
    public static final String LOAN_ORD_ACTION_LOAN_SELL = "LOAN SELL";

    /** Loan Order Action (Loan Return) */
    public static final String LOAN_ORD_ACTION_LOAN_RETURN = "LOAN RETURN";

    /** Order Line Status Name (PENDING FULFILLMENT)  */
    public static final String ORD_LINE_STS_NM_PENDING_FULFILLMENT = "PENDING FULFILLMENT";
    // 2017/09/11 S21_NA#19800 add end
    /** VarCharConstKey SVC_ISTL_RULE_NON_RTL_EQIP_ORD */
    public static final String SVC_ISTL_RULE_NON_RTL_EQIP_ORD = "SVC_ISTL_RULE_NON_RTL_EQIP_ORD"; // QC#22641
    
    // 2018/07/30 S21_NA#26330 add start
    /** VarCharConstKey NWAL1830_LOAN_RTRN_RSN_CD */
    public static final String NWAL1830_LOAN_RTRN_RSN_CD = "NWAL1830_LOAN_RTRN_RSN_CD";
    // 2018/07/30 S21_NA#26330 add end

    // Add Start 2018/08/21 QC#27489
    /** ORD_LINE_STS_NM : CLOSED */
    public static final String ORD_LINE_STS_NM_CLOSED = "CLOSED";
    // Add End 2018/08/21 QC#27489

    // 2020/04/13 QC#56374 Add Start
    /** DS_ORD_LINE_CATG_NM : MERCHANDISE */
    public static final String MERCHANDISE = "MERCHANDISE";

    /** DS_ORD_LINE_CATG_NM : INITIAL_SUPPLIES */
    public static final String INITIAL_SUPPLIES = "INITIAL SUPPLIES";
    // 2020/04/13 QC#56374 Add End

}
