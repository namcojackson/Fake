/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0100.NFDL0100CMsg;
import business.servlet.NFDL0100.common.NFDL0100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NFDL0100Scrn00_Click_Btn_Email extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        NFDL0100CMsg bizMsg = new NFDL0100CMsg();
        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg = (NFDL0100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
            scrnMsg.A.setCheckParam(new String[] {NFDL0100Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxDplyCtrlFlg_EM.clear();
        setArgForSubScreen(NFDL0100CommonLogic.getParamMailEntry(scrnMsg));
    }
}
