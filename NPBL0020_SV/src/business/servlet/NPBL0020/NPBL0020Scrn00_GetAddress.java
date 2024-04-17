/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPBL0020.NPBL0020CMsg;
//import business.servlet.NPBL0020.constant.NPBL0020Constant;

import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    	NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.shipToPostCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

    	NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
    	NPBL0020CMsg bizMsg  = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.shipToPostCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCtyAddr);
        scrnMsg.addCheckItem(scrnMsg.shipToStCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCntyNm);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.shipToPostCd);

    }
}
