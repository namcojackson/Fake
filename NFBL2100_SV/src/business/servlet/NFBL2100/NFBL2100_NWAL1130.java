/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/12   Hitachi         K.Kojima        Create          QC#13088
 *</pre>
 */
public class NFBL2100_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_CustomerSearch".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.Z.no(2).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dplyVndNm, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue() + " - " + scrnMsg.Z.no(3).xxComnScrColValTxt.getValue());
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_CustomerSearch".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndCd);
        }
    }
}
