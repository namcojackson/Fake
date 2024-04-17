package business.servlet.NMAL0140;

import parts.common.EZDBStringItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0140.NMAL0140CMsg;
import business.servlet.NMAL0140.NMAL0140BMsg;
import business.servlet.NMAL0140.common.NMAL0140CommonLogic;
import business.servlet.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140_INIT extends S21INITCommonHandler implements NMAL0140Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		checkBusinessAppGranted( getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
            	EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H1, param01);
            }
        }
		ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCstUpdTpCd_H1, MDSE_CST_UPD_TP.STANDARD_COST);

		NMAL0140CommonLogic.setPage(scrnMsg, 1);
		NMAL0140CMsg bizMsg = new NMAL0140CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0140BMsg scrnMsg = (NMAL0140BMsg) bMsg;
		NMAL0140CMsg bizMsg  = (NMAL0140CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		scrnMsg.coaProdNm_H1.setInputProtected(true);
		scrnMsg.rtlWhNm_H1.setInputProtected(true);
		scrnMsg.rtlSwhNm_H1.setInputProtected(true);
		NMAL0140CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		
	}

	@Override
	protected void setNameForMessage(EZDBMsg bMsg) {

	}

}
