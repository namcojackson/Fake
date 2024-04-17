package business.servlet.NMAL0160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0160.NMAL0160CMsg;
import business.servlet.NMAL0160.NMAL0160BMsg;
import business.servlet.NMAL0160.common.NMAL0160CommonLogic;
import business.servlet.NMAL0160.constant.NMAL0160Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 11/22/2016   Fujitsu         N.Sugiura       Update          S21_NA#16026
 *</pre>
 */
public class NMAL0160Scrn00_CMN_Submit extends S21CommonHandler implements NMAL0160Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0160BMsg scrnMsg = (NMAL0160BMsg) bMsg;
        // Input Check
        NMAL0160CommonLogic.checkInput_Submit(ctx, scrnMsg);
        
    }
    
    

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0160BMsg scrnMsg = (NMAL0160BMsg) bMsg;
        NMAL0160CMsg bizMsg = new NMAL0160CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0160BMsg scrnMsg = (NMAL0160BMsg) bMsg;
        NMAL0160CMsg bizMsg = (NMAL0160CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0160CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
		
		if (scrnMsg.A.getValidCount() > 0) {
			for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
				scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCstUpdStsCd_A1);
				scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCstUpdEffFromDt_A1); // 11/22/2016 S21_NA#16026 Add
			}
		}
		scrnMsg.putErrorScreen();
        
    }
}
