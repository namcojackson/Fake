/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1010.common.NLCL1010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 * 2016/03/02   Hitachi         T.Tomita        Update          QC#3586
 *</pre>
 */
public class NLCL1010Scrn00_OpenWin_SrchOrigMdseCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        NLCL1010CommonLogic.clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.origMdseCd_C0);

        // START 2016/03/02 T.Tomita [QC#3586 MOD]
        setArgForSubScreen(NLCL1010CommonLogic.toArray_popup(scrnMsg.P, 7));
        // END 2016/03/02 T.Tomita [QC#3586 MOD]
    }
}
