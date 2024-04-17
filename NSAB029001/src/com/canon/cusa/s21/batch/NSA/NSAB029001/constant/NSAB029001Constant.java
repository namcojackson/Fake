/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB029001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * CFS Invoice OM Link
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Hitachi         T.Tsuchida      Created         N/A
 * 2016/07/27   Hitachi         Y.Tsuchimoto    Update          QC#10227
 * 2017/09/04   Hitachi         K.Kim           Update          QC#20895
 * 2018/02/13   Hitachi         Y.Takeno        Update          QC#21872
 * </pre>
 */
public class NSAB029001Constant {

    /*****************************************************************
     * Other
     ****************************************************************/

    /**
     * MAX_COMMIT_NUMBER:1000
     */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /*****************************************************************
     * Error Message
     ****************************************************************/

    /**
     * [@] is not found.
     */
    public static final String ZZZM9006E = "ZZZM9006E";

    /**
     * [@] is mandatory.
     */
    public static final String ZZZM9025E = "ZZZM9025E";

    /**
     * An error occurred in API.  <APIID: [@], Error Code: [@], Data Key: [@]> 
     */
    public static final String NSAM0003E = "NSAM0003E";

    /**
     * Failed to update @ table. [@]
     */
    public static final String NSAM0013E = "NSAM0013E";

    /*****************************************************************
     * Message Parameter
     ****************************************************************/

    /**
     * message Item: GlobalCompanyCode
     */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /**
     * message Item: GlobalCompanyCode
     */
    public static final String MSG_ITEM_SLS_DT = "Sales Date";

    /**
     * message Item: Currency Code
     */
    public static final String MSG_ITEM_CCY = "Currency Code";

    /*****************************************************************
     * VarChar Constant
     ****************************************************************/

    /**
     * VarChar Constant Key: DS_INV_TP_CFS_CPC_REBILL
     */
    public static final String DS_INV_TP_CFS_CPC_REBILL = "DS_INV_TP_CFS_CPC_REBILL";

    /**
     * VarChar Constant Key: DS_INV_TP_CFS_CPC_CREDIT
     */
    public static final String DS_INV_TP_CFS_CPC_CREDIT = "DS_INV_TP_CFS_CPC_CREDIT";

    /**
     * VarChar Constant Key: SPLY_CD
     */
    public static final String SPLY_CD = "SPLY_CD";

    /**
     * VarChar Constant Key: SPCL_FLT_MDSE_CD
     */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * VarChar Constant Key: FTR_SVC_DUMMY_MDSE_CD
     */
    public static final String FTR_SVC_DUMMY_MDSE_CD = "FTR_SVC_DUMMY_MDSE_CD";

    /**
     * VarChar Constant Key: CFS_BLLG_TP_TXT
     */
    public static final String CFS_BLLG_TP_TXT = "CFS_BLLG_TP_TXT";

    /**
     * VarChar Constant Key: CFS_BLLG_MTR_LB_NM
     */
    public static final String CFS_BLLG_MTR_LB_NM = "CFS_BLLG_MTR_LB_NM";

    /**
     * VarChar Constant Key: NSAB0290_DS_ORD_TP_CD
     */
    public static final String NSAB0290_DS_ORD_TP_CD = "NSAB0290_DS_ORD_TP_CD";

    /**
     * VarChar Constant Key: NSAB0290_DS_ORD_RSN_CD
     */
    public static final String NSAB0290_DS_ORD_RSN_CD = "NSAB0290_DS_ORD_RSN_CD";

    /**
     * VarChar Constant Key: NSAB0290_TRX_SRC_TP_CD
     */
    public static final String NSAB0290_TRX_SRC_TP_CD = "NSAB0290_TRX_SRC_TP_CD";

    /**
     * VarChar Constant Key: NSAB0290_SYS_SRC_CD
     */
    public static final String NSAB0290_SYS_SRC_CD = "NSAB0290_SYS_SRC_CD";

    // START 2017/09/04 K.Kim [QC#20895, DEL]
//    /**
//     * VarChar Constant Key: NSAB0290_PMT_TERM_CD
//     */
//    public static final String NSAB0290_PMT_TERM_CD = "NSAB0290_PMT_TERM_CD";
    // END 2017/09/04 K.Kim [QC#20895, DEL]

    /**
     * VarChar Constant Key: NSAB0290_TRX_RSN_CD
     */
    public static final String NSAB0290_TRX_RSN_CD = "NSAB0290_TRX_RSN_CD";

    // START 2016/11/21 [QC#14957, ADD]
    /**
     * VarChar Constant Key: CANON_E22_CFS_INFO_ACCT
     */
    public static final String CANON_E22_CFS_INFO_ACCT = "CANON_E22_CFS_INFO_ACCT";
    // END   2016/11/21 [QC#14957, ADD]

    // START 2017/09/04 K.Kim [QC#20895, ADD]
    /**
     * VarChar Constant Key: NSAB0290_PMT_TERM_01
     */
    public static final String NSAB0290_PMT_TERM_01 = "NSAB0290_PMT_TERM_01";

    /**
     * VarChar Constant Key: NSAB0290_PMT_TERM_02
     */
    public static final String NSAB0290_PMT_TERM_02 = "NSAB0290_PMT_TERM_02";
    // END 2017/09/04 K.Kim [QC#20895, ADD]

    /*****************************************************************
     * Other Constant
     ****************************************************************/

    /**
     * DIV_COMMA : ,
     */
    public static final String DIV_COMMA = ",";

    /**
     * PARAM_00001 : 00001
     */
    public static final String PARAM_00001 = "00001";

    /**
     * PARAM_001 : 001
     */
    public static final String PARAM_001 = "001";

    /**
     * PARAM_100 : 100
     */
    public static final int PARAM_100 = 100;

    /**
     * BASE
     */
    public static final String BASE = "BASE";

    /**
     * USAGE
     */
    public static final String USAGE = "USAGE";

    /**
     * MSG_KIND_ERROR
     */
    public static final String MSG_KIND_ERROR = "E";

    /**
     * default invoice Print Status
     */
    public static final String INV_PRINT_STS_DEF = "1";

    /**
     * default invoice Send Status
     */
    public static final String INV_SEND_STS_DEF = "0";

    // START 2018/02/13 [QC#21872, ADD]
    /**
     * DS_CONTR_MACH_LVL_NUM : 1
     */
    public static final String DS_CONTR_MACH_LVL_NUM_1 = "1";

    /**
     * DS_CONTR_MACH_LVL_NUM : 2
     */
    public static final String DS_CONTR_MACH_LVL_NUM_2 = "2";

    /**
     * DS_CONTR_MACH_LVL_NUM : 3
     */
    public static final String DS_CONTR_MACH_LVL_NUM_3 = "3";
    // END   2018/02/13 [QC#21872, ADD]

    /**
     * BigDecimal 100 for percent
     */
    public static final BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);
}
