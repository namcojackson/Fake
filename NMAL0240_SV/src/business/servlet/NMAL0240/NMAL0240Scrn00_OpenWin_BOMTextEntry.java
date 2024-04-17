/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240Scrn00_OpenWin_BOMTextEntry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/14   CITS            K.Ogino         Create          QC#22324
 *</pre>
 */
public class NMAL0240Scrn00_OpenWin_BOMTextEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            scrnMsg.mdseCd.setErrorInfo(1, ZZM9000E, new String[] {"BOM Item#" });
        }
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        Object[] param = new Object[2];
        param[0] = scrnMsg.mdseCd;
        param[1] = scrnMsg.R;
        setArgForSubScreen(param);

    }
}
