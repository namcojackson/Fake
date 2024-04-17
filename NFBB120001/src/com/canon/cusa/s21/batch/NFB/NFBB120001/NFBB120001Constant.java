package com.canon.cusa.s21.batch.NFB.NFBB120001;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Create AP Payment
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/19/2016   CUSA            Y.Aikawa        Create          N/A
 * 10/05/2016   Hitachi         T.Tsuchida      Update          QC#14498
 * 03/14/2017   Hitachi         Y.Tsuchimoto    Update          QC#18028
 * 01/26/2018   CITS            T.Tokutomi      Update          QC#18685
 * </pre>
 */
public interface NFBB120001Constant {
    // ** Fixed Value ** //
    // ** Common ** //
    /** Bulk Commit Limit */
    static final int INT_BULK_COM_LIM = 10000;

    // ** Interface ID ** //
    /** INTERFACE ID NFBI0300 */
    static final String INTERFACE_ID_NFBI0300 = "NFBI0300";

    /** INTERFACE ID NFBI0310 */
    static final String INTERFACE_ID_NFBI0310 = "NFBI0310";
    
    
    // ** Message ID ** //
    /** Error Message */
    static final String NFBM0028E = "NFBM0028E";
    /** Error Message (Unique Constraint Violation) */
    static final String ZZBM0074E = "ZZBM0074E";
    /** Information Message(The record of INTERFACE_TRANSACTION table has already processed. [InterfaceId : @, TransactionId : @ ]) */
    static final String ZZIM0009I = "ZZIM0009I";

    // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
    /** Error Message (Data update failure.  [ TableName = @ , key = @, value = @ ]) */
    static final String ZZMM0015E = "ZZMM0015E";

    /** "@" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NFBM0268E = "NFBM0268E";
    // END 2018/01/26 T.Tokutomi [QC#18685,ADD]

    // ** SSM Statement ID's Fixed Value ** //
    /** SSM Statement ID */
    static final String SELECT_IF_RECORD = "SELECT_IF_RECORD";

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String ARCS_PMT_ID = "ARCS_PMT_ID";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String AP_PMT_CHK_NUM = "AP_PMT_CHK_NUM";
    /** DB Item Column Name */
    static final String AP_INV_AMT = "AP_INV_AMT";
    /** DB Item Column Name */
    static final String PMT_DT = "PMT_DT";
    /** DB Item Column Name */
    static final String ARCS_PMT_STS_CD = "ARCS_PMT_STS_CD";
    /** DB Item Column Name */
    static final String ARCS_INV_SRC_TXT = "ARCS_INV_SRC_TXT";
    /** DB Item Column Name */
    static final String CHK_STS_CD = "CHK_STS_CD";
    // START 2016/10/05 T.Tsuchida [QC#14498,ADD]
    /** DB Item Column Name */
    static final String AP_PMT_DISC_AMT = "AP_PMT_DISC_AMT";
    /** DB Item Column Name */
    static final String ARCS_PMT_AMT = "ARCS_PMT_AMT";
    /** DB Item Column Name */
    static final String ARCS_PMT_METH_CD = "ARCS_PMT_METH_CD";
    /** DB Item Column Name */
    static final String ARCS_CCY_CD = "ARCS_CCY_CD";
    /** DB Item Column Name */
    static final String ARCS_EXCH_RATE = "ARCS_EXCH_RATE";
    /** DB Item Column Name */
    static final String ARCS_EXCH_DT = "ARCS_EXCH_DT";
    /** DB Item Column Name */
    static final String ARCS_BANK_ACCT_CD = "ARCS_BANK_ACCT_CD";
    // END 2016/10/05 T.Tsuchida [QC#14498,ADD]

    // START 2017/03/14 Y.Tsuchimoto [QC#18028,ADD]
    /** Condition of NFBI0310_01.ARCS_INV_SRC_TXT(Comma separated) */
    static final String NFBB1200_SEARCH_COND_SRC_TXT = "NFBB1200_SEARCH_COND_SRC_TXT";

    /** Condition of NFBI0310_01.ARCS_INV_SRC_TXT(Default Value) */
    static final String NFBB1200_SEARCH_COND_SRC_TXT_DEF_VAL = "S21 ACCOUNT PAYABLE";
    // END   2017/03/14 Y.Tsuchimoto [QC#18028,ADD]

    // START 2018/01/26 T.Tokutomi [QC#18685,ADD]
    /**  VarcharConstName : ARCS_PMT_STS_NEGOTIABLE */
    static final String ARCS_PMT_STS_NEGOTIABLE = "ARCS_PMT_STS_NEGOTIABLE";

    /** VarcharConstName : ARCS_PMT_STS_VOID */
    static final String ARCS_PMT_STS_VOID = "ARCS_PMT_STS_VOID";
    // END 2018/01/26 T.Tokutomi [QC#18685,ADD]
}