/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3010.NFCL3010CMsg;
import business.servlet.NFCL3010.common.NFCL3010CommonLogic;
import business.servlet.NFCL3010.constant.NFCL3010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 02/02/2018   Fujitsu         T.Murai         Update          S21_NA#21372
 * 2018/03/20   Fujitsu         H.Ikeda         Update          QC#21737
 *</pre>
 */
public class NFCL3010_INIT extends S21INITCommonHandler implements NFCL3010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL3010");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

        NFCL3010CMsg bizMsg = new NFCL3010CMsg();
        bizMsg.setBusinessID("NFCL3010");
        bizMsg.setFunctionCode("20");

        // START 2018/06/151 J.Kim [QC#25695,ADD]
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param_01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.arBatRcptNm_H, param_01);
            }
        }
        // NED 2018/06/151 J.Kim [QC#25695,ADD]

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21TableColorController tblColor = new S21TableColorController("NFCL3010Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        // Del Start 2018/03/20 S21_NA#21737
        //tblColor.clearRowsBG(TABLE_B, scrnMsg.B);
        // Del End   2018/03/20 S21_NA#21737
        NFCL3010CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        scrnMsg.arRcptSrcCd_H.setNameForMessage("Receipt Source");
        scrnMsg.arBatRcptNm_H.setNameForMessage("Batch Name");
        scrnMsg.arBatRcptStsCd_H.setNameForMessage("Batch Status");
        scrnMsg.arLockBoxFileNm_H.setNameForMessage("Lockbox File Name");
        scrnMsg.arLockBoxCd_H.setNameForMessage("Lockbox");
        scrnMsg.arLockBoxBatNum_H.setNameForMessage("Lockbox Batch");
        // Mod Start 2018/02/02 S21_NA#21372
        scrnMsg.dsAcctNum_H.setNameForMessage("Customer Number");
        // scrnMsg.dsAcctNum_H1.setNameForMessage("Customer Number From");
        // scrnMsg.dsAcctNum_H2.setNameForMessage("Customer Number To");
        // Mod End 2018/02/02 S21_NA#21372
        scrnMsg.dsAcctNm_H.setNameForMessage("Customer Name");
        scrnMsg.rcptNum_H1.setNameForMessage("Receipt Number From");
        scrnMsg.rcptNum_H2.setNameForMessage("Receipt Number To");
        scrnMsg.rcptDt_H1.setNameForMessage("Receipt Date From");
        scrnMsg.rcptDt_H2.setNameForMessage("Receipt Date To");
        scrnMsg.funcRcptAmt_H1.setNameForMessage("Receipt Amount From");
        scrnMsg.funcRcptAmt_H2.setNameForMessage("Receipt Amount To");
        scrnMsg.psnCd_H.setNameForMessage("Collector");
        scrnMsg.cratDt_H1.setNameForMessage("Creation Date From");
        scrnMsg.cratDt_H2.setNameForMessage("Creation Date To");
        scrnMsg.arRcptStsCd_H.setNameForMessage("Receipt Status");

        scrnMsg.funcRcptAmt_H1.setAppFracDigit(2);
        scrnMsg.funcRcptAmt_H2.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).arBatRcptAmt_A.setAppFracDigit(2);
            // Mod Start 2018/03/20 S21_NA#21737
            scrnMsg.A.no(i).funcApplyAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcRcptAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).xxTotAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcRcptRmngBalAmt_A.setAppFracDigit(2);
        }
        //for (int i = 0; i < scrnMsg.B.length(); i++) {
        //    scrnMsg.B.no(i).funcApplyAmt_B.setAppFracDigit(2);
        //    scrnMsg.B.no(i).funcRcptAmt_B.setAppFracDigit(2);
        //}
        // Mod End   2018/03/20 S21_NA#21737
    }
}
