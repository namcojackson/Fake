/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import static business.servlet.NFBL2060.constant.NFBL2060Constant.OPENWIN_GL_CHRG_ACCT_FROM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2060.common.NFBL2060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/08   Fujitsu         C.Tanaka        Create          QC#12040
 *</pre>
 */
public class NFBL2060Scrn00_OpenWin_GlChrgAcctFrom extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FR);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        Object[] params = NFBL2060CommonLogic.getParamForChargeAccount(scrnMsg, scrnMsg.xxCmntTxt_FR);

        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FR);
        scrnMsg.putErrorScreen();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, OPENWIN_GL_CHRG_ACCT_FROM);
        setArgForSubScreen(params);
    }
}
