/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NYEL8810.NYEL8810CMsg;
//import business.servlet.NYEL8810.constant.NYEL8810Constant;

import business.blap.NYEL8810.NYEL8810CMsg;
import business.servlet.NYEL8810.common.NYEL8810CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8810Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        NYEL8810CMsg bizMsg = new NYEL8810CMsg();
        bizMsg.setBusinessID("NYEL8810");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg  = (NYEL8810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }
}