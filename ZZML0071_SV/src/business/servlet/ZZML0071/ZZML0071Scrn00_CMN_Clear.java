/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0071;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZML0071.ZZML0071CMsg;
import business.servlet.ZZML0071.common.ZZML0071Scrn00CommonLogic;
import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0071Scrn00_CMN_Clear extends S21CommonHandler implements ZZML0071Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

		ZZML0071CMsg bizMsg = new ZZML0071CMsg();
		bizMsg.setBusinessID("ZZML0071");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
		ZZML0071CMsg bizMsg  = (ZZML0071CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.glblCmpyCd.setInputProtected(scrnMsg.A.getValidCount() > 0);
        for ( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
            scrnMsg.A.no(i).mlGrpDescTxt_A.setInputProtected(true);
        }
        if ( scrnMsg.A.getValidCount() == 0 ) {
            scrnMsg.A.setValidCount(1);
            scrnMsg.A.no(0).mlGrpDescTxt_A.setInputProtected(true);
        }
        
        ZZML0071Scrn00CommonLogic.setButtonPropertiesInit(this);
        
        scrnMsg.setFocusItem( scrnMsg.glblCmpyCd );
        scrnMsg.glblCmpyCd.setInputProtected(true);
	}

}
