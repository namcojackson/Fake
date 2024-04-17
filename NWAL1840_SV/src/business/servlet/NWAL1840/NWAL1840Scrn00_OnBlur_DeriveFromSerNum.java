/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Fujitsu         T.Murai         Create          N/A
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 *</pre>
 */
public class NWAL1840Scrn00_OnBlur_DeriveFromSerNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.serNum)) {
            scrnMsg.addCheckItem(scrnMsg.serNum);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID("NWAL1840");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.serNum)) {
            scrnMsg.setFocusItem(scrnMsg.serNum);
            // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.svcConfigMstrPk);
        NWAL1840CommonLogic.setProtect(this, scrnMsg);
    }
}
