/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_OpenWin_Territory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_EV, BigDecimal.valueOf(eventLine));

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        scrnMsg.asgTrtyAttrbCd.clear();

        NMAL2460CommonLogic.clearParam(scrnMsg);
        Object[] params = NMAL2460CommonLogic.setParamForTerritoryPopup(scrnMsg);
        setArgForSubScreen(params);
    }
}
