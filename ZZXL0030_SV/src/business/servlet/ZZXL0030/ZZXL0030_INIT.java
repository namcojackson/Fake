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
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZXL0030.ZZXL0030CMsg;
import business.servlet.ZZXL0030.common.ZZXL0030CommonLogic;
import business.servlet.ZZXL0030.constant.ZZXL0030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0030_INIT extends S21CommonHandler implements ZZXL0030Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	  //security violation check of this screen.
      checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZXL0030BMsg scrnMsg = (ZZXL0030BMsg) bMsg;
		
        ZZXL0030CommonLogic.setDisplayNameForMessage(scrnMsg);
        
		scrnMsg.xxMthDt_D2.no(0).setValue("01");
		scrnMsg.xxMthDt_D2.no(1).setValue("02");
		scrnMsg.xxMthDt_D2.no(2).setValue("03");
		scrnMsg.xxMthDt_D2.no(3).setValue("04");
		scrnMsg.xxMthDt_D2.no(4).setValue("05");
		scrnMsg.xxMthDt_D2.no(5).setValue("06");
		scrnMsg.xxMthDt_D2.no(6).setValue("07");
		scrnMsg.xxMthDt_D2.no(7).setValue("08");
		scrnMsg.xxMthDt_D2.no(8).setValue("09");
		scrnMsg.xxMthDt_D2.no(9).setValue("10");
		scrnMsg.xxMthDt_D2.no(10).setValue("11");
		scrnMsg.xxMthDt_D2.no(11).setValue("12");		

		scrnMsg.xxMthDt_D1.no(0).setValue("0");
		scrnMsg.xxMthDt_D1.no(1).setValue("1");
		scrnMsg.xxMthDt_D1.no(2).setValue("2");
		scrnMsg.xxMthDt_D1.no(3).setValue("3");
		scrnMsg.xxMthDt_D1.no(4).setValue("4");
		scrnMsg.xxMthDt_D1.no(5).setValue("5");
		scrnMsg.xxMthDt_D1.no(6).setValue("6");
		scrnMsg.xxMthDt_D1.no(7).setValue("7");
		scrnMsg.xxMthDt_D1.no(8).setValue("8");
		scrnMsg.xxMthDt_D1.no(9).setValue("9");
		scrnMsg.xxMthDt_D1.no(10).setValue("10");
		scrnMsg.xxMthDt_D1.no(11).setValue("11");	
		
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
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
		this.setButtonEnabled("NextMonth",false);
		this.setButtonEnabled("PrevMonth",false);
		this.setButtonEnabled("CheckWeekDaysA",false);
		this.setButtonEnabled("CheckWeekDaysB",false);	
		
		ZZXL0030CommonLogic.initCommonButtons(this);
		
        EZDGUIAttribute glblCmpyTable = new EZDGUIAttribute("ZZXL0030Scrn00", "glblCmpyTable");
        if (SYS_FLG_PARTS.contains(SYS_FLG)) {
        	glblCmpyTable.setVisibility(false);
        } else {
            // S21
        	glblCmpyTable.setVisibility(true);
        }
        scrnMsg.addGUIAttribute(glblCmpyTable);

        scrnMsg.xxYrDt.setNameForMessage("Year");
	}
}