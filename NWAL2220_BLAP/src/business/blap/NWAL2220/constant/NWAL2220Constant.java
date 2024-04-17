/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2220.constant;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#6283
 * 2016/11/11   Fujitsu         T.Yoshida       Update          QC#14410
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20892
 *</pre>
 */
public final class NWAL2220Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NWAL2220";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * The chronological sequence between the dates in the "@" field
     * is wrong.
     */
    public static final String NWAM0266E = "NWAM0266E";

    // START 2016/04/26 K.Kojima [QC#6283,ADD]
    /**
     * The process [@] has been successfully completed.
     */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * ,You can not [@] this record Because of [@] already exists.
     */
    public static final String NWAM0837E = "NWAM0837E";

    /**
     * [@] is not selected.
     */
    public static final String NWAM0838E = "NWAM0838E";

    //QC#9971
    /**
     * Data insert failure.  [ TableName = @ , key = @, value = @ ]
     */
    public static final String ZZMM0001E = "ZZMM0001E";

    // END 2016/04/26 K.Kojima [QC#6283,ADD]

    // QC#14410 Add Start
    /** Please enter at least one search criteria. */
    public static final String NWAM0754E = "NWAM0754E";
    // QC#14410 Add End

    /**
     * ERR_PRM_IMPT_DT_FROM : Import Date From
     */
    public static final String ERR_PRM_IMPT_DT_FROM = "Import Date From";

    /**
     * ERR_PRM_IMPT_DT_TO : Import Date To
     */
    public static final String ERR_PRM_IMPT_DT_TO = "Import Date To";

    //QC#9971
    /**
     * ERR_PRM_CPO_SRC_TP_CD : Source Name
     */
    public static final String ERR_PRM_CPO_SRC_TP_CD = "Source Name";

    /**
     * MAP_KEY : CPO_SRC_TP_CD
     */
    public static final String CPO_SRC_TP_CD = "CPO_SRC_TP_CD";

    /**
     * MAP_KEY : CPO_SRC_TP_DESC_TXT
     */
    public static final String CPO_SRC_TP_DESC_TXT = "CPO_SRC_TP_DESC_TXT";

    /**
     * MAP_KEY : ORD_SRC_REF_NUM
     */
    public static final String ORD_SRC_REF_NUM = "ORD_SRC_REF_NUM";

    /**
     * MAP_KEY : DS_IMPT_ORD_PK
     */
    public static final String DS_IMPT_ORD_PK = "DS_IMPT_ORD_PK";

    /**
     * MAP_KEY : IMPT_STS_CD
     */
    public static final String IMPT_STS_CD = "IMPT_STS_CD";

    /**
     * MAP_KEY : IMPT_STS_DESC_TXT
     */
    public static final String IMPT_STS_DESC_TXT = "IMPT_STS_DESC_TXT";

    /**
     * MAP_KEY : ORD_SRC_IMPT_TS_TXT
     */
    public static final String ORD_SRC_IMPT_TS_TXT = "ORD_SRC_IMPT_TS_TXT";

    /**
     * MAP_KEY : ORD_SRC_IMPT_TS
     */
    public static final String ORD_SRC_IMPT_TS = "ORD_SRC_IMPT_TS";

    /**
     * MAP_KEY : TOC_CD
     */
    public static final String TOC_CD = "TOC_CD";

    /**
     * MAP_KEY : TOC_NM
     */
    public static final String TOC_NM = "TOC_NM";

    /**
     * MAP_KEY : COA_EXTN_CD
     */
    public static final String COA_EXTN_CD = "COA_EXTN_CD";

    /**
     * MAP_KEY : COA_EXTN_DESC_TXT
     */
    public static final String COA_EXTN_DESC_TXT = "COA_EXTN_DESC_TXT";

    /**
     * MAP_KEY : COA_BR_CD
     */
    public static final String COA_BR_CD = "COA_BR_CD";

    /**
     * MAP_KEY : COA_BR_DESC_TXT
     */
    public static final String COA_BR_DESC_TXT = "COA_BR_DESC_TXT";

    /**
     * MAP_KEY : DS_ORD_CATG_CD
     */
    public static final String DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /**
     * MAP_KEY : DS_ORD_CATG_DESC_TXT
     */
    public static final String DS_ORD_CATG_DESC_TXT = "DS_ORD_CATG_DESC_TXT";

    // Mod Start 2017/09/01 QC#20892
