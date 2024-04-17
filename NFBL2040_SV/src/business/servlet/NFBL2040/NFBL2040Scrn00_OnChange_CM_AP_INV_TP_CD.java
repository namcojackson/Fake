/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.DISPLAY_MODE_NOT_OTH_SCR_EDIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13333
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_OnChange_CM_AP_INV_TP_CD extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/03/23 [QC#53413, MOD]
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, false);
            // END   2020/03/23 [QC#53413, MOD]

            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForChangeCmApInvTpCd(scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForChangeCmApInvTpCd(this, scrnMsg);
            // START 2016/09/14 Y.Tsuchimoto [QC#13333,ADD]
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForLinesTab(scrnMsg, false);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForEditForLinesTab(this, scrnMsg, false);
            // END   2016/09/14 Y.Tsuchimoto [QC#13333,ADD]
        } else {
            /** Initialize button control */
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,MOD]

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]

    }
}
