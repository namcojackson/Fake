/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0440.NSAL0440CMsg;
//import business.servlet.NSAL0440.constant.NSAL0440Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Hitachi         T.Tomita        Create          QC#2815
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL0440_NWAL1130 extends S21CommonHandler {

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
        // mod start 2018/06/25 QC#17369
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_Serial".equals(scrEventNm)) {
            String serCd = scrnMsg.Z.no(1).xxComnScrColValTxt.getValue();
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, serCd);
        } else if ("OpenWin_ContrCondVal".equals(scrEventNm)) {
            int selectNumber = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermCondDataValCd_PS, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            if (ZYPCommonFunc.hasValue(scrnMsg.Z.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_A, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_A, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_A);
        } else if ("OpenWin_MachCondVal".equals(scrEventNm)) {
            int selectNumber = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermCondDataValCd_MS, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            if (ZYPCommonFunc.hasValue(scrnMsg.Z.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_M, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_M, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_M);
        }
        // mod end 2018/06/25 QC#17369
    }
}
