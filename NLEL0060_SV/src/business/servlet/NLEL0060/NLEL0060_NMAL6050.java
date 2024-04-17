/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/05   Hitachi         J.Kim           Create          N/A
 * 2018/04/12   Hitachi         J.Kim           Update          QC#22807
 *</pre>
 */
public class NLEL0060_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        // START 2018/03/28 J.Kim [QC#22087,ADD]
        // if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
        // scrnMsg.depcCoaAcctCd_H1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
        // }
        // scrnMsg.setFocusItem(scrnMsg.depcCoaAcctCd_H1);
        // END 2018/03/28 J.Kim [QC#22087,ADD]

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        String expCoaCd = scrnMsg.P.no(9).xxPopPrm.getValue();
        String scrnEventNm = scrnMsg.P.no(11).xxPopPrm.getValue();
        if (OPENWIN_EXP_ACCT_FROM.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.depcCoaAcctCd_F, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.depcCoaAcctCd_F);
        } else if (OPENWIN_EXP_ACCT_TO.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.depcCoaAcctCd_T, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.depcCoaAcctCd_T);
        } else if (OPENWIN_EXP_BR_FROM.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.expCoaBrCd_F, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.expCoaBrCd_F);
        } else if (OPENWIN_EXP_BR_TO.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.expCoaBrCd_T, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.expCoaBrCd_T);
        } else if (OPENWIN_EXP_CC_FROM.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.expCoaCcCd_F, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.expCoaCcCd_F);
        } else if (OPENWIN_EXP_CC_TO.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.expCoaCcCd_T, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.expCoaCcCd_T);
        } else if (OPENWIN_EXP_EXTN_FROM.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.expCoaExtnCd_F, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.expCoaExtnCd_F);
        } else if (OPENWIN_EXP_EXTN_TO.equals(scrnEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.expCoaExtnCd_T, expCoaCd);
            scrnMsg.setFocusItem(scrnMsg.expCoaExtnCd_T);
        }
    }
}
