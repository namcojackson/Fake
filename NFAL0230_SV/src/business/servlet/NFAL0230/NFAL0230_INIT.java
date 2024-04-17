/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0230;

import static business.servlet.NFAL0230.constant.NFAL0230Constant.BIZ_ID;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.FUNCTION_CD;
import static business.servlet.NFAL0230.constant.NFAL0230Constant.NZZM0012E;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0230.NFAL0230CMsg;
import business.servlet.NFAL0230.common.NFAL0230CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NFAL0230_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0230BMsg scrnMsg = (NFAL0230BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.coaSegNm.setValue(param0.getValue());
            scrnMsg.arcsSplyCoaSegTxt.setValue(NFAL0230CommonLogic.getSegVlaueName(scrnMsg));

        } else {
            scrnMsg.setMessageInfo(NZZM0012E, new String[] {"Segment Name" });
            return null;
        }

        NFAL0230CMsg bizMsg = new NFAL0230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0230BMsg scrnMsg = (NFAL0230BMsg) bMsg;
        NFAL0230CMsg bizMsg = (NFAL0230CMsg) cMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            NFAL0230CommonLogic.initialControlScreen(this, scrnMsg);
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0230CommonLogic.convertTreeInfo(scrnMsg, bizMsg.A);
        NFAL0230CommonLogic.initialControlScreen(this, scrnMsg);

    }
}
