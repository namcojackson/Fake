/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/02   Hitachi         J.Kim           Create          N/A
 * 2017/11/30   Hitachi         Y.Takeno        Update          QC#12525
 *</pre>
 */
public class NFAL0200_NMAL6050 extends S21CommonHandler {

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

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        // START 2017/11/30 [QC#12525, MOD]
        // int idx = getButtonSelectNumber();
        // ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).contrCoaBrCd_C, scrnMsg.contrCoaBrCd_P);
        // scrnMsg.setFocusItem(scrnMsg.C.no(idx).contrCoaBrCd_C);
        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_Branch".equals(scrEventNm)) {
            int idx = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).contrCoaBrCd_C, scrnMsg.contrCoaBrCd_P);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).contrCoaBrCd_C);

        } else if ("OpenWin_CoaBr".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaBrCd);

        } else if ("OpenWin_CoaCC".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaCcCd);

        } else if ("OpenWin_CoaAcct".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaAcctCd);

        } else if ("OpenWin_CoaProd".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaProdCd);

        } else if ("OpenWin_CoaCh".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaChCd);

        } else if ("OpenWin_CoaAffl".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaAfflCd);

        } else if ("OpenWin_CoaProj".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaProjCd);

        } else if ("OpenWin_CoaExtn".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.coaExtnCd);
        }
        // END   2017/11/30 [QC#12525, MOD]
    }
}
