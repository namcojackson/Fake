/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0830.constant;

import java.math.BigDecimal;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/19   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11853,12077
 *</pre>
 */
public class NSAL0830Constant {

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process completed successfully . */
    public static final String NZZM0002I = "NZZM0002I";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSAM0320E = "NSAM0320E";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

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

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    // START 2016/07/22 M.Gotou [QC#11853,12077, MOD]
    /** Interface Bat#, Source Ref#, Source Type and Billing Counter Code must be registered on Interface Maintenance Screen. */
    public static final String NSAM0554E = "NSAM0554E";

    /** IB ID and Serial# do not match. */
    public static final String NSAM0556E = "NSAM0556E";

    /** IB ID and Mdse code do not match. */
    public static final String NSAM0557E = "NSAM0557E";

    /** [@] does not exist in Master. */
    public static final String NSAM0011E = "NSAM0011E";

    /** Source Type */
    public static final String SVC_MACH_MSTR_PK = "IB ID";

    /** Source Type */
    public static final String SER_NUM = "Serial#";
    // END 2016/07/22 M.Gotou [QC#11853,12077, MOD]

    // ADD start 2016/04/19 CSA Defect#6691
    /** You cannot delete first pricing record in each Billing Counter. */
    public static final String NSAM0452E = "NSAM0452E";

    /** Input parameter "@" is a mandatory field. */
    public static final String NSAM0362E = "NSAM0362E";

    /** Contract Interface of specified Interface Batch Number does not exist. */
    public static final String NSAM0458E = "NSAM0458E";

    /** Interface Bat# */
    public static final String DS_CONTR_INTFC_BAT_NUM = "Interface Bat#";

    /** Source Ref# */
    public static final String DS_CONTR_SRC_REF_NUM = "Source Ref#";

    /** Source Type */
    public static final String CONTR_INTFC_SRC_TP_CD = "Source Type";

    /** Billing Counter Code */
    public static final String BLLG_MTR_LB_CD = "Billing Counter Code";
    // ADD end 2016/04/19 CSA Defect#6691

    /** message parameter : row */
    public static final String MSG_PARAM_ROW = "row";

    /** DS_CONTR_INTFC */
    public static final String DS_CONTR_INTFC = "DS_CONTR_INTFC";

    /** DS_ACTL_CNT_INTFC */
    public static final String DS_XS_COPY_INTFC = "DS_XS_COPY_INTFC";

    /** XX_TP_CD : H */
    public static final String XX_TP_CD_H = "H";

    /** XX_TP_CD : D */
    public static final String XX_TP_CD_D = "D";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0830";

    /** SCRN_ID : NSAL0830Scrn00 */
    public static final String SCRN_ID = "NSAL0830Scrn00";

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

    /** CONTR_INTFC_PK : not found */
    public static final BigDecimal CONTR_INTFC_PK_VALUE_NOT_FOUND = BigDecimal.valueOf(-1);
}
