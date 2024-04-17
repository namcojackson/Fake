/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NPAL1140.NPAL1140CMsg;
// import business.servlet.NPAL1140.constant.NPAL1140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 07/10/2013   Hitachi         K.Kishimoto     Update          QC1233
 *</pre>
 */
public class NPAL1140Scrn00_poEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;

        Object[] param = new Object[1];
        param[0] = scrnMsg.poOrdNum_HT;
        // START 07/10/2013 K.Kishimoto QC1233 Update
        setArgForSubScreen(param);
        // END 07/10/2013 K.Kishimoto QC1233 Update
    }
}
