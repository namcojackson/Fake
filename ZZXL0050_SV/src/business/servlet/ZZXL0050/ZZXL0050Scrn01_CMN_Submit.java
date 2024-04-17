/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZXL0050;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZXL0050.ZZXL0050CMsg;
import business.servlet.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0050Scrn01_CMN_Submit extends S21CommonHandler implements ZZXL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.dtProcCd_01 );
        scrnMsg.addCheckItem( scrnMsg.dtMgtPgmId_01 );
        scrnMsg.addCheckItem( scrnMsg.mgtDt_01 );

        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

		ZZXL0050CMsg bizMsg = new ZZXL0050CMsg();
		bizMsg.setBusinessID("ZZXL0050");
		bizMsg.setFunctionCode("40");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;
		ZZXL0050CMsg bizMsg  = (ZZXL0050CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.dtProcCd_01 );
        scrnMsg.addCheckItem( scrnMsg.dtMgtPgmId_01 );
        scrnMsg.addCheckItem( scrnMsg.mgtDt_01 );
        scrnMsg.putErrorScreen();

        scrnMsg.glblCmpyCd.setInputProtected( true );
        scrnMsg.dtProcCd_01.setInputProtected( true );
        scrnMsg.dtMgtPgmId_01.setIndispensable( true );
        scrnMsg.setFocusItem( scrnMsg.dtMgtPgmId_01 );
    }

}
