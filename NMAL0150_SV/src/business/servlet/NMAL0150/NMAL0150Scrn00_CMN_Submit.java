package business.servlet.NMAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.NMAL0150BMsg;
import business.servlet.NMAL0150.common.NMAL0150CommonLogic;
import business.servlet.NMAL0150.constant.NMAL0150Constant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0150Scrn00_CMN_Submit extends S21CommonHandler implements NMAL0150Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        // mod start 2022/05/30 QC#55970
        //NMAL0150CommonLogic.checkInput_Submit(ctx, scrnMsg);
        NMAL0150CommonLogic.addCheckItemHeader(ctx, scrnMsg);
        NMAL0150CommonLogic.addCheckItemDetail(ctx, scrnMsg);
        scrnMsg.putErrorScreen();
        // mod end 2022/05/30 QC#55970
    }
    
    

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        NMAL0150CMsg bizMsg = new NMAL0150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        NMAL0150CMsg bizMsg = (NMAL0150CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        // mod start 2022/05/30 QC#55970
        //NMAL0150CommonLogic.checkInput_Submit(ctx, scrnMsg);
        NMAL0150CommonLogic.addCheckItemHeader(ctx, scrnMsg);
        NMAL0150CommonLogic.addCheckItemDetail(ctx, scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // mod end 2022/05/30 QC#55970
    }
}
