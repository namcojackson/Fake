/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.BIZ_ID;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.FUNC_CD_SRCH;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_BOTH;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3070.NFCL3070CMsg;
import business.servlet.NFCL3070.common.NFCL3070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3070Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 *</pre>
 */
public class NFCL3070Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        scrnMsg.clear();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.origInvNum.setValue(param0.getValue());
        }

        NFCL3070CMsg bizMsg = new NFCL3070CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        NFCL3070CMsg bizMsg = (NFCL3070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/05/30 E.Kameishi [QC#26299,MOD]
        //ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn, RQST_TP_BOTH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn, RQST_TP_CREDIT_ONLY);
        // START 2018/05/30 E.Kameishi [QC#26299,MOD]
        NFCL3070CommonLogic.setInitialValue(scrnMsg);

        // protect field
        NFCL3070CommonLogic.initCmnBtnField(this, scrnMsg);

        // START 2018/05/30 E.Kameishi [QC#26299,ADD]
        boolean protectKey = false;
        NFCL3070CommonLogic.protectCreditOnlySection(scrnMsg, protectKey);
        // END 2018/05/30 E.Kameishi [QC#26299,ADD]

        scrnMsg.setFocusItem(scrnMsg.xxRadioBtn);
    }
}
