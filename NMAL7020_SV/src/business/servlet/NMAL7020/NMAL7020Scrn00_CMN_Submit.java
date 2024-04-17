/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020;

import static business.servlet.NMAL7020.constant.NMAL7020Constant.BIZ_ID;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.NMAM0043E;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.NMAM0049E;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.NMAM8289E;
import static business.servlet.NMAL7020.constant.NMAL7020Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7020.NMAL7020CMsg;
import business.servlet.NMAL7020.common.NMAL7020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7020Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CommonLogic.addCheckItem(scrnMsg);
        checkPriceCalculation(scrnMsg);
        checkdateFromTo(scrnMsg);
        checkNewEffectiveDates(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    private void checkNewEffectiveDates(NMAL7020BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.effApplyLvlTpCd) && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt)) {
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0049E, new String[] {"Apply To", "Effetive From" });
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.effApplyLvlTpCd) && ZYPCommonFunc.hasValue(scrnMsg.effFromDt)) {
            scrnMsg.effApplyLvlTpCd.setErrorInfo(1, NMAM0049E, new String[] {"Effetive From", "Apply To" });

        } else if (!ZYPCommonFunc.hasValue(scrnMsg.effApplyLvlTpCd) && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {
            scrnMsg.effApplyLvlTpCd.setErrorInfo(1, NMAM8289E, new String[] {"Apply To", "Effetive From" });
            scrnMsg.effFromDt.setErrorInfo(1, NMAM8289E, new String[] {"Apply To", "Effetive From" });
        }
    }

    private void checkdateFromTo(NMAL7020BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt.getNameForMessage(), scrnMsg.effThruDt.getNameForMessage() });
                scrnMsg.effThruDt.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt.getNameForMessage(), scrnMsg.effThruDt.getNameForMessage() });
            }
        }
    }

    private void checkPriceCalculation(NMAL7020BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCalcTpCd) && (ZYPCommonFunc.hasValue(scrnMsg.prcPctAmtTpCd) || ZYPCommonFunc.hasValue(scrnMsg.copyPrcAmtRate))) {
            scrnMsg.prcCalcTpCd.setErrorInfo(1, NMAM0049E, new String[] {"Percentage/Amount Type,Percentage/Amount", "Price Calculation Type" });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcPctAmtTpCd) && (ZYPCommonFunc.hasValue(scrnMsg.prcCalcTpCd) || ZYPCommonFunc.hasValue(scrnMsg.copyPrcAmtRate))) {
            scrnMsg.prcPctAmtTpCd.setErrorInfo(1, NMAM0049E, new String[] {"Price Calculation Type,Percentage/Amount", "Percentage/Amount Type" });
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.copyPrcAmtRate) && (ZYPCommonFunc.hasValue(scrnMsg.prcCalcTpCd) || ZYPCommonFunc.hasValue(scrnMsg.prcPctAmtTpCd))) {
            scrnMsg.copyPrcAmtRate.setErrorInfo(1, NMAM0049E, new String[] {"Price Calculation Type,Percentage/Amount Type", "Percentage/Amount" });
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CMsg bizMsg = new NMAL7020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CMsg bizMsg = (NMAL7020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7020CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
