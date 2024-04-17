/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_COLOR_TP;

/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/03/15   Hitachi         A.Kohinata      Update          QC#5375
 * 2016/03/23   Hitachi         A.Kohinata      Update          QC#5730
 * 2016/04/05   Hitachi         K.Kasai         Update          QC#6583
 * 2016/07/12   Hitachi         K.Kasai         Update          QC#9884
 * 2016/09/21   Hitachi         A.Kohinata      Update          QC#13267
 * 2016/10/04   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15344
 * 2016/10/20   Hitachi         A.Kohinata      Update          QC#15323
 * 2016/10/25   Hitachi         A.Kohinata      Update          QC#15338
 * 2017/05/09   Hitachi         K.Kitachi       Update          QC#18277
 * 2017/08/02   Hitachi         K.Kim           Update          QC#18311
 * 2018/02/14   Hitachi         U.Kim           Update          QC#20297(Sol#379)
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/04/26   Hitachi         U.Kim           Update          QC#22293
 * 2018/05/25   CITS            M.Naito         Update          QC#15410
 * 2018/06/01   Fujitsu         Nagashima       Update          QC#25966
 * 2018/06/01   Fujitsu         M.Ohno          Update          QC#26273
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26978
 * 2018/08/24   CITS            T.Kikuhara      Update          QC#27853
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2019/06/19   Hitachi         K.Kim           Update          QC#50879
 * 2020/02/10   Hitachi         K.Kim           Update          QC#55795
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/07/14   CITS            T.Kimura        Update          QC#61570
 * 2024/03/07   Hitachi         K.Kishimoto     Update          QC#63547
 *</pre>
 */
