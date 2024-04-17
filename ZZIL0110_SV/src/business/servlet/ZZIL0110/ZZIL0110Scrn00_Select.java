/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0110;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0110.ZZIL0110CMsg;
import business.servlet.ZZIL0110.common.ZZIL0110CommonLogic;
import business.servlet.ZZIL0110.constant.ZZIL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0110Scrn00_Select extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;

        ZZIL0110CMsg bizMsg = new ZZIL0110CMsg();
        bizMsg.setBusinessID(ZZIL0110Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZIL0110Constant.ReadCode);

        int index = getButtonSelectNumber();
        ZZIL0110CommonLogic.setSelectedDataFromScrn00(scrnMsg, index);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        ZZIL0110BMsg scrnMsg = (ZZIL0110BMsg) bMsg;
        ZZIL0110CMsg bizMsg = (ZZIL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZZIL0110CommonLogic.setTableColorB(scrnMsg, bizMsg);
        this.setButtonProperties(ZZIL0110Constant.CMN_BTN7[0], ZZIL0110Constant.CMN_BTN7[1], ZZIL0110Constant.CMN_BTN7[2], 1, null);
        ZZIL0110CommonLogic.setFocusItemScrn01(scrnMsg);
    }

}
