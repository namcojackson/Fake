/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZZL0050;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZZL0050.ZZZL0050CMsg;
import business.servlet.ZZZL0050.common.ZZZL0050CommonLogic;
import business.servlet.ZZZL0050.constant.ZZZL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZZL0050Scrn00_View extends S21CommonHandler implements ZZZL0050Constant {

    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        ZZZL0050BMsg scrnMsg = (ZZZL0050BMsg) bMsg;
//        ZZZL0050CommonLogic.checkItem(scrnMsg);
//        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZZL0050BMsg scrnMsg = (ZZZL0050BMsg) bMsg;

        ZZZL0050CMsg bizMsg = new ZZZL0050CMsg();
        bizMsg.setBusinessID("ZZZL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZZL0050BMsg scrnMsg = (ZZZL0050BMsg) bMsg;
		ZZZL0050CMsg bizMsg  = (ZZZL0050CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        scrnMsg.setInputProtected(false);
        scrnMsg.curPldQueueNum.setInputProtected(true);
        scrnMsg.glblCmpyCd.setInputProtected(true);
        ZZZL0050CommonLogic.screenControlBySearchResult(this, true);
	}
}
