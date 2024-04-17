/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 *</pre>
 */
public class NFCL2610Scrn00_UncheckAll extends S21CommonHandler {

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

        // START 2021/09/09 G.Delgado [QC#58793, ADD]
        // Get already unselected rows
        List<Integer> unSelectedRows = ZYPTableUtil.getUnSelectedRows(scrnMsg.A, "xxChkBox_3", ZYPConstant.CHKBOX_ON_Y);
        // END 2021/09/09 G.Delgado [QC#58793, ADD]

        ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_3");
        // START 2021/09/09 G.Delgado [QC#58793, MOD]
        // scrnMsg.xxTotRfAmt.setValue(BigDecimal.ZERO);
        BigDecimal totalRfAmt = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxTotRfAmt)) {
            totalRfAmt = scrnMsg.xxTotRfAmt.getValue();
        }

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            // Subtract to Total Refund Amount if newly unchecked
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).xxChkBox_3.getValue()) && !unSelectedRows.contains(index)) {
                totalRfAmt = totalRfAmt.subtract(scrnMsg.A.no(index).dealRmngBalGrsAmt_A.getValue());
            }
        }
        scrnMsg.xxTotRfAmt.setValue(totalRfAmt);
        // END 2021/09/09 G.Delgado [QC#58793, MOD]
    }
}
