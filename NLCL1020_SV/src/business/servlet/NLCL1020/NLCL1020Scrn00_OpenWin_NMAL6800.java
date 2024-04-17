/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tomita        Create          QC#3586
 *</pre>
 */
public class NLCL1020Scrn00_OpenWin_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        if (scrnMsg.mdseCd_HD.isInputProtected()) {
            scrnMsg.setMessageInfo(NLCL1020Constant.NLCM0111E, new String[] {"MDSE", "Protected" });
            throw new EZDFieldErrorException();
        }
        NLCL1020CommonLogic.resetSubmitDoubleCheck(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        Object[] params = NLCL1020CommonLogic.otherBizConnectTo_NLCL1020Scrn00_OpenWin_NMAL6800(scrnMsg);

        setArgForSubScreen(params);
    }
}
