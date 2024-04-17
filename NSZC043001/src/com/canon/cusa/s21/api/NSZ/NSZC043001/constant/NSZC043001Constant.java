/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC043001.constant;

/**
 * <pre>
 * FSR Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/04/2015   Fujitsu         Y.Kamide        Create
 * 02/08/2016   Hitachi         T.Iwamoto       Update          QC#2863
 * 05/23/2016   Hitachi         Y.Takeno        Update          QC#447
 * 01/18/2017   Hitachi         K.Kitachi       Update          QC#15818
 * 01/19/2017   Hitachi         Y.Takeno        Update          QC#15092
 * 04/24/2017   Hitachi         K.Kitachi       Update          QC#18370
 * 05/19/2017   Hitachi         K.Kitachi       Update          QC#18213
 * 06/05/2018   CITS            M.Naito         Update          QC#17390
 * 06/25/2018   CITS            M.Naito         Update          QC#25608
 * 07/12/2018   CITS            M.Naito         Update          QC#13309
 * 08/14/2018   CITS            M.Naito         Update          QC#27472
 * 2018/08/30   Hitachi         K.Kitachi       Update          QC#22665
 * 2021/01/26   ci-tech         L.Mandanas      Update          QC#56947
 * 2021/08/03   CITS            L.Mandanas      Update          QC#59066
 * 2022/02/11   CITS            L.Mandanas      Update          QC#59066
 * 2023/05/26   Hitachi         T.Usui          Update          QC#61069
 * 2023/05/31   Hitachi         K.Watanabe      Update          QC#61309
 * 2023/06/14   Hitachi         K.Watanabe      Update          QC#61309
 * 2023/07/14   Hitachi         K.Watanabe      Update          QC#61310
 *</pre>
 */
public interface NSZC043001Constant {
    // -------------- Mode ---------------
    /** Mode: 01 : Create FSR */
    String MODE_CREATE_FSR = "01";

    /** Mode: 02 : Update FSR */
    String MODE_UPDATE_FSR = "02";

    /** Mode: 03 : Cancel FSR */
    String MODE_CANCEL_FSR = "03";

 // START 2021/01/26 L.Mandanas [QC#56947, ADD]
    /** Mode: 03 : UPDATE FSR PR */
    String MODE_UPDATE_FSR_PR = "04";
 // END 2021/01/26 L.Mandanas [QC#56947, ADD]

    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";

    /** Input parameter "Process Mode" is a mandatory field. */
    String NSZM0003E = "NSZM0003E";

    /** Service machine master is not found. */
    String NSZM0006E = "NSZM0006E";

    /** Input parameter "Bill To Code" is a mandatory field. */
    String NSZM0015E = "NSZM0015E";

    /** Input parameter "Ship To Code" is a mandatory field. */
    String NSZM0017E = "NSZM0017E";

    /**
     * Input parameter "Service Call Priority Code" is a mandatory
     * field.
     */
    String NSZM0043E = "NSZM0043E";

    /**
     * Input parameter "DS Service Call Type Code" is a mandatory
     * field.
     */
    String NSZM0049E = "NSZM0049E";

    /**
     * Input parameter "Service Task Received Date" is a mandatory
     * field.
     */
    String NSZM0053E = "NSZM0053E";

    /**
     * Input parameter "Service Task Received Time" is a mandatory
     * field.
     */
    String NSZM0054E = "NSZM0054E";

    /** Input parameter "Task#" is a mandatory field. */
    String NSZM0156E = "NSZM0156E";

    /**
     * Input parameter "Schedule Dispatch Email Flag" is a mandatory
     * field.
     */
    String NSZM0157E = "NSZM0157E";

    /** Input parameter "Execute User Id" is a mandatory field. */
    String NSZM0163E = "NSZM0163E";

    /** Input parameter "xxSvcTaskList" is a mandatory array. */
    String NSZM0164E = "NSZM0164E";

    /** Input parameter "FSR#" is a mandatory field. */
    String NSZM0180E = "NSZM0180E";

    /**
     * Input parameter "Service Problem Type Code" is a mandatory
     * field.
     */
    String NSZM0243E = "NSZM0243E";

    /** SVC_TASK is not found. */
    String NSZM0486E = "NSZM0486E";

    /** Follow Up Task does not exist. */
    String NSZM0970E = "NSZM0970E";

