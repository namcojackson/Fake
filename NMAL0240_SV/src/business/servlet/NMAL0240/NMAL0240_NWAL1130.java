/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.OPEN_CMPSN_A;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.OPEN_CMPSN_B;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0240_NWAL1130 extends S21CommonHandler {

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

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        if (OPEN_CMPSN_A.equals(scrnMsg.xxScrNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
        } else if (OPEN_CMPSN_B.equals(scrnMsg.xxScrNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(getButtonSelectNumber()).mdseCd_B1, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
        }
    }
}
