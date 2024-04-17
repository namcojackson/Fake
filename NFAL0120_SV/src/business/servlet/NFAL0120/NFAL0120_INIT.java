/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0120.NFAL0120CMsg;
import business.servlet.NFAL0120.common.NFAL0120CommonLogic;
import business.servlet.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Class name: Screen Component ID : S21INITCommonHandler
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120_INIT extends S21INITCommonHandler implements NFAL0120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        NFAL0120CMsg bizMsg = new NFAL0120CMsg();
        bizMsg.setBusinessID("NFAL0120");
        bizMsg.setFunctionCode("20");
        
        //---------- CSA modification. Receive parameter from other screens
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBBigDecimalItem param_01 = (EZDBBigDecimalItem) params[0];  // INVTY_TRX_PK

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxQueryFltrTxt, param_01.getValue().toString());
                // START 2016/10/26 E.Kameishi [QC#14177,ADD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt, INVTY_TRX_PK);
                // END 2016/10/26 E.Kameishi [QC#14177,ADD]
            // START 2016/05/13 S.Fujita [QC#6457,ADD]
            } else if (params.length == 2) {
                EZDBStringItem param_01 = (EZDBStringItem) params[1];  // Receipt Doc#

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxQueryFltrTxt, param_01.getValue().toString());
            // END 2016/05/13 S.Fujita [QC#6457,ADD]
                // START 2016/10/26 E.Kameishi [QC#14177,ADD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.srchOptTxt, RCPT_NUM);
                // END 2016/10/26 E.Kameishi [QC#14177,ADD]
            }
        }
        // ------------ End

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0120CommonLogic.initButton(scrnMsg, this);
        NFAL0120CommonLogic.protectParmanentFields(scrnMsg);
        NFAL0120CommonLogic.initFocusItem(scrnMsg);
        
     // CSA mod. 
        NFAL0120CommonLogic.changeTableColorByRow(scrnMsg);
        NFAL0120CommonLogic.setInputProtectedForListInputFiled(scrnMsg);
        NFAL0120CommonLogic.initLink(this, scrnMsg);
        // ------ end
        // START 2016/12/21 E.Kameishi [QC#16730.DEL]
        // START 2016/10/26 E.Kameishi [QC#14177,ADD]
        // scrnMsg.srchOptTxt.clear();
        // END 2016/10/26 E.Kameishi [QC#14177,ADD]
        // START 2016/12/21 E.Kameishi [QC#16730.DEL]
        // START 2016/12/22 E.Kameishi [QC#16730,ADD]
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            this.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BLANK, 0, null);
            this.setButtonEnabled(BTN_INQUIRY[0], false);
        }
        // END 2016/12/22 E.Kameishi [QC#16730,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;
        NFAL0120CommonLogic.setNameForMessage(scrnMsg);
        
        scrnMsg.jrnlDealDrAmt_FM.setAppFracDigit(2);
        scrnMsg.jrnlDealDrAmt_TO.setAppFracDigit(2);
    }

}
