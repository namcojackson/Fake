/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0950.constant;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/07/07   Hitachi         O.Okuma         Update          QC#10991
 *</pre>
 */
public class NSAL0950Constant {

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** The process completed successfully. */
    public static final String NZZM0002I = "NZZM0002I";

    /** @ cannot be added because it is exceeding the maximum number of row [@]. */
    public static final String NSAM0320E = "NSAM0320E";

    /** Please select for Radio button. */
    public static final String NSAM0454E = "NSAM0454E";

    /** The selected record can not be deleted.please enter End Date. */
    public static final String NSAM0455E = "NSAM0455E";

    /** There is no data to be processed. */
    public static final String NSAM0456E = "NSAM0456E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Failed to update "@".. */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    // START 2016/07/07 [QC#10991, ADD]
    /** [@] is duplicated. */
    public static final String NSAM0035E = "NSAM0035E";

    /** Field Name: Validation Name */
    public static final String FIELD_NM_DS_CONTR_VLD_NM = "Validation Name";
    // END 2016/07/07 [QC#10991, ADD]

    /** TABLE NAME:DS_CONTR_VLD */
    public static final String TABLE_NAME_DS_CONTR_VLD = "Ds Contract Validation";

    /** Business ID */
    public static final String BUSINESS_ID = "NSAL0950";

    /** LIMIT_DOWNLOAD */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** MAX_THRU_DATE */
    public static final String MAX_THRU_DATE = "29991231";

    /** DOWNLOAD_FILE_NAME */
    public static final String DOWNLOAD_FILE_NAME = "ContractValidation";
}
