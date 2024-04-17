/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.CMN_CLOSE;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.OPENWIN_TRTYNMFORMOVETO;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.OPENWIN_TRTYNMFORSEARCHITEM;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2710_NMAL2630
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL2710_NMAL2630 extends S21CommonHandler {

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

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        if (OPENWIN_TRTYNMFORSEARCHITEM.equals(scrnMsg.xxScrEventNm.getValue())) {
            if (!CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H, scrnMsg.P.no(1).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.orgNm_H);
        } else if (OPENWIN_TRTYNMFORMOVETO.equals(scrnMsg.xxScrEventNm.getValue())) {
            if (!CMN_CLOSE.equals(getLastGuard())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_DC, scrnMsg.P.no(1).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_DC, scrnMsg.P.no(0).xxPopPrm);
            }
            scrnMsg.setFocusItem(scrnMsg.orgNm_DC);
        }
    }
}
