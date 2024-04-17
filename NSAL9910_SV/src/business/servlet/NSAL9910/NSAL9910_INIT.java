/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9910;

import static business.servlet.NSAL9910.constant.NSAL9910Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL9910.NSAL9910CMsg;
import business.servlet.NSAL9910.common.NSAL9910CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * General Business Master Maintenance List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         A.Kohinata      Create          N/A
 * 2018/06/04   CITS            M.Naito         Update          QC#24320
 *</pre>
 */
public class NSAL9910_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID_NSAL9910);
        // START 2018/06/04 M.Naito [QC#24320, ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID_NSAL9900);
        // END 2018/06/04 M.Naito [QC#24320, ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL9910BMsg scrnMsg = (NSAL9910BMsg) bMsg;

        NSAL9910CommonLogic.getFuncId(scrnMsg);
        NSAL9910CommonLogic.getProcessId(ctx, scrnMsg);

        NSAL9910CMsg bizMsg = new NSAL9910CMsg();
        bizMsg.setBusinessID(BUSINESS_ID_NSAL9910);
        bizMsg.setFunctionCode(FUNC_SEARCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL9910BMsg scrnMsg = (NSAL9910BMsg) bMsg;
        NSAL9910CMsg bizMsg = (NSAL9910CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL9910CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }
}
