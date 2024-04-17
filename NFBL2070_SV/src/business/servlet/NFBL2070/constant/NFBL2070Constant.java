/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2070.constant;

/** 
 *<pre>
 * Compensation Credit I/F Error Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   CUSA            Y.Miyauchi      Create          New
 * 2016/12/21   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public interface NFBL2070Constant {

    // START 2016/11/28 [QC#16158]
//    /** FLAG */
//    public enum FLG {
//        /** Yes */
//        Y,
//        /** No */
//        N
//    }
    // END 2016/11/28 [QC#16158]

    // Frac Digit
    /** Frac Digit 2 */
    static final int FRAC_DIGIT_2 = 2;
    
    // START 2016/11/28 [QC#16158]
    // [@] should be smaller than [@].
    public static final String ZZZM9010E = "ZZZM9010E";
    
    /** ScreenID */
    public static final String SCREEN_ID = "NFBL2070Scrn00";
    // END 2016/11/28 [QC#16158]

    // Start 2016/12/21 H.Ikeda [QC#12865,ADD]
    /** Business ID */
    static final String BIZ_ID = "NFBL2070";

    /** Function ID NFBL2070T020 (Update) */
    static final String FUNC_ID_UPDATE = "NFBL2070T020";

    // User @ has no permissions to operate this application.
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/21 H.Ikeda [QC#12865,ADD]
}
