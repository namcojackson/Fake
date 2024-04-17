/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1020.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2017/09/26   Hitachi         K.Kim           Update          QC#18744
 * 2018/07/19   Hitachi         K.Kim           Update          QC#27009
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26981
 * 2018/08/10   Hitachi         K.Kim           Update          QC#27671
 * 2018/10/23   Hitachi         K.Kim           Update          QC#28721
 * 2024/04/11   Hitachi         T.Kawasue       Update          QC#63717
 *</pre>
 */
public final class NSAL1020Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL1020";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Comma : ,
     */
    public static final String COMMA = ",";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * System Error : @
     */
    public static final String NSAM0219E = "NSAM0219E";

    /**
     * This machine is already on your current quote. Please click
     * Close button to add toners.
     */
    public static final String NSAM0427E = "NSAM0427E";

    /**
     * Machine is covered by supplies exclusive contract.
     */
    public static final String NSAM0428E = "NSAM0428E";

    /**
     * Service Business Unit in this machine is out of target.
     */
    public static final String NSAM0429E = "NSAM0429E";

    // START 2018/08/10 K.Kim [QC#27671, DEL]
    // /**
    //  * Toners cannot be shipped to the customer selected on your
    //  * current quote.
    //  */
    // public static final String NSAM0430E = "NSAM0430E";
    // END 2018/08/10 K.Kim [QC#27671, DEL]

    /**
     * Toners cannot be shipped to the customer selected on your
     * current quote. Before creating a quote for this machine, please
     * first complete your current quote.
     */
    public static final String NSAM0431E = "NSAM0431E";

    /**
     * No input parameter found
     */
    public static final String NSAM0219E_PARAM = "No input parameter found";

    // START 2017/09/26 K.Kim [QC#18744, ADD]
    /**
     * Machine is covered by sub contract of supplies inclusive.
     */
    public static final String NSAM0704E = "NSAM0704E";
    // END 2017/09/26 K.Kim [QC#18744, ADD]

    // START 2018/07/19 K.Kitachi [QC#26981, ADD]
    /**
     * This machine is part of Fleet Contract that is already on your current quote. Please click Back to Order button to add toners.
     */
    public static final String NSAM0733E = "NSAM0733E";
    // END 2018/07/19 K.Kitachi [QC#26981, ADD]

    // START 2024/04/11 T.Kawasue [QC#63717, ADD]
    /** You cannot order the supply item for this machine, since the meter is not entered within @ days. */
    public static final String NSAM0795E = "NSAM0795E";

    /** DEF_METER_ENTRY_PERIOD : 11 */
    public static final BigDecimal DEF_SPLY_ODR_MTR_ENTRY_INTVL_DAYS = new BigDecimal(11);
    // END 2024/04/11 T.Kawasue [QC#63717, ADD]
}
