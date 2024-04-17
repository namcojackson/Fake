/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB007001;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 04/20/2015   Fujitsu         Y.Kamide        Create          N/A
 * 2021/07/01   CITS            G.Delgado       Update          QC#58909
 *</pre>
 */
public class NFDB007001Constant {

    /** business_id : NFDB0070 */
    public static final String BUSINESS_ID = "NFDB0070";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

    /** The mail template cannot be acquired. <Template ID: [@]> */
    public static final String NFBM0184E = "NFBM0184E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0003";

    /** template ID */
    public static final String MAIL_TMPL_ID = "NFDB0070M001";

    /** template parameter key : wrkItemNm */
    public static final String MAIL_TMPL_KEY_WRK_ITEM_NM = "wrkItemNm";

    /** template parameter key : acctNum */
    public static final String MAIL_TMPL_KEY_ACCT_NUM = "acctNum";

    // START 2021/07/01 G.Delgado [QC#58909,MOD]
    // /** template parameter key : locNum */
    // public static final String MAIL_TMPL_KEY_LOC_NUM = "locNum";
    /** template parameter key : billToCustCd */
    public static final String MAIL_TMPL_KEY_BILL_TO_CUST_CD = "billToCustCd";
    // END 2021/07/01 G.Delgado [QC#58909,MOD]

    /** template parameter key : cltPsnNm */
    public static final String MAIL_TMPL_KEY_CLT_PSN_NM = "cltPsnNm";

    /** template parameter key : cltWrkItemRwsdDt */
    public static final String MAIL_TMPL_KEY_CLT_WRK_ITEM_RWSD_DT = "cltWrkItemRwsdDt";

    /** template parameter key : cltWrkItemWsrdDt */
    public static final String MAIL_TMPL_KEY_CLT_WRK_ITEM_WSRD_DT = "cltWrkItemWsrdDt";

    /** template parameter key : cltWrkItemRwedDt */
    public static final String MAIL_TMPL_KEY_CLT_WRK_ITEM_RWED_DT = "cltWrkItemRwedDt";
    
    /** VAR_CHAR Key : AR_CLT_DEF_EML_ADDR */
    public static final String AR_CLT_DEF_EML_ADDR = "AR_CLT_DEF_EML_ADDR";
    
    /** AR_CLT_DEF_EML_ADDR_NM */
    public static final String AR_CLT_DEF_EML_ADDR_NM = "Default Collector";
    

}
