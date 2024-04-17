/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import static business.servlet.NFCL2610.constant.NFCL2610Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.servlet.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4758
 * 2017/10/05   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/03/12   Fujitsu         H.Ikeda         Update          QC#22762
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2022/07/25   Hitachi         A.Kohinata      Update          QC#57417
 *</pre>
 */
public class NFCL2610_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        NFCL2610CommonLogic.setArgForSubScreen(arg, scrnMsg);
        NFCL2610CMsg bizMsg = NFCL2610CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
        NFCL2610CMsg bizMsg = (NFCL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2610CommonLogic.initialize(this, scrnMsg);
        NFCL2610CommonLogic.initAppFracDigit(scrnMsg);
        NFCL2610CommonLogic.setFocusRule(scrnMsg);
        NFCL2610CommonLogic.setListInactive(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;

        scrnMsg.arRfRqstPk.setNameForMessage("Refund Number");
        scrnMsg.arRfTpCd_H.setNameForMessage("Refund Type");
        scrnMsg.xxChkBox_1.setNameForMessage("Refund Trx Tp");
        scrnMsg.arRfRqstPk.setNameForMessage("Credit Memo");
        scrnMsg.billToCustAcctCd.setNameForMessage("Customer");
        // START 2018/03/12 H.Ikeda [QC#22762,ADD]
        scrnMsg.xxChkBox_5.setNameForMessage("Relatied Customers");
        // END   2018/03/12 H.Ikeda [QC#22762,ADD
        // START 2018/07/11 [QC#26989, ADD]
        scrnMsg.xxQueryFltrTxt.setNameForMessage("Transaction#");
        // END   2018/07/11 [QC#26989, ADD]
        // Mod Start 2017/10/05 QC#19922
        //scrnMsg.billToCustLocCd.setNameForMessage("Bill To Loc");
        scrnMsg.billToCustLocCd.setNameForMessage("Bill To Code");
        // Mod End 2017/10/05 QC#19922
        scrnMsg.xxAllLineAddr.setNameForMessage("Address");
        scrnMsg.arRcptRfCmntTxt.setNameForMessage("Refund Comment");
        // del start 2022/07/25 QC#57417
        //scrnMsg.arRcptRfRsnDescTxt_H.setNameForMessage("Refund Reason");
        // del end 2022/07/25 QC#57417
        scrnMsg.arRfChkCmntTxt.setNameForMessage("Check Notes");
        scrnMsg.xxTotRfAmt.setNameForMessage("Total Refund Amount");
        scrnMsg.xxRadioBtn.setNameForMessage("#");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_3.setNameForMessage("#");
            // add start 2022/07/25 QC#57417
            scrnMsg.A.no(i).arRcptRfRsnCd_A.setNameForMessage("Refund Reason");
            // add end 2022/07/25 QC#57417
        }

        for (int j = 0; j < scrnMsg.B.length(); j++) {
            scrnMsg.B.no(j).xxChkBox_4.setNameForMessage("Active");
        }
    }
}
