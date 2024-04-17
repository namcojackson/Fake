/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0040;

import java.io.UnsupportedEncodingException;

import parts.common.*;
import parts.servletcommon.*;
import business.blap.ZZML0040.ZZML0040CMsg;

import business.servlet.ZZML0040.common.ZZML0040CommonLogic;
import business.servlet.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0040Scrn01_CMN_Submit extends S21CommonHandler implements ZZML0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd_01 );
        scrnMsg.addCheckItem( scrnMsg.mlTmplId_01 );
        scrnMsg.addCheckItem( scrnMsg.mlLocId_01 );
        scrnMsg.addCheckItem( scrnMsg.mlSubjTmplTxt_01 );

        try {
            int len = scrnMsg.mlSubjTmplTxt_01.getValue().getBytes("UTF-8").length;
            int maxLen = scrnMsg.getAttribute("mlSubjTmplTxt_01").getDigit();
            
            if ( len > maxLen ) {
                scrnMsg.mlSubjTmplTxt_01.setErrorInfo( 1, "ZZM9001E", new String[] { "Subject" } );
            }
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            scrnMsg.mlSubjTmplTxt_01.setErrorInfo( 1, "ZZM9001E", new String[] { "Subject" } );
        }
        
        scrnMsg.addCheckItem( scrnMsg.xxMlBodyTxt_01 );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;

		ZZML0040CMsg bizMsg = new ZZML0040CMsg();
		bizMsg.setBusinessID("ZZML0040");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZML0040BMsg scrnMsg = (ZZML0040BMsg) bMsg;
		ZZML0040CMsg bizMsg  = (ZZML0040CMsg) cMsg;

        // If error, no go back to the caller.
        if (bizMsg.getMessageKind().equals("E")) { // fail
            throw new EZDFieldErrorException();
        }
        
		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.glblCmpyCd_01.setInputProtected( true );
        scrnMsg.mlTmplId_01.setIndispensable( true );

        scrnMsg.setFocusItem( scrnMsg.mlTmplId_01 );
        
        scrnMsg.clearGUIAttribute(screenId_01,"mlSubjTmplTxt_01");
        scrnMsg.clearGUIAttribute(screenId_01,"xxMlBodyTxt_01");

        ZZML0040CommonLogic.setButtonScrn00(this);
        setButtonEnabled(CMN_BTN7[0], scrnMsg.A.getValidCount() > 0);
        setButtonEnabled(CMN_BTN9[0], false);
	}

}
