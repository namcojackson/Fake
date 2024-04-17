/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;
import static business.servlet.NFDL0090.common.NFDL0090CommonLogic.*;
import static business.servlet.NFDL0090.constant.NFDL0090Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/24   Hitachi         S.Naya          Create          QC#57252
 *</pre>
 */
public class NFDL0090Scrn00_OpenWin_ChargeAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FS);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        NFDL0090CMsg bizMsg = new NFDL0090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg  = (NFDL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = getParamForChargeAccount(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FS);
        scrnMsg.putErrorScreen();

        setArgForSubScreen(params);
    }
}
