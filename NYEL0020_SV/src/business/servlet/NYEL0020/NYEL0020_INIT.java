/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL0020.NYEL0020CMsg;
import business.servlet.NYEL0020.common.NYEL0020CommonLogic;
import business.servlet.NYEL0020.constant.NYEL0020Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NYEL0020_INIT extends S21CommonHandler implements NYEL0020Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
        checkBusinessAppGranted( getContextUserInfo().getUserId(), "NYEL0020" );

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NYEL0020BMsg scrnMsg = (NYEL0020BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount( 0 );
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.B.clear();
        scrnMsg.B.setValidCount( 0 );         
		NYEL0020CMsg bizMsg = new NYEL0020CMsg();
		bizMsg.setBusinessID("NYEL0020");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NYEL0020BMsg scrnMsg = (NYEL0020BMsg) bMsg;
		NYEL0020CMsg bizMsg  = (NYEL0020CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        NYEL0020CommonLogic.dspScrn(scrnMsg, this);

        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        
        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);

        S21SortColumnIMGController.clearIMG(SCREEN_NAME, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCREEN_NAME, scrnMsg, scrnMsg.B.no(0).getBaseContents());
    }

}