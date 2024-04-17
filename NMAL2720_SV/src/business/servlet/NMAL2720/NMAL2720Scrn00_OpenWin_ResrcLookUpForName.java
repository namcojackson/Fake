/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_OpenWin_ResrcLookUpForName
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Scrn00_OpenWin_ResrcLookUpForName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, false);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getLastGuard());
        Object[] params = NMAL2720CommonLogic.setParamForResourceLookup(scrnMsg);

        setArgForSubScreen(params);
    }
}