public final class NSAL0990Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSAL0990";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * Please execute again after correcting the error field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    /**
     * Email  format is incorrect. 
     */
    public static final String NMAM8347E = "NMAM8347E";
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    /**
     * [@] does not exist in Master.
     */
    public static final String NSAM0011E = "NSAM0011E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * "@" does not exist in master.
     */
    public static final String NSAM0037E = "NSAM0037E";

    /**
     * @ is invalid.
     */
    public static final String NSAM0040E = "NSAM0040E";

    // START 2017/05/09 K.Kitachi [QC#18277, ADD]
    /**
     * Cannot add anymore details.
     */
    public static final String NSAM0112E = "NSAM0112E";
    // END 2017/05/09 K.Kitachi [QC#18277, ADD]

    /**
     * Relation between "@" and "@" is not correct.
     */
    public static final String NSAM0138E = "NSAM0138E";

    /**
     * This item is not available for supply order.
     */
    public static final String NSAM0413E = "NSAM0413E";

    /**
     * Please select credit card again.
     */
    public static final String NSAM0420E = "NSAM0420E";

    /**
     * It will exceed the number of [@] caps. Cap:[@] Running:[@].
     */
    public static final String NSAM0421E = "NSAM0421E";

    /**
     * In the table [@], there is no data corresponding to [@].
     */
    public static final String NSAM0440E = "NSAM0440E";

    // add start 2016/09/21 CSA Defect#13267
    /**
     * Ordered Qty is all zero.
     */
    public static final String NSAM0608E = "NSAM0608E";
    // add end 2016/09/21 CSA Defect#13267

    // add start 2016/10/19 CSA Defect#15344
    /** If Freight Condition Code is 'Collect', Carrier Service Level must be entered. */
    public static final String NSAM0614E = "NSAM0614E";
    // add end 2016/09/21 CSA Defect#15344

    // add start 2016/10/20 CSA Defect#15323
    /** [@] cannot be obtained. */
    public static final String NSAM0179E = "NSAM0179E";
    // add end 2016/10/20 CSA Defect#15323

    // add start 2016/10/20 CSA Defect#15323
    /** [@] is mandatory when [@] is "@".  */
    public static final String NSAM0442E = "NSAM0442E";
    // add end 2016/10/20 CSA Defect#15323

    // START 2017/05/09 K.Kitachi [QC#18277, ADD]
    /**
     * More than [@] search results found in [@].
     */
    public static final String NSAM0684W = "NSAM0684W";
    // END 2017/05/09 K.Kitachi [QC#18277, ADD]

    // 2018/06/01 QC#26273 add start
    /** Order qty should be equal to minimum qty or more.(Mimimum Qty=[@]) */
    public static final String NWZM2023E = "NWZM2023E";

    /** Order qty should be equal to maximum qty or less.(Maximum Qty=[@]) */
    public static final String NWZM2024E = "NWZM2024E";

    /** Order qty should be multiple of increment qty.(Increment Qty=[@]) */
    public static final String NWZM2025E = "NWZM2025E";
    // 2018/06/01 QC#26273 add end

    // add start 2019/01/21 QC#27304
    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** "@" does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** Fail to get price. */
    public static final String NSAM0745W = "NSAM0745W";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";
    // add end 2019/01/21 QC#27304

    // START 2019/06/19 [QC#50879, ADD]
    /** Shipping Instructions length can't exceed @ characters. */
    public static final String NSAM0749E = "NSAM0749E";
    // END 2019/06/19 [QC#50879, ADD]

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /** The format of [@] is incorrect. */
    public static final String NSAM0206E = "NSAM0206E";
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

    // START 2024/03/07 [QC#63547, ADD]
    /** Please select at least one item to order. */
    public static final String NSAM0785E = "NSAM0785E";
    // END   2024/03/07 [QC#63547, ADD]

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * Period1:30Days
     */
    public static final String PERIOD_VAL_1 = "30Days";

    /**
     * Period1:60Days
     */
    public static final String PERIOD_VAL_2 = "60Days";

    /**
     * Period3:90Days
     */
    public static final String PERIOD_VAL_3 = "90Days";

    /**
     * Period1:30
     */
    public static final String PERIOD_1 = "30";

    /**
     * Period2:60
     */
    public static final String PERIOD_2 = "60";

    /**
     * Period3:90
     */
    public static final String PERIOD_3 = "90";

    /**
     * Var Char Const:TERM_COND_ATTRB_NM_TONER_TP
     */
    public static final String VAR_CHAR_TONER_TP = "TERM_COND_ATTRB_NM_TONER_TP";

    /**
     * Var Char Const:TERM_COND_ATTRB_NM_STAPLE_INCL
     */
    public static final String VAR_CHAR_STAPLE_INCL = "TERM_COND_ATTRB_NM_STAPLE_INCL";

    /**
     * Var Char TERM_COND_ATTRB_NM_TONER_YIELD
     */
    public static final String VAR_CHAR_TONER_YIELD = "TERM_COND_ATTRB_NM_TONER_YIELD";

    /**
     * Var Char TERM_COND_CAP_BW_ORG_ATTRB_NM
     */
    public static final String VAR_CHAR_TONER_CAP_BW_ORG = "TERM_COND_CAP_BW_ORG_ATTRB_NM";

    /**
     * Var Char TERM_COND_CAP_CLR_ORG_ATTRB_NM
     */
    public static final String VAR_CHAR_TONER_CAP_CLR_ORG = "TERM_COND_CAP_CLR_ORG_ATTRB_NM";

    /**
     * Var Char TERM_COND_CAP_TOT_ORG_ATTRB_NM
     */
    public static final String VAR_CHAR_TONER_CAP_TOT_ORG = "TERM_COND_CAP_TOT_ORG_ATTRB_NM";

    /**
     * Var Char TERM_COND_CAP_BW_RUN_ATTRB_NM
     */
    public static final String VAR_CHAR_TONER_CAP_BW_RUN = "TERM_COND_CAP_BW_RUN_ATTRB_NM";

    /**
     * Var Char TERM_COND_CAP_CLR_RUN_ATTRB_NM
     */
    public static final String VAR_CHAR_TONER_CAP_CLR_RUN = "TERM_COND_CAP_CLR_RUN_ATTRB_NM";

    /**
     * Var Char TERM_COND_CAP_TOT_RUN_ATTRB_NM
     */
    public static final String VAR_CHAR_TONER_CAP_TOT_RUN = "TERM_COND_CAP_TOT_RUN_ATTRB_NM";

    /**
     * Var Char PKG_UOM_FOR_PRC
     */
    public static final String PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    // add start 2016/10/04 CSA Defect#12898
    /**
     * Var Char SPCL_FLT_MDSE_CD
     */
    public static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";
    // add end 2016/10/04 CSA Defect#12898

    // add start 2016/10/19 CSA Defect#15344
    /** Var Char CLT_FRT_COND */
    public static final String CLT_FRT_COND = "CLT_FRT_COND";
    // add end 2016/09/21 CSA Defect#15344

    // START 2018/06/01 H.Nagashima [QC#25966, ADD]
    /** Var Char Const NSAL0990_TRGT_IMG_SPLY_TP */
    public static final String CONST_TRGT_IMG_SPLY_TP = "NSAL0990_TRGT_IMG_SPLY_TP";
    // END 2018/06/01 H.Nagashima [QC#25966, ADD]

    /**
     * NMZC6100 Parameter:bill to cust check
     */
    public static final String BILL_TO = "billTo";

    /**
     * NMZC6100 Parameter:ship to cust check
     */
    public static final String SHIP_TO = "shipTo";

    /**
     * Screen Mode: SplyOrdMode
     */
    public static final String MODE_1 = "SplyOrdMode";

    /**
     * Screen Mode: SplyOrdEditMode
     */
    public static final String MODE_2 = "SplyOrdEditMode";

    /**
     * Default Data: CPO Detail Line Sub Number
     */
    public static final String DEF_LINE_SUB_NUM = "001";

    /**
     * Default Data: 100 Percent
     */
    public static final BigDecimal PCT_100 = new BigDecimal("100");

    /**
     * Number Three: 3
     */
    public static final int NUM_THREE = 3;

    /**
     * Number Four: 4
     */
    public static final int NUM_FOUR = 4;

    /**
     * Number Six: 6
     */
    public static final int NUM_SIX = 6;

    /**
     * Number Eight: 8
     */
    public static final int NUM_EIGHT = 8;

    /**
     * DS_ACPO_NUM_SEQ
     */
    public static final String DS_ACPO_NUM_SEQ = "QC#";
    // add start 2016/04/05 CSA Defect#6583
    // START 2018/09/10 K.Kitachi [QC#26260, DEL]
