/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0750.constant;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/06   Hitachi         K.Kojima        Update          QC#11025
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 *</pre>
 */
public final class NFCL0750Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFCL0750";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
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
     * System error has occurred. Please contact IT Support.
     */
    public static final String NFCM0041E = "NFCM0041E";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Please execute again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * The value in the 'To' field has to be equal to or greater than
     * the 'From' field.
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
     * DATE_FORMAT_YYYYMMDD : yyyyMMdd
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * WRT_OFF_OPT_TP_IS_GEN_ONLY : 1
     */
    public static final String WRT_OFF_OPT_TP_IS_GEN_ONLY = "1";

    /**
     * WRT_OFF_OPT_TP_IS_CRAT_ADJ : 2
     */
    public static final String WRT_OFF_OPT_TP_IS_CRAT_ADJ = "2";

    // START 2016/07/06 K.Kojima [QC#11025,ADD]
    /**
     * WRT_OFF_OPT_TP_IS_GEN_ONLY Name
     */
    public static final String WRT_OFF_OPT_TP_IS_GEN_ONLY_NM = "Generate Report Only";

    /**
     * WRT_OFF_OPT_TP_IS_CRAT_ADJ Name
     */
    public static final String WRT_OFF_OPT_TP_IS_CRAT_ADJ_NM = "Create Adjustment";

    // END 2016/07/06 K.Kojima [QC#11025,ADD]

    /**
     * CSV file name
     */
    public static final String CSV_FILE_NAME = "Auto Write-Off Inquiry";

    /** TBL_NM */
    public static enum TBL_NM {
        /** AR_ADJ_RSN */
        AR_ADJ_RSN
        /** AR_WRT_OFF_RQST_TP */
        , AR_WRT_OFF_RQST_TP
        // START 2018/02/28 J.Kim [QC#21143,DEL]
        ///** PROC_STS */
        //, PROC_STS;
        // END 2018/02/28 J.Kim [QC#21143,DEL]
    }

    /**
     * Divide String:"-"
     */
    public static final String DIV_STR = "-";

    /**
     * WRT_OFF_RQST_GRP_CD_IS_NONE : NONE
     */
    public static final String WRT_OFF_RQST_GRP_CD_IS_NONE = "NONE";

    // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
    /**
     * AR_SYS_BAT_USR_ID : AR_SYS_BAT_USR_ID
     */
    public static final String AR_SYS_BAT_USR_ID = "AR_SYS_BAT_USR_ID";

    /**
     * ADJ_DT : Adjustment Date
     */
    public static final String ADJ_DT = "Adjustment Date";
    // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
}
