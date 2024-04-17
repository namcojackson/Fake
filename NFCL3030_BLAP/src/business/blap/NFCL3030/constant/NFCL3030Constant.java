package business.blap.NFCL3030.constant;

/**
 *<pre>
 * Receipt Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/07/25   Hitachi         J.Kim           Update          QC#9476
 * 2017/03/13   Hitachi         Y.Tsuchimoto    Update          QC#17918
 * 2018/01/10   Fujitsu         T.Murai         Update          QC#21400
 * 2018/01/22   Fujitsu         H.Ikeda         Update          QC#23136
 * 2018/01/31   Fujitsu         T.Murai         Update          QC#21401
 * 2018/02/06   Fujitsu         T.Murai         Update          QC#21372
 * 2020/09/04   CITS            R.Kurahashi     Update          QC#56012-3
 * 2022/08/04   CITS            D.Mamaril       Update          QC#60376
 * 2024/03/21   Hitachi         S.Ikariya       Update          QC#63718
 *</pre>
 */
public interface NFCL3030Constant {

    /**
     * Receipt Return Code : Normal
     */
    public static final String RECEIPT_RTNCD_NORMAL = "0";

    /**
     * Receipt Return Code : Error
     */
    public static final String RECEIPT_RTNCD_ERROR = "1";

    /**
     * Apply Return Code : UnProcess
     */
    public static final String APPLY_RTNCD_UN_PROCCES = "0";

    /**
     * Apply Return Code : Normal
     */
    public static final String APPLY_RTNCD_NORMAL = "1";

    /**
     * Apply Return Code : Cash Application Error
     */
    public static final String APPLY_RTNCD_CASHAPP_ERROR = "2";

    /**
     * Apply Return Code : Exclusion Error
     */
    public static final String APPLY_RTNCD_EXCLUSION_ERROR = "3";

    /**
     * Apply Return Code : Other Error
     */
    public static final String APPLY_RTNCD_OTHERS_ERROR = "4";

    /**
     * Currency Code : USD
     */
    public static final String CCY_USD = "USD";

    /**
     * Receipt Detail Number : 0001
     */
    public static final String RCPT_DTL_NUM = "0001";

    /**
     * Deal Sequence Number : 
     */
    public static final String DEAL_SQ_NUM = "00000001";

    /**
     * Deal Sequence Detail Number
     */
    public static final String DEAL_SQ_DTL_NUM = "0001";

    /**
     * Receipt Batch Number
     */
    public static final String RCPT_BAT_NUM = "999";

    /**
     * Receipt Batch Sequence Number
     */
    public static final String RCPT_BAT_SQ_NUM = "001";

    /**
     * AR Receipt Type Dummy Code
     */
    public static final String AR_RCPT_TP_DUMMY = "99";

    /**
     * AR Cash Application Sequence Number
     */
    public static final String AR_CASH_APP_SQ_NUM = "00001";

    /**
     * AR Receipt Auto Sequence Key
     */
    public static final String AR_RCPT_AUTO_SEQ_KEY = "RC#";

    /**
     * VarChar Constant Key : AR_RCPT_TOC_CD 
     */
    public static final String VAR_CHAR_CONST_AR_RCPT_TOC_CD_KEY = "AR_RCPT_TOC_CD";

    /**
     * VarChar Constant Key : AR_RCPT_PROD_CD
     */
    public static final String VAR_CHAR_CONST_AR_RCPT_PROD_CD_KEY = "AR_RCPT_PROD_CD";

    /**
     * VarChar Constant Key : AR_RCPT_PROD_CD
     */
    public static final String VAR_CHAR_CONST_AR_SUB_SYS_ID_KEY = "AR_SUB_SYS_ID";

    // START 2024/03/21 S.Ikariya [QC#63718, ADD]
    /**
     * VarChar Constant Key : BANK_ACCT_NOT_MAPPING
     */
    public static final String VAR_CHAR_CONST_BANK_ACCT_NOT_MAPPING = "BANK_ACCT_NOT_MAPPING";

    /**
     * VarChar Constant Dummy Customer
     */
    public static final String VAR_CHAR_CONST_DUMMY_CUSTOMER = "531303";

    // END 2024/03/21 S.Ikariya [QC#63718, ADD]
    /**
     * Create Method Type Code : Manual
     */
    public static final String CREATE_METH_TP_CD_MANUAL = "M";

    /** */
    String TAB_AddInfo = "TAB_AddInfo";

    /** */
    String TAB_Customer = "TAB_Customer";

    /** */
    String TAB_Reversal = "TAB_Reversal";

    // Del Start 2018/01/10 S21_NA21400
    // /** */
    // String AR_BAT_RCPT_STS_NEW = "00";
    //
    // /** */
    // String AR_BAT_RCPT_STS_OUT_OB_BALANCE = "01";
    //
    // /** */
    // String AR_BAT_RCPT_STS_OPEN = "02";
    //
    // /** */
    // String AR_BAT_RCPT_STS_COMPLETE = "03";
    // Del Start 2018/01/10 S21_NA21400

