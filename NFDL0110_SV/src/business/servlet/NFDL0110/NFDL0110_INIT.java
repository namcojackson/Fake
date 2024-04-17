/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0110;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0110.NFDL0110CMsg;
import business.servlet.NFDL0110.common.NFDL0110CommonLogic;
import business.servlet.NFDL0110.constant.NFDL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/14   CUSA            K.Lee           Create          N/A
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 *</pre>
 */
public class NFDL0110_INIT extends S21INITCommonHandler implements NFDL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }


    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;
        NFDL0110CMsg bizMsg = new NFDL0110CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {

            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                if (!ZYPCommonFunc.hasValue(param01)) {
                    scrnMsg.setMessageInfo("NFDM0001E", new String[]{"Customer Account Number"});
                    scrnMsg.putErrorScreen();
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd_H, param01);
                }
            }else if (params.length == 2) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                EZDBStringItem param02 = (EZDBStringItem) params[1];
                if (!ZYPCommonFunc.hasValue(param01)) {
                    scrnMsg.setMessageInfo("NFDM0001E", new String[]{"Customer Account Number"});
                    scrnMsg.putErrorScreen();
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd_H, param01);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H, param02);
                }
            } else {
                scrnMsg.setMessageInfo("NFCM0041E");
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.setMessageInfo("NFCM0041E");
            scrnMsg.putErrorScreen();
        }

        bizMsg.setBusinessID("NFDL0110");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;
        NFDL0110CMsg bizMsg  = (NFDL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0110CommonLogic.initialize(this, scrnMsg);
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).pmtLateDaysAot_A.setAppFracDigit(0);
        }
    }
}
