/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;

import business.servlet.NFBL2040.constant.NFBL2040Constant;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/29   Hitachi         Y.Takeno        Create          QC#28904
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_Regenerate_Invoice extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
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
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        /** Initialize button control */ 
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                // Common
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
                // START 2020/02/17 [QC#53413, MOD]
                //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, true);
                // END   2020/02/17 [QC#53413, MOD]
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearch(scrnMsg);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditForSearch(this, scrnMsg);
                // START 2020/02/17 [QC#53413, MOD]
                //NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg);
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg, false);
                // END   2020/02/17 [QC#53413, MOD]
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditForSearchLinesTab(this, scrnMsg);
            }
        } else {
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);

        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
 
        /** Set focus when opening screen */
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.apVndInvNum);
        } else {
            scrnMsg.setFocusItem(scrnMsg.dplyVndNm);
        }
        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
    }
}
