package business.servlet.ZYPL0220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0220.common.ZYPL0220CommonLogic;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220Scrn00_CMN_Clear extends S21CommonHandler implements ZYPL0220Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        
        // ------------------------------------------
        // (1)set screen mode
        // ------------------------------------------
        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        ZYPL0220CommonLogic.setInitMode(this, scrnMsg);
        
        
    }

}
