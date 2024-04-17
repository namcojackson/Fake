/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import static business.servlet.NSAL0730.constant.NSAL0730Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0730.NSAL0730CMsg;
import business.servlet.NSAL0730.common.NSAL0730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/01/10   Hitachi         K.Kitachi       Create          QC#26928
 *</pre>
 */
public class NSAL0730Scrn00_AddLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        // event line
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum.setValue(eventLine);

        NSAL0730CMsg bizMsg = new NSAL0730CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;
        NSAL0730CMsg bizMsg = (NSAL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        NSAL0730CommonLogic.controlScreenDetailFields(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).custPoNum_A);
    }
}
