/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.BUSINESS_ID;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/05   SRAA            Y.Chen          Create          QC#6382
 *</pre>
 */
public class NMAL6730Scrn00_GetCoaChNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.coaChCd_H1)) {
            scrnMsg.coaChCd_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.coaChCd_H1.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg = new NMAL6730CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.coaChCd_H1);
    }
}
