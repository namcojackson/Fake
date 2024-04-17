/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/09/14   Hitachi         K.Kojima        Update          QC#13546
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/06/14   Hitachi         Y.Takeno        Update          QC#26129
 * 2019/05/23   Hitachi         H.Umeda         Update          QC#50451
 * 2021/11/11   CITS            G.Delgado       Update          QC#55788
 * 2022/01/08   CITS            k.Suzuki        Update          QC#55788-1
 *</pre>
 */
public class NFDL0020Scrn00_Click_TransactionActivity extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            scrnMsg.setMessageInfo("NFDM0024E");
        }

        if (checkList.size() > 1) {
            for (int i : checkList) {
                scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFBM0064E");
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            }
        } else {
            for (int i : checkList) {
                // START 2018/06/14 [QC#26129, MOD]
                // if (!scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.INVOICE)) {
                //     scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFCM0825E");
                //     scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                // } else if (scrnMsg.A.no(i).dealOrigGrsAmt_A.getValue().compareTo(BigDecimal.ZERO) < 0) {
                //     scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFDM0038E");
                //     scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                // }
// START 2019/5/23 H.Umeda [QC#50451,MOD]
//              if (!AR_TRX_TP.INVOICE.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue())
//                      && !AR_TRX_TP.CREDIT_MEMO.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue())) {
                // START 2022/01/08 K.Suzuki [QC#55788-1,DEL]
                //if (!AR_TRX_TP.INVOICE.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue())
                // && !AR_TRX_TP.CREDIT_MEMO.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue())
                // && !AR_TRX_TP.DEDUCTION.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue())
                // && !AR_TRX_TP.ON_ACCOUNT.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue())) {
// END   2019/5/23 H.Umeda [QC#50451,MOD]
                //    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFDM0049E");
                //    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                //}
                // END   2022/01/08 K.Suzuki [QC#55788-1,DEL]
                // START 2022/01/08 K.Suzuki [QC#55788-1,ADD]
                if (AR_TRX_TP.RECEIPT.equals(scrnMsg.A.no(i).arTrxTpCd_A.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxNum_A1)){
                    scrnMsg.A.no(i).xxChkBox_A.setErrorInfo(1, "NFDM0049E");
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
                }
                // END   2022/01/08 K.Suzuki [QC#55788-1,ADD]
                // END   2018/06/14 [QC#26129, MOD]
            }
        }

        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageInfo() != null && scrnMsg.getMessageInfo().getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        Object[] params = new Object[1];
        params[0] = scrnMsg.dsAcctNum_H;
        for (int i : checkList) {
            params[0] = scrnMsg.A.no(i).arTrxNum_A;
            // START 2022/01/08 K.Suzuki [QC#55788-1,ADD]
            if(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).arTrxNum_A1)){
                params[0] = scrnMsg.A.no(i).arTrxNum_A1;
            }
            // END   2022/01/08 K.Suzuki [QC#55788-1,ADD]
        }

        setArgForSubScreen(params);
    }
}
