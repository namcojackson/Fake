/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.BIZ_ID;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.DISPLAY_MODE_NOT_OTH_SCR_EDIT;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.FUNC_CD_40;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.MESSAGE_KIND_E;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.ZZM8100I;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_InvoiceCancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (!DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Initialize button control 
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // Set alternate rows background color
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        // Initialize tab position
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        // Set focus when opening screen
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            scrnMsg.setMessageInfo(ZZM8100I);
        }

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2020/03/23 [QC#53413, ADD]
        // mod start 2022/02/15 QC#57090
        //NFBL2040CommonLogic.delMultiPoScrnData(scrnMsg);
        NFBL2040CommonLogic.clearMultiPoOrMultiVndRtrnData(scrnMsg);
        // mod end 2022/02/15 QC#57090
        // END   2020/03/23 [QC#53413, ADD]
    }
}
