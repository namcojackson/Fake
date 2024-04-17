/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0240.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/16   Hitachi         Y.Takeno        Update          QC#7746
 * 2016/08/09   Hitachi         T.Mizuki        Update          QC#12243
 *</pre>
 */
public class NSBL0240Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0240";

    /** SCRN_ID : NSBL0240Scrn00 */
    public static final String SCRN_ID = "NSBL0240Scrn00";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

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

    /** The number of items Unjust error. */
    public static final String NSAM0208E = "NSAM0208E";

    /** Number of digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /** Item classification error (item name [@]). */
    public static final String NSAM0210E = "NSAM0210E";

    /**
     * The number for this process exceeds the maximum numbers for
     * display and can not process.
     */
    public static final String NSAM0214E = "NSAM0214E";

    /**
     * @ cannot be added because it is exceeding the maximum number of
     * row [@].
     */
    public static final String NSAM0320E = "NSAM0320E";

    /** Model Group [@], Line of Business [@] is duplicated. */
    public static final String NSBM0149E = "NSBM0149E";

    /**
     * Setting at Zone[@] of Model Group [@], Line of Business[@] is
     * not completed.
     */
    public static final String NSBM0150E = "NSBM0150E";

    /** [@] doesn't exist in the table [@]. */
    public static final String NSBM0151E = "NSBM0151E";

    /** Selected item is invalid Intangible Item. */
    public static final String NSBM0152E = "NSBM0152E";
    // mod start 2016/08/09 CSA QC#12243
    /** If Rate is entered, the UOM is mandatory. Please enter it. */
    public static final String NSBM0177E = "NSBM0177E";

    /** If UOM is entered, the Rate is mandatory. Please enter it. */
    public static final String NSBM0178E = "NSBM0178E";
    // mod end 2016/08/09 CSA QC#12243
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** status code : 1000 */
    public static final int CSV_READ_STATUS_CODE_1000 = 1000;

    /** status code : 2000 */
    public static final int CSV_READ_STATUS_CODE_2000 = 2000;

    /** item name : XX_CHK_BOX_A */
    public static final String XX_CHK_BOX = "xxChkBox";

    /** SVC_ZN_CD */
    public static final String SVC_MBL_INTFC_KEY_TXT = "SVC_ZN_CD";

    /** Table name : SVC_TRVL_CHRG */
    public static final String TBL_NM_SVC_TRVL_CHRG = "Service Travel Charge";

    /** initial value : SVC_MIN_CHRG_HRS_AOT */
    public static final BigDecimal SVC_MIN_CHRG_HRS_AOT = BigDecimal.ONE;

    // START 2016/05/16 [QC#7746, DEL]
    // /** initial value : SVC_CHRG_CCY_CD */
    // public static final String SVC_CHRG_CCY_CD = "USD";
    // END 2016/05/16 [QC#7746, DEL]

    /** fieldName : SVC_TRVL_UNIT_AMT */
    public static final String FLD_NM_SVC_TRVL_UNIT_AMT = "svcTrvlUnitAmt_";

    /** fieldName : SVC_TRVL_CHRG_TP_CD */
    public static final String FLD_NM_SVC_TRVL_CHRG_TP_CD = "svcTrvlChrgTpCd_";

    /** fieldName : SVC_TRVL_CHRG_PK */
    public static final String FLD_NM_SVC_TRVL_CHRG_PK = "svcTrvlChrgPk_";

    /** fieldName : FLD_NM_EZUPTIME */
    public static final String FLD_NM_EZUPTIME = "ezUpTime_";

    /** fieldName : FLD_NM_EZUPTIMEZONE */
    public static final String FLD_NM_EZUPTIMEZONE = "ezUpTimeZone_";

    /** delimiter character */
    public static final String DELIM = "|";
}
