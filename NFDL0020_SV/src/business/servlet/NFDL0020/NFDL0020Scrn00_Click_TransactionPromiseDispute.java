/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/06   Hitachi         J.Kim           Create          QC#20945
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0020Scrn00_Click_TransactionPromiseDispute extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        int chkCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                chkCnt++;
            }
        }
        // START 2023/05/26 S.Nakatani [QC#61271,DEL]
//        if (chkCnt > 1) {
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
//                    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFBM0064E");
//                }
//            }
//        } else {
        // END 2023/05/26 S.Nakatani [QC#61271,DEL]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
                    if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.CREDIT_MEMO)) {
                        scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFCM0825E");
                    } else if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.ON_ACCOUNT)) {
                        scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFCM0825E");
                    } else if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.RECEIPT)) {
                        scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFCM0825E");
                    } else if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.DEDUCTION)) {
                        scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFCM0825E");
                    } else if (scrnMsg.A.no(i).dealOrigGrsAmt_A.getValue().compareTo(BigDecimal.ZERO) < 0) {
                        scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFDM0038E");
                    }
                }
            // START 2023/05/26 S.Nakatani [QC#61271,DEL]
//            }
            // END 2023/05/26 S.Nakatani [QC#61271,DEL]
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        int chkCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
                chkCnt++;
            }
        }

        Object[] params;
        if (chkCnt > 0) {
            params = new Object[2];
            params[0] = scrnMsg.dsAcctNum_H;
            // START 2023/05/26 S.Nakatani [QC#61271,ADD]
            boolean isFirstChecked = true;
            // END 2023/05/26 S.Nakatani [QC#61271,ADD]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxChkBox_A)) {
                    // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//                    params[1] = scrnMsg.A.no(i).arTrxNum_A;
//                    break;
                    if (isFirstChecked) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxNumSrchTxt, scrnMsg.A.no(i).arTrxNum_A);
                        isFirstChecked = false;
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxNumSrchTxt, S21StringUtil.concatStrings(scrnMsg.xxTrxNumSrchTxt.getValue(), ",", scrnMsg.A.no(i).arTrxNum_A.getValue()));
                    }
                    // END 2023/05/26 S.Nakatani [QC#61271,MOD]
                }
            }
            // START 2023/05/26 S.Nakatani [QC#61271,ADD]
            params[1] = scrnMsg.xxTrxNumSrchTxt;
            // END 2023/05/26 S.Nakatani [QC#61271,ADD]
        } else {
            params = new Object[1];
            params[0] = scrnMsg.dsAcctNum_H;
        }

        setArgForSubScreen(params);
    }
}
