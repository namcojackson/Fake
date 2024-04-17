/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1230Scrn00_OpenWin_Qualifier extends S21CommonHandler {

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

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        // Making of subscreen delivery information
        Object[] params = NPAL1230CommonLogic.getParamNPAL1240(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
