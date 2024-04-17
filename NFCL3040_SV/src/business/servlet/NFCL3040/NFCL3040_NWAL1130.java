/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/14   Hitachi         J.Kim           Create          QC#25695
 *</pre>
 */
public class NFCL3040_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3040BMsg scrnMsg = (NFCL3040BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("Click_LinkBankAccount_1".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNm_H, scrnMsg.P.no(0).xxComnScrColValTxt);
            }
        } else if ("Click_LinkBankAccount_2".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankBrNm_H, scrnMsg.P.no(1).xxComnScrColValTxt);
            }
        } else if ("Click_LinkBankAccount_3".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(3).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctNum_H, scrnMsg.P.no(3).xxComnScrColValTxt);
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NFCL3040BMsg scrnMsg = (NFCL3040BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("Click_LinkBankAccount_1".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsBankAcctNm_H);
        } else if ("Click_LinkBankAccount_2".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsBankBrNm_H);
        } else if ("Click_LinkBankAccount_3".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsBankAcctNum_H);
        }
    }
}
