/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0840;

import static business.servlet.NSAL0840.constant.NSAL0840Constant.BUSINESS_ID;
import static business.servlet.NSAL0840.constant.NSAL0840Constant.FUNCTION_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0840.NSAL0840CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/08/22   Hitachi         T.Mizuki        Update          CSA QC#12085
 *</pre>
 */
public class NSAL0840Scrn00_CMN_ColClear extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;

        NSAL0840CMsg bizMsg = new NSAL0840CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // mod start 2016/08/22 CSA QC#12085
        NSAL0840BMsg scrnMsg = (NSAL0840BMsg) bMsg;
        NSAL0840CMsg bizMsg  = (NSAL0840CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // mod end 2016/08/22 CSA QC#12085
    }
}
