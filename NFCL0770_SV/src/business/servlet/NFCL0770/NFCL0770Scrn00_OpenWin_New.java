/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 *</pre>
 */
public class NFCL0770Scrn00_OpenWin_New extends S21CommonHandler {
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        NFCL0770CMsg bizMsg = null;

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_OpenWin_New();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt());
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;
        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0770CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();



        Object[] params = NFCL0770CommonLogic.otherBusConnectTo_NFCL0770Scrn00_OpenWin_New(scrnMsg);

        setArgForSubScreen(params);


        setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);

    }
}
