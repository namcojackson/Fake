/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0820;

import static business.servlet.NSAL0820.constant.NSAL0820Constant.BUSINESS_ID;
import static business.servlet.NSAL0820.constant.NSAL0820Constant.FUNCTION_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0820.NSAL0820CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/08/22   Hitachi         T.Mizuki        Update          CSA QC#12085
 *</pre>
 */
public class NSAL0820Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;

        NSAL0820CMsg bizMsg = new NSAL0820CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // mod start 2016/08/22 CSA QC#12085
        NSAL0820BMsg scrnMsg = (NSAL0820BMsg) bMsg;
        NSAL0820CMsg bizMsg  = (NSAL0820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // mod end 2016/08/22 CSA QC#12085
    }
}
