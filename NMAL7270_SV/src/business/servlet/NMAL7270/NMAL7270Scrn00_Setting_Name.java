/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import static business.servlet.NMAL7270.constant.NMAL7270Constant.BIZ_ID;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.ZZM9000E;
import static business.servlet.NMAL7270.constant.NMAL7270Constant.ZZM9004E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7270.NMAL7270CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_Setting_Name
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7270Scrn00_Setting_Name extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        int idx = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1)) {
            scrnMsg.A.no(idx).prcRuleCondFromTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(idx).prcRuleCondFromTxt_A1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1);
        } else {
            if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO.equals(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue())
                    || PRC_RULE_ATTRB.FORMULA.equals(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue())) {
                if (!ZYPCommonFunc.isNumberCheck(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1.getValue())) {
                    scrnMsg.A.no(idx).prcRuleCondFromTxt_A1.setErrorInfo(1, ZZM9004E, new String[] {scrnMsg.A.no(idx).prcRuleCondFromTxt_A1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.A.no(idx).prcRuleCondFromTxt_A1);
                }

            }
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL7270CMsg bizMsg = new NMAL7270CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;
        NMAL7270CMsg bizMsg  = (NMAL7270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).prcRuleCondFromTxt_A1);
        scrnMsg.putErrorScreen();
    }
}
