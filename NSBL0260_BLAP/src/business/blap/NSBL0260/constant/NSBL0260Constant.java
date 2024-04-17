/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0260.constant;

/**
 *<pre>
 * EOL Exception Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/07/03   Hitachi         K.Kim           Update          QC#18164
 *</pre>
 */
public class NSBL0260Constant {

    /** SEARCH_CRITERIA_CD_MDL_COMB */
    public static final String SEARCH_CRITERIA_CD_MDL_COMB = "MC";

    /** SEARCH_CRITERIA_CD_SER_COMB */
    public static final String SEARCH_CRITERIA_CD_SER_COMB = "SC";

    /** SEARCH_CRITERIA_CD_MDL_AND_SER_COMB */
    public static final String SEARCH_CRITERIA_CD_MDL_AND_SER_COMB = "MSC";

    /** SEARCH_CRITERIA_CD_MDL_HEADER */
    public static final String SEARCH_CRITERIA_CD_MDL_HEADER = "MH";

    /** SEARCH_CRITERIA_CD_CUST_HEADER */
    public static final String SEARCH_CRITERIA_CD_CUST_HEADER = "CH";

    /** SEARCH_CRITERIA_CD_ALL */
    public static final String SEARCH_CRITERIA_CD_ALL =  "ALL";

    /** MAX_THRU_DATE:99991231 */
    public static final String MAX_THRU_DATE = "99991231";

    /** @ cannot be added because it is exceeding the maximum number of row [@] */
    public static final String NSBM0058E = "NSBM0058E";

    /** Please check at least 1 checkbox. */
    public static final String NSBM0007E = "NSBM0007E";

    /** Serial Number [@] is duplicated. */
    public static final String NSBM0118E = "NSBM0118E";

    /** [@] doesn't exist in the table [@]. */
    public static final String NSBM0154E = "NSBM0154E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Failed to update "@". */
    public static final String NSBM0163E = "NSBM0163E";

    /** Failed to insert "@". */
    public static final String NSBM0164E = "NSBM0164E";

    /** Failed to delete "@". */
    public static final String NSBM0165E = "NSBM0165E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    // START 2017/07/03 K.Kim [QC#18164, ADD]
    /** Invalid combination.  @ & @ */
    public static final String NSAM0127E = "NSAM0127E";
    // END 2017/07/03 K.Kim [QC#18164, ADD]

    /** Table Name : DS Model End of Life Exception */
    public static final String TBL_DS_MDL_EOL_EX = "DS Model End of Life Exception";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** DOWNLOAD_FILE_NAME */
    public static final String DOWNLOAD_FILE_NAME = "EndOfLifeException";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process completed successfully. */
    public static final String NZZM0002I = "NZZM0002I";

    /** LIMIT_DOWNLOAD */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** Table name: Model Name */
    public static final String TABLE_NAME_MDL_NM = "Model Name";

    /** Table name: Service Machine Master */
    public static final String TABLE_NAME_SVC_MACH_MSTR = "Service Machine Master";

    /** Column Name : Model Name */
    public static final String COLUMN_NAME_MDL_NM = "Model Name";

    /** Column Name : Serial Number */
    public static final String COLUMN_NAME_SER_NUM = "Serial Number";

    /** select popup : Serial Number(Line)  */
    public static final String SELECT_POPUP_SER_LINE = "SL";
}
