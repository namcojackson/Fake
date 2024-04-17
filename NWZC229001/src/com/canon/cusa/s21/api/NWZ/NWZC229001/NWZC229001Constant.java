/**
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC229001;

import java.math.BigDecimal;

/**
 * <pre>
 *  eCheck Interface API.
 * This class defines the constant used in the api application
 * program of BusinessID NWZC229001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2023   Hitachi         M.Hashino       Create          QC#55645
 * 11/17/2023   Hitachi         Y.Ogura         Update          QC#62010
 * </pre>
 */
public class NWZC229001Constant {

    // -- Internal constants --------------
    /** Program ID */
    public static final String PROGRAM_ID = "NWZC229001:";

    /** Maximum number of digits in the integer part of echkPmtAmt */
    public static final Integer INTEGER_PART_DIGIT_13 = 13;

    /** Maximum number of digits in the fractional part of echkPmtAmt */
    public static final Integer FRAC_PART_DIGIT_2 = 2;

    /** Maximum length of Telephone Number */
    public static final int TELL_NUM_MAX_LENGTH = 10;

    /** Maximum length of e-Mail Address */
    public static final int EML_ADDR_MAX_LENGTH = 30;

    // -- MSTR_DEF_INFO
    /** MSTR_DEF_INFO : MSTR_FUNC_ID ("NWZC2290_eCheck Interface API") */
    public static final String MSTR_FUNC_ID = "NWZC229001_eCheck Interface API";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Version) */
    public static final String MSTR_COL_ID_VRSN = "ECHK_VRSN";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Industry Type) */
    public static final String MSTR_COL_ID_INDY = "ECHK_INDY_TP";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Transaction Type) */
    public static final String MSTR_COL_ID_TRX = "ECHK_TRX_TP";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Bin) */
    public static final String MSTR_COL_ID_BIN = "ECHK_BIN";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Merchant ID) */
    public static final String MSTR_COL_ID_MRCNT = "ECHK_MRCNT_ID";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Terminal ID) */
    public static final String MSTR_COL_ID_TRM = "ECHK_TRM_ID";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Card Brand) */
    public static final String MSTR_COL_ID_CARD_BRAND = "ECHK_CARD_BRAND";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Card Person Indicator) */
    public static final String MSTR_COL_ID_CARD_PRSN = "ECHK_CARD_PRSN_IND";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Bank Account Type) */
    public static final String MSTR_COL_ID_BANK = "ECHK_BANK_ACCT_TP";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Authorization Method) */
    public static final String MSTR_COL_ID_AUTH = "ECHK_AUTH_METH";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Delv Method) */
    public static final String MSTR_COL_ID_DELV = "ECHK_DELV_METH";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Profile From Order) */
    public static final String MSTR_COL_ID_PRFL_FROM_ORD = "ECHK_PRFL_FROM_ORD";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Order Override Indicator ) */
    public static final String MSTR_COL_ID_ORD_OVRD = "ECHK_ORD_OVRD_IND";

    // START 2023/11/16 Y.Ogura [QC#62010, ADD]
    /** DS_COND_CONST_GRP_ID */
    public static final String NWZC2290_FILTER_TXT = "NWZC2290_FILTER_TXT";

    /** NUM_CONST_NM : LOC_NM_MAX_LEN */
    public static final String ECHK_LOC_NM_MAX_LEN = "ECHK_LOC_NM_MAX_LEN";

    /** NUM_CONST_NM : POST_CD_MAX_LEN */
    public static final String ECHK_POST_CD_MAX_LEN = "ECHK_POST_CD_MAX_LEN";
    // END 2023/11/16 Y.Ogura [QC#62010, ADD]

    /**
     * eCheck Amount Requirement : Implied decimal, including
     * those currencies that are a zero exponent. For example, both
     * $100.00 (an exponent of 2) and ¥100 (an exponent of 0) should
     * be sent as amount = 10000.
     */
    public static final BigDecimal ECHK_AMT_REQ_100 = new BigDecimal(100);

    // -- Error Message-------------------
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** The data [@] does not exist in the master. */
    public static final String NWZM2315E = "NWZM2315E";

    /** For 'Amount' data, enter 13 digits or less before the decimal,and enter 2 digits or less after the decimal. */
    public static final String NWZM2318E = "NWZM2318E";

    /** Relation between "@" and "@" is not correct. */
    public static final String NWZM2317E = "NWZM2317E";

    /** Connection has been timed out. Please do NOT submit again. Please contact IT department. */
    public static final String NWZM2314E = "NWZM2314E";

    /** It failed to PayEezy Gateway New Order Request. */
    public static final String NWZM2319E = "NWZM2319E";

    /** eCheck payment processing failed.[@] */
    public static final String NWZM2316E = "NWZM2316E";

    /** DB error occurred. */
    public static final String NWDM0007E = "NWDM0007E";

    /** Error code :  */
    public static final String ERROR_CODE_HDR ="Error code : ";
}
