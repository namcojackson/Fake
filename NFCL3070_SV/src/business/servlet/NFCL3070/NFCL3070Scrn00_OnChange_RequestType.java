/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3070.common.NFCL3070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3070Scrn00_OnChange_RequestType
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 *</pre>
 */
public class NFCL3070Scrn00_OnChange_RequestType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

        boolean protectKey = true;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)
                && RQST_TP_CREDIT_ONLY.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
            protectKey = false;
        }

        if (protectKey) {
            NFCL3070CommonLogic.setInitialValue(scrnMsg);
            scrnMsg.actvFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        NFCL3070CommonLogic.protectCreditOnlySection(scrnMsg, protectKey);
    }
}