    /**
     * Input parameter "Service Call Source Type Code" is a mandatory
     * field.
     */
    String NSZM0544E = "NSZM0544E";

    /**
     * Input parameter "Service Call Avoidance Code" is a mandatory
     * field.
     */
    String NSZM0545E = "NSZM0545E";

    /**
     * Input parameter "Service Call Request Owner TOC Code" is a
     * mandatory field.
     */
    String NSZM0546E = "NSZM0546E";

    /** The entered 'DS Service Call Type Code' does not exist. */
    String NSZM0549E = "NSZM0549E";

    /** Input parameter "Service Task Type Code" is a mandatory field. */
    String NSZM0561E = "NSZM0561E";

    /** Service Model cannot be specified. */
    String NSZM0569E = "NSZM0569E";

    /** Mobile Interface Flag is required. */
    String NSZM0591E = "NSZM0591E";

    /** Target Debrief Data does not exist .TABLE ID [FSR_VISIT_TM_EVENT] */
    String NSZM0863E = "NSZM0863E";

    /** Target Chargeable Data does not exist .TABLE ID [FSR_CHRG_DTL] */
    String NSZM0864E = "NSZM0864E";

 // START 05/23/2016 [QC#447, ADD]
     /** The Serial has been End of Life. */
     String NSZM0976E = "NSZM0976E";
 // END   05/23/2016 [QC#447, ADD]

    // START 2017/01/18 K.Kitachi [QC#15818, ADD]
    /** This is not main machine. Please create a Call using main Machine. */
    String NSZM1097E = "NSZM1097E";
    // END 2017/01/18 K.Kitachi [QC#15818, ADD]

    // START 01/19/2017 [QC#15092, ADD]
    /** Input parameter "Credit Card Customer Reference Number" is a mandatory field. */
    String NSZM1067E = "NSZM1067E";
    // END   01/19/2017 [QC#15092, ADD]

    // START 2018/06/05 M.Naito [QC#17390, ADD]
    /** According to the master setting, this item does not accept Service call. */
    String NSZM1336E = "NSZM1336E";
    // END 2018/06/05 M.Naito [QC#17390, ADD]

    // START 2018/08/14 M.Naito [QC#27472, ADD]
    /** Current status of this task cannot accept your updates. */
    String NSZM1344E = "NSZM1344E";
    // END 2018/08/14 M.Naito [QC#27472, ADD]

    // Add Start 2018/06/25 QC#25608
    /** It failed to cancel workflow. */
    String NSZM0866E = "NSZM0866E";

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    /**
     * Could not get Mailtemplate.
     */
    String NSZM0185E = "NSZM0185E";

    /**
     * Attempted to create an After Hours Service Call but is on a Hard Credit Hold. The call was not created. Please call the customer.
     */
    String NSZM1346I = "NSZM1346I";
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    /**
     * Process def name : NSWP0004
     */
    String PROCESS_DEF_NSWP0004 = "NSWP0004";
    // Add End 2018/06/25 QC#25608

    /** 000000 */
    String TM_START_OFTHEDAY = "000000";

    /** 000 */
    String TS_POSTFIX = "000";

    /** TimeStamp format */
    String FORMAT_SYS_TS = "yyyyMMddHHmmssSSS";

    /** Time Start Position */
    int TIME_START_POS = 8;

    /** Time End Position */
    int TIME_END_POS = 6;

    // START 2017/04/24 K.Kitachi [QC#18370, ADD]
    /** TimeStamp Length */
    int TS_LEN = 14;
    // END 2017/04/24 K.Kitachi [QC#18370, ADD]

    // START 2017/05/19 K.Kitachi [QC#18213, ADD]
    /** Substring Position 10 */
    public static final int SUB_STR_POS_10 = 10;
    // END 2017/05/19 K.Kitachi [QC#18213, ADD]

    // START 2018/07/12 M.Naito [QC#13309, ADD]
    /**
     * Day of the week is Holiday
     */
    public static final int DAT_OF_THE_WEEK_IS_HOLIDAY = 0;

    /** Substring Position 4 */
    public static final int SUB_STR_POS_4 = 4;

    /** Substring Position 6 */
    public static final int SUB_STR_POS_6 = 6;

    /** Substring Position 8 */
    public static final int SUB_STR_POS_8 = 8;

    /**
     * Date Attribute Value Code
     */
    public static final String DT_ATRB_VAL_CD = "1";

