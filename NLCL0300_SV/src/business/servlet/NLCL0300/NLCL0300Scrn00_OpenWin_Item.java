/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0300.common.NLCL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0300Scrn00_OpenWin_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_Item");
        Object[] params = NLCL0300CommonLogic.setArgumentNMAL6800(scrnMsg);
        setArgForSubScreen(params);
    }
}
