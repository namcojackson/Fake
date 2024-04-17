/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0050;

import static business.servlet.NWWL0050.constant.NWWL0050Constant.BIZ_ID;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.MESSAGE_KIND_INFO;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.NZZM0002I;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0050.NWWL0050CMsg;
import business.servlet.NWWL0050.common.NWWL0050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0050Scrn00_Del_Line
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/09   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050Scrn00_Del_Line extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)) {
            scrnMsg.xxRadioBtn.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.xxRadioBtn.getNameForMessage() });
        }
        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;

        NWWL0050CMsg bizMsg = new NWWL0050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;
        NWWL0050CMsg bizMsg = (NWWL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWWL0050CommonLogic.controlScreenFields(this, scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_INFO.equals(bizMsg.getMessageKind())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxRadioBtn.getValueInt()).ntfyDistQlfyCd_A);
        } else if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).ntfyDistQlfyCd_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.ntfyDistListNm);
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageKind())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

    }
}
