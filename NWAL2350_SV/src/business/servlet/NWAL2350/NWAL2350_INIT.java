/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2350;

import static business.servlet.NWAL2350.constant.NWAL2350Constant.BIZ_APP_ID;
import static business.servlet.NWAL2350.constant.NWAL2350Constant.FUNCTION_CD_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2350.NWAL2350CMsg;
import business.servlet.NWAL2350.common.NWAL2350CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * SOM Profitability INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2350_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2350BMsg scrnMsg = (NWAL2350BMsg) bMsg;
        NWAL2350CMsg bizMsg = new NWAL2350CMsg();

        scrnMsg.clear();

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            EZDBStringItem param0 = (EZDBStringItem) params[0];

            ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptOrdPk, new BigDecimal(param0.getValue()));
        }

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2350BMsg scrnMsg = (NWAL2350BMsg) bMsg;
        NWAL2350CMsg bizMsg = (NWAL2350CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2350CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NWAL2350CommonLogic.setNameForMessage((NWAL2350BMsg) arg0);
    }

}
