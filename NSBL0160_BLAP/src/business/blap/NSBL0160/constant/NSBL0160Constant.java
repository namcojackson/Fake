/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0160.constant;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi         Create          N/A
 * 2013/11/18   Hitachi         T.Kanasaka         Update       QC2852
 * 2015/10/29   Hitachi         T.Tomita           Update          N/A
 * 2016/02/19   Hitachi         K.Kasai            Update       QC3689
 *</pre>
 */
public class NSBL0160Constant {

    /**
     * MSG (NZZM0000E)
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * MSG (NZZM0003E)
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * MSG (NSBM0016E)
     */
    public static final String NSBM0016E = "NSBM0016E";

    // START 2013/11/18 T.Kanasaka [QC2852,DEL]
    ///**
    // * DISP_TS_FORMAT (MM/dd/yyyy HH:mm:ss)
    // */
    //public static final String DISP_TS_FORMAT = "MM/dd/yyyy HH:mm:ss";
    // END 2013/11/18 T.Kanasaka [QC2852,DEL]
    // START 2013/11/18 T.Kanasaka [QC2852,ADD]
    /**
     * LENGTH_OF_YYYYMMDDHHMMSS (yyyyMMddHHmmss)
     */
    public static final int LENGTH_OF_YYYYMMDDHHMMSS = 14;
    // add start 2016/02/19 CSA Defect#3689
    /**
     * LENGTH_OF_DISP (yyyy/MM/dd HH:mm)
     */
    public static final int LENGTH_OF_DISP = 16;
    // add end 2016/02/19 CSA Defect#3689
    // END 2013/11/18 T.Kanasaka [QC2852,ADD]

    // START 2015/10/29 T.Tomita [N/A, ADD]
    /** Search Condition From Time */
    public static final String SRCH_COND_FROM_TIME = "000000000";

    /** Search Condition Thru Time */
    public static final String SRCH_COND_THRU_TIME = "235959999";

    /**
     * MSG(NSBM0057E) Cannot add anymore details.
     */
    public static final String NSBM0057E = "NSBM0057E";

    /**
     * MSG(NZZM0001W) There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";
    // END 2015/10/29 T.Tomita [N/A, ADD]
    // add start 2016/02/19 CSA Defect#3689
    /** Business ID */
    public static final String BUSINESS_ID = "NSBL0160";

    /** LIMIT_DOWNLOAD:65000 */
    public static final int LIMIT_DOWNLOAD = 65000;

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Ssm Param:EZUPAPLID */
    public static final String APLID = "NSBL0160BL06";

    /** Max Fetch Size */
    public static final String MANUAL1 = "Yes";

    /** Max Fetch Size */
    public static final String MANUAL2 = "No";

    /** SCRN_ID : NSBL0160Scrn00 */
    public static final String SCRN_ID = "NSBL0160Scrn00";
    // add end 2016/02/19 CSA Defect#3689
}
