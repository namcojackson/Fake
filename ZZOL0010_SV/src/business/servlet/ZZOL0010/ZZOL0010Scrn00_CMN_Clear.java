/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZOL0010;


import parts.common.*;
import parts.servletcommon.*;
import parts.servletcommon.gui.*;
//import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.blap.ZZOL0010.ZZOL0010CMsg;
import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

public class ZZOL0010Scrn00_CMN_Clear extends S21CommonHandler implements ZZOL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		//ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
		ZZOL0010CMsg bizMsg = new ZZOL0010CMsg();
		
		bizMsg.setBusinessID("ZZOL0010");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
		
        scrnMsg.clear();
		scrnMsg.A.setValidCount(0);
        
		ZZOL0010CommonLogic.initPullDown00(scrnMsg);     
		ZZOL0010CommonLogic.initCommonButton(this);
		
        S21TableColorController tblColor = new S21TableColorController(ZZOL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        
        S21SortColumnIMGController.clearIMG("ZZOL0010Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
		scrnMsg.setFocusItem(scrnMsg.ezBusinessID_00);
        
        S21UserInfo userInfo = getContextUserInfo();
        scrnMsg.ezCompanyCode.setValue(userInfo.getUserCompanyCode());
        //scrnMsg.ezCompanyCode.setInputProtected(true);

	}

}
