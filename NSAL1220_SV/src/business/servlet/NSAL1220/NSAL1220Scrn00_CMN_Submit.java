/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1220;

import static business.servlet.NSAL1220.common.NSAL1220CommonLogic.addCheckItem;
import static business.servlet.NSAL1220.constant.NSAL1220Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1220.NSAL1220CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;
        NSAL1220CMsg bizMsg = new NSAL1220CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;
        NSAL1220CMsg bizMsg  = (NSAL1220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
    }
}
