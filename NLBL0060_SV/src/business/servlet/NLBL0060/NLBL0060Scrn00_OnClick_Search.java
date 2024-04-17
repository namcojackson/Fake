/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0060;


import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0060.NLBL0060CMsg;
import business.servlet.NLBL0060.common.NLBL0060CommonLogic;
import business.servlet.NLBL0060.constant.NLBL0060Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[OnClick_Search] in BusinessID NLBL0060 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/28   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0060Scrn00_OnClick_Search extends S21CommonHandler implements NLBL0060Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
		
		NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;

		scrnMsg.addCheckItem(scrnMsg.whCd_H2);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        scrnMsg.putErrorScreen();
        
        if ((ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1) &&
        		!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1)) ||
        		(!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1) &&
                		ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1))) {
        	
        	scrnMsg.effFromDt_H1.setErrorInfo(1, NLBM0008E);
        	scrnMsg.effThruDt_H1.setErrorInfo(1, NLBM0008E);
			scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
			scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
			
        } else {
        	
        	if (ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.effThruDt_H1.getValue()) == 1) {
        		
        		scrnMsg.effFromDt_H1.setErrorInfo(1, NLBM0008E);
            	scrnMsg.effThruDt_H1.setErrorInfo(1, NLBM0008E);
    			scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
    			scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        	}
        }
        
        scrnMsg.putErrorScreen();
        
        if (NLBL0060CommonLogic.checkSearchButtonConfirmMsgNecessary(scrnMsg)) {
			
			scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_ON_Y);
			scrnMsg.setMessageInfo(NLBM0070W);
            throw new EZDFieldErrorException();
             
		} else {
			
			scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
				
		}
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

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        scrnMsg.addCheckItem(scrnMsg.whCd_H2);
        scrnMsg.putErrorScreen();
        // 2013/05/21 R-OM025 Inventory Transaction Add End
		NLBL0060CommonLogic.cntrlDispScrnItem(this, scrnMsg);
		NLBL0060CommonLogic.setFocusForTbl(scrnMsg);
	}
}
