/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import static business.servlet.NLBL3090.constant.NLBL3090Constant.TAB_ASSIGN;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3090.common.NLBL3090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NLBL3090Scrn00_OpenWin_CdPerson extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(selectIdx));
        Object[] params;
        if (TAB_ASSIGN.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.xxPopPrm_X.setValue("AC");
            params = NLBL3090CommonLogic.setParamForCoordinatorPopup(scrnMsg, scrnMsg.A.no(selectIdx), null, true);
        } else {
            scrnMsg.xxPopPrm_X.setValue("BC");
            params = NLBL3090CommonLogic.setParamForCoordinatorPopup(scrnMsg, null, scrnMsg.B.no(selectIdx), false);
        }
        setArgForSubScreen(params);
    }
}
