/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0360;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0360.NSAL0360CMsg;
import business.servlet.NSAL0360.common.NSAL0360CommonLogic;
import business.servlet.NSAL0360.constant.NSAL0360Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitachi         T.Tomita        Update          N/A
 *</pre>
 */
public class NSAL0360Scrn00_Schedules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;
        NSAL0360CommonLogic.inputCheck(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;
        NSAL0360CMsg bizMsg = new NSAL0360CMsg();
        bizMsg.setBusinessID(NSAL0360Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;
        NSAL0360CMsg bizMsg = (NSAL0360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
            throw new EZDFieldErrorException();
        }

        NSAL0360CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        // START 2015/10/23 T.Tomita [N/A, MOD]
        scrnMsg.xxPopPrm_P0.setValue(SVC_INV_CHRG_TP.METER_CHARGE);
        Object[] params = new Object[3];
        params[0] = scrnMsg.dsContrDtlPk_H1;
        params[1] = scrnMsg.dsContrPrcEffPk_HD;
        params[2] = scrnMsg.xxPopPrm_P0;
        // END 2015/10/23 T.Tomita [N/A, MOD]

        setArgForSubScreen(params);
    }
}
