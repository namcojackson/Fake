/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0860;

import static business.servlet.NFCL0860.constant.NFCL0860Constant.ADJUSTMENT;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.CREDITMEMO;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.DEBITMEMO;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.INVOICE;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.LATEFEE;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.NFCM0814E;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.PAYMENT;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL0860Scrn00_Click_LinkNum_A
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Fujita        Create          N/A
 * 2019/10/04   Fujitsu         Y.Matsui        Create          QC#53891
 * 2021/11/11   CITS            G.Delgado       Update          QC#55788
 *</pre>
 */
public class NFCL0860Scrn00_Click_LinkNum_A extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        Object[] params = new Object[1];

        // START 2021/11/11 G.Delgado [QC#55788, MOD]
//        if (scrnMsg.A.no(selectIdx).xxNum_A.getValue().compareTo(BigDecimal.ONE) == 0) {
//            params[0] = scrnMsg.A.no(selectIdx).arTrxNum_A;
//            setResult(PAYMENT);
//            setArgForSubScreen(params);
//
//        } else if (scrnMsg.A.no(selectIdx).xxNum_A.getValue().compareTo(BigDecimal.valueOf(2)) == 0) {
        if (AR_TRX_TP.RECEIPT.equals(scrnMsg.arTrxTpCd_AB.getValue())
                || scrnMsg.A.no(selectIdx).xxNum_A.getValue().compareTo(BigDecimal.valueOf(2)) == 0) {
        // END 2021/11/11 G.Delgado [QC#55788, MOD]

            if (scrnMsg.A.no(selectIdx).arTrxTpCd_B1.getValue().equals(AR_TRX_TP.INVOICE)) {
                params[0] = scrnMsg.A.no(selectIdx).arTrxNum_B1;
                setResult(INVOICE);
                setArgForSubScreen(params);

            } else if (scrnMsg.A.no(selectIdx).arTrxTpCd_B1.getValue().equals(AR_TRX_TP.CREDIT_MEMO)) {
                params[0] = scrnMsg.A.no(selectIdx).arTrxNum_B1;
                setResult(CREDITMEMO);
                setArgForSubScreen(params);

            } else if (scrnMsg.A.no(selectIdx).arTrxTpCd_B1.getValue().equals(AR_TRX_TP.DEBIT_MEMO)) {
                params[0] = scrnMsg.A.no(selectIdx).arTrxNum_B1;
                setResult(DEBITMEMO);
                setArgForSubScreen(params);

            } else if (scrnMsg.A.no(selectIdx).arTrxTpCd_B1.getValue().equals(AR_TRX_TP.ON_ACCOUNT)) {
                // START 2019/10/04 [QC#53891,MOD]
                // params[0] = scrnMsg.A.no(selectIdx).billToCustAcctCd_B1;
                // setResult(ONACCOUNT);
                params[0] = scrnMsg.A.no(selectIdx).arTrxNum_A3;
                setResult(PAYMENT);
                // END 2019/10/04 [QC#53891,MOD]
                setArgForSubScreen(params);

            } else if (scrnMsg.A.no(selectIdx).arTrxTpCd_B1.getValue().equals(AR_TRX_TP.DEDUCTION)) {
                params[0] = scrnMsg.A.no(selectIdx).billToCustAcctCd_B1;
                setResult(LATEFEE);
                setArgForSubScreen(params);
            } else {
                scrnMsg.setMessageInfo(NFCM0814E);
                throw new EZDFieldErrorException();
            }
        // START 2021/11/11 G.Delgado [QC#55788, ADD]
        } else if (scrnMsg.A.no(selectIdx).xxNum_A.getValue().compareTo(BigDecimal.ONE) == 0) {
            params[0] = scrnMsg.A.no(selectIdx).arTrxNum_A;
            setResult(PAYMENT);
            setArgForSubScreen(params);
            // END 2021/11/11 G.Delgado [QC#55788, ADD]
        } else if (scrnMsg.A.no(selectIdx).xxNum_A.getValue().compareTo(BigDecimal.valueOf(3)) == 0) {
            params[0] = scrnMsg.arTrxNum_AB;
            setResult(ADJUSTMENT);
            setArgForSubScreen(params);
        }
    }
}
