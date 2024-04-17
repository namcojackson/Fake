/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190;

import static business.servlet.NSAL1190.constant.NSAL1190Constant.OPENWIN_AGGCOUNTER;
import static business.servlet.NSAL1190.constant.NSAL1190Constant.OPENWIN_FLTCOUNTER;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSAL1190_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;
        String mtrLbCd = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();
        String mtrLbNm = scrnMsg.P.no(1).xxComnScrColValTxt.getValue();

        if (!"CMN_Close".equals(getLastGuard())) {
            int idx = getButtonSelectNumber();
            if (OPENWIN_FLTCOUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.A.no(idx).fleetMtrLbCd_A, mtrLbCd);
                setValue(scrnMsg.A.no(idx).mtrLbNm_AF, mtrLbNm);
                scrnMsg.setFocusItem(scrnMsg.A.no(idx).mtrLbNm_AF);
            } else if (OPENWIN_AGGCOUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.A.no(idx).aggrMtrLbCd_A, mtrLbCd);
                setValue(scrnMsg.A.no(idx).mtrLbNm_AG, mtrLbNm);
                scrnMsg.setFocusItem(scrnMsg.A.no(idx).mtrLbNm_AG);
            }
        }
    }
}
