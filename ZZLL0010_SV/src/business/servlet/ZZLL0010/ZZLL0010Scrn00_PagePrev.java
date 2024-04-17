/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZLL0010;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZLL0010.ZZLL0010CMsg;

import business.servlet.ZZLL0010.common.ZZLL0010CommonLogic;
import business.servlet.ZZLL0010.constant.ZZLL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZLL0010Scrn00_PagePrev extends S21CommonHandler implements ZZLL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
        scrnMsg.xxPageShowToNum.clear();
        
		ZZLL0010CMsg bizMsg = new ZZLL0010CMsg();
		bizMsg.setBusinessID("ZZLL0010");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
		ZZLL0010CMsg bizMsg  = (ZZLL0010CMsg) cMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        ZZLL0010CommonLogic.convertTimeToDisplay(scrnMsg, bizMsg);
        S21TableColorController tblColor = new S21TableColorController(ZZLL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        scrnMsg.A.setInputProtected(true);

	}

}
