/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/09   Hitachi         J.Kim           Create          QC#21426
 *</pre>
 */
public class NFDL0020_ZYPL0310 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("Click_NoteAttachment".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.cltNoteDtlPk_FH);
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H);
        }
    }
}
