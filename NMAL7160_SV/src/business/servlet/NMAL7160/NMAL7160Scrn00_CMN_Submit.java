/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7160;

import static business.servlet.NMAL7160.constant.NMAL7160Constant.BIZ_ID;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.FUNCTION_CD_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7160.NMAL7160CMsg;
import business.servlet.NMAL7160.common.NMAL7160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7160Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;

        NMAL7160CMsg bizMsg = new NMAL7160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;
        NMAL7160CMsg bizMsg = (NMAL7160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7160CommonLogic.allAddCheck(scrnMsg);
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        NMAL7160CommonLogic.setTblBackColor(scrnMsg);
    }
}
