/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/01/13   Hitachi         H.Watanabe      Create          QC#60860
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_CarrInfo extends S21CommonHandler {
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CommonLogic.clearMandatoryError(scrnMsg);
        NMAL6700CommonLogic.addCheckItem(scrnMsg, getGlobalCompanyCode());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        int index = getButtonSelectNumber();

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.setValue("OTBD_CARR_V");
        scrnMsg.P.no(1).xxPopPrm.setValue("CARR_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("CARR_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("CARR_CD");
        scrnMsg.P.no(4).xxPopPrm.setValue("Carrier Popup");
        scrnMsg.P.no(5).xxPopPrm.setValue("Carrier Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("Carrier Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("Carrier Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("Carrier Name");
        scrnMsg.P.no(10).xxPopPrm.setValue(scrnMsg.M.no(index).carrNm_M3.getValue());

        Object[] params = NMAL6700CommonLogic.toArray_popup(scrnMsg.P, 11);

        setArgForSubScreen(params);
    }
}
