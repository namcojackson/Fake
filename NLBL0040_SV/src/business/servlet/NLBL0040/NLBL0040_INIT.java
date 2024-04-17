package business.servlet.NLBL0040;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.NLBL0040.NLBL0040CMsg;
import business.servlet.NLBL0040.common.NLBL0040CommonLogic;
import business.servlet.NLBL0040.NLBL0040BMsg;
import business.servlet.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * When Event[INIT] in BusinessID NLBL0040 is generated, this class processes it. 
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/07   Fujitsu         D.Fukaya        Create          N/A
 * 2013/05/13   Fujitsu         H.Mizutani      Update          R-OM025 Inventory Transaction
 * </pre>
 */
public class NLBL0040_INIT extends S21INITCommonHandler implements NLBL0040Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		scrnMsg.xxConfMsgAlrdyDplyFlg_G1.setValue(ZYPConstant.FLG_OFF_N);
		scrnMsg.xxConfMsgAlrdyDplyFlg_G2.setValue(ZYPConstant.FLG_OFF_N);
		
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 		
 		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
        NLBL0040CMsg bizMsg = new NLBL0040CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) bMsg;
		NLBL0040CMsg bizMsg  = (NLBL0040CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0040CommonLogic.cntrlDispScrnItem(this, scrnMsg);
        
        setButtonConfirmMsg(BTN_CMN_BTN_9[1], NLBM0070W, new String[] {BTN_CMN_BTN_9[2] }, 0);
        setButtonConfirmMsg(BTN_CMN_BTN_10[1], NZZM0004W, new String[] {BTN_CMN_BTN_10[2] }, 0);
        
        scrnMsg.setFocusItem(scrnMsg.whCd_H2);
	}

	@Override
	protected void setNameForMessage(EZDBMsg arg0) {

		NLBL0040BMsg scrnMsg = (NLBL0040BMsg) arg0;
		
        scrnMsg.whCd_H2.setNameForMessage(NAME_FOR_MESSAGE_LOC_CD);
		scrnMsg.effFromDt_H2.setNameForMessage(NAME_FOR_MESSAGE_EFF_PER);
		scrnMsg.stCd_H2.setNameForMessage(NAME_FOR_MESSAGE_ST);
		scrnMsg.effFromDt_L1.setNameForMessage(NAME_FOR_MESSAGE_EFF_FROM_DT);
		scrnMsg.effThruDt_L1.setNameForMessage(NAME_FOR_MESSAGE_EFF_THRU_DT);
		
		for (int i = 0; i < scrnMsg.A.length(); i++) {
        	
        	scrnMsg.A.no(i).delyLeadAot_A1.setNameForMessage(NAME_FOR_MESSAGE_LEAD_TM);
        }
		
		for (int i = 0; i < scrnMsg.B.length(); i++) {
        	
        	scrnMsg.B.no(i).fromZipCd_B1.setNameForMessage(NAME_FOR_MESSAGE_FROM_POST_CD);
        	scrnMsg.B.no(i).toZipCd_B1.setNameForMessage(NAME_FOR_MESSAGE_TO_POST_CD);
        	scrnMsg.B.no(i).trnspLtAot_B1.setNameForMessage(NAME_FOR_MESSAGE_LEAD_TM);
        }
	}
}
