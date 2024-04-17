/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1200.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18195
 * 2017/09/04   Hitachi         T.Kanasaka      Update          QC#15134
 *</pre>
 */
public class NSAL1200Constant {
    /** Business ID */
    public static final String BUSINESS_ID = "NSAL1200";

    /** SCRN_ID : NSAL1200Scrn00 */
    public static final String SCRN_ID = "NSAL1200Scrn00";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /** The data @ does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Please select at least 1 check-box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** [@] is duplicated. */
    public static final String NSAM0035E = "NSAM0035E";

    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /** The multiplier must be between @ and @. */
    public static final String NSAM0316E = "NSAM0316E";

    /** The multiplier must be incremented by @. */
    public static final String NSAM0317E = "NSAM0317E";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSAM0320E = "NSAM0320E";

    /** The end date must be later than [@]. */
    public static final String NSAM0374E = "NSAM0374E";

    /** [@] is mandatory when [@] is "@". */
    public static final String NSAM0442E = "NSAM0442E";

    /** The effective periods are overlapped. */
    public static final String NSAM0446E = "NSAM0446E";

    /** End Date is earlier than sales date. */
    public static final String NSAM0447E = "NSAM0447E";

    /** [@] cannot be entered when [@] is "@". */
    public static final String NSAM0448E = "NSAM0448E";

    /** The data is not saved yet. The data you are editing will be discarded.  OK? */
    public static final String NSAM0449W = "NSAM0449W";

    // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
    /** Please enter a number between [@] and [@]. */
    public static final String NSAM0194E = "NSAM0194E";
    // END 2017/09/04 T.Kanasaka [QC#15134,ADD]

    // START 2017/08/03 T.Kanasaka [QC#18195,ADD]
    /** table name : Billing Meter Mapping (BLLG_MTR_MAP) */
    public static final String TBL_NM_BLLG_MTR_MAP = "Billing Meter Mapping";
    // END 2017/08/03 T.Kanasaka [QC#18195,ADD]

    /** table name : Meter Group (MTR_GRP) */
    public static final String TBL_NM_MTR_GRP = "Meter Group";

    /** table name : DS Model Meter (DS_MDL_MTR) */
    public static final String TBL_NM_DS_MDL_MTR = "DS Model Meter";

    /** NUM_CONST : NSAL0320_MTR_MULT_RATE_FCT_NUM */
    public static final String NSAL0320_MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";

    /** NUM_CONST : NSAL0320_MTR_MULT_RATE_MIN_NUM */
    public static final String NSAL0320_MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";

    /** NUM_CONST : NSAL0320_MTR_MULT_RATE_MAX_NUM */
    public static final String NSAL0320_MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";

    /** item name : XX_CHK_BOX_A */
    public static final String XX_CHK_BOX_A = "xxChkBox_A";

    /** Max Date Value : 99991231 */
    public static final String MAX_DT_VAL = "99991231";

    // START 2017/08/03 T.Kanasaka [QC#18195,ADD]
    /** BLLG_MTR_MAP_LVL_NUM: 1 */
    public static final String BLLG_MTR_MAP_LVL_NUM_1 = "1";

    /** BLLG_MTR_MAP_LVL_NUM: 2 */
    public static final String BLLG_MTR_MAP_LVL_NUM_2 = "2";

    /** BLLG_MTR_MAP_LVL_NUM: 3 */
    public static final String BLLG_MTR_MAP_LVL_NUM_3 = "3";
    // END 2017/08/03 T.Kanasaka [QC#18195,ADD]

    // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
    /** MIN_CNTR_DIGIT_NUM: 1 */
    public static final BigDecimal MIN_CNTR_DIGIT_NUM = BigDecimal.ONE;

    /** MAX_CNTR_DIGIT_NUM: 10 */
    public static final BigDecimal MAX_CNTR_DIGIT_NUM = BigDecimal.TEN;
    // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
}
