/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_EXP_ACCT_TO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/12   Hitachi         J.Kim           Create          QC#22807
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_ExpenseAcctTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        String scrnEventNm = OPENWIN_EXP_ACCT_TO;
        String depcCoaAcctCdTo = scrnMsg.depcCoaAcctCd_T.getValue();
        Object[] params = NLEL0060CommonLogic.setParamForCoaAcctPopup(scrnMsg, depcCoaAcctCdTo, scrnEventNm);

        setArgForSubScreen(params);
    }
}
