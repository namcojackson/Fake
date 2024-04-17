/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tomita        Create          QC#3586
 *</pre>
 */
public class NLCL1020_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            scrnMsg.mdseCd_HD.setValue(scrnMsg.xxPopPrm_00.getValue());
            scrnMsg.mdseDescShortTxt_HD.setValue(scrnMsg.xxPopPrm_01.getValue());

            NLCL1020CommonLogic.scrnItemControl_NLCL1020_NMAL6800(scrnMsg);
        }
    }
}
