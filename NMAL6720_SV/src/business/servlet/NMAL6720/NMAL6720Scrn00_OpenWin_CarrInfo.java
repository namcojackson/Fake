/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/01/13   Hitachi         H.Watanabe      Create          QC#60860
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_CarrInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CommonLogic.exceptMandatoryError(scrnMsg);
        NMAL6720CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        int index = getButtonSelectNumber();

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm_P.setValue("OTBD_CARR_V");
        scrnMsg.P.no(1).xxPopPrm_P.setValue("CARR_CD");
        scrnMsg.P.no(2).xxPopPrm_P.setValue("CARR_NM");
        scrnMsg.P.no(3).xxPopPrm_P.setValue("CARR_CD");
        scrnMsg.P.no(4).xxPopPrm_P.setValue("Carrier Popup");
        scrnMsg.P.no(5).xxPopPrm_P.setValue("Carrier Code");
        scrnMsg.P.no(6).xxPopPrm_P.setValue("Carrier Name");
        scrnMsg.P.no(7).xxPopPrm_P.setValue("Carrier Code");
        scrnMsg.P.no(8).xxPopPrm_P.setValue("Carrier Name");
        scrnMsg.P.no(10).xxPopPrm_P.setValue(scrnMsg.I.no(index).carrNm_I3.getValue());

        Object[] params = NMAL6720CommonLogic.toArray_popup(scrnMsg.P, 11);
        scrnMsg.xxScrEventNm_P.setValue(ctx.getEventName());

        setArgForSubScreen(params);
    }
}
