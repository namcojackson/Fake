/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3140;

import static business.servlet.NFCL3140.constant.NFCL3140Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3140.NFCL3140CMsg;
import business.servlet.NFCL3140.common.NFCL3140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Invoice Type Setup screen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/25   Hitachi         K.Kojima        Create          N/A
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#12142
 *</pre>
 */
public class NFCL3140Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        // START 2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        scrnMsg.dsInvTpNm_AC.clearErrorInfo();
        // END   2016/07/25 Y.Tsuchimoto [QC#12142,MOD]
        NFCL3140CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = new NFCL3140CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3140BMsg scrnMsg = (NFCL3140BMsg) bMsg;
        NFCL3140CMsg bizMsg = (NFCL3140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3140CommonLogic.setupAnchor(scrnMsg);
        NFCL3140CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() != EZDMessageInfo.MSGTYPE_ERROR) {
            scrnMsg.invTpCd_SV.setInputProtected(true);
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        scrnMsg.setFocusItem(scrnMsg.dsInvTpNm);
    }
}