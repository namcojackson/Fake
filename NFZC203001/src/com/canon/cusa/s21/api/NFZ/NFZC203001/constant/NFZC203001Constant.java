/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFZ.NFZC203001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * NFZC203001Constant.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Nakamura      Create          N/A.
 * 2016/03/16   Fujitsu         C.Naito         Update          QC#5509
 * 2016/04/01   Fujitsu         C.Naito         Update          QC#5570
 * 2016/04/06   Fujitsu         C.Naito         Update          QC#6634
 * 2016/04/06   Fujitsu         C.Naito         Update          QC#6634
 * 2018/04/24   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/04/24   Fujitsu         H.Ikeda         Update          QC#23882-1
 * 2018/05/15   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/07/11   Fujitsu         S.Ohki          Update          QC#27002
 * </pre>
 */
public class NFZC203001Constant {

    /** Number of types of Aging */
    public static final int AGE_TYPE_NUM = 6;

    /** Aging Type Over */
    public static final int AGE_TYPE_TOT = 0;

    /** Aging Type current */
    public static final int AGE_TYPE_CUR = 1;

    /** Aging Type 0130 */
    public static final int AGE_TYPE_0130 = 2;

    /** Aging Type 3160 */
    public static final int AGE_TYPE_3160 = 3;

    /** Aging Type 6190 */
    public static final int AGE_TYPE_6190 = 4;

    /** Aging Type Over */
    public static final int AGE_TYPE_OVER = 5;

    /** Aging 30 */
    public static final int AGE_30 = 30;

    /** Aging 60 */
    public static final int AGE_60 = 60;

    /** Aging 90 */
    public static final int AGE_90 = 90;

    /** SVC_CTAC_PHONE_LEN */
    public static final int SVC_CTAC_PHONE_LEN = 12;
    
    // [QC#6634] INSERT start
    /** CLT_STMT_PHONE_LEN */
    public static final int CLT_STMT_PHONE_LEN = 12;
    
    /** Contact information beginning part */
    public static final String CONTACT_INFO_BIGIN_PART  = "CONTACT - ";
    // [QC#6634] INSERT end

    /** CTAC_PSN_NM_LEN */
    // [QC#5509] UPDATE start
    // public static final int SVC_CTAC_COR_NM_LEN = 45;
    // [QC#6634] UPDATE start
    // public static final int SVC_CTAC_COR_NM_LEN = 44;
    public static final int CTAC_PSN_NM_LEN = 34;
    // [QC#6634] UPDATE end
    // [QC#5509] UPDATE end
    
    /** FIRST_CTAC_LEN */
    public static final int FIRST_CTAC_LEN = 60;

    /** Max Late Days */
    public static final BigDecimal MAX_LATE_DAYS = new BigDecimal("999");

    /** String Receipt Check Separator */
    public static final String STR_RCPT_CHK_SEP = ": ";

    /** String Receipt Check Separator */
    public static final String STR_SVC_CTAC_SEP = " at ";

    /** Format YYYYMMDD */
    public static final String FMT_YYYYMMDD = "YYYYMMDD";

    /** Format MM/DD/YYYY */
    public static final String FMT_MMDDYYYY = "MM/DD/YYYY";

    /** Format Month dd, yyyy */
    public static final String FMT_MONTH_DD_YYYY = "Month dd, yyyy";

    /** Format NLS_DATE_LANGUAGE=ENGLISH */
    public static final String FMT_NLS_DATE_LANGUAGE = "NLS_DATE_LANGUAGE=ENGLISH";

    /** Format Amount */
    public static final String FMT_AMT = "FM999,999,999,990.00";

    // START 2017/06/09 J.Kim [QC#18413,ADD]
    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;
    // END 2017/06/09 J.Kim [QC#18413,ADD]

    //--------------
    // Message ID
    //--------------
    /** Global Company Code is a mandatory field. */
    public static final String NFZM0001E = "NFZM0001E";
    /** Sales Date is a mandatory field. */
    public static final String NFZM0002E = "NFZM0002E";

    /** AR Statement Date is a mandatory field. */
    public static final String NFZM0003E = "NFZM0003E";

    /** Please enter Bill To Customer Account Code or Bill To Customer Code. */
    public static final String NFZM0004E = "NFZM0004E";

    /** Sales Date is an invalid date. */
    public static final String NFZM0005E = "NFZM0005E";

    /** AR Statement Date is an invalid date. */
    public static final String NFZM0006E = "NFZM0006E";

    /** It does not exist in AR Statement Control Issued Cycle. */
    public static final String NFZM0010E = "NFZM0010E";

    /** It failed to register. */
    public static final String NFZM0013E = "NFZM0013E";

    /** It does not exist in Global Company. */
    public static final String NFZM0014E = "NFZM0014E";

    /** It does not exist in Currency. */
    public static final String NFZM0015E = "NFZM0015E";

    /** It does not exist in AR Statement Control. */
    public static final String NFZM0016E = "NFZM0016E";

    /** Process Mode is a mandatory field. */
    public static final String NFZM0017E = "NFZM0017E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    // [QC#5570] INSERT start
    /** Target Data for Statement does not exist. */
    public static final String NFCM0831E = "NFCM0831E";
    // [QC#5570] INSERT end
    /** Could not get the "From Address". */
    public static final String NFZM0018E = "NFZM0018E";
    /** Could not get the "To Address". */
    public static final String NFZM0019E = "NFZM0019E";
    /** Could not get the "Mail Template". */
    public static final String NFZM0020E = "NFZM0020E";

    // START 2018/05/15 [QC#24329,ADD]
    /** Too many search results.  Please narrow your search criteria and retry. */
    public static final String NZZM0007E = "NZZM0007E";
    // END   2018/05/15 [QC#24329,ADD]

    //--------------
    // Report
    //--------------
    /** Statement Report ID */
    public static final String STMT_RPT_ID = "NFCF0010";

    /** Statement Report Branch Number for Print */
    public static final String STMT_RPT_BR_NUM_FOR_PRT = "01";
    /** Statement Report Branch Number for e-Mail */
    public static final String STMT_RPT_BR_NUM_FOR_EML = "02";

    //--------------
    // e-Mail
    //--------------
    /** Statement Report Mail Template ID */
    public static final String ML_TMPL_ID = "NFCB2030M001";
    /** Statement Report Default To address key */
    public static final String ML_FROM_ADDR_KEY = "CLT_DEF_EML_ADDR_FOR_STMT";

    // [QC#23382] INSERT start
    //--------------
    // type
    //--------------
    /** TYPE_CREDIT_MEMO */
    public static final String TYPE_CREDIT_MEMO = "CR";
    /** TYPE_PAYMENT */
    public static final String TYPE_PAYMENT = "RC";
    /** TYPE_ON_ACCOUNT */
    public static final String TYPE_ON_ACCOUNT = "AC";
    /** TYPE_CASH */
    public static final String TYPE_CASH = "CS";
    /** TYPE_ADJUSTMENT */
    public static final String TYPE_ADJUSTMENT = "AD";
    // [QC#23382-1] INSERT start
    /** TYPE_INVOICE */
    public static final String TYPE_INVOICE = "IN";
    // [QC#23382-1] INSERT End
    /** type cut size */
    public static final int CUT_SIZE = 2;
    // [QC#23382] INSERT END

    // START 2018/07/11 [QC#27002, ADD]
    /** EXTENSION_PDF */
    public static final String EXTENSION_PDF = ".pdf";
    // END   2018/07/11 [QC#27002, ADD]
}
