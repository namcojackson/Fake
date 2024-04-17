/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0510.common.NSAL0510CommonLogic;
import business.servlet.NSAL0510.constant.NSAL0510Constant.FUNC_CD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0510_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0510BMsg scrnMsg = (NSAL0510BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (FUNC_CD.OPENWIN_VENDOR.getFuncCd().equals(scrEventNm)
                && !NSAL0510CommonLogic.isClosedEvent(getLastGuard())) {
            scrnMsg.setFocusItem(scrnMsg.vndCd_H);
            scrnMsg.vndCd_H.setValue(scrnMsg.xxCondCd_P.getValue());
        } else if (FUNC_CD.OPENWIN_ACCOUNT.getFuncCd().equals(scrEventNm)
            && !NSAL0510CommonLogic.isClosedEvent(getLastGuard())) {
            scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H);
            scrnMsg.dsAcctNum_H.setValue(scrnMsg.xxCondCd_P.getValue());
        }
    }
}
