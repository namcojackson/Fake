/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.controlElevApptReqFlg;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.controlElevAvalFlg;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_OnChange_ElevAvalFlg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 * 2015/11/26   Fujitsu         T.Ishii         Update          S21_NA#1147
 *</pre>
 */
public class NWAL1510Scrn00_OnChange_ElevAvalFlg extends S21CommonHandler {

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

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        scrnMsg.elevApptReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        controlElevApptReqFlg(scrnMsg);

        controlElevAvalFlg(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.elevAvalFlg_SS);
    }
}
