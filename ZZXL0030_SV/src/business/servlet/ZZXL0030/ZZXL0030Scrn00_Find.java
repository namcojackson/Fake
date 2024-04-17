/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZXL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZXL0030.ZZXL0030CMsg;
import business.servlet.ZZXL0030.common.ZZXL0030CommonLogic;
import business.servlet.ZZXL0030.constant.ZZXL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0030Scrn00_Find extends S21CommonHandler implements ZZXL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
		ZZXL0030CMsg bizMsg = new ZZXL0030CMsg();
		bizMsg.setBusinessID("ZZXL0030");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
		ZZXL0030CMsg bizMsg  = (ZZXL0030CMsg) cMsg;
        
		// Check if modified data is detected.
		if (bizMsg.getMessageKind().equals("W")) { // fail
			scrnMsg.xxLastBtnNm.setValue(bizMsg.xxLastBtnNm.getValue());
            throw new EZDFieldErrorException();
		}
		
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
		ZZXL0030CommonLogic.disenableAllCheckBoxes(bMsg);
		
		// There is no calendar type on DB, therefore diseable all buttons except for "Find" button
		if (bizMsg.getMessageKind().equals("E")) { // fail
			
			this.setButtonEnabled(SEARCH_BUTTON_NAME,false);
			this.setButtonEnabled(TODAY_BUTTON_NAME,false);
			this.setButtonEnabled(NEXT_BUTTON_NAME,false);
			this.setButtonEnabled(PREV_BUTTON_NAME,false);
			this.setButtonEnabled(WEEKDAY_A_BUTTON_NAME,false);
			this.setButtonEnabled(WEEKDAY_B_BUTTON_NAME,false);	
			
			// Diseable Submit button
			ZZXL0030CommonLogic.changeSubmitButton(this, 0);
		} else {
			// Enable Seach & Today button.
			this.setButtonEnabled(SEARCH_BUTTON_NAME,true);
			this.setButtonEnabled(TODAY_BUTTON_NAME,true);
		}
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
	}
}
