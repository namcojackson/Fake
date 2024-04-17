/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0990Scrn00_Search_BillTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.billToLocNum);
        if (!ZYPCommonFunc.hasValue(scrnMsg.billToLocNum)) {
            scrnMsg.billToLocNum.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Bill To"});
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.billToLocNum);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.billToLocNum);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

    }
}
