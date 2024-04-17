/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.BIZ_ID;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.FUNC_CD_20;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.MESSAGE_KIND_E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Create          QC#12043
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16949
 * 2017/01/24   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/01/05   Hitachi         Y.Takeno        Update          QC#22143
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 *</pre>
 */
public class NFBL2040Scrn00_DeleteRowHold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            // START 2020/03/16 [QC#55993, ADD]
            NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
            // END   2020/03/16 [QC#55993, ADD]
            throw new EZDFieldErrorException();
        }

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        // START 2017/01/13 E.Kameishi [QC#16949,MOD]
        /** Set focus when opening screen */
        //scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);
        if (scrnMsg.H.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.H.no(0).xxChkBox_H2);
        } else {
            scrnMsg.setFocusItem(scrnMsg.apVndInvNum_HH);
        }
        // END 2017/01/13 E.Kameishi [QC#16949,MOD]
        NFBL2040CommonLogic.setInputProtectedForAfterInsertAndDelete(scrnMsg);

        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);

        // START 2018/01/05 [QC#22143, DEL]
        // START 2017/01/13 E.Kameishi [QC#16948,ADD]
        // String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        // if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
        //     boolean isProtected = true;
        //     for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
        //         if (!scrnMsg.H.no(i).xxChkBox_H2.isInputProtected()) {
        //             isProtected = false;
        //         }
        //     }
        //     if (isProtected) {
        //         this.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        //     }
        // }
        // END 2017/01/13 E.Kameishi [QC#16948,ADD]
        // END 2018/01/05 [QC#22143, DEL]

        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }
}
