/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8830;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8830Scrn00_MoveWin_History_Detail
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8830Scrn00_MoveWin_History_Detail extends S21CommonHandler {

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

        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

        int selectedIndex = getButtonSelectNumber();
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).wfProcPk_P, scrnMsg.wfProcPk);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).wfWrkItemPk_P, scrnMsg.B.no(selectedIndex).wfWrkItemPk_B);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).wfProcStsCd_P, scrnMsg.wfProcStsCd);
        scrnMsg.P.setValidCount(1);
        setArgForSubScreen(scrnMsg.P);
    }
}
