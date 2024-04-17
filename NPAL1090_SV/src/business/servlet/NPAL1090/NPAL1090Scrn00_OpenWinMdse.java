/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NPAL1090 Service Parts Request Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NPAL1090Scrn00_OpenWinMdse extends S21CommonHandler {

       @Override
        protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
            // no process
        }

        @Override
        protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
            return null;
        }

        @Override
        protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // scrnMsg.P.clear();
        //
        // scrnMsg.P.no(0).xxPopPrm_H1.clear();
        //
        // Object[] params = new Object[2];
        // params[0] = scrnMsg.mdseCd_H1;
        // params[1] = scrnMsg.P.no(0).xxPopPrm_H1;
        // setArgForSubScreen(params);
        }

}
