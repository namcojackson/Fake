/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0630.constant;

/**
 *<pre>
 * Contract On Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tsuchida      Create          N/A
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2017/08/31   Hitachi         K.Kojima        Update          QC#20817
 *</pre>
 */
public class NSAL0630Constant {
    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0630";
    /** DS_CONTR */
    public static final String DS_CONTR = "Direct Sales Contract";

    /** SVC_MEMO */
    public static final String SVC_MEMO = "Service Memo";

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Please select at least 1 check box. */
    public static final String NSAM0015E = "NSAM0015E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Your request cannot be processed under this status. */
    public static final String NSAM0065E = "NSAM0065E";

    /** Relation between "@" and "@" is not correct. */
    public static final String NSAM0138E = "NSAM0138E";

    /** The process has been successfully completed. */
    public static final String NSAM0200I = "NSAM0200I";

    /** Input parameter "@" is a mandatory field. */
    public static final String NSAM0362E = "NSAM0362E";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** XX_CHK_BOX_A1 */
    public static final String XX_CHK_BOX_A = "xxChkBox_A";

    // START 2017/08/31 K.Kojima [QC#20817,ADD]
    /** Return Message : Success */
    public static final String RTRN_MSG_SUCCESS = "Success";
    // END 2017/08/31 K.Kojima [QC#20817,ADD]
}
