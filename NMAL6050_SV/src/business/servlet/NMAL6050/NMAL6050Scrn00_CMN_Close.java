package business.servlet.NMAL6050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6050.constant.NMAL6050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6050Scrn00_CMN_Close extends S21CommonHandler implements NMAL6050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL6050BMsg scrnMsg = (NMAL6050BMsg)bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL6050BMsg scrnMsg = (NMAL6050BMsg)bMsg;

        // NMAL6050CMsg bizMsg = new NMAL6050CMsg();
        // bizMsg.setBusinessID( "NMAL6050" );
        // bizMsg.setFunctionCode( "10" );
        // EZDMsg.copy( scrnMsg, null, bizMsg, null );

        // return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // NMAL6050BMsg scrnMsg = (NMAL6050BMsg)bMsg;
        // NMAL6050CMsg bizMsg = (NMAL6050CMsg)cMsg;

        // EZDMsg.copy( bizMsg, null, scrnMsg, null );
    }

}
