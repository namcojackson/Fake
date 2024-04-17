/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_OpenWin_RqstHist
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Scrn00_OpenWin_RqstHist extends S21CommonHandler {

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

        // Mod Start 2016/07/13 QC#11069
//        Object[] params = NMAL2720CommonLogic.setParamForRequestHistory(scrnMsg, getGlobalCompanyCode());
        String[] params = new String[1];
        params[0] = BIZ_ID;
        // Mod Start 2016/07/13 QC#11069

        setArgForSubScreen(params);
    }
}
