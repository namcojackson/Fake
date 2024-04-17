/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7290_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/24   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL7290_NMAL6050 extends S21CommonHandler {

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

        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("NMAL7290Scrn00_OpenWin_OrderCategory".equals(scrEventNm)) {
            if (!"CMN_Close".equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.P.no(9).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.dsOrdCatgCd);
            }
        } else if ("NMAL7290Scrn00_OpenWin_OrderReason".equals(scrEventNm)) {
            if (!"CMN_Close".equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpCd, scrnMsg.P.no(9).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
            }
        }
    }
}
