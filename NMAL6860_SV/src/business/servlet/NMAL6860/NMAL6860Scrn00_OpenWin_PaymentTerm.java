/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.getParamNWAL1130ForPaymentTerm;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_PAYMENT_TERM;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.transitioncommon.EZDTransition;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/26   CITS            T.Gotoda        Create          QC#13163
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 *</pre>
 */
public class NMAL6860Scrn00_OpenWin_PaymentTerm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        if (TAB_NM_DETAIL.equals(scrnMsg.xxDplyTab.getValue()) && scrnMsg.vndPmtTermDescTxt_H2.isInputProtected()) {
            setNextTransition(EZDTransition.STAY, null);
        }
        // END 2021/03/01 G.Delgado [QC#56057,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_AD, LINK_OPENWIN_PAYMENT_TERM);
        Object[] params = getParamNWAL1130ForPaymentTerm(scrnMsg);

        setArgForSubScreen(params);
    }
}
