/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2680;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2680.NMAL2680CMsg;
import business.servlet.NMAL2680.common.NMAL2680CommonLogic;
import business.servlet.NMAL2680.constant.NMAL2680Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CUSA            Fujitsu         Create          N/A
 * 2016/06/01   Fujitsu         C.Yokoi         Update          CSA-QC#9925
 *</pre>
 */
public class NMAL2680_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2680BMsg scrnMsg = (NMAL2680BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;

        if (arg instanceof Object[]) {
            setParams(params, scrnMsg);
        }

        NMAL2680CMsg bizMsg = new NMAL2680CMsg();
        bizMsg.setBusinessID(NMAL2680Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2680Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2680BMsg scrnMsg = (NMAL2680BMsg) bMsg;
        NMAL2680CMsg bizMsg = (NMAL2680CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2680CommonLogic.initCommonButton(this);
        NMAL2680CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2680BMsg scrnMsg = (NMAL2680BMsg) bMsg;

        // Header
        scrnMsg.xxQueryFltrTxt_H1.setNameForMessage("Filter");
    }

    private void setParams(Object[] params, NMAL2680BMsg scrnMsg) {
        // 2016/06/01 CSA-QC#9925 Mod Start
        if (params != null && (params.length == 1 || params.length == 2)) {
            if (params[0] != null) {
                // Territory Rule PK
                EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
                if (ZYPCommonFunc.hasValue(param0)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.trtyRulePk_H1, param0);
                }
            }

            // Territory ID
            if (params[1] != null) {
                EZDBStringItem param1 = (EZDBStringItem) params[1];
                if (ZYPCommonFunc.hasValue(param1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_P1, param1);
                }
            }
        }
        // 2016/06/01 CSA-QC#9925 Mod End
    }
}