    // Del Start 2018/01/26 S21_NA#21401
//    /** */
//    String AR_RCPT_STS_NEW = "00";
//
//    /** */
//    String AR_RCPT_STS_CLEARED = "01";
//
//    /** */
//    String AR_RCPT_STS_OPEN = "02";
//
//    /** */
//    String AR_RCPT_STS_APPLIED = "03";
//
//    /** */
//    String AR_RCPT_STS_RESERVED_MANUAL = "04";
//
//    /** */
//    String AR_RCPT_STS_RESERVED_AUTO = "05";
    // Del End 2018/01/26 S21_NA#21401

    // START 2016/07/25 J.Kim [QC#9476,ADD]
    /** Var Char Const - CC_RF_AR_RCPT_RF_RSN_CD */
    public static final String CC_RF_AR_RCPT_RF_RSN_CD = "CC_RF_AR_RCPT_RF_RSN_CD";

    /** Var Char Const - CC_RF_AR_RCPT_RF_RSN_CMNT_TXT */
    public static final String CC_RF_AR_RCPT_RF_RSN_CMNT_TXT = "CC_RF_AR_RCPT_RF_RSN_CMNT_TXT";

    /** Var Char Const - CC_RF_AR_RF_CHK_CMNT_TXT */
    public static final String CC_RF_AR_RF_CHK_CMNT_TXT = "CC_RF_AR_RF_CHK_CMNT_TXT";

    /** xxPmtcTaxIndNum */
    public static final String TAX_IND_NUM = "2";

    /** xxPmtcPrflOrdOvrdCd */
    public static final String ORD_OVRD_CD = "NO";

    /** The data @ does not exist in the master. */
    public static final String NFCM0520E = "NFCM0520E";

    /** If Payment Term is not Credit Card, It can not be processed. */
    public static final String NFCM0852E = "NFCM0852E";

    /** If Status is not Partial or Applied, It can not be processed. */
    public static final String NFCM0853E = "NFCM0853E";

    /** If Balance Amount is 0, It can not be processed. */
    public static final String NFCM0854E = "NFCM0854E";

    /** This receipt has already been refunded. */
    public static final String NFCM0036E = "NFCM0036E";

    /** Cannot authenticate Credit Card. */
    public static final String NFCM0855E = "NFCM0855E";

    /** It does not exist in the VAR_CHAR_CONST. (@) */
    public static final String NFCM0856E = "NFCM0856E";

    /** The data does not exist in [@]. [@]  */
    public static final String NFCM0845E = "NFCM0845E";

    /** Data insert failure. (table name is [@]) */
    public static final String NFCM0782E = "NFCM0782E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";
    // END 2016/07/25 J.Kim [QC#9476,ADD]

    // START 2016/11/11 J.Kim [QC#15756,ADD]
    /** Receipt for refund has been applied already. */
    public static final String NFCM0871E = "NFCM0871E";
    // END 2016/11/11 J.Kim [QC#15756,ADD]

    // START 2017/03/13 Y.Tsuchimoto [QC#17918,ADD]
    /** NFCL3030_WINDOW_YEARS_RCPT_DT */
    public static final String NFCL3030_WINDOW_YEARS_RCPT_DT = "NFCL3030_WINDOW_YEARS_RCPT_DT";
    // END   2017/03/13 Y.Tsuchimoto [QC#17918,ADD]

    // START 2018/01/22 H.Ikeda [QC#23136,ADD]
    /** It is being applied with Invoice @. If you want to reverse, cancel Cash Apply of Invoice. */
    public static final String NFCM0882E = "NFCM0882E";
    // END   2018/01/22 H.Ikeda [QC#23136,ADD]

    // START 2018/02/05 [QC#21372,ADD]
    /** The entered customer information is inconsistent. */
    public static final String NFCM0883E = "NFCM0883E";

    /** "@" cannot be specified. */
    public static final String NFCM0857E = "NFCM0857E";

    /** The entered 'Customer Code' does not exist. */
    public static final String NFCM0763E = "NFCM0763E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // END   2018/02/05 [QC#21372,ADD]

    // START 2019/08/26 H.Ikeda [QC#52885, ADD]
    /** on Account data is adjusted. */
    public static final String NFCM0909E = "NFCM0909E";
    // END   2019/08/26 H.Ikeda [QC#52885, ADD]
    
    // START 2020/09/04 R.Kurahashi [QC#56012-3,ADD]
    /** DATE_FORMAT_MMDDYYYY : MM/dd/yyyy */
    public static final String DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy";
    // END 2020/09/04 R.Kurahashi [QC#56012-3,ADD]

    // START 2022/08/04 D.Mamaril [QC#60376,ADD]
    /** This receipt includes amount refunded. If you need to reverse this, please unapply refund first. */
    public static final String NFCM0920E = "NFCM0920E";
    // END 2022/08/04 D.Mamaril [QC#60376,ADD]
}
