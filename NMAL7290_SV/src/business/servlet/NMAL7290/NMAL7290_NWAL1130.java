/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/08   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 *</pre>
 */
public class NMAL7290_NWAL1130 extends S21CommonHandler {

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

        NMAL7290BMsg scrnMsg = (NMAL7290BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            // QC#9694 2016/07/08 Mod Start
            //ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdGrpNum, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt_R.getValue()));
            //ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdGrpNm, scrnMsg.R.no(1).xxComnScrColValTxt_R);
            //scrnMsg.setFocusItem(scrnMsg.prcRulePrcdGrpNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdPk, new BigDecimal(scrnMsg.R.no(0).xxComnScrColValTxt_R.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcRulePrcdGrpNm, scrnMsg.R.no(1).xxComnScrColValTxt_R);
            scrnMsg.setFocusItem(scrnMsg.prcRulePrcdPk);
            // QC#9694 2016/07/08 Mod End
        }
    }
}
