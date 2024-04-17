/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0550.NSAL0550CMsg;
import business.servlet.NSAL0550.constant.NSAL0550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/23   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL0550Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;

        NSAL0550CMsg bizMsg = new NSAL0550CMsg();
        bizMsg.setBusinessID(NSAL0550Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0550Constant.FUCNTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;
        NSAL0550CMsg bizMsg  = (NSAL0550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
