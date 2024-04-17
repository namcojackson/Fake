/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0040;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZML0040.ZZML0040CMsg;
import business.servlet.ZZML0040.common.ZZML0040CommonLogic;
import business.servlet.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0040Scrn00_View extends S21CommonHandler implements ZZML0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.mlTmplId );
        scrnMsg.addCheckItem( scrnMsg.mlSubjTmplTxt );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;

		ZZML0040CMsg bizMsg = new ZZML0040CMsg();
		bizMsg.setBusinessID("ZZML0040");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // selected row number
        int selected =  getButtonSelectNumber();
        bizMsg.xxRowNum.setValue(selected);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;
		ZZML0040CMsg bizMsg  = (ZZML0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.mlTmplId );
        scrnMsg.addCheckItem( scrnMsg.mlSubjTmplTxt );
        scrnMsg.putErrorScreen();
        
        ZZML0040CommonLogic.setButtonScrn01(this);
        setButtonEnabled(CMN_BTN2[0], false);

        EZDGUIAttribute subjAtt = new EZDGUIAttribute(screenId_01,"mlSubjTmplTxt_01");
        subjAtt.setStyleAttribute("background-color", "FBFBFB");
        scrnMsg.addGUIAttribute(subjAtt);
        
        EZDGUIAttribute bodyAtt = new EZDGUIAttribute(screenId_01,"xxMlBodyTxt_01");
        bodyAtt.setStyleAttribute("background-color", "FBFBFB");
        scrnMsg.addGUIAttribute(bodyAtt);

        scrnMsg.setInputProtected( true );
        
	}

}
