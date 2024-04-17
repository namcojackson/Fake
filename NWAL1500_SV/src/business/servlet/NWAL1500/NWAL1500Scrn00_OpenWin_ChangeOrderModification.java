/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.constant.NWAL1500MsgConstant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_OpenWin_ChangeOrderModification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   CUSA            Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_ChangeOrderModification extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            scrnMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0014E);
            scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = NWAL1500CommonLogic.getParamNWAL2090(scrnMsg);
        setArgForSubScreen(params);
    }
}
