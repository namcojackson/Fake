/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010_NLCL0230 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        String eventNm = scrnMsg.xxScrEventNm.getValue();

        if (!NLCL1010Constant.POPUP_CLOSE.equals(getLastGuard())) {
            if ("OpenWin_SrchLocFromCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.fromLocCd_C0, scrnMsg.P.no(1).xxPopPrm);
            } else if ("OpenWin_SrchLocToCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.toLocCd_C0, scrnMsg.P.no(1).xxPopPrm);
            } else if ("OpenWin_AddLocFromCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.fromLocCd_H0, scrnMsg.P.no(1).xxPopPrm);
            } else if ("OpenWin_AddLocToCd".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.toLocCd_H0, scrnMsg.P.no(1).xxPopPrm);
            }
        }
    }
}
