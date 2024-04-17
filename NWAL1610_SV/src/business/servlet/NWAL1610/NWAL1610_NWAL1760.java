/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.BTN_CMN_CLOSE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_PRC_LIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610_NWAL1760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 * 2016/02/20   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 *</pre>
 */
public class NWAL1610_NWAL1760 extends S21CommonHandler {

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
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            if (OPENWIN_PRC_LIST.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, scrnMsg.P.no(10).xxPopPrm_P);
            } else {
                // Mod Start #1130 02/20/2016
                // ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListDescTxt, scrnMsg.P.no(10).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListNm, scrnMsg.P.no(10).xxPopPrm_P);
                // Mod End #1130 02/20/2016
            }
        }
        if (OPENWIN_PRC_LIST.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);
        } else {
            // Mod Start #1130 02/20/2016
            // scrnMsg.setFocusItem(scrnMsg.flPrcListDescTxt);
            scrnMsg.setFocusItem(scrnMsg.flPrcListNm);
            // Mod End #1130 02/20/2016
        }

    }
}
