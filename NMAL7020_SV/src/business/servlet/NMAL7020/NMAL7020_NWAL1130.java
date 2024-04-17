/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020;

import static business.servlet.NMAL7020.constant.NMAL7020Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7020_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020_NWAL1130 extends S21CommonHandler {

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

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_O);
        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("NMAL7020Scrn00_OpenWin_PriceList".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_O, scrnMsg.P.no(1).xxComnScrColValTxt);
        }
    }
}