    /** Key of Num Const : AHS_CALC_MIN */
    public static final String AHS_CALC_MIN = "AHS_CALC_MIN";

    /** Time format */
    public static final String TIME_FORMAT = "HHmmss";

    /**
     * Key Of VAR_CHAR_CONST:After Hours Working Program
     */
    public static final String TERM_COND_AFTER_HOURS_WRK_PGM = "TERM_COND_AFTER_HOURS_WRK_PGM";
    // END 2018/07/12 M.Naito [QC#13309, ADD]

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    /** Key of VarCharConst : AR_CLT_DEF_EML_ADDR */
    public static final String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";

    /** mail group id for From */
    // START 2022/02/11 L.Mandanas [QC#59066, MOD]
      //public static final String MAIL_GROUP_ID_FROM = "FROM0002";
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";
    // END 2022/02/11 L.Mandanas [QC#59066, MOD]

    /** template ID */
    public static final String MAIL_TEMPLATE_ID = "NSZC0430M001";
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    // START 2021/08/03 L.Mandanas [QC#59066, ADD]
    /**
     * mail group mail key for From
     */
    // START 2022/02/11 L.Mandanas [QC#59066, MOD]
      //public static final String MAIL_GROUP_MAIL_KEY_FROM = "NS";
    public static final String MAIL_GROUP_MAIL_KEY_FROM = "NSZ";
    // END 2022/02/11 L.Mandanas [QC#59066, MOD]
    // END 2021/08/03 L.Mandanas [QC#59066, ADD]

    // QC#61069 2023/05/26 Add Start
    /** SVC_BY_LINE_BIZ_TP_CD_PPS */
    public static final String SVC_BY_LINE_BIZ_TP_CD_PPS = "PPS";

    /** SVC_BY_LINE_BIZ_TP_CD_LFS */
    public static final String SVC_BY_LINE_BIZ_TP_CD_LFS = "LFS";
    // QC#61069 2023/05/26 Add End

    // START 2023/06/14 K.Watanabe [QC#61309, ADD]
    /**
     * Dollar fraction
     */
    int DOLLAR_FRACTION = 2;
    // END 2023/06/14 K.Watanabe [QC#61309, ADD]

    // START 2023/07/14 K.Watanabe [QC#61310, ADD]
    /** NSZC0430_DEF_BILL_TO_CUST_CD */
    public static final String NSZC0430_DEF_BILL_TO_CUST_CD = "NSZC0430_DEF_BILL_TO_CUST_CD";

    /** NSZC0430_DEF_BILL_TO_ACCT_CD */
    public static final String NSZC0430_DEF_BILL_TO_ACCT_CD = "NSZC0430_DEF_BILL_TO_ACCT_CD";
    // END 2023/07/14 K.Watanabe [QC#61310, ADD]

    // START 2023/05/31 K.Watanabe [QC#61309, ADD]
    /**
     * Begin index for message
     */
    int BEGIN_INDEX_FOR_MESSAGE = 10;

    /**
     * The labor billing rate is $ @ / hour. Charge for Labor and Parts Cost. <br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge.
     */
    String NSZM1397I = "NSZM1397I";

    /**
     * The labor billing rate is $ @ / hour. Charge for Labor, plus a Flat Travel charge not to exceed $ @ will be added to the cost of service and Parts Cost.<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge.
     */
    String NSZM1398I = "NSZM1398I";

    /**
     * The labor billing rate is $ @ / hour.<br>Your request is covered by a Recall Warranty, first hour of Labor only is at no charge.
     */
    String NSZM1399I = "NSZM1399I";

    /**
     * The Chargeable labor rate for this call is Estimated at $ @ per hour hours.
     */
    String NSZM1400I = "NSZM1400I";

    /**
     * The Chargeable labor rate for this call is Estimated at $ @ per hour with a Minimum of @ hours and a Flat Travel charge not to exceed $ @ will be added to the cost of service.
     */
    String NSZM1401I = "NSZM1401I";

    /**
     * The Chargeable labor rate for this call is Estimated at $ @ per hour with a Minimum of @ hours
     */
    String NSZM1402I = "NSZM1402I";

    /**
     * This SR is Chargeable at a rate of $ @
     */
    String NSZM1403I = "NSZM1403I";
    // END 2023/05/31 K.Watanabe [QC#61309, ADD]
}
