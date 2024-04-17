/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 *</pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL4500.NMAL4500CMsg;
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL4500_INIT extends S21INITCommonHandler implements NMAL4500Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        this.setButtonConfirmMsg("CMN_Clear", "ZZM8101I", new String[] {"Clear" }, 0);
		this.setButtonConfirmMsg("CMN_Reset", "ZZM8101I", new String[] {"Reset" },  0);
		this.setButtonConfirmMsg("CMN_Return", "ZZM8102I", new String[] {"Return" }, 0);
		this.setButtonConfirmMsg("CMN_Submit", "ZZM8101I", new String[] {"Submit" }, 0);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;

		NMAL4500CMsg bizMsg = new NMAL4500CMsg();
		bizMsg.setBusinessID("NMAL4500");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
		
 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
		NMAL4500CMsg bizMsg  = (NMAL4500CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
        // Default value of radio button
        NMAL4500CommonLogic.initScreenRadio(this, scrnMsg);
        
        // Screen control
        NMAL4500CommonLogic.doScreenControl(this, scrnMsg, getUserProfileService());
        
        //set forcus
        scrnMsg.setFocusItem(scrnMsg.vndCd_01);
	}
	
	@Override
    protected void setNameForMessage(EZDBMsg bMsg) {
		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
		NMAL4500CommonLogic.setNameForMessage_INIT(scrnMsg);
	}

}
