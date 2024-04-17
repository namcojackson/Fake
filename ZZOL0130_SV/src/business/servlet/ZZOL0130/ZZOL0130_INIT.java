/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0130;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZOL0130.ZZOL0130CMsg;
import business.servlet.ZZOL0130.common.ZZOL0130CommonLogic;
import business.servlet.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZOL0130_INIT extends S21INITCommonHandler implements ZZOL0130Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	    //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;

		ZZOL0130CMsg bizMsg = new ZZOL0130CMsg();
		bizMsg.setBusinessID("ZZOL0130");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
		ZZOL0130CMsg bizMsg  = (ZZOL0130CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Common Button
        setButtonProperties(BTN_CMN_BTN1[0]  , BTN_CMN_BTN1[1]  , BTN_CMN_BTN1[2]  , 0, null);
        setButtonProperties(BTN_CMN_BTN2[0]  , BTN_CMN_BTN2[1]  , BTN_CMN_BTN2[2]  , 0, null);
        setButtonProperties(BTN_CMN_BTN3[0]  , BTN_CMN_BTN3[1]  , BTN_CMN_BTN3[2]  , 0, null);
        setButtonProperties(BTN_CMN_BTN4[0]  , BTN_CMN_BTN4[1]  , BTN_CMN_BTN4[2]  , 0, null);
        setButtonProperties(BTN_CMN_BTN5[0]  , BTN_CMN_BTN5[1]  , BTN_CMN_BTN5[2]  , 0, null);
        setButtonProperties(BTN_CMN_BTN6[0]  , BTN_CMN_BTN6[1]  , BTN_CMN_BTN6[2]  , 0, null);
        setButtonProperties(BTN_CMN_BTN7[0]  , BTN_CMN_BTN7[1]  , BTN_CMN_BTN7[2]  , 1, null);
        setButtonProperties(BTN_CMN_BTN8[0]  , BTN_CMN_BTN8[1]  , BTN_CMN_BTN8[2]  , 1, null);
        setButtonProperties(BTN_CMN_BTN9[0]  , BTN_CMN_BTN9[1]  , BTN_CMN_BTN9[2]  , 1, null);
        setButtonProperties(BTN_CMN_BTN10[0] , BTN_CMN_BTN10[1] , BTN_CMN_BTN10[2] , 1, null);
        
        ZZOL0130CommonLogic.dspScrn(this, scrnMsg);

	}

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        ZZOL0130BMsg scrnMsg = (ZZOL0130BMsg) bMsg;
        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.delTblId.setNameForMessage("Delete Table ID");
        scrnMsg.specEffMthAot.setNameForMessage("Effective Months");
        scrnMsg.delExecDt.setNameForMessage("Delete Execution Date");
          
    }

}
