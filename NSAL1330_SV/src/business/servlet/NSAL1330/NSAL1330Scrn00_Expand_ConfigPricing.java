/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_ALL_HEADER;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_ALL_MODEL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0677E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_Expand_ConfigPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_Expand_ConfigPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        if (NSAL1330CommonLogic.isFleet(scrnMsg)) {
            scrnMsg.setMessageInfo(NSAM0677E);
            return;
        }
        scrnMsg.xxNum_A.setValue(getButtonSelectNumber());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y); //[+] -> [-]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        if (NSAL1330CommonLogic.isFleet(scrnMsg)) {
            return null;
        }

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
        if (NSAL1330CommonLogic.isFleet(scrnMsg)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (!NSAL1330CommonLogic.isImport(scrnMsg)) {
            this.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, !ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()));
            this.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, getButtonSelectNumber(), !ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()));
        }
        NSAL1330CommonLogic.setConfigPricingAreaCtrlByConfig(scrnMsg, getButtonSelectNumber(), true);
        int ixR = -1;
        BigDecimal mdlIdA = scrnMsg.A.no(getButtonSelectNumber()).mdlId_A.getValue();

        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
            if (ixR < 0 && ZYPCommonFunc.hasValue(mdlIdA) && ZYPCommonFunc.hasValue(scrnMsg.R.no(i).mdlId_R) //
                    && mdlIdA.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0) {
                ixR = i;
            }
        }
        if (ixR < 0) {
            ixR = 0;
        }
        scrnMsg.setFocusItem(scrnMsg.R.no(ixR).prcCatgNm_R);
    }
}
