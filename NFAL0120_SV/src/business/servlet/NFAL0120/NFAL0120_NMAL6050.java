/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         Y.Takeno        Create          QC#12525
 *</pre>
 */
public class NFAL0120_NMAL6050 extends S21CommonHandler {

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

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_JrnlCatg".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.jrnlCatgCd);

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
    }
}
