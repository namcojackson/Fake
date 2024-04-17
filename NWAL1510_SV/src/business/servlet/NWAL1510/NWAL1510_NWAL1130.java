/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.constant.NWAL1510Constant.CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         S.Ohki          Create          N/A
 * 2016/11/04   Fujitsu         M.Ohno          Update          S21_NA#15686
 *</pre>
 */
public class NWAL1510_NWAL1130 extends S21CommonHandler {

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
        // Add Start 2016/11/04 M.Ohno S21_NA#15686
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        if (!CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.istlTechCd_DI, scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_DI, scrnMsg.P.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.rqstIstlDt_DI);
        } else {
            scrnMsg.setFocusItem(scrnMsg.istlTechCd_DI);
        }
        // Add End   2016/11/04 M.Ohno S21_NA#15686
    }
}
