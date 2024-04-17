/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Open_SerTrxSeach Serial Transaction Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NLCL0280Scrn00_Open_SerTrxSeach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Get Select button Number
        int index = getButtonSelectNumber();

        // Set Parameter
        Object[] params = new Object[3];
        params[0] = null;

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).rwsNum_A1)) {

            params[1] = scrnMsg.A.no(index).mdseCd_A1;
            params[2] = scrnMsg.A.no(index).rwsNum_A1;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).soNum_A1)) {

            params[1] = scrnMsg.A.no(index).mdseCd_A1;
            params[2] = scrnMsg.A.no(index).soNum_A1;

        } else {

            params[1] = scrnMsg.A.no(index).mdseCd_A1;
            params[2] = scrnMsg.A.no(index).invtyTrxSlpNum_A1;

        }

        setArgForSubScreen(params);

        // Multi Screen Open
        openMultiModeScreen();
    }
}
