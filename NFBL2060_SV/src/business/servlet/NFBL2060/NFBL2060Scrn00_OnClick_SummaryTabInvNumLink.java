/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/03   Hitachi         K.Kojima        Update          QC#12589
 *</pre>
 */
public class NFBL2060Scrn00_OnClick_SummaryTabInvNumLink extends S21CommonHandler implements NFBL2060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        // START 2016/08/03 K.Kojima [QC#12589,MOD]
        // Object[] params = new Object[1];
        // params[0] =
        // scrnMsg.S.no(getButtonSelectNumber()).apVndInvNum_S1;
        Object[] params = new Object[3];
        params[0] = scrnMsg.S.no(getButtonSelectNumber()).apVndCd_S1;
        params[1] = scrnMsg.S.no(getButtonSelectNumber()).apVndInvNum_S1;
        params[2] = scrnMsg.S.no(getButtonSelectNumber()).apVndInvSqNum_S1;
        // END 2016/08/03 K.Kojima [QC#12589,MOD]

        setArgForSubScreen(params);

    }
}
