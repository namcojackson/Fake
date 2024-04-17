/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0090.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2016/12/02   Fujitsu         H.Ikeda         Update          QC#15823
 * 2018/09/14   Hitachi         Y.Takeno        Update          QC#24884
 * 2019/02/05   Hitachi         H.Umeda         Update          QC#30162
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFDL0090Constant {

    /** Business Application ID */
    public static final String BIZ_ID = "NFDL0090";

    /** Screen ID */
    public static final String SCREEN_ID = "NFDL0090Scrn00";

    /** WRT_OFF_OPT_TP_CD '1': Generate Report Only */
    public static final String GNRT_RPT_ONLY = "1";

    /** WRT_OFF_OPT_TP_CD '2': Create Adjustment */
    public static final String CRAT_ADJ = "2";

    /** max download count */
    public static final int MAX_DOWNLOAD_CNT = 65000;

    /** VAR_CAR_CONST Key : Write Off Workflow ID */
    public static final String CONST_KEY_VLD_APVL_WF_ID = "WRITE_OFF_WF_ID";

    // START 2018/09/14 [QC#24884, ADD]
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // END   2018/09/14 [QC#24884, ADD]

    //--------------------------------
    // Message ID
    //--------------------------------
    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** Not found record. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** System error has occurred.  Please contact IT Support. */
    public static final String NFCM0041E = "NFCM0041E";

    /** Please select @. */
    public static final String NFDM0002E = "NFDM0002E";

    /** System error has occurred. */
    public static final String NFDM0008E = "NFDM0008E";

    /** Your request has been successfully submitted. Request# is @. */
    public static final String NFDM0014I = "NFDM0014I";

    // START 2016/12/05 H.Ikeda [QC#15823,ADD]
    /** Failed to insert [@]. */
    public static final String NFDM0013E = "NFDM0013E";
    // END   2016/12/05 H.Ikeda [QC#15823,ADD]

    // START 2019/02/05 H.Umeda [QC#30162,ADD]
    /** Please check at least 1 checkbox. */
    public static final String NFDM0024E = "NFDM0024E";
    // END   2019/02/05 H.Umeda [QC#30162,ADD]
    // START 2022/11/24 S.Naya [QC#57252,ADD]
    /** @ is invalid. */
    public static final String NFCM0833E = "NFCM0833E";

    /** Other Code */
    public static final String OTHER_CODE = "999";
    // END   2022/11/24 S.Naya [QC#57252,ADD]
}
