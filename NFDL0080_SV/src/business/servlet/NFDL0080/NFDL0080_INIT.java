/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.servlet.NFDL0080;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0080.NFDL0080CMsg;
import business.servlet.NFDL0080.common.NFDL0080CommonLogic;
import business.servlet.NFDL0080.constant.NFDL0080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;

/**
 * <pre>
 * NFDL0080_INIT.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/10/06   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/04/24   Hitachi         Y.Takeno        Update          QC#20940
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno        Update          QC#26989-1
 * 2019/07/31   Fujitsu         M.Ishii         Update          QC#52217
 * </pre>
 */
public class NFDL0080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NFDL0080Constant.BUSINESS_ID);
        // End   2016/12/27 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0080CMsg bizMsg = null;
        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        // IN Parameter -Get
        Object[] params = (Object[]) getArgForSubScreen();

        // START 2018/07/11 [QC#26989, MOD]
        // if (params != null && params.length == 2) {
        if (params != null && params.length == 3) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxNum_H1, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.arCustRefNum_H1, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxBalPk_H1, (EZDBBigDecimalItem) params[2]);

            if (!ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H1)) {
                scrnMsg.setMessageInfo("NFDM0001E", new String[] {scrnMsg.arTrxNum_H1.getNameForMessage()});
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum_H1)) {
                scrnMsg.setMessageInfo("NFDM0001E", new String[] {scrnMsg.arCustRefNum_H1.getNameForMessage()});
                return null;
            }
        } else {
            scrnMsg.setMessageInfo("NFDM0001E", new String[] {scrnMsg.arTrxNum_H1.getNameForMessage()});
            return null;
        }
        // END   2018/07/11 [QC#26989, MOD]

        bizMsg = NFDL0080CommonLogic.setRequestData_NFDL0080_Search();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;

        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // ScreenItem AddCheckItem
        NFDL0080CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        // Button Initialize
        NFDL0080CommonLogic.initialize(this, scrnMsg);

        // Button CommonBtnControl
        NFDL0080CommonLogic.commonBtnControl(this);

        // ScreenDetail Control
        NFDL0080CommonLogic.setDetailControl(scrnMsg);

        // PeculiarProcessing
        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                NFDL0080CommonLogic.protectReturnOnly(scrnMsg, this);

            } else {

                NFDL0080CommonLogic.protectModeNewly(scrnMsg, this);
                scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H2);

            }
        } else {
            NFDL0080CommonLogic.protectReturnOnly(scrnMsg, this);
        }

        NFDL0080CommonLogic.setButtonConfirmMsgCmnReturn(scrnMsg, this);

        NFDL0080CommonLogic.setAppFracDigit(scrnMsg);

        // START 2018/07/11 [QC#26989, ADD]
        S21SortColumnIMGController.clearIMG(NFDL0080Constant.SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2018/07/11 [QC#26989, ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFDL0080BMsg scrnMsg = (NFDL0080BMsg) bMsg;

        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        // START 2018/07/11 [QC#26989, MOD]
        // scrnMsg.dsAcctNum_H1.setNameForMessage("Account No");
        scrnMsg.dsAcctNum_H1.setNameForMessage("Account");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Account Name");
        // scrnMsg.arTrxNum_H1.setNameForMessage("Rec/CM");
        scrnMsg.arTrxNum_H1.setNameForMessage("Transaction#");
        scrnMsg.xxTotAmt_H1.setNameForMessage("Amt to Apply");
        scrnMsg.dealRmngBalGrsAmt_H1.setNameForMessage("Balance to Apply");
        scrnMsg.xxChkBox_H1.setNameForMessage("Related Customers");
        scrnMsg.dsAcctNum_H2.setNameForMessage("Amt to Apply");
        // 2019/07/31 QC#52217 Mod Start
//        scrnMsg.xxTrxCdSrchTxt_H1.setNameForMessage("Invoices");
        scrnMsg.xxTrxNumSrchTxt_H1.setNameForMessage("Invoices");
        // 2019/07/31 QC#52217 Mod End
        // START 2018/04/24 [QC#20940, ADD]
        scrnMsg.dtlNoteTxt_H1.setNameForMessage("Comment");
        // END   2018/04/24 [QC#20940, ADD]
        // END   2018/07/11 [QC#26989, MOD]
        // START 2018/07/24 [QC#26989-1, ADD]
        scrnMsg.custIssPoNum_H2.setNameForMessage("PO Number");
        // END   2018/07/24 [QC#26989-1, ADD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // START 2018/07/11 [QC#26989, MOD]
            // scrnMsg.A.no(i).billToCustAcctCd_A1.setNameForMessage("Account No");
            scrnMsg.A.no(i).billToCustAcctCd_A1.setNameForMessage("Account#");
            // Mod Start 2017/10/06 QC#19922
            //scrnMsg.A.no(i).billToCustCd_A1.setNameForMessage("Bill-To-Loc");
            // scrnMsg.A.no(i).billToCustCd_A1.setNameForMessage("Bill-To-Code");
            scrnMsg.A.no(i).billToCustCd_A1.setNameForMessage("Bill To");
            // Mod End 2017/10/06 QC#19922
            // scrnMsg.A.no(i).arTrxNum_A1.setNameForMessage("Invoice No");
            scrnMsg.A.no(i).arTrxNum_A1.setNameForMessage("Invoice#");
            scrnMsg.A.no(i).arTrxDt_A1.setNameForMessage("Invoice Date");
            // scrnMsg.A.no(i).arTrxTpCd_A1.setNameForMessage("Invoice Class");
            scrnMsg.A.no(i).arTrxTpCd_A1.setNameForMessage("Trx Type");
            // scrnMsg.A.no(i).cpoOrdNum_A1.setNameForMessage("Contract/Order No");
            scrnMsg.A.no(i).cpoOrdNum_A1.setNameForMessage("Contract/Order#");
            // scrnMsg.A.no(i).custIssPoNum_A1.setNameForMessage("PO No");
            scrnMsg.A.no(i).custIssPoNum_A1.setNameForMessage("PO Number");
            // scrnMsg.A.no(i).grpInvNum_A1.setNameForMessage("Bill No");
            scrnMsg.A.no(i).grpInvNum_A1.setNameForMessage("ConBill#");
            // scrnMsg.A.no(i).bllgPerFromDt_A1.setNameForMessage("Bill From");
            scrnMsg.A.no(i).bllgPerFromDt_A1.setNameForMessage("Bill Period From");
            // scrnMsg.A.no(i).bllgPerToDt_A1.setNameForMessage("Bill To");
            scrnMsg.A.no(i).bllgPerToDt_A1.setNameForMessage("Bill Period To");
            scrnMsg.A.no(i).dealOrigGrsAmt_A1.setNameForMessage("Orig Amt");
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.setNameForMessage("Remain Amt");
            // scrnMsg.A.no(i).invDueDt_A1.setNameForMessage("Duee Date");
            scrnMsg.A.no(i).invDueDt_A1.setNameForMessage("Due Date");
            scrnMsg.A.no(i).pastDtAot_A1.setNameForMessage("Days Past Due");
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setNameForMessage("Amt To Apply");
            // END   2018/07/11 [QC#26989, MOD]
        }
    }
}