//    /**
//     * DEF_LINE_BIZ_CD
//     */
//    public static final String DEF_LINE_BIZ_CD = "ALL";
    // END 2018/09/10 K.Kitachi [QC#26260, DEL]
    // add end 2016/04/05 CSA Defect#6583
    // add start 2016/07/12 CSA Defect#9884
    /**
     * SPACE
     */
    public static final String SPACE = " ";

    // START 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]
    /**
     * BLANK
     */
    public static final String BLANK = "";

    /**
     * EQUIPMENT_ORDER_VALUE_SET
     */
    public static final String EQUIPMENT_ORDER_VALUE_SET = "EQUIPMENT_ORDER";

    /**
     * EQUIPMENT_ORDER_VALUE_SET
     */
    public static final String SUPPLIES_ORDER_VALUE_SET = "SUPPLIES_ORDER";

    /** New Line */
    public static final String NEW_LINE = "\r\n";

    // START 2019/06/19 [QC#50879, MOD]
    // /** Shipping Comment Limit Size*/
    // public static final int SHPG_CMT_TXT_LIMIT_SIZE = 260;

    /** NUM_CONST Key : SHPG_CMNT_TXT_LIMIT_SIZE */
    public static final String SHPG_CMNT_TXT_LIMIT_SIZE = "SHPG_CMNT_TXT_LIMIT_SIZE";
    // END 2019/06/19 [QC#50879, MOD]

    // END 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]

    // START 2018/04/26 U.Kim [QC#22293, ADD]
    /**
     * HYPHEN
     */
    public static final String HYPHEN = "-";

    /**
     * shphLbl:end index
     */
    public static final int SHPG_LBL_START_IDX = 0;

    /**
     * shphLbl:end index
     */
    public static final int SHPG_LBL_END_IDX = 90;

    // START 2018/04/26 U.Kim [QC#22293, ADD]

    /**
     * delyAddlCmntTxt:start index
     */
    public static final int DELY_CMNT_START_IDX = 0;
    /**
     * delyAddlCmntTxt:end index
     */
    public static final int DELY_CMNT_END_IDX = 240;
    // add end 2016/07/12 CSA Defect#9884

    // add start 2016/10/04 CSA Defect#12898
    /** IMG_SPLY_COLOR_TP_LIST */
    public static final String[] IMG_SPLY_COLOR_TP_LIST = {IMG_SPLY_COLOR_TP.BLACK, IMG_SPLY_COLOR_TP.YELLOW, IMG_SPLY_COLOR_TP.MAGENTA, IMG_SPLY_COLOR_TP.CYAN };

    /** MDL_MTR_TP_CD : BLACK AND WHITE */
    public static final String MDL_MTR_TP_BW = "1";

    /** MDL_MTR_TP_CD : COLOR */
    public static final String MDL_MTR_TP_CL = "2";

    /** for Calculate ADCV_CNT */
    public static final int GET_BLLG_SCHD_NUM = 4;

    /** One Hundred 100 */
    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    /** coefficient : BLACK AND WHITE */
    public static final BigDecimal COEFFICIENT_BW = BigDecimal.valueOf(1);

    /** coefficient : COLOR */
    public static final BigDecimal COEFFICIENT_CL = BigDecimal.valueOf(3);
    // add end 2016/10/04 CSA Defect#12898

    // START 2017/08/02 K.Kim [QC#18311, ADD]
    /** Attach Business Id */
    public static final String ATTACH_BUSINESS_ID = "NWAL1500";

    /** Attach Business Name */
    public static final String ATTACH_BUSINESS_NM = "Supply Order Entry";
    // END 2017/08/02 K.Kim [QC#18311, ADD]

    // START 2018/05/25 M.Naito [QC#15410, ADD]
    /** SVC_TERM_COND_DATA_DISP_TXT : Yes */
    public static final String SVC_TERM_COND_DATA_YES = "Yes";
    // END 2018/05/25 M.Naito [QC#15410, ADD]

    // START 2018/07/19 K.Kitachi [QC#26978, ADD]
    /** Suffix : Fleet Serial Number */
    public static final String SFX_FLT_SER_NUM = "FLT_";
    // END 2018/07/19 K.Kitachi [QC#26978, ADD]

    // add start 2019/01/21 QC#27304
    /** FULL_PSN_NM:end index  */
    public static final int FULL_PSN_NM_END_IDX = 62;

    /** DELETE MODE:LOGICAL  */
    public static final int DEL_MODE_LOGICAL = 1;

    /** DELETE MODE: PHYSICAL  */
    public static final int DEL_MODE_PHYSICAL = 2;
    // add end 2019/01/21 QC#27304

    // START 2020/02/10 [QC#55795, ADD]
    /** Total Profit Limit Percent(999.99 Percent) */
    public static final BigDecimal PCT_TOTAL_PROFIT_LIMIT = BigDecimal.valueOf(999.99);
    // END 2020/02/10 [QC#55795, ADD]
    
    // START 2023/07/14 T.Kimura  [QC#61570, ADD]
    /** Max End Date **/
    public static final String MAX_EFF_THRU_DT = "99991231";
    // END 2023/07/14 T.Kimura  [QC#61570, ADD]
}
