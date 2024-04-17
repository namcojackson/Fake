/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0840.constant;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11853,12077
 * 2016/08/23   Hitachi         T.Mizuki        Update          QC#11855
 *</pre>
 */
public class NSAL0840Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0840";

    /** SCRN_ID : NSAL0840Scrn00 */
    public static final String SCRN_ID = "NSAL0840Scrn00";

    /** LIMIT_DOWNLOAD:65000 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** LENGTH:30 */
    public static final int LENGTH_30 = 30;

    /** mode (After Open For Correction) */
    public static final String MODE_AFTER_OPEN = "02";

    /** message id length */
    public static final int MESSAGE_ID_LENGTH = 9;

    /** status code : 1000 */
    public static final int CSV_READ_STATUS_CODE_1000 = 1000;

    /** status code : 2000 */
    public static final int CSV_READ_STATUS_CODE_2000 = 2000;

    /** table name : DS_CONTR_INTFC*/
    public static final String DS_CONTR_INTFC = "DS_CONTR_INTFC";

    /** table name : DS_ADDL_CHRG_INTFC*/
    public static final String DS_ADDL_CHRG_INTFC = "DS_ADDL_CHRG_INTFC";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** The number of items Unjust error. */
    public static final String NSAM0208E = "NSAM0208E";

    /** Number of digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /** Item classification error (item name [@]). */
    public static final String NSAM0210E = "NSAM0210E";

    /** The number for this process exceeds the maximum numbers for display and can not process. */
    public static final String NSAM0214E = "NSAM0214E";

    /**
     * The Upload File is empty or only has a header line, therefore it could not be processed. 
     * Please confirm the content of the Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    // ADD start 2016/04/22 CSA Defect#6691
    /** Input parameter "@" is a mandatory field. */
    public static final String NSAM0362E = "NSAM0362E";

    /** Interface Bat# */
    public static final String DS_CONTR_INTFC_BAT_NUM = "Interface Bat#";

    /** Source Ref# */
    public static final String DS_CONTR_SRC_REF_NUM = "Source Ref#";

    /** Source Type */
    public static final String CONTR_INTFC_SRC_TP_CD = "Source Type";

    /** Charge Level */
    public static final String CHRG_LVL_TP_CD = "Charge Level";

    /** Charge Type */
    public static final String ADDL_CHRG_TP_CD = "Charge Type";
    // ADD end 2016/04/22 CSA Defect#6691

    // START 2016/07/22 M.Gotou [QC#11853,12077, MOD]
    /** Interface Bat#, Source Ref#, Source Type, Charge Level and Charge Type must be registered on Interface Maintenance Screen. */
    public static final String NSAM0555E = "NSAM0555E";

    /** IB ID and Serial# do not match. */
    public static final String NSAM0556E = "NSAM0556E";

    /** IB ID and Mdse code do not match. */
    public static final String NSAM0557E = "NSAM0557E";

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    // mod start 2016/08/23 CSA QC#11855
    /** End Date should be >= Start Date of the contract. */
    public static final String NSAM0327E = "NSAM0327E";
    // mod end 2016/08/23 CSA QC#11855

    /** Source Type */
    public static final String SVC_MACH_MSTR_PK = "IB ID";

    /** Source Type */
    public static final String SER_NUM = "Serial#";
    // END 2016/07/22 M.Gotou [QC#11853,12077, MOD]
}
