/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0670.constant;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2015/12/15   Hitachi         K.Kasai         Update          QC1659
 * 2016/12/05   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 *</pre>
 */
public final class NSAL0670Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0660";

    /**
     * DATE_FORMAT
     */
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";

    /** Error Message */
    public static final String NSAM0013E = "NSAM0013E";

    /** Error Message */
    public static final String NSAM0015E = "NSAM0015E";

    /** Error Message */
    public static final String NSAM0392E = "NSAM0392E";

    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";

    /** The process has been successfully completed.  */
    public static final String NSAM0200I = "NSAM0200I";

    /** Return Message : Success */
    public static final String RTRN_MSG_SUCCESS = "Successs";

    /** Return Message : Failed to cancel Contract */
    public static final String RTRN_MSG_FAILED = "Failed to cancel Contract";

    // ADD START 2015/12/15 K.Kasai [QC1659]
    /** Return Message : This data has been updated by another user. */
    public static final String RETURN_MSG_EXCL = "This data has been updated by another user.";

    /** Return Message : Details that can be cancelled do not exist. */
    public static final String RETURN_MSG_NO_DTL = "Details that can be cancelled do not exist.";

    // ADD END 2015/12/15 K.Kasai [QC1659]
}
