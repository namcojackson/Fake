/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0750;

import static business.servlet.NFCL0750.constant.NFCL0750Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0750.NFCL0750CMsg;
import business.servlet.NFCL0750.common.NFCL0750CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 *</pre>
 */
public class NFCL0750Scrn00_OpenWin_Detail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;

        NFCL0750CMsg bizMsg = new NFCL0750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        NFCL0750CommonLogic.initParam(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        NFCL0750CMsg bizMsg  = (NFCL0750CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        Object[] params = new Object[NFCL0760_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.arWrtOffRqstPk_P;
        params[i++] = scrnMsg.wrtOffRqstGrpCd_P;
        params[i++] = scrnMsg.wrtOffRqstUsrId_P;
        params[i++] = scrnMsg.arWrtOffRqstTpCd_P;
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        params[i++] = scrnMsg.xxFromDt_P;
        params[i++] = scrnMsg.xxThruDt_P;
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        setArgForSubScreen(params);
    }
}
