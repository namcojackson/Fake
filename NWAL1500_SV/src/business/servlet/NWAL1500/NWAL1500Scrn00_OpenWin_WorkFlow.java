/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ORD_NUM;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1500.constant.NWAL1500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Fujitsu         S.Takami        Create          S21_NA#3396
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_WorkFlow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            scrnMsg.cpoOrdNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ORD_NUM });
            scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        scrnMsg.xxPopPrm_P0.setValue(NWAL1500Constant.WF_CALL_MODE_GRP_NM);
        Object[] params = new Object[3];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = null;
        params[2] = scrnMsg.cpoOrdNum;

        setArgForSubScreen(params);
    }
}
