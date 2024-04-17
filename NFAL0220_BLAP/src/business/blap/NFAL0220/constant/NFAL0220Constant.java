/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0220.constant;

/**
 *<pre>
 * NFAL0220Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#12766
 * 2016/08/10   Hitachi         J.Kim           Update          QC#12851
 *</pre>
 */
public class NFAL0220Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFAL0220";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NFAL0220Scrn00";

    /** DR_CR_TP_CD(Debit) */
    public static final String DEBIT = "D";

    /** DR_CR_TP_CD(Credit) */
    public static final String CREDIT = "C";

    /**
     * CSV file name
     */
    public static final String CSV_FILE_NAME = "Manual Journal Entry";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results found. */
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * Another user has already updated target record. Please search again.
     */
    public static final String NFAM0004E = "NFAM0004E";

    /** Please check at least 1 checkbox. */
    public static final String NFAM0075E = "NFAM0075E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** @ field is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Invalid @. */
    public static final String NFAM0043E = "NFAM0043E";

    /** @ is required. */
    public static final String NFAM0052E = "NFAM0052E";

    /**
     * The Upload File is empty or only has a header line, therefore
     * it could not be processed. Please confirm the content of the
     * Upload file.
     */
    public static final String ZYEM0004E = "ZYEM0004E";

    /**
     * Selected data has been updated by another user. Pls. return to
     * the previous screen and try again.
     */
    public static final String NFAM0157E = "NFAM0157E";

    /** Please enter either debit amount or credit amount. */
    public static final String NFAM0166E = "NFAM0166E";

    /** There is missing segment(s). */
    public static final String NFAM0159E = "NFAM0159E";

    /** Debit and Credit amount is not balanced. */
    public static final String NFAM0160E = "NFAM0160E";

    /** Data could not be added due to [@]. Please try again. */
    public static final String NFAM0161E = "NFAM0161E";

    /**
     * The record cannot be registered. Table Name: [@], Field Name:
     * [@], Key Value: [@]
     */
    public static final String NFAM0162E = "NFAM0162E";

    /**
     * The record cannot be updated. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NFAM0163E = "NFAM0163E";

    /**
     * The record cannot be deleted. Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NFAM0164E = "NFAM0164E";

    /** Invalid Date! Please enter today or a future date. */
    public static final String NFAM0165E = "NFAM0165E";

    /** @ data has been deleted. Press Return to Exit. */
    public static final String ZZSM4116I = "ZZSM4116I";

    /** Please enter a Reversal Date. */
    public static final String NFAM0167E = "NFAM0167E";

    /** [@] does not match with [@]. Please enter again. */
    public static final String NFAM0168E = "NFAM0168E";

    /**
     * There is field(s) with which length of the field exceeds its limit.
     */
    public static final String NFAM0169E = "NFAM0169E";

    /** Other than numeric value is entered. */
    public static final String NFAM0170E = "NFAM0170E";

    /** Code @ was not found as @ */
    public static final String NFAM0024E = "NFAM0024E";

    /**
     * @ cannot be added because it is exceeding the maximum number of row [@].
     */
    public static final String NFAM0072E = "NFAM0072E";

    /** Please add detail information. */
    public static final String NFAM0176E = "NFAM0176E";

    /** The entered code is incorrect. Please try again. */
    public static final String NFAM0177E = "NFAM0177E";

    /** Please enter a Auto Reversal. */
    public static final String NFAM0178E = "NFAM0178E";

    /** Enter a number other than 0 in [@]. */
    public static final String NFAM0179E = "NFAM0179E";

    // START 2016/08/29 J.Kim [QC#12851,ADD]
    /** Data insert failure.[@] */
    public static final String NFAM0180E = "NFAM0180E";

    /** Data update failure.[@]. */
    public static final String NFAM0181E = "NFAM0181E";
    // END 2016/08/29 J.Kim [QC#12851,ADD]

    /** Mode Type(New) */
    public static final String NEW = "01";

    /** Mode Type(Edit) */
    public static final String EDIT = "02";

    /** Dot(.) */
    public static final String DOT = ".";

    /** Dot(\\.) */
    public static final String STR_DOT = "\\.";

    /** Date(01) */
    public static final String STR_DATE = "01";

    // START 2016/08/02 K.Kojima [QC#12766,ADD]
    /** Array Number : COA_CMPY_CD */
    public static final int ARRAY_NUM_CMPY_CD = 0;

    /** Array Number : COA_BR_CD */
    public static final int ARRAY_NUM_BR_CD = 1;

    /** Array Number : COA_CC_CD */
    public static final int ARRAY_NUM_CC_CD = 2;

    /** Array Number : COA_ACCT_CD */
    public static final int ARRAY_NUM_ACCT_CD = 3;

    /** Array Number : COA_PROD_CD */
    public static final int ARRAY_NUM_PROD_CD = 4;

    /** Array Number : COA_CH_CD */
    public static final int ARRAY_NUM_CH_CD = 5;

    /** Array Number : COA_AFFL_CD */
    public static final int ARRAY_NUM_AFFL_CD = 6;

    /** Array Number : COA_PROJ_CD */
    public static final int ARRAY_NUM_PROJ_CD = 7;

    /** Array Number : COA_EXTN_CD */
    public static final int ARRAY_NUM_EXTN_CD = 8;
    // END 2016/08/02 K.Kojima [QC#12766,ADD]

    // START 2016/08/10 J.Kim [QC#12851,ADD]
    /** GL Common */
    public static int[] COA_LENGTH = {
        3,3,6,8,2,3,3,2,3
    };
    // END 2016/08/10 J.Kim [QC#12851,ADD]

    // START 2016/08/29 J.Kim [QC#12851,ADD]
    /** Insert Max Count */
    public static int MAX_UPDATE_COUNT = 1000;
    // END 2016/08/29 J.Kim [QC#12851,ADD]
}
