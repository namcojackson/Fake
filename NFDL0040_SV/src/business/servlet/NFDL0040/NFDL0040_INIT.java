/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0040;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0040.NFDL0040CMsg;
import business.servlet.NFDL0040.common.NFDL0040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Promise/Dispute Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/08/08   Hitachi         K.Kojima        Update          QC#13005
 * 2016/08/26   Hitachi         K.Kojima        Update          QC#10786
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2023/05/26   Hitachi         S.Nakatani      Update          QC#61271
 *</pre>
 */
public class NFDL0040_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFDL0040");
        // End   2016/12/27 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0040BMsg scrnMsg = (NFDL0040BMsg) bMsg;

        NFDL0040CMsg bizMsg = new NFDL0040CMsg();
        bizMsg.setBusinessID("NFDL0040");
        bizMsg.setFunctionCode("20");

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param_01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param_01);
            } else if (params.length == 2) {
                EZDBStringItem param_01 = (EZDBStringItem) params[0];
                EZDBStringItem param_02 = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param_01);
                // START 2023/05/26 S.Nakatani [QC#61271,MOD]
//                ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxNum_H, param_02);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxNumSrchTxt, param_02);
                // END 2023/05/26 S.Nakatani [QC#61271,MOD]
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0040BMsg scrnMsg = (NFDL0040BMsg) bMsg;
        NFDL0040CMsg bizMsg  = (NFDL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0040CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFDL0040BMsg scrnMsg = (NFDL0040BMsg) bMsg;
        scrnMsg.dealCltPrmsAmt_AH.setNameForMessage("Promise Amount");
        scrnMsg.cltPrmsDt_AH.setNameForMessage("Promise Due Date");
        scrnMsg.dealCltDsptAmt_BH.setNameForMessage("Dispute Amount");
        scrnMsg.cltDsptRsnCd_BH.setNameForMessage("Reason");
        // START 2016/08/08 K.Kojima [QC#13005,ADD]
        scrnMsg.dealRmngBalGrsAmt_H.setAppFracDigit(2);
        // END 2016/08/08 K.Kojima [QC#13005,ADD]
        scrnMsg.dealCltPrmsAmt_AH.setAppFracDigit(2);
        scrnMsg.dealCltDsptAmt_BH.setAppFracDigit(2);

        for (int i=0; i < scrnMsg.A.length(); i++) {
            // START 2016/08/26 K.Kojima [QC#10786,ADD]
            scrnMsg.A.no(i).dealCltPrmsAmt_A.setNameForMessage("Promise Amount");
            // END 2016/08/26 K.Kojima [QC#10786,ADD]
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A2.setAppFracDigit(2);
            scrnMsg.A.no(i).dealCltPrmsAmt_A.setAppFracDigit(2);
        }

        for (int i=0; i < scrnMsg.B.length(); i++) {
            // START 2016/08/26 K.Kojima [QC#10786,ADD]
            scrnMsg.B.no(i).dealCltDsptAmt_B.setNameForMessage("Dispute Amount");
            scrnMsg.B.no(i).cltDsptNoteTxt_B.setNameForMessage("Comment");
            // END 2016/08/26 K.Kojima [QC#10786,ADD]
            scrnMsg.B.no(i).dealRmngBalGrsAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).dealCltDsptAmt_B.setAppFracDigit(2);
        }
    }
}
