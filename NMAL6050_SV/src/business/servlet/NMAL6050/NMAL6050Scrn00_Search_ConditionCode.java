package business.servlet.NMAL6050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6050.NMAL6050CMsg;
import business.servlet.NMAL6050.common.NMAL6050CommonLogic;
import business.servlet.NMAL6050.constant.NMAL6050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6050Scrn00_Search_ConditionCode extends S21CommonHandler implements NMAL6050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6050BMsg scrnMsg = (NMAL6050BMsg) bMsg;

        NMAL6050CMsg bizMsg = new NMAL6050CMsg();
        bizMsg.setBusinessID("NMAL6050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6050BMsg scrnMsg = (NMAL6050BMsg) bMsg;
        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6050CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }

}
