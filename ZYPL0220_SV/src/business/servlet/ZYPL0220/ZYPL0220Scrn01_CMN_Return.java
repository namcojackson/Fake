package business.servlet.ZYPL0220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220Scrn01_CMN_Return extends S21CommonHandler implements ZYPL0220Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;

        // ZYPL0220CMsg bizMsg = new ZYPL0220CMsg();
        // bizMsg.setBusinessID("ZYPL0220");
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        // ZYPL0220CMsg bizMsg = (ZYPL0220CMsg) cMsg;

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }

}
