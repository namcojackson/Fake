/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2300.constant;

import java.math.BigDecimal;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;

/**
 *<pre>
 * NWAL2300Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2016/10/24   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2017/01/31   Fujitsu         H.Nagashima     Update          QC#15624
 * 2017/09/25   Fujitsu         S.Iidaka        Update          QC#20511
 * 2017/10/31   Fujitsu         Y.Kanefusa      Update          S21_NA#22031
 * 2018/08/03   Fujitsu         M.Ishii         Update          S21_NA#27481
 *</pre>
 */
public class NWAL2300Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2300";

    /** Authority Update CD */
    public static final String AUTHORITY_UPDATE_CD = "NWAL2300T020";

    /** Authority NWAL2300T010 */
    public static final String AUTHORITY_NWAL2300T010 = "0";

    /** Authority NWAL2300T020 */
    public static final String AUTHORITY_NWAL2300T020 = "1";

    /** Varchar Const(NWAL2300_INELIG_STS) */
    public static final String VARCHAR_CONST_SHPG_STS_CD = "NWAL2300_INELIG_STS";

    /** Varchar Const(PKG_UOM_FOR_PRC) */
    public static final String VARCHAR_CONST_PKG_UOM_FOR_PRC = "PKG_UOM_FOR_PRC";

    /** Varchar Const(NWAL2300_RB_INTERNAL) */
    public static final String VARCHAR_CONST_NWAL2300_RB_INTERNAL = "NWAL2300_RB_INTERNAL";

    /** Varchar Const(NWAL2300_PMT_TERM_CC_CR) */
    public static final String VARCHAR_CONST_NWAL2300_PMT_TERM_CC_CR = "CR_REBIL_CR_ORD_PMT_TERM_CC";

    /** Varchar Const(NWAL2300_PMT_TERM_CC_RB) */
    public static final String VARCHAR_CONST_NWAL2300_PMT_TERM_CC_RB = "CR_REBIL_REBIL_ORD_PMT_TERM_CC";

    /** Varchar Const(NWAL2300_PMT_TERM_EX_CC_CR) */
    public static final String VARCHAR_CONST_NWAL2300_PMT_TERM_EX_CC_CR = "CR_REBIL_CR_ORD_PMT_TERM_EX_CC";

    /** Varchar Const(CSMP_CLAIM_DS_INV_TP_CD) */
    public static final String VARCHAR_CONST_CSMP_CLAIM_DS_INV_TP_CD = "CSMP_CLAIM_DS_INV_TP_CD";

    /** Varchar Const(DROP_SHIP_RTL_WH_CD) */
    public static final String VARCHAR_CONST_DROP_SHIP_RTL_WH_CD = "DROP_SHIP_RTL_WH_CD";

    /** Varchar Const(NWAL2300_EXCEPT_RMA_WH_CD) */
    public static final String VARCHAR_CONST_NWAL2300_EXCEPT_RMA_WH_CD = "NWAL2300_EXCEPT_RMA_WH_CD";

    /** Comment Max Size */
    public static final int COMMENT_MAX_SIZE = 260;

    /** Radio button def */
    public static final BigDecimal RADIO_BUTTON_DEF = BigDecimal.ONE;

    // 2018/04/06 QC#22122 Add Start
    /** Radio button Order */
    public static final BigDecimal RADIO_BUTTON_ORD = BigDecimal.ONE;

    /** Radio button Invoice */
    public static final BigDecimal RADIO_BUTTON_INV = BigDecimal.valueOf(2);
    // 2018/04/06 QC#22122 Add End

    /** status Open */
    public static final String STS_OPEN = "Open";

    /** status Closed */
    public static final String STS_CLOSED = "Closed";

    /** Rec Type Invoice */
    public static final String REC_TYPE_INVOICE = "01";

    /** Rec Type Order */
    public static final String REC_TYPE_ORDER = "02";

    /** Rec Type RMA */
    public static final String REC_TYPE_RMA = "03";

    /** PullDown Type CD(C&R) */
    public static final String PULLDOWN_CD_C_R = "1";

    /** PullDown Type CD(RMA) */
    public static final String PULLDOWN_CD_RMA_RB = "2";

    /** PullDown Text(C&R) */
    public static final String PULLDOWN_TXT_C_R = "C/R";

    /** PullDown Text(RMA) */
    public static final String PULLDOWN_TXT_RMA_RB = "RMA w/C";

    /** Work Flag Type Yes */
    public static final String FLG_TYPE_Y = "Yes";

    /** Work Flag Type No */
    public static final String FLG_TYPE_N = "No";

    /** mode(save) */
    public static final String MODE_SAVE = NWZC150001Constant.MODE_SAVE;

    /** mode(submit) */
    public static final String MODE_SUBMIT = NWZC150001Constant.MODE_SUBMIT;

    /** mode(cancel) */
    public static final String MODE_CANCEL = NWZC150001Constant.MODE_CANCEL;

    /** mode new */
    public static final String RQST_TP_CONFIG_NEW = NWZC150001Constant.RQST_TP_CONFIG_NEW;

    /** mode modify */
    public static final String RQST_TP_CONFIG_MODIFY = NWZC150001Constant.RQST_TP_CONFIG_MODIFY;

    /** request(new) */
    public static final String RQST_TP_DTL_NEW = NWZC150001Constant.RQST_TP_DTL_NEW;

    /** request(save) */
    public static final String RQST_TP_DTL_CANCEL = NWZC150001Constant.RQST_TP_DTL_CANCEL;

    /** request(modify) */
    public static final String RQST_TP_DTL_MODIFY = NWZC150001Constant.RQST_TP_DTL_MODIFY;

    /** request(modify) */
    public static final String RQST_TP_SLS_CR_NEW = NWZC150001Constant.RQST_TP_SLS_CR_NEW;

    /** request(new) */
    public static final String RQST_TP_SLS_CR_MODIFY = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;

    /** shipFromInvtylocCdCR */
    public static final String SHIP_FROM_INVTY_LOC_CD_CR = "CR";

    /** Reason CD(I) */
    public static final String REASON_CD_I = "I";

    //QC#15624 add Start
    /** default Line Sub Number */
    public static final String DEF_LINE_SUB_NUM = "001";
    //QC#15624 add End

    // --------------------------------
    // Message ID
    // --------------------------------
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** The order # @ selected for Credit & Rebill has been processed to Credit / Rebill. */
    public static final String NWAM8453W = "NWAM8453W";

    /**
     * @ selected for Credit / Rebill excluded. Please choose other
     * order.
     */
    public static final String NWAM0842E = "NWAM0842E";

    /**
     * @ selected will contain the Credit Order. Please choose other
     * order.
     */
    public static final String NWAM0843E = "NWAM0843E";

    /** @ selected is all Return order. Please choose other order. */
    public static final String NWAM0844E = "NWAM0844E";

    /**
     * @ selected for Credit & Rebill has the line(s) which has RWS.
     * Please choose other order.
     */
    public static final String NWAM0845E = "NWAM0845E";

    /**
     * @ selected for Credit & Rebill has the line(s) which is (are)
     * allocated or S/O printed. Please chose other order.
     */
    public static final String NWAM0846E = "NWAM0846E";

    /**
     * @ selected for Credit & Rebill had not yet invoiced. Unable to
     * proceed with C/R.
     */
    public static final String NWAM0847E = "NWAM0847E";

    /**
     * @ selected for Credit & Rebill has already submitted for Credit
     * & Rebill. Unable to Process with C/R.
     */
    public static final String NWAM0848E = "NWAM0848E";

    /**
     * If you select action "RMA" on main machine, other line (s) in
     * the same configuration should be "RMA".
     */
    public static final String NWAM0849E = "NWAM0849E";

    /**
     * @ selected for Credit & Rebill contains open lines. These lines
     * will be automatically cancelled and created on the Rebill.
     */
    public static final String NWAM0850W = "NWAM0850W";

    /**
     * There are some open RMA line. If you would like to cancel open
     * RMA, please check "Cancel Open RMA".
     */
    public static final String NWAM0851W = "NWAM0851W";

    // 2018/08/03 QC#27481 Del Start
