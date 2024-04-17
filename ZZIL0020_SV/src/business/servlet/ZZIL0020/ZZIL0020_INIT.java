/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0020;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0020.ZZIL0020CMsg;
import business.servlet.ZZIL0020.common.ZZIL0020CommonLogic;
import business.servlet.ZZIL0020.constant.ZZIL0020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZIL0020_INIT extends S21INITCommonHandler implements ZZIL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), businessId);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;

		ZZIL0020CMsg bizMsg = new ZZIL0020CMsg();
		bizMsg.setBusinessID("ZZIL0020");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;
		ZZIL0020CMsg bizMsg  = (ZZIL0020CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setButtonProperties( CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null );
        setButtonProperties( CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null );
        setButtonProperties( CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null );
        setButtonProperties( CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null );
        setButtonProperties( CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null );
        setButtonProperties( CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null );
        setButtonProperties( CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null );
        setButtonProperties( CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null );
        setButtonProperties( CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null );
        setButtonProperties( CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null );

        scrnMsg.xxTblNm.setNameForMessage("Table Name");
        scrnMsg.xxFileData.setNameForMessage("Upload Request");
        
        scrnMsg.xxUpldFileNm.setInputProtected(true);
        ZZIL0020CommonLogic.setBackgroundColor(scrnMsg, "xxUpldFileNm");
        
        scrnMsg.xxTblNm.setIndispensable(true);
        scrnMsg.setFocusItem( scrnMsg.xxTblNm );
	}

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        //ZZIL0020BMsg scrnMsg = (ZZIL0020BMsg) bMsg;
        
    }

}
