/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6550.NMAL6550CMsg;
import business.servlet.NMAL6550.common.NMAL6550CommonLogic;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6550_INIT extends S21INITCommonHandler implements NMAL6550Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	    checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        NMAL6550CMsg bizMsg = NMAL6550CommonLogic.setBizFunction("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
		NMAL6550CMsg bizMsg  = (NMAL6550CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).glblCmpySortNum_A1);
        NMAL6550CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
	}
    
	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).glblCmpySortNum_A1.setNameForMessage("Sort Num");
            scrnMsg.A.no(i).glblCmpyCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).glblCmpyNm_A1.setNameForMessage("Name");
            scrnMsg.A.no(i).glblCmpyDescTxt_A1.setNameForMessage("Description Text");
        }
    }

}
