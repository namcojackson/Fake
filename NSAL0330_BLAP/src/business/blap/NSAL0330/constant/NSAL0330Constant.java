/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0330.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/08/05   Hitachi         K.Kishimoto     Update          QC#12879
 * 2016/08/09   Hitachi         K.Kishimoto     Update          QC#12310
 * 2016/09/06   Hitachi         K.Kishimoto     Update          QC#12429
 * 2016/11/17   Hitachi         T.Tomita        Update          QC#15942
 * 2017/08/03   Hitachi         E.Kameishi      Update          QC#18586
 *</pre>
 */
public class NSAL0330Constant {

    /** Failed to update @ [@]. */
    public static final String NSAM0001E = "NSAM0001E";

    /** Failed to insert @ table. [@] */
    public static final String NSAM0012E = "NSAM0012E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Search results exceeded [@]. Only showing first @ records. */
    public static final String NSAM0024W = "NSAM0024W";

    /**  If "@" is selected, can not input "@". */
    public static final String NSAM0149E = "NSAM0149E";

    /** If "@" is selected, must input "@". */
    public static final String NSAM0150E = "NSAM0150E";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSAM0320E = "NSAM0320E";

    /** There are some missing schedule. Please set up schedules. */
    public static final String NSAM0336E = "NSAM0336E";

    /** There are some duplicated schedules. Please fix the schedules. */
    public static final String NSAM0337E = "NSAM0337E";

    // Add Start 09/06/2016 <QC#12429>
    /** The top schedule period is not within the contract price effectivity period. Please correct it. */
    public static final String NSAM0606E = "NSAM0606E";
    // Add End   09/06/2016 <QC#12429>

    /** Your updated schedule will be deleted after transition screen. If you want it, you should save the schedule. */
    public static final String NSAM0338W = "NSAM0338W";

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** XX_CHKBOX_A1 */
    public static final String XX_CHKBOX_A1 = "xxChkBox_A1";

    /** RADIO_VALUE_CLOSING_DAY */
    public static final BigDecimal RADIO_VALUE_CLOSING_DAY = new BigDecimal("1");

    /** RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH */
    public static final BigDecimal RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH = new BigDecimal("2");

    /** RADIO_VALUE_BLLG_DAY */
    public static final BigDecimal RADIO_VALUE_BLLG_DAY = new BigDecimal("1");

    /** RADIO_VALUE_BLLG_LAST_DAY_OF_MONTH */
    public static final BigDecimal RADIO_VALUE_BLLG_LAST_DAY_OF_MONTH = new BigDecimal("2");

    // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
    /** MIN_DAY */
    public static final String MIN_DAY = "0";
    // END 2016/05/18 T.Kanasaka [QC#2184, ADD]

    /** MAX_DAY */
    public static final String MAX_DAY = "99";

    /** YYYYMMDD */
    public static final String YYYYMMDD = "yyyyMMdd";

    // START 2015/10/21 T.Tomita [N/A, ADD]
    /**
     * Error Msg : An error occurred in API. <APIID: [@], Error Code:
     * [@], Data Key: [@]>
     */
    public static final String NSAM0003E = "NSAM0003E";
    // START 2015/10/21 T.Tomita [N/A, ADD]

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    // START 2016/08/05 K.Kishimoto [QC#12879, ADD]
    /** LAST_DAY */
    public static final String LAST_DAY = "Last Day";
    // END 2016/08/05 K.Kishimoto [QC#12879, ADD]
    // Add Start 08/09/2016 <QC#12310>
    /** Can not be calculated billing schedule because the contract period is too long. */
    public static final String NSZM1054E = "NSZM1054E";

    // START 2017/08/03 E.Kameishi [QC#18586,MOD]
    /** Over Per Sequence */
    public static final BigDecimal OVER_PER_SEQ = BigDecimal.valueOf(10000);
    // Add End   08/09/2016 <QC#12310>

    /** Maximum number of digits for date */
    public static final int MAX_NUM_DIGT_DATE = 8;
    // END 2017/08/03 E.Kameishi [QC#18586,MOD]

    // START 2016/11/17 T.Tomita [QC#15942, ADD]
    /** Business Id */
    public static final String BIZ_ID = "NSAL0330";
    // END 2016/11/17 T.Tomita [QC#15942, ADD]
}
