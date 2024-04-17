/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6740.NMAL6740CMsg;
import business.servlet.NMAL6740.constant.NMAL6740Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/05   SRAA            Y.Chen          Create          QC#6382
 *</pre>
 */
public class NMAL6740Scrn00_GetCoaChNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.coaChCd)) {
            scrnMsg.coaChCd.setErrorInfo(1, NMAL6740Constant.ZZM9000E, new String[] {scrnMsg.coaChCd.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.coaChCd);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
        NMAL6740CMsg bizMsg = new NMAL6740CMsg();

        bizMsg.setBusinessID(NMAL6740Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6740BMsg scrnMsg = (NMAL6740BMsg) bMsg;
        NMAL6740CMsg bizMsg = (NMAL6740CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.coaChCd);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.coaChCd);
    }
}
