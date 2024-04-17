/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0860;

import static business.servlet.NSAL0860.constant.NSAL0860Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0860.common.NSAL0860CommonLogic;

import business.blap.NSAL0860.NSAL0860CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 *  06/06/2016   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL0860_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;

        NSAL0860CMsg bizMsg = new NSAL0860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        NSAL0860CMsg bizMsg = (NSAL0860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0860CommonLogic.screenControlProcessForInit(this, scrnMsg, functionList);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0860BMsg scrnMsg = (NSAL0860BMsg) bMsg;
        scrnMsg.serNum.setNameForMessage("Serial#");
        scrnMsg.dsAcctNm.setNameForMessage("Customer Name");
        scrnMsg.mdlNm.setNameForMessage("Model Name");
        scrnMsg.iwrCondCd.setNameForMessage("IWR Status");
        scrnMsg.curLocAcctNum.setNameForMessage("Customer#");
        scrnMsg.dsContrNum.setNameForMessage("Contract#");

    }
}
