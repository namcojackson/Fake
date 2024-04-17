/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1520.constant;

/**
 *<pre>
 * NWAL1520Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Fujitsu         A.Suda          Create          N/A
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#19916-2
 * 2017/09/19   Fujitsu         H.Sugawara      Update          QC#19918
 * 2019/05/31   Fujitsu         R.Nakamura      Update          S21_NA#50553
 * 2019/09/12   Fujitsu         R.Nakamura      Update          S21_NA#51929
 *</pre>
 */
public class NWAL1520Constant {


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

    /** No search result was found. */
    public static final String NWAM0006I = "NWAM0006I";

    /** Search ended normally but not all data is displayed since the result exceeded the maximum. */
    public static final String NWAM0007W = "NWAM0007W";

    /** This @ cannot be processed. Please confirm the [@] status. */
    public static final String NWAM0675E = "NWAM0675E";

    /** Enter a future date in [@]. */
    public static final String NWAM0676E = "NWAM0676E";

    /** The corresponding [@] does not exist. */
    public static final String NWAM0505E = "NWAM0505E";

    /** The details of the process target have not been selected. */
    public static final String NWAM0504E = "NWAM0504E";

    /** [@] is mandatory value. */
    public static final String NWAM0298E = "NWAM0298E";

    // Add Start 2017/09/19 QC#19918
    /** Please select all components of the set item. */
    public static final String NWAM0941E = "NWAM0941E";

    /** Please select parent / all components of the set item. */
    public static final String NWAM0942E = "NWAM0942E";
    // Add End 2017/09/19 QC#19918

    // Add Start 2019/09/12 QC#51929
    /** SO creation process is running.   Please wait and try again later. */
    public static final String NWAM0971E = "NWAM0971E";
    // Add End 2019/09/12 QC#51929

    //--------------------------------
    // FunctionID(Function Role)
    //--------------------------------

    /** View only */
    public static final String FUNC_VIEW = "NWAL1520T010";

    /** Sales Hold */
    public static final String FUNC_SALES_HLD_REL = "NWAL1520T020";

    /** Credit Hold */
    public static final String FUNC_CREDIT_HLD_REL = "NWAL1520T030";

    /** Customs & Regulatory Hold */
    public static final String FUNC_CUST_REGU_HLD_REL = "NWAL1520T040";

    /** Billing Hold */
    public static final String FUNC_BILLING_HLD_REL = "NWAL1520T050";

    /** Delivery Hold */
    public static final String FUNC_DELIVERY_HLD_REL = "NWAL1520T060";

    /** Workflow Hold */
    public static final String FUNC_WORKFLOW_HLD_REL = "NWAL1520T070";

    /** IT only role */
    public static final String FUNC_UPPER_TUB_VIEW = "NWAL1520T100";

    // 2017/07/27 S21_NA#19916-2 Add Start
    /** Header Level Search Header Only Check */
    public static final String NWAL1520_HDR_HLD_ONLY = "NWAL1520_HDR_HLD_ONLY";
    // 2017/07/27 S21_NA#19916-2 Add End

    // Add Start 2017/09/19 QC#19918
    /** Hold Level Desc Text RMA Suffix */
    public static final String HLD_LVL_DESC_TXT_RMA_SUFFIX = "(RMA)";

    /** Line sub number count */
    public static final int LINE_SUB_NUM_COUNT = 3;
    // Add End 2017/09/19 QC#19918

    // Add Start 2019/05/31 QC#50553
    /** Item Name Hold Until Date */
    public static final String ITEM_NM_HLD_UNTL_DATE = "Hold Until Date";
    // Add End 2019/05/31 QC#50553
}
