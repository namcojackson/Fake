/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2040.NFBL2040CMsg;
//import business.servlet.NFBL2040.constant.NFBL2040Constant;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2016/10/17   Hitachi         Y.Tsuchimoto    Update          QC#12957
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_CMN_Clear extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,ADD]
        // START 2016/10/17 Y.Tsuchimoto [QC#12957,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/02/17 [QC#53413, MOD]
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, false);
            // END   2020/02/17 [QC#53413, MOD]
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForInit(scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForInit(this, scrnMsg);
        } else if (DISPLAY_MODE_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/02/17 [QC#53413, MOD]
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, false);
            // END   2020/02/17 [QC#53413, MOD]
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForInit(scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditForInit(this, scrnMsg);

            // Change DISPLAY_MODE_OTH_SCR_EDIT -> DISPLAY_MODE_NOT_OTH_SCR_EDIT
            scrnMsg.apVndInvNum_OT.clear();
        }
        // END   2016/10/17 Y.Tsuchimoto [QC#12957,MOD]
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,ADD]

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
    }
}
