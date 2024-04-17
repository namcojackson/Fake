/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.getGLCommonPopupResult;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LIABILITY_ACCOUNT;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.PREPAY_ACCOUNT;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.VENDOR_RETURN_ACCOUNT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NMAL6860 Supplier Entry.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 *</pre>
 */
public class NMAL6860_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        if (LIABILITY_ACCOUNT.equals(scrnMsg.eventNm.getValue())) {

            // sets the returned value.
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).xxComnScrFirstValTxt_AL, getGLCommonPopupResult(scrnMsg));

            // focus on Liability Account Code.
            scrnMsg.setFocusItem(scrnMsg.A.no(getButtonSelectNumber()).xxComnScrFirstValTxt_AL);

        } else if (PREPAY_ACCOUNT.equals(scrnMsg.eventNm.getValue())) {

            // sets the returned value.
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrFirstValTxt_H2, getGLCommonPopupResult(scrnMsg));

            // focus on PrePay COA Account Code.
            scrnMsg.setFocusItem(scrnMsg.xxComnScrFirstValTxt_H2);

        } else if (VENDOR_RETURN_ACCOUNT.equals(scrnMsg.eventNm.getValue())) {

            // sets the returned value.
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrScdValTxt_H2, getGLCommonPopupResult(scrnMsg));

            // focus on PrePay COA Account Code.
            scrnMsg.setFocusItem(scrnMsg.xxComnScrScdValTxt_H2);
        }
    }
}
