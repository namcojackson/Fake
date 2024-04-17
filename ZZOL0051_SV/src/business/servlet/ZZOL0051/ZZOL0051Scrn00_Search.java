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

public class ZZOL0051Scrn00_Search extends S21CommonHandler implements ZZOL0051Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.upldCsvFileId );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;

        scrnMsg.H.clear();
        for ( int i = 0; i < scrnMsg.H.length(); i++ ) {
            scrnMsg.H.no(i).clear();
        }
        scrnMsg.H.setValidCount(0);

		ZZOL0051CMsg bizMsg = new ZZOL0051CMsg();
		bizMsg.setBusinessID("ZZOL0051");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0051BMsg scrnMsg = (ZZOL0051BMsg) bMsg;
        ZZOL0051CMsg bizMsg  = (ZZOL0051CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Display TAB = Detail
        scrnMsg.xxDplyTab.setValue( TAB_HEADER );

        // table set
        ZZOL0051CommonLogic.setTableColor(scrnMsg);
        for ( int idx = 0; idx < scrnMsg.H.getValidCount(); idx++ ) {
            scrnMsg.H.no(idx).upldCsvHdrNm_H.setInputProtected( true );
        }

        // system button set
        setButtonEnabled(CMN_SEARCH, true);
        setButtonEnabled(CMN_BTN2[0], scrnMsg.H.getValidCount() > 0);

        scrnMsg.addCheckItem( scrnMsg.upldCsvFileId );
        scrnMsg.putErrorScreen();

	}

}
