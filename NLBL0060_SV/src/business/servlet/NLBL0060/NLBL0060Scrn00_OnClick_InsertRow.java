/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0060.NLBL0060CMsg;
import business.servlet.NLBL0060.common.NLBL0060CommonLogic;
import business.servlet.NLBL0060.constant.NLBL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[OnClick_InsertRow] in BusinessID NLBL0060 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/16   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0060Scrn00_OnClick_InsertRow extends S21CommonHandler implements NLBL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		

		if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A1)) {

        	EZDBItem radioButton = scrnMsg.xxRadioBtn_A1;
            radioButton.setErrorInfo(1, NLBM0001E);
            scrnMsg.addCheckItem(radioButton);
        }
        
		NLBL0060CommonLogic.checkSingleUnitAttribution(scrnMsg);
		scrnMsg.putErrorScreen();
	}

	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;

        NLBL0060CMsg bizMsg = new NLBL0060CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
		NLBL0060CMsg bizMsg  = (NLBL0060CMsg) cMsg;
		
        int index = bizMsg.xxRadioBtn_A1.getValueInt();
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		
// 2013/05/21 R-OM025 Inventory Transaction Add Start
//        scrnMsg.addCheckItem(scrnMsg.A.no(index).whCd_A1);
//        scrnMsg.putErrorScreen();
// 2013/05/21 R-OM025 Inventory Transaction Add End
		NLBL0060CommonLogic.cntrlDispScrnItem(this, scrnMsg);
		
		scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A1);
	}
}
