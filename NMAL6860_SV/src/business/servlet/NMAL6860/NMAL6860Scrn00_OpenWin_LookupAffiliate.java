/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInitParam_Affiliation;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.getParamOpenWin_NMAL6050;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;




import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NMAL6860Scrn00_OpenWin_LookupAffiliate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;

        // Init param
        setInitParam_Affiliation(scrnMsg);

        // Pass param
        Object[] params = getParamOpenWin_NMAL6050(scrnMsg);

        setArgForSubScreen(params);
    }
}
