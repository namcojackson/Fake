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

public class ZZIL0010Scrn00_Search extends S21CommonHandler implements ZZIL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.interfaceId);
        scrnMsg.addCheckItem(scrnMsg.transactionId);
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

        String regDtFrom = scrnMsg.xxFromDt_RF.getValue();
        String regDtTo = scrnMsg.xxToDt_RT.getValue();
        String updDtFrom = scrnMsg.xxFromDt_UF.getValue();
        String updDtTo = scrnMsg.xxToDt_UT.getValue();

        String regTmFrom = scrnMsg.xxHrs_R1.getValue();
        String regTmTo = scrnMsg.xxHrs_R2.getValue();
        String updTmFrom = scrnMsg.xxHrs_U1.getValue();
        String updTmTo = scrnMsg.xxHrs_U2.getValue();

        // single category check
        if (regDtFrom.length() > 0 && regTmFrom.length() == 0) {
            scrnMsg.xxHrs_R1.setErrorInfo(1, "ZZM9032E", new String[] {"Register Time From" });
        } else if (regDtFrom.length() == 0 && regTmFrom.length() > 0) {
            scrnMsg.xxFromDt_RF.setErrorInfo(1, "ZZM9032E", new String[] {"Register Date From" });
        }

        if (regDtTo.length() > 0 && regTmTo.length() == 0) {
            scrnMsg.xxHrs_R2.setErrorInfo(1, "ZZM9032E", new String[] {"Register Time To" });
        } else if (regDtTo.length() == 0 && regTmTo.length() > 0) {
            scrnMsg.xxToDt_RT.setErrorInfo(1, "ZZM9032E", new String[] {"Register Date To" });
        }

        if (updDtFrom.length() > 0 && updTmFrom.length() == 0) {
            scrnMsg.xxHrs_U1.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Time From" });
        } else if (updDtFrom.length() == 0 && updTmFrom.length() > 0) {
            scrnMsg.xxFromDt_UF.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Date From" });
        }

        if (updDtTo.length() > 0 && updTmTo.length() == 0) {
            scrnMsg.xxHrs_U2.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Time To" });
        } else if (updDtTo.length() == 0 && updTmTo.length() > 0) {
            scrnMsg.xxToDt_UT.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Date To" });
        }

        // correlative check
        String strFrom = regDtFrom + regTmFrom;
        String strTo = regDtTo + regTmTo;

        if (regDtTo.length() > 0 && regTmTo.length() > 0) {
            int comp = strFrom.compareTo(strTo);

            if (comp > 0) {
                scrnMsg.xxToDt_RT.setErrorInfo(1, "ZZZM9010E", new String[] {"Register Date From", "Register Date To" });
            } else if (comp == 0) {
                scrnMsg.xxHrs_R2.setErrorInfo(1, "ZZZM9010E", new String[] {"Register Time From", "Register Time To" });
            }
        }

        strFrom = updDtFrom + updTmFrom;
        strTo = updDtTo + updTmTo;

        if (updDtTo.length() > 0 && updTmTo.length() > 0) {

            int comp = strFrom.compareTo(strTo);
            if (comp > 0) {
                scrnMsg.xxToDt_UT.setErrorInfo(1, "ZZZM9010E", new String[] {"Updated Date From", "Updated Date To" });
            } else if (comp == 0) {
                scrnMsg.xxHrs_U2.setErrorInfo(1, "ZZZM9010E", new String[] {"Updated Time From", "Updated Time To" });
            }
        }

        boolean errFlg = false;
        errFlg |= scrnMsg.xxHrs_R1.isError();
        errFlg |= scrnMsg.xxHrs_R2.isError();
        errFlg |= scrnMsg.xxHrs_U1.isError();
        errFlg |= scrnMsg.xxHrs_U2.isError();
        errFlg |= scrnMsg.xxFromDt_RF.isError();
        errFlg |= scrnMsg.xxToDt_RT.isError();
        errFlg |= scrnMsg.xxFromDt_UF.isError();
        errFlg |= scrnMsg.xxToDt_UT.isError();

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        if (errFlg) {
            return null;
        }

        ZZIL0010CMsg bizMsg = new ZZIL0010CMsg();
        bizMsg.setBusinessID("ZZIL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0010BMsg scrnMsg = (ZZIL0010BMsg) bMsg;
        ZZIL0010CMsg bizMsg = (ZZIL0010CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        setButtonEnabled(CMN_BTN2[0], scrnMsg.A.getValidCount() > 0);

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("ZZIL0010Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0010CommonLogic.setTableColor(scrnMsg, bizMsg);

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
