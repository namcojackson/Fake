package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.constant.NMAL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0110Scrn00_CMN_Reset extends S21CommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
//        if (!MODE_NEW.equals(scrnMsg.xxModeCd_H1.getValue())) {
	        NMAL0110CMsg bizMsg = new NMAL0110CMsg();
	        bizMsg.setBusinessID(BUSINESS_ID);
	        bizMsg.setFunctionCode(FUNC_CD_SRCH);
	        EZDMsg.copy(scrnMsg, null, bizMsg, null);
	        return bizMsg;
//    	} else {
//    		return null;
//    	}
    	
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
//        if (MODE_NEW.equals(scrnMsg.xxModeCd_H1.getValue())) {
//            // screen clear
//            NMAL0110CommonLogic.clearValue(getUserProfileService(), scrnMsg, true, true);
//    	} else {
	        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
	        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//    	}
		NMAL0110CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        
    }

}
