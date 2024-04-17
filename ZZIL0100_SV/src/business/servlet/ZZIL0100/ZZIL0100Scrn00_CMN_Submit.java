/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.ZZIL0100;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0100.ZZIL0100CMsg;
import business.servlet.ZZIL0100.common.ZZIL0100CommonLogic;
import business.servlet.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0100Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.itrlIntfcId);
        scrnMsg.addCheckItem(scrnMsg.transactionId);
        scrnMsg.addCheckItem(scrnMsg.processedFlag_PS);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_RF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_RT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R2);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_UF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_UT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;

        ZZIL0100CMsg bizMsg = new ZZIL0100CMsg();
        bizMsg.setBusinessID("ZZIL0100");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;
        ZZIL0100CMsg bizMsg = (ZZIL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ( scrnMsg.A.getValidCount() > 0) {
            ZZIL0100CommonLogic.setSubmitButton(this);
        }

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("ZZIL0100Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0100CommonLogic.setTableColorForSubmit(scrnMsg, bizMsg);

        scrnMsg.addCheckItem(scrnMsg.itrlIntfcId);
        scrnMsg.addCheckItem(scrnMsg.transactionId);
        scrnMsg.addCheckItem(scrnMsg.processedFlag_PS);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_RF);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_RT);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_UF);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_UT);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R1);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_R2);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U1);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_U2);
        scrnMsg.putErrorScreen();
    }

}
