/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.BTN_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3070_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 *</pre>
 */
public class NFCL3070_NWAL1130 extends S21CommonHandler {

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

        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

        //START 2018/05/30 E.Kameishi [QC#26229, MOD]
        //if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.arCrRebilRsnCd, scrnMsg.P.no(0).xxComnScrColValTxt);
        //}
        if (!BTN_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.arCrRebilRsnCd, scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.arCrRebilRsnNm, scrnMsg.P.no(1).xxComnScrColValTxt);
        }

        //scrnMsg.setFocusItem(scrnMsg.arCrRebilRsnCd);
        scrnMsg.setFocusItem(scrnMsg.arCrRebilRsnNm);
        //END 2018/05/30 E.Kameishi [QC#26229, MOD]
    }
}
