/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0020.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi         T.Yonekura      Create          N/A
 * 2013/11/21   Hitachi         T.Aoyagi        Update          QC2852
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          NA(No Mark up comment)
 * 2019/11/19   Hitachi         H.Umeda         Update          QC#54593
 *</pre>
 */
public class NSAL0020Constant {

    /** SCRN_ID : NSAL0020Scrn00 */
    public static final String SCRN_ID = "NSAL0020Scrn00";

    /** APL_ID : NSAL0020 */
    public static final String APL_ID = "NSAL0020";

    /** PARAM glblCmpyCd */
    public static final String PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** PARAM userId */
    public static final String PARAM_SRCH_OPT_USR_ID = "srchOptUsrId";

    /** PARAM srchOptAplId */
    public static final String PARAM_SRCH_OPT_APL_ID = "srchOptAplId";

    /** MAX_DATE */
    public static final String MAX_DATE = "99991231";

    // Old NSAB0020 Start----------------------------------------
    /**
     * Business Application ID
     */
    public static final String BUSINESS_ID = "NSAL0020";

    /**
     * Screen ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The corresponding data does not exist.
     * <TABLE: [@], Data Key: [@]>
     */
    public static final String NSAM0004E = "NSAM0004E";

    /**
     * PULLDOWN_LIMIT_COUNT
     */
    public static final int PULLDOWN_LIMIT_COUNT = 99;

    /**
     * SEARCH_LIMIT_COUNT
     */
    public static final int SEARCH_LIMIT_COUNT = 200;

    /**
     * DOWNLOAD_LIMIT_COUNT
     */
// START 2019/11/19 H.Umeda [QC#54593,MOD]
//  public static final int DOWNLOAD_LIMIT_COUNT = 2000;
    public static final int DOWNLOAD_LIMIT_COUNT = 65000;
// END   2019/11/19 H.Umeda [QC#54593,MOD]


    /** MSG_ID */
    public enum MSG_ID {

        /** [@] field is mandatory. */
        ZZM9000E
        /** The process [@] has been successfully completed. */
        , ZZZM9003I
        /** Specified "@" already exists. */
        , NSAM0059E
        /** No search results found. */
        , NSAM0194I
        /** [@] is not selected. */
        , NSAM0199E
        /** System Error : @ */
        , NSAM0219E
        /** No search results found. */
        , NZZM0000E
        /**
         * There are too many search results, there is data that
         * cannot be displayed.
         */
        , NZZM0001W
        //
        , /* */;

    }
    /** MSG_KIND */
    public enum MSG_KIND {
        /** error */
        ERROR("E")
        /** warning */
        , WARNING("W")
        /** information */
        , INFO("I")
        //
        , /* */;

        /** */
        private String msgKind;

        MSG_KIND(String msgKind) {
            this.msgKind = msgKind;
        }

        /**
         * @return getMsgKind
         */
        public String getMsgKind() {
            return msgKind;
        }
    }
    // START 2013/11/21 T.Aoyagi [QC2852,DEL]
//    /** date pattern */
//    public static final String YYYY_MM_DD = "yyyyMMdd";
//
//    /** date pattern */
//    public static final String FMT_YYYY_MM_DD = "MM/dd/yyyy";
    // END 2013/11/21 T.Aoyagi [QC2852,DEL]
}
