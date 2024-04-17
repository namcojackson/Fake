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

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZXL0050Scrn00_Search extends S21CommonHandler implements ZZXL0050Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.dtProcCd );
        scrnMsg.addCheckItem( scrnMsg.dtMgtPgmId );
        scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxSortTblNm.clear();
        scrnMsg.xxSortItemNm.clear();
        scrnMsg.xxSortOrdByTxt.clear();

		ZZXL0050CMsg bizMsg = new ZZXL0050CMsg();
		bizMsg.setBusinessID("ZZXL0050");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZXL0050BMsg scrnMsg = (ZZXL0050BMsg) bMsg;
		ZZXL0050CMsg bizMsg  = (ZZXL0050CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setButtonEnabled(CMN00_BTN7[0], scrnMsg.A.getValidCount() > 0);

        ZZXL0050CommonLogic.setTableColor( scrnMsg );

        // control image file of sort column (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(screenId_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());

        scrnMsg.addCheckItem( scrnMsg.glblCmpyCd );
        scrnMsg.addCheckItem( scrnMsg.dtProcCd );
        scrnMsg.addCheckItem( scrnMsg.dtMgtPgmId );
        scrnMsg.putErrorScreen();
	}

}