//    /** @ selected for Credit & Rebill will be processed, is it OK? */
//    public static final String NWAM0852W = "NWAM0852W";
    // 2018/08/03 QC#27481 Del End

    // 2018/08/03 QC#27481 Add Start
    /** Are you sure to submit the form? */
    public static final String NWAM0961W = "NWAM0961W";
    // 2018/08/03 QC#27481 Add End

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to insert the @. */
    public static final String NWAM0790E = "NWAM0790E";

    /** It does not exist in {@} table. */
    public static final String NWAM0809E = "NWAM0809E";

    /** @ selected for Credit & Rebill has the line(s) which is (are) not required Invoice Final. Please chose other order. */
    public static final String NWAM0866E = "NWAM0866E";

    /** Cannot specify RMA w/C using BILL ONLY WAREHOUSE.*/
    public static final String NWAM0873E = "NWAM0873E";

    /** The TOC for Sales Rep has been terminated.*/
    public static final String NWAM0905E = "NWAM0905E";

    /** Freight Condition Code can't be populated from both invoice and original order.*/
    public static final String NWAM0944E = "NWAM0944E";

    /** Period */
    public static final String PERIOD = ".";

    /** Usage Line Type : Parent */
    public static final String XX_FLG_PARENT = "P";

    /** Usage Line Type : Tier */
    public static final String XX_FLG_TIER = "T";

    /** Usage Line Type : Hard Counter */
    public static final String XX_FLG_HARD = "H";

    // QC#22031 2017/10/31 Add Start
    public static final String DROP_SHIP_WH = "DS";
    // QC#22031 2017/10/31 Add End

    /** Line Separator */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
}
