/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1550;

import static business.servlet.NWAL1550.constant.NWAL1550Constant.BIZ_ID;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.SCRN_ID_00;
import static business.servlet.NWAL1550.constant.NWAL1550Constant.SHARP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1550.NWAL1550CMsg;
import business.servlet.NWAL1550.common.NWAL1550CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1550Scrn00_ExecDIChk
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1550Scrn00_ExecDIChk extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;

        NWAL1550CMsg bizMsg = new NWAL1550CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.eventNm, ctx.getEventName());

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1550BMsg scrnMsg = (NWAL1550BMsg) bMsg;
        NWAL1550CMsg bizMsg = (NWAL1550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i=0; i<scrnMsg.A.length(); i++) {
            scrnMsg.clearGUIAttribute(SCRN_ID_00, NWAL1550Bean.diChkErrTxt_A + SHARP + i);
        }

        NWAL1550CommonLogic.initControlFields(scrnMsg);
        NWAL1550CommonLogic.setIncorrectBackGroundColor(scrnMsg);
        NWAL1550CommonLogic.setControlFields(this, scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
    }
}
