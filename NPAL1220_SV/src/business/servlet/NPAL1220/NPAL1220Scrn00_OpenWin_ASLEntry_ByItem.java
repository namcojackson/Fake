/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1220.NPAL1220CMsg;
//import business.servlet.NPAL1220.constant.NPAL1220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1220 ASL Search
 * Function Name : Open Return to ASL Entry(NPAL1230)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/19/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NPAL1220Scrn00_OpenWin_ASLEntry_ByItem extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;

        // parameter for ASL Entry screen.
        Object[] params = new Object[2];
        int idx = getButtonSelectNumber();
        params[0] = scrnMsg.A.no(idx).aslHdrPk_A1;
        params[1] = scrnMsg.A.no(idx).mdseCd_A1;

        super.setArgForSubScreen(params);

    }
}
