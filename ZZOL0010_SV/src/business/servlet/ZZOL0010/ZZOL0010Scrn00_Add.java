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
//import business.blap.ZZOL0010.ZZOL0010CMsg;

import business.servlet.ZZOL0010.common.ZZOL0010CommonLogic;
import business.servlet.ZZOL0010.constant.ZZOL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

public class ZZOL0010Scrn00_Add extends S21CommonHandler implements ZZOL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZOL0010BMsg scrnMsg = (ZZOL0010BMsg) bMsg;
                       
        S21UserInfo userInfo = getContextUserInfo();
        scrnMsg.ezCompanyCode_01.setValue(userInfo.getUserCompanyCode());
		scrnMsg.ezBusinessID_01.setInputProtected(false);
		scrnMsg.ezCompanyCode_01.setInputProtected(false);
        scrnMsg.ezOnlStopFlag_01.setInputProtected(false);
        scrnMsg.ezBusinessID_01.clear();
		scrnMsg.xxScrEventNm.setValue("Add");

        // set Button Properties
        ZZOL0010CommonLogic.changeCommonButton01(this);
        
        //set default value
        ZZOL0010CommonLogic.initPullDown01(scrnMsg);
        ZZOL0010CommonLogic.initValue01(scrnMsg);
        
        scrnMsg.setFocusItem(scrnMsg.ezBusinessID_01);
	}

}
