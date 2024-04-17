/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6350;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6350.NMAL6350CMsg;
import business.servlet.NMAL6350.common.NMAL6350CommonLogic;
import business.servlet.NMAL6350.constant.NMAL6350Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6350Scrn00_PagePrev extends S21CommonHandler implements NMAL6350Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6350BMsg scrnMsg = (NMAL6350BMsg) bMsg;
        NMAL6350CommonLogic.checkDetail(scrnMsg);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        NMAL6350BMsg scrnMsg = (NMAL6350BMsg) bMsg;

        NMAL6350CMsg bizMsg = NMAL6350CommonLogic.setBizFunction("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6350BMsg scrnMsg = (NMAL6350BMsg) bMsg;
        NMAL6350CMsg bizMsg  = (NMAL6350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6350CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        
        scrnMsg.setFocusItem(scrnMsg.A.no(0).pkgUomSortNum_A1);
        
	}

}
