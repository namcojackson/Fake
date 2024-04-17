/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/05   Hitachi         J.Kim           Create          N/A
 * 2018/04/12   Hitachi         J.Kim           Update          QC#22807
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_AssetCoaAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2018/03/28 J.Kim [QC#22087,MOD]
        // NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        // setArgForSubScreen(NLEL0060CommonLogic.getNMAL6050Param(scrnMsg,
        // scrnMsg.depcCoaAcctCd_H1.getValue()));
        // END 2018/03/28 J.Kim [QC#22087,MOD]
    }
}
