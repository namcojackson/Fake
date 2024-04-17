/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_ALL_HEADER;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_ALL_MODEL;

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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/03   Hitachi         Y.Takeno        Create          QC#20059
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1330Scrn00_Toggle_ServicePriceSettingLevel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int idxA = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(idxA));

        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(idxA);
        if (aScrnMsg.xxSelFlg_A.isInputProtected()) {
            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.xxSelFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y); // [+] -> [-]
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxSmryLineFlg_A, ZYPConstant.FLG_OFF_N); //[-] -> [+]
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int idxA = getButtonSelectNumber();
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(idxA);
        if (aScrnMsg.xxSelFlg_A.isInputProtected()) {
            return null;
        }

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID("NSAL1330");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        int idxA = getButtonSelectNumber();
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(idxA);
        if (aScrnMsg.xxSelFlg_A.isInputProtected()) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/05/07 QC#22482 Del Start
//        NSAL1330CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Del End
        NSAL1330CommonLogic.setAccessoryChargeAreaCtrl(scrnMsg);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);

        NSAL1330CommonLogic.initAppFracDigit(scrnMsg);
        NSAL1330CommonLogic.initCmnBtnProp(this, scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP)) {
            NSAL1330CommonLogic.protectRefMode(this, scrnMsg);
            return;
        }

        NSAL1330CommonLogic.initInputProtected(scrnMsg);
        NSAL1330CommonLogic.initButton(this, scrnMsg);
        NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NSAL1330CommonLogic.setBandButton(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            NSAL1330CommonLogic.setTierButtonCtrl(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.A.no(i), i, scrnMsg);
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330CommonLogic.setBandButton(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
        }

        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.xxSelFlg_A.getValue())) {
            // expand config pricing area
            this.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, !ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()));
            this.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, idxA, !ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()));
            NSAL1330CommonLogic.setConfigPricingAreaCtrlByConfig(scrnMsg, idxA, true);
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

        } else {
            // collapse config pricing area
            this.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, !NSAL1330CommonLogic.isConfigPricingAllCollapsed(scrnMsg));
            this.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, getButtonSelectNumber(), false);
            NSAL1330CommonLogic.setConfigPricingAreaCtrlByConfig(scrnMsg, idxA, false);
            scrnMsg.setFocusItem(scrnMsg.A.no(idxA).prcCatgNm_A);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP)) {
            NSAL1330CommonLogic.protectRefMode(this, scrnMsg);
            return;
        }

        scrnMsg.putErrorScreen();
    }
}
