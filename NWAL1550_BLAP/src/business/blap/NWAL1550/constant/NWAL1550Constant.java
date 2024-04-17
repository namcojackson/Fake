/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1550.constant;

/**
 *<pre>
 * NWAL1550Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2022/10/07   CITS            F.Fadriquela    Update          QC#60623
 *</pre>
 */
public class NWAL1550Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1550";

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

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** Order Number has not been entered. */
    public static final String NWAM0014E = "NWAM0014E";

    /**
     * No Order information linked to the entered 'Order Number'
     * exists.
     */
    public static final String NWAM0142E = "NWAM0142E";

    /** No search result was found. */
    public static final String NWAM0006I = "NWAM0006I";

    /** An error has occurred in the called API [@]. */
    public static final String NWAM0189E = "NWAM0189E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Select at least one of [@]. */
    public static final String NWAM0634E = "NWAM0634E";

    /** Please correct the error fields, and then try again. */
    public static final String NWAM0010E = "NWAM0010E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Only latest version of data could be updated. */
    public static final String NWZM1840E = "NWZM1840E";

    /**
     * It can not be implemented because the [@] has exceeded the
     * limit on the number [@].
     */
    public static final String NWAM0715E = "NWAM0715E";

    /** Order information exists. */
    public static final String ORD_INFO_FLG_EXIST = "Y";

    /** DI Check Hold Flag Off. */
    public static final String DI_CHK_HLD_FLG_OFF = "N";

    /** Open Flag Off. */
    public static final String OPEN_FLG_OFF = "N";

    /** Hold Reason DI. */
    public static final String HLD_RSN_DI = "S21";

    /** Item Version Number Name. */
    public static final String ITEM_VRSN_NUM_NAME = "Version Number";

    // START 2022/10/07 F.Fadriquela [QC#60623, ADD]
    /** Being updated by another user, therefore update could not be processed. */
    public static final String NWAM0242E = "NWAM0242E";
    // END 2022/10/07 F.Fadriquela [QC#60623, ADD]
}
