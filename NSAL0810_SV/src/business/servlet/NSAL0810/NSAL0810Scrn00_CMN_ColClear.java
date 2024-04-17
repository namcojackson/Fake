/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.constant.NSAL0810Constant.BUSINESS_ID;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.FUNCTION_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0810.NSAL0810CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/03/21   Hitachi         K.Yamada        Update          CSA QC#5541
 * 2016/08/22   Hitachi         T.Mizuki        Update          CSA QC#12085
 *</pre>
 */
public class NSAL0810Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;

        NSAL0810CMsg bizMsg = new NSAL0810CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // mod start 2016/03/21 CSA Defect#5541
        return bizMsg;
        // mod end 2016/03/21 CSA Defect#5541
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // mod start 2016/08/22 CSA QC#12085
        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CMsg bizMsg  = (NSAL0810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // mod end 2016/08/22 CSA QC#12085
    }
}
