/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0060.NLBL0060CMsg;
import business.servlet.NLBL0060.common.NLBL0060CommonLogic;
import business.servlet.NLBL0060.constant.NLBL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[CMN_Submit] in BusinessID NLBL0060 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/16   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0060Scrn00_CMN_Submit extends S21CommonHandler implements NLBL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		NLBL0060CommonLogic.checkSingleUnitAttributionForSubmit(scrnMsg);
		scrnMsg.putErrorScreen();
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
 		
        NLBL0060CMsg bizMsg = new NLBL0060CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
		NLBL0060CMsg bizMsg  = (NLBL0060CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

		NLBL0060CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        
		// input value error check
		for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
			
			scrnMsg.addCheckItem(scrnMsg.A.no(i).whCd_A1);
			scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
			scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
			scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgCloTmTs_A1);
			scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDplyLeadTmDaysAot_A1);
		}

		scrnMsg.putErrorScreen();
		
		scrnMsg.setFocusItem(scrnMsg.whCd_H2);
	}
}
