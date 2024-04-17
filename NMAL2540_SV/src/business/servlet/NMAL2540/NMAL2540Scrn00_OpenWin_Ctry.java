/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2540;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2540.common.NMAL2540CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2540Scrn00_OpenWin_Ctry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2540BMsg scrnMsg = (NMAL2540BMsg) bMsg;

        // Initialization of sub screen delivery information
        NMAL2540CommonLogic.setInitParamForCtryPopup(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd_G2, scrnMsg.ctryCd_H1);

        // Making of sub screen delivery information
        Object[] params = NMAL2540CommonLogic.setParamForCtryPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
