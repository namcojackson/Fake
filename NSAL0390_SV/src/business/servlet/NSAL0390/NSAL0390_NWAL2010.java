/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0390.NSAL0390CMsg;
import business.servlet.NSAL0390.common.NSAL0390CommonLogic;
import business.servlet.NSAL0390.constant.NSAL0390Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Hitachi         M.Gotou         Create          QC#4423
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0390_NWAL2010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;

        NSAL0390CMsg bizMsg = new NSAL0390CMsg();
        bizMsg.setBusinessID(NSAL0390Constant.BUSINESS_ID);
        // mod start 2016/08/29 CSA Defect#11243
        bizMsg.setFunctionCode(NSAL0390Constant.FUNCTION_SUBMIT);
        // mod end 2016/08/29 CSA Defect#11243
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        NSAL0390CMsg bizMsg = (NSAL0390CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0390CommonLogic.controlDtl(this, scrnMsg);
    }
}
