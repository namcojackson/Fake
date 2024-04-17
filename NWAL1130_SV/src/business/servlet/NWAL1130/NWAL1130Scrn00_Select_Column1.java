/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NWAL1130;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1130.common.NWAL1130CommonLogic;
import business.servlet.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Common PopUp NWAL1130Scrn00_Select_Column
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2012   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1130Scrn00_Select_Column1 extends S21CommonHandler implements NWAL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        NWAL1130CommonLogic.setResult(scrnMsg, scrnMsg.R, scrnMsg.A.no(selectIdx), COLUMN_0);

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            NWAL1130CommonLogic.setOutputParam((Object[]) arg, scrnMsg.R);
        }
    }
}
