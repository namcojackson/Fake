/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC105001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsgArray;
import parts.common.EZDSStringItem;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMXC105001PriceMasterUtil
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Fujitsu         W.Honda         Create          N/A
 * 2017/08/09   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2017/08/15   Fujitsu         K.Ishizuka      Create          QC#18237(L3#161)
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2017/08/31   Fujitsu         R.Nakamura      Update          QC#20729-2
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569
 *</pre>
 */
public class NMXC105001PriceMasterUtil extends S21SsmEZDQuerySupport {

    /** Message : NMAM8186E */
    private static final String NMAM8186E = "NMAM8186E";

    /** Message : ZZM9001E */
    private static final String ZZM9001E = "ZZM9001E";

    /** Message : ZZM9004E */
    private static final String ZZM9004E = "ZZM9004E";

    /** Message : ZZM9010E */
    private static final String ZZM9010E = "ZZM9010E";

    /** Message : ZZM9015E */
    private static final String ZZM9015E = "ZZM9015E";

    /** Check Type : Master */
    private static final String CHK_TYPE_MST = "01";

    /** Check Type : Integer */
    private static final String CHK_TYPE_INT = "02";

    /** Check Type : Date */
    private static final String CHK_TYPE_DT = "03";

    /** Check Type : Decimal Amount */
    private static final String CHK_TYPE_DEC_AMT = "04";

    /** Check Type : Decimal Weight */
    private static final String CHK_TYPE_DEC_WT = "05";

    /** Check Type : Decimal Percent */
    private static final String CHK_TYPE_DEC_PCT = "06";

    /** Global Company Column (Standard) */
    private static final String GLBL_COL = "GLBL_CMPY_CD";

    /** Global Company Column (Ex. MDL_NM etc...) */
    private static final String T_GLBL_COL = "T_GLBL_CMPY_CD";

    /** String Period. */
    private static final String PERIOD = ".";

    /** String Period. */
    private static final String SPLIT_PERIOD = "\\.";

    /** Length Date:8. */
    private static final int DT_LEN = 8;

    /** Length Quantity:10. */
    private static final int QTY_LEN = 10;

    /** Length Weight:15. */
    private static final int WT_LEN = 15;

    /** Length Amount:19. */
    private static final int AMT_LEN = 19;

    /** Length Percent:5. */
    private static final int PCT_LEN = 5;

    /** Length Decimal:4. */
    private static final int DEC_AMT_LEN = 4;

    /** Length Decimal:4. */
    private static final int DEC_WT_LEN = 6;

    /** Length Percent:5. */
    private static final int DEC_PCT_LEN = 2;

    /** Common Pop-up Parameter/Where */
    private static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Where */
    private static final int CMN_PRM_COLUMN_NUM = 4;

    /** SSM Instance. */
    private static final NMXC105001PriceMasterUtil MY_INSTANCE = new NMXC105001PriceMasterUtil();

    /** String List */
    private static final String[] STRING_LIST = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    /** First PrcRuleCondGrpCd */
    private static final String FIRST_PRC_RULE_COND_GRP_CD = "A";

    /** Twenty Six Decimal */
    private static final int TWENTYSIX_DECIMAL = 26;

    /**
     * getInstance.
     * @return NMXC105001PriceMasterUtil
     */
    public static NMXC105001PriceMasterUtil getInstance() {
        return MY_INSTANCE;
    }

