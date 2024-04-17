/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2015/10/19   Hitachi        Y.Tsuchimoto       Update          N/A(No Mark up comment)
 * 
 *</pre>
 */
public class NSAL0020Scrn00_Disp_Configuration_Maintenance extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;
//
//        Object[] params = new Object[2];
//
//        int index = scrnMsg.xxRadioBtn.getValueInt();
//
//        params[0] = scrnMsg.A.no(index).svcMachTpCd_A0;
//        params[1] = scrnMsg.A.no(index).svcMachMstrPk_A0;
//
//        setArgForSubScreen(params);

    }
}
