/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2670;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2670.NMAL2670CMsg;
import business.servlet.NMAL2670.common.NMAL2670CommonLogic;
import business.servlet.NMAL2670.constant.NMAL2670Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL2670_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2670BMsg scrnMsg = (NMAL2670BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2670CMsg bizMsg = new NMAL2670CMsg();
        bizMsg.setBusinessID(NMAL2670Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2670Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2670BMsg scrnMsg = (NMAL2670BMsg) bMsg;
        NMAL2670CMsg bizMsg = (NMAL2670CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2670CommonLogic.initCommonButton(this);
        NMAL2670CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2670BMsg scrnMsg = (NMAL2670BMsg) bMsg;

        // Header
        scrnMsg.xxQueryFltrTxt_H1.setNameForMessage("Filter");
    }

    private void setParams(Object[] params, NMAL2670BMsg scrnMsg) {

        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, param0);
        }
    }
}
