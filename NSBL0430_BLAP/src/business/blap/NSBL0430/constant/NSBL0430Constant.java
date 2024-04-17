/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0430.constant;

/**
 *<pre>
 * Mods Serial# Assignment
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/04/18   Hitachi         M.Gotou         Update          QC3425
 * 2017/02/15   Hitachi         N.Arai          Update          QC#17562
 * 2018/05/09   CITS            T.Wada          Update          QC#25666
 *</pre>
 */
public final class NSBL0430Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0430";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * SVC_MOD_SER_RNG
     */
    public static final String SVC_MOD_SER_RNG = "SVC_MOD_SER_RNG";

    /**
     * SVC_MOD_SER_RNG_PK
     */
    public static final String SVC_MOD_SER_RNG_PK = "SVC_MOD_SER_RNG_PK";

    /**
     * Mdse Code
     */
    public static final String MDSE_CODE = "Mdse Code";

    /**
     * Mods Detail Info
     */
    public static final String MOD_SETAIL = "Mods Detail Info";

    /**
     * Service Call
     */
    public static final String SVC_CALL = "Service Call";

    /**
     * Serial #
     */
    public static final String SERIAL_NUM = "Serial #";

    /**
     * Max Count
     */
    public static final String MAX_COUNT = "1000";

    /**
     * Mdsc Code Lenght:8
     */
    public static final int MDSE_CODE_LEN_8 = 8;

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * An input parameter, [@],  has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /**
     * The process completed successfully .
     */
    public static final String NSBM0005I = "NSBM0005I";

    /**
     * @ does not exist in @.
     */
    public static final String NSAM0063E = "NSAM0063E";

    /**
     * Please select item(s).
     */
    public static final String NSBM0042E = "NSBM0042E";

    /**
     * @ cannot be added because it is exceeding the maximum number of row [@]
     */
    public static final String NSBM0058E = "NSBM0058E";

    /**
     * The data @ does not exist in the master.
     */
    public static final String NSBM0011E = "NSBM0011E";

    /**  @ is duplicated. */
    public static final String NSBM0118E = "NSBM0118E";

    /**
     * The Serial # is incorrect.  It is set out of the range of the service modification. 
     */
    public static final String NSZM0623E = "NSZM0623E";

    /**
     * This data has been updated by another user.Table Name: [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NSBM0075E = "NSBM0075E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /** The number of items Unjust error. */
    public static final String NSAM0208E = "NSAM0208E";

    /** Number of  digits over error (item name [@]). */
    public static final String NSAM0209E = "NSAM0209E";

    /** Item classification error (item name [@]). */
    public static final String NSAM0210E = "NSAM0210E";

    /** "@" is not entered. */
    public static final String NSAM0007E = "NSAM0007E";

    /** The value for @ must be greater than or equal to @. */
    public static final String NSZM0860E = "NSZM0860E";

    // QC#25666 Add Start
    /** The value for [@] must be less than or equal to [@]. */
    public static final String NSZM1330E = "NSZM1330E";
    // QC#25666 Add End

    /** 
     * The number of digits [Serial# Range From] should be match
     *  each other in [Serial# Range To].
     */
    public static final String NSBM0137E = "NSBM0137E";

    /**
     * The Serial # is incorrect. 
     * It is set out of the range in the Master's value.
     */
    public static final String NSBM0138E = "NSBM0138E";

    /** Because Service Call exists, cannot be changed. */
    public static final String NSBM0139E = "NSBM0139E";

    // add start 2016/04/18 CSA Defect#3425
    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";
    // add start 2016/04/18 CSA Defect#3425

    /** status code : 1000 */
    public static final int CSV_READ_STATUS_CODE_1000 = 1000;

    /** status code : 2000 */
    public static final int CSV_READ_STATUS_CODE_2000 = 2000;

    /** Upload column:1 */
    public static final String MDSE_UPD = "Mdse Code";

    /** Upload column:2 */
    public static final String SER_FROM_UPD = "Serial# Range From";

    /** Upload column:3 */
    public static final String SER_TO_UPD = "Serial# Range To";

    /** Upload column:4 */
    public static final String AUTO_CRE_CALLS_UPD = "Auto Create Calls";

    /** Upload column:5 */
    public static final String AUTO_CRE_REF_RATE_UPD = "Auto Create Refresh Rate";

    /** Column number:1 */
    public static final int COL_NUM_1 = 1;

    /** Column number:2 */
    public static final int COL_NUM_2 = 2;

    /** Column number:3 */
    public static final int COL_NUM_3 = 3;

    /** Column number:4 */
    public static final int COL_NUM_4 = 4;

    /** Column number:5 */
    public static final int COL_NUM_5 = 5;

 // START 2017/02/15 N.Arai [QC#17562, MOD]
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
 // END 2017/02/15 N.Arai [QC#17562, MOD]
}
