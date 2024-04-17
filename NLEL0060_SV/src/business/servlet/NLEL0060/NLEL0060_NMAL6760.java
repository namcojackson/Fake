/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_CUST_CODE;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_DS_ACCT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 *</pre>
 */
public class NLEL0060_NMAL6760 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        if (OPENWIN_CUST_CODE.equals(scrnMsg.P.no(36).xxPopPrm.getValue())) {
            // START 2016/04/18 J.Kim [QC#6581,MOD]
            // ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd_H1, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd_H1, scrnMsg.P.no(0).xxPopPrm);
            // END 2016/04/18 J.Kim [QC#6581,MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.P.no(1).xxPopPrm);

            // START 2016/04/18 J.Kim [QC#6581,MOD]
            // scrnMsg.setFocusItem(scrnMsg.sellToCustCd_H1);
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctCd_H1);
            // END 2016/04/18 J.Kim [QC#6581,MOD]
        } else if (OPENWIN_DS_ACCT_NM.equals(scrnMsg.P.no(36).xxPopPrm.getValue())) {
            int idx = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).shipToCustAcctCd_A1, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).dsAcctNm_A1, scrnMsg.P.no(1).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.A.no(idx).dsAcctNm_A1);
        }
    }
}
