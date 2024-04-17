/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0500.constant;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/13   Hitachi         T.Tsuchida      Update          QC#2820
 * 2016/02/10   Hitachi         K.Kasai         Update          QC#3707
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2016/03/28   Hitachi         K.Kasai         Update          QC#4576
 * 2017/03/01   Hitachi         T.Mizuki        Update          QC#17575
 *</pre>
 */
public class NSAL0500Constant {

    /**
     * Business ID
     */
    public static final String BUSINESS_ID = "NSAL0500";

    /**
     * Timestamp store pattern
     */
    public static final String TS_STORE_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * Message Kind Error
     */
    public static final String ERROR = "E";

    /**
     * SEPARATOR
     */
    public static final String SEPARATOR = "***";

    /**
     * Single Comment Max Length
     */
    public static final int SINGLE_CMNT_MAX_LENGTH = 4000;

    /**
     * Max Comment Count
     */
    public static final int CMNT_MAX_COUNT = 8;

    // del start 2016/03/28 CSA Defect#4576
//    /**
//     * Service Momo Type (Sequence) : 71
//     */
//    public static final String SVC_MEMO_TP_SEQ_71 = "71";
//
//    /**
//     * Service Momo Type (Sequence) : 72
//     */
//    public static final String SVC_MEMO_TP_SEQ_72 = "72";
//
//    /**
//     * Service Momo Type (Sequence) : 73
//     */
//    public static final String SVC_MEMO_TP_SEQ_73 = "73";
//
//    /**
//     * Service Momo Type (Sequence) : 74
//     */
//    public static final String SVC_MEMO_TP_SEQ_74 = "74";
//
//    /**
//     * Service Momo Type (Sequence) : 75
//     */
//    public static final String SVC_MEMO_TP_SEQ_75 = "75";
//
//    /**
//     * Service Momo Type (Sequence) : 76
//     */
//    public static final String SVC_MEMO_TP_SEQ_76 = "76";
//
//    /**
//     * Service Momo Type (Sequence) : 77
//     */
//    public static final String SVC_MEMO_TP_SEQ_77 = "77";
//
//    /**
//     * Service Momo Type (Sequence) : 78
//     */
//    public static final String SVC_MEMO_TP_SEQ_78 = "78";
    // del end 2016/03/28 CSA Defect#4576

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * Data insert failure.(ReturnCode = [@])
     */
    public static final String ZZZM9012E = "ZZZM9012E";

    /**
     * Data update failure.(ReturnCode = [@])
     */
    public static final String ZZZM9013E = "ZZZM9013E";

    /**
     * The entered '@' does not exist.
     */
    public static final String NSAM0072E = "NSAM0072E";

    /**
     * Process ended normally.
     */
    public static final String ZZM8100I = "ZZM8100I";

    // Add Start 2018/12/10 K.Fujimoto QC#29079
    /**
     *  Failed to delete "@". 
     */
    public static final String NSAM0033E = "NSAM0033E";
    // Add End   2018/12/10 K.Fujimoto QC#29079
    
    /**
     * The chronological sequence between the dates in the "@" field is wrong.
     */
    public static final String NSAM0064E = "NSAM0064E";

    /**
     * This data has been updated by another user.
     */
    public static final String NZZM0003E = "NZZM0003E";

    /**
     * Relation "@" and "@" is not correct.
     */
    public static final String NSAM0138E = "NSAM0138E";

    /**
     * [@] is duplicated.
     */
    public static final String NSAM0035E = "NSAM0035E";

    /**
     * For "@", please enter a date between "@" and "@".
     */
    public static final String NSAM0358E = "NSAM0358E";

    /**
     * @ exceeds the maximum number of digits.
     */
    public static final String NSAM0359E = "NSAM0359E";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * For Sub Contract termination, please enter effective date earlier than sales date.
     */
    public static final String NSAM0425E = "NSAM0425E";

    // mod start 2017/03/01 CSA QC#17575
    /** @ is not @.*/
    public static final String NSZM0634E = "NSZM0634E";
    // mod end 2017/03/01 CSA QC#17575

    /**
     * DS_CONTR_PK
     */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    // Add Start 2018/12/10 K.Fujimoto QC#29079
    /**
     * DS_CONTR_PK
     */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";
    // Add End   2018/12/10 K.Fujimoto QC#29079

    /**
     * DS_CONTR_CATG_CD
     */
    public static final String DS_CONTR_CATG_CD = "DS_CONTR_CATG_CD";

    /**
     * TABLE_A
     */
    public static final String TABLE_A = "A";

    /**
     * TABLE_B
     */
    public static final String TABLE_B = "B";

    /**
     * TABLE_C
     */
    public static final String TABLE_C = "C";

    /**
     * MAX_THRU_DT
     */
    public static final String MAX_THRU_DT = "29991231";

    /** Display Mode : Update */
    public static final String DISPLAY_MODE_UPDATE = "1";

    /** Display Mode : Freeze */
    public static final String DISPLAY_MODE_FREEZE = "0";

    // add start 2016/02/22 CSA Defect#3689
    /**
     * VAR_CHAR_CONST:SVC_MEMO_RSN_FOR_SUB_CONTR
     */
    public static final String SVC_MEMO_RSN_FOR_SUB_CONTR = "SVC_MEMO_RSN_FOR_SUB_CONTR";
    // add end 2016/02/22 CSA Defect#3689

}
