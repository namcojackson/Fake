/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

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
 * 2016/10/07   Hitachi         Y.Tsuchimoto    Update          QC#QC#15091
 *</pre>
 */
public class NFBL2040Scrn00_Refresh extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.entPoDtlDealNetAmt_TO);
        scrnMsg.addCheckItem(scrnMsg.invAmt);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invRcvQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apRejQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxInvAmt_A1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        if (TAB_LINES.equals(scrnMsg.xxDplyTab_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_D);
        }
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
        // START 2016/10/07 Y.Tsuchimoto [QC#15091,MOD]
        //if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
        //    NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForChangeCmApInvTpCd(scrnMsg);
        //    NFBL2040CommonLogic.setButtonNotFromOthScrForEditForChangeCmApInvTpCd(this, scrnMsg);
        //} else {
        //    /** Initialize button control */
        //    NFBL2040CommonLogic.initControl(this, scrnMsg);
        //}
        if (!DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            /** Initialize button control */
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // END   2016/10/07 Y.Tsuchimoto [QC#15091,MOD]
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,MOD]

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
    }
}
