/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/23   Fujitsu         T.Aoi           Create          N/A
 * 2019/03/27   Fujitsu         K.Ishizuka      Update          S21_NA#30765
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_CpoOrdNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        // 2019/03/27 S21_NA#30765 Mod Start
        // Object[] params = new Object[1];
        Object[] params = new Object[2];
        params[0] = scrnMsg.ordSrcRefNum;
        params[1] = scrnMsg.cpoSrcTpCd;
        // 2019/03/27 S21_NA#30765 Mod End

        setArgForSubScreen(params);

    }
}
