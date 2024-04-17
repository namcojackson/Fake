/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.ZZIL0010;

import parts.common.*;
import parts.servletcommon.*;

import business.blap.ZZIL0010.ZZIL0010CMsg;
import business.servlet.ZZIL0010.common.ZZIL0010CommonLogic;
import business.servlet.ZZIL0010.constant.ZZIL0010Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0010Scrn00_CMN_Submit extends S21CommonHandler implements ZZIL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.interfaceId);
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

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

        ZZIL0010CMsg bizMsg = new ZZIL0010CMsg();
        bizMsg.setBusinessID("ZZIL0010");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;
        ZZIL0010CMsg bizMsg = (ZZIL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setButtonEnabled(CMN_BTN2[0], scrnMsg.A.getValidCount() > 0);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("ZZIL0010Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0010CommonLogic.setTableColor(scrnMsg, bizMsg);

        scrnMsg.addCheckItem(scrnMsg.interfaceId);
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
