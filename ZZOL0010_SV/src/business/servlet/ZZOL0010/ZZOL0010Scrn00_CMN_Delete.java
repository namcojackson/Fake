/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;


import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZOL0010Scrn00_CMN_Delete extends S21CommonHandler implements ZZOL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
		ZZOL0010CMsg bizMsg = new ZZOL0010CMsg();
		bizMsg.setBusinessID("ZZOL0010");
		bizMsg.setFunctionCode("40");

		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
		ZZOL0010CMsg bizMsg  = (ZZOL0010CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // Table Color Controll
        S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
               
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        } else {
            ZZOL0010CommonLogic.convertFlagDisplay(scrnMsg, bizMsg);
            ZZOL0010CommonLogic.convertTimeDisplay(scrnMsg);
            if (scrnMsg.A.getValidCount() == 0) {
                ZZOL0010CommonLogic.unDisdeleteCommonButton(this);
            }
        }
    }

}
