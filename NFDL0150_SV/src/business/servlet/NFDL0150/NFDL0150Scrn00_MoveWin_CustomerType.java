/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0150;

import static business.servlet.NFDL0150.constant.NFDL0150Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0150.NFDL0150CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0150Scrn00_MoveWin_CustomerType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0150BMsg scrnMsg = (NFDL0150BMsg) bMsg;

        NFDL0150CMsg bizMsg = new NFDL0150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0150BMsg scrnMsg = (NFDL0150BMsg) bMsg;
        NFDL0150CMsg bizMsg = (NFDL0150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        int rowNum = scrnMsg.xxRadioBtn_A.getValueInt();
        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(rowNum).cltStrgyCd_A;
        setArgForSubScreen(params);

    }
}
