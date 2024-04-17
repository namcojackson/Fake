/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2570.common.NMAL2570CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2570Scrn00_OpenWin_CommonPopUp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        // Initialization of sub screen delivery information
        NMAL2570CommonLogic.setInitParamForJobTtlPopup(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.jobTtlCd_G1, scrnMsg.jobTtlCd_H1);

        // Making of sub screen delivery information
        Object[] params = NMAL2570CommonLogic.setParamForJobTtlPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
