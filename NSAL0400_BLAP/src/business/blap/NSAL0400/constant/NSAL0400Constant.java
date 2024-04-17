/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0400.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 * 2016/01/17   Hitachi         K.Yamada        Update          CSA Modify
 * 2016/03/31   Hitachi         K.Kishimoto     Update          QC#6343
 * 2016/08/01   Hitachi         A.Kohinata      Update          QC#2853
 * 2018/05/29   Hitachi         U.Kim           Update          QC#25933
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/09/15   CITS            E.Sanchez       Update          QC#59775
 * 2022/11/02   CITS            L.Mandanas      Update          QC#60652
 * 2023/05/29   CITS            T.Kojima        Update          QC#61529
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 *</pre>
 */
public class NSAL0400Constant {

    /** NSAL0400Scrn00 */
    public static final String SCRN_ID = "NSAL0400Scrn00";

    /** Message IDs */
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    /** Please select at least 1 checkbox. */
    public static final String NSAM0015E = "NSAM0015E";
    /** Parameter "@" is not set. */
    public static final String NSAM0131E = "NSAM0131E";
    /** System Error : @ */
    public static final String NSAM0219E = "NSAM0219E";
    /** The process has been error in some data. Please check the data. */
    public static final String NSAM0394W = "NSAM0394W";
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";
    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";
    /** Please enter a date between [@] and [@] */
    public static final String NSAM0306E = "NSAM0306E";
    /** Please enter a number between [@] and [@]. */
    public static final String NSAM0194E = "NSAM0194E";
    /** Failed to update @ table.[@] */
    public static final String NSAM0001E = "NSAM0001E";
    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";
    /** The status not eligible. */
    public static final String NSAM0414E = "NSAM0414E";
    /** You must terminate all machines under Fleet contract when you specify override amount on fleet contract. */
    public static final String NSAM0415E = "NSAM0415E";
    // Add 03/31/2016 Start <QC#6343>
    /** Cannot specify override amount because there is no billed amount. */
    public static final String NSAM0450E = "NSAM0450E";
    // Add 03/31/2016 End   <QC#6343>
    // add start 2016/08/01 CSA Defect#2853
    /** Meter Read within [@] days from Terminate Date has not been registered. If OK, please click the "Submit" button again. */
    public static final String NSAM0560W = "NSAM0560W";
    // add end 2016/08/01 CSA Defect#2853
    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /** If All Period Terminate is checked, then Termination Date should be the same as Start Date. */
    public static final String NSAM0759E = "NSAM0759E";
    /** Are you sure to terminate all period? If so, press submit again. */
    public static final String NSAM0760W = "NSAM0760W";
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]
    // START 2018/05/29 U.Kim [QC#25933, ADD]
    /** Can not terminate this Machine. Because there is a valid Accessory on this date. */
    public static final String NSZM1334E = "NSZM1334E";
    // END 2018/05/29 U.Kim [QC#25933, ADD]

    // START 2022/11/02 L.Mandanas [QC#60652, ADD]
    /** Account status is inactive. Please activate account. [@] */
    public static final String NSAM0769E = "NSAM0769E";

    /** Bill To Code is inactive. Please activate Bill To Code. [@] */
    public static final String NSAM0770E = "NSAM0770E";

    /** Ship To Code is inactive. Please activate Ship To Code. [@] */
    public static final String NSAM0771E = "NSAM0771E";
    // END 2022/11/02 L.Mandanas [QC#60652, ADD]

    // START 2023/05/29 T.Kojima [QC#61529, ADD]
    /** Termination cannot be done until approved Credit & Rebill invoices get generated. Please wait until next day. */
    public static final String NSAM0777E = "NSAM0777E";
    // END 2023/05/29 T.Kojima [QC#61529, ADD]

    /** Message Parameter */
    public static enum MSG_PRM {
        /** NO_SVC_RSN_CD */
        NO_SVC_RSN_CD("Service Reason Code is not set up")
        /** Message Parameter (No Input Parameter) */
        , NO_INPUT_PARAMETER("No input parameter found")
        /** Message Parameter (DS_CONTR_DTL) */
        , DS_CONTR_DTL("DS_CONTR_DTL")
        /** Message Parameter (SVC_MEMO) */
        , SVC_MEMO("SVC_MEMO")
        /** Message Parameter (Reason Code) */
        , REASON_CODE("Reason Code")
        /** Message Parameter (Termination note) */
        , TERMINATION_NOTE("Termination note")
        /** Message Parameter (Termination cancel) */
        , TERMINATION_CANCEL("Termination cancel")
        /** Message Parameter (Comment) */
        , COMMENT("Comment")
        /** Message Parameter (Termination Date) */
        , TERMINATION_DATE("Termination Date")
        /** Message Parameter (Start Date) */
        , START_DATE("Start Date")
        /** Message Parameter (End Date) */
        , END_DATE("End Date")
        /** Message Parameter (Active) */
        , ACTIVE("Active")
        /** Message Parameter (Amount) */
        , AMOUNT("Amount")
        /** Message Parameter (Fleet line) */
        , FLEET_LINE("Fleet line")
        /** Message Parameter ( not) */
        , NOT(" not")
        /** Message Parameter (DS_CONTR_PK) */
        , DS_CONTR_PK("DS_CONTR_PK")
        /** Message Parameter (DS_CONTR_CATG_CD) */
        , DS_CONTR_CATG_CD("DS_CONTR_CATG_CD")
        /** Message Parameter (Review) */
        , REVIEW("Review")
        /** Message Parameter (Terminate) */
        , TERMINATE("Terminate")
        /** Message Parameter (Terminate Cancel) */
        , CANCEL("Terminate Cancel")
        /** Message Parameter (Review) */
        , APPLY("Apply to All")
        /** Message Parameter (Submit) */
        , SUBMIT("Submit")
        , /* */;

        /** */
        private String msgPrm;

        MSG_PRM(String msgPrm) {
            this.msgPrm = msgPrm;
        }

        /**
         * @return getMsgPrm
         */
        public String getMsgPrm() {
            return msgPrm;
        }

    }

    /** format for time stamp */
    public static final String TS_FMT = "yyyyMMddHHmmssSSS";

    /** Day of Year */
    public static final BigDecimal DAY_OF_YEAR = BigDecimal.valueOf(365);

    /** NSZC0460 Terminate Mode */
    public static final String TERM_MODE = "05";

    /** service memo reason length */
    public static final int SVC_MEMO_RSN_DESC_TXT_LN = 50;

    // add start 2016/08/01 CSA Defect#2853
    /** format for SQL Date */
    public static final String DATE_FMT = "YYYYMMDD";

    /** NUM_CONST: NSAL0400_CONTR_CLO_LIMIT_DT */
    public static final String NSAL0400_CONTR_CLO_LIMIT_DT = "NSAL0400_CONTR_CLO_LIMIT_DT";
    // add end 2016/08/01 CSA Defect#2853

    // START 2022/09/15 E.Sanchez [QC#59775, ADD]
    /** Already terminated. */
    public static final String NSAM0764E = "NSAM0764E";
    // END 2022/09/15 E.Sanchez [QC#59775, ADD]

    // START 2022/11/02 L.Mandanas [QC#60652, ADD]
    /** Delimiter for message parameter */
    public static final String DELIMITER = "], [";

    /** Length 100 */
    public static final int LEN_100 = 100;
    // END 2022/11/02 L.Mandanas [QC#60652, ADD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /** Final Meter read has not been registered within [@] days from Termination Date. Please check the meter type after Submit. */
    public static final String NSAM0788W = "NSAM0788W";
    // END   2024/03/26 K.Watanabe [QC#63549, ADD]
}
