/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_OpenWin_Carr
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2030Scrn00_OpenWin_Carr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "OTBD_CARR_V");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "CARR_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "CARR_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "CARR_SORT_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Carrier Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.carrCd);
        scrnMsg.P.no(i++).clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[11];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        setArgForSubScreen(params);
    }
}