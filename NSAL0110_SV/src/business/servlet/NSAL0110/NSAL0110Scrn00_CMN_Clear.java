/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0110;

import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0110.NSAL0110CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSAL0110Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;

        NSAL0110CMsg bizMsg = new NSAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;
        NSAL0110CMsg bizMsg  = (NSAL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}

