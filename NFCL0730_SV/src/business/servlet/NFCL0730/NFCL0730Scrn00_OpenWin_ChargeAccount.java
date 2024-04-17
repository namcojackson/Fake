/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0730;

import static business.servlet.NFCL0730.constant.NFCL0730Constant.FUNC_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0730.NFCL0730CMsg;
import business.servlet.NFCL0730.common.NFCL0730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/10   Hitachi         S.Naya          Create          QC#57252
 *</pre>
 */
public class NFCL0730Scrn00_OpenWin_ChargeAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

        NFCL0730CMsg bizMsg = new NFCL0730CMsg();
        bizMsg.setBusinessID(FUNC_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;
        NFCL0730CMsg bizMsg  = (NFCL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = NFCL0730CommonLogic.getParamForChargeAccount(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_H1);
        scrnMsg.putErrorScreen();

        setArgForSubScreen(params);
    }
}
