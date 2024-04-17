/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import static business.servlet.NFAL0220.constant.NFAL0220Constant.*;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0220.NFAL0220CMsg;
import business.servlet.NFAL0220.common.NFAL0220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NFAL0220_INIT
 * Manual Journal Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/12/01   Fujitsu         T.Murai         Update          QC#16158
 *</pre>
 */
public class NFAL0220_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        NFAL0220CMsg bizMsg = NFAL0220CommonLogic.setRequestData_SearchCommon();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params != null && params.length == 1) {
                EZDBBigDecimalItem manJrnlEntryHdrPk = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.manJrnlEntryHdrPk, manJrnlEntryHdrPk);
            }
        } else {
            scrnMsg.manJrnlEntryHdrPk.clear();
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        NFAL0220CMsg bizMsg = (NFAL0220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0220CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NFAL0220CommonLogic.initCmnBtnProp(this);
        NFAL0220CommonLogic.setGuiAttr(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.manJrnlNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        scrnMsg.manJrnlNm.setNameForMessage("Journal Name");
        scrnMsg.glSendCpltFlg.setNameForMessage("GL Send");
        scrnMsg.manJrnlCpltFlg.setNameForMessage("Complete");
        scrnMsg.manJrnlDescTxt.setNameForMessage("Description");
        scrnMsg.rsvdRvslDt.setNameForMessage("Reversal Date");
        scrnMsg.autoRvslFlg.setNameForMessage("Auto Reversal");
        scrnMsg.rvslCpltFlg.setNameForMessage("Reversed");
        scrnMsg.xxChkBox_C.setNameForMessage("Cancel");
        scrnMsg.jrnlCatgCd.setNameForMessage("Category");
        scrnMsg.jrnlCatgDescTxt.setNameForMessage("Category");
        scrnMsg.glPerNm.setNameForMessage("GL Period");
        scrnMsg.glDt.setNameForMessage("Accounting Date");
        scrnMsg.xxFileData.setNameForMessage("Upload File");
        scrnMsg.xxChkBox_V.setNameForMessage("Call Cross Validation Check");

        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName()); //ADD 2016/12/01 [QC#16158]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFAL0220_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.xxNum_A.setNameForMessage("Line#");
            aBMsg.xxScrItem61Txt_A.setNameForMessage("Accounting String");
            aBMsg.jrnlDealAmt_A1.setNameForMessage("Debit");
            aBMsg.jrnlDealAmt_A2.setNameForMessage("Credit");
            aBMsg.manJrnlLineTxt_A.setNameForMessage("Line Description");
            aBMsg.dsAcctNum_A.setNameForMessage("Customer#");
            aBMsg.serNum_A.setNameForMessage("Serial#");
            aBMsg.jrnlEntrySrcTpCd_LS.setNameForMessage("Source Type");
            aBMsg.jrnlEntrySrcValTxt_A.setNameForMessage("Source Value");
        }

        scrnMsg.jrnlDealAmt_T1.setAppFracDigit(2);
        scrnMsg.jrnlDealAmt_T2.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).jrnlDealAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).jrnlDealAmt_A2.setAppFracDigit(2);
        }
    }
}
