/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.blap.NFDL0030.constant.NFDL0030Constant;
import business.servlet.NFDL0030.common.NFDL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/04/20   Fujitsu         C.Naito         Update          QC#7021
 * 2016/05/09   Fujitsu         S.Fujita        Update          QC#7021
 * 2016/07/25   Hitachi         K.Kojima        Update          QC#10203
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#13862
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/04/24   Hitachi         Y.Takeno        Update          QC#20940
 * 2019/02/07   Fujitsu         S.Ohki          Update          QC#30023
 * 2020/01/29   CITS            M.Furugoori     Update          QC#55017
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
// START 2016/05/09 S.Fujita [QC#7021,MOD]
//public class NFDL0030_INIT extends S21CommonHandler implements NFDL0030Constant {
public class NFDL0030_INIT extends S21INITCommonHandler implements NFDL0030Constant {
// END 2016/05/09 S.Fujita [QC#7021,MOD]

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFDL0030");
        // End   2016/12/27 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg = new NFDL0030CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {

            if (params.length == 1) {
                // Account Mode
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_ON_ACCOUNT);
            } else if (params.length == 2) {
                // Invoice Mode
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                EZDBStringItem param02 = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param01);
                // START 2020/01/29 [QC#55017,MOD]
                // ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxCdSrchTxt, param02);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxNumSrchTxt, param02);
                // END   2020/01/29 [QC#55017,MOD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_INVOICE);
            } else {
                scrnMsg.setMessageInfo("NFCM0041E");
                scrnMsg.putErrorScreen();
            }
        }

        bizMsg.setBusinessID("NFDL0030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2019/02/07 S.Ohki [QC#30023,ADD]
        NFDL0030CommonLogic.payFullAllCheckOn(scrnMsg);
        // END 2019/02/07 S.Ohki [QC#30023,ADD]

        NFDL0030CommonLogic.initialize(this, scrnMsg, getUserProfileService());

        // START 2019/02/07 S.Ohki [QC#30023,ADD]
        NFDL0030CommonLogic.calculationTotalAmountForModeInvoice(scrnMsg);
        // END 2019/02/07 S.Ohki [QC#30023,ADD]

        // [QC#7021] INSERT start
        setAppFracDigit(scrnMsg);
        // [QC#7021] INSERT end
    }

    // [QC#7021] INSERT start
    private void setAppFracDigit(NFDL0030BMsg scrnMsg) {
        // START 2016/07/25 K.Kojima [QC#10203,ADD]
        scrnMsg.dealNetRcptAmt_H.setAppFracDigit(2);
        // END 2016/07/25 K.Kojima [QC#10203,ADD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dealNetRcptAmt_A1.setAppFracDigit(2);
        }
    }
    // [QC#7021] INSERT end

    // START 2016/05/09 S.Fujita [QC#7021,MOD]
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        scrnMsg.acctDt_H.setNameForMessage("Trans Date");
        scrnMsg.dealNetRcptAmt_H.setNameForMessage("Payment Amount");
        scrnMsg.crCardTpNm_H.setNameForMessage("Card Type");
        scrnMsg.crCardLastDigitNum_H.setNameForMessage("Card Number");
        scrnMsg.crCardExprYrMth_H.setNameForMessage("Expiration Year Month");
        scrnMsg.crCardHldNm_H.setNameForMessage("Card Holder Name");
        scrnMsg.xxAllLineAddr_H.setNameForMessage("Bill to Address");
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        scrnMsg.dsPmtMethCd_H.setNameForMessage("Payment Type");
        scrnMsg.bankRteNum_H.setNameForMessage("Bank Routing Number");
        scrnMsg.dsBankAcctNum_H.setNameForMessage("Bank Account Number");
        scrnMsg.sellToCustLocNm_H.setNameForMessage("Payer Name");
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFDL0030_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.arTrxNum_A1.setNameForMessage("Invoice#");
            aBMsg.acctDt_A1.setNameForMessage("Trans Date");
            // START 2016/09/12 K.Kojima [QC#13862,MOD]
            // aBMsg.dealNetRcptAmt_A1.setNameForMessage("Payment Balance");
            aBMsg.dealNetRcptAmt_A1.setNameForMessage("Payment Amount");
            // END 2016/09/12 K.Kojima [QC#13862,MOD]
        }
        // START 2018/04/24 [QC#20940, ADD]
        scrnMsg.dtlNoteTxt_H.setNameForMessage("Comment");
        // END   2018/04/24 [QC#20940, ADD]
        
    }
    // END 2016/05/09 S.Fujita [QC#7021,MOD]
}
