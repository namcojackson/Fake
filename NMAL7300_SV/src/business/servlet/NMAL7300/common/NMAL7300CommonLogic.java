/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7300.common;

import static business.servlet.NMAL7300.constant.NMAL7300Constant.ATTB_CHECK_BOX;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.CONDITION_LIST;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.GLBL_CMPY_CD;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.INPUT_LIST;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.MAX_INPUT_PARAM_NUM;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.NMAM0044E;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.NMAM0050E;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.NMAM0072E;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.NMAM8054E;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.PRC_ADJ_LINE;
import static business.servlet.NMAL7300.constant.NMAL7300Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDBMsgArray;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import business.servlet.NMAL7260.NMAL7260_BBMsg;
import business.servlet.NMAL7260.NMAL7260_CBMsgArray;
import business.servlet.NMAL7260.NMAL7260_TBMsg;
import business.servlet.NMAL7260.NMAL7260_TBMsgArray;
import business.servlet.NMAL7300.NMAL7300BMsg;
import business.servlet.NMAL7300.NMAL7300_BBMsg;
import business.servlet.NMAL7300.NMAL7300_BBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NMAL7300CommonLogic {
    public static void setInputParam(NMAL7300BMsg scrnMsg, Object[] arg) {
        int index = 0;
        NMAL7260_BBMsg bbMsg = new NMAL7260_BBMsg();
        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < MAX_INPUT_PARAM_NUM) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (String) arg[GLBL_CMPY_CD]);
        if (arg[PRC_ADJ_LINE] instanceof NMAL7260_BBMsg) {
            bbMsg = (NMAL7260_BBMsg) arg[PRC_ADJ_LINE];
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntPrcAdjDtlPk, bbMsg.prcAdjDtlPk_B1);
        }
        if (arg[CONDITION_LIST] instanceof NMAL7260_CBMsgArray) {
            NMAL7260_CBMsgArray cbMsgArray = (NMAL7260_CBMsgArray) arg[CONDITION_LIST];

            for (int i = 0; i < cbMsgArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(cbMsgArray.no(i).prcRuleAttrbCd_C1.getValue(), PRC_RULE_ATTRB.LINE_QTY)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleAttrbCd, cbMsgArray.no(i).prcRuleAttrbCd_C1);
                } else if (S21StringUtil.isEquals(cbMsgArray.no(i).prcRuleAttrbCd_C1.getValue(), PRC_RULE_ATTRB.LINE_QTY_QTYBREAK)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleAttrbCd, cbMsgArray.no(i).prcRuleAttrbCd_C1);
                } else if (S21StringUtil.isEquals(cbMsgArray.no(i).prcRuleAttrbCd_C1.getValue(), PRC_RULE_ATTRB.PERCENT)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                } else if (S21StringUtil.isEquals(cbMsgArray.no(i).prcRuleAttrbCd_C1.getValue(), PRC_RULE_ATTRB.VALUE)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcRuleAttrbCd_A, cbMsgArray.no(i).prcRuleAttrbCd_C1);
                    index++;
                }
            }
            scrnMsg.A.setValidCount(index);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                String prcRuleAttrbCd = scrnMsg.A.no(i).prcRuleAttrbCd_A.getValue();
                if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_04);
                } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_05);
                } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_06);
                } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_07);
                } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_08);
                } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_09);
                } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_10);
                } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_11);
                } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_12);
                } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_13);
                } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_14);
                } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.prcRuleCondFromTxt_15.getValue(), " - ", bbMsg.prcRuleCondThruTxt_15.getValue()));
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_16);
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_17);
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_18);
                } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_19);
                } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_20);
                } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.xxSvcCallDt_FR.getValue(), " - ", bbMsg.xxSvcCallDt_TH.getValue()));
                } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_22);
                } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.prcRuleCondFromTxt_24.getValue(), " - ", bbMsg.prcRuleCondThruTxt_24.getValue()));
                } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_25);
                } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_27);
                } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_28);
                } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_29);
                } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.prcRuleCondFromTxt_30.getValue(), " - ", bbMsg.prcRuleCondThruTxt_30.getValue()));
                } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_31);
                } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_32);
                } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.prcDt_FR.getValue(), " - ", bbMsg.prcDt_TH.getValue()));
                } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_34);
                } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_35);
                } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_36);
                } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.xxRqstDt_FR.getValue(), " - ", bbMsg.xxRqstDt_TH.getValue()));
                } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_38);
                } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_39);
                } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_40);
                } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_41);
                } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_42);
                } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_44);
                } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, concat(bbMsg.prcRuleCondFromTxt_45.getValue(), " - ", bbMsg.prcRuleCondThruTxt_45.getValue()));
                } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_46);
                } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_48);
                } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_49);
                } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_53);
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_54);
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_55);
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_56);
                } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prcRuleCondFromTxt_A, bbMsg.prcRuleCondFromTxt_57);
                }
            }
            if (arg[INPUT_LIST] instanceof NMAL7260_TBMsgArray) {
                NMAL7260_TBMsgArray tbMsgArray = (NMAL7260_TBMsgArray) arg[INPUT_LIST];
                for (int i = 0; i < tbMsgArray.getValidCount(); i++) {
                    NMAL7260_TBMsg fromMsg = tbMsgArray.no(i);
                    EZDMsg.copy(fromMsg, "T", scrnMsg.B.no(i), "B");
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).ezUpTime_B1, fromMsg.ezUpTime_T1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).ezUpTimeZone_B1, fromMsg.ezUpTimeZone_T1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).ezUpTime_B3, fromMsg.ezUpTime_T3);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).ezUpTimeZone_B3, fromMsg.ezUpTimeZone_T3);
                }
                scrnMsg.B.setValidCount(tbMsgArray.getValidCount());
            }
        }
    }

    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    public static void setHeaderProtect(NMAL7300BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).prcRuleAttrbCd_A.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleAttrbDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondFromTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxRecNmTxt_A.setInputProtected(true);
        }
    }

    public static void setDetailProtect(NMAL7300BMsg scrnMsg) {
        boolean isProtected = false;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).xxChkBox_B.setInputProtected(isProtected);
            scrnMsg.B.no(i).qtyDiscDtlQty_B.setInputProtected(isProtected);
            scrnMsg.B.no(i).prcRuleDtlTxt_B.setInputProtected(isProtected);
            scrnMsg.B.no(i).prcRuleDtlRate_B.setInputProtected(isProtected);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL7260BMsg
     * @param scrnAMsgAry NMAL7260_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7300BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL7260BMsg
     * @param scrnAMsgAry NMAL7300_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7300BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL7300BMsg
     * @param scrnAMsgAry NMAL7300_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7300BMsg scrnMsg, NMAL7300_BBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    public static void addNewLine(NMAL7300BMsg scrnMsg) {
        int size = scrnMsg.B.getValidCount();
        if (size >= scrnMsg.B.length()) {
            scrnMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(scrnMsg.A.length()) + "(include Active/Delete)" });
            throw new EZDFieldErrorException();
        }

        scrnMsg.B.no(size).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(size).delFlg_B, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(size).prntPrcAdjDtlPk_B, scrnMsg.prntPrcAdjDtlPk);

        scrnMsg.setFocusItem(scrnMsg.B.no(size).qtyDiscDtlQty_B);
        scrnMsg.B.setValidCount(size + 1);
    }

    public static void checkQty(NMAL7300BMsg scrnMsg) {
        Set<String> cacheQtySet = new HashSet<String>();
        Set<String> targetQtySet = new HashSet<String>();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).delFlg_B.getValue())){
                continue;
            }
            String qty = scrnMsg.B.no(i).qtyDiscDtlQty_B.getValue().toString();

            if (cacheQtySet.contains(qty)) {
                targetQtySet.add(qty);
            } else {
                cacheQtySet.add(qty);
            }
        }

        for (String targetQty : targetQtySet) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).delFlg_B.getValue())) {
                    continue;
                }
                String qty = scrnMsg.B.no(i).qtyDiscDtlQty_B.getValue().toString();
                if (targetQty.equals(qty)) {
                    scrnMsg.B.no(i).qtyDiscDtlQty_B.setErrorInfo(1, NMAM0072E, new String[] {qty });
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).qtyDiscDtlQty_B);
                }
            }
        }

        BigDecimal bufQty = BigDecimal.ZERO;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).delFlg_B.getValue())) {
                continue;
            }
            String inputqty = scrnMsg.B.no(i).qtyDiscDtlQty_B.getValue().toString();
            if (inputqty == null) {
                continue;
            }
            BigDecimal qty = new BigDecimal(inputqty);
            if (bufQty.compareTo(qty) > 0) {
                scrnMsg.B.no(i).qtyDiscDtlQty_B.setErrorInfo(1, NMAM0044E, new String[] {"Quantity", "previous row" });
                scrnMsg.addCheckItem(scrnMsg.B.no(i).qtyDiscDtlQty_B);
            }
            bufQty = qty;
        }
    }

    public static void setReturnObject(NMAL7300BMsg scrnMsg, Object[] arg) {
        NMAL7260_TBMsgArray tbMsgArray = (NMAL7260_TBMsgArray) arg[INPUT_LIST];

        ZYPTableUtil.clear(tbMsgArray);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            EZDMsg.copy(scrnMsg.B.no(i), "B", tbMsgArray.no(i), "T");
            ZYPEZDItemValueSetter.setValue(tbMsgArray.no(i).ezUpTime_T1, scrnMsg.B.no(i).ezUpTime_B1);
            ZYPEZDItemValueSetter.setValue(tbMsgArray.no(i).ezUpTimeZone_T1, scrnMsg.B.no(i).ezUpTimeZone_B1);
            ZYPEZDItemValueSetter.setValue(tbMsgArray.no(i).ezUpTime_T3, scrnMsg.B.no(i).ezUpTime_B3);
            ZYPEZDItemValueSetter.setValue(tbMsgArray.no(i).ezUpTimeZone_T3, scrnMsg.B.no(i).ezUpTimeZone_B3);
        }
        tbMsgArray.setValidCount(scrnMsg.B.getValidCount());
    }

    public static void checkSelected(NMAL7300BMsg scrnMsg) {

        List<Integer> idxList = ZYPTableUtil.getSelectedRows(scrnMsg.B, ATTB_CHECK_BOX, ZYPConstant.FLG_ON_Y);
        if (idxList.size() > 0) {
            return;
        }

        for (int idx : idxList) {
            if (scrnMsg.B.no(idx).xxChkBox_B.isInputProtected()) {
                scrnMsg.B.no(idx).xxChkBox_B.setErrorInfo(1, NMAM8054E);
                scrnMsg.addCheckItem(scrnMsg.B.no(idx).xxChkBox_B);
            }
        }
    }

    public static void deleteLine(NMAL7300BMsg scrnMsg) {
        List<Integer> idxList = ZYPTableUtil.getSelectedRows(scrnMsg.B, ATTB_CHECK_BOX, ZYPConstant.FLG_ON_Y);
        List<Integer> delList = new ArrayList<Integer>();

        if (idxList.size() == 0) {
            return;
        }
        NMAL7300_BBMsg row = new NMAL7300_BBMsg();
        for (int i = 0; i < idxList.size(); i++) {
            row = scrnMsg.B.no(idxList.get(i));
            if (ZYPCommonFunc.hasValue(row.prcRuleDtlPk_B)) {
                row.delFlg_B.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                delList.add(idxList.get(i));
            }
        }
        if (delList.size() != 0) {
            ZYPTableUtil.deleteRows(scrnMsg.B, idxList);
        }
    }

    private static String concat(Object... args) {
        StringBuffer sb = new StringBuffer();
        for (Object o : args) {
            if (o == null) {
                sb.append("");
            } else {
                sb.append(o.toString());
            }
        }
        return sb.toString();
    }
}
