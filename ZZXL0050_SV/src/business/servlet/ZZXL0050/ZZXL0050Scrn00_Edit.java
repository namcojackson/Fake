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
import business.servlet.ZZXL0050.common.ZZXL0050CommonLogic;
import business.servlet.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0050Scrn00_Edit extends S21CommonHandler implements ZZXL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

        scrnMsg.glblCmpyCd.setNameForMessage(   "Global Company CD");
        scrnMsg.dtProcCd_01.setNameForMessage(  "Date Type");
        scrnMsg.dtMgtPgmId_01.setNameForMessage("Program ID");
        scrnMsg.mgtDt_01.setNameForMessage("Date");

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.dtProcCd );
        scrnMsg.addCheckItem( scrnMsg.dtMgtPgmId );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

        ZZXL0050CMsg bizMsg = new ZZXL0050CMsg();
        bizMsg.setBusinessID("ZZXL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // selected row number
        int selected =  getButtonSelectNumber();
        bizMsg.xxRowNum.setValue(selected);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;
        ZZXL0050CMsg bizMsg  = (ZZXL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.dtProcCd );
        scrnMsg.addCheckItem( scrnMsg.dtMgtPgmId );
        scrnMsg.putErrorScreen();

        ZZXL0050CommonLogic.setButtonScrn01(this);

        scrnMsg.glblCmpyCd.setInputProtected( true );
        scrnMsg.dtProcCd_01.setInputProtected( true );
        scrnMsg.dtMgtPgmId_01.setIndispensable( true );
        scrnMsg.setFocusItem( scrnMsg.dtMgtPgmId_01 );
	}

}
