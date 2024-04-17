/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0051;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZOL0051.ZZOL0051CMsg;
import business.servlet.ZZOL0051.common.ZZOL0051CommonLogic;
import business.servlet.ZZOL0051.constant.ZZOL0051Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZOL0051Scrn00_CMN_Delete extends S21CommonHandler implements ZZOL0051Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

        // checkbox enable check
        int cnt = 0;
        String curttab = scrnMsg.xxDplyTab.getValue();

        if ( curttab.equals( TAB_BIZAPP ) ) {

            for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
                
                if ( scrnMsg.B.no(idx).xxChkBox_B.getValue().length() > 0 ) {
                    cnt++;
                }
            }
        } else if ( curttab.equals( TAB_PROCID ) ) {
            
            for (int idx = 0; idx < scrnMsg.P.getValidCount(); idx++) {
                
                if ( scrnMsg.P.no(idx).xxChkBox_P.getValue().length() > 0 ) {
                    cnt++;
                }
            }
        }
        
        if ( cnt == 0 ) {
            scrnMsg.setMessageInfo("ZZOM0011E", new String[] {"Delete" });
        }
        
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

		ZZOL0051CMsg bizMsg = new ZZOL0051CMsg();
		bizMsg.setBusinessID("ZZOL0051");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;
		ZZOL0051CMsg bizMsg  = (ZZOL0051CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZOL0051CommonLogic.setScreenInit(this, scrnMsg, bizMsg);
        
	}

}
