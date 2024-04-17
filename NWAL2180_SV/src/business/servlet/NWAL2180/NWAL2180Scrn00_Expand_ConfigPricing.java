/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWAM0919E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180Scrn00_Expand_ConfigPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/05   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2180Scrn00_Expand_ConfigPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        if (NWAL2180CommonLogic.isFleet(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0919E);
            return;
        }
        scrnMsg.xxNum_A.setValue(getButtonSelectNumber());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxSmryLineFlg_A, ZYPConstant.FLG_ON_Y); //[+] -> [-]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        if (NWAL2180CommonLogic.isFleet(scrnMsg)) {
            return null;
        }

        NWAL2180CMsg bizMsg = new NWAL2180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;
        if (NWAL2180CommonLogic.isFleet(scrnMsg)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2180CommonLogic.setConfigPricingAreaCtrlByConfig(scrnMsg, getButtonSelectNumber(), true);
        int ixR = -1;
        BigDecimal mdlIdA = scrnMsg.A.no(getButtonSelectNumber()).mdlId_A.getValue();

        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NWAL2180CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NWAL2180CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
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
