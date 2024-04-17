/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0580;

import static business.servlet.NSAL0580.constant.NSAL0580Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; 
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0580.NSAL0580CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/16   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL0580Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

        NSAL0580CMsg bizMsg = new NSAL0580CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;
        NSAL0580CMsg bizMsg = (NSAL0580CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
