package business.servlet.NLBL0040;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0040.NLBL0040CMsg;
import business.servlet.NLBL0040.common.NLBL0040CommonLogic;
import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * When Event[PageNext] in BusinessID NLBL0040 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/07   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0040Scrn00_PageNext extends S21CommonHandler implements NLBL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		scrnMsg.xxConfMsgAlrdyDplyFlg_G2.setValue(ZYPConstant.FLG_OFF_N);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
 		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg; 
 		
 		NLBL0040CommonLogic.getPagenationTableNm(scrnMsg, ctx);
 		
        NLBL0040CMsg bizMsg = new NLBL0040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
        NLBL0040CMsg bizMsg = (NLBL0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0040CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        
		if (LEFT_TABLE_NAME.equals(scrnMsg.xxTblNm.getValue())) {

			scrnMsg.xxRadioBtn_A1.clear();
	        NLBL0040CommonLogic.cntrlDispAreaLeadTimeTable(scrnMsg);
	        
	        scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A1);
	        
		} else {
			
			NLBL0040CommonLogic.setFocusForTrnspLTTbl(scrnMsg);
		}
	}
}
