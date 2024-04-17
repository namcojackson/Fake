/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
import static business.servlet.NMAL6700.constant.NMAL6700Constant.*;

import business.servlet.NMAL6700.common.NMAL6700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_Coa extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.P.clear();

        scrnMsg.P.no(0).xxPopPrm.setValue(APP_FUNC_ID_TRANSACTION);
        scrnMsg.P.no(2).xxPopPrm.setValue(scrnMsg.coaAfflCd_H1.getValue());
        scrnMsg.P.no(7).xxPopPrm.setValue(scrnMsg.coaChCd_H1.getValue());
        scrnMsg.P.no(4).xxPopPrm.setValue(scrnMsg.coaCcCd_F1.getValue());

        Object[] params = NMAL6700CommonLogic.toArray_popup(scrnMsg.P, 10);

        setArgForSubScreen(params);


    }
}