    /**
     * checkRuleAttrbFormat.
     * @param prcRuleAttrbCd String
     * @param val EZDCStringItem
     * @param valNm EZDCStringItem
     * @param msgArgs String
     * @return boolean
     */
    public static boolean checkRuleAttrbFormat(String prcRuleAttrbCd, EZDCStringItem val, EZDCStringItem valNm, String msgArgs) {
        boolean isSuccess = true;
        if (!ZYPCommonFunc.hasValue(val)) {
            return isSuccess;
        }

        if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DEC_WT, val, WT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DT, val, DT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DEC_AMT, val, AMT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_INT, val, AMT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DEC_AMT, val, AMT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DT, val, DT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_INT, val, QTY_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DEC_PCT, val, PCT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_DEC_AMT, val, AMT_LEN, msgArgs);
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        // QC#20249 2017/08/09 Add Start
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(prcRuleAttrbCd)) {
            isSuccess = checkFormat(CHK_TYPE_INT, val, QTY_LEN, msgArgs);
        // QC#20249 2017/08/09 Add End
        // 2018/04/19 QC#22569 add start
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd)) {
            isSuccess = getRuleAttrbNm(prcRuleAttrbCd, val, valNm, msgArgs);
        }
        // 2018/04/19 QC#22569 add end

        return isSuccess;
    }

    /**
     * getRuleAttrbNm.
     * @param prcRuleAttrbCd String
     * @param val EZDCStringItem
     * @param valNm EZDCStringItem
     * @param msgArgs String
     * @return boolean
     */
    public static boolean getRuleAttrbNm(String prcRuleAttrbCd, EZDCStringItem val, EZDCStringItem valNm, String msgArgs) {

        boolean isGet = true;
        String addWhere = "";

        if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_CH_DESC_TXT", "COA_CH", "COA_CH_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd)) {
            addWhere = "A.PRC_GRP_TP_CD = '" + PRC_GRP_TP.CUSTOMER_PRICING_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PRC_GRP_NM", "PRC_GRP", "PRC_GRP_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(prcRuleAttrbCd)) {
            addWhere = "A.DS_ACCT_TP_CD = '" + DS_ACCT_TP.CUSTOMER + "'";
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_NM", "SELL_TO_CUST", "SELL_TO_CUST_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.CSMP_NUM_CMNT_TXT", "CSMP_CONTR", "CSMP_NUM", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
            addWhere = "A.PRC_GRP_TP_CD = '" + PRC_GRP_TP.MATERIAL_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PRC_GRP_NM", "PRC_GRP", "PRC_GRP_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LINE + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_PROJ_DESC_TXT", "COA_PROJ", "COA_PROJ_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_PROD_DESC_TXT", "COA_PROD", "COA_PROD_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MDSE_NM", "ALL_MDSE_V", "MDSE_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_CATG_DESC_TXT", "DS_ORD_CATG", "DS_ORD_CATG_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_TP_DESC_TXT", "DS_ORD_TP", "DS_ORD_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.LINE_BIZ_TP_DESC_TXT", "LINE_BIZ_TP", "LINE_BIZ_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
            addWhere = "A.PRC_GRP_TP_CD = '" + PRC_GRP_TP.ORDER_CATEGORY_OR_REASON + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PRC_GRP_NM", "PRC_GRP", "PRC_GRP_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            addWhere = "A.DS_ACCT_TP_CD = '" + DS_ACCT_TP.CUSTOMER + "'";
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_NM", "SELL_TO_CUST", "SELL_TO_CUST_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_CH_NM", "COA_CH", "COA_CH_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_CLS_DESC_TXT", "DS_ACCT_CLS", "DS_ACCT_CLS_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_BR_DESC_TXT", "COA_BR", "COA_BR_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.SVC_CALL_TP_DESC_TXT", "SVC_CALL_TP", "SVC_CALL_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(prcRuleAttrbCd)) {
            addWhere = "A.PRC_GRP_TP_CD = '" + PRC_GRP_TP.CUSTOMER_PRICING_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PRC_GRP_NM", "PRC_GRP", "PRC_GRP_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_LINE_CATG_DESC_TXT", "DS_ORD_LINE_CATG", "DS_ORD_LINE_CATG_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MKT_MDL_DESC_TXT", "MKT_MDL", "MKT_MDL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MKT_MDL_SEG_DESC_TXT", "MKT_MDL_SEG", "MKT_MDL_SEG_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.CPO_SRC_TP_DESC_TXT", "CPO_SRC_TP", "CPO_SRC_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_PMT_METH_DESC_TXT", "DS_PMT_METH", "DS_PMT_METH_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.PRC_CATG_DESC_TXT", "PRC_CATG", "PRC_CATG_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.RTRN_RSN_DESC_TXT", "RTRN_RSN", "RTRN_RSN_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.SHPG_SVC_LVL_DESC_TXT", "SHPG_SVC_LVL", "SHPG_SVC_LVL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
        	// 2017/08/24 QC#20729 Mod Start
//            isGet = getAttrbtName(T_GLBL_COL, "A.T_MDL_ID", "MDL_NM", "T_MDL_NM", addWhere, val.getValue(), valNm);
            isGet = getMdlDescTxt(T_GLBL_COL, val.getValue(), valNm);
            // 2017/08/24 QC#20729 Mod End
        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.PRC_SVC_ZONE_DESC_TXT", "PRC_SVC_ZONE", "PRC_SVC_ZONE_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_CLS_DESC_TXT", "DS_ACCT_CLS", "DS_ACCT_CLS_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.SPCL_HDLG_TP_DESC_TXT", "SPCL_HDLG_TP", "SPCL_HDLG_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_EXTN_DESC_TXT", "COA_EXTN", "COA_EXTN_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.FRT_COND_DESC_TXT", "FRT_COND", "FRT_COND_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.SHIP_TO_FROM_POST_CD || '-' || A.SHIP_TO_THRU_POST_CD", "FRT_ZONE", "FRT_ZONE_NUM", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.PRC_FMLA_NM", "PRC_FMLA", "PRC_FMLA_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
            addWhere = "A.PRC_GRP_TP_CD = '" + PRC_GRP_TP.CUSTOMER_PRICING_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PRC_GRP_NM", "PRC_GRP", "PRC_GRP_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            addWhere = "A.DS_ACCT_TP_CD = '" + DS_ACCT_TP.CUSTOMER + "'";
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_NM", "SELL_TO_CUST", "SELL_TO_CUST_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_CH_NM", "COA_CH", "COA_CH_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_CLS_DESC_TXT", "DS_ACCT_CLS", "DS_ACCT_CLS_CD", addWhere, val.getValue(), valNm);
        // QC#20249 2017/08/09 Add Start
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
            addWhere = "A.PRC_GRP_TP_CD = '" + PRC_GRP_TP.MATERIAL_PRICING_GROUP_QTY_BREAKS + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PRC_GRP_NM", "PRC_GRP", "PRC_GRP_PK", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(prcRuleAttrbCd)) {
            valNm.clear();
            isGet = true;
        // QC#20249 2017/08/09 Add End
        // 2018/04/19 QC#2569 add start
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd)) {
            addWhere = "A.SLS_MAT_GRP_SQ_NUM = 1";
            isGet = getAttrbtName(GLBL_COL, "A.SLS_MAT_GRP_DESC_TXT", "SLS_MAT_GRP", "SLS_MAT_GRP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd)) {
            addWhere = "A.SLS_MAT_GRP_SQ_NUM = 2";
            isGet = getAttrbtName(GLBL_COL, "A.SLS_MAT_GRP_DESC_TXT", "SLS_MAT_GRP", "SLS_MAT_GRP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd)) {
            addWhere = "A.SLS_MAT_GRP_SQ_NUM = 3";
            isGet = getAttrbtName(GLBL_COL, "A.SLS_MAT_GRP_DESC_TXT", "SLS_MAT_GRP", "SLS_MAT_GRP_CD", addWhere, val.getValue(), valNm);
        }
        // 2018/04/19 QC#2569 add end

        if (!isGet) {
            valNm.clear();
            val.setErrorInfo(1, NMAM8186E, new String[] {val.getValue() });
        }
        return isGet;
    }

    /**
     * getRuleAttrbMdlNm.
     * @param prcRuleAttrbCd String
     * @param val EZDCStringItem
     * @param valNm EZDCStringItem
     * @param msgArgs String
     * @return boolean
     */
    public static boolean getRuleAttrbMdlNm(String prcRuleAttrbCd, EZDCStringItem val, EZDCStringItem valNm, String msgArgs) {
        boolean isGet = true;
        String addWhere = "";

        ZYPEZDItemValueSetter.setValue(valNm, val);

        isGet = getAttrbtName(T_GLBL_COL, "A.T_MDL_NM", "MDL_NM", "T_MDL_ID", addWhere, valNm.getValue(), val);

        if (!isGet) {
            valNm.clear();
            val.setErrorInfo(1, NMAM8186E, new String[] {val.getValue() });
        }
        return isGet;
    }

    /**
     * getRuleAttrbFmlaNm.
     * @param prcRuleAttrbCd String
     * @param val EZDCBigDecimalItem
     * @param valNm EZDCStringItem
     * @param msgArgs String
     * @return boolean
     */
    public static boolean getRuleAttrbFmlaNm(String prcRuleAttrbCd, EZDCBigDecimalItem val, EZDCStringItem valNm, String msgArgs) {
        boolean isGet = true;
        String addWhere = "";

        isGet = getAttrbtName(GLBL_COL, "A.PRC_FMLA_NM", "PRC_FMLA", "PRC_FMLA_PK", addWhere, val.getValue().toString(), valNm);

        if (!isGet) {
            valNm.clear();
            val.setErrorInfo(1, NMAM8186E, new String[] {val.getValue().toString() });
        }
        return isGet;
    }

    /**
     * getRuleAttrbPopParamFor6050.
     * @param prcRuleAttrbCd String
     * @param valCMsg EZDBStringItem
     * @param nameCMsg EZDBStringItem
     * @param popParamList EZDBStringItem[]
     * @return Object[]
     */
    public static Object[] getRuleAttrbPopParamFor6050(String prcRuleAttrbCd, EZDBStringItem valCMsg, EZDBStringItem nameCMsg, List<EZDBStringItem> popParamList) {
        Object[] param = new Object[11];

        if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "COA_CH");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "COA_CH_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "COA_CH_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "COA_CH_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "ACCT-CHANNEL Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "ACCT-CHANNEL Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "ACCT-CHANNEL Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "ACCT-CHANNEL Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "ACCT-CHANNEL Name");
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "COA_PROJ");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "COA_PROJ_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "COA_PROJ_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "COA_PROJ_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Merchandise Type Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Merchandise Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Merchandise Type Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Merchandise Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Merchandise Type Name");
        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "COA_PROD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "COA_PROD_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "COA_PROD_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "COA_PROD_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "PRODUCT Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "PRODUCT Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "PRODUCT Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "PRODUCT Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "PRODUCT Name");
        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "DS_ORD_CATG");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "DS_ORD_CATG_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "DS_ORD_CATG_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "DS_ORD_CATG_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Order Category Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Order Category Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Order Category Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Order Category Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Order Category Name");
        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "DS_ORD_TP");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "DS_ORD_TP_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "DS_ORD_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "DS_ORD_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Order Reason Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Order Reason Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Order Reason Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Order Reason Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Order Reason Name");
        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "LINE_BIZ_TP");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "LINE_BIZ_TP_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "LINE_BIZ_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "LINE_BIZ_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "PUBLIC LOB Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "PUBLIC LOB Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "PUBLIC LOB Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "PUBLIC LOB Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "PUBLIC LOB Name");
        } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "DS_ACCT_CLS");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "DS_ACCT_CLS_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "DS_ACCT_CLS_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "DS_ACCT_CLS_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Account Classification Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Account Classification Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Account Classification Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Account Classification Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Account Classification Name");
        } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "COA_BR");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "COA_BR_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "COA_BR_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "COA_BR_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "BRANCH Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "BRANCH Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "BRANCH Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "BRANCH Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "BRANCH Name");
        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "SVC_CALL_TP");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "SVC_CALL_TP_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "SVC_CALL_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "SVC_CALL_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Service Call Type Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Service Call Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Service Call Type Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Service Call Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Service Call Type Name");
        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "DS_ORD_LINE_CATG");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "DS_ORD_LINE_CATG_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "DS_ORD_LINE_CATG_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "DS_ORD_LINE_CATG_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Line Category Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Line Category Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Line Category Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Line Category Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Line Category Name");
        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "MKT_MDL");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "MKT_MDL_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "MKT_MDL_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "MKT_MDL_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Marketing Model Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Marketing Model Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Marketing Model Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Marketing Model Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Marketing Model Name");
        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "MKT_MDL_SEG");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "MKT_MDL_SEG_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "MKT_MDL_SEG_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "MKT_MDL_SEG_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Marketing Model Segment Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Marketing Model Segment Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Marketing Model Segment Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Marketing Model Segment Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Marketing Model Segment Name");
        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "CPO_SRC_TP");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "CPO_SRC_TP_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "CPO_SRC_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "CPO_SRC_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "CPO Source Type Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "CPO Source Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "CPO Source Type Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "CPO Source Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "CPO Source Type Name");
        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "DS_PMT_METH");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "DS_PMT_METH_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "DS_PMT_METH_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "DS_PMT_METH_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Payment Method Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Payment Method Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Payment Method Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Payment Method Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Payment Method Name");
        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "RTRN_RSN");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "RTRN_RSN_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "RTRN_RSN_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "RTRN_RSN_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Return Reason Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Return Reason Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Return Reason Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Return Reason Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Return Reason Name");
        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "SHPG_SVC_LVL");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "SHPG_SVC_LVL_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "SHPG_SVC_LVL_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "SHPG_SVC_LVL_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Shipping Service Level Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Shipping Service Level Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Shipping Service Level Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Shipping Service Level Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Shipping Service Level Name");
        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "PRC_SVC_ZONE");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "PRC_SVC_ZONE_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "PRC_SVC_ZONE_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "PRC_SVC_ZONE_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Service Zone Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Service Zone Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Service Zone Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Service Zone Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Service Zone Name");
        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "SPCL_HDLG_TP");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "SPCL_HDLG_TP_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "SPCL_HDLG_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "SPCL_HDLG_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Special Handling Type Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Special Handling Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Special Handling Type Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Special Handling Type Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Special Handling Type Name");
        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "COA_EXTN");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "COA_EXTN_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "COA_EXTN_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "COA_EXTN_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Business Unit Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Business Unit Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Business Unit Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Business Unit Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Business Unit Name");
        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(popParamList.get(0), "FRT_COND");
            ZYPEZDItemValueSetter.setValue(popParamList.get(1), "FRT_COND_CD");
            ZYPEZDItemValueSetter.setValue(popParamList.get(2), "FRT_COND_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(popParamList.get(3), "FRT_COND_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(popParamList.get(4), "Freight Terms Popup");
            ZYPEZDItemValueSetter.setValue(popParamList.get(5), "Freight Terms Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(6), "Freight Terms Name");
            ZYPEZDItemValueSetter.setValue(popParamList.get(7), "Freight Terms Code");
            ZYPEZDItemValueSetter.setValue(popParamList.get(8), "Freight Terms Name");
        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            popParamList.clear();
        }

        // Table Information
        param[0] = popParamList.get(0);
        param[1] = popParamList.get(1);
        param[2] = popParamList.get(2);
        param[3] = popParamList.get(3);
        param[4] = popParamList.get(4);
        param[5] = popParamList.get(5);
        param[6] = popParamList.get(6);
        param[7] = popParamList.get(7);
        param[8] = popParamList.get(8);
        param[9] = valCMsg;
        param[10] = nameCMsg;

        return param;
    }

    /**
     * getRuleAttrbPopParamFor1130.
     * @param prcRuleAttrbCd String
     * @param valCMsg EZDBBigDecimalItem
     * @param nameCMsg EZDBStringItem
     * @param glblCmpyCd String
     * @param popResultParams EZDMsgArray
     * @return Object[]
     */
    public static Object[] getRuleAttrbPopParamFor1130(String prcRuleAttrbCd, EZDBBigDecimalItem valCMsg, EZDBStringItem nameCMsg, String glblCmpyCd, EZDMsgArray popResultParams) {
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(popResultParams)

        Object[] param = new Object[7];

        if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
            suffixP0 = "";
            scrnNmP1 = "Formula Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PF.GLBL_CMPY_CD");
            tblNmP2.append(",PF.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(PF.PRC_FMLA_PK) PRC_FMLA_PK");
            tblNmP2.append(",PF.ACTV_FLG");
            tblNmP2.append(",PF.PRC_FMLA_NM");
            tblNmP2.append(",PF.PRC_FMLA_DESC_TXT");
            tblNmP2.append(",PF.EFF_FROM_DT");
            tblNmP2.append(",PF.EFF_THRU_DT");
            tblNmP2.append(",PC.PRC_CATG_NM");
            tblNmP2.append(",PFT.PRC_FMLA_TP_DESC_TXT");
            tblNmP2.append(",PFO.PRC_FMLA_OP_DESC_TXT");
            tblNmP2.append(",PF.PRC_FMLA_NUM");
            tblNmP2.append(",PF.PRC_FMLA_PK  PRC_FMLA_PK_NUM");
            tblNmP2.append(" FROM PRC_FMLA PF");
            tblNmP2.append(",PRC_CATG PC");
            tblNmP2.append(",PRC_FMLA_OP PFO");
            tblNmP2.append(",PRC_FMLA_TP PFT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PF.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PF.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PFT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PFT.EZCANCELFLAG");
            tblNmP2.append(" AND PF.PRC_FMLA_TP_CD = PFT.PRC_FMLA_TP_CD");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PC.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PC.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PF.PRC_CATG_CD = PC.PRC_CATG_CD(+)");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PFO.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PFO.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PF.PRC_FMLA_OP_CD = PFO.PRC_FMLA_OP_CD(+)");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Formula PK";
            whereArray0[1] = "PRC_FMLA_PK";
            whereArray0[2] = nvl(valCMsg.getValue());
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Formula Name";
            whereArray1[1] = "PRC_FMLA_NM";
            whereArray1[2] = nameCMsg.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Formula Description";
            whereArray2[1] = "PRC_FMLA_DESC_TXT";
            whereArray2[2] = "";
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Formula Type Name";
            whereArray3[1] = "PRC_FMLA_TP_DESC_TXT";
            whereArray3[2] = "";
            whereArray3[3] = "Y";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Effective Date From";
            whereArray4[1] = "EFF_FROM_DT";
            whereArray4[2] = "";
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Effective Date To";
            whereArray5[1] = "EFF_THRU_DT";
            whereArray5[2] = "";
            whereArray5[3] = "N";
            whereListP3.add(whereArray5);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Formula PK";
            columnArray0[1] = "PRC_FMLA_PK";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Active";
            columnArray1[1] = "ACTV_FLG";
            columnArray1[2] = BigDecimal.valueOf(5);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Formula Name";
            columnArray2[1] = "PRC_FMLA_NM";
            columnArray2[2] = BigDecimal.valueOf(21);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Formula Description";
            columnArray3[1] = "PRC_FMLA_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(21);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Effective Date From";
            columnArray4[1] = "EFF_FROM_DT";
            columnArray4[2] = BigDecimal.valueOf(12);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Effective Date To";
            columnArray5[1] = "EFF_THRU_DT";
            columnArray5[2] = BigDecimal.valueOf(12);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Source Price List Name";
            columnArray6[1] = "PRC_CATG_NM";
            columnArray6[2] = BigDecimal.valueOf(25);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Formula Operation Name";
            columnArray7[1] = "PRC_FMLA_OP_DESC_TXT";
            columnArray7[2] = BigDecimal.valueOf(15);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Formula Text";
            columnArray8[1] = "PRC_FMLA_NUM";
            columnArray8[2] = BigDecimal.valueOf(10);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PRC_FMLA_PK_NUM";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        }

        // Table Information
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = popResultParams;

        return param;
    }

    /**
     * getRuleAttrbPopParamFor1130
     * @param prcRuleAttrbCd String
     * @param valCMsg EZDBStringItem
     * @param nameCMsg EZDBStringItem
     * @param glblCmpyCd String
     * @param popResultParams EZDMsgArray
     * @return Object[]
     */
    public static Object[] getRuleAttrbPopParamFor1130(String prcRuleAttrbCd, EZDBStringItem valCMsg, EZDBStringItem nameCMsg, String glblCmpyCd, EZDMsgArray popResultParams) {
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(popResultParams)

        Object[] param = new Object[7];
        if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd) ||
                    PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd) ||
                    PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd) ||  // QC#20249 2017/08/09 Add
                    PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd) ||
                    PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(prcRuleAttrbCd) ||
                    PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add

            suffixP0 = "";

            String prcGrpTpCd = "";
            if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd)) {
                scrnNmP1 = "Customer Price Group Popup";
                prcGrpTpCd = PRC_GRP_TP.CUSTOMER_PRICING_GROUP;
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
                scrnNmP1 = "Material Price Group Popup";
                prcGrpTpCd = PRC_GRP_TP.MATERIAL_GROUP;
            } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
                scrnNmP1 = "Transaction Group Popup";
                prcGrpTpCd = PRC_GRP_TP.ORDER_CATEGORY_OR_REASON;
            } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(prcRuleAttrbCd)) {
                scrnNmP1 = "Customer Price Group Popup";
                prcGrpTpCd = PRC_GRP_TP.CUSTOMER_PRICING_GROUP;
            } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add
                scrnNmP1 = "Customer Price Group Popup";
                prcGrpTpCd = PRC_GRP_TP.CUSTOMER_PRICING_GROUP;
            // QC#20249 2017/08/09 Add Start
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
                scrnNmP1 = "Material Price Group (Qty Break) Popup";
                prcGrpTpCd = PRC_GRP_TP.MATERIAL_PRICING_GROUP_QTY_BREAKS;
            // QC#20249 2017/08/09 Add End
            }

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PG.GLBL_CMPY_CD");
            tblNmP2.append(",PG.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(PG.PRC_GRP_PK) PRC_GRP_PK");
            tblNmP2.append(",PG.PRC_GRP_NM");
            tblNmP2.append(",PG.PRC_GRP_DESC_TXT");
            tblNmP2.append(",PG.ACTV_FLG");
            tblNmP2.append(",PGTT.PRC_GRP_TRGT_TP_DESC_TXT");
            tblNmP2.append(",PGTA.PRC_GRP_TRGT_ATTRB_DESC_TXT");
            tblNmP2.append(",PGO.PRC_GRP_OP_DESC_TXT");
            tblNmP2.append(",PGD.PRC_GRP_FROM_TXT");
            tblNmP2.append(",PGD.PRC_GRP_THRU_TXT");
            tblNmP2.append(",PGD.EFF_FROM_DT");
            tblNmP2.append(",PGD.EFF_THRU_DT");
            tblNmP2.append(",PGD.PRC_GRP_DTL_PK");
            tblNmP2.append(" FROM PRC_GRP PG");
            tblNmP2.append(",PRC_GRP_DTL PGD");
            tblNmP2.append(",PRC_GRP_TRGT_TP PGTT");
            tblNmP2.append(",PRC_GRP_TRGT_ATTRB PGTA");
            tblNmP2.append(",PRC_GRP_OP PGO");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PG.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PG.PRC_GRP_TP_CD = '").append(prcGrpTpCd).append("'");
            tblNmP2.append(" AND PG.PRC_GRP_PK = PGD.PRC_GRP_PK");
            tblNmP2.append(" AND PG.EZCANCELFLAG  = PGD.EZCANCELFLAG");
            tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGTT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PGD.PRC_GRP_TRGT_TP_CD = PGTT.PRC_GRP_TRGT_TP_CD");
            tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGTT.EZCANCELFLAG");
            tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGTA.GLBL_CMPY_CD");
            tblNmP2.append(" AND PGD.PRC_GRP_TRGT_ATTRB_CD = PGTA.PRC_GRP_TRGT_ATTRB_CD");
            tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGTA.EZCANCELFLAG");
            tblNmP2.append(" AND PGD.GLBL_CMPY_CD = PGO.GLBL_CMPY_CD");
            tblNmP2.append(" AND PGD.PRC_GRP_OP_CD = PGO.PRC_GRP_OP_CD");
            tblNmP2.append(" AND PGD.EZCANCELFLAG  = PGO.EZCANCELFLAG");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Group PK";
            whereArray0[1] = "PRC_GRP_PK";
            whereArray0[2] = valCMsg.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Group Name";
            whereArray1[1] = "PRC_GRP_NM";
            whereArray1[2] = nameCMsg.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Group Description";
            whereArray2[1] = "PRC_GRP_DESC_TXT";
            whereArray2[2] = "";
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Effective Date From";
            whereArray3[1] = "EFF_FROM_DT";
            whereArray3[2] = "";
            whereArray3[3] = "N";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Effective Date To";
            whereArray4[1] = "EFF_THRU_DT";
            whereArray4[2] = "";
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Target Context";
            whereArray5[1] = "PRC_GRP_TRGT_TP_DESC_TXT";
            whereArray5[2] = "";
            whereArray5[3] = "Y";
            whereListP3.add(whereArray5);

            Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
            whereArray6[0] = "Attribute Name";
            whereArray6[1] = "PRC_GRP_TRGT_ATTRB_DESC_TXT";
            whereArray6[2] = "";
            whereArray6[3] = "Y";
            whereListP3.add(whereArray6);

            Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
            whereArray7[0] = "Attribute Value From";
            whereArray7[1] = "PRC_GRP_FROM_TXT";
            whereArray7[2] = "";
            whereArray7[3] = "N";
            whereListP3.add(whereArray7);

            Object[] whereArray8 = new Object[CMN_PRM_WHERE_NUM];
            whereArray8[0] = "Attribute Value To";
            whereArray8[1] = "PRC_GRP_THRU_TXT";
            whereArray8[2] = "";
            whereArray8[3] = "N";
            whereListP3.add(whereArray8);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Group PK";
            columnArray0[1] = "PRC_GRP_PK";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Group Name";
            columnArray1[1] = "PRC_GRP_NM";
            columnArray1[2] = BigDecimal.valueOf(21);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Active";
            columnArray2[1] = "ACTV_FLG";
            columnArray2[2] = BigDecimal.valueOf(5);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Target Context";
            columnArray3[1] = "PRC_GRP_TRGT_TP_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(15);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Attribute Name";
            columnArray4[1] = "PRC_GRP_TRGT_ATTRB_DESC_TXT";
            columnArray4[2] = BigDecimal.valueOf(18);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Operator";
            columnArray5[1] = "PRC_GRP_OP_DESC_TXT";
            columnArray5[2] = BigDecimal.valueOf(7);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Attribute Value From";
            columnArray6[1] = "PRC_GRP_FROM_TXT";
            columnArray6[2] = BigDecimal.valueOf(15);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Attribute Value To";
            columnArray7[1] = "PRC_GRP_THRU_TXT";
            columnArray7[2] = BigDecimal.valueOf(15);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Effective Date From";
            columnArray8[1] = "EFF_FROM_DT";
            columnArray8[2] = BigDecimal.valueOf(13);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] columnArray9 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray9[0] = "Effective Date To";
            columnArray9[1] = "EFF_THRU_DT";
            columnArray9[2] = BigDecimal.valueOf(13);
            columnArray9[3] = "N";
            columnListP4.add(columnArray9);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PRC_GRP_PK";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

            Object[] sortConditionArray2 = new Object[2];
            sortConditionArray2[0] = "PRC_GRP_DTL_PK";
            sortConditionArray2[1] = "ASC";
            sortConditionListP5.add(sortConditionArray2);

        } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {

            suffixP0 = "";
            scrnNmP1 = "CSMP Number Popup";

            tblNmP2.append(" SELECT CSMP.GLBL_CMPY_CD");
            tblNmP2.append(",CSMP.EZCANCELFLAG");
            tblNmP2.append(",CSMP.DS_ACCT_NUM");
            tblNmP2.append(",CSMP.DS_ACCT_NM");
            tblNmP2.append(",CSMP.CSMP_NUM");
            tblNmP2.append(",CSMP.PRC_CATG_CD");
            tblNmP2.append(",CSMP.PRC_CONTR_NUM");
            tblNmP2.append(",CSMP.RNW_CSMP_NUM");
            tblNmP2.append(",CSMP.CSMP_NUM_CMNT_TXT");
            tblNmP2.append(",CSMP.CSMP_CONTR_ACTV_FLG");
            tblNmP2.append(",CSMP.EFF_FROM_DT");
            tblNmP2.append(",CSMP.EFF_THRU_DT");
            tblNmP2.append(" FROM CSMP_CONTR CSMP");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" CSMP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND CSMP.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Account Number";
            whereArray0[1] = "DS_ACCT_NUM";
            whereArray0[2] = "";
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Account Name";
            whereArray1[1] = "DS_ACCT_NM";
            whereArray1[2] = "";
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "CSMP Number";
            whereArray2[1] = "CSMP_NUM";
            whereArray2[2] = valCMsg.getValue();
            whereArray2[3] = "N";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Price List ID";
            whereArray3[1] = "PRC_CATG_CD";
            whereArray3[2] = "";
            whereArray3[3] = "N";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Contract Number";
            whereArray4[1] = "PRC_CONTR_NUM";
            whereArray4[2] = "";
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Account Number";
            columnArray0[1] = "DS_ACCT_NUM";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Account Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "CSMP Number";
            columnArray2[1] = "CSMP_NUM";
            columnArray2[2] = BigDecimal.valueOf(15);
            columnArray2[3] = "Y";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Price List ID";
            columnArray3[1] = "PRC_CATG_CD";
            columnArray3[2] = BigDecimal.valueOf(10);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Price Contract Number";
            columnArray4[1] = "PRC_CONTR_NUM";
            columnArray4[2] = BigDecimal.valueOf(30);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Renew CSMP Number";
            columnArray5[1] = "RNW_CSMP_NUM";
            columnArray5[2] = BigDecimal.valueOf(15);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "CSMP Number Comment";
            columnArray6[1] = "CSMP_NUM_CMNT_TXT";
            columnArray6[2] = BigDecimal.valueOf(60);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Active Flag";
            columnArray7[1] = "CSMP_CONTR_ACTV_FLG";
            columnArray7[2] = BigDecimal.valueOf(8);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Effective Date From";
            columnArray8[1] = "EFF_FROM_DT";
            columnArray8[2] = BigDecimal.valueOf(12);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] columnArray9 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray9[0] = "Effective Date To";
            columnArray9[1] = "EFF_THRU_DT";
            columnArray9[2] = BigDecimal.valueOf(12);
            columnArray9[3] = "N";
            columnListP4.add(columnArray9);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "DS_ACCT_NUM";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "CSMP_NUM";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)
                || PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
            suffixP0 = "";

            String mdseStruElmntTp = "";
            if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
                mdseStruElmntTp = MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP;
            } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
                mdseStruElmntTp = MDSE_STRU_ELMNT_TP.PRODUCT_LINE;
            } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
                mdseStruElmntTp = MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2;
            } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
                mdseStruElmntTp = MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3;
            } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
                mdseStruElmntTp = MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4;
            }
            scrnNmP1 = "Product Control Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PC.GLBL_CMPY_CD");
            tblNmP2.append(",PC.EZCANCELFLAG");
            tblNmP2.append(",PC.PROD_CTRL_CD");
            tblNmP2.append(",PC.PROD_CTRL_DESC_TXT");
            tblNmP2.append(" FROM PROD_CTRL PC");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PC.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(mdseStruElmntTp).append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
                whereArray0[0] = "Product Level 1";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
                whereArray0[0] = "Product Level 2";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
                whereArray0[0] = "Product Level 3";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
                whereArray0[0] = "Product Level 4";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
                whereArray0[0] = "Product Level 5";
            }

            whereArray0[1] = "PROD_CTRL_CD";
            whereArray0[2] = valCMsg.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Description";
            whereArray1[1] = "PROD_CTRL_DESC_TXT";
            whereArray1[2] = nameCMsg.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
                columnArray0[0] = "Product Level 1";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
                columnArray0[0] = "Product Level 2";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
                columnArray0[0] = "Product Level 3";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
                columnArray0[0] = "Product Level 4";
            } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
                columnArray0[0] = "Product Level 5";
            }

            columnArray0[1] = "PROD_CTRL_CD";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Description";
            columnArray1[1] = "PROD_CTRL_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(30);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PROD_CTRL_CD";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) { // QC#6173 2016/04/14 Add
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
            suffixP0 = "";
            scrnNmP1 = "Price List Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PC.GLBL_CMPY_CD");
            tblNmP2.append(",PC.EZCANCELFLAG");
            tblNmP2.append(",PC.PRC_CATG_CD");
            tblNmP2.append(",PC.PRC_CATG_NM");
            tblNmP2.append(",PLT.PRC_LIST_TP_DESC_TXT");
            tblNmP2.append(",PLST.PRC_LIST_STRU_TP_DESC_TXT");
            tblNmP2.append(",PCON.PRC_CONTR_NM");
            tblNmP2.append(",PLG.PRC_LIST_GRP_NM");
            tblNmP2.append(",PC.EFF_FROM_DT");
            tblNmP2.append(",PC.EFF_THRU_DT");
            tblNmP2.append(",PC.PRC_CATG_DESC_TXT");
            tblNmP2.append(",PC.PRC_LIST_DPLY_NM");
            tblNmP2.append(",PC.PRC_CONTR_NUM");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_NM");
            tblNmP2.append(" FROM PRC_CATG PC");
            tblNmP2.append(",PRC_LIST_TP PLT");
            tblNmP2.append(",PRC_LIST_STRU_TP PLST");
            tblNmP2.append(",PRC_CONTR PCON");
            tblNmP2.append(",PRC_LIST_GRP PLG");
            tblNmP2.append(",PRC_LIST_SPLY_PGM PLSP");
            tblNmP2.append(",SPLY_AGMT_PLN SAP");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PC.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PLT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PLT.EZCANCELFLAG");
            tblNmP2.append(" AND PC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD");
            tblNmP2.append(" AND PLT.GLBL_CMPY_CD = PLST.GLBL_CMPY_CD");
            tblNmP2.append(" AND PLT.EZCANCELFLAG = PLST.EZCANCELFLAG");
            tblNmP2.append(" AND PLT.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PCON.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PCON.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PC.PRC_CONTR_NUM = PCON.PRC_CONTR_NUM(+)");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PLG.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PLG.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PC.PRC_LIST_GRP_CD = PLG.PRC_LIST_GRP_CD(+)");
            tblNmP2.append(" AND PC.GLBL_CMPY_CD = PLSP.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PC.EZCANCELFLAG = PLSP.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PC.PRC_CATG_CD = PLSP.PRC_CATG_CD(+)");
            tblNmP2.append(" AND PLSP.GLBL_CMPY_CD = SAP.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PLSP.EZCANCELFLAG = SAP.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PLSP.SPLY_AGMT_PLN_PK = SAP.SPLY_AGMT_PLN_PK(+)");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Price List Name";
            whereArray0[1] = "PRC_CATG_NM";
            whereArray0[2] = "";
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Price List Disp Name";
            whereArray1[1] = "PRC_LIST_DPLY_NM";
            whereArray1[2] = "";
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Price Description";
            whereArray2[1] = "PRC_CATG_DESC_TXT";
            whereArray2[2] = "";
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Contract#";
            whereArray3[1] = "PRC_CONTR_NUM";
            whereArray3[2] = "";
            whereArray3[3] = "N";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Contract Name";
            whereArray4[1] = "PRC_CONTR_NM";
            whereArray4[2] = "";
            whereArray4[3] = "Y";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Price List Type Name";
            whereArray5[1] = "PRC_LIST_TP_DESC_TXT";
            whereArray5[2] = "";
            whereArray5[3] = "Y";
            whereListP3.add(whereArray5);

            Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
            whereArray6[0] = "Price List Stru Name";
            whereArray6[1] = "PRC_LIST_STRU_TP_DESC_TXT";
            whereArray6[2] = "";
            whereArray6[3] = "Y";
            whereListP3.add(whereArray6);

            Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
            whereArray7[0] = "Price List Grp Name";
            whereArray7[1] = "PRC_LIST_GRP_NM";
            whereArray7[2] = "";
            whereArray7[3] = "Y";
            whereListP3.add(whereArray7);

            Object[] whereArray8 = new Object[CMN_PRM_WHERE_NUM];
            whereArray8[0] = "Supply Plan Name";
            whereArray8[1] = "SPLY_AGMT_PLN_NM";
            whereArray8[2] = "";
            whereArray8[3] = "Y";
            whereListP3.add(whereArray8);

            Object[] whereArray9 = new Object[CMN_PRM_WHERE_NUM];
            whereArray9[0] = "Effective Date From";
            whereArray9[1] = "EFF_FROM_DT";
            whereArray9[2] = "";
            whereArray9[3] = "N";
            whereListP3.add(whereArray9);

            Object[] whereArray10 = new Object[CMN_PRM_WHERE_NUM];
            whereArray10[0] = "Effective Date To";
            whereArray10[1] = "EFF_THRU_DT";
            whereArray10[3] = "N";
            whereListP3.add(whereArray10);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Price List ID";
            columnArray0[1] = "PRC_CATG_CD";
            columnArray0[2] = BigDecimal.valueOf(10);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Price List Name";
            columnArray1[1] = "PRC_CATG_NM";
            columnArray1[2] = BigDecimal.valueOf(21);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Price List Type Name";
            columnArray2[1] = "PRC_LIST_TP_DESC_TXT";
            columnArray2[2] = BigDecimal.valueOf(16);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Price List Structure Name";
            columnArray3[1] = "PRC_LIST_STRU_TP_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(16);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Contract Name";
            columnArray4[1] = "PRC_CONTR_NM";
            columnArray4[2] = BigDecimal.valueOf(25);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Price List Group Name";
            columnArray5[1] = "PRC_LIST_GRP_NM";
            columnArray5[2] = BigDecimal.valueOf(21);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Effective Date From";
            columnArray6[1] = "EFF_FROM_DT";
            columnArray6[2] = BigDecimal.valueOf(13);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Effective Date To";
            columnArray7[1] = "EFF_THRU_DT";
            columnArray7[2] = BigDecimal.valueOf(13);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] sortConditionArray = new Object[2];
            sortConditionArray[0] = "PRC_CATG_CD";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);

        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {

            // Add Start S21_NA QC#18237(Sol#161) 
            scrnNmP1 = "Model Search Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append("  DM.GLBL_CMPY_CD");
            tblNmP2.append(", DM.EZCANCELFLAG");
            tblNmP2.append(", MN.T_MDL_ID");
            tblNmP2.append(", NVL(MN.T_MDL_NM                   , '') T_MDL_NM");
            tblNmP2.append(", NVL(DM.MDL_DESC_TXT               , '') MDL_DESC_TXT");
            tblNmP2.append(", NVL(DMG.MDL_GRP_NM                , '') MDL_GRP_NM");
            tblNmP2.append(", (");
            tblNmP2.append("      CASE DM.MDL_ACTV_FLG ");
            tblNmP2.append("          WHEN 'Y' THEN 'Active' ");
            tblNmP2.append("          ELSE          'In-Active' ");
            tblNmP2.append("          END");
            tblNmP2.append("     )                                         XX_SCR_ITEM_10_TXT");
            tblNmP2.append(" FROM DS_MDL            DM");
            tblNmP2.append("    , MDL_NM            MN ");
            tblNmP2.append("    , DS_MDL_GRP        DMG ");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" DM.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND DM.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND DM.MDL_ID           = MN.T_MDL_ID  ");
            tblNmP2.append(" AND DM.GLBL_CMPY_CD     = MN.T_GLBL_CMPY_CD");
            tblNmP2.append(" AND MN.EZCANCELFLAG     = '0'");
            tblNmP2.append(" AND DM.MDL_GRP_ID       = DMG.MDL_GRP_ID(+)");
            tblNmP2.append(" AND DM.GLBL_CMPY_CD     = DMG.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND DMG.EZCANCELFLAG(+) = '0'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Model Name";
            whereArray0[1] = "T_MDL_NM";
            whereArray0[2] = valCMsg.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Model Description";
            whereArray1[1] = "MDL_DESC_TXT";
            whereArray1[2] = nameCMsg.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Model Name";
            columnArray0[1] = "T_MDL_NM";
            columnArray0[2] = BigDecimal.valueOf(25);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Model Description";
            columnArray1[1] = "MDL_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(30);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Model Group";
            columnArray2[1] = "MDL_GRP_NM";
            columnArray2[2] = BigDecimal.valueOf(15);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Status";
            columnArray3[1] = "XX_SCR_ITEM_10_TXT";
            columnArray3[2] = BigDecimal.valueOf(15);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "T_MDL_ID";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);
            // Add End S21_NA QC#18237(Sol#161) 

        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
            suffixP0 = "";
            scrnNmP1 = "Freight Zone Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" FZ.GLBL_CMPY_CD");
            tblNmP2.append(",FZ.EZCANCELFLAG");
            tblNmP2.append(",FZ.FRT_ZONE_NUM");
            tblNmP2.append(",FZ.LINE_BIZ_TP_CD");
            tblNmP2.append(",FZ.SHIP_TO_CUST_CD");
            tblNmP2.append(",FZ.SHIP_TO_ST_CD");
            tblNmP2.append(",FZ.SHIP_TO_CTRY_CD");
            tblNmP2.append(",FZ.SHIP_TO_FROM_POST_CD");
            tblNmP2.append(",FZ.SHIP_TO_THRU_POST_CD");
            tblNmP2.append(",FZ.EFF_FROM_DT");
            tblNmP2.append(",FZ.EFF_THRU_DT");
            tblNmP2.append(" FROM FRT_ZONE FZ");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" FZ.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND FZ.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Freight Zone Number";
            whereArray0[1] = "FRT_ZONE_NUM";
            whereArray0[2] = valCMsg.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Line Business Type";
            whereArray1[1] = "LINE_BIZ_TP_CD";
            whereArray1[2] = "";
            whereArray1[3] = "N";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Ship To Customer";
            whereArray2[1] = "SHIP_TO_CUST_CD";
            whereArray2[2] = "";
            whereArray2[3] = "N";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Ship To State Code";
            whereArray3[1] = "SHIP_TO_ST_CD";
            whereArray3[2] = "";
            whereArray3[3] = "N";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Ship To Country Code";
            whereArray4[1] = "SHIP_TO_CTRY_CD";
            whereArray4[2] = "";
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Freight Zone Number";
            columnArray0[1] = "FRT_ZONE_NUM";
            columnArray0[2] = BigDecimal.valueOf(13);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Line Business Type";
            columnArray1[1] = "LINE_BIZ_TP_CD";
            columnArray1[2] = BigDecimal.valueOf(12);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Ship To Customer";
            columnArray2[1] = "SHIP_TO_CUST_CD";
            columnArray2[2] = BigDecimal.valueOf(11);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Ship To State Code";
            columnArray3[1] = "SHIP_TO_ST_CD";
            columnArray3[2] = BigDecimal.valueOf(12);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Ship To Country Code";
            columnArray4[1] = "SHIP_TO_CTRY_CD";
            columnArray4[2] = BigDecimal.valueOf(14);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Ship To Post Code From";
            columnArray5[1] = "SHIP_TO_FROM_POST_CD";
            columnArray5[2] = BigDecimal.valueOf(15);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Ship To Post Code To";
            columnArray6[1] = "SHIP_TO_THRU_POST_CD";
            columnArray6[2] = BigDecimal.valueOf(15);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Effective Date From";
            columnArray7[1] = "EFF_FROM_DT";
            columnArray7[2] = BigDecimal.valueOf(13);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Effective Date To";
            columnArray8[1] = "EFF_THRU_DT";
            columnArray8[2] = BigDecimal.valueOf(13);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "FRT_ZONE_NUM";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "SHIP_TO_FROM_POST_CD";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

            Object[] sortConditionArray2 = new Object[2];
            sortConditionArray2[0] = "EFF_FROM_DT";
            sortConditionArray2[1] = "ASC";
            sortConditionListP5.add(sortConditionArray2);
        } else if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
            suffixP0 = "";
            scrnNmP1 = "Formula Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PF.GLBL_CMPY_CD");
            tblNmP2.append(",PF.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(PF.PRC_FMLA_PK) PRC_FMLA_PK");
            tblNmP2.append(",PF.ACTV_FLG");
            tblNmP2.append(",PF.PRC_FMLA_NM");
            tblNmP2.append(",PF.PRC_FMLA_DESC_TXT");
            tblNmP2.append(",PF.EFF_FROM_DT");
            tblNmP2.append(",PF.EFF_THRU_DT");
            tblNmP2.append(",PC.PRC_CATG_NM");
            tblNmP2.append(",PFO.PRC_FMLA_OP_DESC_TXT");
            tblNmP2.append(",PF.PRC_FMLA_NUM");
            tblNmP2.append(" FROM PRC_FMLA PF");
            tblNmP2.append(",PRC_FMLA_OP PFO");
            tblNmP2.append(",PRC_FMLA_TP PFT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PF.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PF.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PFT.GLBL_CMPY_CD");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PFT.EZCANCELFLAG");
            tblNmP2.append(" AND PF.PRC_FMLA_TP_CD = PFT.PRC_FMLA_TP_CD");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PC.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PC.EZCANCELFLAG(+)");
            tblNmP2.append(" AND PF.PRC_CATG_CD = PC.PRC_CATG_CD(+)");
            tblNmP2.append(" AND PF.GLBL_CMPY_CD = PFO.GLBL_CMPY_CD(+)");
            tblNmP2.append(" AND PF.EZCANCELFLAG = PFO.EZCANCELFLAG");
            tblNmP2.append(" AND PF.PRC_FMLA_OP_CD = PFO.PRC_FMLA_OP_CD(+)");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Formula PK";
            whereArray0[1] = "PRC_FMLA_PK";
            whereArray0[2] = valCMsg.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Formula Name";
            whereArray1[1] = "PRC_FMLA_NM";
            whereArray1[2] = nameCMsg.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Formula Description";
            whereArray2[1] = "PRC_FMLA_DESC_TXT";
            whereArray2[2] = "";
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Formula Type Name";
            whereArray3[1] = "PRC_FMLA_TP_DESC_TXT";
            whereArray3[2] = "";
            whereArray3[3] = "Y";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Effective Date From";
            whereArray4[1] = "EFF_FROM_DT";
            whereArray4[2] = "";
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Effective Date To";
            whereArray5[1] = "EFF_THRU_DT";
            whereArray5[2] = "";
            whereArray5[3] = "N";
            whereListP3.add(whereArray5);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Formula PK";
            columnArray0[1] = "PRC_FMLA_PK";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Active";
            columnArray1[1] = "ACTV_FLG";
            columnArray1[2] = BigDecimal.valueOf(5);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Formula Name";
            columnArray2[1] = "PRC_FMLA_NM";
            columnArray2[2] = BigDecimal.valueOf(21);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Formula Description";
            columnArray3[1] = "PRC_FMLA_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(21);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Effective Date From";
            columnArray4[1] = "EFF_FROM_DT";
            columnArray4[2] = BigDecimal.valueOf(13);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Effective Date To";
            columnArray5[1] = "EFF_THRU_DT";
            columnArray5[2] = BigDecimal.valueOf(13);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Source Price List Name";
            columnArray6[1] = "PRC_CATG_NM";
            columnArray6[2] = BigDecimal.valueOf(21);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Formula Operation Name";
            columnArray7[1] = "PRC_FMLA_OP_DESC_TXT";
            columnArray7[2] = BigDecimal.valueOf(15);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Formula Text";
            columnArray8[1] = "PRC_FMLA_NUM";
            columnArray8[2] = BigDecimal.valueOf(10);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PRC_FMLA_PK";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            popResultParams.clear();
        // 2018/04/19 QC#22569 add start
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd) ) {
            suffixP0 = "";
            scrnNmP1 = "Material Group Search Popup";
            
            String slsMatGrpSqNum = null;
            if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd)) {
                slsMatGrpSqNum = "1";
            } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd)) {
                slsMatGrpSqNum = "2";
            } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd)) {
                slsMatGrpSqNum = "3";
            }

            tblNmP2.append(" ");
            tblNmP2.append("SELECT ");
            tblNmP2.append("    MG.EZCANCELFLAG ");
            tblNmP2.append("   ,MG.GLBL_CMPY_CD ");
            tblNmP2.append("   ,MG.SLS_MAT_GRP_CD ");
            tblNmP2.append("   ,MG.SLS_MAT_GRP_DESC_TXT ");
            tblNmP2.append("   ,MG.SLS_MAT_GRP_SORT_NUM ");
            tblNmP2.append("FROM ");
            tblNmP2.append("    SLS_MAT_GRP MG ");
            tblNmP2.append("WHERE ");
            tblNmP2.append("    MG.EZCANCELFLAG = '0' ");
            tblNmP2.append("AND MG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
            tblNmP2.append("AND MG.SLS_MAT_GRP_SQ_NUM = ").append(slsMatGrpSqNum).append(" ");

            Object[] whereArray0 = new Object[4];
            whereArray0[0] = "Material Group";
            whereArray0[1] = "SLS_MAT_GRP_CD";
            whereArray0[2] = valCMsg.getValue();
            whereArray0[3] = FLG_OFF_N;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[4];
            whereArray1[0] = "Description";
            whereArray1[1] = "SLS_MAT_GRP_DESC_TXT";
            whereArray1[2] = nameCMsg.getValue();
            whereArray1[3] = FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Material Group";
            columnArray0[1] = "SLS_MAT_GRP_CD";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = FLG_ON_Y;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[4];
            columnArray1[0] = "Description";
            columnArray1[1] = "SLS_MAT_GRP_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(65);
            columnArray1[3] = FLG_OFF_N;
            columnListP4.add(columnArray1);

            Object[] sortCondArray0 = new Object[2];
            sortCondArray0[0] = "SLS_MAT_GRP_SORT_NUM";
            sortCondArray0[1] = "";
            sortConditionListP5.add(sortCondArray0);
            // 2018/04/19 QC#22569 add start
        }

        // Table Information
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = popResultParams;

        return param;
    }

    /**
     * getNextPrcRuleCondGrpCd.
     * @param currentNo String
     * @return String
     */
    public static String getNextPrcRuleCondGrpCd(String currentNo) {
        if (!ZYPCommonFunc.hasValue(currentNo)) {
            return FIRST_PRC_RULE_COND_GRP_CD;
        }

        int[] trgtIdx = new int[currentNo.length()];
        for (int i = 0; i < currentNo.length(); i++) {
            trgtIdx[i] = -1;
            for (int j = 0; j < STRING_LIST.length; j++) {
                if (STRING_LIST[j].equals(currentNo.substring(i, i + 1))) {
                    trgtIdx[i] = j;
                    break;
                }
            }

            if (trgtIdx[i] == -1) {
                return "";
            }
        }

        StringBuilder rsVal = new StringBuilder();
        boolean firstFlg = true;
        boolean carryFlg = false;
        for (int i = 1; i <= trgtIdx.length; i++) {
            int idx = trgtIdx[trgtIdx.length - i];

            if (firstFlg || carryFlg) {
                idx = idx + 1;
                carryFlg = false;
            }

            if (idx >= TWENTYSIX_DECIMAL) {
                carryFlg = true;
                idx = 0;
            }

            rsVal.append(STRING_LIST[idx]);

            if (carryFlg && i == trgtIdx.length) {
                rsVal.append(STRING_LIST[0]);
            }
            firstFlg = false;
        }

        return rsVal.reverse().toString();
    }

    /**
     * getPrcGrpTrgtAttrbCd.
     * @param prcGrpTrgtAttrbCd String
     * @param val EZDCStringItem
     * @param valNm EZDCStringItem
     * @return boolean
     */
    public static boolean getPrcGrpTrgtAttrbCd(String prcGrpTrgtAttrbCd, EZDSStringItem val, EZDSStringItem valNm) {

        boolean isGet = false;
        String addWhere = "";

        if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MDSE_ITEM_TP_CD", "MDSE_ITEM_TP", "MDSE_ITEM_TP_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MDSE_ITEM_CLS_TP_CD", "MDSE_ITEM_CLS_TP", "MDSE_ITEM_CLS_TP_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_PROJ_CD", "COA_PROJ", "COA_PROJ_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_PROD_CD", "COA_PROD", "COA_PROD_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_MARKETING_MODEL.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MKT_MDL_CD", "MKT_MDL", "MKT_MDL_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_CD", "PROD_CTRL", "PROD_CTRL_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LINE + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_CD", "PROD_CTRL", "PROD_CTRL_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_CD", "PROD_CTRL", "PROD_CTRL_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_CD", "PROD_CTRL", "PROD_CTRL_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_CD", "PROD_CTRL", "PROD_CTRL_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(prcGrpTrgtAttrbCd)) {
            if (!ZYPCommonFunc.hasValue(val) || !ZYPCommonFunc.isNumberCheck(val.getValue())) {
                return false;
            }
            isGet = getAttrbtName(GLBL_COL, "A.T_MDL_ID", "MDL_NM", "T_MDL_NM", new BigDecimal(val.getValue()), addWhere, valNm);
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(prcGrpTrgtAttrbCd)) {
            if (!ZYPCommonFunc.hasValue(val) || !ZYPCommonFunc.isNumberCheck(val.getValue())) {
                return false;
            }
            isGet = getAttrbtName(GLBL_COL, "A.MDL_GRP_ID", "DS_MDL_GRP", "MDL_GRP_NM", new BigDecimal(val.getValue()), addWhere, valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.SIC_CODE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.INDY_TP_CD", "INDY_TP", "INDY_TP_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_CLS_CD", "DS_ACCT_CLS", "DS_ACCT_CLS_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_TP_CD", "DS_ACCT_TP", "DS_ACCT_TP_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.DEALER_CODE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_DLR_CD", "DS_ACCT_DLR", "DS_ACCT_DLR_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_CATG_CD", "DS_ORD_CATG", "DS_ORD_CATG_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_TP_CD", "DS_ORD_TP", "DS_ORD_TP_DESC_TXT", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_LINE_CATG_CD", "DS_ORD_LINE_CATG", "DS_ORD_LINE_CATG_DESC_TXT", addWhere, val.getValue(), valNm);
        }

        return isGet;
    }

    /**
     * getPrcGrpTrgtAttrbNm.
     * @param prcGrpTrgtAttrbCd String
     * @param val EZDCStringItem
     * @param valNm EZDCStringItem
     * @return boolean
     */
    public static boolean getPrcGrpTrgtAttrbNm(String prcGrpTrgtAttrbCd, EZDSStringItem val, EZDSStringItem valNm) {

        boolean isGet = false;
        String addWhere = "";

        if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MDSE_ITEM_TP_DESC_TXT", "MDSE_ITEM_TP", "MDSE_ITEM_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MDSE_ITEM_CLS_TP_DESC_TXT", "MDSE_ITEM_CLS_TP", "MDSE_ITEM_CLS_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_PROJ_DESC_TXT", "COA_PROJ", "COA_PROJ_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.COA_PROD_DESC_TXT", "COA_PROD", "COA_PROD_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_MARKETING_MODEL.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.MKT_MDL_DESC_TXT", "MKT_MDL", "MKT_MDL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LINE + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(prcGrpTrgtAttrbCd)) {
            addWhere = "A.MDSE_STRU_ELMNT_TP_CD = '" + MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4 + "'";
            isGet = getAttrbtName(GLBL_COL, "A.PROD_CTRL_DESC_TXT", "PROD_CTRL", "PROD_CTRL_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(prcGrpTrgtAttrbCd)) {
            if (!ZYPCommonFunc.hasValue(val) || !ZYPCommonFunc.isNumberCheck(val.getValue())) {
                return false;
            }
            isGet = getAttrbtName(GLBL_COL, "A.T_MDL_NM", "MDL_NM", "T_MDL_ID", new BigDecimal(val.getValue()), addWhere, valNm);
        } else if (PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(prcGrpTrgtAttrbCd)) {
            if (!ZYPCommonFunc.hasValue(val) || !ZYPCommonFunc.isNumberCheck(val.getValue())) {
                return false;
            }
            isGet = getAttrbtName(GLBL_COL, "A.MDL_GRP_NM", "DS_MDL_GRP", "MDL_GRP_ID", new BigDecimal(val.getValue()), addWhere, valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.SIC_CODE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.INDY_TP_DESC_TXT", "INDY_TP", "INDY_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.CLASSIFICATION.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_CLS_DESC_TXT", "DS_ACCT_CLS", "DS_ACCT_CLS_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_TP_DESC_TXT", "DS_ACCT_TP", "DS_ACCT_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.DEALER_CODE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ACCT_DLR_DESC_TXT", "DS_ACCT_DLR", "DS_ACCT_DLR_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(prcGrpTrgtAttrbCd)) {
            isGet = false;
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_CATG_DESC_TXT", "DS_ORD_CATG", "DS_ORD_CATG_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_TP_DESC_TXT", "DS_ORD_TP", "DS_ORD_TP_CD", addWhere, val.getValue(), valNm);
        } else if (PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE.equals(prcGrpTrgtAttrbCd)) {
            isGet = getAttrbtName(GLBL_COL, "A.DS_ORD_LINE_CATG_DESC_TXT", "DS_ORD_LINE_CATG", "DS_ORD_LINE_CATG_CD", addWhere, val.getValue(), valNm);
        }

        return isGet;
    }

    // Add Start 2017/08/31 QC#20729-2
    /**
     * getPrcGrpTrgtAttrbNm.
     * @param glblCmpyCd String
     * @param mdlVal String
     * @param stsSearch boolean
     * @param valNm EZDCStringItem
     * @return boolean
     */
    public static boolean getMoldelIdName(String glblCmpyCd, String mdlVal, boolean stsSearch, EZDSStringItem valNm) {

        String getMdlVal = NMXC105001PriceMasterUtil.getInstance().getMdlValForIdNm(glblCmpyCd, mdlVal, stsSearch);
        if (!ZYPCommonFunc.hasValue(getMdlVal)) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(valNm, getMdlVal);

        return true;
    }

    /**
     * getPrcGrpTrgtAttrbNm.
     * @param glblCmpyCd String
     * @param mdlVal String
     * @param stsSearch boolean
     * @param valNm EZDCStringItem
     * @return boolean
     */
    public static boolean getMoldelIdName(String glblCmpyCd, String mdlVal, boolean stsSearch, EZDCStringItem valNm) {

        String getMdlVal = NMXC105001PriceMasterUtil.getInstance().getMdlValForIdNm(glblCmpyCd, mdlVal, stsSearch);
        if (!ZYPCommonFunc.hasValue(getMdlVal)) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(valNm, getMdlVal);

        return true;
    }
    // Add End 2017/08/31 QC#20729-2
    public static boolean checkFormat(String chkType, EZDCStringItem val, int fieldSize, String msgArgs) {
        int decLen = 0;
        if (CHK_TYPE_DEC_AMT.equals(chkType)) {
            decLen = DEC_AMT_LEN;
        } else if (CHK_TYPE_DEC_WT.equals(chkType)) {
            decLen = DEC_WT_LEN;
        } else if (CHK_TYPE_DEC_PCT.equals(chkType)) {
            decLen = DEC_PCT_LEN;
        }
        return checkFormat(chkType, val, fieldSize, decLen, msgArgs);
    }

    public static boolean checkFormat(String chkType, EZDCStringItem val, int fieldSize, int decLen, String msgArgs) {
        boolean isSuccess = true;
        if (CHK_TYPE_MST.equals(chkType)) {
            isSuccess = checkLength(val, fieldSize);
            if (!isSuccess) {
                setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
            }
        } else if (CHK_TYPE_INT.equals(chkType)) {
            isSuccess = ZYPCommonFunc.isHankakuSuuji(val.getValue());
            if (isSuccess) {
                isSuccess = checkLength(val, fieldSize);
                if (!isSuccess) {
                    setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
                }
            } else {
                setErrMsg(isSuccess, val, ZZM9004E, msgArgs);
            }
        } else if (CHK_TYPE_DEC_AMT.equals(chkType) || CHK_TYPE_DEC_WT.equals(chkType) || CHK_TYPE_DEC_PCT.equals(chkType)) {
            if (val.getValue().contains(PERIOD)) {
                String[] valArray = val.getValue().split(SPLIT_PERIOD);
//                int decLen = 0;
//                if (CHK_TYPE_DEC_AMT.equals(chkType)) {
//                    decLen = DEC_AMT_LEN;
//                } else if (CHK_TYPE_DEC_WT.equals(chkType)) {
//                    decLen = DEC_WT_LEN;
//                } else if (CHK_TYPE_DEC_PCT.equals(chkType)) {
//                    decLen = DEC_PCT_LEN;
//                }

                if (valArray.length == 2) {
                    isSuccess = ZYPCommonFunc.isNumberCheck(valArray[0]);
                    if (isSuccess) {
                        isSuccess = checkLength(valArray[0], fieldSize - decLen);
                        if (!isSuccess) {
                            setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
                        }
                    } else {
                        isSuccess = false;
                        setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
                        return isSuccess;
                    }

                    isSuccess = ZYPCommonFunc.isNumberCheck(valArray[1]);
                    if (isSuccess) {
                        isSuccess = checkLength(valArray[1], decLen);
                        if (!isSuccess) {
                            setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
                        }
                    } else {
                        isSuccess = false;
                        setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
                        return isSuccess;
                    }
                } else {
                    isSuccess = false;
                    setErrMsg(isSuccess, val, ZZM9015E, msgArgs);
                    return isSuccess;
                }
            } else {
                isSuccess = ZYPCommonFunc.isNumberCheck(val.getValue());
                if (isSuccess) {
                    isSuccess = checkLength(val, fieldSize);
                    if (!isSuccess) {
                        setErrMsg(isSuccess, val, ZZM9001E, msgArgs);
                    }
                } else {
                    setErrMsg(isSuccess, val, ZZM9004E, msgArgs);
                }
            }
        } else if (CHK_TYPE_DT.equals(chkType)) {
            if (val.getValue().length() == fieldSize) {
                isSuccess = ZYPDateUtil.checkDate(val.getValue());
            } else {
                isSuccess = false;
            }
            setErrMsg(isSuccess, val, ZZM9010E, msgArgs);
        }
        return isSuccess;
    }

    private static void setErrMsg(boolean isSuccess, EZDCStringItem val, String msgId, String msgArgs) {
        if (!isSuccess) {
            val.setErrorInfo(1, msgId, new String[] {msgArgs });
        }
    }

    private static boolean checkLength(EZDCStringItem val, int fieldSize) {
        if (val.getValue().length() > fieldSize) {
            return false;
        }
        return true;
    }

    private static boolean checkLength(String val, int fieldSize) {
        if (val.length() > fieldSize) {
            return false;
        }
        return true;
    }

    private static boolean getAttrbtName(String glblCmpyCol, String colNm, String tblNm, String whereCol, String whereVal, String addWhere, EZDCStringItem valNm) {
        S21SsmEZDResult ssmResult = NMXC105001PriceMasterUtil.getInstance().getName(glblCmpyCol, colNm, tblNm, whereCol, whereVal, addWhere);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(valNm, (String) ssmResult.getResultObject());

        return true;
    }

    // For SMsg
    private static boolean getAttrbtName(String glblCmpyCol, String colNm, String tblNm, String whereCol, String whereVal, String addWhere, EZDSStringItem valNm) {
        S21SsmEZDResult ssmResult = NMXC105001PriceMasterUtil.getInstance().getName(glblCmpyCol, colNm, tblNm, whereCol, whereVal, addWhere);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(valNm, (String) ssmResult.getResultObject());

        return true;
    }

    // For SMsg and BigDecimal
    private static boolean getAttrbtName(String glblCmpyCol, String colNm, String tblNm, String whereCol, BigDecimal whereVal, String addWhere, EZDSStringItem valNm) {
        S21SsmEZDResult ssmResult = NMXC105001PriceMasterUtil.getInstance().getName(glblCmpyCol, colNm, tblNm, whereCol, whereVal, addWhere);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(valNm, (String) ssmResult.getResultObject());

        return true;
    }

    private S21SsmEZDResult getName(String glblCmpyCol, String colNm, String tblNm, String whereCol, BigDecimal whereVal, String addWhere) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCol", glblCmpyCol);
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);
        params.put("addWhere", addWhere);

        return getSsmEZDClient().queryObject("getName", params);
    }

    private S21SsmEZDResult getName(String glblCmpyCol, String colNm, String tblNm, String whereCol, String addWhere, String whereVal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCol", glblCmpyCol);
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);
        params.put("addWhere", addWhere);

        return getSsmEZDClient().queryObject("getName", params);
    }

    private static String nvl(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        }
        return "";
    }

    // 2017/08/24 QC#20729 Add Start
    private static boolean getMdlDescTxt(String glblCmpyCd, String mdlNm, EZDCStringItem valNm) {
        String mdlDescTxt = NMXC105001PriceMasterUtil.getInstance().getMdlDescTxtForNm(glblCmpyCd, mdlNm);
        if (!ZYPCommonFunc.hasValue(mdlDescTxt)) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(valNm, mdlDescTxt);

        return true;
    }

    private String getMdlDescTxtForNm(String glblCmpyCd, String mdlNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlNm", mdlNm);

        return (String) getSsmEZDClient().queryObject("getMdlDescTxtForNm", params).getResultObject();
    }
    // 2017/08/24 QC#20729 Add Start

    // Add Start 2017/08/31 QC#20729-2
    private String getMdlValForIdNm(String glblCmpyCd, String mdlVal, boolean stsSearch) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlVal", mdlVal);
        if (stsSearch) {
            params.put("stsSearch", ZYPConstant.FLG_ON_Y);
        }

        return (String) getSsmEZDClient().queryObject("getMdlValForIdNm", params).getResultObject();
    }
    // Add End 2017/08/31 QC#20729-2
}
