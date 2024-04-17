package business.servlet.NMAL7120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7120.NMAL7120CMsg;
import business.servlet.NMAL7120.NMAL7120BMsg;
import business.servlet.NMAL7120.common.NMAL7120CommonLogic;
import business.servlet.NMAL7120.constant.NMAL7120Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL7120Scrn00_Click_SelAll_UnSelAll_Checkbox extends S21CommonHandler implements NMAL7120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
        NMAL7120CMsg bizMsg = new NMAL7120CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NMAL7120BMsg scrnMsg = (NMAL7120BMsg) bMsg;
    	NMAL7120CMsg bizMsg = (NMAL7120CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7120CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        
    }

}
