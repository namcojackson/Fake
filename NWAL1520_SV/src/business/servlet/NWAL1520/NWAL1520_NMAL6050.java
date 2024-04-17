/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.EVENT_NM_POPUP_CLOSE;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_REASON_APPLY_HOLD;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_REASON_VIEW_HOLD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1520_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520_NMAL6050 extends S21CommonHandler {

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

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (!EVENT_NM_POPUP_CLOSE.equals(ctx.getEventName())) {
            if (LINK_NM_HOLD_REASON_APPLY_HOLD.equals(scrEventNm)) {
                scrnMsg.setFocusItem(scrnMsg.hldUntilDt_A);
                scrnMsg.hldApplyRsnCd_A.setValue(scrnMsg.xxPopPrm_PA.getValue());
            } else if (LINK_NM_HOLD_REASON_VIEW_HOLD.equals(scrEventNm)) {
                scrnMsg.setFocusItem(scrnMsg.hldUntilDt_V);
                scrnMsg.hldApplyRsnCd_V.setValue(scrnMsg.xxPopPrm_PA.getValue());
            } else {
                scrnMsg.setFocusItem(scrnMsg.relMemoTxt_RH);
                scrnMsg.hldRelRsnCd_RH.setValue(scrnMsg.xxPopPrm_PA.getValue());
            }
        }
    }

}