//    /**
//     * MAP_KEY : DS_ORD_RSN_CD
//     */
//    public static final String DS_ORD_RSN_CD = "DS_ORD_RSN_CD";
//
//    /**
//     * MAP_KEY : DS_ORD_RSN_DESC_TXT
//     */
//    public static final String DS_ORD_RSN_DESC_TXT = "DS_ORD_RSN_DESC_TXT";
    /**
     * MAP_KEY : DS_ORD_TP_CD
     */
    public static final String DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /**
     * MAP_KEY : DS_ORD_TP_DESC_TXT
     */
    public static final String DS_ORD_TP_DESC_TXT = "DS_ORD_TP_DESC_TXT";
    // Mod End 2017/09/01 QC#20892

    /**
     * MAP_KEY : CPO_ORD_NUM
     */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";

    /**
     * MAP_KEY : SELL_TO_CUST_CD
     */
    public static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /**
     * MAP_KEY : DS_ACCT_NM_AO
     */
    public static final String DS_ACCT_NM_AO = "DS_ACCT_NM_AO";

    /**
     * MAP_KEY : SOLD_TO_CUST_LOC_CD
     */
    public static final String SOLD_TO_CUST_LOC_CD = "SOLD_TO_CUST_LOC_CD";

    /**
     * MAP_KEY : BILL_TO_CUST_ACCT_CD
     */
    public static final String BILL_TO_CUST_ACCT_CD = "BILL_TO_CUST_ACCT_CD";

    /**
     * MAP_KEY : DS_ACCT_NM_AB
     */
    public static final String DS_ACCT_NM_AB = "DS_ACCT_NM_AB";

    /**
     * MAP_KEY : BILL_TO_CUST_CD
     */
    public static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /**
     * MAP_KEY : SHIP_TO_CUST_ACCT_CD
     */
    public static final String SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";

    /**
     * MAP_KEY : DS_ACCT_NM_AH
     */
    public static final String DS_ACCT_NM_AH = "DS_ACCT_NM_AH";

    /**
     * MAP_KEY : SHIP_TO_CUST_CD
     */
    public static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /**
     * LIMIT_DL_ROWNUM : 65000
     */
    public static final int LIMIT_DL_ROWNUM = 65000;

    /**
     * Max Fetch Size : 1000
     */
    public static final int MAX_FETCH_SIZE = 1000;

    /**
     * CSV file name
     */
    public static final String CSV_FILE_NAME = BUSINESS_ID;

    /**
     * PARAM_ONE : 1
     */
    public static final int PARAM_ONE = 1;

    /**
     * PARAM_FOURTEEN : 14
     */
    public static final int PARAM_FOURTEEN = 14;

    /**
     * FORMAT_TS : 00000000000000
     */
    public static final String FORMAT_TS = "00000000000000";

    /**
     * Search Condition From Time
     */
    public static final String SRCH_COND_FROM_TIME = "000000000";

    /**
     * Search Condition Thru Time
     */
    public static final String SRCH_COND_THRU_TIME = "235959999";
    
    //QC#9971
    /**
     * Report Request Column Type Code - 01 : Source Reference Num
     */
    public static final String RQ_ORD_SRC_REF_NUM = "01";
    /**
     * Report Request Column Type Code - 02 : Import Status Code
     */
    public static final String RQ_IMPT_STS_CD = "02";
    /**
     * Report Request Column Type Code - 03 : Sales Rep Name
     */
    public static final String RQ_TOC_NM = "03";
    /**
     * Report Request Column Type Code - 04 : CPO Order Num
     */
    public static final String RQ_CPO_ORD_NUM = "04";
    /**
     * Report Request Column Type Code - 05 : Sales Business Unit
     */
    public static final String RQ_COA_EXTN_CD = "05";
    /**
     * Report Request Column Type Code - 06 : Sold To Customer Number
     */
    public static final String RQ_SELL_TO_CUST_CD = "06";
    /**
     * Report Request Column Type Code - 07 : Bill To Customer Number
     */
    public static final String RQ_BILL_TO_CUST_ACCT_CD = "07";
    /**
     * Report Request Column Type Code - 08 : Ship To Customer Number
     */
    public static final String RQ_SHIP_TO_CUST_ACCT_CD = "08";
    /**
     * Report Request Column Type Code - 09 : Import Date From
     */
    public static final String RQ_ORD_SRC_IMPT_TS_FROM = "09";
    /**
     * Report Request Column Type Code - 10 : Sold To Customer Name
     */
    public static final String RQ_DS_ACCT_NM_SELL = "10";
    /**
     * Report Request Column Type Code - 11 : Bill To Customer Name
     */
    public static final String RQ_DS_ACCT_NM_BILL = "11";
    /**
     * Report Request Column Type Code - 12 : Ship To Customer Name
     */
    public static final String RQ_DS_ACCT_NM_SHIP = "12";
    /**
     * Report Request Column Type Code - 13 : Import Date To
     */
    public static final String RQ_ORD_SRC_IMPT_TS_TO = "13";
    /**
     * Report Request Column Type Code - 14 : Sales Branch Name
     */
    public static final String RQ_COA_BR_DESC_TXT = "14";
    /**
     * Report Request Column Type Code - 15 : Sold To Location Number
     */
    public static final String RQ_SOLD_TO_CUST_LOC_CD = "15";
    /**
     * Report Request Column Type Code - 16 : Bill To Location Number
     */
    public static final String RQ_BILL_TO_CUST_CD = "16";
    /**
     * Report Request Column Type Code - 17 : Ship To Location Number
     */
    public static final String RQ_SHIP_TO_CUST_CD = "17";
    
}
