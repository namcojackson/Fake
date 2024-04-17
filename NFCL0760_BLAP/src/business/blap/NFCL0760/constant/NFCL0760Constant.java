/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0760.constant;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Fujitsu         S.Yoshida       Update          QC#5970
 *</pre>
 */
public final class NFCL0760Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFCL0760";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Entry Parameter Error.
     */
    public static final String NFCM0522E = "NFCM0522E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Please execute again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * The value in the 'To' field has to be equal to or greater than the 'From' field.
     */
    public static final String NFCM0023E = "NFCM0023E";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Please select item(s).
     */
    public static final String NFCM0094E = "NFCM0094E";

    /**
     * The entered 'Customer Code' does not exist.
     */
    public static final String NFCM0763E = "NFCM0763E";

    /**
     * WRT_OFF_RQST_GRP_CD_IS_NONE : NONE
     */
    public static final String WRT_OFF_RQST_GRP_CD_IS_NONE = "NONE";

    /**
     * WRT_OFF_OPT_TP_IS_GEN_ONLY : 1
     */
    public static final String WRT_OFF_OPT_TP_IS_GEN_ONLY = "1";

    /**
     * CSV file name
     */
    public static final String CSV_FILE_NAME = "Auto Write-Off Inquiry";

}
