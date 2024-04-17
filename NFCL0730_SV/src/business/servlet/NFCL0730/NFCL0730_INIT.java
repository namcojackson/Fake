/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0730;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0730.NFCL0730CMsg;
import business.servlet.NFCL0730.common.NFCL0730CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/01   Hitachi         K.Kojima        Update          QC#11165
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFCL0730_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL0730");

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

        NFCL0730CMsg bizMsg = new NFCL0730CMsg();
        bizMsg.setBusinessID("NFCL0730");
        bizMsg.setFunctionCode("20");

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxNum_H1, param01);
            }
        }
        // ZYPEZDItemValueSetter.setValue(scrnMsg.arTrxNum_H1,
        // "9999999990");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;
        NFCL0730CMsg bizMsg = (NFCL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0730CommonLogic.initialize(this, scrnMsg, getUserProfileService());

        // ScreenItem AddCheckItem
        NFCL0730CommonLogic.transMsgCheck(scrnMsg);

        // START 2016/07/01 K.Kojima [QC#11165,ADD]
        if ("E".equals(bizMsg.getMessageKind())) {
            this.setButtonProperties("AddLine", "AddLine", "Add", 0, null);
            this.setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);
        }
        // END 2016/07/01 K.Kojima [QC#11165,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

        scrnMsg.arTrxNum_H1.setNameForMessage("Invoice#");
        scrnMsg.dealRmngBalGrsAmt_H1.setNameForMessage("Invoice Balance");
        scrnMsg.arAdjTpCd_H1.setNameForMessage("Activity");
        scrnMsg.adjDt_H1.setNameForMessage("Adjustment Date");
        scrnMsg.dealArAdjAmt_H1.setNameForMessage("Amount");
        scrnMsg.adjCmntTxt_H1.setNameForMessage("Comments");
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        scrnMsg.xxCmntTxt_H1.setNameForMessage("Charge Account");
        // END   2022/11/10 S.Naya [QC#57252,ADD]

        scrnMsg.dealRmngBalGrsAmt_H1.setAppFracDigit(2);
        scrnMsg.dealArAdjAmt_H1.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealApplyAmt_A1.setAppFracDigit(2);
        }
    }
}
