/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 *</pre>
 */
public class NSAL0500_NMAL6050 extends S21CommonHandler {

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
        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        // 
        // if (!CMN_CLOSE.equals(getLastGuard())) {
        //     scrnMsg.setFocusItem(scrnMsg.contrTrmnFlg);
        // } else {
        //     scrnMsg.setFocusItem(scrnMsg.techTocCd);
        // }
        // END 2016/12/14 K.Kojima [QC#15653,DEL]
    }
}
