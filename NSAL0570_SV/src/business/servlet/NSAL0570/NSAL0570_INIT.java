/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0570;

import static business.servlet.NSAL0570.common.NSAL0570CommonLogic.*;
import static business.servlet.NSAL0570.constant.NSAL0570Constant.*;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0570.NSAL0570CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0570_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 2) {
            if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_H1, param00);
            }

            if (params.length > 1 && params[1] != null && params[1] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrBllgMtrPk_H1, param01);
            }

            if (params.length > 2 && params[2] != null && params[2] instanceof EZDBStringItem) {
                EZDBStringItem param02 = (EZDBStringItem) params[2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_H1, param02);
            }
        }

        NSAL0570CMsg bizMsg = new NSAL0570CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        NSAL0570CMsg bizMsg = (NSAL0570CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        copyAMsgToBMsg(scrnMsg);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0570BMsg scrnMsg = (NSAL0570BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).contrPrcEffFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrPrcEffThruDt_A1.setNameForMessage("End Date");
            scrnMsg.A.no(i).bllgCycleCd_A3.setNameForMessage("Frequency");
            scrnMsg.A.no(i).xsMtrCopyQty_A1.setNameForMessage("Allowance");
            scrnMsg.A.no(i).xsMtrAmtRate_A1.setNameForMessage("Price");
        }
    }
}
