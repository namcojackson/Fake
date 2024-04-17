/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.EVENT_NM_POPUP_CLOSE;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.LINK_NM_HOLD_NAME_VIEW_HOLD;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1520_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520_NWAL1130 extends S21CommonHandler {

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
            if (LINK_NM_HOLD_NAME_VIEW_HOLD.equals(scrEventNm)) {
                scrnMsg.hldRsnDescTxt_V.setValue(scrnMsg.I.no(1).xxComnScrColValTxt.getValue());
                scrnMsg.hldRsnCd_V.setValue(scrnMsg.I.no(0).xxComnScrColValTxt.getValue());
            } else {
                scrnMsg.hldRsnDescTxt_A.setValue(scrnMsg.I.no(1).xxComnScrColValTxt.getValue());
                scrnMsg.hldRsnCd_A.setValue(scrnMsg.I.no(0).xxComnScrColValTxt.getValue());
            }
        }
    }
}
