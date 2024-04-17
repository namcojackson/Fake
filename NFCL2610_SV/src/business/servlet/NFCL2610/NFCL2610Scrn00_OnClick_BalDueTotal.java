/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/25   Hitachi         K.Kojima        Update          QC#7532
 *</pre>
 */
public class NFCL2610Scrn00_OnClick_BalDueTotal extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;

        BigDecimal totalRfAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxTotRfAmt)) {
            totalRfAmt = scrnMsg.xxTotRfAmt.getValue();
        }

        scrnMsg.xxTotRfAmt.clear();
        int index = getButtonSelectNumber();
        if (index >= 0 && ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(index).xxChkBox_3.getValue())) {
            totalRfAmt = totalRfAmt.add(scrnMsg.A.no(index).dealRmngBalGrsAmt_A.getValue());
        } else {
            totalRfAmt = totalRfAmt.subtract(scrnMsg.A.no(index).dealRmngBalGrsAmt_A.getValue());
        }
        scrnMsg.xxTotRfAmt.setValue(totalRfAmt);
        if (index >= 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(index).xxChkBox_3);
        }
    }
}
