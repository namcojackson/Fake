/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2550;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2550.NMAL2550CMsg;
import business.servlet.NMAL2550.common.NMAL2550CommonLogic;
import business.servlet.NMAL2550.constant.NMAL2550Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2550Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.coaCmpyCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaExtnCd_H1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;
        NMAL2550CMsg bizMsg = new NMAL2550CMsg();

        bizMsg.setBusinessID(NMAL2550Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2550Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;
        NMAL2550CMsg bizMsg = (NMAL2550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.coaCmpyCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaExtnCd_H1);
        scrnMsg.putErrorScreen();

        if (NMAL2550Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxMsgPrmTxt)) {
                NMAL2550CommonLogic.setFocusForAPIError(scrnMsg);
            }

            throw new EZDFieldErrorException();
        }

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            EZDBStringItem param5 = (EZDBStringItem) params[5];
            EZDBStringItem param6 = (EZDBStringItem) params[6];
            EZDBStringItem param7 = (EZDBStringItem) params[7];
            EZDBStringItem param8 = (EZDBStringItem) params[8];
            EZDBStringItem param9 = (EZDBStringItem) params[9];

            if (ZYPCommonFunc.hasValue(scrnMsg.appFuncId)) {
                param0.setValue(scrnMsg.appFuncId.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaCmpyCd_H1)) {
                param1.setValue(scrnMsg.coaCmpyCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd_H1)) {
                param2.setValue(scrnMsg.coaAfflCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaBrCd_H1)) {
                param3.setValue(scrnMsg.coaBrCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaCcCd_H1)) {
                param4.setValue(scrnMsg.coaCcCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaAcctCd_H1)) {
                param5.setValue(scrnMsg.coaAcctCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaProdCd_H1)) {
                param6.setValue(scrnMsg.coaProdCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaChCd_H1)) {
                param7.setValue(scrnMsg.coaChCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaProjCd_H1)) {
                param8.setValue(scrnMsg.coaProjCd_H1.getValue());
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.coaExtnCd_H1)) {
                param9.setValue(scrnMsg.coaExtnCd_H1.getValue());
            }
        }
    }
}
