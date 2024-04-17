/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.ZZIL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.ZZIL0010.ZZIL0010CMsg;
import business.servlet.ZZIL0010.common.ZZIL0010CommonLogic;
import business.servlet.ZZIL0010.constant.ZZIL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZIL0010_INIT extends S21INITCommonHandler implements ZZIL0010Constant {

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        // ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

    }

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), businessId);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;
        ZZIL0010CMsg bizMsg = (ZZIL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZZIL0010CommonLogic.setListBox(scrnMsg);

        setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        scrnMsg.interfaceId.setNameForMessage("Interface ID");
        scrnMsg.transactionId.setNameForMessage("Transaction ID");
        scrnMsg.xxFromDt_RF.setNameForMessage("Register Date From");
        scrnMsg.xxHrs_R1.setNameForMessage("Register Time From");
        scrnMsg.xxToDt_RT.setNameForMessage("Register Date To");
        scrnMsg.xxHrs_R2.setNameForMessage("Register Time To");
        scrnMsg.xxFromDt_UF.setNameForMessage("Updated Date From");
        scrnMsg.xxHrs_U1.setNameForMessage("Updated Time From");
        scrnMsg.xxToDt_UT.setNameForMessage("Updated Date To");
        scrnMsg.xxHrs_U2.setNameForMessage("Updated Time To");
        
        scrnMsg.setFocusItem(scrnMsg.interfaceId);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

        ZZIL0010CMsg bizMsg = new ZZIL0010CMsg();
        bizMsg.setBusinessID("ZZIL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

}
