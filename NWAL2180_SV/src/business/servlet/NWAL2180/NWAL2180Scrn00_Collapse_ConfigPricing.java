/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_APPLY_ALL_HEADER;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_APPLY_ALL_MODEL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180Scrn00_Collapse_ConfigPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/05   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2180Scrn00_Collapse_ConfigPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        scrnMsg.xxNum_A.setValue(getButtonSelectNumber());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxSmryLineFlg_A, ZYPConstant.FLG_OFF_N); //[-] -> [+]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

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

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        this.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, false);
        this.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, getButtonSelectNumber(), false);
        NWAL2180CommonLogic.setConfigPricingAreaCtrlByConfig(scrnMsg, getButtonSelectNumber(), false);
        scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).prcCatgNm_A);
    }
}
