/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3140;

import static business.servlet.NFCL3140.constant.NFCL3140Constant.BUSINESS_ID;
import static business.servlet.NFCL3140.constant.NFCL3140Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3140.NFCL3140CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/09   Hitachi         K.Kojima        Create          QC#13200
 *</pre>
 */
public class NFCL3140Scrn00_OnChange_AutoSequence extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = new NFCL3140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = (NFCL3140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.autoSeqCd_SV);
    }
}
