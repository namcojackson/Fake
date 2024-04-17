package business.servlet.NMAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.NMAL0150BMsg;
import business.servlet.NMAL0150.common.NMAL0150CommonLogic;
import business.servlet.NMAL0150.constant.NMAL0150Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0150_INIT extends S21INITCommonHandler implements NMAL0150Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

		checkBusinessAppGranted( getContextUserInfo().getUserId(), BUSINESS_ID);
		
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
            	EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCstUpdHistHdrPk_H1, param01);
            }
        }
		NMAL0150CMsg bizMsg = new NMAL0150CMsg();
		bizMsg.setBusinessID(BUSINESS_ID);
		bizMsg.setFunctionCode(FUNC_CD_SRCH);
		EZDMsg.copy(scrnMsg, null, bizMsg, null);
 		return bizMsg;
 		
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
		NMAL0150CMsg bizMsg  = (NMAL0150CMsg) cMsg;
		EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		scrnMsg.setFocusItem(scrnMsg.mdseCstUpdTpCd_H1);
		
	}

	@Override
	protected void setNameForMessage(EZDBMsg bMsg) {

	}

}
