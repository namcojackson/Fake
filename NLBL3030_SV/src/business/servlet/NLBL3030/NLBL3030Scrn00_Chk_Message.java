/*
 * <Pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </Pre>
 */
package business.servlet.NLBL3030;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3030.common.NLBL3030CommonLogic;
import business.servlet.NLBL3030.constant.NLBL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Message Entry PopUo
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/16   Fujitsu         C.Naito         Create          (From NATL6050)
 * </pre>
 */
public class NLBL3030Scrn00_Chk_Message extends S21CommonHandler implements NLBL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3030BMsg scrnMsg = (NLBL3030BMsg) bMsg;
        NLBL3030CommonLogic.checkOK(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3030BMsg scrnMsg = (NLBL3030BMsg) bMsg;

        String trimMsg = ZYPCommonFunc.trimTail(scrnMsg.xxDsMultMsgDplyTxt.getValue());
        scrnMsg.xxDsInputTxtNum.setValue(new BigDecimal(trimMsg.length()));
        int lineNum = trimMsg.split("\n").length;
        if (ZYPCommonFunc.hasValue(scrnMsg.msgMaxLineNum) && lineNum > scrnMsg.msgMaxLineNum.getValueInt()) {
            scrnMsg.xxDsMultMsgDplyTxt.setErrorInfo(1, NATM0006E, new String[] {"" + scrnMsg.msgMaxLineNum.getValueInt() });
        } else if (trimMsg.length() > scrnMsg.msgMaxTxtNum.getValueInt()) {
            scrnMsg.xxDsMultMsgDplyTxt.setErrorInfo(1, ZZM9001E, new String[] {scrnMsg.msgCtrlTpDescTxt.getValue() });
        }

        NLBL3030CommonLogic.checkOK(scrnMsg);

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ((EZDBStringItem) params[2]).setValue(trimMsg);
        }
    }

}
