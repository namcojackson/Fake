/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1120.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Meter and Pricing Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2017/02/21   Hitachi         K.Kishimoto     Update          CSA QC#17646
 *</pre>
 */
public final class NSAL1120Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1120";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * An input parameter, [@],  has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /**
     * Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * Your request cannot be processed under this status.
     */
    public static final String NSAM0065E = "NSAM0065E";

    /**
     * The value for @ must be greater than or equal to @.
     */
    public static final String NSZM0860E = "NSZM0860E";

    /**
     * Copy Vol must be greater than above.
     */
    public static final String NSAM0438E = "NSAM0438E";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";
    // START 2017/09/15 E.Kameishi [QC#18636,ADD]
    /**
     * [@] must be less than the difference between [@] and [@].
     */
    public static final String NSAM0707E = "NSAM0707E";
    // END 2017/09/15 E.Kameishi [QC#18636,ADD]

    /**
     * Mode Code: Base Charge
     */
    public static final String MODE_CODE_BASE = "1";

    /**
     * Mode Code: Usage Charge
     */
    public static final String MODE_CODE_USAGE = "2";

    /**
     * TABLE_A (Base Price Details)
     */
    public static final String TABLE_A = "A";

    /**
     * TABLE_B (Meter Details)
     */
    public static final String TABLE_B = "B";

    /**
     * TABLE_C (Pricing Details)
     */
    public static final String TABLE_C = "C";

    /**
     * Pagenation Mode: Init
     */
    public static final String PAGE_MODE_INIT = "0";

    /**
     * Pagenation Mode: Next
     */
    public static final String PAGE_MODE_NEXT = "1";

    /**
     * Pagenation Mode: Prev
     */
    public static final String PAGE_MODE_PREV = "2";

    /**
     * Pagenation Mode: Submit Error
     */
    public static final String PAGE_MODE_SUBMIT_ERR = "3";

    /**
     * Parameter MODE_CODE
     */
    public static final String MODE_CODE = "MODE_CODE";

    /**
     * VAR_CHAR_CONST:SPCL_FLT_MDSE_CD
     */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * Process Mode(NSZC053001) : CI Update
     */
    public static final String MODE_CI_UPDATE = "2";

    /**
     * Start Read
     */
    public static final String START_READ = "Start Read";

    /**
     * End Read
     */
    public static final String END_READ = "End Read";

    /**
     * Start Test
     */
    public static final String START_TEST = "Start Test";

    /**
     * End Test
     */
    public static final String END_TEST = "End Tes";
    // START 2017/09/15 E.Kameishi [QC#18636,ADD]
    /**
     * Test Copies
     */
    public static final String TEST_COPIES = "Test Copies";
    // END 2017/09/15 E.Kameishi [QC#18636,ADD]
    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    /**
     * End Test
     */
    public static final String DEF_SER_NUM = ZYPCommonFunc.leftPad("", 32, "_");
    // End   2017/02/21 K.Kishimoto [QC#17646, ADD]
}
