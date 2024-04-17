/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6730.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2016/02/18   Fujitsu         C.Tanaka        Update          QC#2440
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/05/06   SRAA            Y.Chen          Update          QC#6659
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/08/12   SRAA            Y.Chen          Update          QC#12469
 * 2017/08/25   CITS            T.Tokutomi      Update          QC#19869
 * 2018/05/08   Fujitsu         H.Nagashima     Update          QC#23604
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 *</pre>
 */
public interface NMAL6730Constant {

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** It failed to delete [@]. */
    public static final String NMAM0208E = "NMAM0208E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Please check at least 1 checkbox. */
    public static final String NMAM0835E = "NMAM0835E";

    /** [@] field is mandatory. */
    public static final String NMAM0836E = "NMAM0836E";

    /** The entered [@] does not exist in master. */
    public static final String NMAM8121E = "NMAM8121E";

    public static final String NMAM8180E = "NMAM8180E";

    public static final String NMAM0072E = "NMAM0072E";

    /** When @ contains @, @ is required. */
    public static final String NMAM8177E = "NMAM8177E";

    /** When @ is entered, @ must be entered. */
    public static final String NMAM8178E = "NMAM8178E";

    /** Please select @ when @ is entered. */
    public static final String NMAM8179E = "NMAM8179E";

    /**
     * @ cannot be added because it is exceeding the maximum number of
     * row [@]
     */
    public static final String NMAM8187E = "NMAM8187E";

    public static final String NMAM0075E = "NMAM0075E";

    /**
     * Please select the same value for @ in the same
     * "Invoice Group#".
     */
    public static final String NMAM0291E = "NMAM0291E";

    /**
     * In case Bill Vehicle is @, please select Consolidated for the
     * Bill Type.
     */
    public static final String NMAM0292E = "NMAM0292E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";
    
    // QC#6382
    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";
    
    // QC#6659
    /** When @ is entered, @ cannot entered. */
    public static final String NMAM8409E = "NMAM8409E";

    /** The combination between [@] and [@] is not correct. */
    public static final String NMAM0306E = "NMAM0306E";
    
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** TAB_FINANCIAL */
    public static final String TAB_FINANCIAL = "Financial";

    /** TAB_INV_BLLG */
    public static final String TAB_INV_BLLG = "InvBllg";

    // Add Start 2018/08/01 QC#27222
    /** TAB_TAXING */
    public static final String TAB_TAXING = "Taxing";
    // Add End 2018/08/01 QC#27222

    /** pct */
    public static final BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");

    /** MAX_ROW_NUM */
    public static final int MAX_ROW_NUM = 2001;

    /** MAX_LINE_NUM */
    public static final int MAX_LINE_NUM = 200;

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String NMAM8171W = "NMAM8111E";

    /**
     * @ cannot be added because it is exceeding the maximum number of
     * row [@]
     */
    public static final String NSBM0058E = "NSBM0058E";

    public static final String ZZZM9025E = "ZZZM9025E";
    
    // QC#7781
    /** The maximum number of data has been exceeded. */
    public static final String NMAM0289E = "NMAM0289E";
    
    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";
    
    // QC#12469
    /** If Hard Hold Flag is Y, please specify Credit Hold as 'Hold'. */
    public static final String NMAM8638E = "NMAM8638E";

    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";

    //QC#23604 add Start
    /** @ cannot be entered if @ is @. */
    public static final String NMAM8682E = "NMAM8682E";
    //QC#23604 add End

    // Start 2023/2/02 S.Naktani [QC#60811, ADD]
    /** Please enter [@] between [@] and [@]. */
    public static final String NMAM8093E = "NMAM8093E";
    
    /** MLY_MAX_LATE_FEE_RATE. */
    public static final String MLY_MAX_LATE_FEE_RATE = "MLY_MAX_LATE_FEE_RATE";
    // End 2023/2/02 S.Naktani [QC#60811, ADD]
    
    public static final String SCRN_EVENT_INIT = "01";

    public static final String SCRN_EVENT_NEW = "02";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** Business ID */
    public static final String BUSINESS_ID = "NMAL6730";

    /** CONTROL_STR */
    public static final String CONTROL_STR = "Control ";

    // QC#7781
    /** Char: Comma */
    public static final String CHAR_COMMA = ",";
    
    /** Max Customer Invoice Rule Recipient Count */
    public static final int MAX_CUST_INV_RULE_RCPNT_CNT = 20;

    // QC#19869
    /** Function ID1 */
    public static final String DEF_DPLY_COA_INFO_APP_FUNC_ID = "NMAL6730001";
}
