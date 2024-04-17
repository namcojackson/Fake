/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080.constant;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 * 2019/10/16   Hitachi         Y.Takeno        Update          QC#54123
 *</pre>
 */
public interface NFBL2080Constant {

    /** Boolean True */
    public static final boolean TRUE = true;
    /** Boolean False */
    public static final boolean FALSE = false;
    /**
     * Close
     */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";
    /**
     * Code Column for NMAL6800
     */
    public static final String MDSE_CD_TARGET_ALL = "AL";

    // START 2019/10/17 [QC#54123, ADD]
    /**
     * Code Column for NMAL6800
     */
    public static final String MDSE_CD_TARGET_10_DIGIT = "10";
    // END   2019/10/17 [QC#54123, ADD]


    /** TAB NAME */
    public enum TAB {
        /** Tab Static Constant */
        Header,
        /** Tab Static Constant */
        Detail
    }
    /** FLAG */
    public enum FLG {
        /** Yes */
        Y,
        /** No */
        N
    }
    /** Message */
    public enum MSG_ID {
        NLAM0023E,  /** Message ID :NLAM0023E */ 
        NPAM0225E,  /** Message ID :NPAM0225E */
        NFBM0023E,  /** Message ID :NFBM0023E */
        NFBM0216E,  /** Message ID :NFBM0216E You cannot [@] records except [@] */
        ZZM9000E,   /** Message ID :ZZM9000E */
        // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
        ZZSM4300E   /** Message ID :ZZSM4300E */
        // End   2016/12/22 H.Ikeda [QC#12865,ADD]
    }

    /**
     * Screen ID for 02
     * @author
     *
     */
    public static enum SCRN_EVENT {
        NFBL2080_INIT,
        NFBL2080Scrn00_Click_Search,
        NFBL2080Scrn00_Click_Reprocess,
        NFBL2080Scrn00_Click_Cancel,
        NFBL2080Scrn00_Check_All,
        NFBL2080Scrn00_Un_Check_All,
        NFBL2080Scrn00_Click_vndInvNum,
        NFBL2080Scrn00_CMN_Return,
        NFBL2080Scrn00_CMN_Submit,
        NFBL2080Scrn00_OpenWin_Mdse,
        NFBL2080Scrn00_NMAL6800,
        NFBL2080Scrn00_PageJump,
        NFBL2080Scrn00_PageNext,
        NFBL2080Scrn00_PagePrev,
        NFBL2080Scrn00_Header,
        NFBL2080Scrn00_Detail,
        NFBL2080Scrn00_CMN_Reset
    }

    // Frac Digit
    /** Frac Digit 2 */
    static final int FRAC_DIGIT_2 = 2;

    // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
    /** Business ID */
    public static final String BIZ_ID = "NFBL2080";

    /** Function ID NFBL2080T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFBL2080T020";
    // End   2016/12/22 H.Ikeda [QC#12865,ADD]

    // START 2018/10/12 J.Kim [QC#28723,ADD]
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    // END 2018/10/12 J.Kim [QC#28723,ADD]
}