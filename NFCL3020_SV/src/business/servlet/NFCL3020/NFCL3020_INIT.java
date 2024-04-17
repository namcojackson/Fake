/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3020.NFCL3020CMsg;
import business.servlet.NFCL3020.common.NFCL3020CommonLogic;
import business.servlet.NFCL3020.constant.NFCL3020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 10/12/2016   Hitachi         E.Kameishi      Update          QC#14273
 * 01/18/2018   Hitachi         Y.Takeno        Update          QC#21406
 *</pre>
 */
public class NFCL3020_INIT extends S21INITCommonHandler implements NFCL3020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL3020");
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        NFCL3020CMsg bizMsg = new NFCL3020CMsg();
        bizMsg.setBusinessID("NFCL3020");
        bizMsg.setFunctionCode("20");

        Object[] params = (Object[]) getArgForSubScreen();

        ZYPEZDItemValueSetter.setValue(scrnMsg.appFuncId, "NFCL3020");
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.arBatRcptNm_H, param01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.appFuncId, "NFCL30XX");
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        NFCL3020CMsg bizMsg = (NFCL3020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3020CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        
        NFCL3020CommonLogic.setTableCursor(scrnMsg);

        // Display TAB = Add Info
        scrnMsg.xxDplyTab.setValue(TAB_AddInfo);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        scrnMsg.arRcptSrcCd_H.setNameForMessage("Receipt Source");
        scrnMsg.arBatRcptNm_H.setNameForMessage("Batch Name");
        scrnMsg.arBatRcptStsCd_H.setNameForMessage("Batch Status");
        scrnMsg.arLockBoxFileNm_H.setNameForMessage("Lockbox File Name");
        scrnMsg.arLockBoxCd_H.setNameForMessage("Lockbox");
        scrnMsg.arLockBoxBatNum_H.setNameForMessage("Lockbox Batch");
        // START 2016/10/12 E.Kameishi [QC#14273,ADD]
        scrnMsg.arBatRcptCnt_H.setNameForMessage("Batch Count");
        scrnMsg.arBatRcptAmt_H.setNameForMessage("Batch Amount");
        scrnMsg.arBatRcptDt_H.setNameForMessage("Batch Date");
        // START 2018/01/18 [QC#21406, ADD]
        scrnMsg.rcptChkNum_BH.setNameForMessage("Receipt#");
        // END   2018/01/18 [QC#21406, ADD]
        scrnMsg.arRcptTrxTpCd_BH.setNameForMessage("Receipt Type");
        scrnMsg.rcptDt_BH.setNameForMessage("Receipt Date");
        scrnMsg.funcRcptAmt_BH.setNameForMessage("Amount");
        // END 2016/10/12 E.Kameishi [QC#14273,ADD]

        scrnMsg.arBatRcptAmt_H.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H1.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H2.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H3.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H4.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H5.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H6.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H7.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H8.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H9.setAppFracDigit(2);

        scrnMsg.funcRcptAmt_BH.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            // START 2018/01/18 [QC#21406, ADD]
            scrnMsg.B.no(i).rcptChkNum_B.setNameForMessage("Receipt#");
            scrnMsg.B.no(i).funcRcptAmt_B.setNameForMessage("Amount");
            // END   2018/01/18 [QC#21406, ADD]
            scrnMsg.B.no(i).funcApplyAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).funcRcptAmt_B.setAppFracDigit(2);
        }
        
        scrnMsg.setFocusItem(scrnMsg.arRcptSrcCd_H);
    }
}
