/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;
//import business.servlet.NMAL2460.constant.NMAL2460Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_OnChangeSavedSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        NMAL2460CMsg bizMsg = new NMAL2460CMsg();
        bizMsg.setBusinessID("NMAL2460");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        NMAL2460CMsg bizMsg  = (NMAL2460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }
}
