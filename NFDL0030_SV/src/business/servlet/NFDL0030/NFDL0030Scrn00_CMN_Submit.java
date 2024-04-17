/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0030.NFDL0030CMsg;
import business.servlet.NFDL0030.common.NFDL0030CommonLogic;
import business.servlet.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/22   Hitachi         T.Tsuchida      Update          QC#9829
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10203
 * 2017/02/22   Fujitsu         T.Murai         Update          QC#16811
 * 2018/04/24   Hitachi         Y.Takeno        Update          QC#20940
 * 2018/06/15   Hitachi         Y.Takeno        Update          QC#26239
 * 2019/02/07   Fujitsu         S.Ohki          Update          QC#30023
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030Scrn00_CMN_Submit extends S21CommonHandler implements NFDL0030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;

        if (scrnMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            scrnMsg.addCheckItem(scrnMsg.acctDt_H);
            scrnMsg.addCheckItem(scrnMsg.dealNetRcptAmt_H);
        } else {
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).arTrxNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).acctDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dealNetRcptAmt_A1);
            }
        }
        // START 2018/04/24 [QC#20940, ADD]
        scrnMsg.addCheckItem(scrnMsg.dtlNoteTxt_H);
        // END   2018/04/24 [QC#20940, ADD]
        scrnMsg.putErrorScreen();

//        if (!ZYPCommonFunc.hasValue(scrnMsg.crCardLastDigitNum_H)) {
//            scrnMsg.setMessageInfo("NWBM0163E");
//            throw new EZDFieldErrorException();
//        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg = new NFDL0030CMsg();

        bizMsg.setBusinessID("NFDL0030");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        scrnMsg.addCheckItem(scrnMsg.dsPmtMethCd_H);
        if (DS_PMT_METH.CREDIT_CARD.equals(bizMsg.dsPmtMethCd_H.getValue())) {
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            scrnMsg.addCheckItem(scrnMsg.crCardTpNm_H);
            scrnMsg.addCheckItem(scrnMsg.crCardLastDigitNum_H);
            scrnMsg.addCheckItem(scrnMsg.crCardExprYrMth_H);
            scrnMsg.addCheckItem(scrnMsg.crCardHldNm_H);
            // START 2018/05/15 E.Kameishi [QC#24833,ADD]
            scrnMsg.addCheckItem(scrnMsg.crCardCustRefNum_H2);
            // END 2018/05/15 E.Kameishi [QC#24833,ADD]
        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        } else if (DS_PMT_METH.E_CHECK.equals(bizMsg.dsPmtMethCd_H.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.bankRteNum_H);
            scrnMsg.addCheckItem(scrnMsg.dsBankAcctNum_H);
            scrnMsg.addCheckItem(scrnMsg.sellToCustLocNm_H);
        }
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        if (scrnMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            scrnMsg.addCheckItem(scrnMsg.acctDt_H);
            scrnMsg.addCheckItem(scrnMsg.dealNetRcptAmt_H);

        } else {
            for(int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).arTrxNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).acctDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).dealNetRcptAmt_A1);
            }
            // START 2018/06/15 [QC#26239, ADD]
            scrnMsg.addCheckItem(scrnMsg.dealNetRcptAmt_H);
            // END   2018/06/15 [QC#26239, ADD]
        }
        // START 2016/08/26 K.Kojima [QC#10203,ADD]
        // START 2018/06/15 [QC#26239, MOD]
        if (scrnMsg.dealNetRcptAmt_H.getErrorCode() == 0) {
            NFDL0030CommonLogic.calculationTotalAmountForModeInvoice(scrnMsg);
        }
        // END 2018/06/15 [QC#26239, MOD]
        // END 2016/08/26 K.Kojima [QC#10203,ADD]
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }

        // START 2019/02/07 S.Ohki [QC#30023,ADD]
        bizMsg = (NFDL0030CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, FUNC_ID, "70");
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            // Search do not set error message on fields.
            throw new EZDFieldErrorException();
        }

        String tempFilePath = bizMsg.xxFileData.getTempFilePath();
        executeDownloadPdf(tempFilePath, true);
        // END   2019/02/07 S.Ohki [QC#30023,ADD]

        // ADD 2017/02/22 [QC#16811]
        NFDL0030CommonLogic.submitSuccessScreen(this, scrnMsg);

        // START 2016/07/22 T.Tsuchida [QC#9829,ADD]
        scrnMsg.setMessageInfo("NZZM0002I");
        // END 2016/07/22 T.Tsuchida [QC#9829,ADD]

    }
}
